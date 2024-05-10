package com.captcha.botdetect.examples.springmvc.basic.model;

public class BasicExample {
    
    private String captchaCode, captchaCorrect, captchaIncorrect;

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaCorrect() {
        return captchaCorrect;
    }

    public void setCaptchaCorrect(String captchaCorrect) {
        this.captchaCorrect = captchaCorrect;
    }

    public String getCaptchaIncorrect() {
        return captchaIncorrect;
    }

    public void setCaptchaIncorrect(String captchaIncorrect) {
        this.captchaIncorrect = captchaIncorrect;
    }

}
