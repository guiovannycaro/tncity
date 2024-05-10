/**
 * @name Beneficiario control
 *
 * @ description crud de beneficiario
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */

package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.BenefactorFacade;
import com.tncity.facade.entity.BeneficiarioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Beneficiario;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojoaux.TelefoniaJson;
import com.tncity.jpa.pojoaux.TelefoniaManager;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "beneficiarioControl")
@RequestScoped
public class BeneficiarioControl extends AbstractControl<Beneficiario> {

    @EJB
    BeneficiarioFacade facadebeneficiario;

    @EJB
    BenefactorFacade facadebenefactor;
    
    List<Benefactor> lstbenefactores = new ArrayList<>();
    List<Beneficiario> lisben = new ArrayList<>();

    TelefoniaJson datosben;
    Benefactor ben;

    public Benefactor getBen() {
        return ben;
    }

    public void setBen(Benefactor ben) {
        this.ben = ben;
    }

    public TelefoniaJson getDatosben() {
        return datosben;
    }

    public void setDatosben(TelefoniaJson datosben) {
        this.datosben = datosben;
    }

    String redir;
    String mensaje;
    String numdocumento;
    String tipodocumento;
    String telperrecarga;

    public String getTelperrecarga() {
        return telperrecarga;
    }

    public void setTelperrecarga(String telperrecarga) {
        this.telperrecarga = telperrecarga;
    }

    public String getRedir() {
        return redir;
    }

    public void setRedir(String redir) {
        this.redir = redir;
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Beneficiario> getLisben() {
        return lisben;
    }

    public void setLisben(List<Beneficiario> lisben) {
        this.lisben = lisben;
    }

    public List<Benefactor> getLstbenefactores() {
        return lstbenefactores;
    }

    public void setLstbenefactores(List<Benefactor> lstbenefactores) {
        this.lstbenefactores = lstbenefactores;
    }

    public BeneficiarioControl() {
        super(Beneficiario.class);
        attrOrd = "idbeneficiario";
        ascDesc = "ASC";
        obj.setIdpersona(new Persona());
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadebeneficiario;
    }

    @Override
    protected String displayObj(Beneficiario obj) {
        return obj.getNombresApellidos() + " - " + obj.getPin() + " - " + obj.getTd() + " - " + obj.getIdbeneficiario() + " - "
                + obj.getIdpersona().getNombres() + " - " + obj.getIdusuarioUpdated().getIdusuario() + " - " + obj.getUpdatedAt();
    }
    
//recuperar nombre beneficiario beneficiario
    
    public void recuperaByNomBeneficiario(String nombre) {
        obj = facadebeneficiario.findByNomBeneficiario(nombre);
    }
    
//nuevo beneficiario
    
    public void nuevo() {
        StringBuilder err = new StringBuilder();

        obj.getIdpersona().setIdusuarioUpdated(facesUtil.getCurrentUser());
        obj.getIdpersona().setIdusuarioCreated(facesUtil.getCurrentUser());

        obj.setIdusuarioUpdated(facesUtil.getCurrentUser());

        facadebeneficiario.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar beneficiario
    
    public void editar() {
        StringBuilder err = new StringBuilder();

        obj.setIdusuarioUpdated(facesUtil.getCurrentUser());

        facadebeneficiario.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //eliminar beneficiario
    
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadebeneficiario.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }
    
//contar beneficiarios
    
    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadebeneficiario.countBuscar(valBusq);
        }
    }
    
    //buscar full text por beneficiario

    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadebeneficiario.buscarFullText(valBusq, first, maxRegLista);
    }

    //recuperar beneficiarios por id benefactor
    
    public void recuperaNumTotalBenId(Integer idbenefactor) {
  

        totalConsulta = facadebeneficiario.countBuscarIdBen(idbenefactor);
    }
    
     //recuperar beneficiarios por id benefactor

    public void recuperaCountTotalBenId(Integer idbenefactor) {

        lisben = facadebeneficiario.beneficiarioIDBenefactor(idbenefactor, "idbeneficiario", "DESC");
    }
    
//recuperar benefactores
    
    public void recuperaBenefactores() {

        lstbenefactores = facadebenefactor.listarBenefactor();
    }
    
    //recuperar benefactors por id ppl

    public void recuperaIdPPL() {
        StringBuilder err = new StringBuilder();

        datosben = facadebeneficiario.TeleManagerbyIdBene(numdocumento, tipodocumento, err);
        System.out.println("alerta" + datosben);
        if (datosben != null) {
            redir = "CONTINUAR";
        } else {
            redir = "ESPERAR";

        }
    }

    //recuperar id ppl interno
    
    public void recuperaIdPPLInterno(String numdoc, String tipodoc) {
        
        System.out.println("datos benefactor " +  numdoc + " " +  tipodoc);
        StringBuilder err = new StringBuilder();

        datosben = facadebeneficiario.TeleManagerbyIdBene(numdoc, tipodoc, err);
        System.out.println("alerta" + datosben);
        if (datosben != null) {
            redir = "CONTINUAR";
        } else {
            redir = "ESPERAR";

        }
    }

    //recuperar datos beneficiario
    
    public void recuperadatosbeneficiario(String numdoc, String tipodoc) {

        StringBuilder err = new StringBuilder();

        datosben = facadebeneficiario.datosbeneficiario(numdoc, tipodoc, err);
        System.out.println("alerta" + datosben);

    }
    
//validar proceso beneficiario por numero documento
    
    public void validarproceso() {
        obj = facadebeneficiario.findBeneficiarioByNumdocumento(numdocumento);

        if (obj != null) {
            redir = "CONTINUAR";
        }
    }

}
