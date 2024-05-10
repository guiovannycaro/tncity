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

/**
 *
 * @author root
 */
public class PostgresUtil {

    String host;
    String user;
    String pass;
    String shema;
    String db;
    int port;

    public PostgresUtil(String host, String user, String pass, String shema, String db, int port) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.shema = shema;
        this.db = db;
        this.port = port;
    }

    public boolean ping(StringBuilder err) {
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.db;

        boolean p = false;
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, pass);
            Statement stmt = con.createStatement();

            stmt.close();
            con.close();
            p = true;
        } catch (Exception e) {
            err.append(e.getMessage() + "\n" + connectString);
        }
        return p;
    }

    public List ejecutarQuery(String sql) {
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.db;
        List lst = new ArrayList();

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, pass);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                List lstInterna = new ArrayList();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    lstInterna.add(rs.getObject(i));
                }
                lst.add(lstInterna);
            }

            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lst;
    }

    public boolean isPrimaryKey(String tabla, String campo) {
        boolean isPK = false;
        String sql = "SELECT "
                + " tc.constraint_name, tc.table_name, kcu.column_name, "
                + " ccu.table_name AS foreign_table_name, "
                + " ccu.column_name AS foreign_column_name  "
                + " FROM  "
                + "  information_schema.table_constraints AS tc  "
                + "  JOIN information_schema.key_column_usage AS kcu ON tc.constraint_name = kcu.constraint_name "
                + "  JOIN information_schema.constraint_column_usage AS ccu ON ccu.constraint_name = tc.constraint_name "
                + " WHERE constraint_type = 'PRIMARY KEY' AND tc.table_name='" + tabla.trim() + "'"
                + "     AND kcu.column_name='" + campo.trim() + "'";
        List lst = this.ejecutarQuery(sql);
        if (lst.size() > 0) {
            isPK = true;
        }
        return isPK;
    }

    public boolean isForeignKey(String tabla, String campo) {
        boolean isPK = false;
        String sql = "SELECT "
                + " tc.constraint_name, tc.table_name, kcu.column_name, "
                + " ccu.table_name AS foreign_table_name, "
                + " ccu.column_name AS foreign_column_name  "
                + " FROM  "
                + "  information_schema.table_constraints AS tc  "
                + "  JOIN information_schema.key_column_usage AS kcu ON tc.constraint_name = kcu.constraint_name "
                + "  JOIN information_schema.constraint_column_usage AS ccu ON ccu.constraint_name = tc.constraint_name "
                + " WHERE constraint_type = 'FOREIGN KEY' AND tc.table_name='" + tabla.trim() + "'"
                + "     AND kcu.column_name='" + campo.trim() + "'";
        List lst = this.ejecutarQuery(sql);
        if (lst.size() > 0) {
            isPK = true;
        }
        return isPK;
    }

    public String getFkDatos(String tabla, String campo, int opt) {
        /**
         * opt=Entre 0 y 4
         */
        String out = "";
        String sql = "SELECT "
                + " tc.constraint_name, tc.table_name, kcu.column_name, "
                + " ccu.table_name AS foreign_table_name, "
                + " ccu.column_name AS foreign_column_name  "
                + " FROM  "
                + "  information_schema.table_constraints AS tc  "
                + "  JOIN information_schema.key_column_usage AS kcu ON tc.constraint_name = kcu.constraint_name "
                + "  JOIN information_schema.constraint_column_usage AS ccu ON ccu.constraint_name = tc.constraint_name "
                + " WHERE constraint_type = 'FOREIGN KEY' AND tc.table_name='" + tabla.trim() + "'"
                + "     AND kcu.column_name='" + campo.trim() + "'";
        List lst = this.ejecutarQuery(sql);
        if (lst.size() > 0) {
            List lst2 = (ArrayList) lst.get(0);
            out = lst2.get(opt).toString();
        }
        return out;
    }

    public boolean isCheckList(String tabla, String campo) {
        boolean isPK = false;
        String sql = "SELECT ordinal_position, tab_columns.column_name, data_type, character_maximum_length, "
                + " numeric_precision, is_nullable, tab_constraints.constraint_type, col_constraints.constraint_name, "
                + " col_check_constraints.check_clause "
                + " FROM information_schema.columns AS tab_columns "
                + " LEFT OUTER JOIN "
                + "   information_schema.constraint_column_usage AS col_constraints "
                + "   ON tab_columns.table_name = col_constraints.table_name AND "
                + "   tab_columns.column_name = col_constraints.column_name "
                + " LEFT OUTER JOIN "
                + " information_schema.table_constraints AS tab_constraints "
                + " ON tab_constraints.constraint_name = col_constraints.constraint_name "
                + " LEFT OUTER JOIN "
                + " information_schema.check_constraints AS col_check_constraints "
                + " ON col_check_constraints.constraint_name = tab_constraints.constraint_name "
                + " WHERE tab_columns.table_name = '" + tabla.trim() + "' "
                + "    AND tab_columns.column_name='" + campo.trim() + "' "
                + "    AND tab_columns.table_schema = '" + this.shema.trim() + "'"
                + "    AND tab_constraints.constraint_type='CHECK'"
                + "      AND col_check_constraints.check_clause LIKE '%ARRAY%'"
                + " ORDER BY ordinal_position";
        List lst = this.ejecutarQuery(sql);
        if (lst.size() > 0) {
            isPK = true;
        }
        return isPK;
    }

    public List<String> getCheckList(String tabla, String campo) {
        String sql = "SELECT ordinal_position, tab_columns.column_name, data_type, character_maximum_length, "
                + " numeric_precision, is_nullable, tab_constraints.constraint_type, col_constraints.constraint_name, "
                + " col_check_constraints.check_clause "
                + " FROM information_schema.columns AS tab_columns "
                + " LEFT OUTER JOIN "
                + "   information_schema.constraint_column_usage AS col_constraints "
                + "   ON tab_columns.table_name = col_constraints.table_name AND "
                + "   tab_columns.column_name = col_constraints.column_name "
                + " LEFT OUTER JOIN "
                + " information_schema.table_constraints AS tab_constraints "
                + " ON tab_constraints.constraint_name = col_constraints.constraint_name "
                + " LEFT OUTER JOIN "
                + " information_schema.check_constraints AS col_check_constraints "
                + " ON col_check_constraints.constraint_name = tab_constraints.constraint_name "
                + " WHERE tab_columns.table_name = '" + tabla.trim() + "' "
                + "    AND tab_columns.column_name='" + campo.trim() + "' "
                + "    AND tab_columns.table_schema = '" + this.shema.trim() + "'"
                + "    AND tab_constraints.constraint_type='CHECK'"
                + "      AND col_check_constraints.check_clause LIKE '%ARRAY%'"
                + " ORDER BY ordinal_position";
        List lst = this.ejecutarQuery(sql);

        List lstCHK = new ArrayList();
        if (lst.size() > 0) {
            List l = (ArrayList) lst.get(0);
            String[] arr = l.get(8).toString().split("=");
            String strArr = "((" + arr[1].trim().replaceAll("ANY", "");

            List l2 = this.ejecutarQuery("SELECT " + strArr);
            List l3 = (ArrayList) l2.get(0);

            String strLst = l3.get(0).toString().trim().replaceAll("\"", "").replaceAll("'", "").replaceAll("}", "");
            strLst = strLst.trim().substring(1, strLst.trim().length());

            String[] arr2 = strLst.split(",");
            for (int i = 0; i < arr2.length; i++) {
                lstCHK.add(arr2[i]);
            }

        }

        return lstCHK;
    }
}
