/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 *
 * @author guiovanny
 */
public class tokenseguridad {


public  String nextToken() { 
      String msm ="";
    try{
  
    String password = "123456";

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString());
        msm =sb.toString();
    }catch(Exception e){
    
    }
       return msm; 
} 

    public static void main(String[] args) {
        tokenseguridad v = new tokenseguridad();
        v. nextToken();
    }

    
}
