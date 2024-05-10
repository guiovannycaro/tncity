/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.tncity.Modelos.Conexion;
import com.tncity.config.pojoaux.ParametrosRecaudo;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Departamentoestado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tncity.control.pasarelas.Modelos.Crud;
/**
 *
 * @author guiovanny
 */
public class Actualizabd implements Crud {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conex = new Conexion();



    
     public List listard() {
     PreparedStatement ps;
    ResultSet rs;
    Connection con;
    com.tncity.Modelos.Conexion conex = new com.tncity.Modelos.Conexion();
    
     List<Ciudad> datos = new ArrayList<>();
     String sql = "select * from ciudades order by idciudad asc";

     try {
            con = conex.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Ciudad u = new Ciudad();
                u.setIdciudad(new Integer(rs.getString("idciudad")));
                u.setNombre(rs.getString("nombre"));
                u.setCodoficial(rs.getString("codoficial"));
                u.setLatitud(new Double(rs.getString("latitud")));
                u.setLongitud(new Double(rs.getString("longitud")));
                u.setIddepartamento(new Departamentoestado(new Integer(rs.getString("iddepartamento"))));
               

                datos.add(u);
            }
        } catch (Exception er) {

        }
         System.out.println("datos " + datos.get(0).getNombre());
     return datos;
   }
       public static void main(String[] args) {
        Actualizabd dt = new Actualizabd();
        dt.listard();
  }

}
