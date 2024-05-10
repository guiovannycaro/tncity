/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.PerfilFacade;
import com.tncity.facade.entity.PerfilHasUsuarioFacade;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Perfil;
import com.tncity.jpa.pojo.PerfilHasUsuario;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.util.SerialClone;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "perfilHasUsuarioControl")
@RequestScoped
public class PerfilHasUsuarioControl extends AbstractControl<PerfilHasUsuario> {

    @EJB
    PerfilHasUsuarioFacade facade;
    @EJB
    private PerfilFacade perfilFacade;
    @EJB
    private UsuarioFacade usuarioFacade;

    public PerfilHasUsuarioControl() {
        super(PerfilHasUsuario.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    protected PerfilHasUsuarioFacade getPerfilHasUsuarioFacade() {
        return facade;
    }

    @Override
    protected String displayObj(PerfilHasUsuario obj) {
        return obj.getIdpu();
    }          
     

    public void nuevo() {
        obj.setIdpu("");
        obj.setIdusuario(new Usuario(facesUtil.getFacesParamInteger("idusuario_")));

        successful = false;
        StringBuilder err = new StringBuilder();
        facade.nuevo(obj, err);
        if (err.toString().trim().isEmpty()) {
            facesUtil.msgOk("", "Registro Creado !");
            successful = true;
        } else {
            facesUtil.msgError("ALERTA", err.toString());
            successful = false;
        }
    }

    public void nuevoUsuario() {
        obj.setIdpu("");
        obj.setIdperfil(new Perfil(facesUtil.getFacesParamInteger("idperfil_")));

        successful = false;
        StringBuilder err = new StringBuilder();
        facade.nuevo(obj, err);
        if (err.toString().trim().isEmpty()) {
            facesUtil.msgOk("", "Registro Creado !");
            successful = true;
        } else {
            facesUtil.msgError("ALERTA", err.toString());
            successful = false;
        }
    }

    public void editar() {
        editDefault();
    }

    public void recuperaCountTotalRegPerfiles() {
        Integer idusuario = facesUtil.getFacesParamInteger("idusuario_");
        totalConsulta = getPerfilHasUsuarioFacade().countAllPerfiles(idusuario);
    }

    public void recuperaCountTotalRegUsuarios() {
        Integer idperfil = facesUtil.getFacesParamInteger("idperfil_");
        totalConsulta = getPerfilHasUsuarioFacade().countAllUsuarios(idperfil);
    }

    public void recuperaAllRegPaginationNoLightPerfiles() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        Integer idusuario = facesUtil.getFacesParamInteger("idusuario_");

        String cOrder = facesUtil.getFacesParamValue("cOrder_");
        if (cOrder != null && !cOrder.trim().isEmpty()) {
            attrOrd = cOrder;
        }

        String ascDesc_ = facesUtil.getFacesParamValue("ascDesc_");

        if (ascDesc_ != null && !ascDesc_.trim().isEmpty()) {
            ascDesc = ascDesc_;
        }

        lst = getPerfilHasUsuarioFacade().listAllPerfiles(idusuario, attrOrd, ascDesc, first, maxRegLista);

    }

    public void recuperaAllRegPaginationNoLightUsuarios() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        Integer idperfil = facesUtil.getFacesParamInteger("idperfil_");

        String cOrder = facesUtil.getFacesParamValue("cOrder_");
        if (cOrder != null && !cOrder.trim().isEmpty()) {
            attrOrd = cOrder;
        }

        String ascDesc_ = facesUtil.getFacesParamValue("ascDesc_");

        if (ascDesc_ != null && !ascDesc_.trim().isEmpty()) {
            ascDesc = ascDesc_;
        }

        lst = getPerfilHasUsuarioFacade().listAllUsuarios(idperfil, attrOrd, ascDesc, first, maxRegLista);

    }

    public String getEliminar() {
        setSuccessful(false);

        try {
            StringBuilder error = new StringBuilder();

            Integer idperfil = facesUtil.getFacesParamInteger("idperfil_");
            Integer idusuario = facesUtil.getFacesParamInteger("idusuario_");
          
            facade.eliminar(idperfil, idusuario, error);

            if (error.toString().isEmpty()) {
                facesUtil.msgOk("", "¡El perfil se ha eliminado exitosamente!");
                setSuccessful(true);
            } else {
                facesUtil.msgError("ATENCIÓN: ", error.toString());
            }
        } catch (Exception e) {
            facesUtil.msgError("FALLA: ", "eliminando la parte ! =>" + e.toString());
        }

        return null;

    }

    public String getRecuperaParams() {

        Integer idp = facesUtil.getFacesParamInteger("idperfil_");

        if (idp != null) {
            Perfil t = perfilFacade.find(idp);
            t = SerialClone.clone(t);
            obj.setIdperfil(t);

        }

        Integer idu = facesUtil.getFacesParamInteger("idusuario_");

        if (idu != null) {
            Usuario t = usuarioFacade.find(idu);
            t = SerialClone.clone(t);
            obj.setIdusuario(t);

        }

        return "";
    }

}
