/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

/**
 *
 * @author guiovanny
 */

import com.tncity.jpa.pojo.Promociones;
import com.tncity.jpa.pojoaux.TelefoniaJson;
import static java.lang.System.err;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.JOptionPane.showInputDialog;

public class formatearfecha {

    

     private Boolean validaFechasTransaccion() {

         String finicial = "2020-09-01";
         String ffinal = "2020-09-05";
         
         String sistemafechainicio = "2020-09-06";
         String sistemafechafin ="2020-09-15";

        System.out.println("fecha inicial sistema " + finicial);
        System.out.println("fecha final sistema " + ffinal);
        Boolean vfecha = null;
       

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaini = null;
        Date fechafinal = null;
          Date sistemafechainicial= null;
          Date sistemafechafinal= null;
   

      
     


            System.out.println(" LISTADO DE PROMOCIONES INICIAL sistema : " + sistemafechainicio);
            System.out.println(" fecha inicial   : " + finicial);
            System.out.println(" lLISTADO DE PROMOCIONES FINAL  sistema: " + sistemafechafin);
            System.out.println(" fecha final   : " + ffinal);

            if (fechaini.after(sistemafechainicial) && fechafinal.before(sistemafechafinal)) {

                System.out.println(" caso 1 esta dentro del rango " + finicial + " " + ffinal + "true");

                 vfecha = false;

            }

            if (fechaini.before(sistemafechainicial) && fechafinal.after(sistemafechafinal)) {

                System.out.println("caso 2 esta dentro del rango " + finicial + " " + ffinal);

                 vfecha = false;

            }

            if (fechaini.equals(sistemafechainicial) && fechafinal.equals(sistemafechafinal)) {

                System.out.println("fecha inicial antes de 2 igual " + finicial + "" + sistemafechafinal);
                System.out.println(" caso 3 fecha final antes de 2 igual " + finicial + "" + ffinal);

                 vfecha = false;

            }

         System.out.println("imprime resultado " + vfecha);
        return vfecha;
    }
//    public static void Operations() {
//
//        try {
//            DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
//            String str1 = showInputDialog("Ingrese primera fecha "); // Formato “dd/mm/aa”
//            Date date1 = f.parse(str1);
//            String str2 = showInputDialog("Ingrese segunda fecha"); // Formato “dd/mm/aa”
//            Date date2 = f.parse(str2);
//            Calendar cal1 = new GregorianCalendar();
//            cal1.setTime(date1);
//            Calendar cal2 = new GregorianCalendar();
//            cal2.setTime(date2);
//            System.out.println(
//                    "Fecha Inicial: " + f.format(cal1.getTime())
//                    + "\t Fecha Final: " + f.format(cal2.getTime())
//            );
//            String str3 = showInputDialog("Ingrese fecha a comprobar:"); // Formato “dd/mm/aa”
//            Date date3 = f.parse(str3);
//            Calendar cal3 = new GregorianCalendar();
//            cal3.setTime(date3);
//
//            if (cal3.after(cal1) && cal3.before(cal2)) {
//                System.out.println(f.format("La fecha " + cal3.getTime()) + "esta dentro del rango");
//            } else {
//                System.out.println(
//                        "La fecha " + f.format(cal3.getTime()) + " esta fuera del rango ");
//            }
//        } catch (Exception e) {
//
//        }
//
//    }

    public static void main(String[] args) {
//          Date FechaInicial = null;
//       Date FechaFinal = null;
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//            
//            String  inicial = "2020-07-09";
//            String   finald = "2020-07-10";
//            
//            
//            FechaInicial = formatter.parse(inicial);
//            
//            FechaFinal = formatter.parse(finald);
//          
//   
//              if(FechaFinal.after(FechaInicial)){
//               System.out.println("Date 'FechaInicial' " +  FechaFinal + "is after Date 'FechaFinal' : "+FechaInicial.after(FechaFinal));  
//              }
//              
//            int promoaplica = 60000;
//            int valor = 60100;
//            if (promoaplica <= valor) {
//                System.out.println("dato dado monto especial " + valor);
//                
//            }
            formatearfecha d = new formatearfecha();
            d.validaFechasTransaccion();
            
  
    }
}
