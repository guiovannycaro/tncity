package com.tncity.notificacion;


import com.tncity.config.AbstractConfig;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notificacionConfig")
public class NotificacionConfig extends AbstractConfig {

    List<NotificacionXml> lstNotificacion = new ArrayList<>();

    @XmlElement(name = "notificacion")
    public List<NotificacionXml> getLstNotificacion() {
        return lstNotificacion;
    }

    public void setLstNotificacion(List<NotificacionXml> lstNotificacion) {
        this.lstNotificacion = lstNotificacion;
    }

}
