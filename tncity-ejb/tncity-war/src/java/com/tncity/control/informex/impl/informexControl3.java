/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex.impl;

import com.tncity.control.general.AbstractControl;

import com.tncity.facade.general.AbstractFacade;

import com.tncity.informex.entity.InformeFacade3;
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

@ManagedBean(name = "informexControl3")
@RequestScoped
@Named
public class informexControl3 extends AbstractControl<Informes> {

    @EJB
    InformeFacade3 facadeinforme3;

    List<String> lstTitulosWS = new ArrayList<>();
    List<VistaWSpagos> lstInformesWS = new ArrayList<>();

    public List<String> getLstTitulosWS() {
        return lstTitulosWS;
    }

    public void setLstTitulosWS(List<String> lstTitulosWS) {
        this.lstTitulosWS = lstTitulosWS;
    }

    public List<VistaWSpagos> getLstInformesWS() {
        return lstInformesWS;
    }

    public void setLstInformesWS(List<VistaWSpagos> lstInformesWS) {
        this.lstInformesWS = lstInformesWS;
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
    String idpasarela;
    String documento;

    public String getIdpasarela() {
        return idpasarela;
    }

    public void setIdpasarela(String idpasarela) {
        this.idpasarela = idpasarela;
    }

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

    public informexControl3() {
        super(Informes.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeinforme3;
    }

    @Override
    protected String displayObj(Informes obj) {

        return obj.getTitulo();

    }

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeinforme3.create(obj, err);
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
        facadeinforme3.edit(obj, err);
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
        facadeinforme3.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void TotalInformeFechas(String desde, String hasta) {
        System.out.println("datos informe " + desde + hasta);

        totalConsulta = facadeinforme3.countTotalInformeFechas(desde, hasta);
    }

    public void TotalInformesFechas(String desde, String hasta) {
        System.out.println("datos informe " + desde + hasta + idbenefactor + documento + idpasarela);
        lstInformesWS = facadeinforme3.ListarInformeFechas(desde, hasta, "idvista", "DESC");
    }

    public String generarPdf(String desde, String hasta) {
        String url = "";

        System.out.println("datos pdf " + desde + hasta);
        lstInformesWS = facadeinforme3.ListarInformeFechas(desde, hasta, "idvista", "DESC");

        facadeinforme3.generarPdf(lstInformesWS);

        return url = "";

    }

    public void recuperaExel() {
        urleXEL = facesUtil.getProtocolHostPortPath() + "/documentos/reporteWsFechas.xls";
        System.out.println("url informe " + urleXEL);
    }

    public void recuperaPDF() {

        urlPdf = facesUtil.getProtocolHostPortPath() + "/documentos/reportewsfechas.pdf";
        System.out.println("url informe " + urleXEL);
    }

    public String generarExcel(String desde, String hasta) {
        String url = "";

        System.out.println("datos exel " + desde + hasta);
        lstInformesWS = facadeinforme3.ListarInformeFechas(desde, hasta, "idvista", "DESC");

        try {
            facadeinforme3.generarExcel(lstInformesWS);
        } catch (Exception ex) {
            Logger.getLogger(informexControl3.class.getName()).log(Level.SEVERE, null, ex);
        }

        return url = "";

    }

    public String descargarPdf() {
        String url = "";
        url = facadeinforme3.rutadescargas();
        return url;

    }

}
