/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

/**
 *
 * @author Sebastian Montes
 */
public class AleatorioUtil {

    /**
     * Genera un String aleatorio dado el número de caracteres deseado 'lenght'
     *
     * @param n numero de caracteres
     * @return
     */
    public static String generarString(int n) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < n; i++) {
            char letra = (char) aleatorioEntre(65, 90);
            sb.append(letra);
        }

        return sb.toString();
    }

    /**
     * Genera un numero aleatorio entre dos numeros dados
     *
     * @param a limite inferior
     * @param b limite superior
     * @return
     */
    public static int aleatorioEntre(int a, int b) {
        return (int) (a + Math.random() * (b - a));
    }
}