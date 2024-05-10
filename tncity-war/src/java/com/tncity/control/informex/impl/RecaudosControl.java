/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex.impl;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;

import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.RecaudoFacade;
import com.tncity.facade.entity.RecaudosFacade;

import com.tncity.facade.general.AbstractFacade;

import com.tncity.informex.entity.ReportesFacade;
import com.tncity.informex.pojos.Recaudos;





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

@ManagedBean(name = "RecaudosControl")
@RequestScoped
@Named
public class RecaudosControl extends AbstractControl<Recaudos> {

 

  @EJB
    RecaudosFacade facadeRecaudos;
  
  

  
    
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
    
    



    public List<Recaudos> getLstrecaudos() {
        return lstrecaudos;
    }

    public void setLstrecaudos(List<Recaudos> lstrecaudos) {
        this.lstrecaudos = lstrecaudos;
    }

    
    
    public RecaudosControl() {
        super(Recaudos.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeRecaudos;
    }

    @Override
    protected String displayObj(Recaudos obj) {

        return obj.getNombres();

    }

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeRecaudos.create(obj, err);
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
        facadeRecaudos.edit(obj, err);
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
        facadeRecaudos.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }




  
    public void TotalEstadoRecaudoCompleto(){
     totalConsulta = facadeRecaudos.CountTotalRecaudosCompleto();
    }
    
 
            
    public void TotalInformesFechasCompleto() {
    
        lstrecaudos = facadeRecaudos.ListarInformeFechasCompleto("estado", "DESC");
    }

    
     public void generarExcel() {
        

       
     
        lstrecaudos = facadeRecaudos.ListarRecaudosFechasCompleto( "estado", "DESC");

        try {
            facadeRecaudos. GenerarExcelCompleto(lstrecaudos);
        } catch (IOException ex) {
            Logger.getLogger(informexControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        urleXEL = facesUtil.getProtocolHostPortPath() + "/documentos/recaudosTnPagosFechasCompleto.xls";
        
        System.out.println("url recaudos " + urleXEL);
    

    }
     public void recuperaExel() {
         
          urleXEL = facesUtil.getProtocolHostPortPath() + "/documentos/recaudosTnPagosFechasCompleto.xls";
          System.out.println("url recaudos " + urleXEL);
     }
    
          public void recuperaPDF() {
         
          urlPdf = facesUtil.getProtocolHostPortPath() + "/documentos/recaudosfechastnpagosCompleto.pdf";
          System.out.println("url recaudos " + urleXEL);
     }

    public String generarPdf() {
        String url = "";

        lstrecaudos  = facadeRecaudos.ListarRecaudosFechasCompleto( "estado", "DESC");

        facadeRecaudos.generarPdf(lstrecaudos );

        return url = "";

    }
    
       public String GenerarPdfCompleto() {
        String url = "";

        lstrecaudos  = facadeRecaudos.ListarRecaudosFechasCompleto( "estado", "DESC");

        facadeRecaudos.GenerarPdfCompleto(lstrecaudos );

        return url = "";

    }
    
     public String descargarPdf() {
        String url = "";
        url = facadeRecaudos.rutadescargas();
        return url ;

    }
}
