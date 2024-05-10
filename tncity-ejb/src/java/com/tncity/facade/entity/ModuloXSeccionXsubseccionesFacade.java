/**
 * @name modulos por subsecciones  Facade
 *
 * @ description crud de modulos por subsecciones
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Modulos;
import com.tncity.jpa.pojo.Moduloxseccionxsubseccion;
import com.tncity.jpa.pojo.Secciones;
import com.tncity.jpa.pojo.Subsecciones;

import com.tncity.util.Cadena;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class ModuloXSeccionXsubseccionesFacade extends AbstractFacade<Moduloxseccionxsubseccion> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ModuloXSeccionXsubseccionesFacade() {
        super(Moduloxseccionxsubseccion.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idmodulo", "idseccion", "idsubseccion"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"idmodulo"};
        return attrs;
    }

    @Override
    protected Moduloxseccionxsubseccion parseObject(Object[] o) {
        Moduloxseccionxsubseccion m = new Moduloxseccionxsubseccion((Integer) o[0]);
        m.setIdmodulo(new Modulos((Integer)o[1]));
        m.setIdseccion(new Secciones((Integer) o[2]));
        m.setIdsubseccion(new Subsecciones((Integer) o[3]));
     
        return m;
    }

   
//listar modulos por subsecciones
    
    public List<Moduloxseccionxsubseccion> TodosLasSecciones(String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " Moduloxseccionxsubseccion.idmodseccionsubseccion,"
                + " secciones.idseccion,"
                + " secciones.nombre as nombre_seccion,"
                + " subsecciones.nombrevisual, "
                + " subsecciones.estado, "
                + " modulos.idmodulo,"
                + " modulos.nombre as nombre_modulo,"
                + " subsecciones.idsubseccion,"
                + " subsecciones.nombre as nombre_subseccion"
                + " from moduloxseccionxsubseccion "
                + " join  modulos on moduloxseccionxsubseccion.idmodulo = modulos.idmodulo"
                + " join secciones on moduloxseccionxsubseccion.idseccion = secciones.idseccion"
                + " join subsecciones on moduloxseccionxsubseccion.idsubseccion = subsecciones.idsubseccion"
                + " where modulos.estado = 'true' and secciones.estado = 'true' and subsecciones.estado = 'true' "
                + " ORDER BY subsecciones." + attrOrder + " " + ascDesc;
     

        List<Moduloxseccionxsubseccion> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
         
            Moduloxseccionxsubseccion m= new Moduloxseccionxsubseccion(new Integer(o[0].toString()));
            m.setIdseccion(new Secciones(new Integer(o[1].toString())));
            m.getIdseccion().setNombre(o[2].toString());
            m.getIdseccion().setNombrevisual(o[3].toString());
            m.getIdseccion().setEstado(new Boolean(o[4].toString()));
            m.setIdmodulo(new Modulos(new Integer(o[5].toString())));
            m.getIdmodulo().setNombre(o[6].toString());
            m.setIdsubseccion(new Subsecciones(new Integer(o[7].toString())));
            m.getIdsubseccion().setNombre(o[8].toString());
          
           lstR.add(m);

        }
        return lstR;
    }
    
    //listar todos los modulos por subsecciones
    
     public List<Moduloxseccionxsubseccion> ListaSecciones() {

        String sql = "SELECT "
                + " moduloxseccionxsubseccion.idmodseccionsubseccion,"
                + " moduloxseccionxsubseccion.idmodulo,"
                + " moduloxseccionxsubseccion.idseccion,"
                + " moduloxseccionxsubseccion.idsubseccion"
                + " from moduloxseccionxsubseccion order by idsubseccion ASC";

        List<Moduloxseccionxsubseccion> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            
            Moduloxseccionxsubseccion m= new Moduloxseccionxsubseccion(new Integer(o[0].toString()));
         
            m.setIdmodulo(new Modulos(new Integer(o[1].toString())));
            m.setIdseccion(new Secciones(new Integer(o[2].toString())));
            m.setIdsubseccion(new Subsecciones(new Integer(o[3].toString())));
            
           lstR.add(m);

        }
        return lstR;
    }

     //crear modulos por subssecciones
     
    @Override
    public void create(Moduloxseccionxsubseccion obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //editar modulos por subssecciones

    @Override
    public void edit(Moduloxseccionxsubseccion obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        //borrar modulos por subssecciones

    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //insertar  modulos por subssecciones
    
    public void  insetardato(Modulos m,Secciones se,Subsecciones s, StringBuilder err){
    beginTransaction();
        try {
            SessionImpl sess = getSess();

                    String sql = " Insert into  moduloxseccionxsubseccion ("
                    + "idmodulo,"
                    + "idseccion,"
                    + "idsubseccion"
                    + ") values ("
                    + "'" + m.getIdmodulo() + "',"
                    + "'" + se.getIdseccion() + "',"
                    + "'" + s.getIdsubseccion() + "')";

            sess.createSQLQuery(sql).executeUpdate(); 

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }
    
    
    //editar modulos por subsecciones
    
       public void  editardato(Modulos m,Secciones se,String subseccionid, StringBuilder err){
    beginTransaction();
        try {
            SessionImpl sess = getSess();

                    String sql = " UPDATE moduloxseccionxsubseccion SET "
                    + " idmodulo =   '" + m.getIdmodulo() + "',"
                    + " idseccion =  '" + se.getIdseccion() + "'"
                    + " where idsubseccion = '" + subseccionid + "'";
                    
            sess.createSQLQuery(sql).executeUpdate(); 
      
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }
       
       //listar  modulos por subsecciones
    
    public List<Moduloxseccionxsubseccion> SeccionByIdSeccion(long idsubseccion ,String attrOrder, String ascDesc){
    
      String sql = " SELECT "
                + " Moduloxseccionxsubseccion.idmodseccionsubseccion,"
                + " modulos.idmodulo,"
                + " modulos.nombre as nombre_modulo,"
                + " modulos.imagen as imagen_modulo,"
                + " modulos.url as url_modulo,"
                + " modulos.nombrevisual as nombrevisual_modulo,"
                + " modulos.estado as estado_modulo,"
                + " secciones.idseccion,"
                + " secciones.nombre as nombre_secciones,"
                + " secciones.nombrevisual as nombrevisual_secciones,"
                + " secciones.estado as estado_secciones,"
                + " subsecciones.idsubseccion,"
                + " subsecciones.nombre as nombre_subsecciones,"
                + " subsecciones.imagen as imagen_subsecciones,"
                + " subsecciones.url as url_subsecciones,"
                + " subsecciones.nombrevisual as nombrevisual_subsecciones,"
                + " subsecciones.estado as estado_subsecciones "
                + " from moduloxseccionxsubseccion "
                + " join  modulos on moduloxseccionxsubseccion.idmodulo = modulos.idmodulo"
                + " join secciones on moduloxseccionxsubseccion.idseccion = secciones.idseccion"
                + " join subsecciones on moduloxseccionxsubseccion.idsubseccion = subsecciones.idsubseccion"
                + " where modulos.estado = 'true' and secciones.estado = 'true' and subsecciones.estado = 'true' "
                + " and subsecciones.idsubseccion = '"+ idsubseccion +"'  "
                + " ORDER BY subsecciones." + attrOrder + " " + ascDesc;
      

        List<Moduloxseccionxsubseccion> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Moduloxseccionxsubseccion m= new Moduloxseccionxsubseccion(new Integer(o[0].toString()));
           
            m.setIdmodulo(new Modulos(new Integer(o[1].toString())));
            m.getIdmodulo().setNombre(o[2].toString());
            m.getIdmodulo().setImagen(o[3].toString());
            m.getIdmodulo().setUrl(o[4].toString());
            m.getIdmodulo().setNombrevisual(o[5].toString());
            m.getIdmodulo().setEstado(new Boolean(o[6].toString()));
            
            m.setIdseccion(new Secciones(new Integer(o[7].toString())));
            m.getIdseccion().setNombre(o[8].toString());
            m.getIdseccion().setNombrevisual(o[9].toString());
            m.getIdseccion().setEstado(new Boolean(o[10].toString()));

            m.setIdsubseccion(new Subsecciones(new Integer(o[11].toString())));
            m.getIdsubseccion().setNombre(o[12].toString());
            m.getIdsubseccion().setImagen(o[13].toString());
            m.getIdsubseccion().setUrl(o[14].toString());
            m.getIdsubseccion().setNombrevisual(o[15].toString());
            m.getIdsubseccion().setEstado(new Boolean(o[16].toString()));
          
           lstR.add(m);

        }
        return lstR;
        
    }
}
