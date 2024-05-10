/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.MonedaFacade;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Moneda;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "monedaControl")
@RequestScoped
public class MonedaControl extends AbstractControl<Moneda> {

    @EJB
    MonedaFacade facademoneda;

    public MonedaControl() {
        super(Moneda.class);
        attrOrd = "codeiso4217";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facademoneda;
    }

    List<Moneda> lstmonedas = new ArrayList<>();

    public List<Moneda> getLstmonedas() {
        return lstmonedas;
    }

    public void setLstmonedas(List<Moneda> lstmonedas) {
        this.lstmonedas = lstmonedas;
    }

            
            
    @Override
    protected String displayObj(Moneda obj) {

        return obj.getCodeiso4217();

    }

    public void recuperaByNomPais(String nombre) {
        obj = facademoneda.findByNomPais(nombre);
    }

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facademoneda.create(obj, err);
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
        facademoneda.edit(obj, err);
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
        facademoneda.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
        public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facademoneda.countBuscar(valBusq);
        }
    }

        
          public void TotalMonedas() {
     
            totalConsulta = facademoneda.countTotalMonedas();
   
    }
          
    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facademoneda.buscarFullText(valBusq, first, maxRegLista);
    }
    
    public void recuperamonedas(){

       
         lst = facademoneda.listByMoneda();
     }
    
    
    
 public void recuperamonedasd() {

        lstmonedas = facademoneda.TodosMonedas("idmoneda", "DESC");
    }

}
