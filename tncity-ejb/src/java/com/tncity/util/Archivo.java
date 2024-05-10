/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class Archivo {

    File file;
 public Archivo() {
        
    }
    public Archivo(File file) {
        this.file = file;
    }

    public String getContent() {
        String content = "";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while ((line = bf.readLine()) != null) {
                content += line + "\n";
            }
            fr.close();
            bf.close();
        } catch (Exception e) {
            System.out.println("FALLA, leyendo archivo->" + file.getPath() + "/" + file.getName());
        }
        return content;
    }

    public void escribir(String contenido) {
        try {
            FileWriter fichero = new FileWriter(file);
            fichero.write(contenido);
            fichero.close();
        } catch (Exception ex) {
            System.out.println("FALLA, escribiendo en archivo ->" + file.toString());
            ex.printStackTrace();
        }
    }

    public void append(String contenido) {
        String cont = this.getContent();
        this.escribir(cont + contenido);
    }

    public void guardar(byte[] contenido) {
        //Creando carpeta si no existe
        Archivo.crearCarpeta(file.getParentFile());

        try {
            FileOutputStream fos = new FileOutputStream(file.getPath());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(contenido);
            bos.flush();
            bos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("FALLA, guardandoo archivo: " + file.getPath());
        }
    }

    public void guardar(InputStream stream) {
        try {
            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            guardar(buffer);
        } catch (Exception e) {
            System.out.println("FALLA, guardandoo archivo: " + file.getPath());
        }
    }

    public String crearArchivo() {
        String msg = "";
        try {
            if (file.exists()) {
                file.delete();
            }
            if (!file.createNewFile()) {
                msg = "FALLA, creando archivo \"" + file.getPath() + "/" + file.getName() + "\", chequee los permisos !";
            }
        } catch (Exception e) {
            msg = "FALLA eliminando-creando archivo:" + file.toString() + " :: " + e.toString();
            e.printStackTrace();
        }
        return msg;
    }

    public String crearDirectorio(String path) {
        String msg = "";
        try {
            java.io.File dir = new java.io.File(path);

            //Creando directorio si no existe
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    msg = "FALLA, creando directorio \"" + path + "\", chequee los permisos !";
                }
            }
        } catch (Exception e) {
            msg = "FALLA creando directorio:" + e.toString();
            e.printStackTrace();
        }
        return msg;
    }

    public static void crearCarpeta(File f) {
        if (f.exists()) {
            return;
        }
        try {
            if (!f.mkdirs()) {
                System.out.println("FALLA, creando archivo \"" + f.getPath() + "\", chequee los permisos !");
            }
        } catch (Exception e) {
            System.out.println("FALLA, creando archivo \"" + f.getPath() + "\", chequee los permisos !" + e);
            e.printStackTrace();
        }
    }

    public static String getExtension(String nombreArchivo) {
        String ext = "";
        String fileName = nombreArchivo;
        int mid = fileName.lastIndexOf(".");
        ext = fileName.substring(mid + 1, fileName.length());
        return ext;
    }

    public static String getNameWithoutExtension(String nombreArchivo) {
        String name = "";

        if (nombreArchivo != null && !nombreArchivo.isEmpty() && nombreArchivo.contains(".")) {
            int i = nombreArchivo.lastIndexOf(".");
            name = nombreArchivo.substring(0, i);
        }

        return name;
    }

    public static boolean saveFile(String path, byte[] content) {
        boolean ret = false;

        try {
            FileOutputStream fos = new FileOutputStream(path);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(content);
            bos.flush();
            bos.close();
            ret = true;
        } catch (Exception e) {
            ret = false;
            System.out.println("FALLA, guardando archivo: " + path);
            e.printStackTrace();
        }

        return ret;
    }

    public static String getURLServlet(String pathArchivo, String protocolHostPortContext) {
        return getURLServlet(pathArchivo, protocolHostPortContext, "file_" + (int) (Math.random() * 10000) + "." + Archivo.getExtension(pathArchivo));
    }

    public static String getURLServlet(String pathArchivo, String protocolHostPortContext, String nombreDw) {

        String url = "";
        File f = new File(pathArchivo);

        String nam = f.getName();
        nam = new Cadena().reemplazaEspacios(nam, "_");
        nam = Cadena.quitaCaracteresConAcentos(nam);

        url = protocolHostPortContext + "/ServletDownload?ph_=" + new EncodeDecode().encode(pathArchivo) + "&name_=" + new EncodeDecode().encode(nombreDw);
        return url;
    }

    public static String getURLServlet2(String pathArchivo, String protocolHostPortContext) {
        String url = "";
        File f = new File(pathArchivo);
        url = protocolHostPortContext + "/ServletDownload?ph_=" + new EncodeDecode().encode(pathArchivo) + "&name_=" + new EncodeDecode().encode(f.getName());
        return url;
    }

    public static boolean deletefile(String file) {
        File f1 = new File(file);
        boolean success = f1.delete();
        return success;
    }

    public static boolean removeDirectory(File directory) {

        // System.out.println("removeDirectory " + directory);
        if (directory == null) {
            return false;
        }
        if (!directory.exists()) {
            return true;
        }
        if (!directory.isDirectory()) {
            return false;
        }

        String[] list = directory.list();

        // Some JVMs return null for File.list() when the
        // directory is empty.
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                File entry = new File(directory, list[i]);

                //        System.out.println("\tremoving entry " + entry);
                if (entry.isDirectory()) {
                    if (!removeDirectory(entry)) {
                        return false;
                    }
                } else {
                    if (!entry.delete()) {
                        return false;
                    }
                }
            }
        }

        return directory.delete();
    }

    /**
     * Save URL contents to a file.
     */
    public static boolean copy(URL from, File to) {
        BufferedInputStream urlin = null;
        BufferedOutputStream fout = null;
        try {
            int bufSize = 8 * 1024;
            urlin = new BufferedInputStream(
                    from.openConnection().getInputStream(),
                    bufSize);
            fout = new BufferedOutputStream(new FileOutputStream(to), bufSize);
            copyPipe(urlin, fout, bufSize);
        } catch (IOException ioex) {
            return false;
        } catch (SecurityException sx) {
            return false;
        } finally {
            if (urlin != null) {
                try {
                    urlin.close();
                } catch (IOException cioex) {
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException cioex) {
                }
            }
        }
        return true;
    }

    /**
     * Reads data from the input and writes it to the output, until the end of
     * the input stream.
     *
     * @param in
     * @param out
     * @param bufSizeHint
     * @throws IOException
     */
    public static void copyPipe(InputStream in, OutputStream out, int bufSizeHint)
            throws IOException {
        int read = -1;
        byte[] buf = new byte[bufSizeHint];
        while ((read = in.read(buf, 0, bufSizeHint)) >= 0) {
            out.write(buf, 0, read);
        }
        out.flush();
    }

    public static void copyFolder(File src, File dest)
            throws IOException {

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
                /*System.out.println("Directory copied from "
                 + src + "  to " + dest);*/
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile);
            }

        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes 
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            //System.out.println("File copied from " + src + " to " + dest);
        }
    }

    public static boolean copyfile(String dtFile) {
        try {
          
            File f2 = new File(dtFile);
            InputStream in = new FileInputStream(f2);

            //For Append the file.
            //  OutputStream out = new FileOutputStream(f2,true);
            //For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            //System.out.println("File copied.");
            return true;
        } catch (Exception ex) {
            System.err.println("FALLA, copiando archivo: "  + " A " + dtFile);
            ex.printStackTrace();
        }
        return false;
    }

    public static void findFile(File root, String name, List<File> result) {
        File[] faFiles = root.listFiles();
        for (File f : faFiles) {
            if (f.getName().matches("^(.*?)")) {
                //System.out.println(f.getAbsolutePath());
                if (f.getName().contains(name)) {
                    result.add(f);
                }
                if (f.isDirectory()) {
                    findFile(f, name, result);
                }
            }
        }
    }

    public static void writeFile(File file, byte[] data) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(File file, InputStream dataStream) {
        try {
            byte[] buffer = new byte[dataStream.available()];
            dataStream.read(buffer);

            OutputStream outStream = new FileOutputStream(file);
            outStream.write(buffer);
            outStream.close();
            dataStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readFile(File file) {
        try {
            Path path = Paths.get(file.getAbsolutePath());
            byte[] data = Files.readAllBytes(path);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean fileIsImg(File file) {
        String ext = getExtension(file.getName());
        if (ext != null && !ext.trim().isEmpty()) {
            ext = ext.toLowerCase().trim();
            if (ext.equals("gif")
                    || ext.equals("png")
                    || ext.equals("jpg")
                    || ext.equals("jpeg")
                    || ext.equals("jp2")
                    || ext.equals("bmp")) {
                return true;
            }
        }
        return false;
    }

    public static String fileIconFontAwesonme(File file, int size) {
        String style = "fa fa-file-o fa-" + size + "x";

        String ext = getExtension(file.getName());
        if (ext != null && !ext.trim().isEmpty()) {
            ext = ext.toLowerCase().trim();
            if (ext.equals("xls") || ext.equals("xlsx")) {
                style = "fa fa-file-excel-o fa-" + size + "x";
            }
            if (ext.equals("doc") || ext.equals("docx")) {
                style = "fa fa-file-word-o fa-" + size + "x";
            }
            if (ext.equals("ppt") || ext.equals("pptx")) {
                style = "fa fa-file-powerpoint-o fa-" + size + "x";
            }
            if (ext.equals("pdf")) {
                style = "fa fa-file-pdf-o fa-" + size + "x";
            }
            if (ext.equals("txt")) {
                style = "fa fa-file-text-o fa-" + size + "x";
            }
            if (ext.equals("htm") || ext.equals("html")) {
                style = "fa fa-file-code-o fa-" + size + "x";
            }
            if (ext.equals("mp3") || ext.equals("wav")) {
                style = "fa fa-file-audio-o fa-" + size + "x";
            }
            if (ext.equals("mp4") || ext.equals("wmv") || ext.equals("mov")) {
                style = "fa fa-file-video-o fa-" + size + "x";
            }
            if (ext.equals("zip") || ext.equals("rar") || ext.equals("gz")) {
                style = "fa fa-file-archive-o fa-" + size + "x";
            }
            if (fileIsImg(file)) {
                style = "fa fa-file-image-o fa-" + size + "x";
            }

        }
        return style;
    }

    public File getFile() {
        return file;
    }

    public static void main(String[] args) {
        
        String archivo = "c:/home/guiovanny/Imágenes/fondos escritorio/974218.JPG";
        Archivo a = new Archivo();
        a.copyfile(archivo);

    }
}
