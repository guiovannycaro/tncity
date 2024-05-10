/**
 * @name Localidad control
 *
 * @ description crud de localidades
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
import com.tncity.facade.entity.LocalidadFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Localidad;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import com.tncity.jpa.pojo.Barrio;


@ManagedBean(name = "localidadControl")
@RequestScoped
public class LocalidadControl extends AbstractControl<Localidad> {

    @EJB
    LocalidadFacade facade;
    
   @EJB
    BarrioFacade BarriosFacade;
   
    List<Barrio> lstbarrios = new ArrayList<>();

    public List<Barrio> getLstbarrios() {
        return lstbarrios;
    }

    public void setLstbarrios(List<Barrio> lstbarrios) {
        this.lstbarrios = lstbarrios;
    }
    
    
    public LocalidadControl() {
        super(Localidad.class);
        attrOrd="nombre";
        ascDesc="ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Localidad obj) {
        return obj.getNombre()+" - "+obj.getIdciudad().getNombre();
    }

    //recuperar localidades por ciudad
    
      public void recuperaByIdCiudad(Integer idciudad) {
        obj = facade.findByByIdCiudad(idciudad);
    }
      
      //recuperar localidad por nombre
      
     public void recuperaByNomLocalidad(String nombre) {
        obj = facade.findByNomLocalidad(nombre);
    }
     
     
    //nueva localidad
     
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
    
     //editar localidad
     
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
    
    //eliminar localidad
    
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
     //recuperar barrio de localidad
     
         public void recuperaBarrios(){
         Integer idlocalidad=facesUtil.getFacesParamInteger("idlocalidad_");
       
         lstbarrios =BarriosFacade.listByBarrios(idlocalidad);
     }
}
