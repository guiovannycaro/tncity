    /*  
  
 # FIREWALL open 3303 in O.S
 # FIREWALL open 3306 in O.S
 * 
 *#my.cnf  -> bind-address 0.0.0.0
 * 
 #CREAR USUARIO
 CREATE USER 'nombre_usuario'@'localhost' IDENTIFIED BY 'tu_contrasena';
 GRANT ALL PRIVILEGES ON * . * TO 'nombre_usuario'@'localhost';

    
 #Permisos MySQL remoto
 mysql>GRANT ALL ON *.* to root@'xxx.xxx.xxx.xxx' IDENTIFIED BY 'contraseña_de_root';
 mysql> FLUSH PRIVILEGES;
 mysql>GRANT ALL PRIVILEGES ON *.* to root@'%' IDENTIFIED BY 'contraseña_de_root';
 mysql> FLUSH PRIVILEGES;
 mysql> exit;
 service mysqld restart
 
 #SI NADA FUNCIONO
 Ir a al archivo my.cnf y cambiar o mejor dicho comentar la linea:
 bind-address   = 127.0.0.1 
 por
 #bind-address   = 127.0.0.1
 #skip-external-locking
  
 */
package com.tncity.util;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class MySqlManager extends BdManager {

    public MySqlManager(Integer port, String host, String user, String password, String bd) {
        this.port = port;
        this.host = host;
        this.user = user;
        this.password = password;
        this.bd = bd;
        this.dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + bd;
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
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
            return null;
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
        BdManager bd = new MySqlManager(3306, "192.168.0.22", "root", "inmotica123", "koha_colombo");

        /*List<List> lst = bd.executeSQlList("SELECT * FROM borrowers", new StringBuilder());
         for (List reg : lst) {
         System.out.println("borrowernumber: " + reg.get(0));
         System.out.println("cardnumber: " + reg.get(1));
         System.out.println("surname: " + reg.get(2));
         System.out.println("firstname: " + reg.get(3));
         System.out.println("-------------------------------");
         }*/

        /*StringBuilder error = new StringBuilder();
         int n = bd.executeUpdateDeleteInsert("INSERT INTO mdl_user_enrolments (enrolid,userid,timestart,timeend,modifierid,timecreated,timemodified) VALUES (1,9,1466632800,0,2,1466636584,1466636584)", error);
         System.out.println("Error: " + error);
         System.out.println("N:" + n);*/

        String buff = new Archivo(new File("/opt/dianaluna2.jpg")).getContent();
        //System.out.println(buff);
        bd.executeUpdateDeleteInsert("INSERT INTO patronimage VALUES(15621,'image/jpg','" + buff.replaceAll("'", "\\'") + "')", new StringBuilder());
    }
}
