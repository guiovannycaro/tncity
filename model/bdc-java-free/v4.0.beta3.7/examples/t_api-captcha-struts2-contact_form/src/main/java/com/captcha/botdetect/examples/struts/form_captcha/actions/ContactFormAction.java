package com.captcha.botdetect.examples.struts.form_captcha.actions;

import org.apache.struts2.ServletActionContext;

import com.captcha.botdetect.examples.struts.form_captcha.bean.User;
import com.captcha.botdetect.web.servlet.Captcha;
import com.opensymphony.xwork2.ActionSupport;

public class ContactFormAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    
    private User user;
    private String captchaCode;

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String execute() {
        return SUCCESS;
    }
    
    public void validate() {
    	if (captchaCode == null) {
            addFieldError("captchaCode", "Please enter the verification code.");
    	} else {
            Captcha captcha = Captcha.load(ServletActionContext.getRequest(), "formCaptcha");
            boolean isHuman = captcha.validate(captchaCode);
            if (!isHuman) {
                addFieldError("captchaCode", "Incorrect code.");
            }

            // reset captcha code textbox
            captchaCode = null;
        }
    }
}
