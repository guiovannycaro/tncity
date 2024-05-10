package com.captcha.botdetect.examples.jsf.login_form_filter.view;

import com.captcha.botdetect.web.jsf.JsfCaptcha;
import com.captcha.botdetect.web.servlet.Captcha;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginForm")
@SessionScoped
public class LoginForm {

    private String username;
    private String password;
    private String captchaCode;
    private JsfCaptcha captcha;
    private String errorMessage;
    private boolean isLoggedIn;

    /**
     * Login operation.
     *
     * @return
     */
    public String login() {
        // validate captcha and user 
        if (!validateCaptcha() || !validateLogin()) {
            // Captcha or user validation failed
            return "";
        }

        //Check user and captcha passed
        // TODO
        isLoggedIn = true;
        return "secured/index?faces-redirect=true";
    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String logout() {
        username = null;
        captchaCode = null;
        errorMessage = null;
        isLoggedIn = false;
        
        return "/login";
    }

    private boolean validateCaptcha() {
        // validate the Captcha to check we're not dealing with a bot
        if (!this.captcha.validate(captchaCode)) {
            // Captcha validation failed, show error message
            errorMessage = "Invalid code";
            
            // reset captcha code textbox value
            captchaCode = null;
        
            return false;
        }
        
        // Captcha validation passed, perform protected action
        // TODO
        return true;
    }

    //Set connection database user and validate here
    private boolean validateLogin() {
        String pattern = "^[a-zA-Z0-9_]+$"; // alphanumeric chars and underscores only

        if (!username.matches(pattern) || !password.matches(pattern)) {
            errorMessage = "Invalid authentication info";
            return false;
        }
        
        if ((username.length() < 5) || (password.length() < 5)) {
            errorMessage = "Authentication failed";
            return false;
        }
          
        return true;
    }

    // Getters & Setters 
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
        return captcha;
    }

    public void setCaptcha(JsfCaptcha captcha) {
        this.captcha = captcha;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
    
    
    public String getLibInfo() {
        return Captcha.getLibInfo();
    }
    
    public boolean isFree() {
        return Captcha.isFree();
    }
}
