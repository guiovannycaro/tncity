/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLTool {

    public static void disableCertificateValidation() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

        // Ignore differences between given hostname and certificate hostname
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
        } catch (Exception e) {
        }
    }

    /**
     * Reads a Java keystore from a file.
     *
     * @param keystoreFile keystore file to read
     * @param password password for the keystore file
     * @param keyStoreType type of keystore, e.g., JKS or PKCS12
     * @return the keystore object
     * @throws KeyStoreException if the type of KeyStore could not be created
     * @throws IOException if the keystore could not be loaded
     * @throws NoSuchAlgorithmException if the algorithm used to check the
     * integrity of the keystore cannot be found
     * @throws CertificateException if any of the certificates in the keystore
     * could not be loaded
     */
    public static KeyStore loadKeyStore(final File keystoreFile,
            final String password, final String keyStoreType)
            throws KeyStoreException, IOException, NoSuchAlgorithmException,
            CertificateException {
        if (null == keystoreFile) {
            throw new IllegalArgumentException("Keystore url may not be null");
        }
        System.out.println("Initializing key store: {}" + keystoreFile.getAbsolutePath());
        final URI keystoreUri = keystoreFile.toURI();
        final URL keystoreUrl = keystoreUri.toURL();
        final KeyStore keystore = KeyStore.getInstance(keyStoreType);
        InputStream is = null;
        try {
            is = keystoreUrl.openStream();
            keystore.load(is, null == password ? null : password.toCharArray());
            System.out.println("Loaded key store");
        } finally {
            if (null != is) {
                is.close();
            }
        }
        return keystore;
    }

    public static void main(String[] args) {
        try {
            KeyStore ks=loadKeyStore(new File("/opt/glassfish4/glassfish/domains/domain1/config/keystore.jks"), "changeit", "JKS");
            
            Enumeration<String> alias=ks.aliases();
            while(alias.hasMoreElements()){
                String a =alias.nextElement();
                Certificate c=ks.getCertificate(a);
                System.out.println("A("+a+"):\n"+c.toString());
                System.out.println("-----------------------------------------------");
                //System.out.println(String.format("0x%02X", c.getEncoded()[0]));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
