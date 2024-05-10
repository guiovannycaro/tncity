/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author root
 */
public class ImagenURL {

    URL objURL = null;
    ImageIcon img = null;
    String url = "";

    public ImagenURL(String url) {
        this.url = url;
        try {
            objURL = new URL(url);
            img = new ImageIcon(objURL);
        } catch (Exception e) {
            System.out.println("IMAGEN desde URL no ENCONTRADA->" + e);
            e.printStackTrace();
        }
    }

    public int getAlto() {
        int a = 0;
        try {
            a = img.getIconHeight();
        } catch (Exception e) {
        }
        return a;
    }

    public int getAncho() {
        int a = 0;
        try {
            a = img.getIconWidth();
        } catch (Exception e) {
        }
        return a;
    }

    public ImageIcon getImg() {
        return img;
    }

    public ImageIcon getImg(int width, int height) {
        Image icon = this.getImg().getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        img = new ImageIcon(icon);

        return img;
    }

    public static void main(String[] args) {
        System.out.println("Cargando Imagen de URL ...");
        ImagenURL iu = new ImagenURL("http://www.ciencias.unal.edu.co/unciencias/lib/pckImage/unal.gif");
        System.out.println("Listo!");
        System.out.println("ANCHO->" + iu.getAncho() + "; ALTO->" + iu.getAlto());
    }
}
