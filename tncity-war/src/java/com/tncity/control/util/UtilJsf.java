/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author guiovanny
 */
public class UtilJsf {
private Part image;

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }


    public void guardaBlobenFichero( String nombrearchivo) {

        
        try{
        InputStream in = image.getInputStream();
        
             File f = new
File("\\documentos\\" + image.getSubmittedFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
 
            byte[] buffer = new byte[1024];
            int length;
 
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
 
            out.close();
            in.close();
         
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se guardó satisfactoriamente"));
 
            
        }catch(Exception e){
        
            System.err.println("no se pudo cargar la imagen");
        }
        
        
       

    }
}
