/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.bitacora.bitacora.subnivel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guiovanny
 */
public class Control {
    private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
    
    public void controlar(){
        LOGGER.log(Level.INFO, "Proceso exitoso");
    }
}
