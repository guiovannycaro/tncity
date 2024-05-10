/**
 * @name Transacciones  Sms Control
 *
 * @ description crud de Transacciones  Sms
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */

package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;


import com.tncity.facade.entity.TransaccionesSmSFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Transmensajetexto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "transaccionesSmSControl")
@RequestScoped
public class TransaccionesSmSControl extends AbstractControl<Transmensajetexto> {

    @EJB
    TransaccionesSmSFacade facade;

    List<Transmensajetexto> lstmsmtransaccion = new ArrayList<>();

    public List<Transmensajetexto> getLstmsmtransaccion() {
        return lstmsmtransaccion;
    }

    public void setLstmsmtransaccion(List<Transmensajetexto> lstmsmtransaccion) {
        this.lstmsmtransaccion = lstmsmtransaccion;
    }

    public TransaccionesSmSControl() {
        super(Transmensajetexto.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Transmensajetexto obj) {
        return obj.getIdmensajetxt() + " - " + obj.getStatus() + " - " + obj.getValor()
                + " - " + obj.getCantidadCaracteres() + " - " + obj.getMensaje() + " - " + obj.getIdconfirmacion()
                + " - " + obj.getTransaccionid();

    }

    // recuperar transacciones sms por id transaccion 
    
    public void recuperaByNomBarrio(String idtransaccion) {
        obj = facade.findByIdSmS(idtransaccion);
    }

    //nueva transaccion sms 
    
    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facade.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
//editar transaccion sms 
    
    public void editar() {
        StringBuilder err = new StringBuilder();
        facade.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //eliminar transacciones sms 
    
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facade.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }

    //detalle transacciones sms 
    
    public void DetallemsmTransaccion() {
        Integer idtransaccion = facesUtil.getFacesParamInteger("idtransaccion_");
        lstmsmtransaccion = facade.transaccionmsmById(idtransaccion, "idmensajetxt", "DESC");
    }
}
