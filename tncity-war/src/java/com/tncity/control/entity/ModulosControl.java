/**
 * @name modulos control
 *
 * @ description crud de modulos
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.ModulosFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Modulos;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "modulosControl")
@RequestScoped
public class ModulosControl extends AbstractControl<Modulos> {

    @EJB
    ModulosFacade facademodulos;

    List<Modulos> lstmodulos = new ArrayList<>();

    public List<Modulos> getLstmodulos() {
        return lstmodulos;
    }

    public void setLstmodulos(List<Modulos> lstmodulos) {
        this.lstmodulos = lstmodulos;
    }

    public ModulosControl() {
        super(Modulos.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facademodulos;
    }

    @Override
    protected String displayObj(Modulos obj) {
        return obj.getNombre();
    }
    
    //nuevo modulo

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facademodulos.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    
    //editar modulos
    
    public void editar() {
        StringBuilder err = new StringBuilder();
        facademodulos.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    
    //eliminar modulos
    
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facademodulos.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
    //total modulos

    public void TotalModulos() {

        totalConsulta = facademodulos.countTotalModulos();

    }

    //recuperar modulos 
    
    public void RecuperaModulos() {

        lstmodulos = facademodulos.TodosModulos("idmodulo", "DESC");
    }
    
    //listar modulos
    
        public List<SelectItem> selectAllOBJ() {
        List<SelectItem> lstI = new ArrayList<>();
        List<Modulos> lstT = facademodulos.listAllTipos();
        lstI.add(new SelectItem(null, "Seleccione una opcion..."));
        for (Modulos modulosIdent : lstT) {
            SelectItem it = new SelectItem(modulosIdent.getNombre(), modulosIdent.getNombre()
            );
            lstI.add(it);
        }
        return lstI;
    }

     //recuperar modulos
        
     public void RecuperaModulosId() {

         lstmodulos = facademodulos.TodosModulos("idmodulo", "DESC");
    }

}
