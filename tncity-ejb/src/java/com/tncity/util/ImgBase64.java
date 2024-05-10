/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;

public class ImgBase64 {

    public static byte[] encode(byte[] bytes) {
        return Base64.encodeBase64(bytes);
    }

    private static byte[] bytesFromFile(File file) {
        try {
            RandomAccessFile f = new RandomAccessFile(file, "r");
            byte[] b = new byte[(int) f.length()];
            f.read(b);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encode(File file) {
        return encode(bytesFromFile(file));
    }

    public static String encodeString(File file) {
        return new String(encode(bytesFromFile(file)));
    }

    /**
     * Encode image to string
     *
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            imageString = Base64.encodeBase64String(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public static void decode(String imageDataString, File fileDes) {
        try {
            byte[] imageDataBytes = Base64.decodeBase64(imageDataString);
            FileOutputStream fos = new FileOutputStream(fileDes);
            fos.write(imageDataBytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Decode string to image
     *
     * @param imageString The string to decode
     * @return decoded image
     */
    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            imageByte = Base64.decodeBase64(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    public static BufferedImage resizeImageWithHint(BufferedImage originalImage, int width, int height) {
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    public static void main(String args[]) throws IOException, URISyntaxException {
        //IMAGEN DESDE ARCHIVO
        //BufferedImage img = ImageIO.read(new File("/home/inmoticadev4/Imágenes/LOGO-INMOTICA.png"));

        //IMAGEN DESDE URL
        URL url = new URL("http://siescog.sincap.co:8080/appdownload/download?f=ctYzrpM-txHMQn1GVdCMPfhsPJ8v6pxxzerGAMqW6F_AGAj93-8PN3aKXSXbpTCnp7MFSJ181DhFmhnShMCT1-eCiB2TNGFkYkgGsW707_g&pfdrid_c=true");
        BufferedImage img = ImageIO.read(url);
        BufferedImage newImg = resizeImage(img, 40, 60);

        String imgStr = encodeToString(img, "png");
        String imgResized = encodeToString(newImg, "png");

        System.out.println(imgResized);

        //GUARDANDO ARCHIVO EN BASE64
        newImg = decodeToImage(imgResized);
        ImageIO.write(newImg, "png", new File("/home/inmoticadev4/Imágenes/prueba.png"));
    }
}
