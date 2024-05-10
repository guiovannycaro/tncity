/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.informex.entity;

/**
 *
 * @author guiovanny
 */
import com.tncity.facade.general.AbstractFacade;
import com.tncity.informex.pojos.VistaTnpagos;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.tncity.jpa.pojo.Reportes;
import com.tncity.properties.Propiedad;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.hibernate.impl.SessionImpl;

@Stateless
public class ReportesFacade extends AbstractFacade<Reportes> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ReportesFacade() {
        super(Reportes.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idreporte", "nombre", "imagen", "url", "nombrevisual", "estado", "created_at", "idusuario"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Reportes parseObject(Object[] o) {
        Reportes c = new Reportes();
        // c.setIdciudad((Integer) o[0]);

        return c;
    }

    @Override
    public void create(Reportes obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void createreporte(Reportes obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();
            Reportes r = new Reportes();
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

    public void editarreporte(Reportes obj, StringBuilder err) {

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
                    + " WHERE idreporte=" + obj.getIdreporte();
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

    @Override
    public void edit(Reportes obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long countTotalReportes() {
        String sql = "SELECT "
                + "count(*)"
                + " FROM reportes ";

        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    public List<Reportes> TodosReportes(String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + "reportes.idreporte,"
                + "reportes.nombre,"
                + "reportes.imagen,"
                + "reportes.url, "
                + "reportes.nombrevisual, "
                + "reportes.estado "
                + " FROM reportes"
                + " ORDER BY reportes." + attrOrder + " " + ascDesc;
        System.out.println("todos modulos : " + sql);

        List<Reportes> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Reportes m = new Reportes(new Integer(o[0].toString()));
            m.setNombre(o[1].toString());
            m.setImagen(o[2].toString());
            m.setUrl(o[3].toString());
            m.setNombrevisual(o[4].toString());
            m.setEstado(new Boolean(o[5].toString()));

            lstR.add(m);

        }
        return lstR;
    }
    
      public List<Reportes> TodosReportesById(String idreporte,String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " reportes.idreporte,"
                + " reportes.nombre,"
                + " reportes.imagen,"
                + " reportes.url, "
                + " reportes.nombrevisual, "
                + " reportes.estado "
                + " FROM reportes"
                + " WHERE idreporte = '"+idreporte+"'"   
                + " ORDER BY reportes." + attrOrder + " " + ascDesc;
        System.out.println("todos modulos : " + sql);

        List<Reportes> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Reportes m = new Reportes(new Integer(o[0].toString()));
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
