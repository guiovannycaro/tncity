/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.general;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.GeneralConfig;
import com.tncity.properties.Propiedad;
import com.tncity.util.Archivo;
import com.tncity.util.Cadena;
import com.tncity.util.EncryptionUtil;
import java.io.File;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SystemControl {

    FacesUtil facesUtil = FacesUtil.currentInstance();

    @EJB
    ParamFacade paramFacade;

    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void recuperaGlassfishLog() {
        GeneralConfig gc = paramFacade.getParamFromCache(GeneralConfig.class);
        if (gc.getGlassfishPathLog() == null || gc.getGlassfishPathLog().trim().isEmpty() || gc.getGlassfishPathLog().trim().equals("\\.")) {
            content = "Path GlassFish-Log no configurado !";
            return;
        }

        File f = new File(gc.getGlassfishPathLog());
        if (!f.exists()) {
            content = "Archivo '" + f.getAbsolutePath() + "' no encontrado !";
            return;
        }

        content = new Archivo(f).getContent();
    }

    public String getUrlGlassfishLog() {
        GeneralConfig gc = paramFacade.getParamFromCache(GeneralConfig.class);
        if (gc.getGlassfishPathLog() == null || gc.getGlassfishPathLog().trim().isEmpty() || gc.getGlassfishPathLog().trim().equals("\\.")) {
            return "#";
        }

        File f = new File(gc.getGlassfishPathLog());
        if (!f.exists()) {
            return "#";
        }
        return facesUtil.getProtocolHostPortPath() + "/download?f=" + Cadena.urlEncode(EncryptionUtil.encryptAES(f.getAbsolutePath(), Propiedad.getCurrentInstance().getEncryptAesKey()));
    }

}
