package com.tncity.conversor.entity;

import com.tncity.facade.entity.BarrioFacade;
import com.tncity.jpa.pojo.Barrio;
import com.tncity.util.BeanUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author edwar.red@gmail.com
 */
@FacesConverter(forClass = Barrio.class)
public class BarrioConversor implements Converter {

    BarrioFacade bean = BeanUtil.lookupFacadeBean(BarrioFacade.class);

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
        Barrio o = (Barrio) object;
        if (o.getIdbarrio() == null) {
            return null;
        }
        return o.getIdbarrio().toString();
    }
}
