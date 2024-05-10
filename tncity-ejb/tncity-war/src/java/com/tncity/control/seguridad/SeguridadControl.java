package com.tncity.control.seguridad;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.GeneralConfig;
import com.tncity.control.general.FacesUtil;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.security.SecurityFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Edwar Rojas - edwar.red@gmail.com
 */
@ManagedBean(name = "seguridadControl")
@RequestScoped
public class SeguridadControl {

    @EJB
    SecurityFacade securityFacade;
    @EJB
    ParamFacade paramFacade;
    FacesUtil facesUtil = FacesUtil.currentInstance();

    private boolean isPermiso(int idFunc) {
        Usuario us = facesUtil.getCurrentUser();
        if (us != null && us.getIdusuario() != null) {
            return securityFacade.isFuncAutorizada(us.getIdusuario(), idFunc);
        }
        return false;
    }

    private String getValidaPermiso(int idFunc) {
        if (isPermiso(idFunc)) {
            return null;
        } else {
            return noAccess();
        }

    }

    private String noAccess() {
        facesUtil.cerrarSesionAdminApp();
        return "<script tyle='text/javascript'>document.location.href='" + facesUtil.getProtocolHostPortContext() + "/app-admin/access.xhtml';</script>";
    }

    public boolean isPermisoAdmin() {
        if (isPermisoAdminAdmin()) {
            return true;
        }
        Usuario us = facesUtil.getCurrentUser();
        if (us != null && us.getIdusuario() != null) {
            GeneralConfig gc = paramFacade.getParamFromCache(GeneralConfig.class);
            if (gc != null && gc.getIdusuarioAdministrador() != null) {
                if (gc.getIdusuarioAdministrador().equals(us.getIdusuario())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPermisoAdminAdmin() {
        Usuario us = facesUtil.getCurrentUser();
        if (us != null && us.getIdusuario() != null) {
            if (us.getIdusuario().equals(UsuarioFacade.USUARIO_ADMIN)) {
                return true;
            }
        }
        return false;
    }

    public String getValidaPermisoAdmin() {
        if (isPermisoAdmin()) {
            return null;
        } else {
            return noAccess();
        }
    }

    public String getValidaPermisoAdminAdmin() {
        if (isPermisoAdminAdmin()) {
            return null;
        } else {
            return noAccess();
        }
    }

  
    }

                                    