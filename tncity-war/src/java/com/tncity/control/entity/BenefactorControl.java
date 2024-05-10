/**
 * @name Benefactor control
 *
 * @ description crud de benefactor
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.CondicionesyTerminos;
import com.tncity.config.pojoaux.EmailConfig;
import com.tncity.control.general.AbstractControl;
import com.tncity.control.general.FacesUtil;
import com.tncity.facade.entity.BenefactorFacade;
import com.tncity.facade.entity.CuentaFacade;
import com.tncity.facade.entity.MovimientosFacade;
import com.tncity.facade.entity.PersonaFacade;
import com.tncity.facade.entity.RecaudoFacade;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Cuenta;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Recaudo;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.notificacion.NotificacionFacade;
import com.tncity.util.BeanUtil;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name = "benefactorControl")
@RequestScoped
public class BenefactorControl extends AbstractControl<Benefactor> {

    @EJB
    BenefactorFacade facadebenefactor;

    @EJB
    PersonaFacade facadepersona;

    @EJB
    RecaudoFacade facaderecaudo;

    @EJB
    CuentaFacade facadecuenta;

    @EJB
    MovimientosFacade facadecuentamovimiento;

    @EJB
    UsuarioFacade facadeuser;

    CondicionesyTerminos terminos;

    ParamFacade paramFacade = BeanUtil.lookupFacadeBean(ParamFacade.class);

    NotificacionFacade notificacionFacade = BeanUtil.lookupFacadeBean(NotificacionFacade.class);

    Benefactor usLog = new Benefactor();
    List<Benefactor> lisben = new ArrayList<>();

    public List<Benefactor> getLisben() {
        return lisben;
    }

    public void setLisben(List<Benefactor> lisben) {
        this.lisben = lisben;
    }
    Cuenta cta;
    boolean successful = false;

    Benefactor familiar;

    BigInteger numdocumento;
    String tipodocumento;
    String redir;
    String email;
    String redirigir;
    BigInteger documento;
    String subdistribuidor;

    String vfamiliar;
    String nombre;
    String apellidos;
    String telefono;
    String telefono2;
    String correo;
    String ccorreo;
    String contraseña;
    String cccontraseña;
    String ciudad;

    String password;
    String password2;
    boolean value1;
    String value4;

    String con_correo;
    String antcontra;
    String contra;
    String con_contra;

    String antcontrat;
    String contrat;
    String con_contrat;

    String imgcapcha;
    String captcha;

    Double saldo;

    public String getAntcontrat() {
        return antcontrat;
    }

    public void setAntcontrat(String antcontrat) {
        this.antcontrat = antcontrat;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public String getCon_contrat() {
        return con_contrat;
    }

    public void setCon_contrat(String con_contrat) {
        this.con_contrat = con_contrat;
    }

    public Benefactor getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Benefactor familiar) {
        this.familiar = familiar;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCcorreo() {
        return ccorreo;
    }

    public void setCcorreo(String ccorreo) {
        this.ccorreo = ccorreo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCccontraseña() {
        return cccontraseña;
    }

    public void setCccontraseña(String cccontraseña) {
        this.cccontraseña = cccontraseña;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getSubdistribuidor() {
        return subdistribuidor;
    }

    public void setSubdistribuidor(String subdistribuidor) {
        this.subdistribuidor = subdistribuidor;
    }

    public String getRedirigir() {
        return redirigir;
    }

    public void setRedirigir(String redirigir) {
        this.redirigir = redirigir;
    }

    String mensaje;

    Benefactor benefactor;

    public Benefactor getBenefactor() {
        return benefactor;
    }

    public void setBenefactor(Benefactor benefactor) {
        this.benefactor = benefactor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getDocumento() {
        return documento;
    }

    public void setDocumento(BigInteger documento) {
        this.documento = documento;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getImgcapcha() {
        return imgcapcha;
    }

    public void setImgcapcha(String imgcapcha) {
        this.imgcapcha = imgcapcha;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCon_correo() {
        return con_correo;
    }

    public void setCon_correo(String con_correo) {
        this.con_correo = con_correo;
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

    public String getAntcontra() {
        return antcontra;
    }

    public void setAntcontra(String antcontra) {
        this.antcontra = antcontra;
    }

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public CondicionesyTerminos getTerminos() {
        return terminos;
    }

    public void setTerminos(CondicionesyTerminos terminos) {
        this.terminos = terminos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public BigInteger getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(BigInteger numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getRedir() {
        return redir;
    }

    public void setRedir(String redir) {
        this.redir = redir;
    }

    public String getVfamiliar() {
        return vfamiliar;
    }

    public void setVfamiliar(String vfamiliar) {
        this.vfamiliar = vfamiliar;
    }

    public BenefactorControl() {
        super(Benefactor.class);
        attrOrd = "idbenefactor";
        ascDesc = "ASC";
        obj.setIdpersona(new Persona());
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadebenefactor;
    }

    @Override
    protected String displayObj(Benefactor obj) {
        return obj.getIdpersona() + " - " + obj.getIdusuarioUpdated() + " - " + obj.getUpdatedAt();
    }

    //recuperar benefactor por id benefactor
    
    public void recuperaByNomBenefactor(String nombre) {
        obj = facadebenefactor.findByNomBenefactor(nombre);
    }

    //recuperar listado de benefactores
    
    public void recuperaBenefactores() {
        lisben = facadebenefactor.listBenefactores();
    }

    // nuevo benefactor
    
    public void nuevo(String nombre, String apellidos, String tipodocumento, BigInteger numdocumento,
            String telefono, String telefono2, String Ciudad, boolean value1,
            String correo, String con_correo, String contra, String con_contra, String imgcapcha,
            String captcha) {
        StringBuilder err = new StringBuilder();

        facadebenefactor.createbenefactor(nombre, apellidos, tipodocumento, numdocumento,
                telefono, telefono2, Ciudad, value1,
                correo, con_correo, contra, con_contra, imgcapcha,
                captcha, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //nuevo benefactor
    
    public void nuevobenefactor(String nombre, String apellidos, String tipodocumento, BigInteger numdocumento,
            String telefono, String telefono2, String Ciudad,
            String correo, String con_correo, String contra, String con_contra, String direccion) {
        StringBuilder err = new StringBuilder();

        facadebenefactor.createnuevobenefactor(nombre, apellidos, tipodocumento, numdocumento,
                telefono, telefono2, Ciudad,
                correo, con_correo, contra, con_contra, direccion, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

//registrar benefactor
    
    public void registrobenefactor(String nombre, String apellidos, String tipodocumento, BigInteger numdocumento,
            String telefono, String telefono2, String Ciudad, boolean value1,
            String correo, String con_correo, String contra, String con_contra
    ) {
        StringBuilder err = new StringBuilder();

        facadebenefactor.registrarbenefactor(
                nombre, apellidos, tipodocumento, numdocumento,
                telefono, telefono2, Ciudad, value1,
                correo, con_correo, contra, con_contra, err
        );
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

//registro benefactor
    
    public void rfactor() {
        StringBuilder err = new StringBuilder();

        facadebenefactor.registrarbenefactor(
                nombre, apellidos, tipodocumento, numdocumento,
                telefono, telefono2, ciudad, value1,
                correo, con_correo, contra, con_contra, err
        );
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //registro benefactor
    
    public void registrar() {
        StringBuilder err = new StringBuilder();

        obj.getIdpersona().setIdusuarioUpdated(new Usuario(facadeuser.USUARIO_SYS));
        obj.getIdpersona().setIdusuarioCreated(new Usuario(facadeuser.USUARIO_SYS));

        obj.setIdusuarioUpdated(new Usuario(facadeuser.USUARIO_SYS));

        facadebenefactor.registrar(obj, value1, correo, con_correo, contra, con_contra, imgcapcha, captcha, err);
        if (err.toString().isEmpty()) {
            //facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //validar benefactor
    
    public void validar() {
        obj = facadebenefactor.findBenefactorByNumdocumento(numdocumento);
        if (!terminos.equals("Acepto")) {
            facesUtil.msgError("ALERTA:", "Para continuar, debe aceptar los términos y condiciones.");
            return;
        }

        if (obj != null) {
            redir = "RECAUDO";
        } else {
            redir = "REGISTRO";
        }

    }

//validar proceso benefactor por numero de documento
    
    public void validarproceso() {
        obj = facadebenefactor.findBenefactorByNumdocumento(numdocumento);

        if (obj != null) {
            redir = "RECAUDO";
        }

    }

    public void terminos() {

    }

//editar benefactor
    
    public void editar() {
        StringBuilder err = new StringBuilder();

        long idbenefactor = (long) facesUtil.getFacesParamInteger("idbenefactor_");

        facadebenefactor.editbenefactor(idbenefactor, obj, contra, con_contra, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro actualizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

//eliminar benefactor
    
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadebenefactor.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }

//recuperar numero total de registros de benefactor
    
    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadebenefactor.countBuscar(valBusq);
        }
    }

    //buscar por valor de busqueda benefactor   
    
    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadebenefactor.buscarFullText(valBusq, first, maxRegLista);
    }

    //crear capcha benefactor 
    
    public void captchaCode() {
        imgcapcha = facadebenefactor.crearcapcha();
    }

    //cambiar password benefactor
    
    public void cambiarPassCurrentUser() {
        Integer idpersona = facesUtil.getFacesParamInteger("id_");
        System.out.println("cidpersona" + idpersona);
        StringBuilder err = new StringBuilder();
        facadebenefactor.cambiarPassLog(idpersona, antcontra, contra, con_contra, err);
        if (err.toString().isEmpty()) {
            System.out.println("datos" + "hola");
            successful = true;
            facesUtil.msgOk("ALERTA: ", "Contraseña Cambiada!");
        } else {
            facesUtil.msgError("ALERTA: ", err.toString());
            successful = false;
        }
    }

    // cambiar password user benefactor
    
    public void cambiarPassUser() {
        Integer idpersona = facesUtil.getFacesParamInteger("id_");
        System.out.println("cidpersona" + idpersona);
        StringBuilder err = new StringBuilder();
        facadebenefactor.cambiarPassId(idpersona, antcontrat, contrat, con_contrat, err);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("ALERTA: ", "Contraseña Cambiada!");
        } else {
            facesUtil.msgError("ALERTA: ", err.toString());
            successful = false;
        }
    }

    //recuperar id ppl benefactor
    
    public void recuperaIdPPL() {

        lisben = facadebenefactor.listPorIdBenefactor(numdocumento, tipodocumento);

        if (lisben == null) {
            mensaje = "ALERTA";

        }

    }

    //recuperar benefactor por id persona
    
    public void recuperaBenIdPersona(Long id) {

        lisben = facadebenefactor.BuscarIdPersona(id);

    }

//buscar benefactor por id
    
    public void buscarBenefactorPorId() {
        String tipodoc = facesUtil.getFacesParamValue("tdoc_");
        BigInteger numdoc = new BigInteger(facesUtil.getFacesParamValue("ndoc_"));

        lisben = facadebenefactor.listPorIdBenefactor(numdoc, tipodoc);

    }

    //validar primer ingreso benefactor
    
    public void validarPrimerIngreso(Long idbenefactor) {
        obj = facadebenefactor.findBylogId(idbenefactor);

        if (obj != null) {
            redirigir = "true";
        } else {
            redirigir = "false";
        }

    }

    //validar ingreso por id benefactor/benefactor
    
    public void validarIngreso(Long idbenefactor) {
        obj = facadebenefactor.findBylogId(idbenefactor);

        if (obj != null) {
            redirigir = "true";
        } else {
            redirigir = "false";
        }

    }

    //recuperar sub distribuidor por id benefactor / benefactor
    
    public void RecuperaSubdistribuidor(Long idbenefactor) {
        cta = facadecuenta.findByCuentaId(idbenefactor);

        if (cta != null) {
            subdistribuidor = "true";
            System.out.println("subdistribuidor" + subdistribuidor);
        } else {
            subdistribuidor = "false";

            System.out.println("no subdistribuidor" + subdistribuidor);
        }

    }

//recuperar saldo pór id benefactor/benefactor
    
    public Double recuperaSaldoSub(Long idbenefactor) {
        saldo = facadecuentamovimiento.SaldoCuentaId(idbenefactor);

        return saldo;

    }
//recuperar comision de subdistribuidor/benefactor

    public Double recuperacomisionSub(Long idbenefactor) {
        saldo = facadecuenta.SaldoComision(idbenefactor);

        return saldo;

    }

//recuperar saldo benefactor
    
    public Double recuperaSaldoPOST(Long idbenefactor) {
        saldo = facadecuentamovimiento.SaldoCuentaIdSALIDA(idbenefactor);

        return saldo;

    }

//recuperar password benefactor
    
    public void recuperarpass(String email, BigInteger documento) {
        StringBuilder err = new StringBuilder();
        System.out.println("datos recupera " + email + documento);

        facadebenefactor.cambiarPass(email, documento, err);

        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Se ha enviado una contraseña temporal a su correo electrónico");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }

//validar subdistribuidor benefactor
    
    public void validarsubdistribuidor(Long id) {
        StringBuilder err = new StringBuilder();

        totalConsulta = facadebenefactor.totalSubdistribuidor(id);
        if (totalConsulta > 0) {
            successful = true;

        } else {
            successful = false;

        }
    }

    public void submit() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //validar estado subdistribuidor benefactor
    
    public void validarEstadoSubdistribuidor(Long idbenefactor) {

        familiar = facadebenefactor.findEstadoSubdistribuidorId(idbenefactor);

        if (familiar != null) {
            subdistribuidor = "true";
            System.out.println("Activo " + subdistribuidor);
        } else {
            subdistribuidor = "false";

            System.out.println("Inactivo" + subdistribuidor);
        }
    }
    
//validar estado familiar benefactor
    
    public void validarEstadoFamiliar(Long idbenefactor) {

        familiar = facadebenefactor.findEstadoFamiliarId(idbenefactor);

        if (familiar != null) {
            vfamiliar = "true";
            System.out.println("Activo " + vfamiliar);
        } else {
            vfamiliar = "false";

            System.out.println("Inactivo" + vfamiliar);
        }
    }
    
//ver terminos benefactor
    
    public String verTerminos() {

        terminos = facadebenefactor.verTerminosyCondiciones();

        return terminos.getDescripcion();
    }

    //recaudo benefactor
    
    public List<SelectItem> BenefactoresRecaudo() {
        Integer idinforme = facesUtil.getFacesParamInteger("idinforme_");
        List<SelectItem> lstI = new ArrayList<>();
        List<Benefactor> lstT = facadebenefactor.listBenefactores();

        lstI.add(new SelectItem(null, "Seleccione una opcion..."));
        for (Benefactor b : lstT) {
            SelectItem it = new SelectItem(b.getIdbenefactor(), b.getUsername());
            lstI.add(it);
        }
        return lstI;
    }

}
