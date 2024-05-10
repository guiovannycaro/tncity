/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.EncodingUtil;

public class HttpClientExample {

    private String accessToken;
    private String BASE_URL = "https://app.gestionados.co/tnc/api/";

    public HttpClientExample(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    public void sendSMSUsingGet(String message, String destinataires, String Tokensms) {
        try {
            String getURL = BASE_URL + "sendSMS.do";
            GetMethod httpMethod = new GetMethod(getURL);
            httpMethod.addRequestHeader("Content-Type", "plain/text; charset=ISO-8859-15");

            NameValuePair params[] = {new NameValuePair("token_principal", this.accessToken), 
                new NameValuePair("sms", message), 
                new NameValuePair("numero", destinataires), 
                new NameValuePair("token_sms", Tokensms) 
        };

            httpMethod.setQueryString(EncodingUtil.formUrlEncode(params, "ISO-8859-15"));

            System.out.println(httpMethod.getURI() + "" + httpMethod.getQueryString());

            executeMethod(httpMethod);
        } catch (Exception e) {
            manageError(e);
        }
    }

    private void executeMethod(HttpMethod httpMethod) throws IOException, HttpException {
        HttpClient httpClient = new HttpClient();

        System.out.println(httpMethod);
        int codeReponse = httpClient.executeMethod(httpMethod);
        verifyReponse(httpMethod, codeReponse);
    }

    private void verifyReponse(HttpMethod httpMethod, int codeReponse) throws IOException {
        if (codeReponse == HttpStatus.SC_OK || codeReponse == HttpStatus.SC_ACCEPTED) {
            String result = new String(httpMethod.getResponseBody());
            System.out.println(result);
        }
    }

    private void manageError(Exception e) {
        e.printStackTrace();
        System.err.println("Error during API call");
    }

    public void sendSMSUsingPost(String text, String destinataires, String Tokensms) {
        try {
            String postURL = BASE_URL + "sendSMS.do";
            PostMethod httpMethod = new PostMethod(postURL);
            httpMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-15");

            NameValuePair data[] = {new NameValuePair("token_principal", this.accessToken), 
                new NameValuePair("sms", text), //
                new NameValuePair("numero", destinataires), 
                new NameValuePair("token_sms", Tokensms) 
        };
            httpMethod.setRequestBody(data);

            System.out.println("///////////////////////");
            httpMethod.getRequestEntity().writeRequest(System.out);
            executeMethod(httpMethod);

        } catch (Exception e) {
            manageError(e);
        }
    }

    public static void main(String[] args) {
        String destinataires = "3157371236"; 
        String message = "Mensaje de prueba";
        String accessToken = "3dG5jb2-mxvbWJpYSBlbXByZ-pXNhIGNvbiBj-2b24gbml0IDE5-wMC4yNTIuMT-3A3LjUyb";
        String Tokensms = "3dG5jb";

        HttpClientExample client = new HttpClientExample(accessToken);

        client.sendSMSUsingGet(message, destinataires, Tokensms);
        client.sendSMSUsingPost(message, destinataires, Tokensms);
    }
}
