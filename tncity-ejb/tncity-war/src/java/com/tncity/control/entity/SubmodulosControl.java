/**
 * @name Sub Modulos Facade
 *
 * @ description crud de Sub Modulos
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */

package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.SubmodulosFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Secciones;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "submodulosControl")
@RequestScoped
public class SubmodulosControl extends AbstractControl<Secciones> {

    @EJB
    SubmodulosFacade facadesubmodulos;

    List<Secciones> lstmSecciones = new ArrayList<>();

    public List<Secciones> getLstmSecciones() {
        return lstmSecciones;
    }

    public void setLstmSecciones(List<Secciones> lstmSecciones) {
        this.lstmSecciones = lstmSecciones;
    }



    public SubmodulosControl() {
        super(Secciones.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadesubmodulos;
    }

    @Override
    protected String displayObj(Secciones obj) {
        return obj.getNombre();
    }
    
    //nuevo sub modulo

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadesubmodulos.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar submodulo
    
    public void editar() {
        StringBuilder err = new StringBuilder();
        facadesubmodulos.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //eliminar sub modulo
    
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadesubmodulos.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //total sub modulos
    
    public void TotalModulos() {

        totalConsulta = facadesubmodulos.countTotalModulos();

    }

    //recuperar sub modulos 
    
    public void RecuperaModulos() {

        lstmSecciones = facadesubmodulos.TodosModulos("idseccion", "DESC");
    }

    
      public List<SelectItem> selectAllOBJ() {
        List<SelectItem> lstI = new ArrayList<>();
        List<Secciones> lstT = facadesubmodulos.listAllTipos();
        lstI.add(new SelectItem(null, "Seleccione una opcion..."));
        for (Secciones seccionIdent : lstT) {
            SelectItem it = new SelectItem(seccionIdent.getNombre(), seccionIdent.getNombre()
            );
            lstI.add(it);
        }
        return lstI;
    }
      
      //recuperar sub modulos por id submodulos
      
        public void RecuperaSubmodulosId() {

         lstmSecciones = facadesubmodulos.TodosModulos("idseccion", "DESC");
    }
}
