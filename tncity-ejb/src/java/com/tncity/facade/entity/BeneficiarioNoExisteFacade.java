/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

/**
 *
 * @author guiovanny
 */
import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.TelefoniaConfig;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.BeneficiarioNoExiste;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Persona;

import com.tncity.jpa.pojo.Usuario;
import com.tncity.jpa.pojoaux.TelefoniaJson;
import com.tncity.jpa.pojoaux.TelefoniaManager;
import com.tncity.telefonia.client.Datos;
import static com.tncity.util.HashUtil.md5;
import com.tncity.util.JsonUtil;
import com.tncity.util.UrlContent;
import static java.lang.System.err;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class BeneficiarioNoExisteFacade extends AbstractFacade<BeneficiarioNoExiste> {

    @EJB
    PersonaFacade personaFacade;

    @EJB
    BenefactorFacade benefactorFacade;

    @EJB
    ParamFacade paramFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BeneficiarioNoExisteFacade() {
        super(BeneficiarioNoExiste.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idbeneficiario", "td", "pin", "nombresApellidos", "idpersona", "updatedAt", "idusuarioUpdated"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombres_apellidos"};
        return attrs;
    }

    @Override
    protected BeneficiarioNoExiste parseObject(Object[] o) {

        BeneficiarioNoExiste b = new BeneficiarioNoExiste();
        b.setIdbeneficiarione((Long) o[1]);

        b.setTd((String) o[2]);
        b.setPin((String) o[3]);
        b.setNombresApellidos((String) o[4]);

        b.setUpdateAt(new Date());
        b.getIdusuarioUpdated().setIdusuario((Integer) o[7]);

        b.setIdpersona(new Persona((Long) o[5]));
        return b;

    }

    @Override
    public void create(BeneficiarioNoExiste obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(BeneficiarioNoExiste obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void createbeneficiarione(String nombresApellidos,
            String tipodocumento,
            String pin,
            String tariffgroupname,
            String idbenefactor, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editbeneficiarione(String nombresApellidos,
            String tipodocumento,
            String pin,
            String tariffgroupname,
            String idbenefactor, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deletebeneficiarione(String nombresApellidos,
            String tipodocumento,
            String pin,
            String tariffgroupname,
            String idbenefactor, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void crearbeneficiario(String nombre, String tdoc, String idbenefactor, String cedula, String ciudad, StringBuilder err) {

        long idben = Long.parseLong(idbenefactor);

        Benefactor obj = benefactorFacade.findByNumID(idben);

        Persona p = personaFacade.findByIdBenefactor(obj);

        beginTransaction();
        try {
            SessionImpl sess = getSess();
            BeneficiarioNoExiste bOri = findBeneficiarioByNumdocumento(cedula);
            if (bOri == null) {

                String sql = " INSERT INTO beneficiarioNoExiste (td,pin,nombres_apellidos,idpersona,"
                        + "update_at,idusuario_updated ,tariffgroupname ) "
                        + "VALUES ('" + tdoc + "','" + cedula + "','" + nombre + "','" + p.getIdpersona() + "',"
                        + "'" + new Date() + "','" + UsuarioFacade.USUARIO_SYS + "','" + ciudad + "')";
                System.out.println("benefactor no existe " + sql);
                sess.createSQLQuery(sql).executeUpdate();

            } else {

                err.append("El número de documento ").append(cedula).append(" ya está registrado como PPL (Persona Privada de la Libertad)");
                return;

            }
            commitTransaction();

        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }

    public BeneficiarioNoExiste findBeneficiarioByNumdocumento(String cedula) {
        String hql = " SELECT b "
                + "  FROM BeneficiarioNoExiste b "
                + " WHERE b.pin ='" + cedula + "' ";
        System.out.println("beneficiario no existe " + hql);
        return objectFromHQL(hql);
    }
    
    
       public long countBeneficiarioNoExiste() {
        String sql = " SELECT "
                + " count(*)"
                + " FROM beneficiarionoexiste  "
                + " join persona  on beneficiarionoexiste.idpersona = persona.idpersona ";
         
       
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

       
       public long  countBeneficiarioNoExisteFechas(String desde,String hasta){
        String sql = " SELECT "
                + " count(*)"
                + " FROM beneficiarionoexiste  "
                + " join persona  on beneficiarionoexiste.idpersona = persona.idpersona "
                + " WHERE update_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and update_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss') ";
       
        return numFromSQL(sql, new BigInteger("0")).longValue();
       }
       
       

    public List<BeneficiarioNoExiste> listarBenefactorNE() {
        List<BeneficiarioNoExiste> lstR = new ArrayList<>();

        String sql = "SELECT "
                + " beneficiarionoexiste.idbeneficiarione,"
                + " beneficiarionoexiste.nombres_apellidos,"
                + " beneficiarionoexiste.td,"
                + " beneficiarionoexiste.pin,"
                + " beneficiarionoexiste.idpersona,"
                + " beneficiarionoexiste.update_at,"
                + " persona.nombres,"
                + " persona.apellidos,"
                + " beneficiarionoexiste.tariffgroupname"
                + " FROM beneficiarionoexiste  "
                + " join persona  on beneficiarionoexiste.idpersona = persona.idpersona "
                + " ORDER BY beneficiarionoexiste.idbeneficiarione ASC";

        System.out.println("listado " + sql);

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            BeneficiarioNoExiste b = new BeneficiarioNoExiste(new Long(o[0].toString()));
            b.setNombresApellidos(o[1].toString());
            b.setTd(o[2].toString());
            b.setPin(o[3].toString());
            b.setIdpersona(new Persona(new Long(o[4].toString())));

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date fechaDate = null;

            try {
                fechaDate = formato.parse(o[5].toString());

            } catch (Exception ex) {
                System.out.println(ex);
            }

            b.setUpdateAt(fechaDate);

      
            b.getIdpersona().setNombres((String) o[6].toString());
            b.getIdpersona().setApellidos((String) o[7].toString());
            b.setTariffgroupname(new Ciudad(new Integer(o[8].toString())));
            lstR.add(b);

        }
        return lstR;

    }
    
     public List<BeneficiarioNoExiste> listarBenefactorNEFechas(String desde,String hasta){
       List<BeneficiarioNoExiste> lstR = new ArrayList<>();

        String sql = "SELECT "
                + " beneficiarionoexiste.idbeneficiarione,"
                + " beneficiarionoexiste.nombres_apellidos,"
                + " beneficiarionoexiste.td,"
                + " beneficiarionoexiste.pin,"
                + " beneficiarionoexiste.idpersona,"
                + " beneficiarionoexiste.update_at,"
                + " persona.nombres,"
                + " persona.apellidos,"
                + " beneficiarionoexiste.tariffgroupname"
                + " FROM beneficiarionoexiste  "
                + " join persona  on beneficiarionoexiste.idpersona = persona.idpersona "
                + " WHERE update_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and update_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss') "
                + " ORDER BY beneficiarionoexiste.idbeneficiarione ASC";

        System.out.println("listado " + sql);

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            BeneficiarioNoExiste b = new BeneficiarioNoExiste(new Long(o[0].toString()));
            b.setNombresApellidos(o[1].toString());
            b.setTd(o[2].toString());
            b.setPin(o[3].toString());
            b.setIdpersona(new Persona(new Long(o[4].toString())));

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date fechaDate = null;

            try {
                fechaDate = formato.parse(o[5].toString());

            } catch (Exception ex) {
                System.out.println(ex);
            }

            b.setUpdateAt(fechaDate);

      
            b.getIdpersona().setNombres((String) o[6].toString());
            b.getIdpersona().setApellidos((String) o[7].toString());
            b.setTariffgroupname(new Ciudad(new Integer(o[8].toString())));
            lstR.add(b);

        }
        return lstR;
     
     }
}
