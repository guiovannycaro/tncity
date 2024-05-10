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
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.BarrioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Barrio;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "barrioControl")
@RequestScoped
public class BarrioControl extends AbstractControl<Barrio> {

    @EJB
    BarrioFacade facade;

    public BarrioControl() {
        super(Barrio.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Barrio obj) {
        return obj.getNombre() + " - " + obj.getIdlocalidad().getNombre() + " - " +
                
                obj.getIdlocalidad().getIdciudad().getNombre();
    }

    
    //recuperar por nombre de barrio
    
      public void recuperaByNomBarrio(String nombre) {
        obj = facade.findByNomBarrio(nombre);
    }
    
      //  nuevo barrio
      
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
    
 //editar barrio
 
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
    
    //eliminar barrio
    
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
}
