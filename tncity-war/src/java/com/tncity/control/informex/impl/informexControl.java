/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex.impl;

import com.tncity.control.general.AbstractControl;

import com.tncity.facade.general.AbstractFacade;

import com.tncity.informex.entity.InformeFacade;
import com.tncity.informex.pojos.VistaTnpagos;

import com.tncity.jpa.pojo.Informes;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "informexControl")
@RequestScoped
public class informexControl extends AbstractControl<Informes> {

    @EJB
    InformeFacade facade;

    List<VistaTnpagos> lstvistainfome1 = new ArrayList<>();
    List<String> lstTitulosPagos = new ArrayList<>();
    List<VistaTnpagos> lstInformesTnPagos = new ArrayList<>();

    private StreamedContent archivo;


    String pathDescargas;

    public String getPathDescargas() {
        return pathDescargas;
    }

    public void setPathDescargas(String pathDescargas) {
        this.pathDescargas = pathDescargas;
    }

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

    public List<String> getLstTitulosPagos() {
        return lstTitulosPagos;
    }

    public void setLstTitulosPagos(List<String> lstTitulosPagos) {
        this.lstTitulosPagos = lstTitulosPagos;
    }

    public List<VistaTnpagos> getLstvistainfome1() {
        return lstvistainfome1;
    }

    public void setLstvistainfome1(List<VistaTnpagos> lstvistainfome1) {
        this.lstvistainfome1 = lstvistainfome1;
    }

    public List<VistaTnpagos> getLstInformesTnPagos() {
        return lstInformesTnPagos;
    }

    public void setLstInformesTnPagos(List<VistaTnpagos> lstInformesTnPagos) {
        this.lstInformesTnPagos = lstInformesTnPagos;
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

    public informexControl() {
        super(Informes.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Informes obj) {

        return obj.getTitulo();

    }

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facade.create(obj, err);
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
        facade.edit(obj, err);
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
        facade.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void lstTotalInformesFechas(String desde, String hasta, String idbenefactor, String documento, String idpasarela) {
        System.out.println("datos informe " + desde + hasta + idbenefactor + documento + idpasarela);
        lstInformesTnPagos = facade.ListarInformeFechas(desde, hasta, idbenefactor, documento, idpasarela, "idvista", "DESC");
    }

    public void TotalInformeFechas(String desde, String hasta, String idbenefactor, String documento, String idpasarela) {
        System.out.println("datos informe " + desde + hasta + idbenefactor + documento + idpasarela);
        totalConsulta = facade.countTotalInformeFechas(desde, hasta, idbenefactor, documento, idpasarela);

    }

    public void generarExcel(String desde, String hasta, String benefactor, String documento, String idpasarela) {

        System.out.println("datos exel " + desde + hasta + benefactor + documento);
        lstInformesTnPagos = facade.ListarInformeFechas(desde, hasta, benefactor, documento, idpasarela, "idvista", "DESC");

        try {
            facade.generarExcel(lstInformesTnPagos, desde, hasta);
        } catch (IOException ex) {
            Logger.getLogger(informexControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        urleXEL = facesUtil.getProtocolHostPortPath() + "/documentos/reporteTnPagosFechas.xls";

        System.out.println("url informe " + urleXEL);

    }

    public void recuperaExel(String desde, String hasta) {

        urleXEL = facesUtil.getProtocolHostPortPath() + "/documentos/reporteTnPagosFechas.xls";
        System.out.println("exel informe1 " + urleXEL);

    }

    public void recuperaPDF() {

        urlPdf = facesUtil.getProtocolHostPortPath() + "/documentos/reportefechastnpagos.pdf";
        System.out.println("url informe1 " + urleXEL);
    }

    public String generarPdf(String desde, String hasta, String benefactor, String documento, String idpasarela) {
        String url = "";

        System.out.println("datos pdf " + desde + hasta + benefactor + documento + idpasarela);
        lstInformesTnPagos = facade.ListarInformeFechas(desde, hasta, benefactor, documento, idpasarela, "idvista", "DESC");

        facade.generarPdf(lstInformesTnPagos);

        return url = "";

    }

    public String descargarPdf() {
        String url = "";
        url = facade.rutadescargas();
        return url;

    }

    public StreamedContent downloadFileTemplate(String desde, String hasta) {

        pathDescargas = facade.rutadescargas();
        System.out.println("archivo a descargar" + pathDescargas);

        try {
            FileInputStream inputStream = new FileInputStream(new File(getClass().getClassLoader().getResource(pathDescargas + "reporteTnPagosFechas.xls").getFile()));

            StreamedContent fileTemplate = new DefaultStreamedContent(
                    inputStream,
                    "application/vnd.ms-excel",
                    "my_file.xlsx");

            return fileTemplate;
        } catch (Exception e) {

            return null;
        }
    }

}
