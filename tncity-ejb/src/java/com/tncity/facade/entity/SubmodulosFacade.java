/**
 * @name submodulos Facade
 *
 * @ description crud de submodulos
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Secciones;

import com.tncity.security.SecurityFacade;
import com.tncity.util.Cadena;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class SubmodulosFacade extends AbstractFacade<Secciones> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public SubmodulosFacade() {
        super(Secciones.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idseccion", "nombre", "descripcion", "imagen", "url"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Secciones parseObject(Object[] o) {
        Secciones m = new Secciones((Integer) o[0]);
        m.setNombre((String) o[1]);
        m.setNombrevisual((String) o[4]);
        m.setEstado((Boolean)o[5]);
        return m;
    }
//crear submodulos
    
    @Override
    public void create(Secciones obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Secciones mo = new Secciones();
             mo.setNombre(obj.getNombre());
             mo.setNombrevisual(obj.getNombrevisual());
             mo.setEstado(obj.getEstado());
        
            sess.save(mo);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }
    
    //editar secciones

    @Override
    public void edit(Secciones obj, StringBuilder err) {
        beginTransaction();
        try {
            SessionImpl sess = getSess();
            String nombre = obj.getNombre();

           

            String sql = " UPDATE secciones "
                    + "    SET nombre ='" + obj.getNombre() + "',"
                    + "      nombrevisual ='" + obj.getNombrevisual()+ "',"
                    + "      estado =" + obj.getEstado()+ " "
                    + " WHERE idseccion =" + obj.getIdseccion();
            System.out.println("actualiza modulo" + sql);
            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    //borrar secciones
    
    @Override
    public void delete(Object idmodulo, StringBuilder err) {
        deleteGeneral(idmodulo, err);
    }

   //listar secciones por valor dado
    
    public List<Secciones> listFullTextLight(String query, String attrOrd, String ascDesc, int firstReg, int maxReg) {
        List<Secciones> lstC = new ArrayList<>();
        String sql = "SELECT "
                + " secciones.idseccion,"
                + " secciones.nombre,"
                + " secciones.nombrevisual, "
                + " secciones.estado "
                + " FROM secciones"
                + " WHERE to_tsvector(secciones.nombre)@@to_tsquery('" + new Cadena().reemplazaEspacios(query, "&").trim() + "') "
                + " ORDER BY secciones." + attrOrd + " ASC";
        System.out.println("SQL:" + sql);
        List<Object[]> lst = findNativeGeneric(sql, firstReg, maxReg);
        for (Object[] o : lst) {
            Secciones c = new Secciones(new Integer(o[0].toString()));
            c.setNombre(o[1].toString());
            c.setNombrevisual(o[4].toString());
            c.setEstado(new Boolean(o[5].toString()));

            lstC.add(c);
        }
        return lstC;
    }

    // buscar secciones por id session
    
    public Secciones findIDModulo(String idmodulo) {

        String hql = " SELECT  M FROM secciones M"
                + " WHERE M.idseccion='" + idmodulo + "'";
        System.out.println("id seccion " + hql);
        return objectFromHQL(hql);

    }

    //contar secciones
    
    public long countTotalModulos() {
        String sql = "SELECT "
                + " count(*)"
                + " FROM secciones  ";

        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    
    public List<Secciones> TodosModulos(String attrOrder, String ascDesc) {

        String sql = " SELECT "
                + " secciones.idseccion,"
                + " secciones.nombre,"
                + " secciones.nombrevisual, "
                + " secciones.estado "
                + " FROM secciones"
                + " ORDER BY secciones." + attrOrder + " " + ascDesc;
        System.out.println("todos secciones : " + sql);

        List<Secciones> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Secciones m= new Secciones(new Integer(o[0].toString()));
            m.setNombre(o[1].toString());
            m.setNombrevisual(o[2].toString());
            m.setEstado(new Boolean(o[3].toString()));

           lstR.add(m);

        }
        return lstR;
    }
    
     public List<Secciones> listAllTipos() {
        String hql = " SELECT C "
                + " FROM    Secciones C "
                + " ORDER BY C.nombre ASC";

        System.out.println(hql);

        return findList(hql);

    }
     
     
  public Secciones findSubseccion(String secciones) {

        String hql = " SELECT  M FROM Secciones M"
                + " WHERE M.nombre ='" + secciones + "'";
        System.out.println("secciones" + hql);
        return objectFromHQL(hql);

    }    
}
