/**
 * @name Indicativos control
 *
 * @ description crud de indicativos
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
import com.tncity.facade.entity.CiudadFacade;
import com.tncity.facade.entity.DepartamentoestadoFacade;
import com.tncity.facade.entity.IndicativosFacade;
import com.tncity.facade.entity.LocalidadFacade;
import com.tncity.facade.entity.PaisFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Indicativospaises;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import com.tncity.jpa.pojo.Barrio;

@ManagedBean(name = "indicativosControl")
@RequestScoped
public class IndicativosControl extends AbstractControl<Indicativospaises> {

    @EJB
    IndicativosFacade facadeIndicativos;

    @EJB
    LocalidadFacade facade;

    @EJB
    BarrioFacade BarriosFacade;

    @EJB
    CiudadFacade ciudadFacade;

    @EJB
    PaisFacade paisFacade;

    @EJB
    DepartamentoestadoFacade departamentoFacade;

    List<Indicativospaises> lstindicativos = new ArrayList<>();

    public List<Indicativospaises> getLstindicativos() {
        return lstindicativos;
    }

    public void setLstindicativos(List<Indicativospaises> lstindicativos) {
        this.lstindicativos = lstindicativos;
    }

    public IndicativosControl() {
        super(Indicativospaises.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeIndicativos;
    }

    @Override
    protected String displayObj(Indicativospaises obj) {
        return obj.getIdciudad() + " - " + obj.getIdciudad().getNombre() + " - " +
                obj.getIddepartamento().getNombre()
                + " - " + obj.getIdpais().getNombre() + " - " + obj.getCodindicativo();
    }
    
    //nuevo indicativo

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeIndicativos.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar indicativo
    
    public void editar() {
        StringBuilder err = new StringBuilder();
        facadeIndicativos.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
//eliminar indicativo
    
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadeIndicativos.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //recuperar total  indicativos
    
    public void recuperaTotalIndicativos() {

        totalConsulta = facadeIndicativos.countTotalIndicativos();
    }

    //total indicativos paises
    
    public void TotalIndicativosPaises() {

        lstindicativos = facadeIndicativos.TodosIndicativos("idindicativo", "DESC");
    }

    //nuevo indicativo
    
    public void nuevoindicativo() {
        StringBuilder err = new StringBuilder();
        facadeIndicativos.nuevoindi(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar indicativo
    
    public void editarindicativo() {
        StringBuilder err = new StringBuilder();
        facadeIndicativos.editarindi(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //recuperar numero indicativos por total registros
    
    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadeIndicativos.countBuscar(valBusq);
        }
    }
    
//buscar indicativos por  valor de busqueda
    
    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadeIndicativos.buscarFullText(valBusq, first, maxRegLista);
    }
    
    // recuperar indicativos por id

    public void recuperaById(Integer idindicativo) {
        if (idindicativo != null) {
            obj = facadeIndicativos.find(idindicativo);
        }

    }
}
