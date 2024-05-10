/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex.impl;

import com.tncity.control.general.AbstractControl;

import com.tncity.facade.general.AbstractFacade;


import com.tncity.informex.entity.InformeFacade4;
import com.tncity.informex.pojos.VistaWSpagos;

import com.tncity.jpa.pojo.Informes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "informexControl4")
@RequestScoped
@Named
public class informexControl4 extends AbstractControl<Informes> {

    @EJB
    InformeFacade4 facadeInforme4;

    List<VistaWSpagos> lstvistainfome4 = new ArrayList<>();
    List<String> lstTitulosWS = new ArrayList<>();
    List<VistaWSpagos> lstInformesWs = new ArrayList<>();

    public List<VistaWSpagos> getLstvistainfome4() {
        return lstvistainfome4;
    }

    public void setLstvistainfome4(List<VistaWSpagos> lstvistainfome4) {
        this.lstvistainfome4 = lstvistainfome4;
    }

    public List<String> getLstTitulosWS() {
        return lstTitulosWS;
    }

    public void setLstTitulosWS(List<String> lstTitulosWS) {
        this.lstTitulosWS = lstTitulosWS;
    }

    public List<VistaWSpagos> getLstInformesWs() {
        return lstInformesWs;
    }

    public void setLstInformesWs(List<VistaWSpagos> lstInformesWs) {
        this.lstInformesWs = lstInformesWs;
    }

   

  

    private StreamedContent archivo;
    String urleXEL;

    String urlPdf;

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    public String getUrleXEL() {
        return urleXEL;
    }

    public void setUrleXEL(String urleXEL) {
        this.urleXEL = urleXEL;
    }

    public StreamedContent getArchivo() {
        return archivo;
    }

    public void setArchivo(StreamedContent archivo) {
        this.archivo = archivo;
    }

 
   

   

    String desde;
    String hasta;
    String idbenefactor;
    String documento;

    public String getIdbenefactor() {
        return idbenefactor;
    }

    public void setIdbenefactor(String idbenefactor) {
        this.idbenefactor = idbenefactor;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public informexControl4() {
        super(Informes.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeInforme4;
    }

    @Override
    protected String displayObj(Informes obj) {

        return obj.getTitulo();

    }

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeInforme4.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void editar() {
        StringBuilder err = new StringBuilder();
        facadeInforme4.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadeInforme4.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void TotalInformes() {
    
       lstInformesWs = facadeInforme4.ListarInformeFechas("idvista", "DESC");
    }

    public void TotalInformeCompleto() {

        totalConsulta = facadeInforme4.countTotalInformeFechas();

    }

    public String generarExcel() {
        String url = "";
      
        lstInformesWs = facadeInforme4.ListarInformeFechas( "idvista", "DESC");
        
        try {
            facadeInforme4.generarExcel(lstInformesWs);
        } catch (IOException ex) {
            Logger.getLogger(informexControl4.class.getName()).log(Level.SEVERE, null, ex);
        }

        return url = "";

    }
    
     public String generarPdf() {
        String url = "";

        lstInformesWs = facadeInforme4.ListarInformeFechas( "idvista", "DESC");
            facadeInforme4.generarPdf(lstInformesWs);
        return url = "";

    }

 
         public void recuperaExel() {
        urleXEL = facesUtil.getProtocolHostPortPath() + "/documentos/reportecompletoWs.xls";
        System.out.println("url informe " + urleXEL);
    }

    public void recuperaPDF() {

        urlPdf = facesUtil.getProtocolHostPortPath() + "/documentos/reportetnWSCompleto.pdf";
        System.out.println("url informe " + urleXEL);
    }
}
