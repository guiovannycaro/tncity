package com.tncity.conversor.entity;

import com.tncity.facade.entity.LocalidadFacade;
import com.tncity.jpa.pojo.Localidad;
import com.tncity.util.BeanUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author edwar.red@gmail.com
 */
@FacesConverter(forClass = Localidad.class)
public class LocalidadConversor implements Converter {

   LocalidadFacade bean = BeanUtil.lookupFacadeBean(LocalidadFacade.class);

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
        Localidad o = (Localidad) object;
        if (o.getIdlocalidad()== null) {
            return null;
        }
        return o.getIdlocalidad().toString();
    }
}
