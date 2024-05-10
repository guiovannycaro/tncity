/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author guiovanny
 */



public class Dating {
    public static void main(String[] args) {

        String startDate = "2014-09-10";
        String endDate = "2014-09-15";

        try {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       
         Date   start = formatter.parse(startDate);
           Date end = formatter.parse(endDate);

            System.out.println(start);
            System.out.println(end);

            if (start.compareTo(end) > 0) {
                System.out.println("start is after end");
            } else if (start.compareTo(end) < 0) {
                System.out.println("start is before end");
            } else if (start.compareTo(end) == 0) {
                System.out.println("start is equal to end");
            } else {
                System.out.println("Something weird happened...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
