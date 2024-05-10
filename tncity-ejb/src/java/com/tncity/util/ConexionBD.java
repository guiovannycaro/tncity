/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author guiovanny
 */
public class ConexionBD {
    private Connection conexion = null;
    private String urlBD = "jdbc:postgresql://localhost:5432/prueba_reportes";
    private String userBD = "sergio";
    private String passBD = "gaspar";
     
     
    public Connection getConexion() throws Exception{
        Class.forName("org.postgresql.Driver");
        conexion = DriverManager.getConnection(urlBD, userBD, passBD);
        return conexion;
    }
}
