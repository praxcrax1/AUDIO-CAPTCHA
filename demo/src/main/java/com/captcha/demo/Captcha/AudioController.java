package com.captcha.demo.Captcha;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.captcha.demo.CaptchaDTO.DataDTO;

@RestController
@RequestMapping("/api/text")
@CrossOrigin(origins = "*")
public class AudioController {

private final TextToSpeechService textToSpeechService;
public AudioController(TextToSpeechService textToSpeechService) {
        this.textToSpeechService = textToSpeechService;
    }
   
@GetMapping("/generateCaptcha")
public DataDTO generateCaptcha() {
    String captcha = CaptchaGenerator.getCaptcha();
    DataDTO dataDTO = new DataDTO();
    dataDTO.setId(1L);
    dataDTO.setCaptcha(captcha);
    String base64Value = textToSpeechService.getStream(dataDTO.getCaptcha());
    dataDTO.setBase64Value(base64Value);
    return dataDTO;
}
}
