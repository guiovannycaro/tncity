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
import com.tncity.jpa.pojo.Barrio;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Localidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Stateless
public class BarrioFacade extends AbstractFacade<Barrio> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BarrioFacade() {
        super(Barrio.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idbarrio", "nombre", "idlocalidad.idlocalidad", "idlocalidad.nombre", 
            "idlocalidad.idciudad.idciudad", "idlocalidad.idciudad.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Barrio parseObject(Object[] o) {
        Barrio b = new Barrio((Integer) o[0]);
        b.setNombre((String) o[1]);
        b.setIdlocalidad(new Localidad((Integer) o[2]));
        b.getIdlocalidad().setNombre((String) o[3]);
        b.getIdlocalidad().setIdciudad(new Ciudad((Integer) o[4]));
        b.getIdlocalidad().getIdciudad().setNombre((String) o[5]);
        return b;
    }
    
//crear los barrios
    
    @Override
    public void create(Barrio obj, StringBuilder err) {
        createGeneral(obj, err);
    }
    
//editar los barrios
    
    @Override
    public void edit(Barrio obj, StringBuilder err) {
        editGeneral(obj, err);
    }
    
//borrar los barrios
    
    @Override
    public void delete(Object idbarrio, StringBuilder err) {
        deleteGeneral(idbarrio, err);
    }
    
//buscar los barrios
    
    public Barrio findByNomBarrio(String nombre) {
        String hql = " SELECT * FROM barrio WHERE nombre='" + nombre + "'";
        return objectFromHQL(hql);
    }
    
//listar los barrios
    
    public List<Barrio> listByBarrios(Integer idlocalidad) {
        Integer maxRegLista = 25;

        String hql = " SELECT B"
                + " FROM Barrio B "
                + " WHERE B.idlocalidad=" + idlocalidad
                + " ORDER BY B.nombre ASC ";
        return findList(hql);

    }
}
