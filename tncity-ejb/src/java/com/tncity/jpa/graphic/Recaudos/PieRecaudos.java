/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.graphic.Recaudos;
import java.io.Serializable;
/**
 *
 * @author guiovanny
 */
public class PieRecaudos  implements Serializable {
    Integer recaudos;
    String estado;

    public Integer getRecaudos() {
        return recaudos;
    }

    public void setRecaudos(Integer recaudos) {
        this.recaudos = recaudos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
