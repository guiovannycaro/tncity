/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.EmailConfig;
import com.tncity.control.general.AbstractControl;
import com.tncity.control.general.FacesUtil;
import com.tncity.facade.entity.BenefactorFacade;
import com.tncity.facade.entity.CuentaFacade;
import com.tncity.facade.entity.PersonaFacade;
import com.tncity.facade.entity.PasarelaPagoFacade;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Pasarelapago;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.notificacion.NotificacionFacade;
import com.tncity.util.BeanUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "pasarelapagoControl")
@RequestScoped
public class PasarelaPagoControl extends AbstractControl<Pasarelapago> {

    
     @EJB
    PasarelaPagoFacade facadepasarelapago;
     
     
    @EJB
    BenefactorFacade facadebenefactor;

    @EJB
    PersonaFacade facadepersona;

    @EJB
    CuentaFacade facadecuentas;

    public PasarelaPagoControl() {
        super(Pasarelapago.class);
        attrOrd = "idpasarela";
        ascDesc = "ASC";

    }
    
    Pasarelapago nombrepasarela;

    public Pasarelapago getNombrepasarela() {
        return nombrepasarela;
    }

    public void setNombrepasarela(Pasarelapago nombrepasarela) {
        this.nombrepasarela = nombrepasarela;
    }

    List<Pasarelapago> lispasarelapago = new ArrayList<>();

    public List<Pasarelapago> getLispasarelapago() {
        return lispasarelapago;
    }

    public void setLispasarelapago(List<Pasarelapago> lispasarelapago) {
        this.lispasarelapago = lispasarelapago;
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadepasarelapago;
    }

    @Override
    protected String displayObj(Pasarelapago obj) {
        return obj.getIdpasarela()+ " - " + obj.getNombre()+ " - " + obj.getConfigXml()+ " - " + 
                obj.getUpdatedAt()
                + " - " + obj.getIdusuarioUpdated();

    }

   public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadepasarelapago.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
             successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
   
   
    public void nuevapasarela() {
        StringBuilder err = new StringBuilder();
        facadepasarelapago.nuevapasa(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
             successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    

           public void editarpasarela() {
        StringBuilder err = new StringBuilder();
        facadepasarelapago.editarpasa(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro editado");
             successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
           
    
    public void editar() {
        StringBuilder err = new StringBuilder();
        facadepasarelapago.edit(obj, err);
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
        facadepasarelapago.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
       public void recuperaTotalPasarelas() {

        totalConsulta = facadepasarelapago.countTotalPasarelas();
    }   
         
       
       public void    TotalPasarelasPago(){
    
    lispasarelapago = facadepasarelapago.TodasPasarelasPago("idpasarela", "DESC");
    }


        

        
       public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadepasarelapago.countBuscar(valBusq);
        }
    }    
       

       public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadepasarelapago.buscarFullText(valBusq, first, maxRegLista);
    }   
       
        public List<SelectItem> selectId() {
        List<Pasarelapago> lstObjs = facadepasarelapago.listAllTipos("nombre", "ASC");
        List<SelectItem> lstS = new ArrayList<>();
        lstS.add(new SelectItem(null, "---"));

        for (Pasarelapago obj : lstObjs) {
            SelectItem it = new SelectItem(obj.getIdpasarela(), obj.getNombre());
            lstS.add(it);
        }
        return lstS;
    }
        
          public void recuperaById(Integer idpasarela) {
        if (idpasarela != null) {
            obj = facadepasarelapago.find(idpasarela);
        }
        
       
    }
          
          
    public void recuperanombre(Integer idpasarela) {
     
            nombrepasarela =  facadepasarelapago. PasarelaNombre(idpasarela);

    }
     
}
