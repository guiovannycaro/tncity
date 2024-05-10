package com.tncity.conversor.entity;

import com.tncity.facade.entity.PerfilFacade;
import com.tncity.jpa.pojo.Perfil;
import com.tncity.util.BeanUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author edwar.red@gmail.com
 */
@FacesConverter(forClass = Perfil.class)
public class PerfilConversor implements Converter {

    PerfilFacade bean = BeanUtil.lookupFacadeBean(PerfilFacade.class);

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
        Perfil o = (Perfil) object;
        if (o.getIdperfil() == null) {
            return null;
        }
        return o.getIdperfil().toString();
    }
}
