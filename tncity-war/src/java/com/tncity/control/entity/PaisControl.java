/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.PaisFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Pais;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "paisControl")
@RequestScoped
public class PaisControl extends AbstractControl<Pais> {

    @EJB
    PaisFacade facade;

    public PaisControl() {
        super(Pais.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Pais obj) {

        return obj.getNombre();

    }

    public void recuperaByNomPais(String nombre) {
        obj = facade.findByNomPais(nombre);
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
    
        public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facade.countBuscar(valBusq);
        }
    }

    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facade.buscarFullText(valBusq, first, maxRegLista);
    }

}
