/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.auditorias.entity;

/**
 *
 * @author guiovanny
 */
import com.tncity.facade.general.AbstractFacade;
import com.tncity.util.Cadena;
import com.tncity.jpa.pojo.VistaRecaudo;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

import java.text.SimpleDateFormat;
import java.util.Date;


@Stateless
public class VistaRecaudoFacade extends AbstractFacade<VistaRecaudo>{
    
 @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public VistaRecaudoFacade() {
        super(VistaRecaudo.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = { "idrecaudo",
 "idbenefactor","idbeneficiario","idpersona",
"nombreFamiliar","apellidoFamiliar","tipodocumentoFamiliar",
"numdocumentoFamiliar","telefonoFamiliar","correoFamilar",
 "td","pin","nombresApellidos","valor",
"estado","log", "createdAt","checkTelefoniaAt","establecimiento", 
   "codPago","tokenWtnpagos","idpasarela","telefonosms",
     "ciudad","observacion","observacionAdicional","pasareladia",
 "valorcomision","numtransaccion","valorpromocion","promo"     
        };
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"NombreTabla"};
        return attrs;
    }

    @Override
    protected VistaRecaudo parseObject(Object[] o) {
        VistaRecaudo r = new VistaRecaudo();
        r.setIdrecaudo(new BigInteger(o[0].toString()));
            r.setIdbenefactor(new BigInteger(o[1].toString()));
            r.setIdbeneficiario(new BigInteger(o[2].toString()));
            r.setIdpersona(new BigInteger(o[3].toString()));
            r.setNombreFamiliar(o[4].toString());
            r.setApellidoFamiliar(o[5].toString());
            r.setTipodocumentoFamiliar(o[6].toString());
            r.setNumdocumentoFamiliar(new BigInteger(o[7].toString()));
            r.setTelefonoFamiliar(new Long(o[8].toString()));
            r.setCorreoFamilar(o[9].toString());
        
            r.setTd(o[10].toString());
            r.setPin(o[11].toString());
            r.setNombresApellidos(o[12].toString());

            r.setValor(new Double(o[13].toString()));
            r.setEstado(o[14].toString());
            r.setLog(o[15].toString());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date fechaDate = null;
            Date fechaDate1 = null;
            try {
                fechaDate = formato.parse(o[16].toString());
                fechaDate1 = formato.parse(o[17].toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }

            r.setCreatedAt(fechaDate);
            r.setCheckTelefoniaAt(fechaDate1);
            r.setEstablecimiento(o[18].toString());
            r.setCodPago(o[19].toString());
            r.setTokenWtnpagos(o[20].toString());
            r.setIdpasarela(new Integer(o[21].toString()));
            r.setTelefonosms(o[22].toString());
            r.setCiudad(o[23].toString());
            r.setObservacion(o[24].toString());
            r.setObservacionAdicional(o[25].toString());

            r.setPasareladia(o[26].toString());
            r.setValorcomision(new Double(o[27].toString()));
            r.setNumtransaccion(o[28].toString());
            r.setValorpromocion(new Integer(o[29].toString()));
            r.setPromo(o[30].toString());
        return r;

    }

    //crear los barrios
    @Override
    public void create(VistaRecaudo obj, StringBuilder err) {
        createGeneral(obj, err);
    }

//editar los barrios
    @Override
    public void edit(VistaRecaudo obj, StringBuilder err) {
        editGeneral(obj, err);
    }

//borrar los barrios
    @Override
    public void delete(Object idbarrio, StringBuilder err) {
        deleteGeneral(idbarrio, err);
    }

    public long countTotalRecaudos() {
        String sql = " SELECT "
                + " count(*)"
                + " FROM recaudo  WHERE recaudo.idpasarela = 1 ";
        System.out.println("conteo de recaudos " + sql);
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    public List<VistaRecaudo> TodosRecaudoTrans(String attrOrder, String ascDesc) {

        String sql = " select "
                + " recaudo.idrecaudo,"
                  + " recaudo.idbenefactor,"
                  + " recaudo.idbeneficiario,"
                 + " persona.idpersona,"
                + " persona.nombres as nombre_familiar,"
                + " persona.apellidos as apellido_familiar,"
                + " persona.tipodocumento as tipodocumento_familiar,"
                + " persona.numdocumento as numdocumento_familiar,"
                + " persona.num_telefono as telefono_familiar,"
                + " persona.email  as correo_familar,"
                + " beneficiario.td ,"
                + " beneficiario.pin ,"
                + " beneficiario.nombres_apellidos ,"
                + " recaudo.valor,"
                + " recaudo.estado ,"
                + " recaudo.log ,"
                + " recaudo.created_at,"
                + " recaudo.check_telefonia_at ,"
                + " recaudo.establecimiento ,"
                + " recaudo.cod_pago ,"
                + " recaudo.token_wtnpagos,"
                + " recaudo.idpasarela ,"
                + " recaudo.telefonosms,"
                + " recaudo.ciudad ,"
                + " recaudo.observacion ,"
                + " recaudo.observacion_adicional ,"
                + " recaudo.pasareladia ,"
                + " recaudo.valorcomision ,"
                + " recaudo.numtransaccion, "
                + " recaudo.valorpromocion, "
                + " recaudo.promo"
                + " from recaudo "
                + " join benefactor on recaudo.idbenefactor = benefactor.idbenefactor"
                + " JOIN persona ON benefactor.idpersona = persona.idpersona"
                + " join beneficiario on recaudo.idbeneficiario = beneficiario.idbeneficiario"
                + " WHERE recaudo.idpasarela = 1 "
                + " order by "
                + " recaudo." + attrOrder + " " + ascDesc + "";

        List<VistaRecaudo> lstR = new ArrayList<>();
        System.out.println("auditoria consulta " + sql);
        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            VistaRecaudo r = new VistaRecaudo();
            r.setIdrecaudo(new BigInteger(o[0].toString()));
            r.setIdbenefactor(new BigInteger(o[1].toString()));
            r.setIdbeneficiario(new BigInteger(o[2].toString()));
            r.setIdpersona(new BigInteger(o[3].toString()));
            r.setNombreFamiliar(o[4].toString());
            r.setApellidoFamiliar(o[5].toString());
            r.setTipodocumentoFamiliar(o[6].toString());
            r.setNumdocumentoFamiliar(new BigInteger(o[7].toString()));
            r.setTelefonoFamiliar(new Long(o[8].toString()));
            r.setCorreoFamilar(o[9].toString());
        
            r.setTd(o[10].toString());
            r.setPin(o[11].toString());
            r.setNombresApellidos(o[12].toString());

            r.setValor(new Double(o[13].toString()));
            r.setEstado(o[14].toString());
            r.setLog(o[15].toString());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date fechaDate = null;
            Date fechaDate1 = null;
            try {
                fechaDate = formato.parse(o[16].toString());
                fechaDate1 = formato.parse(o[17].toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }

            r.setCreatedAt(fechaDate);
            r.setCheckTelefoniaAt(fechaDate1);
            r.setEstablecimiento(o[18].toString());
            r.setCodPago(o[19].toString());
            r.setTokenWtnpagos(o[20].toString());
            r.setIdpasarela(new Integer(o[21].toString()));
            r.setTelefonosms(o[22].toString());
            r.setCiudad(o[23].toString());
            r.setObservacion(o[24].toString());
            r.setObservacionAdicional(o[25].toString());

            r.setPasareladia(o[26].toString());
            r.setValorcomision(new Double(o[27].toString()));
            r.setNumtransaccion(o[28].toString());
            r.setValorpromocion(new Integer(o[29].toString()));
            r.setPromo(o[30].toString());

            lstR.add(r);

        }
        return lstR;
    }
    
  

            
      public long countTotalRecaudoFechas(String desde, String hasta, String idbenefactor, String estado, String idpasarela) {
        String nombres = "";
        String apellidos = "";

        if (idbenefactor.equals("")) {
            nombres = "";
            apellidos = "";
        } else {

            String[] parts = idbenefactor.split(" ");
            nombres = parts[0]; // 123
            apellidos = parts[1]; // 654321
        }

        String sql = "SELECT "
                + " COUNT(*)"
                + " FROM vista_recaudo where "
                + " created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss') "
                + " and cast(estado as  text)  lIKE '%" + estado + "%' "
                + " and cast(pasareladia as  text)  lIKE '%" + idpasarela + "%' "
                + " and cast(nombre_familiar as  text)  lIKE '%" + nombres + "%'"
                + " and cast(apellido_familiar as  text)  lIKE '%" + apellidos + "%'"
                + " and vista_recaudo.idpasarela = 1 ";
        
        System.out.println("auditoria recaudo fecha :" + sql);
        return numFromSQL(sql, new BigInteger("0")).longValue();

    }
      
       public List<VistaRecaudo> ListarRecaudoFechas(String desde, String hasta, String idbenefactor, String estado, String idpasarela, String attrOrder, String ascDesc) {

        String nombres = "";
        String apellidos = "";

        if (idbenefactor.equals("")) {
            nombres = "";
            apellidos = "";
        } else {

            String[] parts = idbenefactor.split(" ");
            nombres = parts[0]; // 123
            apellidos = parts[1]; // 654321
        }

        String sql = "SELECT "
              + " vista_recaudo.idrecaudo,"
                  + " vista_recaudo.idbenefactor,"
                  + " vista_recaudo.idbeneficiario,"
                 + " vista_recaudo.idpersona,"
                + " vista_recaudo.nombre_familiar,"
                + " vista_recaudo.apellido_familiar,"
                + " vista_recaudo.tipodocumento_familiar,"
                + " vista_recaudo.numdocumento_familiar,"
                + "vista_recaudo.telefono_familiar,"
                + " vista_recaudo.correo_familar,"
                + " vista_recaudo.td ,"
                + " vista_recaudo.pin ,"
                + " vista_recaudo.nombres_apellidos ,"
                + " vista_recaudo.valor,"
                + " vista_recaudo.estado ,"
                + " vista_recaudo.log ,"
                + " vista_recaudo.created_at,"
                + " vista_recaudo.check_telefonia_at ,"
                + " vista_recaudo.establecimiento ,"
                + " vista_recaudo.cod_pago ,"
                + " vista_recaudo.token_wtnpagos,"
                + " vista_recaudo.idpasarela ,"
                + " vista_recaudo.telefonosms,"
                + " vista_recaudo.ciudad ,"
                + " vista_recaudo.observacion ,"
                + " vista_recaudo.observacion_adicional ,"
                + " vista_recaudo.pasareladia ,"
                + " vista_recaudo.valorcomision ,"
                + " vista_recaudo.numtransaccion, "
                + " vista_recaudo.valorpromocion, "
                + " vista_recaudo.promo"
                + " FROM vista_recaudo where "
                + " created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss') "
                + " and cast(estado as  text)  lIKE '%" + estado + "%' "
                + " and cast(pasareladia as  text)  lIKE '%" + idpasarela + "%' "
                + " and cast(nombre_familiar as  text)  lIKE '%" + nombres + "%'"
                + " and cast(apellido_familiar as  text)  lIKE '%" + apellidos + "%'"
                + " and vista_recaudo.idpasarela = 1 "
                 + " order by "
                + " vista_recaudo." + attrOrder + " " + ascDesc + "";
        
        System.out.println("toda informes: " + sql);

        List<VistaRecaudo> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            VistaRecaudo r = new VistaRecaudo();
            r.setIdrecaudo(new BigInteger(o[0].toString()));
            r.setIdbenefactor(new BigInteger(o[1].toString()));
            r.setIdbeneficiario(new BigInteger(o[2].toString()));
            r.setIdpersona(new BigInteger(o[3].toString()));
            r.setNombreFamiliar(o[4].toString());
            r.setApellidoFamiliar(o[5].toString());
            r.setTipodocumentoFamiliar(o[6].toString());
            r.setNumdocumentoFamiliar(new BigInteger(o[7].toString()));
            r.setTelefonoFamiliar(new Long(o[8].toString()));
            r.setCorreoFamilar(o[9].toString());
        
            r.setTd(o[10].toString());
            r.setPin(o[11].toString());
            r.setNombresApellidos(o[12].toString());

            r.setValor(new Double(o[13].toString()));
            r.setEstado(o[14].toString());
            r.setLog(o[15].toString());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date fechaDate = null;
            Date fechaDate1 = null;
            try {
                fechaDate = formato.parse(o[16].toString());
                fechaDate1 = formato.parse(o[17].toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }

            r.setCreatedAt(fechaDate);
            r.setCheckTelefoniaAt(fechaDate1);
            r.setEstablecimiento(o[18].toString());
            r.setCodPago(o[19].toString());
            r.setTokenWtnpagos(o[20].toString());
            r.setIdpasarela(new Integer(o[21].toString()));
            r.setTelefonosms(o[22].toString());
            r.setCiudad(o[23].toString());
            r.setObservacion(o[24].toString());
            r.setObservacionAdicional(o[25].toString());

            r.setPasareladia(o[26].toString());
            r.setValorcomision(new Double(o[27].toString()));
            r.setNumtransaccion(o[28].toString());
            r.setValorpromocion(new Integer(o[29].toString()));
            r.setPromo(o[30].toString());

            lstR.add(r);

        }
        return lstR;

    }

}

