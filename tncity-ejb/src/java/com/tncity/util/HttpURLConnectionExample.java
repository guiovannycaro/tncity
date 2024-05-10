/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.tncity.config.pojoaux.ParametrosRecaudo;
import com.tncity.config.pojoaux.ParametrosSmSText;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

public class HttpURLConnectionExample {

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    private void sendingGetRequest() {
        try {
            String urlString = "http://app.gestionados.co/tnc/api/smsWeb";

            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // By default it is GET request
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("Sending get request : " + url);
            System.out.println("Response code : " + responseCode);

            // Reading response from input Stream
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(
                    con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            //printing result from response
            System.out.println(response.toString());
        } catch (Exception e) {
        }

    }

    // HTTP Post request
    private ParametrosSmSText sendingPostRequest() {
        try {
            StringBuilder response = new StringBuilder();
            String url = "http://app.gestionados.co/tnc/api/smsWeb";
       

            String post = "{\"codigo\":\"123458\",\"numero\":\"5713157371236\",\"mensaje\":\"Prueba\",\"flash\":\"normal\",\"token\":\"3dG5jb2-mxvbWJpYSBlbXByZ-pXNhIGNvbiBj-2b24gbml0IDE5-wMC4yNTIuMT-3A3LjUyb\"}";

           ParametrosSmSText resu = JsonUtil.jsonToObject(post,  ParametrosSmSText.class);
              
//                  String POST_PARAMS = "codigo=" + resu.getCodigo()+ "&origen=" + resu.getNumero()+ ""
//                   + "&mensaje=" + resu.getMensaje()+ "&flash=" + resu.getFlash()+ "&token=" + resu.getToken() +"";
//                    
            int connectTimeout = 1000;
            HttpURLConnection postConnection = null;

            URL objurl = new URL(url);
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
            os.write(post.getBytes());
            os.flush();
            //os.close();
            int responseCode = postConnection.getResponseCode();
            System.out.println("POST Response Code MSM:  " + responseCode);
            System.out.println("POST Response Message MSM: " + postConnection.getResponseMessage());

            if (responseCode != HttpURLConnection.HTTP_CREATED) { //su ccess
                BufferedReader in = new BufferedReader(new java.io.InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result

                System.out.println("Respuesta del Servidor MSM");
                System.out.println(response.toString());

                 ParametrosSmSText res = JsonUtil.jsonToObject(response.toString(),  ParametrosSmSText.class);

                return res;

            } else {
                System.out.println("POST NOT WORKED");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }
    
    // HTTP Post request
    private ParametrosRecaudo sendingPostSteven() {
        try {
         
               String post = "codigo=123458&numero=5713157371236&mensaje=Prueba&flash=normal&token=3dG5jb2-mxvbWJpYSBlbXByZ-pXNhIGNvbiBj-2b24gbml0IDE5-wMC4yNTIuMT-3A3LjUyb";

            
                   
            int connectTimeout = 1000;
            HttpURLConnection postConnection = null;
           URL objurl = new URL("https://app.gestionados.co/tnc/api/smsWeb?" + post );
            
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

            
            //os.close();
            int responseCode = postConnection.getResponseCode();
            System.out.println("POST Response Code Servidor Steven:  " + responseCode);
            System.out.println("POST Response Message Servidor Steven: " + postConnection.getResponseMessage());

            if (responseCode != HttpURLConnection.HTTP_CREATED) { //su ccess
                BufferedReader in = new BufferedReader(new java.io.InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result

                System.out.println("Respuesta del Servidor Steven");
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

        HttpURLConnectionExample http = new HttpURLConnectionExample();


        
      http.sendingPostSteven();

    }
}
