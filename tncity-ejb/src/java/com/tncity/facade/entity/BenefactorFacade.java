/**
 * @name Benefactor Facade
 *
 * @ description crud de benefactor
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.CondicionesyTerminos;
import com.tncity.config.pojoaux.EmailConfig;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Beneficiario;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Localidad;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.notificacion.NotificacionFacade;
import com.tncity.util.BeanUtil;
import com.tncity.util.HashUtil;
import static com.tncity.util.HashUtil.md5;
import com.tncity.util.JsonUtil;
import java.math.BigInteger;
import static java.nio.file.Files.list;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class BenefactorFacade extends AbstractFacade<Benefactor> {

    public static final Integer USUARIO_SYS = -1;
    public static final Integer USUARIO_ADMIN = 0;
    Integer USUARIO_SYSTEMA = -1;

    @EJB
    ParamFacade paramFacade;

    @EJB
    NotificacionFacade notificacionFacade;

    @EJB
    BenefactorFacade benefactorFacade;

    public Integer getUSUARIO_SYSTEMA() {
        return USUARIO_SYSTEMA;
    }

    public void setUSUARIO_SYSTEMA(Integer USUARIO_SYSTEMA) {
        this.USUARIO_SYSTEMA = USUARIO_SYSTEMA;
    }

    @EJB
    PersonaFacade personaFacade;

    @EJB
    UsuarioFacade usuarioFacade;

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BenefactorFacade() {
        super(Benefactor.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idbenefactor", "username", "contrasena", "updatedAt", "idpersona", 
            "idusuarioUpdated", "isactivo"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"idpersona"};
        return attrs;
    }

    @Override
    protected Benefactor parseObject(Object[] o) {

        Benefactor b = new Benefactor();
        b.setIdbenefactor((Long) o[0]);
        b.getIdpersona().setNumdocumento(new BigInteger(o[1].toString()));
        b.setUpdatedAt(new Date());
        b.getIdusuarioUpdated().setIdpersona(new Persona((Long) o[3]));
        

        return b;

    }

    // buscar benefactor por numero de documento 
    public Benefactor findBenefactorByNumdocumento(BigInteger numdocumento) {
        String hql = " SELECT b "
                + "  FROM Benefactor b "
                + " WHERE b.idpersona.numdocumento ='" + numdocumento.toString() + "' ";

        return objectFromHQL(hql);
    }

    // buscar benefactor por usuario
    public Benefactor findBenefactorByCorreo(String correo) {
        String hql = " SELECT b "
                + "  FROM Benefactor b "
                + " WHERE  b.username = '" + correo + "'";

        return objectFromHQL(hql);
    }

    // crear  benefactor 
    @Override
    public void create(Benefactor obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    // crear  benefactor nuevo
    public void createbenefactor(String nombre, String apellidos, String tipodocumento, BigInteger numdocumento,
            String telefono, String telefono2, String Ciudad, boolean value1,
            String correo, String con_correo, String contra, String con_contra, String imgcapcha,
            String captcha, StringBuilder err) {

        //0- Validando la contraseña
        if (!validaPasswords(contra, con_contra, err)) {
            return;
        }

        //validar campo terminos
        if (value1 == false) {

            err.append("Debe Aceptar los terminos y condiciones");
            return;

        }

        //validar campo correo
        if (!correo.equals(con_correo)) {
            err.append("Los correos deben ser iguales!");
            return;
        }

        //validar campo contraseña
        if (!contra.equals(con_contra)) {
            err.append("Las contraseñas deben ser iguales!");
            return;
        }

        //validar campo capcha
//        if (!imgcapcha.equals(captcha)) {
//            err.append("Usted ha digitado un capcha incorrecto vuelva a intentarlo!");
//            return;
//        }

        //Validando si el befactor existe
        Benefactor bOri = findBenefactorByNumdocumento(numdocumento);
        if (bOri != null) {
            err.append("El número de documento ").append(numdocumento).append(" ya está registrado como benefactor");
            return;
        }
        Benefactor obj = benefactorFacade.findBenefactorByNumdocumento(numdocumento);
        //Buscando si la persona existe
        Persona pOri = personaFacade.findByNumDocumento(numdocumento);
        if (pOri != null) {
            obj.setIdpersona(pOri);
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Persona p = personaFacade.findByNumDocumento(numdocumento);

            //Guardando persona
            if (pOri == null) {
                p = new Persona();
                p.setNombres(nombre);
                p.setApellidos(apellidos);
                p.setTipodocumento(tipodocumento);
                p.setNumdocumento(numdocumento);
                p.setNumTelefono(new Long(telefono));
                p.setNumTelefono2(new Long(telefono2));
                p.setEmail(correo);

                p.setUpdatedAt(new Date());
                p.setCreatedAt(new Date());
                sess.save(p);

            }

            //Guardando benefactor
            Benefactor ben = new Benefactor();
            ben.setIdpersona(new Persona(p.getIdpersona()));
            ben.setUpdatedAt(new Date());
            ben.setUsername(correo);
            ben.setConstrasena(md5(contra));
            ben.setIsActivo(true);
            ben.setIsCambiarcontrasena(false);
            ben.setIdusuarioUpdated(new Usuario(UsuarioFacade.USUARIO_SYS));

            sess.save(ben);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    public void registrarbenefactor(
            String nombre, String apellidos, String tipodocumento, BigInteger numdocumento,
            String telefono, String telefono2, String Ciudad, boolean value1,
            String correo, String con_correo, String contra, String con_contra, StringBuilder err) {
        
        System.out.println(" campos de correo 1 " +  correo + "correo igual  2 "  + con_correo);
        String tele1;
        //0- Validando la contraseña
        if (!validaPasswords(contra, con_contra, err)) {
            return;
        }

        //validar campo terminos
        if (value1 == false) {

            err.append("Para continuar debe aceptar los términos y condiciones");
            return;

        }

        //validar campo correo
//        if (!correo.equals(con_correo)) {
//            err.append("Los correos deben ser iguales!");
//            return;
//        }

        //validar campo contraseña
        if (!contra.equals(con_contra)) {
            err.append("Las contraseñas deben ser iguales!");
            return;
        }

        //validar campo capcha
//        if (!imgcapcha.equals(captcha)) {
//            err.append("!Por favor digite el texto que se ve en la imagen , "
//                    + "Por favor vuelva a recargar la imagen!");
//            return;
//        }

        //Validando si el befactor existe
        Benefactor bOri = findBenefactorByNumdocumento(numdocumento);
        if (bOri != null) {
            err.append("El usuario con número de documento ").append(numdocumento).append(" ya está registrado.");
            return;
        }

        if (telefono.equals("")) {

            tele1 = "0000000";

        } else {
            tele1 = telefono;
        }

        Benefactor corr = findBenefactorByCorreo(correo);

        if (corr != null) {
            err.append("El usuario  ").append(correo).append(" ya se encuentra  registrado.  ");
            return;

        }
        Benefactor obj = benefactorFacade.findBenefactorByNumdocumento(numdocumento);
        //Buscando si la persona existe
        Persona pOri = personaFacade.findByNumDocumento(numdocumento);
        if (pOri != null) {
            obj.setIdpersona(pOri);
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Persona p = personaFacade.findByNumDocumento(numdocumento);

            //Guardando persona
            if (pOri == null) {
                p = new Persona();
                p.setNombres(nombre);
                p.setApellidos(apellidos);
                p.setTipodocumento(tipodocumento);
                p.setNumdocumento(numdocumento);
                p.setNumTelefono(new Long(tele1));
                p.setNumTelefono2(new Long(telefono2));
                p.setEmail(con_correo);
                p.setIdusuarioCreated(new Usuario(UsuarioFacade.USUARIO_SYS));
                p.setIdciudad(new Ciudad(new Integer(Ciudad)));
                p.setUpdatedAt(new Date());
                p.setCreatedAt(new Date());
                sess.save(p);

            }

            Benefactor ben = new Benefactor();
            ben.setIdpersona(new Persona(p.getIdpersona()));
            ben.setUpdatedAt(new Date());
            ben.setUsername(correo);
            ben.setConstrasena(md5(contra));
            ben.setIsActivo(true);
            ben.setIsCambiarcontrasena(false);
            ben.setCuenta(false);
            ben.setPerfil("familiar");
            ben.setPrimerarecarga(true);

            ben.setIdusuarioUpdated(new Usuario(UsuarioFacade.USUARIO_SYS));

            sess.save(ben);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    public void registrar(Benefactor obj, boolean value1, String correo, String con_correo, String contra, String con_contra, String imgcapcha, String captcha, StringBuilder err) {

        //Buscando si la persona existe por numero documento
        Persona pOri = personaFacade.findByNumDocumento(obj.getIdpersona().getNumdocumento());

        //Buscando si la persona existe por correo
        Usuario pEmail = usuarioFacade.findByEmail(obj.getIdpersona().getEmail());

        if (pOri != null) {
            obj.setIdpersona(pOri);
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            //Guardando persona
            if (pOri == null) {
                Persona p = obj.getIdpersona();
                p.setUpdatedAt(new Date());
                p.setCreatedAt(new Date());
                sess.save(p);
            } else {

                err.append("El Usuario ya existe en el sistema");
            }

            //Guardando usuario
            if (pEmail == null) {

//                Usuario u = new Usuario();
//                u.setIdpersona(obj.getIdpersona());
//                u.setUsername(obj.getIdpersona().getEmail());
//
//                if (password.equals(password2)) {
//                    u.setContrasena(HashUtil.md5(password));
//                }
//
//                u.setUpdatedAt(new Date());
//                u.setIsActivo(true);
//
//                sess.save(u);
                //Guardando benefactor
                obj.setUpdatedAt(new Date());

                sess.save(obj);
            } else {

                err.append("El Usuario ya existe en el sistema");
            }

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    public void validar(Benefactor obj, StringBuilder err) {

        //Buscando si la persona existe por numero documento
        Persona pOri = personaFacade.findByNumDocumento(obj.getIdpersona().getNumdocumento());

        if (pOri != null) {
            obj.setIdpersona(pOri);
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            //validando  persona
            if (pOri == null) {
                err.append("false");
            } else {

                err.append("true");
            }

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    @Override
    public void edit(Benefactor obj, StringBuilder err) {
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            //actualiza la tabla benefactor
            String sql = " UPDATE benefactor "
                    + "    SET    idusuario_updated=" + obj.getIdusuarioUpdated().getIdusuario() + ","
                    + "        updated_at=now() "
                    + " WHERE idbenefactor=" + obj.getIdbenefactor();

            sess.createSQLQuery(sql).executeUpdate();

            //actualiza la tabla persona
            String sqld = " UPDATE persona   SET    "
                    + "nombres = '" + obj.getIdpersona().getNombres() + "', "
                    + " apellidos = '" + obj.getIdpersona().getApellidos() + "', "
                    + " tipodocumento = '" + obj.getIdpersona().getTipodocumento() + "', "
                    + " email= '" + obj.getIdpersona().getEmail() + "', "
                    + " direccion= '" + obj.getIdpersona().getDireccion() + "', "
                    + " num_telefono= '" + obj.getIdpersona().getNumTelefono() + "', "
                    + " num_telefono2= '" + obj.getIdpersona().getNumTelefono2() + "', "
                    + " idciudad= '" + obj.getIdpersona().getIdciudad() + "', "
                    + " idusuario_updated = '" + obj.getIdusuarioUpdated().getIdusuario() + "', "
                    + " updated_at =  now()   "
                    + " WHERE idpersona = " + obj.getIdbenefactor() + "";

            sess.createSQLQuery(sqld).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }

        endTransaction();
    }

    public void editbenefactor(long idbenefactor, Benefactor obj,String contra,String con_contra, StringBuilder err) {

            if (!validaPasswords(contra, con_contra, err)) {
            return;
        }
        beginTransaction();
        try {
            SessionImpl sess = getSess();
            //actualiza la tabla benefactor

            
            obj.setConstrasena(contra);
            
            String sql = " UPDATE benefactor "
                    + " SET  "
                    + " username ='" + obj.getIdpersona().getEmail() + "',"
                    + " constrasena = md5('" + obj.getConstrasena() + "'),"
                    + " updated_at=now() ,"
                    + " is_cambiarcontrasena=false ,"
                    + " primerarecarga=false "
                    + " WHERE idbenefactor=" + idbenefactor;
            System.out.println("actualizar benefactor " + sql);
            sess.createSQLQuery(sql).executeUpdate();

            Benefactor pers = findPersonaIdbENEFACTOR(idbenefactor);

            //actualiza la tabla persona
            String sqld = " UPDATE persona   SET    "
                    + " nombres         = '" + obj.getIdpersona().getNombres() + "', "
                    + " apellidos       = '" + obj.getIdpersona().getApellidos() + "', "
                    + " tipodocumento   = 'CEDULA', "
                    + " email           = '" + obj.getIdpersona().getEmail() + "', "
                    + " direccion       = '" + obj.getIdpersona().getDireccion() + "', "
                    + " num_telefono    = '" + obj.getIdpersona().getNumTelefono() + "', "
                    + " num_telefono2   = '" + obj.getIdpersona().getNumTelefono2() + "', "
                    + " idciudad        = '" + obj.getIdpersona().getIdciudad().getIdciudad() + "', "
                    + " updated_at      =  now()   "
                    + " WHERE idpersona = " + pers.getIdpersona().getIdpersona() + "";

            sess.createSQLQuery(sqld).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }

        endTransaction();
    }

    // borrar tabla benefactor
    @Override
    public void delete(Object idbeneficiario, StringBuilder err) {
        deleteGeneral(idbeneficiario, err);
    }

    // buscar tabla benefactor por nombre
    public Benefactor findByNomBenefactor(String nombre) {
        String hql = " SELECT * FROM benefactor"
                + " join persona on benefactor.idpersona = persona.idpersona WHERE persona.nombre='" + nombre + "'";
        return objectFromHQL(hql);
    }

    // buscar tabla benefactores
    public Benefactor findBenefactores() {
        String hql = " SELECT * FROM benefactor"
                + " join persona on benefactor.idpersona = persona.idpersona";
        return objectFromHQL(hql);
    }

    // listar  tabla benefactores
    public List<Benefactor> listBenefactores() {
        String sql = " SELECT c FROM Recaudo  c"
                + " join benefactor b on c.idbenefactor = b.idbenefactor \n"
                + " join persona p on b.idpersona = p.idpersona ";

        return findNative(sql);

    }

    // contar registros   tabla benefactores
    
    public long countBuscar(String valbusq) {
        String sql = " SELECT count(*)\n"
                + "  FROM benefactor b\n"
                + "  WHERE b.idpersona IN(  \n"
                + "	SELECT p.idpersona \n"
                + "	FROM persona p  \n"
                + "	WHERE to_tsvector(nombres||' '||apellidos||' '||numdocumento)@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n"
                + "	ORDER BY p.idpersona DESC)";
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

        // buscar  registros   tabla benefactores por  datos 
    
    public List<Benefactor> buscarFullText(String valBusq, int first, int maxRegLista) {
        String sql = " SELECT *\n"
                + "  FROM benefactor b\n"
                + "  WHERE b.idpersona IN(  \n"
                + "	SELECT p.idpersona \n"
                + "	FROM persona p  \n"
                + "	WHERE to_tsvector(nombres||' '||apellidos||' '||numdocumento)@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') \n"
                + "	ORDER BY p.idpersona DESC)"
                + " ORDER BY idbenefactor ASC ";
        return findNative(sql, first, maxRegLista, Benefactor.class);
    }

           //listar benefactores
    
    public List<Benefactor> listarBenefactor() {
        Integer maxRegLista = 25;

        String sql = " SELECT c.idpersona,c.nombres,c.apellidos FROM persona c join benefactor b on c.idpersona = b.idpersona \n"
                + " group by (c.idpersona,c.nombres,c.apellidos)";

        return findNative(sql);

    }
    
 //listar benefactores por id benefactor
    
    public Benefactor findByNumIdd(Long idnenefactor) {

        String sql = " SELECT * FROM benefactor "
                + " join persona on benefactor.idpersona = persona.idpersona "
                + " where benefactor.idbenefactor = " + idnenefactor + "";

        return objectFromSQL(sql);

    }
    
 //listar benefactores por id benefactor
    
    public Benefactor findByNumID(long id) {
        String hql = " SELECT b "
                + "  FROM Benefactor b "
                + " WHERE b.idbenefactor='" + id + "'";
        System.out.println("id benefactor " + hql);
        return objectFromHQL(hql);

    }

    public Benefactor findByNumIDBen(long id) {
        String hql = " SELECT b "
                + "  FROM Benefactor b "
                + " WHERE b.idbenefactor='" + id + "'";

        return objectFromHQL(hql);

    }
    
 //listar benefactores por cedula
    
    public Benefactor findByCedula(BigInteger doctobenefactor) {
        String hql = " SELECT b "
                + "  FROM Benefactor b "
                + " WHERE b.idpersona.numdocumento ='" + doctobenefactor + "'";

        return objectFromHQL(hql);

    }

    public List<Persona> listarBenefactor2() {
        Integer maxRegLista = 25;

        List<Persona> lstP = new ArrayList<>();
        String hql = " SELECT c.idpersona,c.nombres,c.apellidos FROM persona c join benefactor b on c.idpersona = b.idpersona \n"
                + " group by (c.idpersona,c.nombres,c.apellidos)";

        List<Object[]> lst = findGeneric(hql);

        for (Object[] obj : lst) {
            Persona p = new Persona((Long) obj[1]);

            p.setNombres((String) obj[2]);
            p.setApellidos((String) obj[3]);

            lstP.add(p);
        }

        return lstP;

    }
    
 //listar benefactores por usuario y contraseña
    
    public Benefactor buscarByLogin(String login) {
        List<Benefactor> l = findList(" SELECT u FROM Benefactor u WHERE u.username='" + login + "'");
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }
    
     //listar benefactores por usuario

    public Benefactor findUsuarioId(String userName) {

        String sql = " SELECT u FROM Benefactor u WHERE u.username='" + userName + "'";

        return objectFromHQL(sql);

    }
    
 //validar benefactores
    
    public List<Benefactor> validarbenefactor(String login) {
        List<Benefactor> l = findList("SELECT u FROM Benefactor u WHERE u.username='" + login + "'");

        return l;
    }
    
//crearcapcha

    public String crearcapcha() {
        String temporal = "";
        final char[] caracteres
                = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'e', 'h', 'i', 'j', 'l', 'k', 'm',
                    'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                    '@', '#', '!', '$', '&', '[', ']', '_', '(', ')', '?', '+', '-', '*', '/', '%', '.', '='};
        Integer longitud = 8;
        Random aleatorio = new Random();

        // Iteramos tantas veces como longitud de caracteres debe tener la contraseña
        for (int i = 0; i < longitud; i++) {
            // En cada iteracción a la cadena temporal le asignamos el carácter
            // asociado a la posición (generada aleatoriamente) del array caracteres
            temporal += caracteres[aleatorio.nextInt(caracteres.length)];
        }

        return temporal;
    }

    
    // generar clave
    
    public String genacionclave() {
        String temporal = "";
        final char[] caracteres
                = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'e', 'h', 'i', 'j', 'l', 'k', 'm',
                    'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                    '@', '#', '!', '$', '&', '[', ']', '_', '(', ')', '?'};
        Integer longitud = 8;
        Random aleatorio = new Random();

        // Iteramos tantas veces como longitud de caracteres debe tener la contraseña
        for (int i = 0; i < longitud; i++) {
            // En cada iteracción a la cadena temporal le asignamos el carácter
            // asociado a la posición (generada aleatoriamente) del array caracteres
            temporal += caracteres[aleatorio.nextInt(caracteres.length)];
        }

        return temporal;
    }
    
    // cambiar password

    public void cambiarPass(String email, BigInteger documento, StringBuilder err) {
        System.out.println("datos " + documento + email);
        Benefactor benLog = buscarByCorreo(email, documento);

        String doc = "" + documento;
        if (benLog == null) {
            err.append("ALERTA:  Usuario No Existe ! Usted no se encuentra regístrado,lo invitamos a regiśtrarse");
            return;
        }

        if (!benLog.getIsActivo()) {
            err.append("ALERTA:  Usuario Inactivo!");
            return;
        }

        if (email == "") {
            err.append("ALERTA:  Usuario No Valido ! , Por Favor diligencie su correo electrónico para recibir una contraseña ");
            return;
        }

        if (doc == "") {
            err.append("ALERTA: Usuario No Valido ! , Por Favor un numero de documento valido. ");
            return;
        }

        String clatemp = genacionclave();

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE benefactor SET constrasena=md5('" + clatemp + "') ,is_cambiarcontrasena = 'true'  WHERE idpersona=" + benLog.getIdpersona().getIdpersona();
            System.out.println("contraseña temporal " + sql);
            sess.createSQLQuery(sql).executeUpdate();


            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();

        enviarNotificacionPasswordTmp(new Persona(benLog.getIdpersona().getIdpersona()), clatemp, err);
        
    }

  /// buscar benefactor por id
    
    public Benefactor findContraByNumId(Integer idbenefactor) {

        String hql = " SELECT b FROM Benefactor b "
                + " where b.idbenefactor = " + idbenefactor + "";

        return objectFromHQL(hql);

    }

    
    // validacion de contraseñas
    
    private boolean validaPasswords(String pass1, String pass2, StringBuilder err) {

//        Integer tampassuno = pass1.length();

        Integer tampassdos = pass2.length();

//        if (tampassuno < 4) {
//            err.append("¡La contraseña debe tener como mínimo 4 caracteres!");
//            return false;
//        }

        if (tampassdos < 4) {
            err.append("¡La contraseña debe tener como minimo 4 caracteres!");
            return false;
        }

//        char clave;
//        byte contNumero = 0, contLetraMay = 0, contLetraMin = 0;
//
//        for (byte i = 0; i < pass1.length(); i++) {
//            clave = pass1.charAt(i);
//            String passValue = String.valueOf(clave);
//            if (passValue.matches("[A-Z]")) {
//                contLetraMay++;
//            } else if (passValue.matches("[a-z]")) {
//                contLetraMin++;
//            } else if (passValue.matches("[0-9]")) {
//                contNumero++;
//            }
//
//        }
//
//        if (contLetraMay == 0) {
//            err.append("¡La contraseña debe tener como mínimo 1 letra mayúscula!");
//            return false;
//        }
//
//        if (contLetraMin == 0) {
//            err.append("¡La contraseña debe tener como mínimo 1 letra Minuscula!");
//            return false;
//        }
//
//        if (contNumero == 0) {
//            err.append("¡La contraseña debe tener como mínimo  1 Numero!");
//            return false;
//        }

        char claved;
        byte contNumerod = 0, contLetraMayd = 0, contLetraMind = 0;
        for (byte i = 0; i < pass2.length(); i++) {
            claved = pass2.charAt(i);
            String passValued = String.valueOf(claved);
            if (passValued.matches("[A-Z]")) {
                contLetraMayd++;
            } else if (passValued.matches("[a-z]")) {
                contLetraMind++;
            } else if (passValued.matches("[0-9]")) {
                contNumerod++;
            }

        }

        if (contLetraMayd == 0) {
            err.append("El campo Confirmar contraseña  debe tener como minimo 1 letra Mayuscula!");
            return false;
        }

        if (contLetraMind == 0) {
            err.append("El campo Confirmar contraseña  debe tener como minimo 1 letra Minuscula!");
            return false;
        }

        if (contNumerod == 0) {
            err.append("El campo Confirmar contraseña  debe tener como minimo 1 Numero!");
            return false;
        }

        return true;
    }

    public void cambiarPassLog(Integer idbenefactor, String antcontra, String pass1, String pass2, StringBuilder err) {

        //0- Validando la contraseña
        if (!validaPasswords(pass1, pass2, err)) {
            return;
        }

        Benefactor acont = findContraByNumId(idbenefactor);
        String cant = acont.getConstrasena();

        String cifrarant = md5(antcontra);

        if (!cant.equals(cifrarant)) {
            err.append("Antigua Contraseña incorrecta. Por favor verifique los datos ingresados!");
            return;
        }

        if (!pass1.equals(pass2)) {
            err.append("Las contraseñas deben ser iguales!");
            return;
        }
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE benefactor SET constrasena=md5('" + pass1 + "') ,is_cambiarcontrasena = 'false' WHERE idbenefactor=" + acont.getIdbenefactor();

            sess.createSQLQuery(sql).executeUpdate();
            err.append("¡Usted ha cambiado su contraseña exitosamente!");
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();
    }

    public void cambiarPassId(Integer idbenefactor, String antcontra, String pass1, String pass2, StringBuilder err) {

        //0- Validando la contraseña
        if (!validaPasswords(pass1, pass2, err)) {
            return;
        }

        Benefactor acont = findContraByNumId(idbenefactor);
        String cant = acont.getConstrasena();

        String cifrarant = md5(antcontra);

        if (!cant.equals(cifrarant)) {
            err.append("Antigua Contraseña incorrecta. Por favor verifique los datos ingresados!");
            return;
        }

        if (!pass1.equals(pass2)) {
            err.append("Las contraseñas deben ser iguales!");
            return;
        }
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE benefactor SET constrasena=md5('" + pass1 + "') ,is_cambiarcontrasena = 'false' WHERE idbenefactor=" + acont.getIdbenefactor();

            sess.createSQLQuery(sql).executeUpdate();
            err.append("¡Usted ha cambiado su contraseña exitosamente!");
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();
    }

    public List<Benefactor> BuscarIdPersona(Long id) {

        String POST_PARAMS = null;
        List<Benefactor> lstR = new ArrayList<>();

        String sql = " SELECT benefactor.idbenefactor,"
                + " benefactor.idpersona,"
                + " persona.idpersona as persona,"
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento,"
                + " persona.num_telefono,"
                + " persona.numdocumento"
                + " FROM benefactor  "
                + " join persona  on benefactor.idpersona = persona.idpersona  "
                + " WHERE benefactor.idbenefactor='" + id + "' ";

        List<Object[]> lst = findGeneric(sql);
        for (Object[] o : lst) {
            Benefactor b = new Benefactor((Long) o[0]);
            b.setIdpersona(new Persona((Long) o[1]));
            b.getIdpersona().setNombres((String) o[3]);
            b.getIdpersona().setApellidos((String) o[4]);
            b.getIdpersona().setTipodocumento((String) o[5]);

            b.getIdpersona().setNumTelefono((Long) o[6]);
            b.getIdpersona().setNumdocumento(new BigInteger(o[7].toString()));

            lstR.add(b);

        }
        return lstR;

    }

    public List<Benefactor> listPorIdBenefactor(BigInteger numdoc, String tipoide) {
        String hql = " SELECT D"
                + " FROM Benefactor D "
                + " WHERE D.idpersona.tipodocumento='" + tipoide + "'"
                + " And D.idpersona.numdocumento=" + numdoc + ""
                + " ORDER BY D.idbenefactor ASC ";

        return findList(hql);
    }

    public Benefactor buscarByCorreo(String Email, BigInteger numdoc) {
        String hql = " SELECT D"
                + " FROM Benefactor D "
                + " WHERE D.username='" + Email + "'"
                + " AND D.idpersona.numdocumento='" + numdoc + "'"
                + " ORDER BY D.idbenefactor ASC ";

        return objectFromHQL(hql);

    }

    public Benefactor findByNumDocumento(String numdoc) {
        String hql = " SELECT D"
                + " FROM Benefactor D "
                + " AND D.idpersona.numdocumento='" + numdoc + "'"
                + " ORDER BY D.idbenefactor ASC ";

        return objectFromHQL(hql);

    }

    public Benefactor findByNumDocumentoD(long numdoc) {
        String hql = " SELECT D"
                + " FROM Benefactor D "
                + " WHERE D.idpersona.numdocumento='" + numdoc + "'"
                + " ORDER BY D.idbenefactor ASC ";
        System.out.println("benefactor id " + hql);
        return objectFromHQL(hql);

    }

    public Benefactor findPromoBenBYId(long numdoc) {
        String hql = " SELECT D"
                + " FROM Benefactor D "
                + " WHERE D.idpersona.numdocumento='" + numdoc + "'"
                + " ORDER BY D.idbenefactor ASC ";

        return objectFromHQL(hql);

    }

    public Benefactor findBylogId(Long idnenefactor) {

        String sql = " SELECT b FROM Benefactor b "
                + " where b.isCambiarcontrasena =  TRUE "
                + " and b.idbenefactor = " + idnenefactor + "";

        return objectFromHQL(sql);

    }

    public Benefactor RecuperaDatosBenefactor(Persona pe) {

        String sql = " SELECT b FROM Benefactor b "
                + "where b.idpersona = " + pe.getIdpersona() + "";

        return objectFromHQL(sql);
    }

    public void enviarNotificacionPasswordTmp(Persona persona, String clatemp, StringBuilder err) {

        Benefactor DatBen = RecuperaDatosBenefactor(persona);

        Persona p = personaFacade.RecuperaDatosPersona(persona);

        try {

            Benefactor b = RecuperaDatosBenefactor(persona);

            System.out.println("datos benefactor " +  b.getIdpersona().getApellidos());
            
            EmailConfig BCT = paramFacade.getParamFromCache(EmailConfig.class);

            //Params
            HashMap<String, String> params = new HashMap<>();
            params.put("@nombrebenefactor@", b.getIdpersona().getNombres());
            params.put("@apellidobenefactor@", b.getIdpersona().getNombres() + " " + b.getIdpersona().getApellidos());

            params.put("@correobenefactor@", b.getIdpersona().getEmail());
            params.put("@clavetemporal@", clatemp);

            notificacionFacade.sendNotifMail(1, b.getIdpersona().getEmail(), params, err);

            if (!err.toString().isEmpty()) {
                System.out.println("FALLA ENVIANDO EMAIL:" + err.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Benefactor findPersonaIdbENEFACTOR(long idbenefactor) {
        String hql = " SELECT b "
                + "  FROM Benefactor b "
                + " WHERE b.idbenefactor='" + idbenefactor + "'";

        return objectFromHQL(hql);
    }

    public Benefactor findIdbENEFACTOR(long idbenefactor) {
        String hql = "SELECT b "
                + "  FROM Benefactor b "
                + " WHERE b.idbenefactor='" + idbenefactor + "'";

        return objectFromHQL(hql);
    }

    public long totalSubdistribuidor(Long id) {

        String sql
                = " SELECT "
                + " count(cuenta.idcuenta)"
                + " FROM cuenta\n"
                + " join benefactor on cuenta.idbenefactor = benefactor.idbenefactor \n"
                + " WHERE benefactor.idpersona = " + id + "\n";

        return numFromSQL(sql, new BigInteger("0")).longValue();

    }

    public void createnuevobenefactor(String nombre, String apellidos, String tipodocumento, BigInteger numdocumento,
            String telefono, String telefono2, String Ciudad,
            String correo, String con_correo, String contra, String con_contra, String direccion,
            StringBuilder err) {

        //0- Validando la contraseña
        if (!validaPasswords(contra, con_contra, err)) {
            return;
        }

        //validar campo correo
        if (!correo.equals(con_correo)) {
            err.append("Los correos deben ser iguales!");
            return;
        }

        //validar campo contraseña
        if (!contra.equals(con_contra)) {
            err.append("Las contraseñas deben ser iguales!");
            return;
        }
        Integer conttelefono1 = telefono.length();
        Integer conttelefono2 = telefono2.length();
        if (conttelefono1 > 7 || conttelefono1.equals("")) {
            err.append("El Campo Telefono debe tener minimo 7 caracteres!");
            return;

        }
        //Validando si el befactor existe
        Benefactor bOri = findBenefactorByNumdocumento(numdocumento);
        if (bOri != null) {
            err.append("El número de documento ").append(numdocumento).append(" ya está registrado como benefactor");
            return;
        }

        Benefactor corr = findBenefactorByCorreo(correo);
        if (corr != null) {
            err.append("El correo electrónico ").append(correo).append(" ya está registrado.  ");
            return;
        }
        Benefactor obj = benefactorFacade.findBenefactorByNumdocumento(numdocumento);
        //Buscando si la persona existe
        Persona pOri = personaFacade.findByNumDocumento(numdocumento);
        if (pOri != null) {
            obj.setIdpersona(pOri);
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Persona p = personaFacade.findByNumDocumento(numdocumento);

            //Guardando persona
            if (pOri == null) {
                p = new Persona();
                p.setNombres(nombre);
                p.setApellidos(apellidos);
                p.setTipodocumento(tipodocumento);
                p.setNumdocumento(numdocumento);
                p.setNumTelefono(new Long(telefono));
                p.setNumTelefono2(new Long(telefono2));
                p.setEmail(correo);
                p.setIdciudad(new Ciudad(new Integer(Ciudad)));
                p.setDireccion(direccion);
                p.setUpdatedAt(new Date());
                p.setCreatedAt(new Date());
                sess.save(p);

            }

            //Guardando benefactor
            Benefactor ben = new Benefactor();
            ben.setIdpersona(new Persona(p.getIdpersona()));
            ben.setUpdatedAt(new Date());
            ben.setUsername(correo);
            ben.setConstrasena(md5(contra));
            ben.setIsActivo(true);
            ben.setIsCambiarcontrasena(false);
            ben.setCuenta(false);
            ben.setPerfil("familiar");
            ben.setIdusuarioUpdated(new Usuario(UsuarioFacade.USUARIO_SYS));

            sess.save(ben);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    public Benefactor findEstadoSubdistribuidorId(Long idbenefactor) {

        String hql = " SELECT b FROM Benefactor b  "
                + " where b.idbenefactor = " + idbenefactor + ""
                + " and b.isActivo = 'TRUE'"
                + " and b.cuenta   = 'TRUE'"
                + " and b.perfil   = 'subdistribuidor'";

        return objectFromHQL(hql);
    }

    public Benefactor findEstadoFamiliarId(Long idbenefactor) {

        String hql = " SELECT b FROM Benefactor b  "
                + " where b.idbenefactor = " + idbenefactor + ""
                + " and b.isActivo = 'TRUE'"
                + " and b.cuenta   = 'FALSE'"
                + " and b.perfil   = 'familiar'";

        return objectFromHQL(hql);
    }

    public CondicionesyTerminos verTerminosyCondiciones() {

        CondicionesyTerminos terminos = paramFacade.getParamFromCache(CondicionesyTerminos.class);
        return terminos;
    }

    public void ActualizarPromoBenefactor(Long idbenefactor, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = "UPDATE benefactor SET primerarecarga=false  WHERE idbenefactor=" + idbenefactor;

            sess.createSQLQuery(sql).executeUpdate();
            err.append("¡Usted ha cambiado su contraseña exitosamente!");
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();
    }

    public void actualizarbenefactor(Long idbenefactor) {
        StringBuilder error = new StringBuilder();

        beginTransaction();
        SessionImpl sess = getSess();

        try {

            String sql = " Update  benefactor "
                    + " SET primerarecarga = 'false'"
                    + "  WHERE idbenefactor = " + idbenefactor;

            sess.createSQLQuery(sql).executeUpdate();

        } catch (Exception e) {
            error.append("FALLA: actualisando recaudos: ").append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }

        endTransaction();

    }

    public Benefactor findBenefactorByCedula(String numdocumento) {
        String hql = " SELECT b "
                + "  FROM Benefactor b "
                + " WHERE b.idpersona.numdocumento ='" + numdocumento + "' ";

        return objectFromHQL(hql);
    }
}
