/**
 * @name Usuario Control
 *
 * @ description crud de Usuario
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.control.general.FacesUtil;
import com.tncity.facade.entity.PermisosFacade;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Permisos;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.util.HashUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "usuarioControl")
@RequestScoped
public class UsuarioControl extends AbstractControl<Usuario> {

    @EJB
    UsuarioFacade facade;

    @EJB
    PermisosFacade facadepermisos;

    String pass1;
    String pass2;

    String con_correo;
    String antcontra;
    String contra;
    String con_contra;
    String perfil;
    String idpersona;
    String isActivo;

    String modulo;
    String seccion;
    String subseccion;
    String consulta;
    String ingreso;
    String edicion;
    String busqueda;
    String desactivacion;

    String nomusuario;

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }

    List<Usuario> lstusuarios = new ArrayList<>();
    List<Permisos> lstpermisouser = new ArrayList<>();

    public List<Permisos> getLstpermisouser() {
        return lstpermisouser;
    }

    public void setLstpermisouser(List<Permisos> lstpermisouser) {
        this.lstpermisouser = lstpermisouser;
    }

    public List<Usuario> getLstusuarios() {
        return lstusuarios;
    }

    public void setLstusuarios(List<Usuario> lstusuarios) {
        this.lstusuarios = lstusuarios;
    }

    String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    String[] modulos;

    public String[] getModulos() {
        return modulos;
    }

    public void setModulos(String[] modulos) {
        this.modulos = modulos;
    }

    public String getModulosString() {
        return "Tus colores preferidos: " + Arrays.toString(modulos);
    }

    public String getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(String isActivo) {
        this.isActivo = isActivo;
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

    public String getSubseccion() {
        return subseccion;
    }

    public void setSubseccion(String subseccion) {
        this.subseccion = subseccion;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getDesactivacion() {
        return desactivacion;
    }

    public void setDesactivacion(String desactivacion) {
        this.desactivacion = desactivacion;
    }

    public String getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(String idpersona) {
        this.idpersona = idpersona;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getCon_correo() {
        return con_correo;
    }

    public void setCon_correo(String con_correo) {
        this.con_correo = con_correo;
    }

    public String getAntcontra() {
        return antcontra;
    }

    public void setAntcontra(String antcontra) {
        this.antcontra = antcontra;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getCon_contra() {
        return con_contra;
    }

    public void setCon_contra(String con_contra) {
        this.con_contra = con_contra;
    }

    String redirigir;

    public String getRedirigir() {
        return redirigir;
    }

    public void setRedirigir(String redirigir) {
        this.redirigir = redirigir;
    }

    public UsuarioControl() {
        super(Usuario.class);
        attrOrd = "username";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Usuario obj) {
        return obj.getIdpersona().getNombres() + " " + obj.getIdpersona().getApellidos() + " - " + obj.getUsername();
    }

    public List<SelectItem> selectId() {
        List<Usuario> lstObjs = facade.listAllLight("idpersona.nombres", "ASC");
        List<SelectItem> lstS = new ArrayList<>();
        lstS.add(new SelectItem(null, "---"));

        for (Usuario obj : lstObjs) {
            SelectItem it = new SelectItem(obj.getIdusuario(), displayObj(obj));
            lstS.add(it);
        }
        return lstS;
    }

    //recuperar usuario por id usuario
    
    public void recuperaByIdInteger(Integer idusuario) {
        if (idusuario != null) {
            obj = facade.find(idusuario);
        }
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    //nuevo usuario
    
    public void nuevo() {

        StringBuilder err = new StringBuilder();
        obj.setUpdatedAt(new Date());
        obj.setIsActivo(true);

        obj.setUsername(obj.getIdpersona().getNumdocumento().toString());

        facade.createusuario(obj, pass1, pass2, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }
//editar  usuario
    
    public void editar() {
        obj.setUpdatedAt(new Date());
        editDefault();
    }

    //cambiar password usuario
    
    public void cambiarPass() {
        Integer idusuario = facesUtil.getFacesParamInteger("idusuario_");
        StringBuilder err = new StringBuilder();
        facade.cambiarPass(idusuario, pass1, pass2, err);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("", "Contraseña Cambiada!");
        } else {
            facesUtil.msgError("ALERTA: ", err.toString());
            successful = false;
        }

    }

    //cambiar password usuario
    
    public void cambiarPassUser(String contant, String contra, String concontra, Integer id) {
        StringBuilder err = new StringBuilder();
        facade.cambiarAPass(id, contant, contra, concontra, err);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("", "Contraseña Cambiada!");
        } else {
            facesUtil.msgError("ALERTA: ", err.toString());
            successful = false;
        }
    }

    //recuperar password por usuario
    
    public void recuperarpass() {
        StringBuilder err = new StringBuilder();

        facade.recuperarPass(pass1, err);
        if (err.toString().isEmpty()) {
            successful = true;

            FacesUtil.currentInstance().msgOk("", "Contraseña Temporal asignada ahora ya puede ingresar al sistema");
        } else {
            FacesUtil.currentInstance().msgError("ALERTA:", err.toString());
            successful = false;
        }

    }

    public List<Usuario> listAutocomplete(String query) {
        return facade.listFullText(query, 0, 20);
    }

    //validar primer ingreso id usuario
    
    public void validarPrimerIngreso(Long idusuario) {
        obj = facade.findBylogId(idusuario);

        if (obj != null) {
            redirigir = "true";
            System.out.println(" rta:    " + redirigir);
        } else {
            redirigir = "false";
        }

    }
// recuperar usuario id usuario
    
    public void RecuperaUsuariosId() {
        lstusuarios = facade.RecuperaUsuarios();
    }

    //cambiar password de usuario
    
    public void cambiarPassCurrentUser() {
        StringBuilder err = new StringBuilder();
        facade.cambiarPass(facesUtil.getCurrentUser().getIdusuario(), pass1, pass2, err);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("", "Contraseña Cambiada!");
        } else {
            facesUtil.msgError("ALERTA: ", err.toString());
            successful = false;
        }
    }
    
    //ingresar usuario y activarle modulos

    public void ingresarusuario(
            String nusuario,
            String nperfil,
            String npass1,
            String npass2,
            String nestado,
            
            String modulo1,
            String modulo2,
            String modulo3,
            String modulo4,
            String modulo5,
            String modulo6,
            String modulo7,
            String modulo8,
            String modulo9,
            String modulo10,
            String modulo11,
            String modulo12,
            String modulo13,
            String modulo14,
            String modulo15,
            String modulo16,
            String modulo17,
            String modulo18,
            String modulo19,
            String modulo20,
            String modulo21,
            String modulo22,
            String modulo23,
            String modulo24,
            String modulo25,
            String modulo26,
            
            String seccion1,
            String seccion2,
            String seccion3,
            String seccion4,
            String seccion5,
            String seccion6,
            String seccion7,
            String seccion8,
            String seccion9,
            String seccion10,
            String seccion11,
            String seccion12,
            String seccion13,
            String seccion14,
            String seccion15,
            String seccion16,
            String seccion17,
            String seccion18,
            String seccion19,
            String seccion20,
            String seccion21,
            String seccion22,
            String seccion23,
            String seccion24,
            String seccion25,
            String seccion26,
            
            String sseccion1,
            String sseccion2,
            String sseccion3,
            String sseccion4,
            String sseccion5,
            String sseccion6,
            String sseccion7,
            String sseccion8,
            String sseccion9,
            String sseccion10,
            String sseccion11,
            String sseccion12,
            String sseccion13,
            String sseccion14,
            String sseccion15,
            String sseccion16,
            String sseccion17,
            String sseccion18,
            String sseccion19,
            String sseccion20,
            String sseccion21,
            String sseccion22,
            String sseccion23,
            String sseccion24,
            String sseccion25,
            String sseccion26,
            
            String csseccion1,
            String csseccion2,
            String csseccion3,
            String csseccion4,
            String csseccion5,
            String csseccion6,
            String csseccion7,
            String csseccion8,
            String csseccion9,
            String csseccion10,
            String csseccion11,
            String csseccion12,
            String csseccion13,
            String csseccion14,
            String csseccion15,
            String csseccion16,
            String csseccion17,
            String csseccion18,
            String csseccion19,
            String csseccion20,
            String csseccion21,
            String csseccion22,
            String csseccion23,
            String csseccion24,
            String csseccion25,
            String csseccion26,
            
            String isseccion1,
            String isseccion2,
            String isseccion3,
            String isseccion4,
            String isseccion5,
            String isseccion6,
            String isseccion7,
            String isseccion8,
            String isseccion9,
            String isseccion10,
            String isseccion11,
            String isseccion12,
            String isseccion13,
            String isseccion14,
            String isseccion15,
            String isseccion16,
            String isseccion17,
            String isseccion18,
            String isseccion19,
            String isseccion20,
            String isseccion21,
            String isseccion22,
            String isseccion23,
            String isseccion24,
            String isseccion25,
            String isseccion26,
            
            String esseccion1,
            String esseccion2,
            String esseccion3,
            String esseccion4,
            String esseccion5,
            String esseccion6,
            String esseccion7,
            String esseccion8,
            String esseccion9,
            String esseccion10,
            String esseccion11,
            String esseccion12,
            String esseccion13,
            String esseccion14,
            String esseccion15,
            String esseccion16,
            String esseccion17,
            String esseccion18,
            String esseccion19,
            String esseccion20,
            String esseccion21,
            String esseccion22,
            String esseccion23,
            String esseccion24,
            String esseccion25,
            String esseccion26,
            
            String bsseccion1,
            String bsseccion2,
            String bsseccion3,
            String bsseccion4,
            String bsseccion5,
            String bsseccion6,
            String bsseccion7,
            String bsseccion8,
            String bsseccion9,
            String bsseccion10,
            String bsseccion11,
            String bsseccion12,
            String bsseccion13,
            String bsseccion14,
            String bsseccion15,
            String bsseccion16,
            String bsseccion17,
            String bsseccion18,
            String bsseccion19,
            String bsseccion20,
            String bsseccion21,
            String bsseccion22,
            String bsseccion23,
            String bsseccion24,
            String bsseccion25,
            String bsseccion26) {

        StringBuilder err = new StringBuilder();

                    System.out.println (
                    " buscar 26 " + bsseccion26  
                   );
                    
        //crear usuario
        
        facade.crearusuario(
                nusuario,
                nperfil,
                npass1,
                npass2,
                nestado,
                
                modulo1,
                modulo2,
                modulo3,
                modulo4,
                modulo5,
                modulo6,
                modulo7,
                modulo8,
                modulo9,
                modulo10,
                modulo11,
                modulo12,
                modulo13,
                modulo14,
                modulo15,
                modulo16,
                modulo17,
                modulo18,
                modulo19,
                modulo20,
                modulo21,
                modulo22,
                modulo23,
                modulo24,
                modulo25,
                modulo26,
                
                seccion1,
                seccion2,
                seccion3,
                seccion4,
                seccion5,
                seccion6,
                seccion7,
                seccion8,
                seccion9,
                seccion10,
                seccion11,
                seccion12,
                seccion13,
                seccion14,
                seccion15,
                seccion16,
                seccion17,
                seccion18,
                seccion19,
                seccion20,
                seccion21,
                seccion22,
                seccion23,
                seccion24,
                seccion25,
                seccion26,
                
                sseccion1,
                sseccion2,
                sseccion3,
                sseccion4,
                sseccion5,
                sseccion6,
                sseccion7,
                sseccion8,
                sseccion9,
                sseccion10,
                sseccion11,
                sseccion12,
                sseccion13,
                sseccion14,
                sseccion15,
                sseccion16,
                sseccion17,
                sseccion18,
                sseccion19,
                sseccion20,
                sseccion21,
                sseccion22,
                sseccion23,
                sseccion24,
                sseccion25,
                sseccion26,
                
                csseccion1,
                csseccion2,
                csseccion3,
                csseccion4,
                csseccion5,
                csseccion6,
                csseccion7,
                csseccion8,
                csseccion9,
                csseccion10,
                csseccion11,
                csseccion12,
                csseccion13,
                csseccion14,
                csseccion15,
                csseccion16,
                csseccion17,
                csseccion18,
                csseccion19,
                csseccion20,
                csseccion21,
                csseccion22,
                csseccion23,
                csseccion24,
                csseccion25,
                csseccion26,
                
                isseccion1,
                isseccion2,
                isseccion3,
                isseccion4,
                isseccion5,
                isseccion6,
                isseccion7,
                isseccion8,
                isseccion9,
                isseccion10,
                isseccion11,
                isseccion12,
                isseccion13,
                isseccion14,
                isseccion15,
                isseccion16,
                isseccion17,
                isseccion18,
                isseccion19,
                isseccion20,
                isseccion21,
                isseccion22,
                isseccion23,
                isseccion24,
                isseccion25,
                isseccion26,
                
                esseccion1,
                esseccion2,
                esseccion3,
                esseccion4,
                esseccion5,
                esseccion6,
                esseccion7,
                esseccion8,
                esseccion9,
                esseccion10,
                esseccion11,
                esseccion12,
                esseccion13,
                esseccion14,
                esseccion15,
                esseccion16,
                esseccion17,
                esseccion18,
                esseccion19,
                esseccion20,
                esseccion21,
                esseccion22,
                esseccion23,
                esseccion24,
                esseccion25,
                esseccion26,
                
                bsseccion1,
                bsseccion2,
                bsseccion3,
                bsseccion4,
                bsseccion5,
                bsseccion6,
                bsseccion7,
                bsseccion8,
                bsseccion9,
                bsseccion10,
                bsseccion11,
                bsseccion12,
                bsseccion13,
                bsseccion14,
                bsseccion15,
                bsseccion16,
                bsseccion17,
                bsseccion18,
                bsseccion19,
                bsseccion20,
                bsseccion21,
                bsseccion22,
                bsseccion23,
                bsseccion24,
                bsseccion25, 
                bsseccion26, err
        );
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }
    
//editar usuario
    
    public void editarusuario(
            String nusuario,
            String nperfil,
            String nestado,
            String modulo1,
            String modulo2,
            String modulo3,
            String modulo4,
            String modulo5,
            String modulo6,
            String modulo7,
            String modulo8,
            String modulo9,
            String modulo10,
            String modulo11,
            String modulo12,
            String modulo13,
            String modulo14,
            String modulo15,
            String modulo16,
            String modulo17,
            String modulo18,
            String modulo19,
            String modulo20,
            String modulo21,
            String modulo22,
            String modulo23,
            String modulo24,
            String modulo25,
            String modulo26,
            
            String seccion1,
            String seccion2,
            String seccion3,
            String seccion4,
            String seccion5,
            String seccion6,
            String seccion7,
            String seccion8,
            String seccion9,
            String seccion10,
            String seccion11,
            String seccion12,
            String seccion13,
            String seccion14,
            String seccion15,
            String seccion16,
            String seccion17,
            String seccion18,
            String seccion19,
            String seccion20,
            String seccion21,
            String seccion22,
            String seccion23,
            String seccion24,
            String seccion25,
            String seccion26,
            String sseccion1,
            String sseccion2,
            String sseccion3,
            String sseccion4,
            String sseccion5,
            String sseccion6,
            String sseccion7,
            String sseccion8,
            String sseccion9,
            String sseccion10,
            String sseccion11,
            String sseccion12,
            String sseccion13,
            String sseccion14,
            String sseccion15,
            String sseccion16,
            String sseccion17,
            String sseccion18,
            String sseccion19,
            String sseccion20,
            String sseccion21,
            String sseccion22,
            String sseccion23,
            String sseccion24,
            String sseccion25,
            String sseccion26,
            
            String csseccion1,
            String csseccion2,
            String csseccion3,
            String csseccion4,
            String csseccion5,
            String csseccion6,
            String csseccion7,
            String csseccion8,
            String csseccion9,
            String csseccion10,
            String csseccion11,
            String csseccion12,
            String csseccion13,
            String csseccion14,
            String csseccion15,
            String csseccion16,
            String csseccion17,
            String csseccion18,
            String csseccion19,
            String csseccion20,
            String csseccion21,
            String csseccion22,
            String csseccion23,
            String csseccion24,
            String csseccion25,
            String csseccion26,
            
            String isseccion1,
            String isseccion2,
            String isseccion3,
            String isseccion4,
            String isseccion5,
            String isseccion6,
            String isseccion7,
            String isseccion8,
            String isseccion9,
            String isseccion10,
            String isseccion11,
            String isseccion12,
            String isseccion13,
            String isseccion14,
            String isseccion15,
            String isseccion16,
            String isseccion17,
            String isseccion18,
            String isseccion19,
            String isseccion20,
            String isseccion21,
            String isseccion22,
            String isseccion23,
            String isseccion24,
            String isseccion25,
            String isseccion26,
            
            String esseccion1,
            String esseccion2,
            String esseccion3,
            String esseccion4,
            String esseccion5,
            String esseccion6,
            String esseccion7,
            String esseccion8,
            String esseccion9,
            String esseccion10,
            String esseccion11,
            String esseccion12,
            String esseccion13,
            String esseccion14,
            String esseccion15,
            String esseccion16,
            String esseccion17,
            String esseccion18,
            String esseccion19,
            String esseccion20,
            String esseccion21,
            String esseccion22,
            String esseccion23,
            String esseccion24,
            String esseccion25,
            String esseccion26,
            
            String bsseccion1,
            String bsseccion2,
            String bsseccion3,
            String bsseccion4,
            String bsseccion5,
            String bsseccion6,
            String bsseccion7,
            String bsseccion8,
            String bsseccion9,
            String bsseccion10,
            String bsseccion11,
            String bsseccion12,
            String bsseccion13,
            String bsseccion14,
            String bsseccion15,
            String bsseccion16,
            String bsseccion17,
            String bsseccion18,
            String bsseccion19,
            String bsseccion20,
            String bsseccion21,
            String bsseccion22,
            String bsseccion23,
            String bsseccion24,
            String bsseccion25,
            String bsseccion26) {

        StringBuilder err = new StringBuilder();

        //editar usuario
        
        facade.editarusuario(
                nusuario,
                nperfil,
                nestado,
                modulo1,
                modulo2,
                modulo3,
                modulo4,
                modulo5,
                modulo6,
                modulo7,
                modulo8,
                modulo9,
                modulo10,
                modulo11,
                modulo12,
                modulo13,
                modulo14,
                modulo15,
                modulo16,
                modulo17,
                modulo18,
                modulo19,
                modulo20,
                modulo21,
                modulo22,
                modulo23,
                modulo24,
                modulo25,
                modulo26,
                
                seccion1,
                seccion2,
                seccion3,
                seccion4,
                seccion5,
                seccion6,
                seccion7,
                seccion8,
                seccion9,
                seccion10,
                seccion11,
                seccion12,
                seccion13,
                seccion14,
                seccion15,
                seccion16,
                seccion17,
                seccion18,
                seccion19,
                seccion20,
                seccion21,
                seccion22,
                seccion23,
                seccion24,
                seccion25,
                seccion26,
                
                sseccion1,
                sseccion2,
                sseccion3,
                sseccion4,
                sseccion5,
                sseccion6,
                sseccion7,
                sseccion8,
                sseccion9,
                sseccion10,
                sseccion11,
                sseccion12,
                sseccion13,
                sseccion14,
                sseccion15,
                sseccion16,
                sseccion17,
                sseccion18,
                sseccion19,
                sseccion20,
                sseccion21,
                sseccion22,
                sseccion23,
                sseccion24,
                sseccion25,
                sseccion26,
                
                csseccion1,
                csseccion2,
                csseccion3,
                csseccion4,
                csseccion5,
                csseccion6,
                csseccion7,
                csseccion8,
                csseccion9,
                csseccion10,
                csseccion11,
                csseccion12,
                csseccion13,
                csseccion14,
                csseccion15,
                csseccion16,
                csseccion17,
                csseccion18,
                csseccion19,
                csseccion20,
                csseccion21,
                csseccion22,
                csseccion23,
                csseccion24,
                csseccion25,
                csseccion26,
                
                isseccion1,
                isseccion2,
                isseccion3,
                isseccion4,
                isseccion5,
                isseccion6,
                isseccion7,
                isseccion8,
                isseccion9,
                isseccion10,
                isseccion11,
                isseccion12,
                isseccion13,
                isseccion14,
                isseccion15,
                isseccion16,
                isseccion17,
                isseccion18,
                isseccion19,
                isseccion20,
                isseccion21,
                isseccion22,
                isseccion23,
                isseccion24,
                isseccion25,
                isseccion26,
                
                esseccion1,
                esseccion2,
                esseccion3,
                esseccion4,
                esseccion5,
                esseccion6,
                esseccion7,
                esseccion8,
                esseccion9,
                esseccion10,
                esseccion11,
                esseccion12,
                esseccion13,
                esseccion14,
                esseccion15,
                esseccion16,
                esseccion17,
                esseccion18,
                esseccion19,
                esseccion20,
                esseccion21,
                esseccion22,
                esseccion23,
                esseccion24,
                esseccion25,
                esseccion26,
                
                bsseccion1,
                bsseccion2,
                bsseccion3,
                bsseccion4,
                bsseccion5,
                bsseccion6,
                bsseccion7,
                bsseccion8,
                bsseccion9,
                bsseccion10,
                bsseccion11,
                bsseccion12,
                bsseccion13,
                bsseccion14,
                bsseccion15,
                bsseccion16,
                bsseccion17,
                bsseccion18,
                bsseccion19,
                bsseccion20,
                bsseccion21,
                bsseccion22,
                bsseccion23,
                bsseccion24,
                bsseccion25,
                bsseccion26, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }

    //recuperar usuario por id persona
    
    public void RecuperaUsuarioById(String idpersonas) {

        StringBuilder err = new StringBuilder();

        Usuario u = facade.identificaUsuario(idpersonas, err);
        String idusuario = "" + u.getIdusuario();
        lstpermisouser = facadepermisos.BuscarsuarioById(idusuario, "idusuario", "DESC");
    }
    
//recuperar usuario por id usuario
    
    public void RecuperaUsuarioByIdS(String idusuario) {

        lstpermisouser = facadepermisos.BuscarsuarioById(idusuario, "idusuario", "DESC");
    }

}
