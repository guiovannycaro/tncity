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
public class TelefoniaJson {
    String id;
    String cedula;
    String status;
    String lastname;
    String tariffgroupname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTariffgroupname() {
        return tariffgroupname;
    }

    public void setTariffgroupname(String tariffgroupname) {
        this.tariffgroupname = tariffgroupname;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
}
