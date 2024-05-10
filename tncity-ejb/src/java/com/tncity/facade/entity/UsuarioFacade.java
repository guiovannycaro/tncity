/**
 * @name Usuario Facade
 *
 * @ description crud de usuario
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.EmailConfig;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.notificacion.NotificacionFacade;
import com.tncity.util.Cadena;
import static com.tncity.util.HashUtil.md5;
import java.math.BigInteger;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    public static final Integer USUARIO_SYS = -1;
    public static final Integer USUARIO_ADMIN = 0;
    Integer USUARIO_SYSTEMA = -1;

    public Integer getUSUARIO_SYSTEMA() {
        return USUARIO_SYSTEMA;
    }

    public void setUSUARIO_SYSTEMA(Integer USUARIO_SYSTEMA) {
        this.USUARIO_SYSTEMA = USUARIO_SYSTEMA;
    }
    @EJB
    PersonaFacade personaFacade;

    @EJB
    ParamFacade paramFacade;

    @EJB
    NotificacionFacade notificacionFacade;

    @EJB
    PermisosFacade permisosFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idusuario", "username", "idpersona.idpersona", "idpersona.nombres", 
            "idpersona.apellidos", "idpersona.numdocumento"};
        return attrs;
    }

    @Override
    protected Usuario parseObject(Object[] o) {
        Usuario u = new Usuario((Integer) o[0]);
        u.setUsername((String) o[1]);
        u.setIdpersona(new Persona(new Long(o[2].toString())));
        u.getIdpersona().setNombres((String) o[3]);
        u.getIdpersona().setApellidos((String) o[4]);
        u.getIdpersona().setNumdocumento(new BigInteger(o[5].toString()));
        return u;
    }

    //listar usuario por valor de busqueda
    
    public List<Usuario> listFullText(String query, int firstReg, int maxReg) {
        String language = "spanish";

        String sql = " SELECT u.idusuario,u.username,p.idpersona,p.nombres,p.apellidos,p.numdocumento "
                + "   FROM Usuario u, Persona p "
                + "   WHERE to_tsvector(u.username||' '||p.nombres||' '||p.apellidos||' '||p.numdocumento) @@ to_tsquery('" + language + "','" + new Cadena().reemplazaEspacios(query, "&").trim() + "')"
                + "   AND u.idpersona=p.idpersona";

        List<Object[]> lst = findNativeGeneric(sql, firstReg, maxReg);
        List<Usuario> lstU = new ArrayList<>();
        for (Object[] o : lst) {
            Usuario u = parseObject(o);
            lstU.add(u);
        }
        return lstU;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"username"};
        return attrs;
    }

    // buscar usuario por loguin
    
    public Usuario buscarByLogin(String login) {
        List<Usuario> l = findList(" SELECT u FROM Usuario u WHERE u.username='" + login + "'");
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    //buscar usuario por id usuario
    
    public Usuario findUsuarioId(String userName) {

        String sql = " SELECT u FROM Usuario u WHERE u.username ='" + userName + "'";

        return objectFromHQL(sql);

    }

    //crear usuario
    
    @Override
    public void create(Usuario obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    //editar usuario
    
    @Override
    public void edit(Usuario obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    //borrar usuario
    
    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //cambiar password de usuario
    
    public void cambiarPass(Integer idusuario, String pass1, String pass2, StringBuilder err) {

        if (!pass1.equals(pass2)) {
            err.append("Las contraseñas deben ser iguales!");
            return;
        }

        if (!validaPasswords(pass1, pass2, err)) {
            return;
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE usuario SET contrasena=md5('" + pass1 + "'),is_cambiarcontrasena = 'false' "
                    + "WHERE idusuario=" + idusuario;
            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();
    }

    //generar clave usuario
    
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

    // cambiar password usuario
    
    public void cambiarAPass(Integer idusuario, String contant, String pass1, String pass2, StringBuilder err) {

        if (!pass1.equals(pass2)) {
            err.append("Las contraseñas deben ser iguales!");
            return;
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE usuario SET contrasena=md5('" + pass1 + "'),is_cambiarcontrasena = 'false'  "
                    + " WHERE idusuario=" + idusuario;

            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();
    }

    // recuperar password usuario
    
    public void recuperarPass(String pass1, StringBuilder err) {

        Usuario usLog = buscarByCorreo(pass1);
        String clatemp = genacionclave();

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE usuario SET contrasena=md5('" + clatemp + "'),is_cambiarcontrasena = 'true'"
                    + " WHERE idusuario=" + usLog.getIdusuario();
          

            sess.createSQLQuery(sql).executeUpdate();

            enviarNotificacionPasswordTmp(usLog, clatemp, err);

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();
    }

    // buscar usuario por email
    
    public Usuario findByEmail(String Email) {
        String hql = " SELECT u FROM usuario u WHERE u.username='" + Email + "'";
        return objectFromHQL(hql);
    }

    //buscar usuario por id usuario
    
    public Usuario findUserSystem() {
        String hql = " SELECT u FROM usuario u WHERE u.idusuario='" + -1 + "'";
        return objectFromHQL(hql);
    }

    //buscar usuario por correo
    
    private Usuario buscarByCorreo(String pass1) {
        String hql = "SELECT D"
                + " FROM Usuario D "
                + " WHERE D.username='" + pass1 + "'"
                + " ORDER BY D.idusuario ASC ";

        return objectFromHQL(hql);
    }

    // enviar notificaciones de pago a usuarios
    
    public void enviarNotificacionPasswordTmp(Usuario usuario, String clatemp, StringBuilder err) {

        try {

            EmailConfig BCT = paramFacade.getParamFromCache(EmailConfig.class);

            //Params
            HashMap<String, String> params = new HashMap<>();
            params.put("@nombrebenefactor@", usuario.getIdpersona().getNombres());
            params.put("@apellidobenefactor@", usuario.getIdpersona().getNombres() + " " + usuario.getIdpersona().getApellidos());

            params.put("@correobenefactor@", usuario.getUsername());
            params.put("@clavetemporal@", clatemp);

            notificacionFacade.sendNotifMail(1, usuario.getUsername(), params, err);

            if (!err.toString().isEmpty()) {
                System.out.println("FALLA ENVIANDO EMAIL:" + err.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // validar pagos de usuarios
    
    private boolean validaPasswords(String pass1, String pass2, StringBuilder err) {

        Integer tampassuno = pass1.length();

        Integer tampassdos = pass2.length();

        if (tampassuno < 7) {
            err.append("La contraseña debe tener como minimo 8 caracteres!");
            return false;
        }

        if (tampassdos < 7) {
            err.append("La contraseña debe tener como minimo 8 caracteres!");
            return false;
        }

        char clave;
        byte contNumero = 0, contLetraMay = 0, contLetraMin = 0, contCarEsp = 0;

        for (byte i = 0; i < pass1.length(); i++) {
            clave = pass1.charAt(i);
            String passValue = String.valueOf(clave);
            if (passValue.matches("[A-Z]")) {
                contLetraMay++;
            } else if (passValue.matches("[a-z]")) {
                contLetraMin++;
            } else if (passValue.matches("[0-9]")) {
                contNumero++;
            } else if (passValue.matches("[/[`~!@#$%^&*()_°¬|+\\-=?;:'\",.<>\\{\\}\\[\\]\\\\\\/]/gi ]")) {
                contCarEsp++;
            }

        }

        if (contLetraMay == 0) {
            err.append("El campo Contraseña debe tener como minimo 1 letra Mayuscula!");
            return false;
        }

        if (contLetraMin == 0) {
            err.append("El campo Contraseña debe tener como minimo 1 letra Minuscula!");
            return false;
        }

        if (contNumero == 0) {
            err.append("El campo Contraseña  debe tener como minimo 1 Numero!");
            return false;
        }

        if (contCarEsp == 0) {
            err.append("El campo Contraseña debe tener como minimo 1 Caracter Especial! ($#_*&@.)");
            return false;
        }

        char claved;
        byte contNumerod = 0, contLetraMayd = 0, contLetraMind = 0, contCarEspd = 0;

        for (byte i = 0; i < pass2.length(); i++) {
            claved = pass2.charAt(i);
            String passValued = String.valueOf(claved);
            if (passValued.matches("[A-Z]")) {
                contLetraMayd++;
            } else if (passValued.matches("[a-z]")) {
                contLetraMind++;
            } else if (passValued.matches("[0-9]")) {
                contNumerod++;
            } else if (passValued.matches("[/[`~!@#$%^&*()_°¬|+\\-=?;:'\",.<>\\{\\}\\[\\]\\\\\\/]/gi ]")) {
                contCarEspd++;
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

        if (contCarEspd == 0) {
            err.append("El campo Confirmar contraseña  debe tener como minimo 1 Caracter Especial! ($#_*&@.) ");
            return false;
        }

        return true;
    }

    //cambiar password de usuario
    
    public void cambiarPassLog(Integer idusuario, String antcontra, String pass1, String pass2, StringBuilder err) {

        //0- Validando la contraseña
        if (!validaPasswords(pass1, pass2, err)) {
            return;
        }

        Usuario acont = findContraByNumId(idusuario);
        String cant = acont.getContrasena();

        String cifrarant = md5(antcontra);

        if (!cant.equals(cifrarant)) {
            err.append("Las contraseña temporal no esta correctamente digitada por favor intente de nuevo!");
            return;
        }

        if (!pass1.equals(pass2)) {
            err.append("Las contraseñas deben ser iguales!");
            return;
        }
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = " UPDATE usuario SET constrasena=md5('" + pass1 + "') ,is_cambiarcontrasena = 'false' WHERE idusuario=" + acont.getIdusuario();

            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();
    }

    // buscar usuario por id usuario
    
    private Usuario findContraByNumId(Integer idusuario) {
        String sql = " SELECT b FROM Usuario b "
                + " where b.idusuario = " + idusuario + "";

        return objectFromHQL(sql);
    }

    // buscar usuario por y cambio de contraseña para envio de correo temporral
    
    public Usuario findBylogId(Long idusuario) {

        String sql = " SELECT b FROM Usuario b "
                + "where b.isCambiarcontrasena =  TRUE "
                + "and b.idusuario = " + idusuario + "";
      
        return objectFromHQL(sql);

    }
    
    //crear usuario
    

    public void createusuario(Usuario obj, String pass1, String pass2, StringBuilder err) {
        if (!validaPasswords(pass1, pass2, err)) {
            return;
        }

        Usuario bOri = findUsuarioById(obj.getIdpersona().getIdpersona());
        if (bOri != null) {
            err.append("El número de documento ").append(obj.getIdpersona().getNumdocumento()).append(" ya está registrado ");
            return;
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Usuario u = findUsuarioById(obj.getIdpersona().getIdpersona());
            //Guardando Usuario
            if (u == null) {
                Usuario user = new Usuario();

                user.setIdpersona(obj.getIdpersona());
                user.setUsername(obj.getIdpersona().getEmail());
                user.setUpdatedAt(new Date());

                user.setContrasena(md5(pass1));
                user.setIsActivo(true);
                user.setIsCambiarcontrasena(false);
                sess.save(user);
            }
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }
    
    // buscar usuario por id persona 

    public Usuario findUsuarioById(Long idpersona) {

        String sql = " SELECT * FROM usuario u "
                + " join persona p on u.idpersona = p.idpersona "
                + " WHERE p.idpersona = " + idpersona + "";
      
        return objectFromSQL(sql);
    }

    //buscar usaurio por id persona
    
    public Usuario findUsuarioByIdD(String idpersona) {

        String sql = " SELECT * FROM usuario u "
                + " join persona p on u.idpersona = p.idpersona "
                + " WHERE p.idpersona = " + idpersona + "";
   
        return objectFromSQL(sql);
    }

    //recuperar usuario por persona
    
    public List<Usuario> RecuperaUsuarios() {

        String sql = " SELECT u.idusuario,u.username,p.idpersona,p.nombres,p.apellidos,p.numdocumento "
                + " FROM Usuario u"
                + " join Persona p on u.idpersona = p.idpersona";

        List<Object[]> lst = findNativeGeneric(sql);
        List<Usuario> lstU = new ArrayList<>();
        for (Object[] o : lst) {
            Usuario u = parseObject(o);
            lstU.add(u);
        }
        return lstU;
    }

    //crear usuario nuevo y darle permisos
    
    public void crearusuario(
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
            String bsseccion26,
            StringBuilder err) {

                    System.out.println ("consultar" + csseccion26  + "\n" + 
                    " insertar " + isseccion26  + "\n" + 
                    " eliminar false"  + "\n" + 
                    " editar" + esseccion26  + "\n" + 
                    " buscar " + bsseccion26  + "\n" + 
                    " modulo " + modulo26  + "\n" + 
                    " seccion " + seccion26  + "\n" + 
                    "subseccion " + sseccion26 );
                    
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Usuario pOri = findByNumID(nusuario);
            if (pOri == null) {

                Persona p = personaFacade.findPersonaById(nusuario);

                //Guardando Usuario
                String sqlu = " insert into usuario (idpersona,username,contrasena,updated_at,is_activo,is_cambiarcontrasena) "
                        + " values('" + nusuario + "','" + p.getEmail() + "','" + md5(npass1) + "','" + new Date() + "','" + nestado + "','false')";
              
                sess.createSQLQuery(sqlu).executeUpdate();

                Usuario u = findUsuarioByIdD(nusuario);

                String codusuario = "" + u.getIdusuario();
                
//crear perfiles de usuario
System.out.println("perfil de usuario b 26 " + bsseccion26);
                permisosFacade.CrearPerfilUsuario(
                        codusuario,
                        nperfil,
                        
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
                        bsseccion26,
                        err
                );
            } else {
                err.append("El Usuario ya Se Encuentra Registrado Como Administradór");
                return;
            }
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
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
            String modulo25,String modulo26,
            
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
            String seccion25, String seccion26,
            
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
            String isseccion25,  String isseccion26,
            
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
            String esseccion25, String esseccion26,
            
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
            String bsseccion25, String bsseccion26, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            Persona p = personaFacade.findPersonaById(nusuario);
            //Guardando Usuario

            String sqlu = " UPDATE  usuario SET "
                    + " username = '" + p.getEmail() + "',"
                    + " updated_at = '" + new Date() + "',is_activo = '" + nestado + "',is_cambiarcontrasena = 'false'"
                    + "  WHERE  idpersona = '" + nusuario + "'";

      
            sess.createSQLQuery(sqlu).executeUpdate();

            Usuario u = findUsuarioByIdD(nusuario);

            String codusuario = "" + u.getIdusuario();
            
//editar permisos de usuario

            permisosFacade.EditarPerfilUsuario(
                    codusuario,
                    nperfil,
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
                     modulo26,
                     
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
                    bsseccion23, bsseccion24, bsseccion25,bsseccion26,
                    err
            );

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    //buscar usuario por id persona
    
    public Usuario findPersonaById(String idpersona) {

        String sql = " SELECT * FROM usuario u "
                + " join persona p on u.idpersona = p.idpersona "
                + " WHERE p.idpersona = " + idpersona + "";
     
        return objectFromSQL(sql);
    }

    //buscar usuario por id usuario
    
    public List<Usuario> BuscarsuarioById(long idusuario, String attrOrder, String ascDesc) {

        String sql = " SELECT "
                + " usuario.idusuario,"
                + " usuario.idpersona,"
                + " persona.nombres,"
                + " persona.apellidos,"
                + " usuario.username,"
                + " usuario.contrasena,"
                + " usuario.is_activo,"
                + " usuario.is_cambiarcontrasena"
                + " from usuario "
                + " join persona on usuario.idpersona = persona.idpersona"
                + " where usuario.idusuario = '" + idusuario + "'"
                + " ORDER BY subsecciones." + attrOrder + " " + ascDesc;
     

        List<Usuario> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Usuario u = new Usuario(new Integer(o[0].toString()));
            u.setIdpersona(new Persona(new Long(o[1].toString())));
            u.getIdpersona().setNombres(o[2].toString());
            u.getIdpersona().setApellidos(o[3].toString());
            u.setUsername(o[4].toString());
            u.setContrasena(o[5].toString());
            u.setIsActivo(new Boolean(o[6].toString()));
            u.setIsCambiarcontrasena(new Boolean(o[7].toString()));
            lstR.add(u);

        }
        return lstR;

    }

    
    //identificar usuario por id persona
    
    public Usuario identificaUsuario(String idpersona, StringBuilder err) {
        String hql = " SELECT  U FROM Usuario U"
                + " WHERE U.idpersona='" + idpersona + "'";
       
        return objectFromHQL(hql);
    }
    
    //buscar usario por id persona

    public Usuario findByNumID(String nusuario) {
        String hql = " SELECT  U FROM Usuario U"
                + " WHERE U.idpersona='" + nusuario + "'";
       
        return objectFromHQL(hql);
    }
}
