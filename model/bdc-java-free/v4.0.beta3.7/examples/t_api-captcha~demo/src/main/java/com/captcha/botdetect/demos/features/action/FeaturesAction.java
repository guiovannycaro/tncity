package com.captcha.botdetect.demos.features.action;

import com.captcha.botdetect.web.servlet.Captcha;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeaturesAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        Captcha captcha = Captcha.load(request, "exampleCaptcha");
        
        if (request.getParameter("validateCaptchaButton") != null) {
            if (captcha.validate(request.getParameter("captchaCode"))) {
                messages.put("captchaCorrect", "Correct code");
            } else {
                messages.put("captchaIncorrect", "Incorrect code");
            }
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

}
