/**
 * @name Subsecciones Facade
 *
 * @ description crud de subsecciones
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
import com.tncity.jpa.pojo.Secciones;
import com.tncity.jpa.pojo.Subsecciones;

import com.tncity.util.Cadena;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class SubseccionesFacade extends AbstractFacade<Subsecciones> {

    @PersistenceUnit
    private EntityManagerFactory emf;
    
    
    @EJB
    ModuloXSeccionXsubseccionesFacade modxsuecxsubsecFacade;
       
    @EJB
    ModulosFacade modulosFacade;
    
  @EJB
    SubmodulosFacade submodulosFacade;
  
  
    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public SubseccionesFacade() {
        super(Subsecciones.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idseccion", "nombre", "descripcion", "imagen", "url"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Subsecciones parseObject(Object[] o) {
        Subsecciones m = new Subsecciones((Integer) o[0]);
        m.setNombre((String) o[1]);
        m.setImagen((String) o[2]);
        m.setUrl((String) o[3]);
        m.setNombrevisual((String) o[4]);
        m.setEstado((Boolean)o[5]);
        return m;
    }

//creacion de subsecciones
    
    public void created(String nombre ,String  imagen ,String url ,String  nombrevisual ,String  estado ,
           String  modulo , String seccion,  StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();

              String sql = " Insert into  subsecciones ("
                    + "nombre,"
                    + "imagen,"
                    + "url,"
                    + "nombrevisual,"
                    + "estado"
                    + ") values ("
                    + "'" + nombre + "',"
                    + "'" + imagen + "',"
                    + "'" + url + "',"
                    + "'" + nombrevisual + "', "
                       + "'" + estado + "' "
                      + ")";

            System.out.println("insert  submodulos: " + sql);
            sess.createSQLQuery(sql).executeUpdate();
            
            
            
            Subsecciones s = findBySubseccion(nombre ,imagen , url ,nombrevisual);
            
            Modulos mod = modulosFacade.findModulo(modulo);
                    
            Secciones sec =  submodulosFacade.findSubseccion(seccion);  
            
            modxsuecxsubsecFacade.insetardato(mod,sec,s,err);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }
    
//buscar sub secciones  por nombre,imagen,url,nombrevisual
    
  public  Subsecciones findBySubseccion(String nombre ,String imagen , String url ,String nombrevisual){
          String hql = " SELECT D"
                + " FROM Subsecciones D "
                + " WHERE D.nombre='" + nombre + "'"
                   + " and D.imagen='" + imagen + "'"
                   + " and D.url='" + url + "'"
                   + " and D.nombrevisual='" + nombrevisual + "'"
                  
                + " ORDER BY D.idsubseccion ASC ";
        System.out.println("consultar" + hql);
        return objectFromHQL(hql);
  
  }
            
            //editar sub secciones

    public void editdos(String nombre,String nomvisual,String imagen,String link,String estado,String subseccionid,String modulos,String seccion , StringBuilder err) {
        beginTransaction();
        try {
            SessionImpl sess = getSess();
       
            String sql = "UPDATE subsecciones "
                    + " SET nombre ='" + nombre + "',"
                    + "      imagen ='" + imagen+ "',"
                    + "      url ='" + link+ "',"
                    + "      nombrevisual ='" + nomvisual+ "',"
                    + "      estado =" + estado+ " "
                    + " WHERE idsubseccion  =" + subseccionid;
            System.out.println("actualiza subseccion" + sql);
            sess.createSQLQuery(sql).executeUpdate();
            
            
            if(!modulos.equals("") || !seccion.equals("")){
               Modulos mod = modulosFacade.findModulo(modulos);
                    
            Secciones sec =  submodulosFacade.findSubseccion(seccion);  
            
            modxsuecxsubsecFacade.editardato(mod,sec,subseccionid,err);
            }
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    //borrar subsecciones
    
    @Override
    public void delete(Object idmodulo, StringBuilder err) {
        deleteGeneral(idmodulo, err);
    }

    //listar subsecciones
    
    public List<Subsecciones> listFullTextLight(String query, String attrOrd, String ascDesc, int firstReg, int maxReg) {
        List<Subsecciones> lstC = new ArrayList<>();
        String sql = " SELECT "
                 + " subsecciones.idseccion,"
                 + " subsecciones.nombre,"
                 + " subsecciones.imagen,"
                 + " subsecciones.url,"
                 + " subsecciones.nombrevisual, "
                 + " subsecciones.estado "
                 + " FROM subsecciones"
                 + " WHERE to_tsvector(subsecciones.nombre)@@to_tsquery('" + new Cadena().reemplazaEspacios(query, "&").trim() + "') "
                 + " ORDER BY subsecciones." + attrOrd + " ASC";
        System.out.println("SQL:" + sql);
        List<Object[]> lst = findNativeGeneric(sql, firstReg, maxReg);
        for (Object[] o : lst) {
            Subsecciones c = new Subsecciones(new Integer(o[0].toString()));
            c.setNombre(o[1].toString());
            c.setImagen(o[2].toString());
            c.setUrl(o[3].toString());
            c.setNombrevisual(o[4].toString());
            c.setEstado(new Boolean(o[5].toString()));

            lstC.add(c);
        }
        return lstC;
    }

    //buscar id modulo
    
    public Subsecciones findIDModulo(String idmodulo) {

        String hql = "SELECT  M FROM subsecciones M"
                + " WHERE M.idsubseccion='" + idmodulo + "'";
        System.out.println("id sub seccion " + hql);
        return objectFromHQL(hql);

    }

    //conteo de subsecciones
    
    public long countTotalSubsecciones() {
        String sql = "SELECT "
                + " count(*)"
                + " FROM subsecciones ";

        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    // listar todas las subsecciones
    
    public List<Subsecciones> TodosLasSecciones(String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " subsecciones.idseccion,"
                + " subsecciones.nombre,"
                + " subsecciones.imagen,"
                + " subsecciones.url,"
                + " subsecciones.nombrevisual, "
                + " subsecciones.estado, "
                + " modulos.idmodulo,"
                + " modulos.nombre,"
                + " subsecciones.idsubseccion,"
                + " subsecciones.nombre "
                + " from moduloxseccionxsubseccion "
                + " join  modulos on moduloxseccionxsubseccion.idmodulo = modulos.idmodulo"
                + " join secciones on moduloxseccionxsubseccion.idseccion = secciones.idseccion"
                + " join subsecciones on moduloxseccionxsubseccion.idsubseccion = subsecciones.idsubseccion"
                + " where modulos.estado = 'true' and secciones.estado = 'true' and subsecciones.estado = 'true' "
                + " ORDER BY subsecciones." + attrOrder + " " + ascDesc;
        System.out.println("todos secciones : " + sql);

        List<Subsecciones> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Subsecciones m= new Subsecciones(new Integer(o[0].toString()));
            m.setNombre(o[1].toString());
            m.setImagen(o[2].toString());
            m.setUrl(o[3].toString());
            m.setNombrevisual(o[4].toString());
            m.setEstado(new Boolean(o[5].toString()));


           lstR.add(m);

        }
        return lstR;
    }

    //crear subsecciones
    
    @Override
    public void create(Subsecciones obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //editar subsecciones
    
    @Override
    public void edit(Subsecciones obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
