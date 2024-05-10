/**
 * @name Modulos Facade
 *
 * @ description crud de Modulos
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Modulos;

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
public class ModulosFacade extends AbstractFacade<Modulos> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ModulosFacade() {
        super(Modulos.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idmodulo", "nombre", "descripcion", "imagen", "url"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Modulos parseObject(Object[] o) {
        Modulos m = new Modulos((Integer) o[0]);
        m.setNombre((String) o[1]);
        m.setImagen((String) o[2]);
        m.setUrl((String) o[3]);
        m.setNombrevisual((String) o[4]);
        m.setEstado((Boolean)o[5]);
        return m;
    }

    //crear modulos
    
    @Override
    public void create(Modulos obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Modulos mo = new Modulos();
             mo.setNombre(obj.getNombre());
             mo.setImagen(obj.getImagen());
             mo.setUrl(obj.getUrl());
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

    
    //editar modulos
    
    @Override
    public void edit(Modulos obj, StringBuilder err) {
        beginTransaction();
        try {
            SessionImpl sess = getSess();
            String nombre = obj.getNombre();

            String nciudad = nombre.toUpperCase();

            String sql = " UPDATE modulos "
                    + "    SET nombre ='" + obj.getNombre() + "',"
                    + "      imagen ='" + obj.getImagen()+ "',"
                    + "      url ='" + obj.getUrl()+ "',"
                    + "      nombrevisual ='" + obj.getNombrevisual()+ "',"
                    + "      estado =" + obj.getEstado()+ " "
                    + " WHERE idmodulo=" + obj.getIdmodulo();
  
            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    //borrar modulos
    
    @Override
    public void delete(Object idmodulo, StringBuilder err) {
        deleteGeneral(idmodulo, err);
    }

    //listar modulos por valor de busqueda
    
    public List<Modulos> listFullTextLight(String query, String attrOrd, String ascDesc, int firstReg, int maxReg) {
        List<Modulos> lstC = new ArrayList<>();
        String sql = " SELECT "
                + " modulos.idmodulo,"
                + " modulos.nombre,"
                + " modulos.imagen,"
                + " modulos.url, "
                + " modulos.nombrevisual, "
                + " modulos.estado "
                + " FROM modulos"
                + " WHERE to_tsvector(modulos.nombre)@@to_tsquery('" + new Cadena().reemplazaEspacios(query, "&").trim() + "') "
                + " ORDER BY modulos." + attrOrd + " ASC";
       
        List<Object[]> lst = findNativeGeneric(sql, firstReg, maxReg);
        for (Object[] o : lst) {
            Modulos c = new Modulos(new Integer(o[0].toString()));
            c.setNombre(o[1].toString());
            c.setImagen(o[2].toString());
            c.setUrl(o[3].toString());
            c.setNombrevisual(o[4].toString());
            c.setEstado(new Boolean(o[5].toString()));

            lstC.add(c);
        }
        return lstC;
    }
    
//buscar modulo por id modulo
    
    public Modulos findIDModulo(String idmodulo) {

        String hql = " SELECT  M FROM Modulos M"
                + " WHERE M.idmodulo='" + idmodulo + "'";

        return objectFromHQL(hql);

    }
    
    //buscar modulo por id modulo
    
      public Modulos findModulo(String modulo) {

        String hql = " SELECT  M FROM Modulos M"
                + " WHERE M.nombre ='" + modulo + "'";
 
        return objectFromHQL(hql);

    }    
      
            //conteo total modulos
      
    public long countTotalModulos() {
        String sql = " SELECT "
                + " count(*)"
                + " FROM modulos  ";

        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    //listar todos los modulos
    
    public List<Modulos> TodosModulos(String attrOrder, String ascDesc) {

        String sql = " SELECT "
                + " modulos.idmodulo,"
                + " modulos.nombre,"
                + " modulos.imagen,"
                + " modulos.url, "
                + " modulos.nombrevisual, "
                + " modulos.estado "
                + " FROM modulos"
                + " ORDER BY modulos." + attrOrder + " " + ascDesc;
   

        List<Modulos> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Modulos m= new Modulos(new Integer(o[0].toString()));
            m.setNombre(o[1].toString());
            m.setImagen(o[2].toString());
            m.setUrl(o[3].toString());
            m.setNombrevisual(o[4].toString());
            m.setEstado(new Boolean(o[5].toString()));

           lstR.add(m);

        }
        return lstR;
    }
    
    //listar todos los modulos
    
      public List<Modulos> listAllTipos() {
        String hql = " SELECT C "
                + " FROM    Modulos C "
                + " ORDER BY C.nombre ASC";


        return findList(hql);

    }
}
