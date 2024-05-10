/**
 * @name Recaudo Promocion Facade
 *
 * @ description crud de Recaudo Promocion
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.RecaudopromoFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Recaudopromo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "recaudopromoControl")
@RequestScoped
public class RecaudoPromoControl extends AbstractControl<Recaudopromo> {

    @EJB
    RecaudopromoFacade facaderecaudopromo;
    
      List<Recaudopromo> lstpromociones = new ArrayList<>();

    public List<Recaudopromo> getLstpromociones() {
        return lstpromociones;
    }

    public void setLstpromociones(List<Recaudopromo> lstpromociones) {
        this.lstpromociones = lstpromociones;
    }
      
      

    public RecaudoPromoControl() {
        super(Recaudopromo.class);
        attrOrd = "idrecpromocion";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facaderecaudopromo;
    }

    @Override
    protected String displayObj(Recaudopromo obj) {
        return obj.getIdrecpromocion()+ " - " + obj.getIdpromociones()+ " - " + obj.getIdrecaudo();
    }

    

    //nuevo recaudo promocion 
      
 public void nuevo() {
        StringBuilder err = new StringBuilder();
        facaderecaudopromo.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
             successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
 
 //editar recaudo promocion
 
    public void editar() {
        StringBuilder err = new StringBuilder();
        facaderecaudopromo.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
             successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
    //eliminar recaudo promo
    
     public void eliminar() {
        StringBuilder err = new StringBuilder();
        facaderecaudopromo.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
             successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    
    }
     
     //recuperar total promociones recaudo
     
         public void recuperaTotalPromociones() {

        totalConsulta = facaderecaudopromo.countTotalPromociones();
    }
         
         //total promociones recaudo promo
         
                 public void TotalPromociones() {

        lstpromociones = facaderecaudopromo.TodasPromociones("idrecpromocion", "DESC");
    }
         
}
