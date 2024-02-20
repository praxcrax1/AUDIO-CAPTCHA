package com.captcha.demo.Audio;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import javax.sound.sampled.AudioFileFormat;
import org.springframework.stereotype.Service;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

 

@Service
public class TextToSpeechService {

    public String convertTextToBase64(String text) {
        // Initialize FreeTTS voice
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice("kevin");
        if (voice == null) {
            throw new IllegalArgumentException("Cannot find a voice named kevin16.");
        }
        voice.allocate();

        try {
            File tempFile = File.createTempFile("temp", ""); 
            SingleFileAudioPlayer audioPlayer = new SingleFileAudioPlayer(tempFile.getAbsolutePath(), AudioFileFormat.Type.WAVE);
            voice.setAudioPlayer(audioPlayer);
            voice.speak(text);
            audioPlayer.close();
            byte[] audioBytes = Files.readAllBytes(tempFile.toPath());
            System.err.println(Arrays.toString(audioBytes));
            String base64String = Base64.getEncoder().encodeToString(audioBytes);
            System.out.println("Base 64:" + base64String);
            tempFile.delete();
            return base64String;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            voice.deallocate();
        }
    }

    public static void main(String[] args) {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        TextToSpeechService textToSpeechService = new TextToSpeechService();
        String base64String = textToSpeechService.convertTextToBase64("A B C D");
        if (base64String != null){
            System.out.println(base64String);
        }
        else{
            System.err.println("Failed");
        }
    }

}