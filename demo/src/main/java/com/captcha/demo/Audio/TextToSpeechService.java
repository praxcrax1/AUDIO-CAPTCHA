package com.captcha.demo.Audio;


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

	public String getStream(String text)
	{
    try{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		LocalMaryInterface mary = null;
		mary = new LocalMaryInterface();
		AudioInputStream audio = null;
		mary.setLocale(Locale.US);
		mary.setVoice("cmu-rms-hsmm");
		audio = mary.generateAudio(text);
		AudioSystem.write(audio, AudioFileFormat.Type.WAVE, outputStream);
		byte[] audioBytes = outputStream.toByteArray(); 
        String baseString = Base64.getEncoder().encodeToString(audioBytes);
        return baseString;
    }
    catch (Exception e) {
        e.printStackTrace();
        return null;
	}
}
    public static void main(String[] args) {
        TextToSpeechService textToSpeechService = new TextToSpeechService();
        try {
            String base64String = textToSpeechService.getStream("Hello");
            System.out.println(base64String);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to generate base64 encoded audio stream.");
        }
    }
}
