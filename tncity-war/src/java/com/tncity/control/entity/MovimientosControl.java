/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.EmailConfig;
import com.tncity.control.general.AbstractControl;
import com.tncity.control.general.FacesUtil;
import com.tncity.facade.entity.BenefactorFacade;
import com.tncity.facade.entity.MovimientosFacade;
import com.tncity.facade.entity.CuentaFacade;
import com.tncity.facade.entity.PersonaFacade;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Cuentamovimiento;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.notificacion.NotificacionFacade;
import com.tncity.util.BeanUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "movimientosControl")
@RequestScoped
public class MovimientosControl extends AbstractControl<Cuentamovimiento> {

    @EJB
    MovimientosFacade facademovimientos;

    @EJB
    BenefactorFacade facadebenefactor;

    @EJB
    PersonaFacade facadepersona;

    @EJB
    CuentaFacade facadecuentas;

    public MovimientosControl() {
        super(Cuentamovimiento.class);
        attrOrd = "idmovimiento";
        ascDesc = "ASC";

    }
    
    Double comision;

    List<Cuentamovimiento> lisMovCuenta = new ArrayList<>();
    List<Cuentamovimiento> lisRecCuenta = new ArrayList<>();

    public List<Cuentamovimiento> getLisRecCuenta() {
        return lisRecCuenta;
    }

    public void setLisRecCuenta(List<Cuentamovimiento> lisRecCuenta) {
        this.lisRecCuenta = lisRecCuenta;
    }
    

    public List<Cuentamovimiento> getLisMovCuenta() {
        return lisMovCuenta;
    }

    public void setLisMovCuenta(List<Cuentamovimiento> lisMovCuenta) {
        this.lisMovCuenta = lisMovCuenta;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    
    
    
    
    @Override
    protected AbstractFacade getFacade() {
        return facademovimientos;
    }

    @Override
    protected String displayObj(Cuentamovimiento obj) {
        return obj.getIdmovimiento() + " - " + obj.getValor() + " - " + obj.getTipo() + " - " + obj.getObservaciones() + " - " + obj.getObservacionesAnulacion()
                + " - " + obj.getPathAdjunto() + " - " + obj.getEstado() + " - " + obj.getObservacionesAnulacion()+ " - " + obj.getUpdatedAt() + " - " + obj.getCreatedAt()
                + " - " + obj.getIdcuenta() + " - " + obj.getIdrecaudo() + " - " + obj.getIdusuarioCreated() + " - " + obj.getIdusuarioUpdated();

    }

    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facademovimientos.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void nuevomovimiento() {
        StringBuilder err = new StringBuilder();
        
              long idcuenta = (long) facesUtil.getFacesParamInteger("idcuenta_");
              long idbenefactor = (long) facesUtil.getFacesParamInteger("idbenefactor_");
     
        facademovimientos.createmovimiento(obj,idcuenta,idbenefactor, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void editar() {
        StringBuilder err = new StringBuilder();
        facademovimientos.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facademovimientos.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    public void recuperaTotalmovimientosId(String benefactor, String idcuenta) {

        System.out.println("id benefactor " + benefactor);
        totalConsulta = facademovimientos.totalmovimientosIDB(benefactor, idcuenta);
    }

    public void recuperaCountTotalMovimientosId(String benefactor, String idcuenta) {

        System.out.println("id benefactor " + benefactor);
        lisMovCuenta = facademovimientos.CuentasIDMovimiento(benefactor, idcuenta, "idcuenta", "DESC");
    }

    public void recuperaCountTotalMovimientosIdd(String benefactor, String idcuenta,String idmovimiento) {

        System.out.println("id benefactor " + benefactor);
        lisMovCuenta = facademovimientos.CuentasIDMovimientodet(benefactor, idcuenta,idmovimiento, "idcuenta", "DESC");
    }
    
     public void recuperaTotalMovimientosCue(String benefactor, String idcuenta,String idmovimiento) {

        System.out.println("id benefactor " + benefactor);
        lisMovCuenta = facademovimientos.CuentasMovimientodatos(benefactor, idcuenta,idmovimiento, "idcuenta", "DESC");
    }

     

             
        public void recuperaRecaudosCuenta(String benefactor, String idcuenta,String idmovimiento) {

        System.out.println("id benefactor " + benefactor);
        lisRecCuenta = facademovimientos.DatosCuentaRecaudo(benefactor, idcuenta,idmovimiento, "idcuenta", "DESC");
    }     
             
}
