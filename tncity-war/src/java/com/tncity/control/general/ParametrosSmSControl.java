/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.general;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Parametrossms;


import com.tncity.parametros.sms.ParametrosFacade;
import com.tncity.parametros.sms.ParametrosXml;
import com.tncity.parametros.sms.ParametrossmsConfig;
import com.tncity.util.Cadena;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ParametrosSmSControl extends AbstractControl<Parametrossms> {

    @EJB
    ParametrosFacade parametrosFacade;

    ParametrossmsConfig parametrosConfig = new ParametrossmsConfig();

    ParametrosXml parametrosC = new ParametrosXml();

    FacesUtil facesUtil = FacesUtil.currentInstance();
    private boolean successful = false;

    @Override
    protected AbstractFacade getFacade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String displayObj(Parametrossms obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ParametrosSmSControl() {
        super(Parametrossms.class);
        attrOrd = "idnotificacion";
        ascDesc = "DESC";
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public ParametrossmsConfig getParametrosConfig() {
        return parametrosConfig;
    }

    public void setParametrosConfig(ParametrossmsConfig parametrosConfig) {
        this.parametrosConfig = parametrosConfig;
    }

    public ParametrosXml getParametrosC() {
        return parametrosC;
    }

    public void setParametrosC(ParametrosXml parametrosC) {
        this.parametrosC = parametrosC;
    }



    public void recuperaParametrosConfig() {
        parametrosConfig = parametrosFacade.getParametrosConfigFromCache();
    }

    public void recuperaParametrosConfigById(String id) {
        parametrosC = parametrosFacade.findByIdfromCache(new Integer(id));
    }

    public void guardarCambios() {
         parametrosFacade.saveParametrosConfig(parametrosC, facesUtil.getCurrentUser());
         successful = true;
        facesUtil.msgOk("", "Los cambios fueron guardados!");
    }

    public String getRecuperaById() {
        obj = parametrosFacade.find(facesUtil.getFacesParamLong("idparametro_"));
        return null;
    }

    public long getRecuperaNumAllObj() {
        totalConsulta = (int) parametrosFacade.countAll();
        return totalConsulta;
    }

    public String getRecuperaAllObj() {
        Integer pag = facesUtil.getFacesParamInteger("pag_");
        if (pag == null) {
            pag = 0;
        }

        this.lst = parametrosFacade.listAll("idparametro", ascDesc, pag, maxRegLista);
        return "";
    }

}
