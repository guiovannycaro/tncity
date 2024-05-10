/**
 * @name Ciudad control
 *
 * @ description crud de ciudad
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.CiudadFacade;
import com.tncity.facade.entity.DepartamentoestadoFacade;
import com.tncity.facade.entity.LocalidadFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Departamentoestado;
import com.tncity.jpa.pojo.Localidad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "ciudadControl")
@RequestScoped
public class CiudadControl extends AbstractControl<Ciudad> {

    @EJB
    CiudadFacade facadeciudad;
    @EJB
    LocalidadFacade localidadFacade;
    @EJB
    DepartamentoestadoFacade departamentosFacade;

    List<Localidad> lstLocalidad = new ArrayList<>();
    List<Departamentoestado> lstdepartamentos = new ArrayList<>();
    List<Ciudad> listciudad = new ArrayList<>();

    public List<Ciudad> getListciudad() {
        return listciudad;
    }

    public void setListciudad(List<Ciudad> listciudad) {
        this.listciudad = listciudad;
    }
    
    
    public List<Departamentoestado> getLstdepartamentos() {
        return lstdepartamentos;
    }

    public void setLstdepartamentos(List<Departamentoestado> lstdepartamentos) {
        this.lstdepartamentos = lstdepartamentos;
    }

    public CiudadControl() {
        super(Ciudad.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeciudad;
    }

    @Override
    protected String displayObj(Ciudad obj) {
        return obj.getNombre() + " - " + obj.getIddepartamento().getNombre();
    }

    public List<Localidad> getLstLocalidad() {
        return lstLocalidad;
    }

    public void setLstLocalidad(List<Localidad> lstLocalidad) {
        this.lstLocalidad = lstLocalidad;
    }
    
//nueva ciudad
    
    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeciudad.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar ciudad
    
    public void editar() {
        StringBuilder err = new StringBuilder();
        facadeciudad.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //eliminar ciudad
    
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadeciudad.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //recuperar localidades de una ciudad
    
    public void recuperaLocalidades() {
        Integer idciudad = facesUtil.getFacesParamInteger("idciudad_");
        lstLocalidad = localidadFacade.listByCiudad(idciudad);
    }
    
//recuperar ciudades por id departamento
    
    public void recuperaCiudades() {
        Integer iddepartamento = facesUtil.getFacesParamInteger("iddepartamento_");

        lstdepartamentos = departamentosFacade.listByCiudad(iddepartamento);
    }
    
//auto completar ciudad
    
    public List<Ciudad> autoCompleteAllLight(String query) {
        List<Ciudad> lstC = facadeciudad.listFullTextLight(query, "idciudad", "ASC", 0, 20);
        return lstC;
    }
    
//listar ciudades por ciudad
    
    public List<SelectItem> selectAllObj() {
        List<SelectItem> lstI = new ArrayList<>();
        List<Ciudad> lstT = facadeciudad.listAllTipos();
        lstI.add(new SelectItem(null, "Seleccione una opcion..."));
        for (Ciudad ciudadIdent : lstT) {
            SelectItem it = new SelectItem(ciudadIdent, ciudadIdent.getNombre() + " " + ciudadIdent.getIddepartamento().getNombre()
            );
            lstI.add(it);
        }
        return lstI;
    }

    //listar todas las ciudades
    
        public List<SelectItem> Listadociudades() {
        List<SelectItem> lstI = new ArrayList<>();
        List<Ciudad> lstT = facadeciudad.listAllCiudades();
        lstI.add(new SelectItem(null, "Seleccione una opcion..."));
        for (Ciudad ciudadIdent : lstT) {
            SelectItem it = new SelectItem(ciudadIdent.getNombre(), ciudadIdent.getNombre()
            );
            lstI.add(it);
        }
        return lstI;
    }
        
        //recuperar conteo total de ciudades
        
    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadeciudad.countBuscar(valBusq);
        }
    }
    
//buscar ciudades por valor de busqueda
    
    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadeciudad.buscarFullText(valBusq, first, maxRegLista);
    }
    //recuperar ciudades por id
    public void RecuperaCiudadesId(){
     listciudad = facadeciudad.CiudadesIDCiudad( "idciudad", "DESC");
    }

}
