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
import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.ParametrosRecaudo;
import com.tncity.config.pojoaux.PasarelasPagosConfig;
import com.tncity.config.pojoaux.TelefoniaConfig;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Benefactor;
import com.tncity.jpa.pojo.Beneficiario;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Cuenta;
import com.tncity.jpa.pojo.Cuentamovimiento;
import com.tncity.jpa.pojo.Pasarelapago;
import com.tncity.jpa.pojo.Persona;
import java.util.List;
import com.tncity.jpa.pojo.Recaudo;
import com.tncity.jpa.pojo.Transacciones;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.jpa.pojoaux.PplPojo;
import com.tncity.jpa.pojoaux.TelefoniaJson;
import com.tncity.jpa.pojoaux.TelefoniaManager;
import com.tncity.telefonia.client.Datosrecarga;
import static com.tncity.util.HashUtil.md5;
import com.tncity.util.JsonUtil;
import com.tncity.util.UrlContent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import java.util.Date;

import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import org.hibernate.impl.SessionImpl;

public class POSTutilSms {



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {

            String post = "codigo=123458&numero=5713157371236&mensaje=Prueba&flash=normal&token=3dG5jb2-mxvbWJpYSBlbXByZ-pXNhIGNvbiBj-2b24gbml0IDE5-wMC4yNTIuMT-3A3LjUyb";

            // Send data
            URL myUrl = new URL("https://app.gestionados.co/tnc/api/smsWeb?" + post);

            URLConnection conn = myUrl.openConnection();
            conn.setDoOutput(true);
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            rd.close();

            System.out.println(myUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
