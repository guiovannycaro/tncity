package com.tncity.conversor.entity;

import com.tncity.facade.entity.DepartamentoestadoFacade;
import com.tncity.jpa.pojo.Departamentoestado;
import com.tncity.util.BeanUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author edwar.red@gmail.com
 */
@FacesConverter(forClass = Departamentoestado.class)
public class DepartamentoestadoConversor implements Converter {

    DepartamentoestadoFacade bean = BeanUtil.lookupFacadeBean(DepartamentoestadoFacade.class);

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
        Departamentoestado o = (Departamentoestado) object;
        if (o.getIddepartamento() == null) {
            return null;
        }
        return o.getIddepartamento().toString();
    }
}
