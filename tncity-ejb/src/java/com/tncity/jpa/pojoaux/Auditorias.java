/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojoaux;

/**
 *
 * @author guiovanny
 */
public class Auditorias {

    String NombreTabla;
    String Operacion;
    String ValorAnterior;
    String NuevoValor;
    String UpdateDate;
    String USUARIO;
    Integer CODIGO_AU;

    public Auditorias() {
    }

    public Auditorias(Integer auditoria) {
      this.CODIGO_AU = auditoria; //To change body of generated methods, choose Tools | Templates.
    }
    
    
  
    

    public String getNombreTabla() {
        return NombreTabla;
    }

    public void setNombreTabla(String NombreTabla) {
        this.NombreTabla = NombreTabla;
    }

    public String getOperacion() {
        return Operacion;
    }

    public void setOperacion(String Operacion) {
        this.Operacion = Operacion;
    }

    public String getValorAnterior() {
        return ValorAnterior;
    }

    public void setValorAnterior(String ValorAnterior) {
        this.ValorAnterior = ValorAnterior;
    }

    public String getNuevoValor() {
        return NuevoValor;
    }

    public void setNuevoValor(String NuevoValor) {
        this.NuevoValor = NuevoValor;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String UpdateDate) {
        this.UpdateDate = UpdateDate;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public Integer getCODIGO_AU() {
        return CODIGO_AU;
    }

    public void setCODIGO_AU(Integer CODIGO_AU) {
        this.CODIGO_AU = CODIGO_AU;
    }



}
