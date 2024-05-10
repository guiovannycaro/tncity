/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 * @author Edwar Rojas
 */
public class SystemComand {

    Process processDuration;

    public String executeComandLarge(String cmd, StringBuilder err) {
        String[] arr = cmd.split(" ");
        return executeComandLarge(arr, err);
    }

    public void stopCommandLarge() {
        try {
            processDuration.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String executeComandLarge(String cmd[], StringBuilder err) {
        String outputJson = "";
        try {
            processDuration = new ProcessBuilder(cmd).redirectErrorStream(true).start();
            StringBuilder strBuild = new StringBuilder();
            try (BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(processDuration.getInputStream(), Charset.defaultCharset()));) {
                String line;
                while ((line = processOutputReader.readLine()) != null) {
                    strBuild.append(line + System.lineSeparator());
                }
                processDuration.waitFor();
            }
            outputJson = strBuild.toString().trim();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
        }
        return outputJson;
    }

    public static String executeComand(String cmd, StringBuilder err) {
        String[] arr = cmd.split(" ");
        return executeComand(arr, err);
    }

    public static String executeComand(String cmd[], StringBuilder err) {
        String outputJson = "";
        try {
            Process processDuration = new ProcessBuilder(cmd).redirectErrorStream(true).start();
            StringBuilder strBuild = new StringBuilder();
            try (BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(processDuration.getInputStream(), Charset.defaultCharset()));) {
                String line;
                while ((line = processOutputReader.readLine()) != null) {
                    strBuild.append(line + System.lineSeparator());
                }
                processDuration.waitFor();
            }
            outputJson = strBuild.toString().trim();
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
        }
        return outputJson;
    }

    public static String executeComandNoWait(String cmd, StringBuilder err) {
        StringBuilder ret = new StringBuilder();
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            return getStringFromInputStream(proc.getInputStream());
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
        }
        return ret.toString();
    }

    public static String executeComandNoWait(String cmd[], StringBuilder err) {
        StringBuilder ret = new StringBuilder();
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            return getStringFromInputStream(proc.getInputStream());
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
        }
        return ret.toString();
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

   
}
