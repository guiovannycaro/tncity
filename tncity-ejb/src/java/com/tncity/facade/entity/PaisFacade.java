/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Pais;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class PaisFacade extends AbstractFacade<Pais> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PaisFacade() {
        super(Pais.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idpais", "nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre", "iso2", "iso3"};
        return attrs;
    }

    @Override
    protected Pais parseObject(Object[] o) {
        Pais p = new Pais((Integer) o[0]);
        p.setNombre((String) o[1]);
        return p;
    }

    @Override
    public void create(Pais obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Pais obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idpais, StringBuilder err) {
        deleteGeneral(idpais, err);
    }

    public Pais findByNomPais(String nombre) {
        String hql = " SELECT * FROM Pais "
                + " WHERE nombre='" + nombre + "'";
        
        return objectFromHQL(hql);
    }

    public long countBuscar(String valbusq) {
        String sql = " SELECT count(*)\n"
                + " FROM pais b\n"
                + " WHERE to_tsvector(nombre)@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n";
  
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    public List<Pais> buscarFullText(String valBusq, int first, int maxRegLista) {
        String sql = " SELECT *\n"
                + "  FROM pais b\n"
                + "	WHERE to_tsvector(nombre)@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') \n"
                + " ORDER BY idpais ASC ";
        
        return findNative(sql, first, maxRegLista, Pais.class);
    }

    public Pais findPaisID(Pais p) {
        String sql = " SELECT * FROM Pais WHERE idpais='" + p.getIdpais() + "'";
       
        return objectFromSQL(sql);
    }

}
