package com.captcha.botdetect.examples.jsf.validator;

import com.captcha.botdetect.web.servlet.Captcha;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

@FacesValidator(value="exampleValidator")
public class ExampleValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String captchaId = (String) component.getAttributes().get("captchaId");
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        // validate the Captcha to check we're not dealing with a bot
        Captcha captcha = Captcha.load(request, captchaId);
        boolean isHuman = captcha.validate((String) value);
        if (!isHuman) {
            FacesMessage message = new FacesMessage();
            throw new ValidatorException(message);
        }
    }

}
