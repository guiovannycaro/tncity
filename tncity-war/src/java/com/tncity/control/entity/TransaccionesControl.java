/**
 * @name Transacciones Control
 *
 * @ description crud de Transacciones 
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */

package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.RecaudoFacade;

import com.tncity.facade.entity.TransaccionesFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Transacciones;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "transaccionesControl")
@RequestScoped
public class TransaccionesControl extends AbstractControl<Transacciones> {

    @EJB
    TransaccionesFacade facadeTransaccion;
    
     @EJB
    RecaudoFacade facadeRecaudo;

    List<Transacciones> lsttransaccion = new ArrayList<>();
long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }


    public List<Transacciones> getLsttransaccion() {
        return lsttransaccion;
    }

    public void setLsttransaccion(List<Transacciones> lsttransaccion) {
        this.lsttransaccion = lsttransaccion;
    }

    public TransaccionesControl() {
        super(Transacciones.class);
        attrOrd = "idtransaccion";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeTransaccion;
    }

    @Override
    protected String displayObj(Transacciones obj) {
        return obj.getIdtransaccion() + " - " + obj.getCodtransaccion() + " - " + obj.getFormapago()
                + " - " + obj.getFranquicia() + " - " + obj.getDescripcion() + " - " + obj.getReferencia1()
                + " - " + obj.getFechapago() + " - " + obj.getNumerorecibo() + " - " + obj.getCodigoint()
                + " - " + obj.getMensajeerror();

    }
//recuperar transaccion por id transaccion
    
    public void recuperaByNomBarrio(String idtransaccion) {
        obj = facadeTransaccion.findByIdTransaccion(idtransaccion);
    }

    //nueva transacciones
    
    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeTransaccion.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar transacciones
    
    public void editar() {
        StringBuilder err = new StringBuilder();
        facadeTransaccion.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //eliminar transacciones
    
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadeTransaccion.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }
    
//total transacciones por ws
    
    public void TotalTransaccionesWS() {

        lsttransaccion = facadeTransaccion.TodastransaccionesWS("idtransaccion", "DESC");
    }
    
    //total transacciones por tn pagos
    
        public void TotalTransaccionesTNPagos() {

        lsttransaccion = facadeTransaccion.TodastransaccionesTNPagos("idtransaccion", "DESC");
    }
        
        //detalle transacciones por id transaccion
        
     public void DetalleTransaccion() {
        Integer idtransaccion = facesUtil.getFacesParamInteger("idtransaccion_");
        lsttransaccion = facadeTransaccion.transaccionById(idtransaccion,"idtransaccion", "DESC");
    }
    
     //detalle transaccion por tn pagos
     
      public void DetalleTransaccionTNPagos() {
        Integer idtransaccion = facesUtil.getFacesParamInteger("idtransaccion_");
        lsttransaccion = facadeTransaccion.transaccionByIdTNPago(idtransaccion,"idtransaccion", "DESC");
    }
      
      //recuperar transacciones por id transaccion
      
     public void recuperaById() {
      Integer idrecaudo = facesUtil.getFacesParamInteger("idtransaccion_");
            obj = facadeTransaccion.findidrecaudo(idrecaudo);
        

    }
     
     //recuperar conteo transacciones
     
public void recuperaNumTotalRegTnpagos() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta =facadeTransaccion.countBuscarTnpagos(valBusq);
        }
    }

// buscar tn pagos full text transacciones

    public void buscarFullTextTnpagos() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadeTransaccion.buscarFullTextTnpagos(valBusq, first, maxRegLista);
    }
     
    // recuperar total tn pagos transacciones
    
       public void recuperaTotalTnpagos() {

        total =  facadeTransaccion.countTotalTransacciones();
    }
       
//recuperar total ws transacciones 
       
        public void recuperaTotalRegWS() {

        total =  facadeTransaccion.countTotalTransaccionesWS();
    }
        
}
