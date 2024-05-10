/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.ModuloXSeccionXsubseccionesFacade;

import com.tncity.facade.entity.PermisosFacade;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Moduloxseccionxsubseccion;
import com.tncity.jpa.pojo.Permisos;
import com.tncity.jpa.pojo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "PermisosControl")
@RequestScoped
public class PermisosControl extends AbstractControl<Permisos> {

    @EJB
    PermisosFacade facadepermisos;

    @EJB
    UsuarioFacade facadeusuario;

    @EJB
    ModuloXSeccionXsubseccionesFacade facademodxseccionxsub;

    List<Permisos> lstpermisos = new ArrayList<>();
    List<Permisos> lstsecciones = new ArrayList<>();
    List<Permisos> lstsubsecciones = new ArrayList<>();

    List<Moduloxseccionxsubseccion> lstmodsecsub = new ArrayList<>();

    String nombre;
    String imagen;
    String url;
    String nombrevisual;
    String estado;
    String modulo;

    String pmodulo;
    String seccion;
    String[] modulos;

    public List<Permisos> getLstsecciones() {
        return lstsecciones;
    }

    public void setLstsecciones(List<Permisos> lstsecciones) {
        this.lstsecciones = lstsecciones;
    }

    public List<Permisos> getLstsubsecciones() {
        return lstsubsecciones;
    }

    public void setLstsubsecciones(List<Permisos> lstsubsecciones) {
        this.lstsubsecciones = lstsubsecciones;
    }

    public String[] getModulos() {
        return modulos;
    }

    public String getPmodulo() {
        return pmodulo;
    }

    public void setPmodulo(String pmodulo) {
        this.pmodulo = pmodulo;
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

    public List<Permisos> getLstpermisos() {
        return lstpermisos;
    }

    public void setLstpermisos(List<Permisos> lstpermisos) {
        this.lstpermisos = lstpermisos;
    }

    public PermisosControl() {
        super(Permisos.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadepermisos;
    }

    public void RecuperaSubsecciones() {

        lstmodsecsub = facademodxseccionxsub.TodosLasSecciones("idsubseccion", "DESC");
    }

    public void EditarRecuperaPermisos(String idpersona) {
 StringBuilder err = new StringBuilder();
        Usuario u = facadeusuario.identificaUsuario(idpersona, err);
        String idusuario = ""+u.getIdusuario();
        lstpermisos = facadepermisos.TodosLosPermisos(idusuario, "idpermiso", "DESC");
    }

    
        public void EditarRecuperaPermisosS(String idpersona) {

        lstpermisos = facadepermisos.TodosLosPermisos(idpersona, "idpermiso", "DESC");
    }
        
    public void validarPermisosModulos(String idpersona) {
        StringBuilder err = new StringBuilder();
        Usuario u = facadeusuario.identificaUsuario(idpersona, err);
        String idusuario = ""+u.getIdusuario();
        lstpermisos = facadepermisos.ModulosPermisos(idusuario, "idpermiso", "DESC");
    }

    public void validarPermisosSecciones(String idpersona, String nommodulo) {
       StringBuilder err = new StringBuilder();
        Usuario u = facadeusuario.identificaUsuario(idpersona, err);
        String idusuario = ""+u.getIdusuario();
        lstsecciones = facadepermisos.SeccionesPermisos(idusuario, nommodulo, "idpermiso", "ASC");
    }

    public void validarPermisosSubSecciones(String idpersona, String nommodulo, String secciones) {
            StringBuilder err = new StringBuilder();
        Usuario u = facadeusuario.identificaUsuario(idpersona, err);
        String idusuario = ""+u.getIdusuario();
        lstsubsecciones = facadepermisos.SubSeccionesPermisos(idusuario, nommodulo, secciones, "idpermiso", "ASC");
    }

    public void validarSeccionesSubSecciones(String idpersona, String nommodulo, String secciones, String subseccion) {
      
            StringBuilder err = new StringBuilder();
        Usuario u = facadeusuario.identificaUsuario(idpersona, err);
        String idusuario = ""+u.getIdusuario();
        
        lstsubsecciones = facadepermisos.SubSeccionesContenidoPermisos(idusuario, nommodulo, secciones, subseccion, "idpermiso", "ASC");
    }

    @Override
    protected String displayObj(Permisos obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void validarModulo() {

    }

}
