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
import com.tncity.config.pojoaux.ParametrosRecaudo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

public class POSTutil {

    public static ParametrosRecaudo utilrestfull(String urlString, String obj) {
        try {

      
              ParametrosRecaudo resu = JsonUtil.jsonToObject(obj, ParametrosRecaudo.class);
              
                  String POST_PARAMS = "monto=" + resu.getMonto() + "&origen=" + resu.getOrigen() + ""
                   + "&destino=" + resu.getDestino() + "&epc=" + resu.getEpc() + "&ip=" + resu.getIp() + "&fuente=" + resu.getFuente() + "";
              
            int connectTimeout = 1000;
            HttpURLConnection postConnection = null;

            URL objurl = new URL(urlString);
            postConnection = (HttpURLConnection) objurl.openConnection();
            postConnection.setConnectTimeout(connectTimeout);

            postConnection.setUseCaches(false);
            postConnection.setDoOutput(true);

            postConnection.setRequestMethod(HttpMethod.POST);

            postConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            postConnection.setRequestProperty("charset", "utf-8");
            postConnection.setRequestProperty("Accept", MediaType.APPLICATION_XML);
            postConnection.setRequestProperty("connection", "Keep-Alive");
            postConnection.connect();

            OutputStream os = postConnection.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            //os.close();
            int responseCode = postConnection.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            System.out.println("POST Response Message : " + postConnection.getResponseMessage());

            if (responseCode != HttpURLConnection.HTTP_CREATED) { //su ccess
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result

                System.out.println("Respuesta del Servidor");
                System.out.println(response.toString());

                ParametrosRecaudo res = JsonUtil.jsonToObject(response.toString(), ParametrosRecaudo.class);

                return res;

            } else {
                System.out.println("POST NOT WORKED");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public static void main(String[] args) {

        

        String url = "http://172.30.5.3/restwebtn/recargatn.php";
        String POST = "{\"monto\":\"50000\",\"origen\":\"77777\",\"destino\":\"40337\",\"epc\":\"Bogota\",\"ip\":\"192.168.0.2\",\"fuente\":\"47987\"}";

        
        
        ParametrosRecaudo r = utilrestfull(url, POST);
        System.out.println("ID:" + r.getId());

    }
}
