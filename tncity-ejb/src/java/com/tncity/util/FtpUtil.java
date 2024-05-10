/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.zehon.sftp.SFTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * Para que pueda conetarse agregue las lineas al archivo del servidor:
 * /etc/ssh/sshd_config
 *
 *
 * KexAlgorithms +diffie-hellman-group1-sha1
 *
 * Ciphers +aes128-cbc
 *
 *
 */
public class FtpUtil {

    public final static String PROTOCOL_FPT = "ftp";
    public final static String PROTOCOL_SFPT = "sftp";
    private FTPClient ftp = null;
    SFTPClient sftp = null;
    String host = "";
    int port = 0;
    String user = "";
    String pass = "";
    String protocol = "";

    public FtpUtil(String host, int port, String user, String pass) {

        ftp = new FTPClient();
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;

        connect();
    }

    public FtpUtil(String host, int port, String user, String pass, String protocol) {

        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.protocol = protocol;

        if (!isSFTP()) {
            ftp = new FTPClient();
            connect();
        } else {
            sftp = new SFTPClient(this.host, this.port, this.user, this.pass);
        }
    }

    private boolean isSFTP() {
        if (this.protocol.trim().equals(PROTOCOL_SFPT)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * **********************
     * GENERAL METHODS *********************
     */
    public InputStream getFileStream(String remoteFilePath) {
        if (!isSFTP()) {
            return getFileStream_FTP(remoteFilePath);
        } else {
            return getFileStream_SFTP(remoteFilePath);
        }
    }

    public void putFile(String localFileFullName, String fileName, String hostDir) {
        if (!isSFTP()) {
            putFile_FTP(localFileFullName, fileName, hostDir);
        }
    }

    public void putFile(InputStream input, String fileName, String hostDir, StringBuilder err) {
        if (!isSFTP()) {
            putFile_FTP(input, fileName, hostDir, err);
        } else {
            putFile_SFTP(input, fileName, hostDir);
        }
    }

    public void deleteFile(String path) {
        try {
            connect();
            ftp.deleteFile(path);
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FTPFile[] listFiles(String root) {
        if (!isSFTP()) {
            return listFiles_FTP(root);
        } else {
            return listFiles_SFTP(root);
        }
    }

    public boolean makeDir(String path) {
        if (!isSFTP()) {
            return makeDir_FTP(path);
        } else {
            return makeDir_SFTP(path);
        }
    }

    /**
     * **********************
     * SFTP METHODS *********************
     */
    private InputStream getFileStream_SFTP(String remoteFilePath) {
        File f = new File(remoteFilePath);
        try {
            return sftp.getFileAsStream(f.getName(), f.getParent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void putFile_SFTP(InputStream input, String fileName, String hostDir) {
        try {
            sftp.sendFile(input, fileName, hostDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteFile_SFTP(String path) {
        try {
            File f = new File(path);
            sftp.deleteFile(f.getName(), f.getParent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FTPFile[] listFiles_SFTP(String root) {
        List<FTPFile> lstF = new ArrayList<FTPFile>();
        try {
            String[] files = sftp.getFileNamesInFolder(root);
            for (String f : files) {
                FTPFile file = new FTPFile();
                file.setName(f);
                if (sftp.folderExists(root + "/" + f)) {
                    file.setType(FTPFile.DIRECTORY_TYPE);
                } else {
                    file.setType(FTPFile.FILE_TYPE);
                }
                Calendar c = Calendar.getInstance();
                c.setTime(new Date(sftp.getLastModificationTime(root + "/" + f)));
                file.setTimestamp(c);

                lstF.add(file);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstF.toArray(new FTPFile[0]);
    }

    private boolean makeDir_SFTP(String path) {
        try {
            File f = new File(path);
            if (!sftp.folderExists(path)) {
                sftp.createFolder(f.getName(), f.getParent());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * **********************
     * FTP METHODS *********************
     */
    private InputStream getFileStream_FTP(String remoteFilePath) {
        connect();
        InputStream is = downloadFile(remoteFilePath);
        disconnect();
        return is;
    }

    private void putFile_FTP(String localFileFullName, String fileName, String hostDir) {
        connect();
        uploadFile(localFileFullName, fileName, hostDir);
        disconnect();
    }

    private void putFile_FTP(InputStream input, String fileName, String hostDir, StringBuilder err) {
        connect();
        uploadFile(input, fileName, hostDir, err);
        disconnect();
    }

    private void deleteFile_FTP(String path) {
        try {
            connect();
            ftp.deleteFile(path);
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private FTPFile[] listFiles_FTP(String root) {
        connect();
        try {
            ftp.changeWorkingDirectory(root);
            FTPFile[] l = ftp.listFiles();
            disconnect();
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private boolean makeDir_FTP(String path) {
        try {
            connect();
            path = path.replaceAll("//", "/");

            String[] dirs = path.split("/");
            String acum = "";

            boolean s = false;
            for (int i = 0; i < dirs.length; i++) {
                String string = dirs[i];

                if (!string.isEmpty()) {
                    acum = acum + "/" + string;
                    s = ftp.makeDirectory(acum);
                }
            }

            disconnect();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void connect() {
        //DESCOMENTAR ESTA LINEA PARA VER EN CONSOLA LOG DE SERVIDOR FTP
        //ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        int reply;
        try {
            disconnect();
            ftp.connect(host);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new Exception("Exception in connecting to FTP Server");
            }
            ftp.login(user, pass);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void uploadFile(String localFileFullName, String fileName, String hostDir) {
        try {
            connect();
            FileInputStream input = new FileInputStream(new File(localFileFullName));
            String name = hostDir + "/" + fileName;
            name = name.replaceAll("//", "/");
            this.ftp.storeFile(name, input);
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadFile(InputStream input, String fileName, String hostDir, StringBuilder err) {
        try {
            connect();
            String name = hostDir + "/" + fileName;
            name = name.replaceAll("//", "/");
            this.ftp.storeFile(name, input);
            disconnect();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
        }
    }

    private InputStream downloadFile(String remoteFilePath) {
        try {
            return ftp.retrieveFileStream(remoteFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void disconnect() {
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already downloaded from FTP server
                f.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //TEST FTP
        /*System.out.println("=========== FTP ==============");
        FtpUtil ftpu = new FtpUtil("localhost", 21, "pruebaftp", "123", FtpUtil.PROTOCOL_FPT);
        //ftpu.getFileStream("/jt.zip");
        FTPFile[] l = ftpu.listFiles("/");

        for (FTPFile f : l) {
            System.out.println("F:" + f.getName());
            System.out.println("Z:" + f.getSize());
            System.out.println("R:" + f.getTimestamp().getTime());
            System.out.println("-----------------------");
        }
        //System.out.println("C:" + ftpu.makeDir("/sitio"));*/

        //TEST SFTP
        System.out.println("=========== SFTP ==============");
        FtpUtil ftpu = new FtpUtil("192.168.0.14", 22, "root", "itron123", FtpUtil.PROTOCOL_SFPT);
        FTPFile[] l = ftpu.listFiles("/");
        for (FTPFile f : l) {
            System.out.println("F:" + f.getName());
            System.out.println("-----------------------");
        }
    }
}
