package com.captcha.demo.CaptchaDTO;

public class DataDTO {
    private Long id;
    private String base64value;
    private String captcha;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase64Value() {
        return base64value;
    }

    public void setBase64Value(String base64value) {
        this.base64value = base64value;
    }
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String value) {
        this.captcha = value;
    }
}
