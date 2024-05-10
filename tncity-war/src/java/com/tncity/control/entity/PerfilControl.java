/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.PerfilFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Perfil;
import com.tncity.jpa.pojo.PerfilHasUsuario;
import com.tncity.jpa.pojo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "perfilControl")
@RequestScoped
public class PerfilControl extends AbstractControl<Perfil> {

    @EJB
    PerfilFacade facadeperfil;

    List<PerfilHasUsuario> lstUser = new ArrayList<>();

    List<Perfil> lstperfiles = new ArrayList<>();

    public List<Perfil> getLstperfiles() {
        return lstperfiles;
    }

    public void setLstperfiles(List<Perfil> lstperfiles) {
        this.lstperfiles = lstperfiles;
    }

    Usuario objUs = new Usuario();

    public PerfilControl() {
        super(Perfil.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeperfil;
    }

    @Override
    protected String displayObj(Perfil obj) {
        return obj.getNombre();
    }

    public List<PerfilHasUsuario> getLstUser() {
        return lstUser;
    }

    public void setLstUser(List<PerfilHasUsuario> lstUser) {
        this.lstUser = lstUser;
    }

    public Usuario getObjUs() {
        return objUs;
    }

    public void setObjUs(Usuario objUs) {
        this.objUs = objUs;
    }

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeperfil.create(obj, err);
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
        facadeperfil.edit(obj, err);
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
        facadeperfil.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void recuperaUsuarios() {
        Integer idperfil = facesUtil.getFacesParamInteger("idperfil_");
        lstUser = facadeperfil.listUsuariosLight(idperfil);
    }

    public void addUsuario() {
        Integer idperfil = facesUtil.getFacesParamInteger("idperfil_");
        StringBuilder err = new StringBuilder();

    }

    public List<SelectItem> getAllSelectListByPerfil() {
        List<SelectItem> l = new ArrayList<>();
        l.add(new SelectItem(null, "---"));
        List<Perfil> lstC = facadeperfil.listAllLightByPerfil("idperfil", "ASC");
        for (Perfil g : lstC) {
            l.add(new SelectItem(g, g.getNombre()));
        }
        return l;
    }

    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadeperfil.countBuscar(valBusq);
        }
    }

    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lstperfiles = facadeperfil.buscarFullText(valBusq, first, maxRegLista);
    }

    public void RecuperaPerfilesId() {
lstperfiles = facadeperfil.listPerfiles();
    }
    
    

}
