/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class NFSUtil {

    public NFSUtil() {
    }

    public void makeDir(String targetPath) {
        File destino = new File(targetPath);
        Archivo a = new Archivo(destino);
        a.crearDirectorio(destino.getPath());
    }

    public File[] listFiles(String targetPath) {
        File destino = new File(targetPath);
        return destino.listFiles();
    }

    public boolean deleteFile(String targetPath) {
        return Archivo.deletefile(targetPath);
    }

    public void saveFileFromInputStreamWithDirectoryIncluded(InputStream targetStream, String targetPath, StringBuilder err) {
        File destino = new File(targetPath);
        Archivo a = new Archivo(destino);
        a.crearDirectorio(destino.getParent());
        saveFileFromInputStream(targetStream, destino.toPath(), err);
    }

    public void saveFileFromInputStream(InputStream targetStream, Path targetPath, StringBuilder err) {
        try {
            Files.copy(targetStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(NFSUtil.class.getName()).log(Level.SEVERE, null, ex);
            err.append(ex);
        }
    }

    public void saveFileFromBytesArrayWithDirectoryIncluded(byte[] bytesArray, String targetPath, StringBuilder err) {
        File destino = new File(targetPath);
        Archivo a = new Archivo(destino);
        a.crearDirectorio(destino.getParent());
        saveFileFromBytesArray(bytesArray, targetPath, err);
    }

    public void saveFileFromBytesArray(byte[] bytesArray, String targetPath, StringBuilder err) {
        FileOutputStream fileOuputStream = null;
        try {
            fileOuputStream = new FileOutputStream(targetPath);
            fileOuputStream.write(bytesArray);
            fileOuputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NFSUtil.class.getName()).log(Level.SEVERE, null, ex);
            err.append(ex);
        } catch (IOException ex) {
            Logger.getLogger(NFSUtil.class.getName()).log(Level.SEVERE, null, ex);
            err.append(ex);
        }
    }

    public static void main(String[] args) {
        //TEST NFS
        System.out.println("=========== NFS ==============");

        NFSUtil nfs = new NFSUtil();

        //ORIGEN
        File file = new File("/home/inmoticadev4/Imágenes/nofoto.jpg");
        String path1 = "/opt/nfslocal/test/n1/n2/n3/n4/" + file.getName();
        String path2 = "/opt/nfslocal/prueba/nivel1/nivel2/" + file.getName();

        byte[] bytesArray = null;
        //FileOutputStream fileOuputStream = null;
        if (file.exists()) {

            StringBuilder err = new StringBuilder();

            try {
                bytesArray = new byte[(int) file.length()];
                FileInputStream fis;
                fis = new FileInputStream(file);
                fis.read(bytesArray);
                fis.close();

                nfs.saveFileFromBytesArrayWithDirectoryIncluded(bytesArray, path1, err);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NFSUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NFSUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

            InputStream targetStream = null;
            try {
                targetStream = new FileInputStream(file);

                //nfs.saveFileFromInputStreamWithDirectoryIncluded(targetStream, path2, err);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NFSUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
