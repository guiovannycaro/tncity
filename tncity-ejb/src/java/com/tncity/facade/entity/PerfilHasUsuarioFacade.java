/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.PerfilHasUsuario;
import com.tncity.security.SecurityFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class PerfilHasUsuarioFacade extends AbstractFacade<PerfilHasUsuario> {

    @EJB
    SecurityFacade securityFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PerfilHasUsuarioFacade() {
        super(PerfilHasUsuario.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idperfil", "nombre", "observaciones"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected PerfilHasUsuario parseObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(PerfilHasUsuario obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(PerfilHasUsuario obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idperfil, StringBuilder err) {
        deleteGeneral(idperfil, err);
    }

    public long countAllPerfiles(Integer idusuario) {
        String hql = " SELECT count(obj) FROM PerfilHasUsuario obj "
                + " WHERE obj.idusuario = " + idusuario;
        
        return numFromHQL(hql, new Long(0));
    }

    public List<PerfilHasUsuario> listAllPerfiles(Integer idusuario, String attrOrd, 
            String ascDesc, int firstReg, int maxReg) {
        String hql = " SELECT obj FROM PerfilHasUsuario obj "
                + " WHERE obj.idusuario = " + idusuario + " ORDER BY obj.idpu " + ascDesc;
        
        return findList(hql, firstReg, maxReg);
    }

    public long countAllUsuarios(Integer idperfil) {
        String hql = " SELECT count(obj) FROM PerfilHasUsuario obj"
                + "  WHERE obj.idperfil = " + idperfil;
       
        return numFromHQL(hql, new Long(0));
    }

    public List<PerfilHasUsuario> listAllUsuarios(Integer idperfil, String attrOrd, String ascDesc, 
            int firstReg, int maxReg) {
        
        String hql = " SELECT obj FROM PerfilHasUsuario obj "
                + " WHERE obj.idperfil = " + idperfil + " ORDER BY obj.idpu " + ascDesc;
      
        return findList(hql, firstReg, maxReg);
    }

    public void eliminar(Integer idperfil, Integer idusuario, StringBuilder error) {

        beginTransaction();

        try {
            SessionImpl sess = getSess();

            String sql = " DELETE FROM perfil_has_usuario  WHERE idusuario = " + idusuario
                    + " AND idperfil = " + idperfil;

            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();

        } catch (Exception e) {
            error.append("Falla en la transacción, ").append(e.toString());
        }

        endTransaction();

        securityFacade.loadPerfilesUsuarios();
    }

    public void nuevo(PerfilHasUsuario obj, StringBuilder err) {

        String sql0 = " SELECT count(*) FROM perfilusuario "
                + " WHERE idusuario = " + obj.getIdusuario().getIdusuario()
                + " AND idperfil = " + obj.getIdperfil().getIdperfil();//Buscando referencia a colarea
      
        int m = this.numFromSQL(sql0, new BigInteger("0")).intValue();

        if (m > 0) {
            err.append("El registro ya posee un perfil asociado, no es posible realizar la acción");
            return;
        }
        beginTransaction();
        try {
            SessionImpl sess = getSess();

        
               String sql = " Insert into perfilusuario  (idperfil,idusuario)"
                       + " VALUES ('"+obj.getIdusuario().getIdusuario()+"','"+obj.getIdperfil().getIdperfil()+"')";
                   
              
                sess.createSQLQuery(sql).executeUpdate();
            
            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();

        securityFacade.loadPerfilesUsuarios();
    }

}
