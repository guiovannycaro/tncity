/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Usage sample serializing SomeClass instance
 */
public class SerialObject {

    public static Object fromString(String s) {
        try {
            byte[] data = new Base64().decode(s);
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(data));
            Object o = ois.readObject();
            ois.close();
            return o;
        } catch (Exception e) {
            System.out.println("FALLA, serializando cadena->" + e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Write the object to a Base64 string.
     */
    public static String toString(Serializable o) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.close();
            return new String(Base64.encode(baos.toByteArray()));
        } catch (Exception e) {
            System.out.println("FALLA, recuperando objeto(Serializable)->" + e);
            e.printStackTrace();
        }

        return null;
    }

}
