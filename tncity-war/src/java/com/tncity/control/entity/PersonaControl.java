/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.PersonaFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Persona;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import static org.primefaces.component.contextmenu.ContextMenu.PropertyKeys.event;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "personaControl")
@RequestScoped
public class PersonaControl extends AbstractControl<Persona> {

    private static final int BUFFER_SIZE = 6124;

    String destination = facesUtil.getProtocolHostPortPath() + "/faces/documentos/";
    private static final long serialVersionUID = 1L;
    private UploadedFile file;

Persona img;
String nombres;
String apellidos;
String tipodocumento;
String numdocumento;
String email;
String numTelefono;
String direccion;
String numTelefono2;
String idciudad;


        
       List<Persona> lstusuarios = new ArrayList<>();    

    public List<Persona> getLstusuarios() {
        return lstusuarios;
    }

    public void setLstusuarios(List<Persona> lstusuarios) {
        this.lstusuarios = lstusuarios;
    }

       
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumTelefono2() {
        return numTelefono2;
    }

    public void setNumTelefono2(String numTelefono2) {
        this.numTelefono2 = numTelefono2;
    }

    public String getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(String idciudad) {
        this.idciudad = idciudad;
    }





    public Persona getImg() {
        return img;
    }

    public void setImg(Persona img) {
        this.img = img;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @EJB
    PersonaFacade facade;
    
    
    
    

    public PersonaControl() {
        super(Persona.class);
        attrOrd = "idpersona";
        ascDesc = "DESC";
    }

    private UploadedFile imagenavatar;
    List<Persona> lstpersonas = new ArrayList<>();

    public List<Persona> getLstpersonas() {
        return lstpersonas;
    }

    public void setLstpersonas(List<Persona> lstpersonas) {
        this.lstpersonas = lstpersonas;
    }

    public UploadedFile getImagenavatar() {
        return imagenavatar;
    }

    public void setImagenavatar(UploadedFile imagenavatar) {
        this.imagenavatar = imagenavatar;
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Persona obj) {
        return obj.getNombres() + " " + obj.getApellidos() + " " + obj.getNumdocumento();
    }

    public List<SelectItem> selectId() {
        List<Persona> lstObjs = facade.listAllLight("idpersona.nombres", "ASC");
        List<SelectItem> lstS = new ArrayList<>();
        lstS.add(new SelectItem(null, "---"));

        for (Persona obj : lstObjs) {
            SelectItem it = new SelectItem(obj.getIdpersona(), displayObj(obj));
            lstS.add(it);
        }
        return lstS;
    }

    public void recuperaByIdInteger(Integer idpersona) {
        if (idpersona != null) {
            obj = facade.find(idpersona);
        }
    }

    public void nuevo() {
        
        
             
         StringBuilder err = new StringBuilder();
        facade.crearpersona(nombres,apellidos,tipodocumento,numdocumento,
                            email,numTelefono,direccion,numTelefono2, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
     
        
        
        
        
    }

    public String imgantigua(String img) {
        return img;
    }

    public void editar() {

        obj.setIdusuarioUpdated(facesUtil.getCurrentUser());
        obj.setUpdatedAt(new Date());

        editDefault();

    }

    public void subirImagen() {

        StringBuilder err = new StringBuilder();
        Integer idpersona = facesUtil.getFacesParamInteger("idpersona_");
        obj = imgusuario(idpersona);

        String destination = facesUtil.getProtocolHostPortPath() + "/faces/documentos/";

        facade.subirLogo(obj, idpersona, imagenavatar.getFileName(), 
                imagenavatar.getContents(), imagenavatar.getSize(),
                imagenavatar.getContentType(),destination, err);

        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Imagen Subida Correctamente!");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }

    public void recuperaByNumDocumento(String numDoc) {
        obj = facade.findByNumDocumento(new BigInteger(numDoc));
    }

    public void recuperaByIdPersona(Integer idpersona) {
        obj = facade.findByIdPersona(idpersona);
    }

    public void upload(FileUploadEvent event) {

        System.out.println("Uploaded File Name Is :: " + file.getFileName() + " "
                + ":: Uploaded File Size :: " + file.getSize());
        System.out.println("sssss");

        InputStream inputStream = null;
        OutputStream outputStream = null;

        String arquivo = file.getFileName();
        String ruta = "\\faces\\documentos\\";

        FacesMessage msg = new FacesMessage("Success! ", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {
            copyFile(file.getFileName(), file.getInputstream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void copyFile(String fileName, InputStream in) {

        System.out.println("destino" + destination);
        try {

            // write the inputStream to a FileOutputStream
            FileOutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[(int) file.getSize()];
            int contador = 0;

            while ((contador = in.read(bytes)) != -1) {
                out.write(bytes, 0, contador);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facade.countBuscar(valBusq);
        }
    }

    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lstpersonas = facade.buscarFullText(valBusq, first, maxRegLista);
    }

    public Persona imgusuario(Integer idpersona) {
        obj = facade.findBylogId(idpersona);

        return obj;
    }
    
            public List<Persona> autoCompleteAllLight(String query) {
         List<Persona> lstC=facade.listFullTextLight(query, "idpersona","ASC",0, 20);
         return lstC;
    }
            
             public void RecuperaUsuariosId() {
        lstusuarios = facade.RecuperaUsuarios();
    }
}
