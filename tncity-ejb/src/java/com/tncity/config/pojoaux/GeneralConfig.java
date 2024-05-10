/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeneralConfig extends AbstractConfig {

    Integer idusuarioAdministrador;
    String nombreSistema;

    String appAdminProtocol = "http";
    String appAdminHost = "localhost";
    int appAdminPort = 8080;
    String appAdminPath = "tncity-war";
    String glassfishPathLog;
    String urlPerfilFacturador = "https://tncity.co";

    public Integer getIdusuarioAdministrador() {
        return idusuarioAdministrador;
    }

    public void setIdusuarioAdministrador(Integer idusuarioAdministrador) {
        this.idusuarioAdministrador = idusuarioAdministrador;
    }

    public String getNombreSistema() {
        return nombreSistema;
    }

    public void setNombreSistema(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

    public String getAppAdminProtocol() {
        return appAdminProtocol;
    }

    public void setAppAdminProtocol(String appAdminProtocol) {
        this.appAdminProtocol = appAdminProtocol;
    }

    public String getAppAdminHost() {
        return appAdminHost;
    }

    public void setAppAdminHost(String appAdminHost) {
        this.appAdminHost = appAdminHost;
    }

    public int getAppAdminPort() {
        return appAdminPort;
    }

    public void setAppAdminPort(int appAdminPort) {
        this.appAdminPort = appAdminPort;
    }

    public String getAppAdminPath() {
        return appAdminPath;
    }

    public void setAppAdminPath(String appAdminPath) {
        this.appAdminPath = appAdminPath;
    }

    public String getGlassfishPathLog() {
        return glassfishPathLog;
    }

    public void setGlassfishPathLog(String glassfishPathLog) {
        this.glassfishPathLog = glassfishPathLog;
    }

    public String getUrlPerfilFacturador() {
        return urlPerfilFacturador;
    }

    public void setUrlPerfilFacturador(String urlPerfilFacturador) {
        this.urlPerfilFacturador = urlPerfilFacturador;
    }

}
