/**
 * @name Barrio control
 *
 * @ description crud de barrio
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.auditorias;

import com.tncity.auditorias.entity.VistaRecaudoFacade;
import com.tncity.control.entity.*;
import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.BenefactorFacade;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;

import com.tncity.jpa.pojo.VistaRecaudo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "vistarecaudoControl")
@RequestScoped
public class VistaRecaudoControl extends AbstractControl<VistaRecaudo> {

    @EJB
    VistaRecaudoFacade facadeAuditorias;

    @EJB
    BenefactorFacade facadeBenefactor;

    String desde;
    String hasta;
    String idbenefactor;
    String idpasarela;
    String idestado;

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

    public String getIdestado() {
        return idestado;
    }

    public void setIdestado(String idestado) {
        this.idestado = idestado;
    }

    List<VistaRecaudo> listrecaudotrans = new ArrayList<>();

    public List<VistaRecaudo> getListrecaudotrans() {
        return listrecaudotrans;
    }

    public void setListrecaudotrans(List<VistaRecaudo> listrecaudotrans) {
        this.listrecaudotrans = listrecaudotrans;
    }

    List<Benefactor> lisbenfactores = new ArrayList<>();

    public List<Benefactor> getLisbenfactores() {
        return lisbenfactores;
    }

    public void setLisbenfactores(List<Benefactor> lisbenfactores) {
        this.lisbenfactores = lisbenfactores;
    }

    public VistaRecaudoControl() {
        super(VistaRecaudo.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeAuditorias;
    }

    @Override
    protected String displayObj(VistaRecaudo obj) {
        return obj.getIdrecaudo() + " - " + obj.getValor() + " - " + obj.getIdbeneficiario()
                + " - " + obj.getCreatedAt() + " - " + obj.getCheckTelefoniaAt()
                + " - " + obj.getLog() + " - " + obj.getEstado();
    }

    public void recuperaConteoRegistros() {
        totalConsulta = facadeAuditorias.countTotalRecaudos();
    }

    public void RecuperarTodosRecaudos() {
        listrecaudotrans = facadeAuditorias.TodosRecaudoTrans("idrecaudo", "DESC");
    }

    //recuperar recaudo por fecha
   public void  recuperaConteoRegistrosFechas(String desde,String hasta,String idbenefactor,String estado,String idpasarela){
     System.out.println("datos auditoria  " + desde + hasta + idbenefactor + estado + idpasarela);
   
       totalConsulta = facadeAuditorias.countTotalRecaudoFechas(desde, hasta, idbenefactor, estado, idpasarela);
   }
  
   public void   RecuperarRecaudosFechas(String desde,String hasta,String idbenefactor,String estado,String idpasarela){
       System.out.println("datos auditoria  " + desde + hasta + idbenefactor + estado + idpasarela);
   
       
         listrecaudotrans = facadeAuditorias.ListarRecaudoFechas(desde, hasta, idbenefactor, estado, idpasarela, "idrecaudo", "DESC");
        
   }
   
     
}
