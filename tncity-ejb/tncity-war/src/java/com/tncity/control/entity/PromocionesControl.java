/**
 * @name Promociones Control
 *
 * @ description crud de promociones
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;

import com.tncity.facade.entity.PromocionesFacade;

import com.tncity.facade.general.AbstractFacade;

import com.tncity.jpa.pojo.Promociones;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "promocionesControl")
@RequestScoped
public class PromocionesControl extends AbstractControl<Promociones> {

    @EJB
    PromocionesFacade facadePromociones;

    public PromocionesControl() {
        super(Promociones.class);
        attrOrd = "idromociones";
        ascDesc = "ASC";

    }

    String nombre;
    String tipopromocion;
    String valorpromocion;
    String fechainicial;
    String fechafinal;
    String estado;
    String observacion;
    String valcriterio;
    String[] digitoscedula;
    
    String idpromocion;

    public String getValcriterio() {
        return valcriterio;
    }

    public void setValcriterio(String valcriterio) {
        this.valcriterio = valcriterio;
    }
    
    
    
    String caplica;

    public String getIdpromocion() {
        return idpromocion;
    }

    public void setIdpromocion(String idpromocion) {
        this.idpromocion = idpromocion;
    }

    
    
    public String getCaplica() {
        return caplica;
    }

    public void setCaplica(String caplica) {
        this.caplica = caplica;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipopromocion() {
        return tipopromocion;
    }

    public void setTipopromocion(String tipopromocion) {
        this.tipopromocion = tipopromocion;
    }

    public String getValorpromocion() {
        return valorpromocion;
    }

    public void setValorpromocion(String valorpromocion) {
        this.valorpromocion = valorpromocion;
    }

    public String getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(String fechainicial) {
        this.fechainicial = fechainicial;
    }

    public String getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(String fechafinal) {
        this.fechafinal = fechafinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String[] getDigitoscedula() {
        return digitoscedula;
    }

    public void setDigitoscedula(String[] digitoscedula) {
        this.digitoscedula = digitoscedula;
    }

 
    
    

    List<Promociones> lispromociones = new ArrayList<>();

    public List<Promociones> getLispromociones() {
        return lispromociones;
    }

    public void setLispromociones(List<Promociones> lispromociones) {
        this.lispromociones = lispromociones;
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadePromociones;
    }

    @Override
    protected String displayObj(Promociones obj) {
        return obj.getIdpromocion() + " - " + obj.getIdpromocion()+ " - " + obj.getTipoValor() + " - " +
                obj.getValorPromocion()
                + " - " + obj.getFechaInicial() + " - " + obj.getFechaFinal() + " - " + 
                obj.getEstado() + " - " + obj.getObservacion()
                + " - " + obj.getCero()+ " - " + obj.getUno()+ " - " + obj.getDos()+ " - " +
                obj.getTres()+ " - " + obj.getCuatro()+ " - " + obj.getCinco()
                + " - " + obj.getSeis()+ " - " + obj.getSiete()+ " - " + obj.getOcho()+ " - " +
                obj.getNueve()+ " - " + obj.getTipoAplicadesde()
                + " - " + obj.getAplicadesde();

    }

    //nuevo promociones
    
    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadePromociones.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

        //nuevo promociones
    
    public void nuevapromocion(String idpromocion,String tipopromo,String valorpromouno,String valorpromodos,
            String finicial,String ffinal,String estado,String observacion,String cedula
                     ,String cero,String uno,String dos,String tres,String cuatro,String cinco,String seis,
                     String siete,String ocho,String nueve,String taplica,String caplicaa,String caplicab,
                     String caplicac,String caplicad,String caplicasi) {
        StringBuilder err = new StringBuilder();
        
        facadePromociones.nuevapromo(idpromocion,tipopromo,valorpromouno,valorpromodos,finicial,
                ffinal,estado,observacion,cedula,cero,uno,dos,tres,cuatro,cinco,seis,
                     siete, ocho,nueve,taplica,caplicaa,caplicab,caplicac,caplicad,caplicasi, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
        //editar promociones

     public void actualizarpromocion(String idpromocion,String tipopromo,String valorpromouno,
             String valorpromodos,String finicial,String ffinal,String estados ) {
        StringBuilder err = new StringBuilder();
        
        facadePromociones.actualizapromociones(idpromocion, tipopromo,valorpromouno,valorpromodos,
                finicial, ffinal,estados,err);
     
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
         //editar promociones
     
          public void eactualizarpromocion(String idpromocion,String tipopromo,String valorpromouno,
                  String finicial,String ffinal,String estados,String promocion ) {
        StringBuilder err = new StringBuilder();
        
        facadePromociones.eactualizapromociones(idpromocion, tipopromo,valorpromouno,finicial, 
                ffinal,estados,promocion,err);
     
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
              //editar promociones
          
    public void editarpromocion(  String nombre, String tipopromo, String valorpromouno,
                                  String finicial, String ffinal, String estado, String observacion, 
                                  String cero, String uno, String dos, String tres, String cuatro,
                                  String cinco, String seis, String siete, String ocho, String nueve,
                                  String  taplica, String caplicaa ,String idpromocion_,String promocion
    
            
            
    ) {
        StringBuilder err = new StringBuilder();
  
        facadePromociones.editarpromo(nombre,tipopromo,valorpromouno,
                           finicial,  ffinal, estado,  observacion, 
                           cero, uno, dos,  tres,  cuatro,
                           cinco,  seis, siete,  ocho,  nueve,
                           taplica,  caplicaa,
                           idpromocion_,promocion, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
        //editar promociones

    public void editar() {
        StringBuilder err = new StringBuilder();
        facadePromociones.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
    //eliminar promociones
    
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadePromociones.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

        //recuperar  promociones
    
    public void recuperaTotalPromociones() {

        totalConsulta = facadePromociones.countTotalPromociones();
    }

        //todas las promociones
    
    public void TotalPromociones() {

        lispromociones = facadePromociones.TodasPromociones("idpromocion", "DESC");
    }

    //recuperar todos los registros de las promociones
    
    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadePromociones.countBuscar(valBusq);
        }
    }

    //buscar promociones por valor de busqueda
    
    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadePromociones.buscarFullText(valBusq, first, maxRegLista);
    }


//recuperar promociones por id
    
    public void RecuperaPromocionesById(String idpromocion) {

       lispromociones = facadePromociones.BuscarPromocionesById(idpromocion, "idpromocion", "DESC");

    }

}
