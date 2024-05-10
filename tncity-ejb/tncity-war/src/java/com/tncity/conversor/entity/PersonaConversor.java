package com.tncity.conversor.entity;

import com.tncity.facade.entity.PersonaFacade;
import com.tncity.jpa.pojo.Persona;
import com.tncity.util.BeanUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author edwar.red@gmail.com
 */
@FacesConverter(forClass = Persona.class)
public class PersonaConversor implements Converter {

    PersonaFacade bean = BeanUtil.lookupFacadeBean(PersonaFacade.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        return bean.find(new Long(string));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        Persona o = (Persona) object;
        if (o.getIdpersona() == null) {
            return null;
        }
        return o.getIdpersona().toString();
    }
}
