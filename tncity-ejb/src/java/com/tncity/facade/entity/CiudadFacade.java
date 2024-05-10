/**
 * @name Ciudad Facade
 *
 * @ description crud de ciudad
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
import com.tncity.util.Cadena;
import com.tncity.jpa.pojo.Departamentoestado;
import com.tncity.jpa.pojo.Localidad;
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
public class CiudadFacade extends AbstractFacade<Ciudad> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idciudad", "nombre", "iddepartamento.iddepartamento", "iddepartamento.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Ciudad parseObject(Object[] o) {
        
        Ciudad c = new Ciudad();
        
        c.setIdciudad((Integer) o[0]);
        c.setNombre((String) o[1]);
        c.setIddepartamento(new Departamentoestado((Integer) o[2]));
        c.getIddepartamento().setNombre((String) o[3]);
        
        return c;
    }

    
    //crear ciudad
    
    @Override
    public void create(Ciudad obj, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();
            
            String nombre = obj.getNombre();
            String nciudad = nombre.toUpperCase();
            
            Ciudad c = new Ciudad();
            
            c.setNombre(nciudad);
            c.setCodoficial(obj.getCodoficial());
            c.setIddepartamento(obj.getIddepartamento());
            c.setLongitud(obj.getLongitud());
            c.setLatitud(obj.getLatitud());

            sess.save(c);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    
    //editar beneficiario
    
    @Override
    public void edit(Ciudad obj, StringBuilder err) {
        beginTransaction();
        try {
            SessionImpl sess = getSess();
            String nombre = obj.getNombre();

            String nciudad = nombre.toUpperCase();

            String sql = " UPDATE ciudad "
                    + "    SET nombre='" + nciudad + "',"
                    + "        codoficial=" + obj.getCodoficial() + ","
                    + "        latitud=" + obj.getLatitud() + ","
                    + "        longitud=" + obj.getLongitud() + ","
                    + "        iddepartamento=" + obj.getIddepartamento().getIddepartamento() + ""
                    + " WHERE idciudad=" + obj.getIdciudad();
        
           sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    
    //borrar beneficiario
    
    @Override
    public void delete(Object idciudad, StringBuilder err) {
        deleteGeneral(idciudad, err);
    }
    
//listar ciudad por valor de busqueda
    
    public List<Ciudad> listFullTextLight(String query, String attrOrd, String ascDesc, int firstReg, int maxReg) {
     
        List<Ciudad> lstC = new ArrayList<>();
     
        String sql = " SELECT "
                + " ciudad.idciudad,"
                + " ciudad.nombre as ciudad,"
                + " ciudad.codoficial,"
                + " ciudad.iddepartamento,"
                + " departamentoestado.codoficial,"
                + " departamentoestado.nombre as departamento,"
                + " departamentoestado.idpais,"
                + " pais.nombre as  pais,"
                + " pais.cod_oficial_iso,"
                + " pais.iso2,"
                + " pais.iso3"
                + " FROM ciudad "
                + " join departamentoestado on ciudad.iddepartamento = departamentoestado.iddepartamento\n"
                + " join pais on departamentoestado.idpais = pais.idpais\n"
                + " WHERE to_tsvector(ciudad.nombre)@@to_tsquery('" + new Cadena().reemplazaEspacios(query, "&").trim() + "') "
                + " ORDER BY ciudad." + attrOrd + " ASC";
    
        List<Object[]> lst = findNativeGeneric(sql, firstReg, maxReg);
        for (Object[] o : lst) {
            
            Ciudad c = new Ciudad(new Integer(o[0].toString()));
            
            c.setNombre(o[1].toString());
            c.setCodoficial((String) o[2].toString());
            c.setIddepartamento(new Departamentoestado(new Integer(o[3].toString())));
            c.getIddepartamento().setCodoficial(o[4].toString());
            c.getIddepartamento().setNombre(o[5].toString());
            c.getIddepartamento().setIdpais(new Pais(new Integer(o[6].toString())));
            c.getIddepartamento().getIdpais().setNombre(o[7].toString());
            c.getIddepartamento().getIdpais().setCodOficialIso(o[8].toString());
            c.getIddepartamento().getIdpais().setIso2(o[9].toString());
            c.getIddepartamento().getIdpais().setIso3(o[10].toString());

            lstC.add(c);
        }
        return lstC;
    }

    
    //buscar ciudad por id
    
    public Ciudad findIDCiudad(String ciudad) {

        String hql = " SELECT  C FROM Ciudad C"
                + " WHERE C.nombre='" + ciudad + "'";
      
        return objectFromHQL(hql);

    }
    
     public Ciudad findCiudadID(String ciudad) {

        String hql = " SELECT  C FROM Ciudad C"
                + " WHERE C.idciudad='" + ciudad + "'";
      
        return objectFromHQL(hql);

    }
        
      
                
    public Ciudad findCiudadIDS(Integer c) {

        String hql = " SELECT  C FROM Ciudad C"
                + " WHERE C.idciudad='" + c + "'";
                      System.out.println("consulta ciudad " + hql);
        return objectFromHQL(hql);

    }
    
        //buscar ciudad por id
    
     public Ciudad findCiudadID(Ciudad ciudad) {

        String hql = " SELECT  C FROM Ciudad C"
                + " WHERE C.idciudad='" + ciudad.getIdciudad() + "'";
       
        return objectFromHQL(hql);

    }

     
     //consultar ciudad ppl
     
    public Ciudad consultarPPLCIUDAD(String ciudad, StringBuilder err) {
        
        String hql = " SELECT  C FROM Ciudad C"
                + " WHERE C.nombre='"+ciudad+"'";
       
        return objectFromHQL(hql);
    }

    //listar todos las ciudades
    
    public List<Ciudad> listAllTipos() {
        
        String hql = " SELECT C "
                + " FROM    Ciudad C "
                + " where C.iddepartamento\n"
                + " and C.iddepartamento.idpais\n"
                + " ORDER BY C.nombre ASC";

        return findList(hql);

    }
    
    //listar todas las ciudades
    
      public List<Ciudad> listAllCiudades() {
          
        String hql = " SELECT C "
                + " FROM    Ciudad C "
                + " ORDER BY C.nombre ASC";

        return findList(hql);

    }
 
      //contar todad las ciudades
      
     public long countBuscar(String valbusq) {
            
        String sql = " SELECT count(*)\n"
                + "  FROM ciudad b\n"
                + "	WHERE to_tsvector(nombre)@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n";
           
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }
        
        //listar ciudades por valur de busqueda
        
     public List<Ciudad> buscarFullText(String valBusq, int first, int maxRegLista) {
                
        String sql = " SELECT *\n"
                + "  FROM ciudad b\n"
                + "	WHERE to_tsvector(nombre)@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') \n"
                + " ORDER BY idciudad ASC ";
        
        return findNative(sql, first, maxRegLista, Ciudad.class);
    }
            
            
            //listar todas las ciudades
            
    public List listard() {


     List<Ciudad> lstC = new ArrayList<>();
     
        String sql = " SELECT "
                + " ciudad.idciudad,"
                + " ciudad.nombre as ciudad,"
                + " ciudad.codoficial,"
                + " ciudad.iddepartamento,"
                + " departamentoestado.codoficial,"
                + " departamentoestado.nombre as departamento,"
                + " departamentoestado.idpais,"
                + " pais.nombre as  pais,"
                + " pais.cod_oficial_iso,"
                + " pais.iso2,"
                + " pais.iso3"
                + " FROM ciudad "
                + " join departamentoestado on ciudad.iddepartamento = departamentoestado.iddepartamento\n"
                + " join pais on departamentoestado.idpais = pais.idpais\n"
                + " ORDER BY ciudad.idciudad  ASC";
     
        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            
            Ciudad c = new Ciudad(new Integer(o[0].toString()));
            
            c.setNombre(o[1].toString());
            c.setCodoficial((String) o[2].toString());
            c.setIddepartamento(new Departamentoestado(new Integer(o[3].toString())));
            c.getIddepartamento().setCodoficial(o[4].toString());
            c.getIddepartamento().setNombre(o[5].toString());
            c.getIddepartamento().setIdpais(new Pais(new Integer(o[6].toString())));
            c.getIddepartamento().getIdpais().setNombre(o[7].toString());
            c.getIddepartamento().getIdpais().setCodOficialIso(o[8].toString());
            c.getIddepartamento().getIdpais().setIso2(o[9].toString());
            c.getIddepartamento().getIdpais().setIso3(o[10].toString());

            lstC.add(c);
        }
        
         lstC.get(0).getNombre();
         
        return lstC;
   }
               
   
               //buscar ciudad por id ciudad
               
     public List<Ciudad> CiudadesIDCiudad( String attrOrder, String ascDesc) {
                    
        String sql = " SELECT "
                + " ciudad.idciudad,"
                + " ciudad.nombre as ciudad,"
                + " ciudad.codoficial,"
                + " ciudad.iddepartamento,"
                + " departamentoestado.codoficial,"
                + " departamentoestado.nombre as departamento,"
                + " departamentoestado.idpais,"
                + " pais.nombre as  pais,"
                + " pais.cod_oficial_iso,"
                + " pais.iso2,"
                + " pais.iso3"
                + " FROM ciudad "
                + " join departamentoestado on ciudad.iddepartamento = departamentoestado.iddepartamento\n"
                + " join pais on departamentoestado.idpais = pais.idpais\n"
                + " ORDER BY ciudad.idciudad  ASC";
    
    
        List<Ciudad> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            
            Ciudad c = new Ciudad(new Integer(o[0].toString()));
            
            c.setNombre(o[1].toString());
            c.setCodoficial((String) o[2].toString());
            c.setIddepartamento(new Departamentoestado(new Integer(o[3].toString())));
            c.getIddepartamento().setCodoficial(o[4].toString());
            c.getIddepartamento().setNombre(o[5].toString());
            c.getIddepartamento().setIdpais(new Pais(new Integer(o[6].toString())));
            c.getIddepartamento().getIdpais().setNombre(o[7].toString());
            c.getIddepartamento().getIdpais().setCodOficialIso(o[8].toString());
            c.getIddepartamento().getIdpais().setIso2(o[9].toString());
            c.getIddepartamento().getIdpais().setIso3(o[10].toString());
            
            lstR.add(c);
        }

        return lstR;
    }
               
           
}
