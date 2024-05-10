package com.captcha.botdetect.examples.dynamic_captcha;

import com.captcha.botdetect.CaptchaRandomization;
import com.captcha.botdetect.CodeStyle;
import com.captcha.botdetect.web.CaptchaHttpCommand;
import com.captcha.botdetect.web.servlet.Captcha;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CaptchaFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        CaptchaHttpCommand command = CaptchaHttpCommand.getCaptchaCommand(request.getParameter("get"));
        
        if ((command == CaptchaHttpCommand.GET_IMAGE)
                || (command == CaptchaHttpCommand.GET_SOUND)
                || (command == CaptchaHttpCommand.GET_VALIDATION_RESULT)) {
            String captchaId = request.getParameter("c");
            Captcha captcha = Captcha.load(request, captchaId);
            
            // Dynamic Captcha settings depending on failed validation attempts: increase Captcha 
            // difficulty according to number of previously failed validations
            Counter counter = new Counter(((HttpServletRequest) request).getSession());
            Integer count = counter.getFailedValidationsCount();
            if (count < 3) {
                captcha.setCodeLength(CaptchaRandomization.getRandomCodeLength(3, 4));
                captcha.setCodeStyle(CodeStyle.NUMERIC);
                captcha.setCodeTimeout(600); // 10 minutes
            } else if (count < 10) {
                captcha.setCodeLength(CaptchaRandomization.getRandomCodeLength(4, 6));
                captcha.setCodeStyle(CodeStyle.ALPHA);
                captcha.setCodeTimeout(180); // 3 minutes
            } else {
                captcha.setCodeLength(CaptchaRandomization.getRandomCodeLength(6, 9));
                captcha.setCodeStyle(CodeStyle.ALPHANUMERIC);
                captcha.setCodeTimeout(60); // 1 minutes
            }
            
            // Set Captcha locale to Chinese for requests from a certain IP range
            String testIPRange = "223.254.";
            if (request.getRemoteAddr().startsWith(testIPRange)) {
                captcha.setCodeStyle(CodeStyle.ALPHA);
                captcha.setLocale("cmn");
            }

            // Save captcha settings
            captcha.save(captcha);
        }
        
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
    }
}
