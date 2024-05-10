package com.captcha.botdetect.examples.springmvc.basic.controller;

import com.captcha.botdetect.examples.springmvc.basic.model.BasicExample;
import com.captcha.botdetect.web.servlet.Captcha;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicController {
    
    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("basic");
    }
    
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ModelAndView onSubmit(HttpServletRequest request, 
                                @Valid @ModelAttribute("basicExample")BasicExample basicExample) 
    {
        // validate the Captcha to check we're not dealing with a bot
        Captcha captcha = Captcha.load(request, "basicExample");
        boolean isHuman = captcha.validate(basicExample.getCaptchaCode());
        
        if (isHuman) {
          basicExample.setCaptchaCorrect("Correct code");
          basicExample.setCaptchaIncorrect("");
        } else {
          basicExample.setCaptchaCorrect("");
          basicExample.setCaptchaIncorrect("Incorrect code");
        }

        basicExample.setCaptchaCode("");
        
        return new ModelAndView("basic", "basicExample", basicExample);
    }
        
}
