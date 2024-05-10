/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.general;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Notificacion;
import com.tncity.notificacion.NotificacionXml;
import com.tncity.notificacion.smstext.NotificacionSmSConfig;
import com.tncity.notificacion.smstext.NotificacionSmSFacade;
import com.tncity.notificacion.smstext.NotificacionVarSmSXml;
import com.tncity.util.Cadena;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class NotificacionSmSControl extends AbstractControl<Notificacion> {

    @EJB
    NotificacionSmSFacade notificacionsmsFacade;

    NotificacionSmSConfig notificacionConfig = new NotificacionSmSConfig();

    NotificacionVarSmSXml notificacionM = new NotificacionVarSmSXml();

 

    FacesUtil facesUtil = FacesUtil.currentInstance();
    private boolean successful = false;

    @Override
    protected AbstractFacade getFacade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String displayObj(Notificacion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NotificacionSmSControl() {
        super(Notificacion.class);
        attrOrd = "idnotificacion";
        ascDesc = "DESC";
    }

     public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public NotificacionSmSConfig getNotificacionConfig() {
        return notificacionConfig;
    }

    public void setNotificacionConfig(NotificacionSmSConfig notificacionConfig) {
        this.notificacionConfig = notificacionConfig;
    }

    public NotificacionVarSmSXml getNotificacionM() {
        return notificacionM;
    }

    public void setNotificacionM(NotificacionVarSmSXml notificacionM) {
        this.notificacionM = notificacionM;
    }



 
    public void recuperaNotificacionesConfig() {
        notificacionConfig = notificacionsmsFacade.getNofiticacionConfigFromCache();
    }


    public String getRecuperaById() {
        obj = notificacionsmsFacade.find(facesUtil.getFacesParamLong("idnotificacion_"));
        return null;
    }

    public long getRecuperaNumAllObj() {
        totalConsulta = (int) notificacionsmsFacade.countAll();
        return totalConsulta;
    }

    public String getRecuperaAllObj() {
        Integer pag = facesUtil.getFacesParamInteger("pag_");
        if (pag == null) {
            pag = 0;
        }

        this.lst = notificacionsmsFacade.listAll("idnotificacion", ascDesc, pag, maxRegLista);
        return "";
    }

}
