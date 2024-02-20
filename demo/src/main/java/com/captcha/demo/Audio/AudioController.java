package com.captcha.demo.Audio;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.captcha.demo.AudioDTO.DataDTO;

@RestController
@RequestMapping("/api/text")
@CrossOrigin(origins = "*")
public class AudioController {

private final TextToSpeechService textToSpeechService;
public AudioController(TextToSpeechService textToSpeechService) {
        this.textToSpeechService = textToSpeechService;
    }
   
@GetMapping("/helloWorld")
public DataDTO helloWorld() {
    DataDTO dataDTO = new DataDTO();
    dataDTO.setId(103L);
    dataDTO.setValue("Hello");
    String base64Value = textToSpeechService.getStream(dataDTO.getValue());
    dataDTO.setValue(base64Value);
    return dataDTO;
}
}
