package com.tncity.parametros.sms;


import com.tncity.notificacion.*;
import com.tncity.config.AbstractConfig;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parametrossmsConfig")
public class ParametrossmsConfig extends AbstractConfig {

    List<ParametrosXml> lstparametros = new ArrayList<>();

    @XmlElement(name = "parametros")
    public List<ParametrosXml> getLstparametros() {
        return lstparametros;
    }

    public void setLstparametros(List<ParametrosXml> lstparametros) {
        this.lstparametros = lstparametros;
    }

 

}
