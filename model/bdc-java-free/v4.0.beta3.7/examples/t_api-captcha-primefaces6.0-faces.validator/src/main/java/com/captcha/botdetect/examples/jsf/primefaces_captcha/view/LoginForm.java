package com.captcha.botdetect.examples.jsf.primefaces_captcha.view;

import com.captcha.botdetect.web.jsf.JsfCaptcha;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean(name="jsfPrimefacesLoginForm")
@RequestScoped
public class LoginForm
{
    private String username;
    private String password;
    private String captchaCode;
    private JsfCaptcha captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public JsfCaptcha getCaptcha() {
        return this.captcha;
    }

    public void setCaptcha(JsfCaptcha captcha) {
      this.captcha = captcha;
    }

    public void login() {
        FacesMessage message = null;

        boolean loggedIn = false;
        
        // validate the Captcha to check we're not dealing with a bot
        boolean isHuman = captcha.validate(captchaCode);
        if (isHuman) {
            if ((username != null) && (username.equals("admin")) && (password != null) && (password.equals("admin"))) {
                loggedIn = true;
                // TODO: do what you want here
                // like log the user, redirect the user to other page
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "You successfully logged in to this site");
            } else {
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
            }
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Please enter a valid Captcha code");
        }

        this.captchaCode = "";

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }
}
