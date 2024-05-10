/**
 * @name Beneficiario Facade
 *
 * @ description crud de beneficiario
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

/**
 *
 * @author guiovanny
 */
import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.TelefoniaConfig;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Beneficiario;
import com.tncity.jpa.pojo.Persona;

import com.tncity.jpa.pojo.Usuario;
import com.tncity.jpa.pojoaux.TelefoniaJson;
import com.tncity.jpa.pojoaux.TelefoniaManager;
import com.tncity.telefonia.client.Datos;
import com.tncity.util.JsonUtil;
import com.tncity.util.UrlContent;
import static java.lang.System.err;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class BeneficiarioFacade extends AbstractFacade<Beneficiario> {

    @EJB
    PersonaFacade personaFacade;
    
    @EJB
    ParamFacade paramFacade;
    
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BeneficiarioFacade() {
        super(Beneficiario.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idbeneficiario", "td", "pin", "nombresApellidos", "idpersona", "updatedAt", "idusuarioUpdated"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombres_apellidos"};
        return attrs;
    }

    @Override
    protected Beneficiario parseObject(Object[] o) {

        Beneficiario b = new Beneficiario();
        b.setIdbeneficiario((Long) o[1]);

        b.setTd((String) o[2]);
        b.setPin((String) o[3]);
        b.setNombresApellidos((String) o[4]);

        b.setUpdatedAt(new Date());
        b.getIdusuarioUpdated().setIdusuario((Integer) o[7]);

        b.setIdpersona(new Persona((Long) o[5]));
        return b;

    }
    
    //buscar beneficiario por numero de documento

    public Beneficiario findBeneficiarioByNumdocumento(String numdocumento) {
        String hql = " SELECT b "
                + "  FROM Beneficiario b "
                + " WHERE b.idpersona.numdocumento='" + numdocumento + "'";
        
        return objectFromHQL(hql);
    }
    
    //buscar beneficiario por pin

    public Beneficiario findByNumPin(Integer pin) {
        String hql = " SELECT b "
                + "  FROM Beneficiario b "
                + " WHERE b.pin='" + pin + "'";
        
        return objectFromHQL(hql);

    }
    
    //buscar por numero de pin

    public Beneficiario findByNumDoc(String cedula) {
        String hql = " SELECT b "
                + "  FROM Beneficiario b "
                + " WHERE b.pin='" + cedula + "'";
        System.out.println("validar beneficiario   "+ hql);
        return objectFromHQL(hql);

    }
    
    //crear beneficiario

    @Override
    public void create(Beneficiario obj, StringBuilder err) {
        //Buscando si la persona existe
        Persona pOri = personaFacade.findByNumDocumento(obj.getIdpersona().getNumdocumento());
        if (pOri != null) {
            obj.setIdpersona(pOri);
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            //Guardando beneficiario
            obj.setUpdatedAt(new Date());
            obj.setNombresApellidos(obj.getNombresApellidos());

            sess.save(obj);

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
    public void edit(Beneficiario obj, StringBuilder err) {
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE beneficiario "
                    + "    SET td='" + obj.getTd() + "',"
                    + "        pin=" + obj.getPin() + ","
                    + "        idusuario_updated=" + obj.getIdusuarioUpdated().getIdusuario() + ","
                    + "        updated_at=now() "
                    + " WHERE idbeneficiario=" + obj.getIdbeneficiario();

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
    public void delete(Object idbeneficiario, StringBuilder err) {
        deleteGeneral(idbeneficiario, err);
    }
    
    //buscar beneficiario por nombre

    public Beneficiario findByNomBeneficiario(String nombre) {
        String hql = " SELECT * FROM beneficiario"
                + " join benefactor on beneficiario.idpersona = benefactor.idpersona "
                + " join persona on benefactor.idpersona = persona.idpersona WHERE persona.nombre='" + nombre + "'";
       
        return objectFromHQL(hql);
    }

    //contar beneficiarios
    
    public long countBuscar(String valbusq) {
        String sql = " SELECT count(*)\n"
                + "  FROM beneficiario b\n"
                + "  WHERE b.idpersona IN(  \n"
                + "	SELECT p.idpersona \n"
                + "	FROM persona p  \n"
                + "	WHERE to_tsvector(nombres||' '||apellidos||' '||numdocumento)@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n"
                + "	ORDER BY p.idpersona DESC)";
       
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    //buscar beneficiario por valor de busqueda
    
    public List<Beneficiario> buscarFullText(String valBusq, int first, int maxRegLista) {
        String sql = " SELECT *\n"
                + "  FROM beneficiario b\n"
                + "  WHERE b.idpersona IN(  \n"
                + "	SELECT p.idpersona \n"
                + "	FROM persona p  \n"
                + "	WHERE to_tsvector(nombres||' '||apellidos||' '||numdocumento)@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') \n"
                + "	ORDER BY p.idpersona DESC)"
                + " GROUP BY idbeneficiario";
     
        return findNative(sql, first, maxRegLista, Beneficiario.class);
    }

    // buscar ppl steven
    
    public TelefoniaJson TeleManagerbyIdBene(String numdoc, String tdoc, StringBuilder err) {

        TelefoniaConfig tc = paramFacade.getParamFromCache(TelefoniaConfig.class);
        String url = tc.getProtocolo().toLowerCase() + "://" + tc.getHost() + "/restwebtn/recargatn.php?id=" + numdoc;

        System.out.println("puerto:" + tc);
        String cont = new UrlContent(url).getContent();
        if (cont == null || cont.trim().equals("false")) {
            boolean respuestaService = false;
         

            err.append("El PIN no es válido!");
            return null;
        }

        String urlCont = new UrlContent(url).getContent();

        String prueba = String.valueOf(cont);
        System.out.println("prueba = " + prueba);
        TelefoniaJson datos = JsonUtil.jsonToObject(prueba, TelefoniaJson.class);
  System.out.println("prueba = " +  datos);
        if (!datos.getStatus().equals("1")) {
            err.append("La Cedula esta inactiva!");
            return null;
        }

        return datos;

    }

    //consultar por id ppl
    
    public TelefoniaJson consultabyIdBene(BigInteger numdoc) {

        StringBuilder err = new StringBuilder();

        TelefoniaConfig tc = paramFacade.getParamFromCache(TelefoniaConfig.class);
        String url = tc.getProtocolo().toLowerCase() + "://" + tc.getHost() + "/restwebtn/recargatn.php?id=" + numdoc;

        String cont = new UrlContent(url).getContent();

        if (cont == null || cont.trim().equals("false")) {
            boolean respuestaService = false;
        

            err.append("El cedula ppl no es válido!");
            return null;
        }

        String urlCont = new UrlContent(url).getContent();

        String prueba = String.valueOf(cont);

        TelefoniaJson datos = JsonUtil.jsonToObject(prueba, TelefoniaJson.class);

        if (!datos.getStatus().equals("1")) {
            err.append("La Cedula esta inactiva!");
            return null;
        }

        return datos;

    }

    //consultar por cedula
    
    public TelefoniaJson consultabyCedula(BigInteger numdoc, StringBuilder err) {

        TelefoniaConfig tc = paramFacade.getParamFromCache(TelefoniaConfig.class);
        String url = tc.getProtocolo().toLowerCase() + "://" + tc.getHost() + "/restwebtn/recargatn.php?id=" + numdoc;

        String cont = new UrlContent(url).getContent();

        if (cont == null || cont.trim().equals("false")) {
            boolean respuestaService = false;
       

            err.append("El PIN no es válido!");
            return null;
        }

        String urlCont = new UrlContent(url).getContent();

        String prueba = String.valueOf(cont);

        TelefoniaJson datos = JsonUtil.jsonToObject(prueba, TelefoniaJson.class);

        if (!datos.getStatus().equals("1")) {
            err.append("La Cedula esta inactiva!");
            return null;
        }

        return datos;

    }

    
    //buscar beneficiario  por id
    
    public Beneficiario findByNumID(String id) {
        String hql = " SELECT b "
                + "  FROM Beneficiario b "
                + " WHERE b.pin='" + id + "'";

        return objectFromHQL(hql);

    }

        //buscar beneficiario  por id
       public Beneficiario findByNumIDBen(Integer id) {
        String hql = " SELECT b "
                + "  FROM Beneficiario b "
                + " WHERE b.pin='" + id + "'";

        return objectFromHQL(hql);

    }
           //buscar beneficiario  por id
          public Beneficiario findByNumPin(String id) {
        String hql = " SELECT b "
                + "  FROM Beneficiario b "
                + " WHERE b.pin='" + id + "'";
         
        return objectFromHQL(hql);

    }
     
          //conteo de beneficiarios
          
    public long countBuscarIdBen(Integer idbenefactor) {
        String sql = " SELECT "
                + " count(*)"
                + " FROM beneficiario  "
                + " join persona  on beneficiario.idpersona = persona.idpersona "
                + " join benefactor on benefactor.idpersona = persona.idpersona  "
                + " WHERE beneficiario.idpersona = '" + idbenefactor + "' ";
       
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    //buscar beneficiario por id beneficiario
    
    public List<Beneficiario> beneficiarioIDBenefactor(Integer idbenefactor, String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " beneficiario.idbeneficiario,"
                + " beneficiario.nombres_apellidos,"
                + " beneficiario.td,"
                + " beneficiario.idpersona,"
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento,"
                + " persona.num_telefono,"
                + " persona.numdocumento,"
                + " beneficiario.pin"
                + " FROM beneficiario  "
                + " join persona  on beneficiario.idpersona = persona.idpersona "
                + " join benefactor on benefactor.idpersona = persona.idpersona  "
                + " WHERE beneficiario.idpersona = '" + idbenefactor + "' "
                + " ORDER BY beneficiario." + attrOrder + " " + ascDesc;
        

        
        System.out.println("listado " + sql);

        List<Beneficiario> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Beneficiario b = new Beneficiario(new Long(o[0].toString()));
            b.setNombresApellidos(o[1].toString());
            b.setTd(o[2].toString());
            b.setIdpersona(new Persona(new Long(o[3].toString())));
            b.getIdpersona().setNombres((String) o[4].toString());
            b.getIdpersona().setApellidos((String) o[5].toString());
            b.getIdpersona().setTipodocumento(o[6].toString());

            b.getIdpersona().setNumTelefono(new Long(o[7].toString()));
            b.getIdpersona().setNumdocumento(new BigInteger(o[8].toString()));
            b.setPin(o[9].toString());

            lstR.add(b);

        }
        return lstR;
    }

    //validar beneficiario
    
    public Beneficiario validabeneficiarios(String benefactor) {

         String hql = " SELECT b "
                + "  FROM Beneficiario b "
                + " WHERE b.idpersona ='" + benefactor + "'";
        
        return objectFromHQL(hql);
    }

    
    //consulta datos de beneficiario
    
    public TelefoniaJson datosbeneficiario(String numdoc, String tdoc, StringBuilder err) {

        TelefoniaJson benef = TeleManagerbyIdBene(numdoc, tdoc, err);

        String nombenef = benef.getLastname();

        return benef;

    }

}
