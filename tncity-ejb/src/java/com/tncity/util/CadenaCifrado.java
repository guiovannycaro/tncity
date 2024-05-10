/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;
/**
 *
 * @author guiovanny
 */
public class CadenaCifrado {

 // Base64 Basic Decoding
    public static String base64Decode(String value) {
        String decodificado = "";
        try {
            byte[] decodedValue = Base64.getDecoder().decode(value);
            return new String(decodedValue, StandardCharsets.UTF_8.toString());        
        } catch(UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String base64Encode(String value) {
        String encodedData = "";
        try {
            encodedData = Base64.getEncoder()
                        .encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));        
        } catch(UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        
        return encodedData;
    }
    public static void main(String[] args) {
       
       

        String input = "5" ;
        System.out.println("Enter a value to Encode : " + input);
        CadenaCifrado c = new CadenaCifrado();
         String codificado = c.base64Encode(input);
        String decodificado = c.base64Decode(codificado);
         
        System.out.println(codificado);
        System.out.println(decodificado);
    }
    
    
}