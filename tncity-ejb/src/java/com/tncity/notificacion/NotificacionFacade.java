/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.notificacion;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.EmailConfig;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Notificacion;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.properties.Propiedad;
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
public class NotificacionFacade extends AbstractFacade<Notificacion> {

    public final static String TIPO_SMS = "SMS";
    public final static String TIPO_EMAIL = "EMAIL";

    public final static int NOTIF_ALERT_TRAKER = 3;

    private final static String notifPath = Notificacion.class.getResource("/com/tncity/notificacion/notificacion.xml").getFile();
    private static NotificacionConfig notificacionConfig;

    @EJB
    ParamFacade paramFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public NotificacionFacade() {
        super(Notificacion.class);
    }

    public NotificacionConfig getNofiticacionConfigFromCache() {
        if (notificacionConfig == null) {
            notificacionConfig = buildNC();
        }
        return notificacionConfig;
    }

    private NotificacionConfig buildNC() {
        String xml = new Archivo(new File(notifPath)).getContent();

        NotificacionConfig ncFromFile = JaxbUtil.xmlToObject(xml, NotificacionConfig.class);
        NotificacionConfig ncFromBd = paramFacade.getParamFromCache(NotificacionConfig.class);

        //Valores de la base de datos
        HashMap<Integer, NotificacionXml> mapBD = new HashMap<>();
        for (NotificacionXml n : ncFromBd.getLstNotificacion()) {
            mapBD.put(n.getId(), n);
        }

        //Actualizando informacion
        for (NotificacionXml n : ncFromFile.getLstNotificacion()) {
            NotificacionXml nBd = mapBD.get(n.getId());
            if (nBd != null) {
                parseObjFromBd(nBd, n);
            }
        }

        return ncFromFile;
    }

    private void parseObjFromBd(NotificacionXml ori, NotificacionXml des) {
        des.setIsActiva(ori.isIsActiva());
        des.setMailAsunto(ori.getMailAsunto());
        des.setMailContent(ori.getMailContent());
        des.setSendEmail(ori.isSendEmail());
        des.setSendSms(ori.isSendSms());
        des.setSmsContent(ori.getSmsContent());
    }

    public NotificacionXml findByIdfromCache(Integer id) {
        NotificacionConfig nc = getNofiticacionConfigFromCache();
        for (NotificacionXml n : nc.getLstNotificacion()) {
            if (n.getId() == id.intValue()) {
                return n;
            }
        }
        return null;
    }


    public void sendNotifMailHilo(int idNotifConf, String email, HashMap<String, String> params, StringBuilder err) {
        List<String> lstEmail = new ArrayList<>();
        lstEmail.add(email);
        sendNotifMailHilo(idNotifConf, lstEmail, params, err);
    }

    public void sendNotifMail(int idNotifConf, String email, HashMap<String, String> params, StringBuilder err) {
        List<String> lstEmail = new ArrayList<>();
        lstEmail.add(email);
        sendNotif(idNotifConf, new ArrayList<BigInteger>(), lstEmail, params, err);
    }

    public void sendNotifMailHilo(int idNotifConf, List<String> lstEmail, HashMap<String, String> params, StringBuilder err) {
        Thread h = new Thread() {
            public void run() {
                sendNotif(idNotifConf, new ArrayList(), lstEmail, params, err);
            }
        };
        h.start();
    }

    private void sendNotif(int idNotifConf, List<BigInteger> lstCel, List<String> lstEmail, HashMap<String, String> params, StringBuilder err) {
        NotificacionXml notif = findByIdfromCache(idNotifConf);
        if (notif == null) {
            err.append("Notificacion config ").append(idNotifConf).append(" no encontrada !");
            return;
        }
        if (!notif.isActiva) {
            return;
        }

        //Set Params
        String smsContent = notif.getSmsContent();
        String mailContent = notif.getMailContent();
        String mailAsunto = notif.getMailAsunto();
        for (NotificacionVarXml var : notif.getVar()) {
            String value = params.get(var.getName());
            if (value != null) {
                smsContent = smsContent.replaceAll(var.getName(), value);
                mailContent = mailContent.replaceAll(var.getName(), value);
                mailAsunto = mailAsunto.replaceAll(var.getName(), value);
            }
        }

        if (notif.sendEmail) {
            sendMAIL(notif.id, mailAsunto, mailContent, lstEmail, err);
        }
        /*if (notif.sendSms) {
            sendSMS(TIPO_SMS, lstCel, params, err);
        }*/

    }

    private void sendMAIL(int idnotif, String asunto, String content, List<String> lstEmail, StringBuilder err) {
        sendSimpleMailTnfactor(asunto, content, lstEmail, err);
    }

    public void sendSimpleMailTnfactor(String asunto, String contenido, String mail, StringBuilder err) {
        List<String> lstMail = new ArrayList<>();
        lstMail.add(mail);
       sendSimpleMailTnfactor(asunto, contenido, lstMail, err);
    }

    public void sendSimpleMailTnfactor(String asunto, String contenido, List<String> lstEmail, StringBuilder err) {
        EmailConfig mailConfig = paramFacade.getParamFromCache(EmailConfig.class);
        try {
            //Connect
            if (!SmtpUtil.getInstance().isConnect()) {
                SmtpUtil.getInstance().connect(mailConfig.getSmtp(), mailConfig.getUsuarioMail(), mailConfig.getContrasenaMail(), mailConfig.getNotificacion(),new Integer(mailConfig.getPort()), err);
            }
            
            List<File> lstAdjuntos = new ArrayList<>();
            SmtpUtil.getInstance().sendMsgHtml(lstEmail,lstAdjuntos, asunto, contenido, err);

        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
        }
    }

    private void registrarNotificacionesMail(Integer idnotificacionConf, String asunto, String contenido, List<String> lstEmail) {
        beginTransaction();
        SessionImpl sess = getSess();
        try {

            for (String email : lstEmail) {
                Notificacion n = new Notificacion();
                n.setConfigId(idnotificacionConf);
                n.setCreatedAt(new Date());
                n.setEmail(email);
                n.setEmailAsunto(asunto);
                n.setEmailContenido(contenido);
                n.setTipo(TIPO_EMAIL);
                sess.save(n);
            }

            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    @Override
    protected String[] attrFullTextCriteria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String[] attrsQueryLight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Notificacion parseObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Notificacion obj, StringBuilder err) {
        beginTransaction();
        SessionImpl sess = getSess();
        try {
            obj.setCreatedAt(new Date());
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
    public void edit(Notificacion obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        public void saveNotificacionConfig(NotificacionXml notif, Usuario usUpdated) {
        NotificacionConfig nc = buildNC();

        for (NotificacionXml n : nc.getLstNotificacion()) {
            if (n.getId() == notif.getId()) {
                parseObjFromBd(notif, n);
                break;
            }
        }
        notificacionConfig = nc;
        paramFacade.saveParam(nc, usUpdated);
    }

}
