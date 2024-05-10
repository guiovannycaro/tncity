/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex.impl;


import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.RecaudoFacade;
import com.tncity.facade.entity.RecaudosFacade;

import com.tncity.facade.general.AbstractFacade;

import com.tncity.informex.entity.ReportesFacade;
import com.tncity.informex.pojos.Recaudos;

import com.tncity.jpa.pojo.Reportes;
import com.tncity.jpa.pojo.Usuario;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "reporteControl")
@RequestScoped
@Named
public class ReportesControl extends AbstractControl<Reportes> {

    @EJB
    ReportesFacade facadeReporte;

  @EJB
    RecaudosFacade facadeRecaudos;
  
  

    List<Reportes> lstreportes = new ArrayList<>();
    
     List<Recaudos> lstrecaudos = new ArrayList<>();
     
         String desde;
    String hasta;
    String idbenefactor;
    String idpasarela;
    String documento;

    List<String> lstTitulosReportes = new ArrayList<>();


    private StreamedContent archivo;
    String urleXEL;

    String urlPdf;

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

    public String getIdbenefactor() {
        return idbenefactor;
    }

    public void setIdbenefactor(String idbenefactor) {
        this.idbenefactor = idbenefactor;
    }

    public String getIdpasarela() {
        return idpasarela;
    }

    public void setIdpasarela(String idpasarela) {
        this.idpasarela = idpasarela;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<String> getLstTitulosReportes() {
        return lstTitulosReportes;
    }

    public void setLstTitulosReportes(List<String> lstTitulosReportes) {
        this.lstTitulosReportes = lstTitulosReportes;
    }

    public StreamedContent getArchivo() {
        return archivo;
    }

    public void setArchivo(StreamedContent archivo) {
        this.archivo = archivo;
    }

    public String getUrleXEL() {
        return urleXEL;
    }

    public void setUrleXEL(String urleXEL) {
        this.urleXEL = urleXEL;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }
    
    

    public List<Reportes> getLstreportes() {
        return lstreportes;
    }

    public void setLstreportes(List<Reportes> lstreportes) {
        this.lstreportes = lstreportes;
    }

    public List<Recaudos> getLstrecaudos() {
        return lstrecaudos;
    }

    public void setLstrecaudos(List<Recaudos> lstrecaudos) {
        this.lstrecaudos = lstrecaudos;
    }

    
    
    public ReportesControl() {
        super(Reportes.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeReporte;
    }

    @Override
    protected String displayObj(Reportes obj) {

        return obj.getNombre();

    }

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeReporte.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void editarreporte(String idusuario) {
        StringBuilder err = new StringBuilder();

        obj.setIdusuario(new Usuario(new Integer(idusuario)));
        facadeReporte.editarreporte(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void nuevoreporte(String idusuario) {
        StringBuilder err = new StringBuilder();

        obj.setIdusuario(new Usuario(new Integer(idusuario)));
        facadeReporte.createreporte(obj, err);
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
        facadeReporte.edit(obj, err);
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
        facadeReporte.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void TotalReportes() {

        totalConsulta = facadeReporte.countTotalReportes();

    }

    public void RecuperaReportes() {

        lstreportes = facadeReporte.TodosReportes("idreporte", "ASC");
    }

    public void RecuperaReportesByID(String idreporte) {

        lstreportes = facadeReporte.TodosReportesById(idreporte, "idreporte", "ASC");
    }
    
    public void TotalEstadoRecaudoFechas(String desde,String hasta,String idbenefactor,String idpasarela){
     totalConsulta = facadeRecaudos.countTotalRecaudosFechas(desde, hasta, idbenefactor,idpasarela);
    }
    
 
            
    public void TotalInformesFechas(String desde, String hasta, String idbenefactor,String idpasarela) {
        System.out.println("datos informe " + desde + hasta + idbenefactor  + idpasarela);
        lstrecaudos = facadeRecaudos.ListarInformeFechas(desde, hasta, idbenefactor, idpasarela,"estado", "DESC");
    }

    
     public void generarExcel(String desde,String hasta,String benefactor,String idpasarela) {
        

       
        System.out.println("datos exel " + desde + hasta + benefactor );
        lstrecaudos = facadeRecaudos.ListarRecaudosFechas(desde, hasta, benefactor,idpasarela, "estado", "DESC");

        try {
            facadeRecaudos.generarExcel(lstrecaudos);
        } catch (IOException ex) {
            Logger.getLogger(informexControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        urleXEL = facesUtil.getProtocolHostPortPath() + "/documentos/recaudosTnPagosFechas.xls";
        
        System.out.println("url recaudos " + urleXEL);
    

    }
     public void recuperaExel() {
         
          urleXEL = facesUtil.getProtocolHostPortPath() + "/documentos/recaudosfechastnpagos.pdf";
          System.out.println("url recaudos " + urleXEL);
     }
    
          public void recuperaPDF() {
         
          urlPdf = facesUtil.getProtocolHostPortPath() + "/documentos/recaudosfechastnpagos.pdf";
          System.out.println("url recaudos " + urleXEL);
     }

    public String generarPdf(String desde, String hasta, String benefactor,String idpasarela) {
        String url = "";

        System.out.println("datos pdf " + desde + hasta + benefactor  + idpasarela);
        lstrecaudos  = facadeRecaudos.ListarRecaudosFechas(desde, hasta, benefactor,idpasarela, "estado", "DESC");

        facadeRecaudos.generarPdf(lstrecaudos );

        return url = "";

    }
    
     public String descargarPdf() {
        String url = "";
        url = facadeRecaudos.rutadescargas();
        return url ;

    }
}
