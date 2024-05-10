/**
 * @name transmensajetexto facade
 *
 * @ description crud de transmensajetexto
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.ParametrosSmSText;
import com.tncity.config.pojoaux.ParametrosSmsConfig;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Beneficiario;
import com.tncity.jpa.pojo.Recaudo;
import com.tncity.jpa.pojo.Transacciones;
import com.tncity.jpa.pojo.Transmensajetexto;
import com.tncity.jpa.pojoaux.ParametrosSmsRespuesta;
import com.tncity.util.JsonUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.hibernate.impl.SessionImpl;

@Stateless
public class TransaccionesSmSFacade extends AbstractFacade<Transmensajetexto> {

    @EJB
    ParamFacade paramFacade;

    @EJB
    BeneficiarioFacade beneficiarioFacade;

    @EJB
    BenefactorFacade benefactorFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TransaccionesSmSFacade() {
        super(Transmensajetexto.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idmensajetxt", "status", "cantidadmensajes", "valor", "cantidad_caracteres",
            "mensaje", "idconfirmacion", "transaccionid", "codigo", "mensajeerror"
        };
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"idmensajetxt", "status", "cantidadmensajes", "valor", "cantidad_caracteres",
            "mensaje", "idconfirmacion", "transaccionid", "codigo", "mensajeerror"
        };
        return attrs;
    }

    @Override
    protected Transmensajetexto parseObject(Object[] o) {
        Transmensajetexto p = new Transmensajetexto((Integer) o[0]);
        p.setStatus(o[1].toString());
        p.setCantidadmensajes(o[2].toString());
        p.setValor(o[3].toString());
        p.setCantidadCaracteres(o[4].toString());
        p.setMensaje(o[5].toString());
        p.setIdconfirmacion(o[6].toString());
        p.setTransaccionid(new Transacciones(new Long(o[7].toString())));

        return p;
    }

    //crear Transmensajetexto
    
    @Override
    public void create(Transmensajetexto obj, StringBuilder err) {
        createGeneral(obj, err);
    }

     //editar Transmensajetexto
    
    @Override
    public void edit(Transmensajetexto obj, StringBuilder err) {
        editGeneral(obj, err);
    }
    
     //borrar Transmensajetexto

    @Override
    public void delete(Object idpais, StringBuilder err) {
        deleteGeneral(idpais, err);
    }

     //buscar id Transmensajetexto
    
    public Transmensajetexto findByIdSmS(String id) {
        String hql = " SELECT * FROM transmensajetexto WHERE Idmensajetxt='" + id + "'";
        return objectFromHQL(hql);
    }

     // guardar mensaje de texto
    
    public void crearsmstext(String t, ParametrosSmsRespuesta res) {

        System.out.println("smstext" + t + "********************************" + res.getStatus());
        StringBuilder err = new StringBuilder();
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " Insert into  transmensajetexto ("
                    + "status,"
                    + "cantidadmensajes,"
                    + "valor,"
                    + "cantidad_caracteres,"
                    + "mensaje,"
                    + "idconfirmacion,"
                    + "transaccionid"
                    + ") values ("
                    + "'" + res.getStatus() + "',"
                    + "'" + res.getCantidad_mensajes() + "',"
                    + "'" + res.getValor() + "',"
                    + "'" + res.getCantidad_caracteres() + "',"
                    + "'" + res.getMensaje() + "',"
                    + "'" + res.getId() + "',"
                    + "'" + t + "')";

            System.out.println(" insert  transaccion: " + sql);
            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    //insertar mensajes de texto 
    
    public void crearsmstexttnpagos(String line, Transacciones trans, String t) {
        int numero = (int) (Math.random() * ((500000000 + 1) - 1)) + 1;

     
        System.out.println(" smstext " + t + "********************************");

        StringBuilder err = new StringBuilder();
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " Insert into  transmensajetexto ("
                    + "status,"
                    + "cantidadmensajes,"
                    + "valor,"
                    + "cantidad_caracteres,"
                    + "mensaje,"
                    + "idconfirmacion,"
                    + "transaccionid"
                    + ") values ("
                    + "'" + 1 + "',"
                    + "'PENDING ',"
                    + "'PENDING_ENROUTE',"
                    + "'" + numero + "',"
                    + "'" + line + "',"
                    + "'" + trans.getNumerorecibo() + "',"
                    + "'" + trans.getIdtransaccion() + "')";

            System.out.println("insert  transaccion: " + sql);
            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    //listar mensajes de texto por id
    
    public List<Transmensajetexto> transaccionmsmById(Integer idtransaccion, String attrOrder, String ascDesc) {

        String sql = " SELECT "
                + " transmensajetexto.idmensajetxt       ,"
                + " transmensajetexto.status             ,"
                + " transmensajetexto.cantidadmensajes   ,"
                + " transmensajetexto.valor              ,"
                + " transmensajetexto.cantidad_caracteres,"
                + " transmensajetexto.mensaje            ,"
                + " transmensajetexto.idconfirmacion     , "
                + " transmensajetexto.transaccionid "
                + " from transmensajetexto  "
                + " where transmensajetexto.transaccionid  = '" + idtransaccion + "'\n"
                + " ORDER BY transmensajetexto." + attrOrder + " " + ascDesc;
        System.out.println("toda msm: " + sql);

        List<Transmensajetexto> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Transmensajetexto t = new Transmensajetexto(new Integer(o[0].toString()));

            t.setStatus(o[1].toString());
            t.setCantidadmensajes(o[2].toString());
            t.setValor(o[3].toString());
            t.setCantidadCaracteres(o[4].toString());
            t.setMensaje(o[5].toString());
            t.setIdconfirmacion(o[6].toString());
            t.setTransaccionid(new Transacciones(new Long(o[7].toString())));

            lstR.add(t);

        }
        return lstR;
    }
}
