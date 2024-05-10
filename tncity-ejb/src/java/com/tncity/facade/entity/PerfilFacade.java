/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Perfil;
import com.tncity.jpa.pojo.PerfilFuncionalidad;
import com.tncity.jpa.pojo.PerfilHasUsuario;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.security.SecurityFacade;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class PerfilFacade extends AbstractFacade<Perfil> {

    @EJB
    SecurityFacade securityFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PerfilFacade() {
        super(Perfil.class);
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
    protected Perfil parseObject(Object[] o) {
        Perfil p = new Perfil((Integer) o[0]);
        p.setNombre((String) o[1]);
        p.setObservaciones((String) o[2]);
        return p;
    }

    @Override
    public void create(Perfil obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Perfil obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idperfil, StringBuilder err) {
        deleteGeneral(idperfil, err);
    }

    public HashMap<Long, Long> mapFuncionalidadesByPerfil(Integer idperfil) {
        HashMap<Long, Long> map = new HashMap<>();

        String hql = " SELECT pf.idperfil.idperfil, pf.funcionalidadId"
                + "    FROM PerfilFuncionalidad pf"
                + "    WHERE pf.idperfil.idperfil = " + idperfil;

        List<Object[]> lst = findGeneric(hql);

        for (Object[] obj : lst) {
            map.put(new Long(obj[1].toString()), new Long(obj[1].toString()));
        }

        return map;
    }

    public void guardarPermisos(List<Long> lstId, Integer idperfil, StringBuilder err) {

        if (lstId.isEmpty()) {
            err.append("No ha seleccionado ninguna funcionalidad!");
            
            return;
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            //Se borran las funcionalidades dadas anteriormente al perfil
            String sql = " DELETE FROM perfil_funcionalidad WHERE idperfil = " + idperfil;
            
            sess.createSQLQuery(sql).executeUpdate();

            for (Long id : lstId) {

                String idpf = idperfil + "_" + id;

                PerfilFuncionalidad pf = new PerfilFuncionalidad(idpf);
                pf.setIdperfil(new Perfil(idperfil));
                pf.setFuncionalidadId(id.intValue());

                sess.save(pf);
            }

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();

        securityFacade.loadPerfilesFunc();

    }

    public List<PerfilHasUsuario> listUsuariosLight(Integer idperfil) {
        String hql = " SELECT up.idpu,up.idperfil.idperfil,up.idusuario.idusuario,"
                + " up.idusuario.idpersona.idpersona,up.idusuario.idpersona.numdocumento,"
                + " up.idusuario.idpersona.nombres,up.idusuario.idpersona.apellidos "
                + "   FROM PerfilHasUsuario up"
                + " WHERE up.idperfil.idperfil=" + idperfil
                + " ORDER BY up.idusuario.idpersona.nombres ASC ";

        List<PerfilHasUsuario> lstPU = new ArrayList<>();

        List<Object[]> lst = findGeneric(hql);
        for (Object[] o : lst) {
            PerfilHasUsuario pu = new PerfilHasUsuario((String) o[0]);
            pu.setIdperfil(new Perfil((Integer) o[1]));
            pu.setIdusuario(new Usuario((Integer) o[2]));
            pu.getIdusuario().setIdpersona(new Persona((Long) o[3]));
            pu.getIdusuario().getIdpersona().setNumdocumento((BigInteger) o[4]);
            pu.getIdusuario().getIdpersona().setNombres((String) o[5]);
            pu.getIdusuario().getIdpersona().setApellidos((String) o[6]);

            lstPU.add(pu);
        }

        return lstPU;
    }

    public List<Perfil> listAllLightByPerfil(String attrOrder, String desAsc) {
        String hql = " SELECT p "
                + "  FROM Perfil p "
                + " ORDER BY p." + attrOrder + " " + desAsc;
        
        return this.findList(hql);
    }

    /**
     * Key: idperfil, Value: Map<idfunc,idfunc>
     *
     * @return
     */
    public HashMap<Integer, HashMap<Integer, Integer>> mapPerfilesFuncionalidades() {
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

        String hql = " SELECT pf.idperfil.idperfil, pf.funcionalidadId "
                + "  FROM PerfilFuncionalidad pf ";

        List<Object[]> lst = findGeneric(hql);
        for (Object[] o : lst) {
            Integer idperfil = (Integer) o[0];
            Integer idfuncionalidad = (Integer) o[1];

            HashMap<Integer, Integer> mapF = map.get(idperfil);
            if (mapF == null) {
                mapF = new HashMap<>();
                map.put(idperfil, mapF);
            }
            mapF.put(idfuncionalidad, idfuncionalidad);
        }
        return map;
    }

    /**
     * Key: idusuario, Val Map<idperfil,idperfil>
     *
     * @return
     */
    public HashMap<Integer, HashMap<Integer, Integer>> mapPerfilesUsuarios() {
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

        String hql = " SELECT pu.idperfil.idperfil, pu.idusuario.idusuario "
                + "  FROM PerfilHasUsuario pu ";

        List<Object[]> lst = findGeneric(hql);
        for (Object[] o : lst) {
            Integer idperfil = (Integer) o[0];
            Integer idusuario = (Integer) o[1];

            HashMap<Integer, Integer> mapP = map.get(idusuario);
            if (mapP == null) {
                mapP = new HashMap<>();
                map.put(idusuario, mapP);
            }
            mapP.put(idperfil, idperfil);
        }
        return map;
    }

    public long countBuscar(String valbusq) {
        String sql = " SELECT count(*)\n"
                + "  FROM perfil b\n"
                + "	WHERE to_tsvector(nombre)@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n";
       
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    public List<Perfil> buscarFullText(String valBusq, int first, int maxRegLista) {
        String sql = " SELECT *\n"
                + "  FROM perfil b\n"
                + "	WHERE to_tsvector(nombre)@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') \n"
                + " ORDER BY idperfil ASC ";
        
        return findNative(sql, first, maxRegLista, Perfil.class);
    }

    public List<Perfil> listPerfiles() {
        String hql = " SELECT c FROM Perfil  c where  idperfil <> 1";

        return findList(hql);

    }

}
