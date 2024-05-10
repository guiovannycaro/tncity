package com.captcha.botdetect.examples.jsp.form;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.captcha.botdetect.web.servlet.Captcha;

public class CaptchaFormAction extends HttpServlet {
    
    HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
        Captcha captcha = Captcha.load(request, "formCaptcha");
        boolean messageValid = true;
        String destinationPage = "/index.jsp";

        session = request.getSession(true);

        if (session.getAttribute("isCaptchaSolved") == null) {
            // validate the Captcha to check we're not dealing with a bot
            boolean isHuman = captcha.validate(request.getParameter("captchaCode"));
            if (isHuman) {
                // Captcha validation passed, perform protected action
                session.setAttribute("isCaptchaSolved", true);
            } else {
                // Captcha validation failed, show error message
                messages.put("captchaIncorrect", "*");
                messageValid = false;
            }
        }

        if (!isValidName(request.getParameter("name"))) {
            messages.put("nameIncorrect", "*");
            messageValid = false;
        }

        if (!isValidEmail(request.getParameter("email"))) {
            messages.put("emailIncorrect", "*");
            messageValid = false;
        }

        if (!isValidMessage(request.getParameter("message"))) {
            messages.put("messageIncorrect", "*");
            messageValid = false;
        }

        if (messageValid) {
            saveMessage(request.getParameter("name"), request.getParameter("email"), request.getParameter("message"));
            session.removeAttribute("isCaptchaSolved");
            destinationPage = "/messages.jsp";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
        dispatcher.forward(request, response);
    }

    private boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        return name.length() > 2 && name.length() < 30;
    }

    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }

    private boolean isValidMessage(String message) {
        if (message == null) {
            return false;
        }
        return (message.length() > 2) && (message.length() < 255);
    }

    private void saveMessage(String name, String email, String messageText) {
        Date timeStamp = new Date();
        String message = name + " (" + email + ") says: " + messageText;
        session.setAttribute("Message_" + timeStamp.getTime(), message);
    }
}
