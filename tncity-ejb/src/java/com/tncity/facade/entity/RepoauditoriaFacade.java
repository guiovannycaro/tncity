/**
 * @name Barrio Facade
 *
 * @ description crud de barrios
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Repoauditoria;
import com.tncity.jpa.pojo.Usuario;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import org.hibernate.impl.SessionImpl;

@Stateless
public class RepoauditoriaFacade extends AbstractFacade<Repoauditoria> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public RepoauditoriaFacade() {
        super(Repoauditoria.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idbarrio", "nombre", "idlocalidad.idlocalidad", "idlocalidad.nombre", "idlocalidad.idciudad.idciudad", "idlocalidad.idciudad.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Repoauditoria parseObject(Object[] o) {
        Repoauditoria b = new Repoauditoria((Integer) o[0]);
        b.setNombre((String) o[1]);
        b.setImagen((String) o[2]);
        b.setUrl((String) o[3]);
        b.setNombrevisual((String) o[4]);
        b.setEstado((boolean) o[5]);
        b.setCreatedAt( new Date());
        b.setIdusuario(new Usuario((Integer)o[7]));
       
        return b;
    }
    
//crear los barrios
    
    @Override
    public void create(Repoauditoria obj, StringBuilder err) {
        createGeneral(obj, err);
    }
    
    
    
      public void createreporte(Repoauditoria obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();
            Repoauditoria r = new Repoauditoria();
            r.setNombre(obj.getNombre());
            r.setImagen(obj.getImagen());
            r.setUrl(obj.getUrl());
            r.setNombrevisual(obj.getNombrevisual());
            r.setEstado(obj.getEstado());
            r.setCreatedAt(new Date());
            r.setIdusuario(obj.getIdusuario());
            sess.save(r);
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }

    public void editarreporte(Repoauditoria obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();
            String sql = "UPDATE reportes "
                    + "    SET nombre ='" + obj.getNombre() + "',"
                    + "      imagen ='" + obj.getImagen() + "',"
                    + "      url ='" + obj.getUrl() + "',"
                    + "      nombrevisual ='" + obj.getNombrevisual() + "',"
                    + "      estado =" + obj.getEstado() + ", "
                    + "      created_at =" + new Date() + ", "
                    + "      idusuario =" + obj.getIdusuario() + " "
                    + " WHERE idreporte=" + obj.getIdrepoauditoria();
            System.out.println("actualiza REPORTE" + sql);
            sess.createSQLQuery(sql).executeUpdate();
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }
    
//editar los barrios
    
    @Override
    public void edit(Repoauditoria obj, StringBuilder err) {
        editGeneral(obj, err);
    }
    
//borrar los barrios
    
    @Override
    public void delete(Object idbarrio, StringBuilder err) {
        deleteGeneral(idbarrio, err);
    }
    
//buscar los barrios
    
    public Repoauditoria findByNomAuditoria(String nombre) {
        String hql = " SELECT * FROM Repoauditoria WHERE nombre='" + nombre + "'";
        return objectFromHQL(hql);
    }
    
//listar los barrios
    
    public List<Repoauditoria> listByBarrios(Integer idlocalidad) {
        Integer maxRegLista = 25;

        String hql = " SELECT B"
                + " FROM Repoauditoria B "
                + " WHERE B.idrepoauditoria=" + idlocalidad
                + " ORDER BY B.nombre ASC ";
        return findList(hql);

    }
    
     public long countTotalReportAuditoria() {
        String sql = "SELECT "
                + "count(*)"
                + " FROM repoauditoria ";

        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    public List<Repoauditoria> TodosReportAuditoria(String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + "repoauditoria.idrepoauditoria,"
                + "repoauditoria.nombre,"
                + "repoauditoria.imagen,"
                + "repoauditoria.url, "
                + "repoauditoria.nombrevisual, "
                + "repoauditoria.estado "
                + " FROM repoauditoria"
                + " ORDER BY repoauditoria." + attrOrder + " " + ascDesc;
        System.out.println("todos repoauditoria : " + sql);

        List<Repoauditoria> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Repoauditoria m = new Repoauditoria(new Integer(o[0].toString()));
            m.setNombre(o[1].toString());
            m.setImagen(o[2].toString());
            m.setUrl(o[3].toString());
            m.setNombrevisual(o[4].toString());
            m.setEstado(new Boolean(o[5].toString()));

            lstR.add(m);

        }
        return lstR;
    }
    
}
