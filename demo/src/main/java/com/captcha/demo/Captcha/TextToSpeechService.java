package com.captcha.demo.Captcha;

import java.io.ByteArrayOutputStream;
import java.util.Locale;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import org.springframework.stereotype.Service;
import marytts.LocalMaryInterface;
import java.util.Base64;

@Service
public class TextToSpeechService {

    public String getStream(String text) {
        try {
            String modifiedText = textModifier(text);
            System.out.println(modifiedText);

            // File tempFile = File.createTempFile("temp_audio", ".mp3");
            // FileOutputStream fileOutputStream = new FileOutputStream(tempFile);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            LocalMaryInterface mary = null;
            mary = new LocalMaryInterface();

            AudioInputStream audio = null;
            mary.setLocale(Locale.UK);
            mary.setVoice("cmu-slt-hsmm");
            audio = mary.generateAudio(modifiedText);

            AudioSystem.write(audio, AudioFileFormat.Type.WAVE, outputStream);
            byte[] audioBytes = outputStream.toByteArray();

            // fileOutputStream.write(audioBytes);
            // fileOutputStream.close();
            outputStream.close();
            String baseString = Base64.getEncoder().encodeToString(audioBytes);
            System.out.println(baseString);
            return baseString;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String textModifier(String text) {
        StringBuilder modifiedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isLowerCase(currentChar)) {
                modifiedText.append("small ");
            }
            modifiedText.append(currentChar).append(". ");
        }

        return modifiedText.toString();
    }

    public static void main(String[] args) {
        TextToSpeechService textToSpeechService = new TextToSpeechService();
        try {
            String base64String = textToSpeechService.getStream("Hello World");
            System.out.println(base64String);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to generate base64 encoded audio stream.");
        }
    }
}
