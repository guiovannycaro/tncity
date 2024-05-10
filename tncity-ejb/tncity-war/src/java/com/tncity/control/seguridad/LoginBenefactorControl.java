/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.seguridad;

import com.tncity.control.general.FacesBenefactorUtil;
import com.tncity.control.general.FacesUtil;
import com.tncity.facade.entity.BenefactorFacade;
import com.tncity.facade.entity.RecaudoFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Recaudo;
import com.tncity.util.HashUtil;
import static com.tncity.util.Numero.isNumeric;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginBenefactorControl")
@SessionScoped
public class LoginBenefactorControl implements Serializable {

    @EJB
    BenefactorFacade benefactorFacade;

    @EJB
    RecaudoFacade recaudoFacade;

    Benefactor usLog = new Benefactor();

    boolean successful = false;
    String userName;
    String documento;
    String password;
    Persona personaTmp;

    String sesionbene;

    public String getSesionbene() {
        return sesionbene;
    }

    public void setSesionbene(String sesionbene) {
        this.sesionbene = sesionbene;
    }

    public void validaIngreso() {
        StringBuilder err = new StringBuilder();
        successful = false;
        usLog = benefactorFacade.buscarByLogin(userName);

        if (usLog == null) {
            FacesBenefactorUtil.currentInstance().msgError("ALERTA: ", "¡Usuario No Existe! Usted no se encuentra regístrado,lo invitamos a regiśtrarse");
            return;
        }

        if (!usLog.getIsActivo()) {
            FacesBenefactorUtil.currentInstance().msgError("ALERTA: ", "¡Usuario Inactivo!");
            return;
        }
        if (!usLog.getConstrasena().equals(HashUtil.md5(password))) {
            FacesBenefactorUtil.currentInstance().msgError("ALERTA: ", "¡Usuario y/o Contraseña incorrecto!");
            return;
        }

        if (password == "" && userName == "") {
            FacesBenefactorUtil.currentInstance().msgError("ALERTA: ", "¡Usuario No Valido ! , Por Favor diligencie su correo electrónico y contraseña");
            return;
        }

        successful = true;

    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void salir() {
        usLog = null;
        successful = false;
    }

    public boolean isSessionValida() {
        if (usLog != null && successful) {
            return true;
        }
        return false;
    }

    public boolean isSessionValidapasarela(Benefactor bn) {

        System.out.println("benefactor id " + bn.getUsername());

        usLog = benefactorFacade.buscarByLogin(bn.getUsername());

        successful = true;

        if (usLog != null && successful) {
            return true;
        }
        return false;
    }

    public String validaSession() {
        if (!isSessionValida()) {
            FacesUtil.currentInstance().msgError("", "La sesión ha caducado !");
            return "<script type='text/javascript'>document.location.href='" + FacesUtil.currentInstance().getProtocolHostPortContext() + "/app-familiar/access.xhtml';</script>";
        }
        return null;
    }

    public String validaSessionPasarela(String recaudoid) {

        String cd = "";

        if (isNumeric(recaudoid) == true) {
                           return "<script type='text/javascript'>document.location.href='" + FacesUtil.currentInstance().getProtocolHostPortContext() + "/app-familiar/access.xhtml';</script>";
        } else {
     

            Integer codrec = Integer.parseInt(recaudoFacade.decodificar(recaudoid));
            System.out.println("dato recarga" + codrec);

            Recaudo ur = recaudoFacade.RecaudoPasarelaId(codrec);
            System.out.println("dato recarga" + ur.getIdrecaudo());
            System.out.println("dato recarga a string " + ur.getIdrecaudo().toString());

            System.out.println("benefactor " + ur.getIdbenefactor().getIdpersona());

            Benefactor bn = ur.getIdbenefactor();

            if (!isSessionValidapasarela(bn)) {
                FacesUtil.currentInstance().msgError("", "La sesión ha caducado !");
                return "<script type='text/javascript'>document.location.href='" + FacesUtil.currentInstance().getProtocolHostPortContext() + "/app-familiar/access.xhtml';</script>";
            }

        }
        return null;
    }

    public Benefactor getUsLog() {
        return usLog;
    }

    public void setUsLog(Benefactor usLog) {
        this.usLog = usLog;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Persona getPersonaTmp() {
        return personaTmp;
    }

    public void setPersonaTmp(Persona personaTmp) {
        this.personaTmp = personaTmp;
    }

}
