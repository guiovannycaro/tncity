/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Beneficiario;
import com.tncity.jpa.pojo.Pasarelapago;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Promocion;
import com.tncity.jpa.pojo.Promociones;

import com.tncity.jpa.pojo.Recaudo;
import com.tncity.jpa.pojo.Recaudopromo;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class RecaudopromoFacade extends AbstractFacade<Recaudopromo> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public RecaudopromoFacade() {
        super(Recaudopromo.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idrecpromocion", "idpromociones", "idrecaudo"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Recaudopromo parseObject(Object[] o) {
        Recaudopromo b = new Recaudopromo((Integer) o[0]);
        b.setIdrecaudo(new Recaudo(new Long(o[1].toString())));
        b.setIdpromociones(new Promociones(new Integer(o[2].toString())));

        return b;
    }

    @Override
    public void create(Recaudopromo obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Recaudopromo obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idbarrio, StringBuilder err) {
        deleteGeneral(idbarrio, err);
    }

    public Recaudopromo validapromocion(Long b, String idpromocion) {
        Recaudopromo r = null;
        String sql = " select * from "
                + " recaudopromo "
                + " join recaudo on recaudopromo.idrecaudo = recaudo.idrecaudo "
                + " join promociones on recaudopromo.idpromociones = promociones.idpromociones "
                + " join benefactor on recaudo.idbenefactor = benefactor.idbenefactor "
                + " where benefactor.idbenefactor = '" + b + "' and promociones.idpromocion = '" + idpromocion + "'";
    
        return objectFromSQL(sql);
    }

    public Recaudopromo validarPromociones(Promociones p, Benefactor b) {

        String sql = " select * from "
                + " recaudopromo "
                + " join recaudo on recaudopromo.idrecaudo = recaudo.idrecaudo "
                + " join promociones on recaudopromo.idpromociones = promociones.idpromociones "
                + " where  recaudo.created_at > current_date - interval '1' MONTH "
                + " and recaudo.idbenefactor = '" + b.getIdbenefactor() + "' "
                + " and promociones.idpromociones = '" + p.getIdpromociones() + "'";

        return objectFromSQL(sql);
    }
 
    public long countTotalPromociones() {
        String sql = "SELECT "
                + " count(*)"
                + " FROM recaudopromo  ";


        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    public List<Recaudopromo> TodasPromociones(String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " recaudopromo.idrecpromocion,"
                + " recaudopromo.promocion_finicial,"
                + " recaudopromo.promocion_ffinal,"
                + " promociones.idpromociones,"
                + " promociones.idpromocion,"
                + " promocion.nombre,"
                + " promocion.estado as estado_promo,"
                + " promocion.observacion as observacion_promo,"
                + " recaudopromo.idrecaudo,"
                + " recaudo.valor,"
                + " recaudo.estado,"
                + " recaudo.created_at,"
                + " recaudo.establecimiento,"
                + " recaudo.pasareladia ,"
                + " recaudo.telefonosms,"
                + " recaudo.ciudad,"
                + " recaudo.observacion ,"
                + " recaudo.observacion_adicional,"
                + " recaudo.valorcomision ,"
                + " recaudo.numtransaccion ,"
                + " recaudo.valorpromocion,"
                + " recaudo.promo, "
                + " recaudo. idbenefactor,"
                + " persona.idpersona ,"
                + " persona.nombres ,"
                + " persona.apellidos ,"
                + " recaudo.idbeneficiario,"
                + " beneficiario.td,"
                + " beneficiario.pin,"
                + " beneficiario.nombres_apellidos as nombre_beneficiario"
                + " FROM recaudopromo  "
                + " join promociones on recaudopromo.idpromociones = promociones.idpromociones"
                + " join promocion on promociones.idpromocion = promocion.idpromocion"
                + " join recaudo on recaudopromo.idrecaudo = recaudo.idrecaudo"
                + " join benefactor on recaudo.idbenefactor = benefactor.idbenefactor "
                + " join persona on benefactor.idpersona = persona.idpersona"
                + " join beneficiario on recaudo.idbeneficiario = beneficiario.idbeneficiario"
                + " ORDER BY recaudopromo." + attrOrder + " " + ascDesc;
      

        List<Recaudopromo> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {

            Recaudopromo p = new Recaudopromo(new Integer(o[0].toString()));

            Date FINI = null;
            Date FEND = null;
            Date FCREATED = null;
            try {
                FINI = new SimpleDateFormat("yyyy-MM-dd").parse(o[1].toString());
                FEND = new SimpleDateFormat("yyyy-MM-dd").parse(o[2].toString());
                FCREATED = new SimpleDateFormat("yyyy-MM-dd").parse(o[11].toString());

            } catch (ParseException ex) {
                Logger.getLogger(RecaudopromoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            p.setPromocionFinicial(FINI);
            p.setPromocionFfinal(FEND);
            p.setIdpromociones(new Promociones(new Integer(o[3].toString())));
            p.getIdpromociones().setIdpromocion(new Promocion(new Integer(o[4].toString())));
            p.getIdpromociones().getIdpromocion().setNombre(o[5].toString());
            p.getIdpromociones().getIdpromocion().setEstado(new Boolean(o[6].toString()));
            p.getIdpromociones().getIdpromocion().setObservacion(o[7].toString());
            p.setIdrecaudo(new Recaudo(new Long(o[8].toString())));
            p.getIdrecaudo().setValor(new Double(o[9].toString()));
            p.getIdrecaudo().setEstado(o[10].toString());
            p.getIdrecaudo().setCreatedAt(FCREATED);
            p.getIdrecaudo().setEstablecimiento(o[12].toString());
            p.getIdrecaudo().setIdpasarela(new Pasarelapago(new Integer(o[13].toString())));
            p.getIdrecaudo().setTelefonosms(o[14].toString());
            p.getIdrecaudo().setCiudad(o[15].toString());
            p.getIdrecaudo().setObservacion(o[16].toString());
            p.getIdrecaudo().setObservacionAdicional(o[17].toString());
            p.getIdrecaudo().setValorcomision(new Double(o[18].toString()));
            p.getIdrecaudo().setNumtransaccion(o[19].toString());
            p.getIdrecaudo().setValorpromocion(new Integer(o[20].toString()));
            p.getIdrecaudo().setPromo(o[21].toString());
            p.getIdrecaudo().setIdbenefactor(new Benefactor(new Long(o[22].toString())));
            p.getIdrecaudo().getIdbenefactor().setIdpersona(new Persona(new Long(o[23].toString())));
            p.getIdrecaudo().getIdbenefactor().getIdpersona().setNombres(o[24].toString());
            p.getIdrecaudo().getIdbenefactor().getIdpersona().setApellidos(o[25].toString());
            p.getIdrecaudo().setIdbeneficiario(new Beneficiario(new Long(o[26].toString())));
            p.getIdrecaudo().getIdbeneficiario().setPin(o[27].toString());
            p.getIdrecaudo().getIdbeneficiario().setTd(o[28].toString());
            p.getIdrecaudo().getIdbeneficiario().setNombresApellidos(o[29].toString());

            lstR.add(p);

        }
        return lstR;
    }
}
