package com.captcha.botdetect.examples.dynamic_captcha;

import javax.servlet.http.HttpSession;

/**
 * Helper class for counting Captcha validation failures at form submission
 */
public class Counter {
    
    private HttpSession session;
    private static final String FAILED_VALIDATIONS_COUNT_KEY = "failedValidationsCount";
    
    public Counter(HttpSession session) {
        this.session = session;
    }
    
    public int getFailedValidationsCount() {
        Integer count = (Integer) session.getAttribute(FAILED_VALIDATIONS_COUNT_KEY);
        if (count == null) {
            count = 0;
        }
        
        return count.intValue();
    }
    
    public void incrementFailedValidationsCount() {
        int count = getFailedValidationsCount();
        count++;
        session.setAttribute(FAILED_VALIDATIONS_COUNT_KEY, count);
    }
    
    public void resetFailedValidationsCount() {
        session.removeAttribute(FAILED_VALIDATIONS_COUNT_KEY);
    }
}
