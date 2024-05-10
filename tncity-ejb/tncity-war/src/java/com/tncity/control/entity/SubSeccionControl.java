/**
 * @name Sub Secciones Facade
 *
 * @ description crud de Sub Secciones 
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */

package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.ModuloXSeccionXsubseccionesFacade;

import com.tncity.facade.entity.SubseccionesFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Moduloxseccionxsubseccion;
import com.tncity.jpa.pojo.Subsecciones;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "SubSeccionControl")
@RequestScoped
public class SubSeccionControl extends AbstractControl<Subsecciones> {

    @EJB
    SubseccionesFacade facadesubseccion;

    @EJB
    ModuloXSeccionXsubseccionesFacade facademodxseccionxsub;

    List<Subsecciones> lstSSecciones = new ArrayList<>();

    List<Moduloxseccionxsubseccion> lstmodsecsub = new ArrayList<>();

    String nombre;
    String imagen;
    String url;
    String nombrevisual;
    String estado;
    String modulo ;  
    String seccion;
 String[] modulos;

    public String[] getModulos() {
        return modulos;
    }

    public void setModulos(String[] modulos) {
        this.modulos = modulos;
    }
 
 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombrevisual() {
        return nombrevisual;
    }

    public void setNombrevisual(String nombrevisual) {
        this.nombrevisual = nombrevisual;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

 


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    public List<Moduloxseccionxsubseccion> getLstmodsecsub() {
        return lstmodsecsub;
    }

    public void setLstmodsecsub(List<Moduloxseccionxsubseccion> lstmodsecsub) {
        this.lstmodsecsub = lstmodsecsub;
    }

    public List<Subsecciones> getLstSSecciones() {
        return lstSSecciones;
    }

    public void setLstSSecciones(List<Subsecciones> lstSSecciones) {
        this.lstSSecciones = lstSSecciones;
    }

    public SubSeccionControl() {
        super(Subsecciones.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadesubseccion;
    }

    @Override
    protected String displayObj(Subsecciones obj) {
        return obj.getNombre();
    }

    public void nuevo() {
        
        System.out.println(nombre + ' ' + imagen +' '+ url + ' '+ nombrevisual + ' '+ estado + ' '+
            modulo +' '+ seccion);   

        
        
        StringBuilder err = new StringBuilder();
        facadesubseccion.created(nombre , imagen ,url , nombrevisual , estado ,
            modulo , seccion, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar sub secciones
    
    public void editardto(String nombre,String nomvisual,String imagen
                          ,String link,String estado,String subseccionid,String modulos,String seccion){
    
     System.out.println( nombre + ' '  + nomvisual + ' ' + imagen  + ' '+ link + ' ' + estado
             + ' '  + subseccionid + ' ' + modulos +' '+ seccion); 
   
       StringBuilder err = new StringBuilder();
        facadesubseccion.editdos(nombre,nomvisual,imagen,link,estado,subseccionid,modulos,seccion, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
     
    }
    
    //editar sub secciones
    
    public void editar() {
        
         System.out.println( modulo +' '+ seccion); 
         
         
        StringBuilder err = new StringBuilder();
        facadesubseccion.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }
    
    //eliminar Sub subsecciones

    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadesubseccion.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //listar todas las sub secciones
    
    public void TotalSubsecciones() {

        totalConsulta = facadesubseccion.countTotalSubsecciones();

    }

    // recuperar todas las subsecciones
    
    public void RecuperaSubsecciones() {

        lstmodsecsub = facademodxseccionxsub.TodosLasSecciones("idsubseccion", "DESC");
    }


        //recuperar subsecciones por id subseccion
    
      public void RecuperaSubseccionesById(long idsubseccion) {

              
        lstmodsecsub = facademodxseccionxsub.SeccionByIdSeccion(idsubseccion,"idsubseccion", "DESC");
    }
      
}
