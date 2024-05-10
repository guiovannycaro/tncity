/**
 * @name Subida de Imagenes benefactor
 *
 * @ description crud de Subida de Imagenes benefactor
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.control.util.UtilJsf;
import com.tncity.facade.entity.PersonaFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Persona;
import com.tncity.util.Archivo;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author guiovanny
 */
@ManagedBean(name = "fileUploadController")
@RequestScoped
public class FileUploadController extends AbstractControl<Persona> {

//destino de las imagenes
    
    String destination = facesUtil.getProtocolHostPortPath() + "/faces/documentos/";

    @EJB
    PersonaFacade facade;

    public FileUploadController() {
        super(Persona.class);
        attrOrd = "idpersona";
        ascDesc = "DESC";
    }
    String imagenavatar;

    public String getImagenavatar() {
        return imagenavatar;
    }

    public void setImagenavatar(String imagenavatar) {
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

    private static final long serialVersionUID = 1L;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    //subida de archivo de imagenes
    
    public void upload(FileUploadEvent event) {
        System.out.println("Uploaded File Name Is :: " + file.getFileName() + 
                " :: Uploaded File Size :: " + file.getSize());
        System.out.println("sssss");

        InputStream inputStream = null;
        OutputStream outputStream = null;

        String arquivo = file.getFileName();
        String ruta = "\\faces\\documentos\\";

        FacesMessage msg = new FacesMessage("Success! ", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {
            copyFile(file.getFileName(), file.getInputstream());
            ;

            editDefault();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    //copiar archivo de imagen
    
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

}
