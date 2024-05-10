package com.tncity.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
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
 * Envio de correo con JavaMail
 *
 * @author root
 *
 */
public class MailUtil {

    Properties smtpConfig;
    Session sessionSmtp;
    Transport transport;
    String usuario;
    String password;
    String alias;

    /**
     * main de prueba
     *
     * @param args Se ignoran.
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Dirección de Email: ");
        String usuario = scan.next();

        System.out.println("Password de Email: ");
        String password = scan.next();

        if (usuario.isEmpty() || password.isEmpty()) {
            System.out.println("Recuerde especificar el email y el password para la conexión. (Implementado solo para Gmail).");
            System.exit(1);
        }

        try {
            MailUtil app = new MailUtil("smtp.gmail.com", "587", usuario, "Prueba Sobre5", password);

            MensajeEmail msg = app.new MensajeEmail();
            msg.toDestinatarios = new ArrayList<String>();
            msg.toDestinatarios.add("andresfgordillom@gmail.com");
            //msg.toDestinatarios.add("nao2.2@hotmail.com");
            //msg.toDestinatarios.add("yerk_sophos@hotmail.com");
            msg.ccDestinatarios = null;
            msg.bccDestinatarios = null;

            msg.asunto = "Adjunto desde Java";
            msg.contenido = "<html><body>Mensaje con <u>Java Mail</u> y <b>html</b>. <a href=\"http://www.incap.edu.co \">INCAP</a></body></html>";

            msg.adjuntos = new ArrayList<String>();
            msg.adjuntos.add("/opt/adjunto.txt");

            app.conectarSMTP();

            //app.sendTxt(msg);
            String result = app.sendComplex(msg);

            app.desconectar();

            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    /**
     * Establece los parámetros de conexión a la cuenta de Email registrada en
     * los parámetros del sistema
     */
    public MailUtil(String host, String port, String user, String alias, String password) throws Exception {
        try {
            this.usuario = user;
            this.password = password;
            this.alias = alias;

            if (host.isEmpty() || port.isEmpty() || user.isEmpty() || password.isEmpty()) {
                throw new Exception("¡Los parámetros de configuración de Correo Electrónico No son correctos!");
            }

            // Propiedades de la conexión smtp
            smtpConfig = new Properties();
            smtpConfig.setProperty("mail.smtp.host", host);
            smtpConfig.setProperty("mail.smtp.port", port);
            smtpConfig.setProperty("mail.smtp.starttls.enable", "true");
            smtpConfig.setProperty("mail.smtp.auth", "true");
            //smtpConfig.setProperty("mail.smtp.user", userAddress);

            // Preparando la sesion smtp
            sessionSmtp = Session.getInstance(smtpConfig);
            // sessionSmtp.setDebug(true);

            transport = sessionSmtp.getTransport("smtp");

        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getUsuario() {
        return usuario;
    }

    /**
     * Permite cambiar el usuario por defecto de la conexión a Email (Sólo
     * implementado Gmail)
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Permite cambiar el password por defecto de la conexión a Email
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Establece una conexión SMTP para envío de mensajes de Email.
     *
     * @return
     */
    private String conectarSMTP() {
        String result = "";
        try {
            if (!transport.isConnected()) {
                transport.connect(usuario, password);
                //System.out.println("Conectando smtp...");
            }
        } catch (Exception ex) {
            result = "Error: " + ex.toString();
        }
        return result;
    }

    /**
     * Desconecta la sesión POP3 y SMTP si están abiertas.
     *
     * @return
     */
    private String desconectar() {
        String result = "";
        try {
            if (transport.isConnected()) {
                transport.close(); //Cierra conexion smtp
            }
            //System.out.println("Desconectando POP3 y SMTP...");
        } catch (Exception ex) {
            result = "Error: " + ex.toString();
        }
        return result;
    }

    private Address[] leerDestinatarios(List<String> destinatarios) {
        List<Address> lstDir = new ArrayList<>();
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

    /**
     * Envia un mensaje de correo electronico ignorando los adjuntos.
     *
     * @param msg Mensaje a enviar con asunto, mensaje y destinatarios.
     * @return Cadena vacia o en caso de error envia el mensaje de la excepcion
     * causada.
     */
    private String sendTxt(MensajeEmail msg) {
        String result = "";
        try {

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(sessionSmtp);

            message.setFrom(new InternetAddress(usuario, alias));

            if (msg.toDestinatarios != null && !msg.toDestinatarios.isEmpty()) {
                message.addRecipients(Message.RecipientType.TO, leerDestinatarios(msg.toDestinatarios));
            }

            if (msg.ccDestinatarios != null && !msg.ccDestinatarios.isEmpty()) {
                message.addRecipients(Message.RecipientType.CC, leerDestinatarios(msg.ccDestinatarios));
            }

            if (msg.bccDestinatarios != null && !msg.bccDestinatarios.isEmpty()) {
                message.addRecipients(Message.RecipientType.BCC, leerDestinatarios(msg.bccDestinatarios));
            }

            message.setSubject(msg.asunto);
            message.setText(msg.contenido);

            // Lo enviamos.
            if (transport.isConnected()) {
                transport.sendMessage(message, message.getAllRecipients());
            } else {
                result = "Tu cuenta se ha desconectado.";
            }
        } catch (Exception e) {
            result = "Error: " + e.toString();
        }

        return result;
    }

    /**
     * Envia un mensaje de correo electronico utilizando archivos adjuntos.
     *
     * @param msg Mensaje a enviar con asunto, mensaje, adjuntos y
     * destinatarios.
     * @return Cadena vacia o en caso de error envia el mensaje de la excepcion
     */
    private String sendComplex(MensajeEmail msg) {
        String result = "";

        try {
            MimeMultipart multiParte = new MimeMultipart();

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setContent(msg.getContenido(), "text/html");
            multiParte.addBodyPart(texto);

            // Se compone el adjunto con el archivo
            if (msg.getAdjuntos() != null && !msg.getAdjuntos().isEmpty()) {
                for (String path : msg.getAdjuntos()) {
                    MimeBodyPart adjunto = new MimeBodyPart();
//                    adjunto.setDataHandler(new DataHandler(new FileDataSource(path)));
//                    adjunto.setFileName(adjunto.getDataHandler().getDataSource().getName());
                    adjunto.attachFile(new File(path));
                    multiParte.addBodyPart(adjunto);
                }
            }
            // Se compone el correo, especificando: to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(sessionSmtp);
            message.setFrom(new InternetAddress(usuario, alias));

            if (msg.getToDestinatarios() != null && !msg.getToDestinatarios().isEmpty()) {
                message.addRecipients(Message.RecipientType.TO, leerDestinatarios(msg.getToDestinatarios()));
            }

            if (msg.getCcDestinatarios() != null && !msg.getCcDestinatarios().isEmpty()) {
                message.addRecipients(Message.RecipientType.CC, leerDestinatarios(msg.getCcDestinatarios()));
            }

            if (msg.getBccDestinatarios() != null && !msg.getBccDestinatarios().isEmpty()) {
                message.addRecipients(Message.RecipientType.BCC, leerDestinatarios(msg.getBccDestinatarios()));
            }

            message.setSubject(msg.getAsunto());
            message.setContent(multiParte);

            // Se envia el correo.
            if (transport.isConnected()) {
                transport.sendMessage(message, message.getAllRecipients());
            } else {
                result = "Tu cuenta se ha desconectado.";
            }
        } catch (Exception e) {
            result = "Error: " + e.toString();
        }

        //System.out.println("RES:" + result);
        return result;
    }

    /**
     * Clase auxiliar que representa un mensaje de Email
     */
    private class MensajeEmail {

        String asunto;
        String contenido;
        List<String> toDestinatarios;
        List<String> ccDestinatarios;
        List<String> bccDestinatarios;
        List<String> adjuntos;

        public MensajeEmail() {
        }

        public List<String> getToDestinatarios() {
            return toDestinatarios;
        }

        public void setToDestinatarios(List<String> toDestinatarios) {
            this.toDestinatarios = toDestinatarios;
        }

        public List<String> getCcDestinatarios() {
            return ccDestinatarios;
        }

        public void setCcDestinatarios(List<String> ccDestinatarios) {
            this.ccDestinatarios = ccDestinatarios;
        }

        public List<String> getBccDestinatarios() {
            return bccDestinatarios;
        }

        public void setBccDestinatarios(List<String> bccDestinatarios) {
            this.bccDestinatarios = bccDestinatarios;
        }

        public String getAsunto() {
            return asunto;
        }

        public void setAsunto(String asunto) {
            this.asunto = asunto;
        }

        public String getContenido() {
            return contenido;
        }

        public void setContenido(String contenido) {
            this.contenido = contenido;
        }

        public List<String> getAdjuntos() {
            return adjuntos;
        }

        public void setAdjuntos(List<String> adjuntos) {
            this.adjuntos = adjuntos;
        }
    }

    public void enviarMailTxtHilo(String emailTo, String asunto, String contenido) {
        MensajeEmail msg = new MensajeEmail();
        List<String> lstDes = new ArrayList<>();
        lstDes.add(emailTo);

        msg.setToDestinatarios(lstDes);
        msg.setAsunto(asunto);
        msg.setContenido(contenido);

        new HiloMail(msg, HiloMail.ENVIAR_MAIL).start();
    }

    public void enviarMailTxtHilo(List<String> destinatarios, String asunto, String contenido) {
        MensajeEmail msg = new MensajeEmail();

        msg.setBccDestinatarios(destinatarios);
        msg.setAsunto(asunto);
        msg.setContenido(contenido);

        new HiloMail(msg, HiloMail.ENVIAR_MAIL).start();
    }

    public void enviarMailComplexHilo(String destinatario, String asunto, String contenido) {
        List<String> l = new ArrayList<String>();
        l.add(destinatario);
        enviarMailComplexHilo(l, new ArrayList<String>(), asunto, contenido);
    }

    public void enviarMailComplexHilo(List<String> destinatarios, String asunto, String contenido) {
        enviarMailComplexHilo(destinatarios, new ArrayList<String>(), asunto, contenido);
    }

    public void enviarMailComplexHilo(List<String> destinatarios, List<String> adjuntos, String asunto, String contenido) {
        MensajeEmail msg = new MensajeEmail();

        msg.setBccDestinatarios(destinatarios);
        msg.setAsunto(asunto);
        msg.setContenido(contenido);
        msg.setAdjuntos(adjuntos);

        new HiloMail(msg, HiloMail.ENVIAR_MAIL_COMPLEX).start();
    }

    private class HiloMail extends Thread {

        public final static int ENVIAR_MAIL = 1;
        public final static int ENVIAR_MAIL_COMPLEX = 2;
        int action = 0;
        MensajeEmail msg;

        public HiloMail(MensajeEmail msg, int action) {
            this.msg = msg;
            this.action = action;
        }

        public void run() {
            if (action == ENVIAR_MAIL) {
                conectarSMTP();
                sendTxt(msg);
                desconectar();
            }
            if (action == ENVIAR_MAIL_COMPLEX) {
                conectarSMTP();
                sendComplex(msg);
                desconectar();
            }
        }
    }
}
