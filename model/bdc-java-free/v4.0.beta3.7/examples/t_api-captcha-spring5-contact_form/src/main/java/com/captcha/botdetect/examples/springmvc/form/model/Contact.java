package com.captcha.botdetect.examples.springmvc.form.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Contact {
    
    private String name;
    private String email;
    private String subject;
    private String message;
    
    private HttpServletRequest httpRequest;
    
    private String captchaCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
    
    
    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public boolean isCaptchaVerified() {
        if (this.httpRequest == null) {
            return false;
        }
        
        HttpSession session = httpRequest.getSession();
        if (session == null) {
            return false;
        }
        
        return (session.getAttribute("captchaVerified") != null);
    }
}
