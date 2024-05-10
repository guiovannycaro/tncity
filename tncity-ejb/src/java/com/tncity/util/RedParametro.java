
package com.tncity.util;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class RedParametro {

    public static List<String> getMacAddress() {
        List<String> lstD = new ArrayList();

        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface ni = en.nextElement();
                List<InterfaceAddress> list = ni.getInterfaceAddresses();
                Iterator<InterfaceAddress> it = list.iterator();

                while (it.hasNext()) {
                    InterfaceAddress ia = it.next();
                    String mac=RedParametro.getMacAddress(ia.getAddress().getHostAddress());
                    if(mac!=null){
                        lstD.add(mac);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("FALLA, leyendo mac de equipo ! ->" + e);
            e.printStackTrace();
        }

        return lstD;
    }

    private static String getMacAddress(String ipAddr)
            throws UnknownHostException, SocketException {
        InetAddress addr = InetAddress.getByName(ipAddr);
        NetworkInterface ni = NetworkInterface.getByInetAddress(addr);
        if (ni == null) {
            return null;
        }

        byte[] mac = ni.getHardwareAddress();
        if (mac == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(18);
        for (byte b : mac) {
            if (sb.length() > 0) {
                sb.append(':');
            }
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> lst=RedParametro.getMacAddress();
        for (String mac : lst) {
            System.out.println("MAC-> "+mac);
        }
    }
    
    
    /*public static void main(String[] args) throws Exception {
     Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
     while (en.hasMoreElements()) {
     NetworkInterface ni = en.nextElement();
     printParameter(ni);

     }
     }

     public static void printParameter(NetworkInterface ni) throws SocketException {
     System.out.println(" Nombre = " + ni.getName());
     System.out.println(" Nombre a mostrar= " + ni.getDisplayName());
     System.out.println(" Está activa = " + ni.isUp());
     System.out.println(" Soporte para multicast = " + ni.supportsMulticast());
     System.out.println(" Es loopback = " + ni.isLoopback());
     System.out.println(" Es virtual = " + ni.isVirtual());
     System.out.println(" Es punto a punto = " + ni.isPointToPoint());
     System.out.println(" Dirección MAC = " + ni.getHardwareAddress());    
     System.out.println(" MTU = " + ni.getMTU());

     System.out.println("\nLista de direcciones de interfaz:");
     List<InterfaceAddress> list = ni.getInterfaceAddresses();
     Iterator<InterfaceAddress> it = list.iterator();

     while (it.hasNext()) {
     InterfaceAddress ia = it.next();
     System.out.println(" Dirección = " + ia.getAddress());
     System.out.println(" Broadcast = " + ia.getBroadcast());
     System.out.println(" Longitud de prefijo de red = " + ia.getNetworkPrefixLength());
     System.out.println("");
     }
     }*/
}
