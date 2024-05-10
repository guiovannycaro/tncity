/**
 * @name Persona Facade
 *
 * @ description crud de persona
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Persona;
import com.tncity.util.Archivo;
import com.tncity.properties.Propiedad;
import com.tncity.util.Cadena;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;
import java.util.List;


@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idpersona", "nombres", "apellidos", "numdocumento", "email", "numTelefono", "direccion"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombres", "apellidos", "numdocumento"};
        return attrs;
    }

    @Override
    protected Persona parseObject(Object[] o) {
        Persona p = new Persona(new Long(o[0].toString()));
        p.setNombres((String) o[1]);
        p.setApellidos((String) o[2]);
        if (o[3] != null) {
            p.setNumdocumento(new BigInteger(o[3].toString()));
        }
        p.setEmail((String) o[4]);
        if (o[5] != null) {
            p.setNumTelefono(new Long(o[5].toString()));
        }
        p.setDireccion((String) o[6]);
        return p;
    }

    //buscar persona por numero de documento
    
    public Persona findByNumDocumento(BigInteger numdocumento) {
        String hql = " SELECT p FROM Persona p WHERE p.numdocumento='" + numdocumento + "'";
        
        return objectFromHQL(hql);
    }

    //buscar persona por id persona
    
    public Persona findByIdPersona(Integer idpersona) {
        String hql = " SELECT p FROM Persona p WHERE p.idpersona='" + idpersona + "'";
        
        return objectFromHQL(hql);
    }
    
        public Persona findByIdBenefactor(Benefactor i) {
        String hql = " SELECT p FROM Persona p WHERE p.idpersona='" + i.getIdpersona().getIdpersona() + "'";
        
        return objectFromHQL(hql);
    }
        
    //crear usuario persona

    @Override
    public void create(Persona obj, StringBuilder err) {
        Persona p = findByNumDocumento(obj.getNumdocumento());
        if (p != null) {
            err.append("El número de documento ").append(obj.getNumdocumento()).append(" ya está registrado !");
            return;
        }
        createGeneral(obj, err);
    }
    
    //editar usuario persona

    @Override
    public void edit(Persona obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    
    //borrar usuario persona 
    
    @Override
    public void delete(Object valueId, StringBuilder err) {
        deleteGeneral(valueId, err);
    }

    //recuperar datos persona
    
    public Persona RecuperaDatosPersona(Persona p) {

        String sql = " SELECT b FROM Persona b "
                + " where b.idpersona = " + p.getIdpersona() + "";

        return objectFromHQL(sql);
    }

    //contar personas
    
    public long countBuscar(String valbusq) {
        String sql = " SELECT COUNT(persona.idpersona)"
                + " FROM persona\n"
                + "	WHERE to_tsvector(persona.nombres||' '||persona.apellidos||' '||persona.numdocumento\n"
                + " )@@to_tsquery('" + valbusq.replaceAll(" ", "&") + "') \n";
        
        return numFromSQL(sql, new BigInteger("0")).longValue();
    }

    //bucar peronas por valor busqueda
    
    public List<Persona> buscarFullText(String valBusq, int first, int maxRegLista) {

        String sql = "SELECT "
                + " persona.idpersona,"
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento,"
                + " persona.numdocumento,"
                + " persona.num_telefono,"
                + " persona.email"
                + " FROM persona  "
                + "	WHERE to_tsvector(nombres||' '||apellidos||' '||numdocumento)@@to_tsquery('" + valBusq.replaceAll(" ", "&") + "') \n"
                + "ORDER BY persona.idpersona DESC";
    
        List<Persona> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            Persona i = new Persona(new Long(o[0].toString()));

            i.setNombres(o[1].toString());
            i.setApellidos(o[2].toString());

            i.setTipodocumento(o[3].toString());
            i.setNumdocumento(new BigInteger(o[4].toString()));

            i.setNumTelefono(new Long(o[5].toString()));
            i.setEmail(o[6].toString());

            lstR.add(i);

        }

        return lstR;
    }

    //imagen de la persona
    
    public void subirLogo(Persona obj, Integer idpersona, String fileName, byte[] contents, Long filesize, String tipoImg, String destination, StringBuilder err) {

        
       File file = new File(Propiedad.getCurrentInstance().getPathUpload());
            file.setWritable(true);
            file.setReadable(true);
            file.setExecutable(true);
         
            file.canRead();
            file.canWrite();
            file.canExecute();
            
        long tamImg = 10240;

        if (filesize > tamImg) {

            err.append("El tamaño de la imagen a cargar es mayor a la permitida!");
            return;

        }

        if (!(tipoImg.equals("image/jpg") || tipoImg.equals("image/png") || tipoImg.equals("image/jpeg"))) {

            err.append("Sòlo son permitidos archivos: .jpg ,jpeg .png ");
            return;
        }

        String[] fileNameArr = fileName.split("\\.");
        String ext = fileNameArr[1];

        obj.getImagen();
        String ruta = "";

        ruta = fileNameArr[0] + "." + ext;
        File archivo = new File(Propiedad.getCurrentInstance().getPathUpload() + "/" + obj.getImagen());

        

        if (fileNameArr.length != 2) {
            err.append("El archivo no es válido!");
            return;
        }

        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String pathRelativo = Propiedad.getCurrentInstance().getPathUpload() + "/" + fileNameArr[0] + "." + ext;

            File img = new File(pathRelativo);

                String sql = " UPDATE persona SET imagen ='" + ruta + "' WHERE idpersona=" + idpersona;
              
                sess.createSQLQuery(sql).executeUpdate();

                //Creando archivo físico
                Archivo.crearCarpeta(img.getParentFile());
                if (!img.exists()) {
                    new Archivo(img).crearArchivo();
                }
                Archivo.writeFile(img, contents);
          
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();
    }

    //buscar persona por id persona
    
    public Persona findBylogId(Integer idpersona) {

        String hql = " SELECT b FROM Persona b "
                + " WHERE b.idpersona = '" + idpersona + "'";
    
        return objectFromHQL(hql);

    }

    public Persona findBylogIdd(Integer idpersona) {

        String hql = " SELECT b FROM Persona b "
                + " WHERE b.idpersona = '" + idpersona + "'";
       
        return objectFromHQL(hql);

    }
    
    //buscar persona por valor busqueda
    
      public List<Persona> listFullTextLight(String query, String attrOrd, String ascDesc, int firstReg, int maxReg) {
        List<Persona> lstC = new ArrayList<>();

         String sql = "SELECT "
                + " persona.idpersona,"
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento,"
                + " persona.numdocumento,"
                + " persona.num_telefono,"
                + " persona.email"
                + " FROM persona  "
                + "	WHERE to_tsvector(nombres||' '||apellidos||' '||numdocumento)@@to_tsquery('" + new Cadena().reemplazaEspacios(query, "&").trim() + "')"
                + " ORDER BY persona." + attrOrd + " ASC";
         
        List<Object[]> lst = findNativeGeneric(sql, firstReg, maxReg);
        for (Object[] o : lst) {
             Persona i = new Persona(new Long(o[0].toString()));

            i.setNombres(o[1].toString());
            i.setApellidos(o[2].toString());

            i.setTipodocumento(o[3].toString());
            i.setNumdocumento(new BigInteger(o[4].toString()));

            i.setNumTelefono(new Long(o[5].toString()));
            i.setEmail(o[6].toString());

            lstC.add(i);
        }
        return lstC;
    }
      
      // crear persona
      
          public void crearpersona(String nombres,String apellidos,String tipodocumento,String numdocumento,
String email,String numTelefono,String direccion,String numTelefono2,StringBuilder err) {

          
        beginTransaction();
        try {
            SessionImpl sess = getSess();
   

                String sql = " Insert into  persona ("
                    + " nombres,"
                    + " apellidos,"
                    + " tipodocumento,"
                    + " numdocumento,"
                    + " num_telefono,"
                    + " email,"
                    + " direccion,"
                    + " updated_at,"
                    + " created_at,"
                    + " idciudad"
                    + ") values ("
                    + "'" + nombres + "',"
                    + "'" + apellidos + "',"
                    + "'" + tipodocumento + "',"
                    + "'" + numdocumento + "',"
                    + "'" + numTelefono + "',"
                    + "'" + email + "',"
                    + "'" + direccion + "',"
                    + "'" + new Date() + "',"
                    + "'" + new Date() + "',"
                    + "'512' )";
        
              
                sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }
        endTransaction();

    }
          //buscar persona por id
          
        public Persona  findPersonaById(String idpersona){
                 String hql = " SELECT b FROM Persona b "
                + " WHERE b.idpersona = '" + idpersona + "'";

        return objectFromHQL(hql);
          
          }
        //recuparar listado de usuarios
        
          public List<Persona> RecuperaUsuarios() {

        String sql = " SELECT p.idpersona,p.nombres,p.apellidos,p.numdocumento "
                + " FROM Persona p";

        List<Object[]> lst = findNativeGeneric(sql);
        List<Persona> lstU = new ArrayList<>();
        for (Object[] o : lst) {
           
             Persona i = new Persona(new Long(o[0].toString()));

            i.setNombres(o[1].toString());
            i.setApellidos(o[2].toString());
            i.setNumdocumento(new BigInteger(o[3].toString()));

            lstU.add(i);
        
        }
        return lstU;
    }

}
