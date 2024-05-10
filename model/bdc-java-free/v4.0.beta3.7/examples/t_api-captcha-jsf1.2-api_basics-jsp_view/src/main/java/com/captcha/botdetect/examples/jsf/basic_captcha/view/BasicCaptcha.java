package com.captcha.botdetect.examples.jsf.basic_captcha.view;

import com.captcha.botdetect.CodeStyle;
import com.captcha.botdetect.web.jsf.JsfCaptcha;

public class BasicCaptcha {

    private String captchaCode;
    private JsfCaptcha captcha;
    private boolean correctLabelVisible, incorrectLabelVisible;

    public BasicCaptcha() {
        this.captcha = new JsfCaptcha();
        captcha.setCodeStyle(CodeStyle.NUMERIC.name());
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public JsfCaptcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(JsfCaptcha captcha) {
        this.captcha = captcha;
    }

    public boolean isCorrectLabelVisible() {
        return correctLabelVisible;
    }

    public boolean isIncorrectLabelVisible() {
        return incorrectLabelVisible;
    }


    public void validate(){
        // validate the Captcha to check we're not dealing with a bot
        boolean isHuman = captcha.validate(captchaCode);
        if (isHuman) {
            correctLabelVisible = true;
            incorrectLabelVisible = false;
        } else {
            correctLabelVisible = false;
            incorrectLabelVisible = true;
        }
        this.captchaCode = "";
    }
}
