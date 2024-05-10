package com.tncity.conversor.entity;

import com.tncity.facade.entity.PaisFacade;
import com.tncity.jpa.pojo.Pais;
import com.tncity.util.BeanUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author edwar.red@gmail.com
 */
@FacesConverter(forClass = Pais.class)
public class PaisConversor implements Converter {

    PaisFacade bean = BeanUtil.lookupFacadeBean(PaisFacade.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        return bean.find(new Integer(string));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        Pais o = (Pais) object;
        if (o.getIdpais() == null) {
            return null;
        }
        return o.getIdpais().toString();
    }
}
