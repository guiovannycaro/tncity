/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.seguridad;

import com.tncity.control.general.FacesUtil;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.util.HashUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginAdminControl")
@SessionScoped
public class LoginAdminControl implements Serializable {

    @EJB
    UsuarioFacade usuarioFacade;

    Usuario usLog = new Usuario();
    boolean successful = false;
    String userName;
    String password;
    Persona personaTmp;

    public void validaIngreso() {
        successful = false;
        usLog = usuarioFacade.buscarByLogin(userName);
        if (userName.equals("")) {
            FacesUtil.currentInstance().msgError("ALERTA: ", "¡Debe ingresar un Usuario!");
            return;
        }
        
           if (password.equals("")) {
            FacesUtil.currentInstance().msgError("ALERTA: ", "¡Debe ingresar una Contraseña !");
            return;
        }
        
        if (usLog == null) {
            FacesUtil.currentInstance().msgError("ALERTA: ", "¡Debe Completar Los Campos !");
            return;
        }
        if (!usLog.getIsActivo()) {
            FacesUtil.currentInstance().msgError("ALERTA: ", "¡Usuario Inactivo!");
            return;
        }
        if (!usLog.getContrasena().equals(HashUtil.md5(password))) {
            FacesUtil.currentInstance().msgError("ALERTA: ", "¡Usuario y/o Contraseña incorrecto!");
            return;
        }
        successful = true;
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

    public String validaSession() {
        if (!isSessionValida()) {
            FacesUtil.currentInstance().msgError("", "La sesión ha caducado !");
            return "<script type='text/javascript'>document.location.href='" + FacesUtil.currentInstance().getProtocolHostPortContext() + "/app-admin/access.xhtml';</script>";
        }
        return null;
    }

    public Usuario getUsLog() {
        return usLog;
    }

    public void setUsLog(Usuario usLog) {
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

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Persona getPersonaTmp() {
        return personaTmp;
    }

    public void setPersonaTmp(Persona personaTmp) {
        this.personaTmp = personaTmp;
    }

}