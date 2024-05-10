/**
 * @name Promocion Facade
 *
 * @ description crud de promocion
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Promocion;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import org.hibernate.impl.SessionImpl;

@Stateless
public class PromocionFacade extends AbstractFacade<Promocion> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PromocionFacade() {
        super(Promocion.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idpromocion", "nombre", "estado", "observacion"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Promocion parseObject(Object[] o) {
        Promocion b = new Promocion((Integer) o[0]);
        b.setNombre((String) o[1]);
        b.setEstado((Boolean) o[2]);
        b.setObservacion((String) o[3]);
        return b;
    }

   //borrar promocion
   
    @Override
    public void delete(Object idpromocion, StringBuilder err) {
        deleteGeneral(idpromocion, err);
    }

    //buscar promocion por nombre
    
    public Promocion findByNomPromocion(String nombre) {
        String hql = " SELECT * FROM promocion WHERE nombre='" + nombre + "'";
        
        return objectFromHQL(hql);
    }

    // listar promociones
    
    public List<Promocion> listByPromocines(Integer idpromocion) {
        Integer maxRegLista = 25;

        String hql = " SELECT B"
                + " FROM promocion B "
                + " WHERE B.idpromocion=" + idpromocion
                + " ORDER BY B.nombre ASC ";
        
        return findList(hql);

    }

    //conteo de promociones
    
    public long countTotalPromociones() {
        String sql = "SELECT "
                + " count(*)"
                + " FROM promocion  ";

    
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    //listar todas las promociones
    
    public List<Promocion> TodasPromociones(String attrOrder, String ascDesc) {

        String sql = " SELECT "
                + " promocion.idpromocion,"
                + " promocion.nombre,"
                + " promocion.estado,"
                + " promocion.observacion"
                + " FROM promocion  "
                + " ORDER BY promocion." + attrOrder + " " + ascDesc;

        List<Promocion> lstR = new ArrayList<>();
        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {

            Promocion p = new Promocion(new Integer(o[0].toString()));
            p.setNombre(o[1].toString());
            p.setEstado(new Boolean(o[2].toString()));
            p.setObservacion(o[3].toString());
            lstR.add(p);

        }
        return lstR;
    }
    
//conteo de promociones
    
    public long countBuscar(String valbusq) {
        String sql = " SELECT COUNT(promocion.idpromocion)"
                + "  FROM promocion "
                + "	WHERE to_tsvector(promocion.nombre"
                + " )@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n";
        
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    // buscar promociones por filtro
    
    public List<Promocion> buscarFullText(String valBusq, int first, int maxRegLista) {

        String sql = " SELECT "
                + " promocion.idpromocion,"
                + " promocion.nombre,"
                + " promocion.estado,"
                + " promocion.observacion"
                + " FROM promocion  "
                +  " WHERE to_tsvector(promocion.nombre \n"
                + " )@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') "
                + " ORDER BY promociones.idpromocion DESC";

        List<Promocion> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            Promocion p = new Promocion(new Integer(o[0].toString()));
            p.setNombre(o[1].toString());
            p.setEstado(new Boolean(o[2].toString()));
            p.setObservacion(o[3].toString());
            lstR.add(p);
        }

        return lstR;
    }
    
    //crear promociones
    
     @Override
    public void create(Promocion obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();
       
            Promocion c = new Promocion();
            c.setNombre(obj.getNombre());
            c.setEstado(obj.getEstado());
            c.setObservacion(obj.getObservacion());  
            sess.save(c);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    //editar promociones
    
    @Override
    public void edit(Promocion obj, StringBuilder err) {
        beginTransaction();
        try {
            SessionImpl sess = getSess();
           
            String sql = "UPDATE promocion "
                    + "    SET nombre ='" + obj.getNombre()+ "',"
                    + "        estado ='" + obj.getEstado()+ "',"
                    + "        observacion ='" + obj.getObservacion()+ "'"
                    + " WHERE idpromocion =" + obj.getIdpromocion();
        
           sess.createSQLQuery(sql).executeUpdate();
           
           String sqld = " UPDATE promociones "
                    + "    SET estado ='" + obj.getEstado()+ "'"
                    + " WHERE idpromocion = " + obj.getIdpromocion();
           
           sess.createSQLQuery(sqld).executeUpdate();
                  
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }
    
    //buscar promociones por id
    
     public Promocion buscarpromocionbyid(String idpromocion) {
        String hql = " SELECT p FROM Promocion p WHERE p.idpromocion ='" + idpromocion + "'";
        
        return objectFromHQL(hql);
    }
     
     //verifiar promociones activas
     
      public Promocion VerificarPromocionActiva() {
        String hql = " SELECT p FROM Promocion p WHERE p.estado = 'true'";

        return objectFromHQL(hql);
    }
     
}
