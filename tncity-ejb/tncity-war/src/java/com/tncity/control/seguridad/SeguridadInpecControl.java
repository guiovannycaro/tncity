package com.tncity.control.seguridad;

import com.tncity.config.ParamFacade;

import com.tncity.control.general.FacesBenefactorUtil;


import com.tncity.jpa.pojo.Benefactor;
import com.tncity.security.SecurityBenefactorFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Edwar Rojas - edwar.red@gmail.com
 */
@ManagedBean(name = "seguridadippecControl")
@RequestScoped
public class SeguridadInpecControl {

    @EJB
    SecurityBenefactorFacade securityFacade;
    @EJB
    ParamFacade paramFacade;
    FacesBenefactorUtil facesUtil = FacesBenefactorUtil.currentInstance();





    private String noAccess() {
        facesUtil.cerrarSesionAdminApp();
        return "<script tyle='text/javascript'>document.location.href='" + facesUtil.getProtocolHostPortContext() + "/app-admin/app-familiar/access.xhtml';</script>";
    }

    }

                                    