/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.pasarelas.Modelos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guiovanny
 */
public class RecaudoDao implements Crud {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conex = new Conexion();
    String idpasarela = "3";
 String STATUS = "VALIDO";
    @Override
    public List listar() {

        List<RecaudoTrans> datos = new ArrayList<>();
        String sql = "select  * from  recaudo "
                + "join beneficiario on recaudo.idbeneficiario = beneficiario.idbeneficiario "
                + "where recaudo.idpasarela = '" + idpasarela + "' and "
                + "recaudo.estado = '"+STATUS+"'   ORDER BY idrecaudo ";
        System.out.println("dt" + sql);
        try {
            con = conex.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                RecaudoTrans u = new RecaudoTrans();
                u.setIdrecaudo(rs.getString("idrecaudo"));
                u.setIdbenefactor(rs.getString("idbenefactor"));
                u.setIdbeneficiario(rs.getString("idbeneficiario"));
                u.setTd(rs.getString("td"));
                u.setPin(rs.getString("pin"));
                u.setTelefonosms(rs.getString("telefonosms"));
                u.setCreated_at(rs.getString("created_at"));
                u.setValor(rs.getString("valor"));
                u.setCiudad(rs.getString("ciudad"));
                u.setLog("log");
                datos.add(u);
            }             
                System.out.println("datos " + datos.get(0).getIdrecaudo() + datos.get(0).getCiudad());
        } catch (Exception er) {

        }
        return datos;
    }
    


    @Override
    public RecaudoTrans listarID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String add(
            String idbenefactor,
            String idbeneficiario,
            String idrecaudo,
            String pin,
            String td,
            String telefonosms,
            String valor,
            String log,
            String codigotransaccion,
            String formaPago,
            String franquicia,
            String descripcion,
            String Referencia1,
            String fechaPago,
            String reciboPago,
            String codigo,
            String mensaje
    ) {

        Connection con = conex.getConnection();
        String rpt = null;

        try {
            String sql = "insert into transacciones (codtransaccion,"
                    + "  formapago,"
                    + "  franquicia,"
                    + "  descripcion,"
                    + "  referencia1,"
                    + "  fechapago,"
                    + "  numerorecibo,"
                    + "  codigo,"
                    + "  mensajeerror,"
                    + "  idrecaudo,"
                    + "  codigoint )"
                    + "  values ('" + codigotransaccion + "','" + formaPago + "','" + franquicia + "',"
                    + "'" + descripcion + "','" + Referencia1 + "','" + fechaPago + "',"
                    + "'" + reciboPago + "'," + codigo + ",'" + mensaje + "','" + idrecaudo + "',"
                    + "'" + 0 + "')";
            System.out.println("consulta " + sql);
            Statement st = con.createStatement();
            int rs = st.executeUpdate(sql);

            if (rs > 0) {
                rpt = "transaccion registrada";

            }
        } catch (SQLException ex) {

        }
        return rpt;

    }

    @Override
    public String edit(int id, String nombre, String ape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecaudoTrans drop(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        RecaudoDao dao = new RecaudoDao();
        dao.listar();
    }

}
