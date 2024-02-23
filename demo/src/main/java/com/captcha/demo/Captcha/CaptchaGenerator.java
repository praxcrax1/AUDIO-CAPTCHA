package com.captcha.demo.Captcha;

import java.util.Random;

public class CaptchaGenerator {
        private static final String text = "DEFGHJKLMNPQRTUVWXY34679@=bdefghkmnqrtu";
        private static final int CAPTCHA_LENGTH = 5;
    
        public static String getCaptcha() {
            Random random = new Random();
            StringBuilder captcha = new StringBuilder();
    
            for (int i = 0; i < CAPTCHA_LENGTH; i++) {
                int index = random.nextInt(text.length());
                captcha.append(text.charAt(index));
            }
    
            return captcha.toString();
        }
    }
    
    

