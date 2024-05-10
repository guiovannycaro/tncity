/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.cache;

import java.util.HashMap;

/**
 *
 * @author edwar
 */
public class GeneralCache {

    private static HashMap<String, String> mapTmpKeys = new HashMap<>();

    public static void addTmpKey(String tmpKey) {
        mapTmpKeys.put(tmpKey, tmpKey);
    }

    public static void delTmpKey(String tmpKey) {
        mapTmpKeys.remove(tmpKey);
    }

    public static boolean isTmpkeyRegister(String tmpKey) {
        return mapTmpKeys.get(tmpKey) != null;
    }

}
