/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guiovanny
 */
public class Conexion {

    Connection con;

    public Conexion() {

        try {

     
               Class.forName("org.postgresql.Driver");
            String db = "jdbc:postgresql://64.225.36.174:5432/tncityDB";
          con = DriverManager.getConnection(db, "desarrollo", "tncolombia123");
          

            

        } catch (Exception ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Connection getConnection() {
        return con;
    }

}
