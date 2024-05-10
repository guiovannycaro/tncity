/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class PgsqlManager extends BdManager {

    public PgsqlManager(Integer port, String host, String user, String password, String bd) {
        this.port = port;
        this.host = host;
        this.user = user;
        this.password = password;
        this.bd = bd;
        this.dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + bd;
        JDBC_DRIVER = "org.postgresql.Driver";
    }

    @Override
    public List<List> executeSQlList(String sql, StringBuilder err) {
        List lst = new ArrayList();
        try {
            //Open Connection
            open(err);
            //Execute Query
            ResultSet rs = executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next()) {
                List lst2 = new ArrayList();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    Object object = rs.getObject(i);
                    lst2.add(object);
                }
                lst.add(lst2);
            }
            //Close Connection
            close();
        } catch (Exception e) {
            System.out.println("FALLA, " + e);
            e.printStackTrace();
            err.append(e.toString());
        }
        return lst;
    }

    @Override
    public int executeUpdateDeleteInsert(String sql, StringBuilder err) {
        int n = 0;
        try {
            //Open Connection
            open(err);
            //Execute Query
            n = smt.executeUpdate(sql);

            //Close Connection
            close();
        } catch (Exception e) {
            System.out.println("FALLA, " + e);
            e.printStackTrace();
            err.append(e.toString());
        }
        return n;
    }

    public static void main(String[] args) {
        BdManager bd = new PgsqlManager(5432, "192.168.0.3", "desarrollo", "inmotica123", "sobre5DB");

        List<List> lst = bd.executeSQlList("SELECT * FROM ies", new StringBuilder());
        for (List reg : lst) {
            for (Object col : reg) {
                System.out.println("C:" + col);
            }
            System.out.println("-------------------------------");
        }

        /*int n = bd.executeUpdateDeleteInsert("INSERT INTO ies VALUES(nextval('ies_idies_seq'),'PRUEBA JDBC')", new StringBuilder());
         System.out.println("N:" + n);*/
    }
}