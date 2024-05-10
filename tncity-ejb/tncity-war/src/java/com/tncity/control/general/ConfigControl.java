/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.general;

import com.tncity.config.AbstractConfig;
import com.tncity.config.ParamFacade;

import com.tncity.config.pojoaux.GeneralConfig;
import com.tncity.config.pojoaux.GeneralBenefactorConfig;
import com.tncity.util.SmtpUtil;
import com.tncity.config.pojoaux.EmailConfig;
import com.tncity.config.pojoaux.CondicionesyTerminos;
import com.tncity.config.pojoaux.PagosInteligentesConfig;
import com.tncity.config.pojoaux.PagosInteligentesEnvioPago;
import com.tncity.config.pojoaux.PagosInteligentesMostrarPago;
import com.tncity.config.pojoaux.PagosInteligentesRta;
import com.tncity.config.pojoaux.ParametrosSmSText;
import com.tncity.config.pojoaux.ParametrosSmsConfig;
import com.tncity.config.pojoaux.ParametrosSmsTeleamigoConfig;
import com.tncity.config.pojoaux.PasarelasPagosConfig;
import com.tncity.config.pojoaux.PayzendConfig;

import com.tncity.config.pojoaux.TelefoniaConfig;
import com.tncity.config.pojoaux.TnPagosConfig;
import com.tncity.notificacion.NotificacionFacade;
import com.tncity.parametros.sms.ParametrosFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ConfigControl {

    @EJB
    ParamFacade paramFacade;

    @EJB
    NotificacionFacade notificacionFacade;

    @EJB
    ParametrosFacade parametrosFacade;

    GeneralConfig objGeneralConfig = new GeneralConfig();
    GeneralBenefactorConfig objGeneralBenefactorConfig = new GeneralBenefactorConfig();
    TelefoniaConfig objTelefoniaSistema = new TelefoniaConfig();
    PagosInteligentesConfig objPagosInteligentesConfig = new PagosInteligentesConfig();
    TnPagosConfig objTnPagosConfig = new TnPagosConfig();
    EmailConfig objEmailSistema = new EmailConfig();
    CondicionesyTerminos objCondicionesyTerminosSistema = new CondicionesyTerminos();
    PasarelasPagosConfig objPasarelasPagosConfig = new PasarelasPagosConfig();

    ParametrosSmSText objParametrosSmSText = new ParametrosSmSText();
    ParametrosSmsConfig objParametrosSmsConfig = new ParametrosSmsConfig();
    ParametrosSmsTeleamigoConfig objParametrosSmsTeleamigoConfig = new ParametrosSmsTeleamigoConfig();

    PagosInteligentesEnvioPago objPagosInteligentesEnvioPago = new PagosInteligentesEnvioPago();
    PagosInteligentesMostrarPago objPagosInteligentesMostrarPago = new PagosInteligentesMostrarPago();
    PagosInteligentesRta objPagosInteligentesRta = new PagosInteligentesRta();

    PayzendConfig objPayzendConfig = new PayzendConfig();
    
    
    String mail;
    String asunto;

    String contenido;

    public PayzendConfig getObjPayzendConfig() {
        return objPayzendConfig;
    }

    public void setObjPayzendConfig(PayzendConfig objPayzendConfig) {
        this.objPayzendConfig = objPayzendConfig;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    FacesUtil facesUtil = FacesUtil.currentInstance();
    private boolean successful = false;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public void guardar(AbstractConfig objConfig) {
        paramFacade.saveParam(objConfig, facesUtil.getCurrentUser());
        successful = true;
        facesUtil.msgOk("", "Cambios Guardados!");
    }

    public AbstractConfig config(AbstractConfig objConfig) {
        return paramFacade.getParamFromCache(objConfig.getClass());
    }

    public GeneralConfig getObjGeneralConfig() {
        return objGeneralConfig;
    }

    public void setObjGeneralConfig(GeneralConfig objGeneralConfig) {
        this.objGeneralConfig = objGeneralConfig;
    }

    public TelefoniaConfig getObjTelefoniaSistema() {
        return objTelefoniaSistema;
    }

    public void setObjTelefoniaSistema(TelefoniaConfig objTelefoniaSistema) {
        this.objTelefoniaSistema = objTelefoniaSistema;
    }

    public EmailConfig getObjEmailSistema() {
        return objEmailSistema;
    }

    public void setObjEmailSistema(EmailConfig objEmailSistema) {
        this.objEmailSistema = objEmailSistema;
    }

    public CondicionesyTerminos getObjCondicionesyTerminosSistema() {
        return objCondicionesyTerminosSistema;
    }

    public void setObjCondicionesyTerminosSistema(CondicionesyTerminos objCondicionesyTerminosSistema) {
        this.objCondicionesyTerminosSistema = objCondicionesyTerminosSistema;
    }

    public PagosInteligentesConfig getObjPagosInteligentesConfig() {
        return objPagosInteligentesConfig;
    }

    public void setObjPagosInteligentesConfig(PagosInteligentesConfig objPagosInteligentesConfig) {
        this.objPagosInteligentesConfig = objPagosInteligentesConfig;
    }

    public TnPagosConfig getObjTnPagosConfig() {
        return objTnPagosConfig;
    }

    public void setObjTnPagosConfig(TnPagosConfig objTnPagosConfig) {
        this.objTnPagosConfig = objTnPagosConfig;
    }

    public void setObjGeneralBenefactorConfig(GeneralBenefactorConfig objGeneralBenefactorConfig) {
        this.objGeneralBenefactorConfig = objGeneralBenefactorConfig;
    }

    public PasarelasPagosConfig getObjPasarelasPagosConfig() {
        return objPasarelasPagosConfig;
    }

    public void setObjPasarelasPagosConfig(PasarelasPagosConfig objPasarelasPagosConfig) {
        this.objPasarelasPagosConfig = objPasarelasPagosConfig;
    }

    public ParametrosSmsConfig getObjParametrosSmsConfig() {
        return objParametrosSmsConfig;
    }

    public void setObjParametrosSmsConfig(ParametrosSmsConfig objParametrosSmsConfig) {
        this.objParametrosSmsConfig = objParametrosSmsConfig;
    }

    public ParametrosSmsTeleamigoConfig getObjParametrosSmsTeleamigoConfig() {
        return objParametrosSmsTeleamigoConfig;
    }

    public void setObjParametrosSmsTeleamigoConfig(ParametrosSmsTeleamigoConfig objParametrosSmsTeleamigoConfig) {
        this.objParametrosSmsTeleamigoConfig = objParametrosSmsTeleamigoConfig;
    }

    public ParametrosSmSText getObjParametrosSmSText() {
        return objParametrosSmSText;
    }

    public void setObjParametrosSmSText(ParametrosSmSText objParametrosSmSText) {
        this.objParametrosSmSText = objParametrosSmSText;
    }

    public PagosInteligentesEnvioPago getObjPagosInteligentesEnvioPago() {
        return objPagosInteligentesEnvioPago;
    }

    public void setObjPagosInteligentesEnvioPago(PagosInteligentesEnvioPago objPagosInteligentesEnvioPago) {
        this.objPagosInteligentesEnvioPago = objPagosInteligentesEnvioPago;
    }

    public PagosInteligentesMostrarPago getObjPagosInteligentesMostrarPago() {
        return objPagosInteligentesMostrarPago;
    }

    public void setObjPagosInteligentesMostrarPago(PagosInteligentesMostrarPago objPagosInteligentesMostrarPago) {
        this.objPagosInteligentesMostrarPago = objPagosInteligentesMostrarPago;
    }

    public PagosInteligentesRta getObjPagosInteligentesRta() {
        return objPagosInteligentesRta;
    }

    public void setObjPagosInteligentesRta(PagosInteligentesRta objPagosInteligentesRta) {
        this.objPagosInteligentesRta = objPagosInteligentesRta;
    }
    
    
    

    public void guardarEmailConfig(AbstractConfig objConfig) {
        guardar(objConfig);
        EmailConfig mailConfig = paramFacade.getParamFromCache(EmailConfig.class);
        StringBuilder err = new StringBuilder();
        try {
            SmtpUtil.getInstance().connect(mailConfig.getSmtp(), mailConfig.getUsuarioMail(), mailConfig.getContrasenaMail(), mailConfig.getNotificacion(), new Integer(mailConfig.getPort()), err);
        } catch (Exception e) {
            e.printStackTrace();
            err.append(e.toString());
        }

        if (!err.toString().isEmpty()) {
            facesUtil.msgError("ALERTA: ", err.toString());
        }
    }

    public void testMail() {
        successful = false;
        StringBuilder err = new StringBuilder();
        notificacionFacade.sendSimpleMailTnfactor(asunto, contenido, mail, err);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("", "Mensaje enviado!");
        } else {
            facesUtil.msgError("ALERTA: ", err.toString());
        }
    }

}
