/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.general;

import com.tncity.properties.Propiedad;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class PropiedadControl {

    Propiedad prop = Propiedad.getCurrentInstance();

    public PropiedadControl() {
    }

    //VERSION
    public String getVersion() {
        return prop.getVersion();
    }

    public String getVersionUpdated() {
        return prop.getVersionUpdated();
    }

    //GLOBAL
    public String getPathTmp() {
        return prop.getPathTmp();
    }

}
