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
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class postgetutil {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "http://api.messaging-service.com/sms/1/text/query";

    private static final String POST_URL = "http://api.messaging-service.com/sms/1/text/query";

    private static final String POST_PARAMS = "username=TNCOLOMBIA&password=Colombia2020&to=573157371236&text=SuRecargahasidoExitosaseharecargadoalSr(ra)GUTIERREZDIEGOElvalorde$20000";

    public static void main(String[] args) throws IOException {

        sendGET();
        System.out.println("GET DONE");
        
       

    }

    private static void sendGET() throws IOException {
        //String urlParameters = "username=TNCOLOMBIA&password=Colombia2020&to=573508441032&text=Su%20Recarga%20ha%20sido%20Exitosa%20se%20ha%20recargado%20al%20Sr%20(ra)%20GUTIERREZ%20DIEGO%20El%20valor%20de%20$20000";
         String urlParameters = "username=TNCOLOMBIA&password=Colombia2020&to=573157371236text=Su%20Recarga%20ha%20sido%20Exitosa%20se%20ha%20recargado%20al%20Sr%20(ra)%20GUTIERREZ%20DIEGO%20El%20valor%20de%20$20000";
        URL url = new URL("http://api.messaging-service.com/sms/1/text/query?" + urlParameters);
        
        System.out.println("url " + url);
        
        URLConnection conn = url.openConnection();

        conn.setDoOutput(true);

 

        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
       
        reader.close();

    }

    private static void sendPOST() throws IOException {
        URL obj = new URL(POST_URL + POST_PARAMS);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
    
     private static final String USER_AGENTd = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";

    public void post(String url, String params) throws Exception {
	String result = null;

	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	con.setRequestMethod("POST");
	con.setRequestProperty("User-Agent", USER_AGENTd);

	con.setDoOutput(true);
	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	wr.writeBytes(params);
	wr.flush();
	wr.close();

	int responseCode = con.getResponseCode();
	System.out.println("'POST' request to URL : " + url);
	System.out.println("Response Code : " + responseCode);

	System.out.println("Response Body : ");
	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
	}
	in.close();

	result = response.toString();
	System.out.println(result);

    }

    public void get(String url, String params) throws Exception {
	String result = null;

	URL obj = new URL(url + "?" + params);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	con.setRequestMethod("GET");

	con.setRequestProperty("User-Agent", USER_AGENTd);

	int responseCode = con.getResponseCode();
	System.out.println("'GET' request to URL : " + url);
	System.out.println("Response Code : " + responseCode);

	System.out.println("Response Body : ");
	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
	}
	in.close();

	result = response.toString();
	System.out.println(result);
    }

  

}
