/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.general;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Notificacion;
import com.tncity.notificacion.NotificacionConfig;
import com.tncity.notificacion.NotificacionFacade;
import com.tncity.notificacion.NotificacionXml;
import com.tncity.util.Cadena;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class NotificacionControl extends AbstractControl<Notificacion> {

    @EJB
    NotificacionFacade notificacionFacade;

    NotificacionConfig notificacionConfig = new NotificacionConfig();

    NotificacionXml notificacionC = new NotificacionXml();

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

    public NotificacionControl() {
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

    public NotificacionConfig getNotificacionConfig() {
        return notificacionConfig;
    }

    public void setNotificacionConfig(NotificacionConfig notificacionConfig) {
        this.notificacionConfig = notificacionConfig;
    }

    public NotificacionXml getNotificacionC() {
        return notificacionC;
    }

    public void setNotificacionC(NotificacionXml notificacionC) {
        this.notificacionC = notificacionC;
    }

    public void recuperaNotificacionesConfig() {
        notificacionConfig = notificacionFacade.getNofiticacionConfigFromCache();
    }

    public void recuperaNotificacionConfigById(String id) {
        notificacionC = notificacionFacade.findByIdfromCache(new Integer(id));
    }

    public void guardarCambios() {
        notificacionFacade.saveNotificacionConfig(notificacionC, facesUtil.getCurrentUser());
        successful = true;
        facesUtil.msgOk("", "Los cambios fueron guardados!");
    }

    public String getRecuperaById() {
        obj = notificacionFacade.find(facesUtil.getFacesParamLong("idnotificacion_"));
        return null;
    }

    public long getRecuperaNumAllObj() {
        totalConsulta = (int) notificacionFacade.countAll();
        return totalConsulta;
    }

    public String getRecuperaAllObj() {
        Integer pag = facesUtil.getFacesParamInteger("pag_");
        if (pag == null) {
            pag = 0;
        }

        this.lst = notificacionFacade.listAll("idnotificacion", ascDesc, pag, maxRegLista);
        return "";
    }

}
