/**
 * @name Departamento estado control
 *
 * @ description crud de Departamento estado
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.DepartamentoestadoFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Departamentoestado;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "departamentoestadoControl")
@RequestScoped
public class DepartamentoestadoControl extends AbstractControl<Departamentoestado> {

    @EJB
    DepartamentoestadoFacade facade;
    List<Departamentoestado> lstdepartamentos = new ArrayList<>();

    public List<Departamentoestado> getLstdepartamentos() {
        return lstdepartamentos;
    }

    public void setLstdepartamentos(List<Departamentoestado> lstdepartamentos) {
        this.lstdepartamentos = lstdepartamentos;
    }

    public DepartamentoestadoControl() {
        super(Departamentoestado.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Departamentoestado obj) {

        return obj.getNombre();

    }

//recuperar departamento estado por nombre pais
    
    public void recuperaByNomPais(String nombre) {
        obj = facade.findByNomDepartamento(nombre);
    }

    //nuevo departamento estado
    
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

    //editar departamento estado
    
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

//eliminar departamento estado
    
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

//recuperar departamentos
    
    public void recuperaDepartamentos() {
        Integer idpais = facesUtil.getFacesParamInteger("idpais_");

        lstdepartamentos = facade.listByDepatamento(idpais);
    }

    //recuperar por valor de busqueda departamento
    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facade.countBuscar(valBusq);
        }
    }

//buscar departamento
    
    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facade.buscarFullText(valBusq, first, maxRegLista);
    }
}
