/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.PromocionFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Promocion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "promocionControl")
@RequestScoped
public class PromocionControl extends AbstractControl<Promocion> {

    @EJB
    PromocionFacade facadepromocion;
    
 List<Promocion> lispromociones = new ArrayList<>();

    public List<Promocion> getLispromociones() {
        return lispromociones;
    }

    public void setLispromociones(List<Promocion> lispromociones) {
        this.lispromociones = lispromociones;
    }
 
 
 
 
    public PromocionControl() {
        super(Promocion.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadepromocion;
    }

    @Override
    protected String displayObj(Promocion obj) {
        return obj.getNombre() + " - " + obj.getEstado() + " - " + obj.getObservacion();
    }

    
      public void recuperaByNomPromocion(String nombre) {
        obj = facadepromocion.findByNomPromocion(nombre);
    }
    
      
 public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadepromocion.create(obj, err);
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
        facadepromocion.edit(obj, err);
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
        facadepromocion.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
             successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    
    }
     
      public void recuperaTotalPromociones() {

        totalConsulta = facadepromocion.countTotalPromociones();
    }
      
      

    public void TotalPromociones() {

        lispromociones = facadepromocion.TodasPromociones("idpromocion", "DESC");
    }

    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadepromocion.countBuscar(valBusq);
        }
    }

    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadepromocion.buscarFullText(valBusq, first, maxRegLista);
    }
    
      public void RecuperaPromocionesId() {

        lispromociones = facadepromocion.TodasPromociones("idpromocion", "ASC");
    }
}
