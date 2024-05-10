/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author leyer
 */
public class Download {

    public static void main(String[] args) {
        Scanner entrada = null;

        JFileChooser fileChooser = new JFileChooser("/home/guiovanny/Descargas/");


        FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xls");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File("reporte.xls"));
        fileChooser.showOpenDialog(fileChooser);
       fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            File f = new File(ruta);
            entrada = new Scanner(f);
            System.out.println("archivo" + entrada);
            while (entrada.hasNext()) {
                System.out.println(entrada.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }
}
