/**
 * @name Localidad Facade
 *
 * @ description crud de localidad
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
import com.tncity.jpa.pojo.Localidad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class LocalidadFacade extends AbstractFacade<Localidad> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public LocalidadFacade() {
        super(Localidad.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idlocalidad", "nombre", "idciudad.idciudad", "idciudad.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Localidad parseObject(Object[] o) {
        Localidad l = new Localidad((Integer) o[0]);
        l.setNombre((String) o[1]);
        l.setIdciudad(new Ciudad((Integer) o[2]));
        l.getIdciudad().setNombre((String) o[3]);
        return l;
    }

    //crear localidad
    
    @Override
    public void create(Localidad obj, StringBuilder err) {
        createGeneral(obj, err);
    }
    
//editar localidad
    
    @Override
    public void edit(Localidad obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    //borrar localidad
    
    @Override
    public void delete(Object idlocalidad, StringBuilder err) {
        deleteGeneral(idlocalidad, err);
    }

    //buscar nombre localidad
    
    public Localidad findByNomLocalidad(String nombre) {
        String hql = " SELECT * FROM localidad WHERE nombre='" + nombre + "'";
        
        return objectFromHQL(hql);
    }

    //buscar ciudad por id localidad
    
    public Localidad findByByIdCiudad(Integer idciudad) {
        String hql = " SELECT * FROM localidad WHERE idciudad='" + idciudad + "'";
        
        return objectFromHQL(hql);
    }

    // listar localidades por ciudad
    
    public List<Localidad> listByCiudad(Integer idciudad) {
        String hql = " SELECT l"
                + " FROM Localidad l "
                + " WHERE l.idciudad.idciudad=" + idciudad
                + " ORDER BY l.nombre ASC ";
        
        return findList(hql);
    }

}
