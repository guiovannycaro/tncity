/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author edwar
 */
public class SmtpUtil {

    private static SmtpUtil smtpUtil;

    private Session sessionSmtp;
    private Transport transport;
    private String smtpHost;
    private String smtpUser;
    private String smtpAlias;
    private String smtpPass;
    private int smtpPort;

    private SmtpUtil() {
    }

    public static SmtpUtil getInstance() {
        if (smtpUtil == null) {
            smtpUtil = new SmtpUtil();
        }
        return smtpUtil;
    }

    public void connect(String smtpHost, String smtpUser, String smtpPass, String smtpAlias, int smtpPort, StringBuilder err) {
        this.smtpHost = smtpHost;
        this.smtpAlias = smtpAlias;
        this.smtpUser = smtpUser;
        this.smtpPass = smtpPass;
        this.smtpPort = smtpPort;

        Properties smtpConfig = new Properties();
        smtpConfig.setProperty("mail.smtp.host", smtpHost);
        smtpConfig.setProperty("mail.smtp.port", smtpPort + "");
        smtpConfig.setProperty("mail.smtp.starttls.enable", "true");
        smtpConfig.setProperty("mail.smtp.auth", "true");

        try {
            sessionSmtp = Session.getInstance(smtpConfig);
            transport = sessionSmtp.getTransport("smtp");

            closeConnect();

            if (!transport.isConnected()) {
                //System.out.println("conectarSMTP uss:" + usuario + " / pss: " + password);
                transport.connect(smtpUser, smtpPass);
                //System.out.println("Conectando smtp...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void closeConnect() {
        try {
            if (transport.isConnected()) {
                transport.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnect() {
        if (transport == null) {
            return false;
        }
        try {
            return transport.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Address[] leerDestinatarios(List<String> destinatarios) {
        List<Address> lstDir = new ArrayList<Address>();
        for (int i = 0; i < destinatarios.size(); i++) {
            try {
                Address a = new InternetAddress(destinatarios.get(i));
                lstDir.add(a);
            } catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }
        }

        return lstDir.toArray(new Address[0]);
    }

    public void sendMsgHtml(String to, List<File> lstAdjuntos, String asunto, String contenido, StringBuilder err) {
        List<String> lstTO = new ArrayList<>();
        lstTO.add(to);
        sendMsgHtml(lstTO, new ArrayList<String>(), new ArrayList<String>(), lstAdjuntos, asunto, contenido, err);
    }

    public void sendMsgHtml(List<String> lstTO, List<File> lstAdjuntos, String asunto, String contenido, StringBuilder err) {
        sendMsgHtml(lstTO, new ArrayList<String>(), new ArrayList<String>(), lstAdjuntos, asunto, contenido, err);
    }

    public void sendMsgHtml(List<String> lstTO, List<String> lstCC, List<String> lstBCC, List<File> lstAdjuntos, String asunto, String contenido, StringBuilder err) {

        try {
            if (!transport.isConnected()) {
                connect(smtpHost, smtpUser, smtpPass, smtpAlias, smtpPort, err);
            }

            MimeMultipart multiParte = new MimeMultipart();
            BodyPart texto = new MimeBodyPart();
            texto.setContent(contenido, "text/html; charset=UTF-8");
            multiParte.addBodyPart(texto);

            //Adjuntos
            for (File f : lstAdjuntos) {
                MimeBodyPart adjunto = new MimeBodyPart();
                // adjunto.setDataHandler(new DataHandler(new FileDataSource(path)));
                // adjunto.setFileName(adjunto.getDataHandler().getDataSource().getName());
                adjunto.attachFile(f);
                multiParte.addBodyPart(adjunto);
            }

            MimeMessage message = new MimeMessage(sessionSmtp);
            message.setFrom(new InternetAddress(smtpUser, smtpAlias));

            //Destinatarios
            message.addRecipients(Message.RecipientType.TO, leerDestinatarios(lstTO));
            message.addRecipients(Message.RecipientType.CC, leerDestinatarios(lstCC));
            message.addRecipients(Message.RecipientType.BCC, leerDestinatarios(lstBCC));

            //Enviando Correo
            message.setSubject(asunto);
            message.setContent(multiParte);

            if (transport.isConnected()) {
                transport.sendMessage(message, message.getAllRecipients());
            } else {
                err.append("Cuenta desconectada !");
            }

        } catch (Exception e) {
            e.printStackTrace();
            err.append(e.toString());
        }
    }

    public static void main(String[] args) {

        String to = "guiovanny.caro@tncolombia.com.co";
        String host = "smtp.office365.com";
        int port = 587;
        String user = "guiovanny.caro@tncolombia.com.co";
        String alias = "Notificaciones TNFactor V2";
        String pass = "";
        String asunto = "Correo de prueba";
        String contenido = "Hello world <b>HTML</b>";

        List<File> lstAdjuntos = new ArrayList<>();
        //lstAdjuntos.add(new File("/opt/hola.txt"));

        StringBuilder err = new StringBuilder();
        System.out.println("Conectando....");
        SmtpUtil.getInstance().connect(host, user, pass, alias, port, err);
        if (!err.toString().isEmpty()) {
            System.out.println("ERROR-CONNECT: " + err.toString());
        }

        System.out.println("Enviando mail...");
        SmtpUtil.getInstance().sendMsgHtml(to, lstAdjuntos, asunto, contenido, err);
        if (!err.toString().isEmpty()) {
            System.out.println("ERROR-SEND: " + err.toString());
        }

        SmtpUtil.getInstance().closeConnect();

        if (err.toString().isEmpty()) {
            System.out.println("Correo Enviado a " + to);
        }

    }
}
