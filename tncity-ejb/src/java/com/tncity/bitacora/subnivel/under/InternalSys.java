/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.bitacora.subnivel.under;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guiovanny
 */
public class InternalSys {
     private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.under.InternalSys");

    public void llamadaSistema(){
        LOGGER.log(Level.WARNING, "Ocurrio un error de acceso en 0xFF");
    }
}
