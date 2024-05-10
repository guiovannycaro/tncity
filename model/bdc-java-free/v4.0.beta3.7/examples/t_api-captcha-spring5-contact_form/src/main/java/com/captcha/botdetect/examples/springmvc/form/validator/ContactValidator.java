package com.captcha.botdetect.examples.springmvc.form.validator;

import com.captcha.botdetect.examples.springmvc.form.model.Contact;
import com.captcha.botdetect.web.servlet.Captcha;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ContactValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> paramClass) {
        return Contact.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Contact contact = (Contact) obj;
        
        if (!isValidName(contact.getName())) {
            errors.rejectValue("name", "length", "*");
        }

        if (!isValidEmail(contact.getEmail())) {
            errors.rejectValue("email", "format", "*");
        }
        
        if (!isValidSubject(contact.getSubject())) {
            errors.rejectValue("subject", "length", "*");
        }

        if (!isValidMessage(contact.getMessage())) {
            errors.rejectValue("message", "length", "*");
        }

        if (!isCaptchaValid(contact.getHttpRequest(), contact.getCaptchaCode())) {
            errors.rejectValue("captchaCode", "captcha", "*");
        }
    }
    
    private boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        return ((name.length() > 2) && (name.length() < 30));
    }

    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }
    
    private boolean isValidSubject(String subject) {
        if (subject == null) {
            return false;
        }
        return ((subject.length() > 2) && (subject.length() < 50));
    }

    private boolean isValidMessage(String message) {
        if (message == null) {
            return false;
        }
        return ((message.length() > 2) && (message.length() < 255));
    }

    private boolean isCaptchaValid(HttpServletRequest request, String captchaCode) {
        HttpSession session = request.getSession();
        if ((session != null) && (session.getAttribute("captchaVerified") != null)) {
            return true;
        }
        // validate the Captcha to check we're not dealing with a bot
        Captcha captcha = Captcha.load(request, "springFormCaptcha");
        boolean isHuman = captcha.validate(captchaCode);
        if (isHuman) {
            if (session == null) {
                session = request.getSession(true);
            }
            session.setAttribute("captchaVerified", true);
            return true;
        } else {
            return false;
        }
    }
}
