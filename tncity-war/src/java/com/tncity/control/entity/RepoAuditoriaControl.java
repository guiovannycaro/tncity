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
import com.tncity.facade.entity.RepoauditoriaFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Repoauditoria;
import com.tncity.jpa.pojo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "repoauditoriaControl")
@RequestScoped
public class RepoAuditoriaControl extends AbstractControl<Repoauditoria> {

    @EJB
    RepoauditoriaFacade facadeRepoauditoria;
    
     List<Repoauditoria> lstrepoauditoria = new ArrayList<>();

    public List<Repoauditoria> getLstrepoauditoria() {
        return lstrepoauditoria;
    }

    public void setLstrepoauditoria(List<Repoauditoria> lstrepoauditoria) {
        this.lstrepoauditoria = lstrepoauditoria;
    }


     

    public RepoAuditoriaControl() {
        super(Repoauditoria.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeRepoauditoria;
    }

    @Override
    protected String displayObj(Repoauditoria obj) {
        return obj.getNombre() + " - " + obj.getImagen() + " - " + obj.getUrl();
    }

    
    //recuperar por nombre de barrio
    
      public void recuperaByNomAuditoria(String nombre) {
        obj = facadeRepoauditoria.findByNomAuditoria(nombre);
    }
    
      //  nuevo barrio
      
 public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeRepoauditoria.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void editarreporte(Integer idusuario) {
        StringBuilder err = new StringBuilder();

       obj.setIdusuario(new Usuario(new Integer(idusuario)));
        facadeRepoauditoria.editarreporte(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void nuevoreporte(String idusuario) {
        StringBuilder err = new StringBuilder();

      obj.setIdusuario(new Usuario(new Integer(idusuario)));
        facadeRepoauditoria.createreporte(obj, err);
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
        facadeRepoauditoria.edit(obj, err);
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
        facadeRepoauditoria.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
     
       public void TotalRepoAuditoria() {

        totalConsulta = facadeRepoauditoria.countTotalReportAuditoria();

    }

    public void RecuperaReporAditorias() {

        lstrepoauditoria = facadeRepoauditoria.TodosReportAuditoria("idrepoauditoria", "ASC");
    }
     
}
