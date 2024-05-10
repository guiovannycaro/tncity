/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.tncity.properties.Propiedad;
import java.io.File;

/**
 *
 * @author inmoticamaster
 */
public class LibreOfficeUtil {

    public static final String FORMAT_PDF = "pdf";
    public static final String FORMAT_HTML = "html";
    public static final String FORMAT_DOC = "doc";
    public static final String FORMAT_DOCX = "docx";

    public static File convert(File ori, String format) {
        StringBuilder err = new StringBuilder();
        String[] cmd = {Propiedad.getCurrentInstance().getPathBin() + "/libreoffice-convert.sh", ori.getParent(), format, ori.getAbsolutePath()};
        String res = SystemComand.executeComand(cmd, err);
        if (!err.toString().isEmpty()) {
            System.out.println("ALERTA CONVERT: FALLA -> " + err.toString());
        }
        return new File(ori.getParent() + "/" + Archivo.getNameWithoutExtension(ori.getName()) + "." + format);
    }

}
