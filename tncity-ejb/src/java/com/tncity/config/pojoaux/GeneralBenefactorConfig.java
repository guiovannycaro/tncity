/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeneralBenefactorConfig extends AbstractConfig {

    Integer idusuarioBenefactor;
    String nombreSistema;

    public Integer getIdusuarioBenefactor() {
        return idusuarioBenefactor;
    }

    public void setIdusuarioBenefactor(Integer idusuarioBenefactor) {
        this.idusuarioBenefactor = idusuarioBenefactor;
    }



    public String getNombreSistema() {
        return nombreSistema;
    }

    public void setNombreSistema(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

}
