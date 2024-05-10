package com.captcha.botdetect.examples.angularjs.contact_form;

import com.captcha.botdetect.web.servlet.SimpleCaptcha;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        
        response.setContentType("application/json; charset=utf-8");
        
        JsonParser parser = new JsonParser();
        JsonObject formDataObj = (JsonObject) parser.parse(request.getReader());
        
        String name = formDataObj.get("name").getAsString();
        String email = formDataObj.get("email").getAsString();
        String subject = formDataObj.get("subject").getAsString();
        String message = formDataObj.get("message").getAsString();
        String userEnteredCaptchaCode = formDataObj.get("userEnteredCaptchaCode").getAsString();
        String captchaId = formDataObj.get("captchaId").getAsString();

        // validate the form data
        Map<String, String> errors = new HashMap<String, String>();
        
        if (!isValidName(name)) {
            errors.put("name", "Name must be at least 3 chars long!");
        }
        
        if (!isValidEmail(email)) {
            errors.put("email", "Email is invalid!");
        }
        
        if (!isValidSubject(subject)) {
            errors.put("message", "Subject must be at least 10 chars long!");
        }
        
        if (!isValidMessage(message)) {
            errors.put("message", "Message must be at least 10 chars long!");
        }
        
        // validate the user entered captcha code        
        if (!isCaptchaCorrect(request, userEnteredCaptchaCode, captchaId)) {
            errors.put("userEnteredCaptchaCode", "CAPTCHA validation failed!");
            // TODO: consider logging the attempt
        }
        
        if (errors.isEmpty()) {
            // TODO: all validations succeeded; execute the protected action
            // (send email, write to database, etc...)
        }
        
        // create an object that stores the validation result
        ContactValidationResult validationResult = new ContactValidationResult();
        validationResult.setSuccess(errors.isEmpty());
        validationResult.setErrors(errors);
        
        try {
            // return the json string with the validation result to the frontend
            out.write(gson.toJson(validationResult));
        } finally {
            out.close();
        }
    }
    
    // the captcha validation function
    private boolean isCaptchaCorrect(HttpServletRequest request, String userEnteredCaptchaCode, String captchaId) {
        // create a captcha instance to be used for the captcha validation        
        SimpleCaptcha captcha = SimpleCaptcha.load(request);
        // execute the captcha validation        
        return captcha.validate(userEnteredCaptchaCode, captchaId);
    }
    
    private boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        return (name.length() >= 3);
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
        return (subject.length() > 9) && (subject.length() < 255);
    }
    
    private boolean isValidMessage(String message) {
        if (message == null) {
            return false;
        }
        return (message.length() > 9) && (message.length() < 255);
    }
}

class ContactValidationResult {
    private boolean success;
    private Map<String, String> errors;

    public ContactValidationResult() {
        errors = new HashMap<String, String>();
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
