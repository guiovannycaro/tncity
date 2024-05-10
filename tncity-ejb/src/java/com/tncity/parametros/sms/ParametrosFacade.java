/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.parametros.sms;

import com.tncity.config.ParamFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Parametrossms;

import com.tncity.jpa.pojo.Usuario;

import com.tncity.util.Archivo;
import com.tncity.util.JaxbUtil;
import com.tncity.util.SmtpUtil;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class ParametrosFacade extends AbstractFacade<Parametrossms> {

    public final static String TIPO_SMS = "SMS";


    public final static int NOTIF_ALERT_TRAKER = 3;

    private final static String notifPath = Parametrossms.class.getResource("/com/tncity/parametros/sms/parametros.xml").getFile();
    private static ParametrossmsConfig paramterossmsConfig;

       @EJB
    ParamFacade paramFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ParametrosFacade() {
        super(Parametrossms.class);
    }

    public ParametrossmsConfig getParametrosConfigFromCache() {
        if (paramterossmsConfig == null) {
            paramterossmsConfig = buildNC();
        }
        return paramterossmsConfig;
    }

    private ParametrossmsConfig buildNC() {
        String xml = new Archivo(new File(notifPath)).getContent();

        ParametrossmsConfig ncFromFile = JaxbUtil.xmlToObject(xml, ParametrossmsConfig.class);
        ParametrossmsConfig ncFromBd = paramFacade.getParamFromCache(ParametrossmsConfig.class);

        //Valores de la base de datos
        HashMap<Integer, ParametrosXml> mapBD = new HashMap<>();
        for (ParametrosXml n : ncFromBd.getLstparametros()) {
            mapBD.put(n.getId(), n);
        }

        //Actualizando informacion
        for (ParametrosXml n : ncFromFile.getLstparametros()) {
            ParametrosXml nBd = mapBD.get(n.getId());
            if (nBd != null) {
                parseObjFromBd(nBd, n);
            }
        }

        return ncFromFile;
    }

    private void parseObjFromBd(ParametrosXml ori, ParametrosXml des) {
        
        des.setHost(ori.getHost());
        des.setProtocolo(ori.getProtocolo());
        des.setPuerto(ori.getPuerto());
        des.setIsActiva(ori.isIsActiva());
        
       
    }

    public  ParametrosXml findByIdfromCache(Integer id) {
        ParametrossmsConfig nc = getParametrosConfigFromCache();
        for ( ParametrosXml n : nc.getLstparametros()) {
            if (n.getId() == id.intValue()) {
                return n;
            }
        }
        return null;
    }



   

   

      
        /*if (notif.sendSms) {
            sendSMS(TIPO_SMS, lstCel, params, err);
        }*/

   



 



    @Override
    protected String[] attrFullTextCriteria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String[] attrsQueryLight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Parametrossms parseObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Parametrossms obj, StringBuilder err) {
        beginTransaction();
        SessionImpl sess = getSess();
        try {
           
            sess.save(obj);
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            err.append(e.toString());
            rollbackTransaction();
        }
        endTransaction();
    }

    @Override
    public void edit(Parametrossms obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        public void saveParametrosConfig(ParametrosXml notif, Usuario usUpdated) {
        ParametrossmsConfig nc = buildNC();

        for (ParametrosXml n : nc.getLstparametros()) {
            if (n.getId() == notif.getId()) {
                parseObjFromBd(notif, n);
                break;
            }
        }
        paramterossmsConfig = nc;
        paramFacade.saveParamd(nc, usUpdated);
    }

 
  

}
