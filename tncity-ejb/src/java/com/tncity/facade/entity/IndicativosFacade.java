/**
 * @name Indicativos Facade
 *
 * @ description crud de indicativos
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Departamentoestado;
import com.tncity.jpa.pojo.Indicativospaises;
import com.tncity.jpa.pojo.Pais;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class IndicativosFacade extends AbstractFacade<Indicativospaises> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public IndicativosFacade() {
        super(Indicativospaises.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idindicativo", "iddepartamento", "idpais", "idciudad", "codindicativo"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"codindicativo"};
        return attrs;
    }

    @Override
    protected Indicativospaises parseObject(Object[] o) {
        
        Indicativospaises l = new Indicativospaises((Integer) o[0]);
        
        l.setIdciudad(new Ciudad(new Integer(o[1].toString())));
        l.setIddepartamento(new Departamentoestado((Integer) o[2]));
        l.setIdpais(new Pais((Integer) o[3]));
        l.setCodindicativo((String) o[4]);
        
        return l;
    }

    
    //crear indicativos
    
    @Override
    public void create(Indicativospaises obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    //editar indicativos 
    
    @Override
    public void edit(Indicativospaises obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    //borrar indicativos
    
    @Override
    public void delete(Object idindicativo, StringBuilder err) {
        deleteGeneral(idindicativo, err);
    }
    
    //conteo total indicativos

    public long countTotalIndicativos() {
        String sql = "SELECT "
                + " count(*)"
                + " FROM indicativospaises  ";

        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    //conteo indicativos
     
    public long countBuscar(String valbusq) {
        String sql = " SELECT COUNT(indicativospaises.idindicativo)"
                + " FROM indicativospaises\n"
                + " join pais on indicativospaises.idpais = pais.idpais \n"
                + " join ciudad on indicativospaises.idciudad = ciudad.idciudad\n"
                + " join departamentoestado on indicativospaises.iddepartamento = departamentoestado.iddepartamento\n"
                + " WHERE to_tsvector(pais.nombre||' '||ciudad.nombre||' '||departamentoestado.nombre ||' '|| indicativospaises.codindicativo\n"
                + " )@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n";
        
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }
    
    //listar todos los indicativos

    public List<Indicativospaises> TodosIndicativos(String attrOrder, String ascDesc) {

        String sql = " SELECT "
                + " indicativospaises.idindicativo,"
                + " indicativospaises.idpais,"
                + " pais.nombre as nombre_pais,"
                + " indicativospaises.iddepartamento,"
                + " departamentoestado.nombre as nombre_departamento,"
                + " indicativospaises.idciudad,"
                + " ciudad.nombre as nombre_ciudad,"
                + " indicativospaises.codindicativo,"
                + " indicativospaises.codarea"
                + " FROM indicativospaises  "
                + " join pais on indicativospaises.idpais = pais.idpais \n"
                + " join ciudad on indicativospaises.idciudad = ciudad.idciudad\n"
                + " join departamentoestado on indicativospaises.iddepartamento = departamentoestado.iddepartamento\n"
                + " ORDER BY indicativospaises." + attrOrder + " " + ascDesc;
        

        List<Indicativospaises> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Indicativospaises i = new Indicativospaises(new Integer(o[0].toString()));

            i.setIdpais(new Pais(new Integer(o[1].toString())));
            i.getIdpais().setNombre(o[2].toString());
            i.setIddepartamento(new Departamentoestado(new Integer(o[3].toString())));
            i.getIddepartamento().setNombre(o[4].toString());
            i.setIdciudad(new Ciudad(new Integer(o[5].toString())));
            i.getIdciudad().setNombre(o[6].toString());
            i.setCodindicativo(o[7].toString());
            i.setCodarea(new Integer(o[8].toString()));

            lstR.add(i);

        }
        return lstR;
    }

    //crear nuevo indicativo
    
    public void nuevoindi(Indicativospaises obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();
            Indicativospaises p = new Indicativospaises();

            p.setIdpais(obj.getIdpais());
            p.setIdciudad(obj.getIdciudad());
            p.setIddepartamento(obj.getIddepartamento());
            p.setCodindicativo(obj.getCodindicativo());
            p.setCodarea(obj.getCodarea());
            sess.save(p);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }

    //editar indicativo
    
    public void editarindi(Indicativospaises obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE indicativospaises "
                    + "    SET idpais ='" + obj.getIdpais().getIdpais() + "',"
                    + "        idciudad ='" + obj.getIdciudad().getIdciudad() + "',"
                    + "        iddepartamento ='" + obj.getIddepartamento().getIddepartamento() + "',"
                    + "        codindicativo = '" + obj.getCodindicativo() + "' ,"
                    + "        codarea = '" + obj.getCodarea() + "' "
                    + " WHERE idindicativo =" + obj.getIdindicativo();

          
            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }

    //buscar indicativo por valor busqueda
    
    public List<Indicativospaises> buscarFullText(String valBusq, int first, int maxRegLista) {

        String sql = "SELECT "
                + " indicativospaises.idindicativo,"
                + " indicativospaises.idpais,"
                + " pais.nombre as nombre_pais,"
                + " indicativospaises.iddepartamento,"
                + " departamentoestado.nombre as nombre_departamento,"
                + " indicativospaises.idciudad,"
                + " ciudad.nombre as nombre_ciudad,"
                + " indicativospaises.codindicativo,"
                + " indicativospaises.codarea"
                + " FROM indicativospaises  "
                + " join pais on indicativospaises.idpais = pais.idpais \n"
                + " join ciudad on indicativospaises.idciudad = ciudad.idciudad\n"
                + " join departamentoestado on indicativospaises.iddepartamento = departamentoestado.iddepartamento\n"
                + " WHERE to_tsvector(pais.nombre||' '||ciudad.nombre||' '||departamentoestado.nombre ||' '|| indicativospaises.codindicativo\n"
                + ")@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') \n"
                + "ORDER BY indicativospaises.idindicativo DESC";

        List<Indicativospaises> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            Indicativospaises i = new Indicativospaises(new Integer(o[0].toString()));

            i.setIdpais(new Pais(new Integer(o[1].toString())));
            i.getIdpais().setNombre(o[2].toString());
            i.setIddepartamento(new Departamentoestado(new Integer(o[3].toString())));
            i.getIddepartamento().setNombre(o[4].toString());
            i.setIdciudad(new Ciudad(new Integer(o[5].toString())));
            i.getIdciudad().setNombre(o[6].toString());
            i.setCodindicativo(o[7].toString());
            i.setCodarea(new Integer(o[8].toString()));
            lstR.add(i);

        }

        return lstR;
    }
    
    //buscar indicativo por departamento
    
   public Indicativospaises Indicativociudad(String ciudad){
       
       String hql = " SELECT I FROM Indicativospaises I WHERE I.iddepartamento='" + ciudad + "'";
       
        return objectFromHQL(hql);
    }
}
