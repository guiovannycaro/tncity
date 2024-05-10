/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Edwar A. Rojas - edwar.red@gmail.com
 */
public class Serializer {

    public static void serializer(Object obj, File f) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(f.getPath());
            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("FALLA, serializando objeto !");
            i.printStackTrace();
        }
    }

    public static Object unSerializer(File f) {
        Object obj = null;
        try {
            FileInputStream fileIn =
                    new FileInputStream(f.getPath());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return obj;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }

        return obj;
    }

    public static byte[] serializer(Object obj) {
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bs);
            os.writeObject(obj);  // this es de tipo DatoUdp
            os.close();
            byte[] bytes = bs.toByteArray(); // devuelve byte[]
            return bytes;
        } catch (Exception e) {
            System.out.println("FALLA, serializando objeto !");
            e.printStackTrace();
        }
        return null;
    }

    public static Object unSerializer(byte[] bytes) {
        try {
            ByteArrayInputStream bs = new ByteArrayInputStream(bytes); // bytes es el byte[]
            ObjectInputStream is = new ObjectInputStream(bs);
            Object obj = is.readObject();
            is.close();
            return obj;
        } catch (Exception e) {
            System.out.println("FALLA, serializando objeto !");
            e.printStackTrace();
        }
        return null;
    }
}
