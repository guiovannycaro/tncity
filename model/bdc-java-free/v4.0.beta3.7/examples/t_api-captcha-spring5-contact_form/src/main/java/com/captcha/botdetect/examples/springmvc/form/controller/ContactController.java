package com.captcha.botdetect.examples.springmvc.form.controller;

import com.captcha.botdetect.examples.springmvc.form.model.Contact;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
    
    @Autowired
    @Qualifier("contactValidator")
    private Validator validator;
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    @ModelAttribute("contact")
    public Contact createContactModel(HttpServletRequest request) {
        Contact contact = new Contact();
        contact.setHttpRequest(request);
        return contact;
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView showForm(Model model) {
        model.addAttribute("contact", new Contact());
        return new ModelAndView("contact");
    }
    
    @RequestMapping(value = "/add-contact", method = RequestMethod.POST)
    public ModelAndView onSubmit(HttpServletRequest request,
                                @ModelAttribute("contact") @Validated Contact contact,
                                BindingResult bindingResult, Model model) 
    {
        // form validation failed
        if (bindingResult.hasErrors()) {
            return new ModelAndView("contact");
        }
        
        // form validation passed
        // TODO: you can do anything you want here
        // for example: save contact data into database, sendmail, etc.
        
        // we also need to remove "captchaVerified" in session in order to show 
        // captcha for another request
        HttpSession session = request.getSession();
        session.removeAttribute("captchaVerified");
        
        return new ModelAndView("message", "contact", contact);
    }
        
}
