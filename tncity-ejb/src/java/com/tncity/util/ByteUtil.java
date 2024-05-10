/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteUtil {

    public static ByteBuffer addHeader(ByteBuffer data, String header) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(data.array());
            outputStream.write(header.getBytes());
            outputStream.close();

            return ByteBuffer.wrap(outputStream.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getHeader(ByteBuffer data, int lenHeader) {
        byte[] h = new byte[lenHeader];
        byte[] b = data.array();

        int j = 0;
        for (int i = b.length - lenHeader; i < b.length; i++) {
            h[j] = b[i];
            j++;
        }
        return new String(h);
    }

    public static ByteBuffer dropHeader(ByteBuffer data, int lenHeader) {
        byte[] ori = data.array();
        byte[] des = Arrays.copyOfRange(ori, 0, ori.length - lenHeader);
        return ByteBuffer.wrap(des);
    }

    public static byte[] addBytes(byte[] ori, byte[] add) {
        byte[] destination = new byte[ori.length + add.length];
        System.arraycopy(add, 0, destination, ori.length, add.length);
        return destination;
    }

    public static String postgresByteaData(byte[] data) {
        StringBuilder out = new StringBuilder();
        for (byte b : data) {
            out.append(String.format("\\x%x", b));
        }
        return "E'" + out.toString() + "'";
    }

    public static String bytesToHexPrint(byte in) {
        byte[] arr = new byte[1];
        arr[0] = in;
        return ByteUtil.bytesToHexPrint(arr);
    }

    public static String bytesToHexPrint(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static Integer byteToInteger(byte[] data) {
        return new Integer(ByteUtil.bytesToHexPrint(data).trim());
    }

    public static byte xor(byte a, byte b) {
        byte c = (byte) (a ^ b);
        return c;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static void main(String[] args) {
        byte[] b = {0x35, 0x35};
        System.out.println("S:" + postgresByteaData(b));

    }

}
