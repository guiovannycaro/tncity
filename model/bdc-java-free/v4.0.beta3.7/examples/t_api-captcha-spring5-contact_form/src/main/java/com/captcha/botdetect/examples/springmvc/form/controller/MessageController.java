package com.captcha.botdetect.examples.springmvc.form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {
    
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ModelAndView showForm(Model model) {
        return new ModelAndView("message");
    }
    
}
