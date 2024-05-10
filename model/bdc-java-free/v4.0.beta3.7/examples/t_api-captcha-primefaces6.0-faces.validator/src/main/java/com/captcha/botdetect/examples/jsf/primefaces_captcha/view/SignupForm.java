package com.captcha.botdetect.examples.jsf.primefaces_captcha.view;

import com.captcha.botdetect.web.jsf.JsfCaptcha;
import com.captcha.botdetect.web.servlet.Captcha;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean(name="jsfPrimefacesSignupForm")
@RequestScoped
public class SignupForm
{
    private String username;
    private String password;
    private String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void signup() {
        FacesMessage message = null;

        boolean signupSuccess = false;
        
        // validate the Captcha to check we're not dealing with a bot
        boolean isHuman = captcha.validate(captchaCode);
        if (isHuman) {
            if ((username != null) && (email != null) && (password != null)) {
                signupSuccess = true;
                // TODO: do what you want here
                // such as save data into database, sendmail, etc.
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Signup Success", "Thank you for signing up!");
            } else {
                signupSuccess = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Signup Error", "Please enter valid values");
            }
        } else {
            signupSuccess = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Signup Error", "Please enter a valid Captcha code");
        }

        this.captchaCode = "";

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("signupSuccess", signupSuccess);
    }

    public String getLibInfo() {
        return Captcha.getLibInfo();
    }

    public boolean isFree() {
        return Captcha.isFree();
    }
}
