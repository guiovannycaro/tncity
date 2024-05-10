/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.estadisticas.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.util.Cadena;
import com.tncity.jpa.pojoaux.Auditorias;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class EstadisticasFacade extends AbstractFacade<Auditorias> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EstadisticasFacade() {
        super(Auditorias.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {
            "NombreTabla", "Operacion", "ValorAnterior", "NuevoValor", "UpdateDate", "USUARIO", "CODIGO_AU"

        };
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"NombreTabla"};
        return attrs;
    }

    @Override
    protected Auditorias parseObject(Object[] o) {
        Auditorias a = new Auditorias();
        a.setNombreTabla(o[1].toString());
        a.setOperacion((String) o[4]);
        a.setValorAnterior(o[2].toString());
        a.setNuevoValor((String) o[3]);
        a.setUpdateDate((String) o[6]);
        a.setUSUARIO((String) o[5]);
        a.setCODIGO_AU((Integer) o[0]);

        return a;
    }

    @Override
    public void create(Auditorias obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();
            Auditorias a = new Auditorias();

            a.setNombreTabla(obj.getNombreTabla());
            a.setOperacion(obj.getOperacion());
            a.setValorAnterior(obj.getValorAnterior());
            a.setNuevoValor(obj.getNuevoValor());
            a.setUpdateDate(obj.getUpdateDate());
            a.setUSUARIO(obj.getUSUARIO());

            sess.save(a);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    @Override
    public void edit(Auditorias obj, StringBuilder err) {
        beginTransaction();
        try {
            SessionImpl sess = getSess();
            String sql = "UPDATE auditar.auditorias  "
                    + "SET NombreTabla ='" + obj.getNombreTabla() + "', "
                    + "Operacion=" + obj.getOperacion() + ", "
                    + "ValorAnterior=" + obj.getValorAnterior() + ", "
                    + "NuevoValor=" + obj.getNuevoValor() + ",   "
                    + " UpdateDate=" + obj.getUpdateDate() + ","
                    + "USUARIO = " + obj.getUSUARIO() + "  "
                    + "WHERE CODIGO_AU=" + obj.getCODIGO_AU() + "";
            System.out.println("actualiza auditoria" + sql);
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
    public void delete(Object idciudad, StringBuilder err) {
        deleteGeneral(idciudad, err);
    }

    public List<Auditorias> listFullTextLight(String query, String attrOrd, String ascDesc, int firstReg, int maxReg) {
        List<Auditorias> lstC = new ArrayList<>();
        String sql = "SELECT "
       + "auditoria.NombreTabla," 
       + "auditoria.Operacion," 
       + "auditoria.ValorAnterior," 
       + "auditoria.NuevoValor,"
       + "auditoria.UpdateDate," 
       + "auditoria.USUARIO," 
       + "auditoria.CODIGO_AU" 
                
                + " FROM auditar.auditorias "
           
                + " WHERE to_tsvector(auditorias.NombreTabla)@@to_tsquery('" + new Cadena().reemplazaEspacios(query, "&").trim() + "') "
                + " ORDER BY auditorias." + attrOrd + " ASC";
        System.out.println("SQL:" + sql);
        List<Object[]> lst = findNativeGeneric(sql, firstReg, maxReg);
        for (Object[] o : lst) {
            
            
           Auditorias a = new Auditorias(new Integer(o[0].toString()));
            a.setNombreTabla(o[0].toString());
            a.setOperacion(o[0].toString());
            a.setValorAnterior(o[0].toString());
            a.setNuevoValor(o[0].toString());
            a.setUpdateDate(o[0].toString());
            a.setUSUARIO(o[0].toString());
            lstC.add(a);
        }
        return lstC;
    }

}
