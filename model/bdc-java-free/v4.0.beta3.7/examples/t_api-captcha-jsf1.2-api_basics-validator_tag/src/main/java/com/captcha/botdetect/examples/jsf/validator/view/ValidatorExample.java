package com.captcha.botdetect.examples.jsf.validator.view;

public class ValidatorExample {
    
    private String captchaCode;
    private boolean validated;

    public ValidatorExample() {
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public boolean isValidated() {
        return validated;
    }

    public void submit() {
        captchaCode = null;
        validated = true;
    }
}
