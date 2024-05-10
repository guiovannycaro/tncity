/**
 * @name Cuentas control
 *
 * @ description crud de cuentas
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
import com.tncity.facade.entity.CuentaFacade;
import com.tncity.facade.entity.PersonaFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Cuenta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "cuentasControl")
@RequestScoped
public class CuentasControl extends AbstractControl<Cuenta> {

    @EJB
    BenefactorFacade facadebenefactor;

    @EJB
    PersonaFacade facadepersona;

    @EJB
    CuentaFacade facadecuentas;

    public CuentasControl() {
        super(Cuenta.class);
        attrOrd = "idcuenta";
        ascDesc = "ASC";

    }
Cuenta pasarela;

    public Cuenta getPasarela() {
        return pasarela;
    }

    public void setPasarela(Cuenta pasarela) {
        this.pasarela = pasarela;
    }
 
    List<Cuenta> lisbenCuenta = new ArrayList<>();

    public List<Cuenta> getLisbenCuenta() {
        return lisbenCuenta;
    }

    public void setLisbenCuenta(List<Cuenta> lisbenCuenta) {
        this.lisbenCuenta = lisbenCuenta;
    }

    Double valor;

    Boolean estado;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadecuentas;
    }

    @Override
    protected String displayObj(Cuenta obj) {
        return obj.getIdcuenta() + " - " + obj.getIdbenefactor() + " - " + obj.getValEntradas() + " - " + 
                obj.getValSalidas()
                + " - " + obj.getValSaldo() + " - " + obj.getIsActiva()  + " - " + obj.getValcomision();

    }
    
//nueva cuenta
    
    public void nuevo() {
        StringBuilder err = new StringBuilder();
  
        long benefactor = (long) facesUtil.getFacesParamInteger("idbenefactor_");

        facadecuentas.createcuenta(obj ,benefactor, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Se ha Creado la Cuenta Con Exito");
            successful = true;
        } else {
            facesUtil.msgError("Alerta","El usuario ya tiene una cuenta asociada");
            successful = false;
        }
    }

    //editar cuentas
    
    public void editar() {
        StringBuilder err = new StringBuilder();
        facadecuentas.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Cuenta Editada");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar cuenta
    
    public void editarcuenta() {
        StringBuilder err = new StringBuilder();
        long idbenefactor = (long) facesUtil.getFacesParamInteger("idbenefactor_");


        facadecuentas.editcuenta(obj,idbenefactor, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Cuenta Editada");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
    //eliminar cuenta

    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadecuentas.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Cuenta ELiminada");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //recuperar numero total de cuentas por id
    
    public void recuperaNumTotalCuentasId() {
        Integer idbenefactor = facesUtil.getFacesParamInteger("idbenefactor_");

        totalConsulta = facadecuentas.countBuscarIdBen(idbenefactor);
    }

    //recuperar conteo total de cuentas por id
    
    public void recuperaCountTotalCuentasId(String benefactor) {

        
        lisbenCuenta = facadecuentas.CuentasIDBenefactor(benefactor, "idcuenta", "DESC");
    }
    
    //sincronisar cuentas por id cuenta
    
    public void  sincronisarCuentasId(Long benefactor,String idcuenta){
        StringBuilder err = new StringBuilder();
      
    facadecuentas.sincronisarCuenta(benefactor,idcuenta,err);
    }
    
//recuperar total cuentas
    
    public void recuperaTotalCuentas() {

        totalConsulta = facadecuentas.countBenCuentas();
    }
    
//recuperar cuentas por benefactor
    
    public void TotalCuentasBenefactor() {

        lisbenCuenta = facadecuentas.TodascuentasBenefactor("idcuenta", "DESC");
    }
    
    //recuperar pasarela dia
    
    public void pasareladia(){
       pasarela = facadecuentas.Pasareladia();
         
          
        
    
    }
    
//recuperar numero total de registros por benefactor
    
    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadecuentas.countBuscar(valBusq);
        }
    }
    
//buscar cuentas por valor de busqueda
    
    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadecuentas.buscarFullText(valBusq, first, maxRegLista);
    }
    
//recuperar cuenta por id cuenta
    
    public void recuperaByIdBenefactor(String idbenefactor) {
 
        obj = facadecuentas.findByIDBenefactor(idbenefactor);
    }
}
