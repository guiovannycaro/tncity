package com.captcha.botdetect.examples.jsp.login_form;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.captcha.botdetect.web.servlet.Captcha;

public class LoginFormAction extends HttpServlet {
    
    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession(true);
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
        boolean isCaptchaSolved = (session.getAttribute("isCaptchaSolved") != null);
   
        // CAPTCHA user input validation
        if (!isCaptchaSolved) {
            Captcha captcha = Captcha.load(request, "loginCaptcha");
            // validate the Captcha to check we're not dealing with a bot
            boolean isHuman = captcha.validate(request.getParameter("captchaCode"));
            if (!isHuman) {
                // Captcha validation failed, show error message
                messages.put("captchaIncorrect", "Incorrect code");
            } else {
                isCaptchaSolved = true;
                session.setAttribute("isCaptchaSolved", true);
            }
        }
        
        // Captcha validation passed, only now do we perform the protected action 
        // (try to authenticate the user)
        if (isCaptchaSolved) {
            // sumbitted login data
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // check login format
            boolean isValidLoginFormat = validateLogin(username, password);
       
            if (!isValidLoginFormat) {
                // invalid login format, show error message
                messages.put("loginError", "Invalid authentication info");
            }
            
            // authenticate the user
            if (isValidLoginFormat) {
                boolean isAuthenticated = authenticate(username, password);
                if (!isAuthenticated) {
                    // failing authentication 3 times shows the Captcha again
                    int count = 0;
                    if (session.getAttribute("failedAuthCount") != null) {
                        count = (Integer) session.getAttribute("failedAuthCount");
                    }
                    count++;
                    if (count > 2) {
                        session.removeAttribute("isCaptchaSolved");
                        count = 0;
                    }
                    session.setAttribute("failedAuthCount", count);
                    
                    messages.put("loginError", "Authentication failed");
                } else {
                    request.setAttribute("isLoggedIn", true);
                    session.removeAttribute("isCaptchaSolved");
                }
            }
        }
 
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private boolean validateLogin(String username, String password) {
        String pattern = "^[a-zA-Z0-9_]+$"; // alphanumeric chars and underscores only
        if ((username != null) && (password != null)) {
            return (username.matches(pattern) && password.matches(pattern));
        } else {
            return false;
        }
    }

    private boolean authenticate(String username, String password) {
        return (username.length() > 4) && (password.length() > 4);
    }
}
