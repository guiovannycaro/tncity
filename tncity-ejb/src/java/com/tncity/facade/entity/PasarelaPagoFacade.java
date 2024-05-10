/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.EmailConfig;

import com.tncity.facade.general.AbstractFacade;

import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Cuenta;
import com.tncity.jpa.pojo.Pasarelapago;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.util.SerialClone;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class PasarelaPagoFacade extends AbstractFacade<Pasarelapago> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PasarelaPagoFacade() {
        super(Pasarelapago.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idpasarela", "nombre", "configXml", "comision", "updatedAt", "idusuarioUpdated"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"idpasarela", "nombre", "configXml", "comision", "updatedAt", "idusuarioUpdated"};
        return attrs;
    }

    @Override
    protected Pasarelapago parseObject(Object[] o) {
        Pasarelapago p = new Pasarelapago((Integer) o[0]);
        p.setNombre(o[1].toString());
        p.setConfigXml(o[2].toString());
        p.setComision(new Double(o[3].toString()));
        p.setUpdatedAt(new Date(o[4].toString()));

        p.setIdusuarioUpdated(new Usuario(new Integer(o[4].toString())));
        return p;
    }

    @Override
    public void create(Pasarelapago obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Pasarelapago obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idpasarela, StringBuilder err) {
        deleteGeneral(idpasarela, err);
    }

    public long countTotalPasarelas() {
        String sql = "SELECT "
                + " count(*)"
                + " FROM pasarelapago  ";

      
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    public List<Pasarelapago> TodasPasarelasPago(String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " pasarelapago.idpasarela,"
                + " pasarelapago.nombre,"
                + " pasarelapago.config_xml,"
                + " pasarelapago.comision,"
                + " pasarelapago.idusuario_updated"
                + " FROM pasarelapago  "
                + " ORDER BY pasarelapago." + attrOrder + " " + ascDesc;
        

        List<Pasarelapago> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Pasarelapago pg = new Pasarelapago(new Integer(o[0].toString()));
            pg.setNombre(o[1].toString());
            pg.setConfigXml(o[2].toString());
            pg.setComision(new Double(o[3].toString()));
            pg.setIdusuarioUpdated(new Usuario(new Integer(o[4].toString())));
            lstR.add(pg);

        }
        return lstR;
    }

    public List<Pasarelapago> PasarelasPago() {

        String sql = " SELECT "
                + " pasarelapago.idpasarela,"
                + " pasarelapago.nombre,"
                + " pasarelapago.config_xml,"
                + " pasarelapago.comision,"
                + " pasarelapago.idusuario_updated"
                + " FROM pasarelapago  ";
   

        List<Pasarelapago> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Pasarelapago pg = new Pasarelapago(new Integer(o[0].toString()));
            pg.setNombre(o[1].toString());
            pg.setConfigXml(o[2].toString());
            pg.setComision(new Double(o[3].toString()));
            pg.setIdusuarioUpdated(new Usuario(new Integer(o[4].toString())));
            lstR.add(pg);

        }
        return lstR;
    }

    public void nuevapasa(Pasarelapago obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();
            Pasarelapago p = new Pasarelapago();

            p.setNombre(obj.getNombre());
            p.setConfigXml(obj.getConfigXml());
            p.setComision(obj.getComision());
            p.setIdusuarioUpdated(new Usuario(UsuarioFacade.USUARIO_SYS));

            p.setUpdatedAt(new Date());
            sess.save(p);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }

    public void editarpasa(Pasarelapago obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE pasarelapago "
                    + "    SET nombre='" + obj.getNombre() + "',"
                    + "        config_xml='" + obj.getConfigXml() + "',"
                    + "        comision='" + obj.getComision() + "',"
                    + "        idusuario_updated='" + UsuarioFacade.USUARIO_SYS + "',"
                    + "        updated_at= '" + new Date() + "' "
                    + " WHERE idpasarela=" + obj.getIdpasarela();

            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }

    public long countBuscar(String valbusq) {
        String sql = " SELECT COUNT(pasarelapago.idpasarela)"
                + "  FROM pasarelapago \n"
                + "	WHERE to_tsvector(pasarelapago.nombre ||' '||pasarelapago.config_xml\n"
                + " )@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n";
        
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    public List<Pasarelapago> buscarFullText(String valBusq, int first, int maxRegLista) {

        String sql = "SELECT "
                + " pasarelapago.idpasarela,"
                + " pasarelapago.nombre,"
                + " pasarelapago.config_xml,"
                + " pasarelapago.comision,"
                + " pasarelapago.idusuario_updated"
                + " FROM pasarelapago  "
                + "	WHERE to_tsvector(pasarelapago.nombre ||' '||pasarelapago.config_xml\n"
                + " )@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') "
                + "ORDER BY pasarelapago.idpasarela DESC";

        List<Pasarelapago> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            Pasarelapago pg = new Pasarelapago(new Integer(o[0].toString()));
            pg.setNombre(o[1].toString());
            pg.setConfigXml(o[2].toString());
            pg.setComision(new Double(o[3].toString()));
            pg.setIdusuarioUpdated(new Usuario(new Integer(o[3].toString())));

            lstR.add(pg);
        }

        return lstR;
    }

    public List<Pasarelapago> listAllTipos(String attrOrder, String ascDesc) {
        String hql = "SELECT t "
                + " FROM Pasarelapago t "
                + " ORDER BY " + attrOrder + " " + ascDesc;
        
        return findList(hql);
    }

    public Pasarelapago PasarelaNombre(Integer idpasarela) {
        String hql = "SELECT t "
                + " FROM Pasarelapago t "
                + " where idpasarela = " + idpasarela + "";
        
        return objectFromHQL(hql);
    }

    public Pasarelapago NombrePasarela(String nombre) {
        String hql = "SELECT t "
                + " FROM Pasarelapago t "
                + " where t.nombre = '" + nombre + "'";
        
        return objectFromHQL(hql);
    }

    public Pasarelapago Pasareladeldia(Integer idpasarela) {

        String hql = "SELECT C "
                + "  FROM Pasarelapago C "
                + " WHERE idpasarela = '" + idpasarela + "'";
     
        return objectFromHQL(hql);

    }

    public Pasarelapago getDatosPasarela(String idpasarelaspago) {

        String hql = "SELECT C "
                + "  FROM Pasarelapago C "
                + " WHERE idpasarela = '" + idpasarelaspago + "'";

        return objectFromHQL(hql);

    }

    public List<Pasarelapago> PasarelasPagoID() {
        String sql = " SELECT "
                + " pasarelapago.idpasarela,"
                + " pasarelapago.nombre,"
                + " pasarelapago.config_xml,"
                + " pasarelapago.comision,"
                + " pasarelapago.idusuario_updated"
                + " FROM pasarelapago  "
                + " ORDER BY pasarelapago.idpasarela DESC";

        List<Pasarelapago> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            Pasarelapago pg = new Pasarelapago(new Integer(o[0].toString()));
            pg.setNombre(o[1].toString());
            pg.setConfigXml(o[2].toString());
            pg.setComision(new Double(o[3].toString()));
            pg.setIdusuarioUpdated(new Usuario(new Integer(o[3].toString())));

            lstR.add(pg);
        }

        return lstR;
    }

    public List<Pasarelapago> PasarelasIDPasarela( String attrOrder, String ascDesc) {
        String sql
                = " SELECT"
                + " pasarelapago.idpasarela ,"
                + " pasarelapago.nombre ,"
                + " pasarelapago.config_xml ,"
                + " pasarelapago.comision"
                + " FROM pasarelapago "
               + " WHERE idpasarela <> 2 and  idpasarela <> 1"
        +" ORDER BY pasarelapago." + attrOrder + " DESC";
 
        List<Pasarelapago> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            Pasarelapago a = new Pasarelapago(new Integer(o[0].toString()));
            a.setNombre(o[1].toString());
            a.setConfigXml(o[2].toString());
            a.setComision(new Double(o[3].toString()));

            lstR.add(a);
        }

        return lstR;
    }
}
