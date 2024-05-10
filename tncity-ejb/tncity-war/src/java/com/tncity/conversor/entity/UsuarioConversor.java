package com.tncity.conversor.entity;

import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.util.BeanUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author edwar.red@gmail.com
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConversor implements Converter {

    UsuarioFacade bean = BeanUtil.lookupFacadeBean(UsuarioFacade.class);

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
        Usuario o = (Usuario) object;
        if (o.getIdusuario() == null) {
            return null;
        }
        return o.getIdusuario().toString();
    }
}
