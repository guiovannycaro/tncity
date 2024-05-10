package com.tncity.conversor.entity;


import com.tncity.facade.entity.RecaudoFacade;

import com.tncity.jpa.pojo.Recaudo;


import com.tncity.util.BeanUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author edwar.red@gmail.com
 */
@FacesConverter(forClass = Recaudo.class)
public class RecaudoConversor implements Converter {

    RecaudoFacade bean = BeanUtil.lookupFacadeBean(RecaudoFacade.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        return bean.find(Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        Recaudo o = (Recaudo) object;
        if (o.getIdbenefactor()== null) {
            return null;
        }
        return o.getIdbenefactor().toString();
    }
}
