/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex.impl;

import com.tncity.control.general.AbstractControl;

import com.tncity.facade.general.AbstractFacade;

import com.tncity.informex.entity.InformeFacade2;
import com.tncity.informex.pojos.VistaTnpagos;

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

@ManagedBean(name = "informexControl2")
@RequestScoped
@Named
public class informexControl2 extends AbstractControl<Informes> {

    @EJB
    InformeFacade2 facade;

    List<VistaTnpagos> lstvistainfome1 = new ArrayList<>();
    List<String> lstTitulosPagos = new ArrayList<>();
    List<VistaTnpagos> lstInformesTnPagos = new ArrayList<>();

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

    public informexControl2() {
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

    public void TotalInformes() {

        lstInformesTnPagos = facade.ListarInformeFechas("idvista", "DESC");
    }

    public void TotalInformeCompleto() {

        totalConsulta = facade.countTotalInformeFechas();

    }

    public String generarExcel() {
        String url = "";

        lstInformesTnPagos = facade.ListarInformeFechas("idvista", "DESC");

        try {
            facade.generarExcel(lstInformesTnPagos);
        } catch (IOException ex) {
            Logger.getLogger(informexControl2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return url = "";

    }

    public String generarPdf() {
        String url = "";
        lstInformesTnPagos = facade.ListarInformeFechas("idvista", "DESC");

        facade.generarPdf(lstInformesTnPagos);
        return url = "";

    }
    
        public void recuperaExel() {
         
          urleXEL = facesUtil.getProtocolHostPortPath() + "/documentos/reporteTnPagosCompleto.xls";
          System.out.println("url informe " + urleXEL);
     }
    
          public void recuperaPDF() {
         
          urlPdf = facesUtil.getProtocolHostPortPath() + "/documentos/reportecompletotnpagos.pdf";
          System.out.println("url informe " + urleXEL);
     }

}
