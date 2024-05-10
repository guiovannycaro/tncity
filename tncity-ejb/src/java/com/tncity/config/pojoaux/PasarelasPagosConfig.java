/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PasarelasPagosConfig extends AbstractConfig {

    Integer Idpasarela;
    String Descripcion;

    public Integer getIdpasarela() {
        return Idpasarela;
    }

    public void setIdpasarela(Integer Idpasarela) {
        this.Idpasarela = Idpasarela;
    }

  

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }


}
