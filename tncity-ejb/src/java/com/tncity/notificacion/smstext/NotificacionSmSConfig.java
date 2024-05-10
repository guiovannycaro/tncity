package com.tncity.notificacion.smstext;


import com.tncity.notificacion.*;
import com.tncity.config.AbstractConfig;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notificacionConfig")
public class NotificacionSmSConfig extends AbstractConfig {

    List<NotificacionSmSXml> lstNotificacion = new ArrayList<>();

    @XmlElement(name = "notificacion")
    public List<NotificacionSmSXml> getLstNotificacion() {
        return lstNotificacion;
    }

    public void setLstNotificacion(List<NotificacionSmSXml> lstNotificacion) {
        this.lstNotificacion = lstNotificacion;
    }

}
