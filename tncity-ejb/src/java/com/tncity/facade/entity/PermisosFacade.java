/**
 * @name Permisos Facade
 *
 * @ description crud de permisos
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.config.ParamFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Modulos;
import com.tncity.jpa.pojo.Perfil;
import com.tncity.jpa.pojo.Permisos;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Secciones;
import com.tncity.jpa.pojo.Subsecciones;
import com.tncity.jpa.pojo.Usuario;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import javax.ejb.EJB;
import com.tncity.jpa.pojo.Moduloxseccionxsubseccion;
import org.hibernate.impl.SessionImpl;

@Stateless
public class PermisosFacade extends AbstractFacade<Permisos> {

    @PersistenceUnit
    private EntityManagerFactory emf;
    
     @EJB
    ParamFacade paramFacade;
     
     @EJB
    ModuloXSeccionXsubseccionesFacade moduloseccionFacade;
     
      List<Moduloxseccionxsubseccion> lstmodulosubseccion;

    public List<Moduloxseccionxsubseccion> getLstmodulosubseccion() {
        return lstmodulosubseccion;
    }

    public void setLstmodulosubseccion(List<Moduloxseccionxsubseccion> lstmodulosubseccion) {
        this.lstmodulosubseccion = lstmodulosubseccion;
    }
      
      

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PermisosFacade() {
        super(Permisos.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idbarrio", "nombre", "idlocalidad.idlocalidad", "idlocalidad.nombre", "idlocalidad.idciudad.idciudad", "idlocalidad.idciudad.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Permisos parseObject(Object[] o) {
        Permisos b = new Permisos((Integer) o[0]);
        b.setIdpermiso((Integer) o[1]);
        b.setIdusuario(new Usuario((Integer) o[2]));
        b.setIdperfil(new Perfil((Integer) o[2]));
        b.setIdmodulo(new Modulos((Integer) o[2]));
        b.setIdseccion(new Secciones((Integer) o[2]));
        b.setIdsubsecccion(new Subsecciones((Integer) o[2]));
        b.setConsulta(true);
        b.setIngreso(true);
        b.setEliminacion(true);
        b.setEdicion(true);
        b.setBuscar(true);
        return b;
    }

    // crear permisos
    
    @Override
    public void create(Permisos obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    //editar permisos
    
    @Override
    public void edit(Permisos obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    //borrar permisos
    
    @Override
    public void delete(Object idbarrio, StringBuilder err) {
        deleteGeneral(idbarrio, err);
    }



//crear permisos usuario

    public void CrearPerfilUsuario(
            String nusuario,
            String nperfil,
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
            String modulo25, String modulo26,
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
            String sseccion25, String sseccion26,
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
            String csseccion25, String csseccion26,
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
            String isseccion25, String isseccion26,
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
            
            lstmodulosubseccion =    moduloseccionFacade.ListaSecciones();
              
            for (Moduloxseccionxsubseccion mssb : lstmodulosubseccion) {
                
                System.out.println(" listado modulos " + mssb.getIdmodulo().getIdmodulo() + " listado seccion " + mssb.getIdseccion().getIdseccion() + " listado subseccion " + mssb.getIdsubseccion().getIdsubseccion()); 
     
            }
   
            String sql1 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'1','1','1','" + csseccion1 + "','" + isseccion1 + "','false','" + esseccion1 + "','" + bsseccion1 + "',"
                    + "'" + modulo1 + "','" + seccion1 + "','" + sseccion1 + "')";
            System.out.println("consulta 1 " + sql1);
            sess.createSQLQuery(sql1).executeUpdate();

            String sql2 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'1','2','2','" + csseccion2 + "','" + isseccion2 + "','false','" + esseccion2 + "','" + bsseccion2 + "',"
                    + "'" + modulo2 + "','" + seccion2 + "','" + sseccion2 + "')";
            System.out.println("consulta 2 " + sql2);
            sess.createSQLQuery(sql2).executeUpdate();

            String sql3 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'1','2','3',"
                    + "'" + csseccion3 + "','" + isseccion3 + "','false','" + esseccion3 + "','" + bsseccion3 + "',"
                    + "'" + modulo3 + "','" + seccion3 + "','" + sseccion3 + "')";
            System.out.println("consulta 3" + sql3);
            sess.createSQLQuery(sql3).executeUpdate();

            String sql4 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion) values('" + nusuario + "','" + nperfil + "',"
                    + "'1','2','4',"
                    + "'" + csseccion4 + "','" + isseccion4 + "','false','" + esseccion4 + "','" + bsseccion4 + "',"
                    + "'" + modulo4 + "','" + seccion4 + "','" + sseccion4 + "')";
            System.out.println("consulta 4 " + sql4);
            sess.createSQLQuery(sql4).executeUpdate();

            String sql5 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'1 ','3','5',"
                    + "'" + csseccion5 + "','" + isseccion5 + "','false','" + esseccion5 + "','" + bsseccion5 + "',"
                    + "'" + modulo5 + "','" + seccion5 + "','" + sseccion5 + "')";
            System.out.println("consulta 5 " + sql5);
            sess.createSQLQuery(sql5).executeUpdate();

            String sql6 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'1','4','6',"
                    + "'" + csseccion6 + "','" + isseccion6 + "','false','" + esseccion6 + "','" + bsseccion6 + "',"
                    + "'" + modulo6 + "','" + seccion6 + "','" + sseccion6 + "')";
            System.out.println("consulta 6 " + sql6);
            sess.createSQLQuery(sql6).executeUpdate();

            String sql7 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'1','4','7',"
                    + "'" + csseccion7 + "','" + isseccion7 + "','false','" + esseccion7 + "','" + bsseccion7 + "',"
                    + "'" + modulo7 + "','" + seccion7 + "','" + sseccion7 + "')";
            System.out.println("consulta 7 " + sql7);
            sess.createSQLQuery(sql7).executeUpdate();

            String sql8 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','5','8',"
                    + "'" + csseccion8 + "','" + isseccion8 + "','false','" + esseccion8 + "','" + sseccion8 + "',"
                    + "'" + modulo8 + "','" + seccion8 + "','" + sseccion8 + "')";
            System.out.println("consulta 8 " + sql8);
            sess.createSQLQuery(sql8).executeUpdate();

            String sql9 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','6','9',"
                    + "'" + csseccion9 + "','" + isseccion9 + "','false','" + esseccion9 + "','" + bsseccion9 + "',"
                    + "'" + modulo9 + "','" + seccion9 + "','" + sseccion9 + "')";
            System.out.println("consulta 9 " + sql9);
            sess.createSQLQuery(sql9).executeUpdate();

            String sql10 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion) values('" + nusuario + "','" + nperfil + "',"
                    + "'2','6','10',"
                    + "'" + csseccion10 + "','" + isseccion10 + "','false','" + esseccion10 + "','" + bsseccion10 + "',"
                    + "'" + modulo10 + "','" + seccion10 + "','" + sseccion10 + "')";
            System.out.println("consulta 10 " + sql10);
            sess.createSQLQuery(sql10).executeUpdate();

            String sql11 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','6','11',"
                    + "'" + csseccion11 + "','" + isseccion11 + "','false','" + esseccion11 + "','" + bsseccion11 + "',"
                    + "'" + modulo11 + "','" + seccion11 + "','" + sseccion11 + "')";
            System.out.println("consulta 11" + sql11);
            sess.createSQLQuery(sql11).executeUpdate();

            String sql12 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','6','12',"
                    + "'" + csseccion12 + "','" + isseccion12 + "','false','" + esseccion12 + "','" + bsseccion12 + "',"
                    + "'" + modulo12 + "','" + seccion12 + "','" + sseccion12 + "')";
            System.out.println("consulta 12" + sql12);
            sess.createSQLQuery(sql12).executeUpdate();

            String sql13 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','7','13',"
                    + "'" + csseccion13 + "','" + isseccion13 + "','false','" + esseccion13 + "','" + bsseccion13 + "',"
                    + "'" + modulo13 + "','" + seccion13 + "','" + sseccion13 + "')";
            System.out.println("consulta 13" + sql13);
            sess.createSQLQuery(sql13).executeUpdate();

            String sql14 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','7','14',"
                    + "'" + csseccion14 + "','" + isseccion14 + "','false','" + esseccion14 + "','" + bsseccion14 + "',"
                    + "'" + modulo14 + "','" + seccion14 + "','" + sseccion14 + "')";
            System.out.println("consulta 14" + sql14);
            sess.createSQLQuery(sql14).executeUpdate();

            String sql15 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2 ','7','15',"
                    + "'" + csseccion15 + "','" + isseccion15 + "','false','" + esseccion15 + "','" + bsseccion15 + "',"
                    + "'" + modulo15 + "','" + seccion15 + "','" + sseccion15 + "')";
            System.out.println("consulta 15" + sql15);
            sess.createSQLQuery(sql15).executeUpdate();

            String sql16 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','7','16','" + csseccion16 + "',"
                    + "'" + isseccion16 + "','false','" + esseccion16 + "','" + bsseccion16 + "',"
                    + "'" + modulo16 + "','" + seccion16 + "','" + sseccion16 + "')";
            System.out.println("consulta 16" + sql16);
            sess.createSQLQuery(sql16).executeUpdate();

            String sql17 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','8','17','" + csseccion17 + "',"
                    + "'" + isseccion17 + "','false','" + esseccion17 + "','" + bsseccion17 + "',"
                    + "'" + modulo17 + "','" + seccion17 + "','" + sseccion17 + "')";
            System.out.println("consulta 17" + sql17);
            sess.createSQLQuery(sql17).executeUpdate();

            String sql18 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','8','18 ','" + csseccion18 + "',"
                    + "'" + isseccion18 + "','false','" + esseccion18 + "','" + bsseccion18 + "',"
                    + "'" + modulo18 + "','" + seccion18 + "','" + sseccion18 + "')";
            System.out.println("consulta 18" + sql18);
            sess.createSQLQuery(sql18).executeUpdate();

            String sql19 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','8','19','" + csseccion19 + "',"
                    + "'" + isseccion19 + "','false','" + esseccion19 + "','" + bsseccion19 + "',"
                    + "'" + modulo19 + "','" + seccion19 + "','" + sseccion19 + "')";
            System.out.println("consulta 19" + sql19);
            sess.createSQLQuery(sql19).executeUpdate();

            String sql20 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'3','12','20',"
                    + "'" + csseccion20 + "','" + isseccion20 + "','false','" + esseccion20 + "','" + bsseccion20 + "',"
                    + "'" + modulo20 + "','" + seccion20 + "','" + sseccion20 + "')";
            System.out.println("consulta 20" + sql20);
            sess.createSQLQuery(sql20).executeUpdate();

            String sql21 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'4','9','21',"
                    + "'" + csseccion21 + "','" + isseccion21 + "','false','" + esseccion21 + "','" + bsseccion21 + "',"
                    + "'" + modulo21 + "','" + seccion21 + "','" + sseccion21 + "')";
            System.out.println("consulta 21" + sql21);
            sess.createSQLQuery(sql21).executeUpdate();

            String sql22 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'4','10','22','" + csseccion22 + "',"
                    + "'" + isseccion22 + "','false','" + esseccion22 + "','" + bsseccion22 + "',"
                    + "'" + modulo22 + "','" + seccion22 + "','" + sseccion22 + "')";
            System.out.println("consulta 22" + sql22);
            sess.createSQLQuery(sql22).executeUpdate();

            String sql23 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'5','11','23','" + csseccion23 + "',"
                    + "'" + isseccion23 + "','false','" + esseccion23 + "','" + bsseccion23 + "',"
                    + "'" + modulo23 + "','" + seccion23 + "','" + sseccion23 + "')";
            System.out.println("consulta 23" + sql23);
            sess.createSQLQuery(sql23).executeUpdate();

            String sql24 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'1','13','24','" + csseccion24 + "',"
                    + "'" + isseccion24 + "','false','" + esseccion24 + "','" + bsseccion24 + "',"
                    + "'" + modulo24 + "','" + seccion24 + "','" + sseccion24 + "')";
            System.out.println("consulta 24" + sql24);
            sess.createSQLQuery(sql24).executeUpdate();

            String sql25 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'1','7','25','" + csseccion25 + "',"
                    + "'" + isseccion25 + "','false','" + esseccion25 + "','" + bsseccion25 + "',"
                    + "'" + modulo25 + "','" + seccion25 + "','" + sseccion25 + "')";
            System.out.println("consulta 25" + sql25);
            sess.createSQLQuery(sql25).executeUpdate();

            System.out.println (
                    "consultar" + csseccion26  + "\n" + 
                    " insertar " + isseccion26  + "\n" + 
                    " eliminar false"  + "\n" + 
                    " editar" + esseccion26  + "\n" + 
                    " buscar " + bsseccion26  + "\n" + 
                    " modulo " + modulo26  + "\n" + 
                    " seccion " + seccion26  + "\n" + 
                    "subseccion " + sseccion26 );
            
            String sql26 = "insert into permisos (idusuario,idperfil,idmodulo,idseccion,idsubsecccion,consulta,ingreso,eliminacion,edicion,buscar,modulo,seccion,subseccion)  values('" + nusuario + "','" + nperfil + "',"
                    + "'2','8','26','" + csseccion26 + "',"
                    + "'" + isseccion26 + "','false','" + esseccion26 + "','" + bsseccion26 + "',"
                    + "'" + modulo26 + "','" + seccion26 + "','" + sseccion26 + "')";
            System.out.println("consulta 26" + sql26);
            sess.createSQLQuery(sql26).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }

    //editar permisos
    
    public void EditarPerfilUsuario(String nusuario,
            String nperfil,
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
            String modulo25, String modulo26,
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
            String sseccion25, String sseccion26,
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
            String csseccion24, String csseccion25, String csseccion26,
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
            String isseccion25, String isseccion26,
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
            String bsseccion24, String bsseccion25,
            String bsseccion26, StringBuilder err) {

        beginTransaction();
        try {
            SessionImpl sess = getSess();
            
              lstmodulosubseccion =    moduloseccionFacade.ListaSecciones();
              
            for (Moduloxseccionxsubseccion mssb : lstmodulosubseccion) {
                
                System.out.println(" listado modulos " + mssb.getIdmodulo().getIdmodulo() + " listado seccion " + mssb.getIdseccion().getIdseccion() + " listado subseccion " + mssb.getIdsubseccion().getIdsubseccion()); 

            }
            
            String sql1 = " UPDATE  permisos SET "
                    + " consulta = '" + csseccion1 + "',"
                    + " ingreso = '" + isseccion1 + "',eliminacion = 'false',edicion = '" + esseccion1 + "',"
                    + " buscar = '" + bsseccion1 + "',modulo = '" + modulo1 + "',seccion = '" + seccion1 + "',"
                    + " subseccion = '" + sseccion1 + "' "
                    + "WHERE idmodulo = 1 and idseccion = 1 and idsubsecccion = 1 and"
                    + " idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "' ";
            System.out.println("consulta 1 " + sql1);
            sess.createSQLQuery(sql1).executeUpdate();

            String sql2 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion2 + "',ingreso = '" + isseccion2 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion2 + "',buscar = '" + bsseccion2 + "',"
                    + " modulo = '" + modulo2 + "',seccion = '" + seccion2 + "',subseccion =  '" + sseccion2 + "'"
                    + " WHERE idmodulo = 1 and idseccion = 2 and idsubsecccion = 2 and"
                    + " idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 2 " + sql2);
            sess.createSQLQuery(sql2).executeUpdate();

            String sql3 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion3 + "',ingreso = '" + isseccion3 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion3 + "',buscar = '" + bsseccion3 + "',"
                    + " modulo = '" + modulo3 + "',seccion = '" + seccion3 + "',subseccion =  '" + sseccion3 + "'"
                    + " WHERE idmodulo = 1 and idseccion = 2 and idsubsecccion = 3 and "
                    + " idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 3 " + sql3);
            sess.createSQLQuery(sql3).executeUpdate();

            String sql4 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion4 + "',ingreso = '" + isseccion4 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion4 + "',buscar = '" + bsseccion4 + "',"
                    + " modulo = '" + modulo4 + "',seccion = '" + seccion4 + "',subseccion =  '" + sseccion4 + "'"
                    + " WHERE idmodulo = 1 and idseccion = 2 and idsubsecccion = 4 and"
                    + " idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 4 " + sql4);
            sess.createSQLQuery(sql4).executeUpdate();

            String sql5 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion5 + "',ingreso = '" + isseccion5 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion5 + "',buscar = '" + bsseccion5 + "',"
                    + " modulo = '" + modulo5 + "',seccion = '" + seccion5 + "',subseccion =  '" + sseccion5 + "'"
                    + " WHERE idmodulo = 1 and idseccion = 3 and idsubsecccion = 5 "
                    + " and  idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 5 " + sql5);
            sess.createSQLQuery(sql5).executeUpdate();

            String sql6 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion6 + "',ingreso = '" + isseccion6 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion6 + "',buscar = '" + bsseccion6 + "',"
                    + " modulo = '" + modulo6 + "',seccion = '" + seccion6 + "',subseccion =  '" + sseccion6 + "'"
                    + " WHERE idmodulo = 1 and idseccion = 4 and idsubsecccion = 6 and "
                    + " idusuario = '" + nusuario + "' and  idperfil = '" + nperfil + "'";
            System.out.println("consulta 6 " + sql6);
            sess.createSQLQuery(sql6).executeUpdate();

            String sql7 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion7 + "',ingreso = '" + isseccion7 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion7 + "',buscar = '" + bsseccion7 + "',"
                    + " modulo = '" + modulo7 + "',seccion = '" + seccion7 + "',subseccion =  '" + sseccion7 + "'"
                    + " WHERE idmodulo = 1 and idseccion = 4 and idsubsecccion = 7 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 7 " + sql7);
            sess.createSQLQuery(sql7).executeUpdate();

            String sql8 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion8 + "',ingreso = '" + isseccion8 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion8 + "',buscar = '" + bsseccion8 + "',"
                    + " modulo = '" + modulo8 + "',seccion = '" + seccion8 + "',subseccion =  '" + sseccion8 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 5 and idsubsecccion = 8 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 8 " + sql8);
            sess.createSQLQuery(sql8).executeUpdate();

            String sql9 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion9 + "',ingreso = '" + isseccion9 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion9 + "',buscar = '" + bsseccion9 + "',"
                    + " modulo = '" + modulo9 + "',seccion = '" + seccion9 + "',subseccion =  '" + sseccion9 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 6 and idsubsecccion = 9 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 9 " + sql9);
            sess.createSQLQuery(sql9).executeUpdate();

            String sql10 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion10 + "',ingreso = '" + isseccion10 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion10 + "',buscar = '" + bsseccion10 + "',"
                    + " modulo = '" + modulo10 + "',seccion = '" + seccion10 + "',subseccion =  '" + sseccion10 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 6 and idsubsecccion = 10 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 10 " + sql10);
            sess.createSQLQuery(sql10).executeUpdate();

            String sql11 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion11 + "',ingreso = '" + isseccion11 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion11 + "',buscar = '" + bsseccion11 + "',"
                    + " modulo = '" + modulo11 + "',seccion = '" + seccion11 + "',subseccion =  '" + sseccion11 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 6 and idsubsecccion = 11"
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 11 " + sql11);
            sess.createSQLQuery(sql11).executeUpdate();

            String sql12 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion12 + "',ingreso = '" + isseccion12 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion12 + "',buscar = '" + bsseccion12 + "',"
                    + " modulo = '" + modulo12 + "',seccion = '" + seccion12 + "',subseccion =  '" + sseccion12 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 6 and idsubsecccion = 12 "
                    + "  and idusuario = '" + nusuario + "'  and idperfil = '" + nperfil + "'";
               System.out.println("consulta 12 " + sql12);
            sess.createSQLQuery(sql12).executeUpdate();

            String sql13 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion13 + "',ingreso = '" + isseccion13 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion13 + "',buscar = '" + bsseccion13 + "',"
                    + " modulo = '" + modulo13 + "',seccion = '" + seccion13 + "',subseccion =  '" + sseccion13 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 7 and idsubsecccion = 13 "
                    + " and idusuario = '" + nusuario + "' and  idperfil = '" + nperfil + "'";
             System.out.println("consulta 13 " + sql13);
            sess.createSQLQuery(sql13).executeUpdate();

            String sql14 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion14 + "',ingreso = '" + isseccion14 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion14 + "',buscar = '" + bsseccion14 + "',"
                    + " modulo = '" + modulo14 + "',seccion = '" + seccion14 + "',subseccion =  '" + sseccion14 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 7 and idsubsecccion = 14 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
              System.out.println("consulta 14 " + sql14);
            sess.createSQLQuery(sql14).executeUpdate();

            String sql15 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion15 + "',ingreso = '" + isseccion15 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion15 + "',buscar = '" + bsseccion15 + "',"
                    + " modulo = '" + modulo15 + "',seccion = '" + seccion15 + "',subseccion =  '" + sseccion15 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 7 and idsubsecccion = 15 "
                    + " and  idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 15 " + sql15);
            sess.createSQLQuery(sql15).executeUpdate();

            String sql16 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion16 + "',ingreso = '" + isseccion16 + "',"
                    + " eliminacion = 'false',"
                    + " edicion = '" + esseccion16 + "',buscar = '" + bsseccion16 + "',"
                    + " modulo = '" + modulo16 + "',seccion = '" + seccion16 + "',"
                    + " subseccion =  '" + sseccion16 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 7 and idsubsecccion = 16 "
                    + " and  idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 16 " + sql16);
            sess.createSQLQuery(sql16).executeUpdate();

            String sql17 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion17 + "',ingreso = '" + isseccion17 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion17 + "',buscar = '" + bsseccion17 + "',"
                    + " modulo = '" + modulo17 + "',seccion = '" + seccion17 + "',subseccion =  '" + sseccion17 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 8 and idsubsecccion = 17 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
              System.out.println("consulta 17 " + sql17);
            sess.createSQLQuery(sql17).executeUpdate();

            String sql18 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion18 + "',ingreso = '" + isseccion18 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion18 + "',buscar = '" + bsseccion18 + "',"
                    + " modulo = '" + modulo18 + "',seccion = '" + seccion18 + "',subseccion =  '" + sseccion18 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 8 and idsubsecccion = 18 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
             System.out.println("consulta 18 " + sql18);
            sess.createSQLQuery(sql18).executeUpdate();

            String sql19 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion19 + "',ingreso = '" + isseccion19 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion19 + "',buscar = '" + bsseccion19 + "',"
                    + " modulo = '" + modulo19 + "',seccion = '" + seccion19 + "',subseccion =  '" + sseccion19 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 8 and idsubsecccion = 19 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
           System.out.println("consulta 19 " + sql19);
            sess.createSQLQuery(sql19).executeUpdate();

            String sql20 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion20 + "',ingreso = '" + isseccion20 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion20 + "',buscar = '" + bsseccion20 + "',"
                    + " modulo = '" + modulo20 + "',seccion = '" + seccion20 + "',subseccion =  '" + sseccion20 + "'"
                    + " WHERE idmodulo = 3 and idseccion = 12 and idsubsecccion = 20 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 20 " + sql20);
            sess.createSQLQuery(sql20).executeUpdate();

            String sql21 = " UPDATE permisos SET"
                    + " consulta = '" + csseccion21 + "',ingreso = '" + isseccion21 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion21 + "',buscar = '" + bsseccion21 + "',"
                    + " modulo = '" + modulo21 + "',seccion = '" + seccion21 + "',subseccion =  '" + sseccion21 + "'"
                    + " WHERE idmodulo = 4 and idseccion = 9 and idsubsecccion = 21 "
                    + " and  idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
            System.out.println("consulta 21" + sql21);
            sess.createSQLQuery(sql21).executeUpdate();

            String sql22 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion22 + "',ingreso = '" + isseccion22 + "',eliminacion = 'false',"
                    + " edicion = '" + esseccion22 + "',buscar = '" + bsseccion22 + "',"
                    + " modulo = '" + modulo22 + "',seccion = '" + seccion22 + "',subseccion =  '" + sseccion22 + "'"
                    + " WHERE idmodulo = 4 and idseccion = 10 and idsubsecccion = 22 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
             System.out.println("consulta 22 " + sql22);
            sess.createSQLQuery(sql22).executeUpdate();

            String sql23 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion23 + "',"
                    + " ingreso = '" + isseccion23 + "',"
                    + " eliminacion = 'false',"
                    + " edicion = '" + esseccion23 + "',"
                    + " buscar = '" + bsseccion23 + "',"
                    + " modulo = '" + modulo23 + "',"
                    + " seccion = '" + seccion23 + "',"
                    + " subseccion =  '" + sseccion23 + "'"
                    + " WHERE idmodulo = 5 and idseccion = 11 and idsubsecccion = 23 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
  System.out.println("consulta 23 " + sql23);
            sess.createSQLQuery(sql23).executeUpdate();

            String sql24 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion24 + "',"
                    + " ingreso = '" + isseccion24 + "',"
                    + " eliminacion = 'false',"
                    + " edicion = '" + esseccion24 + "',"
                    + " buscar = '" + bsseccion24 + "',"
                    + " modulo = '" + modulo24 + "',"
                    + " seccion = '" + seccion24 + "',"
                    + " subseccion =  '" + sseccion24 + "'"
                    + " WHERE idmodulo = 1 and idseccion = 13 and idsubsecccion = 24 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
  System.out.println("consulta 24 " + sql24);
            sess.createSQLQuery(sql24).executeUpdate();

            String sql25 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion25 + "',"
                    + " ingreso = '" + isseccion25 + "',"
                    + " eliminacion = 'false',"
                    + " edicion = '" + esseccion25 + "',"
                    + " buscar = '" + bsseccion25 + "',"
                    + " modulo = '" + modulo25 + "',"
                    + " seccion = '" + seccion25 + "',"
                    + " subseccion =  '" + sseccion25 + "'"
                    + " WHERE idmodulo = 1 and idseccion = 7 and idsubsecccion = 25 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
  System.out.println("consulta 25  " + sql25);
            sess.createSQLQuery(sql25).executeUpdate();
            
              String sql26 = " UPDATE permisos SET "
                    + " consulta = '" + csseccion26 + "',"
                    + " ingreso = '" + isseccion26 + "',"
                    + " eliminacion = 'false',"
                    + " edicion = '" + esseccion26 + "',"
                    + " buscar = '" + bsseccion26 + "',"
                    + " modulo = '" + modulo26 + "',"
                    + " seccion = '" + seccion26 + "',"
                    + " subseccion =  '" + sseccion26 + "'"
                    + " WHERE idmodulo = 2 and idseccion = 8 and idsubsecccion = 26 "
                    + " and idusuario = '" + nusuario + "' and idperfil = '" + nperfil + "'";
  System.out.println("consulta 26  " + sql26);
            sess.createSQLQuery(sql26).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }
    
//listado de permisos
    
    public List<Permisos> TodosLosPermisos(String idusuario, String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " permisos.idpermiso ,"
                + " permisos.idusuario ,"
                + " permisos.idperfil ,"
                + " permisos.idmodulo,"
                + " modulos.nombre as nombre_modulo,"
                + " secciones.idseccion,"
                + " secciones.nombre as nombre_seccion,"
                + " permisos.idsubsecccion,"
                + " subsecciones.nombre as nombre_subseccion,"
                + " subsecciones.nombrevisual, "
                + " subsecciones.estado, "
                + " permisos.consulta,"
                + " permisos.ingreso,"
                + " permisos.eliminacion,"
                + " permisos.edicion,"
                + " permisos.buscar,"
                + " permisos.modulo,"
                + " permisos.seccion,"
                + " permisos.subseccion "
                + " from permisos "
                + " join  modulos on permisos.idmodulo = modulos.idmodulo"
                + " join  secciones on permisos.idseccion = secciones.idseccion"
                + " join  subsecciones on permisos.idsubsecccion = subsecciones.idsubseccion"
                + " where permisos.idusuario = '" + idusuario + "'"
                + " ORDER BY permisos." + attrOrder + " " + ascDesc;

        List<Permisos> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Permisos p = new Permisos(new Integer(o[0].toString()));

            p.setIdusuario(new Usuario(new Integer(o[1].toString())));
            p.setIdperfil(new Perfil(new Integer(o[2].toString())));
            p.setIdmodulo(new Modulos(new Integer(o[3].toString())));
            p.getIdmodulo().setNombre(o[4].toString());

            p.setIdseccion(new Secciones(new Integer(o[5].toString())));
            p.getIdseccion().setNombre(o[6].toString());
            p.setIdsubsecccion(new Subsecciones(new Integer(o[7].toString())));
            p.getIdsubsecccion().setNombre(o[8].toString());
            p.getIdsubsecccion().setNombrevisual(o[9].toString());
            p.getIdsubsecccion().setEstado(new Boolean(o[10].toString()));

            p.setConsulta(new Boolean(o[11].toString()));
            p.setIngreso(new Boolean(o[12].toString()));
            p.setEliminacion(new Boolean(o[13].toString()));
            p.setEdicion(new Boolean(o[14].toString()));
            p.setBuscar(new Boolean(o[15].toString()));
            p.setModulo(new Boolean(o[16].toString()));
            p.setSeccion(new Boolean(o[17].toString()));
            p.setSubseccion(new Boolean(o[18].toString()));

            lstR.add(p);

        }
        return lstR;
    }

    //buscar permiso por idusuario
    
    public List<Permisos> BuscarsuarioById(String idusuario, String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " permisos.idpermiso ,"
                + " permisos.idusuario ,"
                + " usuario.username,"
                + " usuario.contrasena,"
                + " usuario.is_activo,"
                + " usuario.is_cambiarcontrasena,"
                + " usuario.idpersona,"
                + " persona.nombres,"
                + " persona.apellidos,"
                + " permisos.idperfil ,"
                + " perfil.nombre as nombre_perfil "
                + " from permisos "
                + " join  usuario on permisos.idusuario = usuario.idusuario"
                + " join  persona on usuario.idpersona = persona.idpersona"
                + " join  perfil on permisos.idperfil = perfil.idperfil"
                + " where permisos.idusuario = '" + idusuario + "'"
                + " ORDER BY permisos." + attrOrder + " "
                + " LIMIT 1";

        List<Permisos> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Permisos p = new Permisos(new Integer(o[0].toString()));

            p.setIdusuario(new Usuario(new Integer(o[1].toString())));
            p.getIdusuario().setUsername(o[2].toString());
            p.getIdusuario().setContrasena(o[3].toString());
            p.getIdusuario().setIsActivo(new Boolean(o[4].toString()));
            p.getIdusuario().setIsCambiarcontrasena(new Boolean(o[5].toString()));

            p.getIdusuario().setIdpersona(new Persona(new Long(o[6].toString())));
            p.getIdusuario().getIdpersona().setNombres(o[7].toString());
            p.getIdusuario().getIdpersona().setApellidos(o[8].toString());
            p.setIdperfil(new Perfil(new Integer(o[9].toString())));
            p.getIdperfil().setNombre(o[10].toString());
            lstR.add(p);

        }
        return lstR;
    }

    //persos por modulos
    
    public List<Permisos> ModulosPermisos(String idusuario, String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " permisos.idmodulo ,"
                + " permisos.modulo ,"
                + " modulos.nombre ,"
                + " modulos.imagen,"
                + " modulos.nombrevisual ,"
                + " modulos.url,"
                + " modulos.estado"
                + " from permisos "
                + " join modulos on permisos.idmodulo = modulos.idmodulo"
                + " where permisos.idusuario = '" + idusuario + "' and "
                + " modulos.estado = 'true' and permisos.modulo = 'true'"
                + " group by(permisos.idmodulo,permisos.modulo,modulos.nombre,modulos."
                + "imagen,modulos.url,modulos.nombrevisual,modulos.estado)";

        List<Permisos> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Permisos p = new Permisos();

            p.setIdmodulo(new Modulos(new Integer(o[0].toString())));
            p.setModulo(new Boolean(o[1].toString()));
            p.getIdmodulo().setNombre(o[2].toString());
            p.getIdmodulo().setImagen(o[3].toString());
            p.getIdmodulo().setNombrevisual(o[4].toString());
            p.getIdmodulo().setUrl(o[5].toString());

            p.getIdmodulo().setEstado(new Boolean(o[6].toString()));

            lstR.add(p);

        }
        return lstR;
    }
    
//buscar permisos por sseccion de usuario
    
    public List<Permisos> SeccionesPermisos(String idusuario, String nommodulo, String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " permisos.idseccion ,"
                + " permisos.seccion ,"
                + " permisos.modulo ,"
                + "permisos.idmodulo ,"
                + " modulos.nombre ,"
                + " secciones.estado,"
                + " secciones.nombre as nombre_seccion "
                + " from permisos "
                + " join modulos on permisos.idmodulo = modulos.idmodulo"
                + " join secciones on permisos.idseccion = secciones.idseccion"
                + " where permisos.idusuario = '" + idusuario + "' and "
                + " modulos.estado = 'true' and secciones.estado = 'true'"
                + " and permisos.modulo = 'true' and permisos.seccion = 'true'"
                + " and modulos.nombre = '" + nommodulo + "' and permisos.idseccion <> '12'"
                + " group by(permisos.idseccion,permisos.seccion,permisos.modulo,permisos.idmodulo,modulos.nombre,"
                + " secciones.estado,secciones.nombre) "
                + " order by permisos.idseccion " + ascDesc + "";

        List<Permisos> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Permisos p = new Permisos();

            p.setIdseccion(new Secciones(new Integer(o[0].toString())));
            p.setSeccion(new Boolean(o[1].toString()));
            p.setModulo(new Boolean(o[2].toString()));
            p.setIdmodulo(new Modulos(new Integer(o[3].toString())));
            p.getIdmodulo().setNombre(o[4].toString());

            p.getIdseccion().setEstado(new Boolean(o[5].toString()));
            p.getIdseccion().setNombre(o[6].toString());

            lstR.add(p);

        }
        return lstR;
    }

    //buscar permisos por subseccion de usuario
    
    public List<Permisos> SubSeccionesPermisos(String idusuario, String nommodulo, String seccion, String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " permisos.idseccion ,"
                + " permisos.modulo ,"
                + " permisos.seccion ,"
                + " permisos.subseccion ,"
                + " permisos.idmodulo ,"
                + " modulos.nombre,"
                + " secciones.estado,"
                + " secciones.nombre as nombre_seccion,"
                + " permisos.idsubsecccion,"
                + " subsecciones.nombre as nombre_subseccion, "
                + " subsecciones.imagen,"
                + " subsecciones.url,"
                + " subsecciones.nombrevisual, "
                + " subsecciones.estado "
                + " from permisos "
                + " join modulos on permisos.idmodulo = modulos.idmodulo"
                + " join secciones on permisos.idseccion = secciones.idseccion"
                + " join subsecciones on permisos.idsubsecccion = subsecciones.idsubseccion"
                + " where permisos.idusuario = '" + idusuario + "'  and  modulos.estado = 'true' "
                + " and  secciones.estado = 'true' and subsecciones.estado = 'true'"
                + " and permisos.subseccion = 'true' and permisos.seccion = 'true' "
                + " and  permisos.modulo = 'true'"
                + " and modulos.nombre = '" + nommodulo + "'  and  secciones.nombre = '" + seccion + "' "
                + " and permisos.idseccion <> '12'"
                + " group by(permisos.idseccion,permisos.seccion,permisos.subseccion,"
                + " permisos.modulo,permisos.idmodulo,modulos.nombre, "
                + " secciones.estado,secciones.nombre,permisos.idsubsecccion,"
                + " subsecciones.nombre,subsecciones.imagen,subsecciones.url,subsecciones."
                + " nombrevisual,subsecciones.estado) "
                + " order by permisos.idseccion " + ascDesc + "";

        List<Permisos> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Permisos p = new Permisos();

            p.setIdseccion(new Secciones(new Integer(o[0].toString())));
            p.setModulo(new Boolean(o[1].toString()));
            p.setSeccion(new Boolean(o[2].toString()));
            p.setSubseccion(new Boolean(o[3].toString()));
            p.setIdmodulo(new Modulos(new Integer(o[4].toString())));

            p.getIdmodulo().setNombre(o[5].toString());

            p.getIdseccion().setEstado(new Boolean(o[6].toString()));
            p.getIdseccion().setNombre(o[7].toString());
            p.setIdsubsecccion(new Subsecciones(new Integer(o[8].toString())));
            p.getIdsubsecccion().setNombre(o[9].toString());
            p.getIdsubsecccion().setImagen(o[10].toString());
            p.getIdsubsecccion().setUrl(o[11].toString());
            p.getIdsubsecccion().setNombrevisual(o[12].toString());
            p.getIdsubsecccion().setEstado(new Boolean(o[13].toString()));
            lstR.add(p);

        }
        return lstR;
    }

    //buscar permisos por subseccion de usuario
    
    public List<Permisos> SubSeccionesContenidoPermisos(String idusuario, String nommodulo, String seccion, String sseccion, String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + " permisos.idseccion ,"
                + " permisos.modulo ,"
                + " permisos.seccion ,"
                + " permisos.subseccion ,"
                + " permisos.idmodulo ,"
                + " modulos.nombre,"
                + " secciones.estado,"
                + " secciones.nombre as nombre_seccion,"
                + " permisos.idsubsecccion,"
                + " subsecciones.nombre as nombre_subseccion, "
                + " subsecciones.imagen,"
                + " subsecciones.url,"
                + " subsecciones.nombrevisual, "
                + " subsecciones.estado,"
                + " permisos.consulta,"
                + " permisos.ingreso,"
                + " permisos.edicion,"
                + " permisos.buscar"
                + " from permisos "
                + " join modulos on permisos.idmodulo = modulos.idmodulo"
                + " join secciones on permisos.idseccion = secciones.idseccion"
                + " join subsecciones on permisos.idsubsecccion = subsecciones.idsubseccion"
                + " where permisos.idusuario = '" + idusuario + "'  and  modulos.estado = 'true' "
                + " and  secciones.estado = 'true' and subsecciones.estado = 'true'"
                + " and permisos.subseccion = 'true' and permisos.seccion = 'true' "
                + " and  permisos.modulo = 'true'"
                + " and modulos.nombre = '" + nommodulo + "'  and  secciones.nombre = '" + seccion + "' "
                + "and subsecciones.nombre = '" + sseccion + "' and permisos.idseccion <> '12'"
                + " group by(permisos.idseccion,permisos.seccion,permisos.subseccion,permisos.modulo,"
                + "permisos.idmodulo,modulos.nombre, "
                + " secciones.estado,secciones.nombre,permisos.idsubsecccion,"
                + " subsecciones.nombre,subsecciones.imagen,subsecciones.url,subsecciones.nombrevisual,"
                + "subsecciones.estado,permisos.consulta,"
                + "permisos.ingreso,permisos.edicion,permisos.buscar) "
                + " order by permisos.idseccion " + ascDesc + "";

        List<Permisos> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            Permisos p = new Permisos();

            p.setIdseccion(new Secciones(new Integer(o[0].toString())));
            p.setModulo(new Boolean(o[1].toString()));
            p.setSeccion(new Boolean(o[2].toString()));
            p.setSubseccion(new Boolean(o[3].toString()));
            p.setIdmodulo(new Modulos(new Integer(o[4].toString())));

            p.getIdmodulo().setNombre(o[5].toString());

            p.getIdseccion().setEstado(new Boolean(o[6].toString()));
            p.getIdseccion().setNombre(o[7].toString());
            p.setIdsubsecccion(new Subsecciones(new Integer(o[8].toString())));
            p.getIdsubsecccion().setNombre(o[9].toString());
            p.getIdsubsecccion().setImagen(o[10].toString());
            p.getIdsubsecccion().setUrl(o[11].toString());
            p.getIdsubsecccion().setNombrevisual(o[12].toString());
            p.getIdsubsecccion().setEstado(new Boolean(o[13].toString()));

            p.setConsulta(new Boolean(o[14].toString()));
            p.setIngreso(new Boolean(o[15].toString()));
            p.setEdicion(new Boolean(o[16].toString()));
            p.setBuscar(new Boolean(o[17].toString()));

            lstR.add(p);

        }
        return lstR;
    }

}
