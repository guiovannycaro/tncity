/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BdManager {

    protected String JDBC_DRIVER;
    protected String user;
    protected Integer port;
    protected String host;
    protected String password;
    protected String bd;
    protected String dbUrl;
    protected Connection conn;
    protected Statement smt;

    public Connection getConn() {
        return conn;
    }

    public Statement getSmt() {
        return smt;
    }

    public void open(StringBuilder err) {
        try {
            conn = connect(err);
            smt = conn.createStatement();
        } catch (Exception e) {
            System.out.println("FALLA, conectandose a BD ->" + dbUrl);
            err.append("FALLA, conectandose a BD");
            e.printStackTrace();
            return;
        }
    }

    public void openForTransaccion(StringBuilder err) {
        try {
            conn = connect(err);
            smt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("FALLA, conectandose a BD ->" + dbUrl);
            err.append("FALLA, conectandose a BD");
            e.printStackTrace();
            return;
        }
    }

    private Connection connect(StringBuilder err) {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(dbUrl, user, password);

        } catch (Exception e) {
            System.out.println("FALLA, conectando driver de BD ->" + dbUrl);
            err.append("FALLA, conectando driver de BD -> ").append(dbUrl);
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            return smt.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("FALLA, ejecutando consulta en BD ->" + e);
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            smt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("FALLA, cerrando conexión a BD ! ->" + e);
            e.printStackTrace();
        }
    }

    public List<List> executeSQlList(Statement stmt, String sql, StringBuilder err) {
        List lst = new ArrayList();
        try {
            //Execute Query
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next()) {
                List lst2 = new ArrayList();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    Object object = rs.getObject(i);
                    lst2.add(object);
                }
                lst.add(lst2);
            }

        } catch (Exception e) {
            System.out.println("FALLA ejecutando consulta de lista, " + e);
            e.printStackTrace();
            err.append(e.toString());
            return null;
        }
        return lst;

    }

    public int executeUpdateDeleteInsert(Statement stmt, String sql, StringBuilder err) {
        int n = 0;
        try {
            //Execute Query
            n = stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("FALLA ejecutando consulta de U.D.I, " + e);
            e.printStackTrace();
            err.append(e.toString());
        }
        return n;
    }

    public abstract List<List> executeSQlList(String sql, StringBuilder err);

    public abstract int executeUpdateDeleteInsert(String sql, StringBuilder err);
}
