/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Configuracion;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.util.Archivo;
import com.tncity.util.JaxbUtil;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class ParamFacade extends AbstractFacade<Configuracion> {

    private static String configPath = Configuracion.class.getResource("/com/tncity/config/config.xml").getFile();

    private static HashMap<Class, Integer> mapParamsId = new HashMap<>();
    private static HashMap<Integer, AbstractConfig> mapParamsValue = new HashMap<>();

    public ParamFacade() {
        super(Configuracion.class);
    }

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
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
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Configuracion parseObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Configuracion obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Configuracion obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Configuracion getConfigFromBd(Integer idparam) {
        String hql = "SELECT c FROM Configuracion c WHERE c.idparam=" + idparam;
        return objectFromHQL(hql);
    }

    private String getConfigFromBdValues(Integer idparam) {
        Configuracion c = getConfigFromBd(idparam);
        if (c != null) {
            return c.getValor();
        }
        return null;
    }

    private Integer getIdClass(Class classConfig) {
        Integer id = mapParamsId.get(classConfig);
        if (id == null) {
            ConfigXml conf = JaxbUtil.xmlToObject(new Archivo(new File(configPath)).getContent(), ConfigXml.class);
            for (ConfigXml p : conf.lstParams) {
                if (p.getClassPojo().equals(classConfig.getCanonicalName())) {
                    mapParamsId.put(classConfig, p.getId());
                    return p.getId();
                }
            }
        }
        return id;
    }

    private void intMapClass() {
        try {
            ConfigXml conf = JaxbUtil.xmlToObject(new Archivo(new File(configPath)).getContent(), ConfigXml.class);
            for (ConfigXml p : conf.lstParams) {
                Class c = Class.forName(p.classPojo);
                mapParamsId.put(c, p.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> T getParamFromDb(Class<T> classConfig) {
        Integer idparam = getIdClass(classConfig);
        if (idparam != null) {
            String xml = getConfigFromBdValues(idparam);
            if (xml != null) {
                T obj = JaxbUtil.xmlToObject(xml, classConfig);
                return obj;
            }
        }

        try {
            return classConfig.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getParamFromCache(Class<T> classConfig) {
        Integer idparam = getIdClass(classConfig);
        AbstractConfig objConf = mapParamsValue.get(idparam);
        if (objConf == null) {
            T c = getParamFromDb(classConfig);
            mapParamsValue.put(idparam, (AbstractConfig) c);
            return c;
        }
        return (T) objConf;
    }

    public void saveParam(AbstractConfig param, Usuario usUpdated) {
        Integer idparam = getIdClass(param.getClass());
        Configuracion cOld = find(idparam);

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Configuracion configuracion = new Configuracion(idparam);
            configuracion.setIdusuarioUpdated(usUpdated);
            configuracion.setUpdatedAt(new Date());
            configuracion.setValor(JaxbUtil.objectToXML(param));

            if (cOld == null) {
                sess.save(configuracion);
            } else {
                sess.update(configuracion);
            }

            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();

        AbstractConfig obj = getParamFromDb(param.getClass());
        mapParamsValue.put(idparam, obj);
    }

    
      public void saveParamd(AbstractConfig param, Usuario usUpdated) {
        Integer idparam = getIdClass(param.getClass());
        Configuracion cOld = find(idparam);

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Configuracion configuracion = new Configuracion(idparam);
            configuracion.setIdusuarioUpdated(usUpdated);
            configuracion.setUpdatedAt(new Date());
            configuracion.setValor(JaxbUtil.objectToXML(param));

            if (cOld == null) {
                sess.save(configuracion);
            } else {
                sess.update(configuracion);
            }

            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();

        AbstractConfig obj = getParamFromDb(param.getClass());
        mapParamsValue.put(idparam, obj);
    }

    
    private Class getClassById(Integer idparam) {
        if (mapParamsId.size() == 0) {
            intMapClass();
        }

        Iterator it = mapParamsId.keySet().iterator();

        while (it.hasNext()) {
            Class c = (Class) it.next();
            Integer id = mapParamsId.get(c);
            if (id != null && id.equals(idparam)) {
                return c;
            }
        }

        return null;
    }
    
    public String getUrlImgLogoFromCache(String protocolHostPortContext){
        return protocolHostPortContext+"/img/app/logo.png";
    }

}
