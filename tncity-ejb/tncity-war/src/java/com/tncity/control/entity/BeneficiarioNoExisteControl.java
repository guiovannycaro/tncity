/**
 * @name Beneficiario control
 *
 * @ description crud de beneficiario
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.BenefactorFacade;
import com.tncity.facade.entity.BeneficiarioNoExisteFacade;
import com.tncity.facade.entity.CiudadFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.BeneficiarioNoExiste;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojoaux.TelefoniaJson;
import com.tncity.jpa.pojoaux.TelefoniaManager;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "beneficiarionoexisteControl")
@RequestScoped
public class BeneficiarioNoExisteControl extends AbstractControl<BeneficiarioNoExiste> {

    @EJB
    BeneficiarioNoExisteFacade facadebeneficiarione;

    @EJB
    BenefactorFacade facadebenefactor;

    @EJB
    CiudadFacade facadeCiudad;

    List<Benefactor> lstbenefactores = new ArrayList<>();
    List<BeneficiarioNoExiste> lisben = new ArrayList<>();

    
    List<BeneficiarioNoExiste> listbeneficiariosne = new ArrayList<>();

    public List<BeneficiarioNoExiste> getListbeneficiariosne() {
        return listbeneficiariosne;
    }

    public void setListbeneficiariosne(List<BeneficiarioNoExiste> listbeneficiariosne) {
        this.listbeneficiariosne = listbeneficiariosne;
    }
            
            
    String nombresApellidos;
    String tipodocumento;
    String pin;
    String tariffgroupname;
    String idbenefactor;

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTariffgroupname() {
        return tariffgroupname;
    }

    public void setTariffgroupname(String tariffgroupname) {
        this.tariffgroupname = tariffgroupname;
    }

    public String getIdbenefactor() {
        return idbenefactor;
    }

    public void setIdbenefactor(String idbenefactor) {
        this.idbenefactor = idbenefactor;
    }

    public List<BeneficiarioNoExiste> getLisben() {
        return lisben;
    }

    public void setLisben(List<BeneficiarioNoExiste> lisben) {
        this.lisben = lisben;
    }

    public List<Benefactor> getLstbenefactores() {
        return lstbenefactores;
    }

    public void setLstbenefactores(List<Benefactor> lstbenefactores) {
        this.lstbenefactores = lstbenefactores;
    }

    public BeneficiarioNoExisteControl() {
        super(BeneficiarioNoExiste.class);
        attrOrd = "idbeneficiario";
        ascDesc = "ASC";
        obj.setIdpersona(new Persona());
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadebeneficiarione;
    }

    @Override
    protected String displayObj(BeneficiarioNoExiste obj) {
        return obj.getNombresApellidos() + " - " + obj.getPin() + " - " + obj.getTd() + " - " + obj.getIdbeneficiarione() + " - "
                + obj.getIdpersona().getNombres() + " - " + obj.getIdusuarioUpdated().getIdusuario() + " - " + obj.getUpdateAt();
    }

//nuevo beneficiario
    public void nuevo(String nombresApellidos,
            String tipodocumento,
            String pin,
            String tariffgroupname,
            String idbenefactor) {
        StringBuilder err = new StringBuilder();

        facadebeneficiarione.createbeneficiarione(nombresApellidos, tipodocumento,
                pin, tariffgroupname, idbenefactor, err);

        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar beneficiario
    public void editar(String nombresApellidos,
            String tipodocumento,
            String pin,
            String tariffgroupname,
            String idbenefactor) {
        StringBuilder err = new StringBuilder();

        facadebeneficiarione.editbeneficiarione(nombresApellidos, tipodocumento,
                pin, tariffgroupname, idbenefactor, err);

        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //eliminar beneficiario
    public void eliminar(String nombresApellidos,
            String tipodocumento,
            String pin,
            String tariffgroupname,
            String idbenefactor) {
        StringBuilder err = new StringBuilder();

        facadebeneficiarione.deletebeneficiarione(nombresApellidos, tipodocumento,
                pin, tariffgroupname, idbenefactor, err);

        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }

    public void guardarbenefeficiario(String nombre, String tdoc, String idbenefactor, String cedula, String ciudad) {
        StringBuilder err = new StringBuilder();

        facadebeneficiarione.crearbeneficiario(nombre, tdoc, idbenefactor, cedula, ciudad, err);

        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro recibido.");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public String consultarCIUDAD(Integer ci) {
        
        System.out.println("consulta ciudad " + ci);
        Ciudad c = facadeCiudad.findCiudadIDS(ci);
        return c.getNombre();
    }
    
        public void ConteoBeneficiariosNoExiste() {
  

        totalConsulta = facadebeneficiarione.countBeneficiarioNoExiste();
    }

    
                
      public void TodosBeneficiariosNoExiste(){
       
        listbeneficiariosne = facadebeneficiarione.listarBenefactorNE();
    }
      
          public void   recuperaConteoRegistrosFechas(String desde,String hasta){
             totalConsulta = facadebeneficiarione.countBeneficiarioNoExisteFechas(desde,hasta);
          
          }
          
        public void    RecuperarBeneficiarioNoExisteFechas(String desde,String hasta){
       
        listbeneficiariosne = facadebeneficiarione.listarBenefactorNEFechas(desde,hasta);
    }
}
