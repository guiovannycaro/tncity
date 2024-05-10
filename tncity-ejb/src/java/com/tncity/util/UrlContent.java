/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class UrlContent {

    String url;

    public UrlContent(String url) {
        this.url = url;
    }

    public String getContent() {
        StringBuilder content = new StringBuilder();
        try {

            SSLTool.disableCertificateValidation();

            URL uri = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(uri.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }
            in.close();
        } catch (Exception e) {
            System.out.println("FALLA, LEYENDO DE URL:" + url);
            e.printStackTrace();
        }
        return content.toString();
    }

    public String getUrl() {
        return url;
    }

    public String getContentByProxy(String ipProxy, int port) {
        StringBuilder content = new StringBuilder();
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipProxy, port));
            //HttpURLConnection connection = (HttpURLConnection) new URL("http://abc.abcd.com").openConnection(proxy);
            InputStream inp = ((HttpURLConnection) new URL(url).openConnection(proxy)).getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inp));
            //System.out.println(connection.usingProxy());
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void main(String[] args) {
        System.out.println(new UrlContent("https://www.google.com/").getContent());
        
        //System.out.println(new UrlContent("http://www.google.com").getContentByProxy("192.168.0.3", 3128));
    }
}
