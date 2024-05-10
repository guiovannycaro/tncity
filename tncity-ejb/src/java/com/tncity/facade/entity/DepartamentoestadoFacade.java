/**
 * @name Departamento estado  Facade
 *
 * @ description crud de Departamento estado
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Departamentoestado;
import com.tncity.jpa.pojo.Pais;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class DepartamentoestadoFacade extends AbstractFacade<Departamentoestado> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DepartamentoestadoFacade() {
        super(Departamentoestado.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"iddepartamento", "nombre", "idpais.idpais", "idpais.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Departamentoestado parseObject(Object[] o) {
        Departamentoestado d = new Departamentoestado((Integer) o[0]);
        d.setNombre((String) o[1]);
        d.setIdpais(new Pais((Integer) o[2]));
        d.getIdpais().setNombre((String) o[3]);
        return d;
    }

    //crear departamento estado
    
    @Override
    public void create(Departamentoestado obj, StringBuilder err) {
        createGeneral(obj, err);
    }
    
    //eliminar departamento estado

    @Override
    public void edit(Departamentoestado obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    //borrar departamento estado
    
    @Override
    public void delete(Object iddepartamento, StringBuilder err) {
        deleteGeneral(iddepartamento, err);
    }
//buscar departamento estado por nombre
    
    public Departamentoestado findByNomDepartamento(String nombre) {
        String hql = " SELECT * FROM departamentoestado WHERE nombre='" + nombre + "'";
        
        return objectFromHQL(hql);
    }
    
    // listar departamento estado por ciudad

    public List<Departamentoestado> listByCiudad(Integer iddepartamento) {
        Integer maxRegLista = 25;

        String hql = " SELECT C"
                + " FROM Ciudad C "
                + " WHERE C.iddepartamento=" + iddepartamento
                + " ORDER BY C.nombre ASC ";
        
        return findList(hql);

    }

    // listar departamento estado
    
    public List<Departamentoestado> listByDepatamento(Integer idpais) {
        String hql = " SELECT D"
                + " FROM Departamentoestado D "
                + " WHERE D.idpais=" + idpais
                + " ORDER BY D.nombre ASC ";

       
        return findList(hql);
    }
    
    //contar departamentos estados existentes
    

    public long countBuscar(String valbusq) {
        String sql = " SELECT count(*)\n"
                + "  FROM departamentoestado b\n"
                + "	WHERE to_tsvector(nombre)@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n";
        
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }
    
//buscar departamento estado por valor de busqueda
    
    public List<Departamentoestado> buscarFullText(String valBusq, int first, int maxRegLista) {
        String sql = " SELECT *\n"
                + "  FROM departamentoestado b\n"
                + "	WHERE to_tsvector(nombre)@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') \n"
                + " ORDER BY iddepartamento ASC ";
        return findNative(sql, first, maxRegLista, Departamentoestado.class);
    }

    //buscar departamento estado por id
    
    public Departamentoestado findDepartamentoID(Departamentoestado d) {
        String sql = " SELECT * FROM departamentoestado  WHERE iddepartamento='" + d.getIddepartamento() + "'";
        
        return objectFromSQL(sql);
    }
}
