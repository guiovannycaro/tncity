/**
 * @name Moneda control
 *
 * @ description crud de moneda
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Moneda;
import com.tncity.jpa.pojo.Pais;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class MonedaFacade extends AbstractFacade<Moneda> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MonedaFacade() {
        super(Moneda.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idmoneda", "idpais", "codeiso4217", "numiso4217", "simbolo", "valortrm"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"idmoneda", "idpais", "codeiso4217", "numiso4217", "simbolo", "valortrm"};
        return attrs;
    }

    @Override
    protected Moneda parseObject(Object[] o) {
        Moneda p = new Moneda((Integer) o[0]);

        p.setCodeiso4217(o[2].toString());
        p.setNumiso4217(o[3].toString());
        p.setSimbolo(o[4].toString());
        p.setValortrm(o[5].toString());
        return p;
    }

    
    //crear moneda
    
    @Override
    public void create(Moneda obj, StringBuilder err) {
        createGeneral(obj, err);
    }
    
//editar moneda
    
    @Override
    public void edit(Moneda obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    //borrar moneda
    
    @Override
    public void delete(Object idpais, StringBuilder err) {
        deleteGeneral(idpais, err);
    }
    
    //busacr moneda por pais
    

    public Moneda findByNomPais(String nombre) {
        String hql = " SELECT M FROM Moneda M WHERE M.idpais.idpais='" + nombre + "'";
        
        return objectFromHQL(hql);
    }

    //buscar
    public long countBuscar(String valbusq) {
        String sql = " SELECT count(*)\n"
                + "  FROM moneda b\n"
                + "  WHERE b.idpais IN(  \n"
                + "	SELECT p.idpais \n"
                + "	FROM pais p  \n"
                + "	WHERE to_tsvector(p.nombre||' '||b.codeiso4217||' '||b.numiso4217)@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n"
                + "	ORDER BY p.idpais DESC)";
        
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    public List<Moneda> buscarFullText(String valBusq, int first, int maxRegLista) {
        String sql = " SELECT *\n"
                + " FROM moneda b\n"
                + "  WHERE b.idpais IN(  \n"
                + "	SELECT p.idpais \n"
                + "	FROM pais p  \n"
                + "	WHERE to_tsvector(p.nombre||' '||b.codeiso4217||' '||b.numiso4217)@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') \n"
                + "	ORDER BY p.idpais DESC)"
                + " ORDER BY idmoneda ASC ";
        
        return findNative(sql, first, maxRegLista, Moneda.class);
    }

    public Moneda findMonedaID(Integer p) {
        String sql = " SELECT * FROM moneda WHERE idpais = '" + p + "'";
     
        return objectFromSQL(sql);
    }

    public List<Moneda> listByMoneda() {
        Integer maxRegLista = 25;

        String hql = " SELECT B"
                + " FROM Moneda B "
                + " ORDER BY B.idmoneda ASC ";
        
        return findList(hql);

    }
    
        public long countTotalMonedas() {
        String sql = "SELECT COUNT(moneda.idmoneda)"
                + " FROM moneda\n";
        
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }


    public List<Moneda> TodosMonedas(String attrOrder, String ascDesc) {

        String sql = " SELECT "
                + " moneda.idmoneda,"
                + " moneda.idpais,"
                + " moneda.codeiso4217,"
                + " moneda.numiso4217,"
                + " moneda.simbolo,"
                + " moneda.valortrm"
                + " FROM moneda  "
                + " ORDER BY moneda." + attrOrder + " " + ascDesc;
      

        List<Moneda> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Moneda i = new Moneda(new Integer(o[0].toString()));

            i.setIdpais(new Pais(new Integer(o[1].toString())));
            i.setCodeiso4217(o[2].toString());
            i.setNumiso4217(o[3].toString());
            i.setSimbolo(o[4].toString());
            i.setValortrm(o[5].toString());
            lstR.add(i);

        }
        return lstR;
    }
}
