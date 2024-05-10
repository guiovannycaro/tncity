/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.pasarelas.Modelos;

import com.tncity.config.pojoaux.ParametrosRecaudo;
import java.util.List;

/**
 *
 * @author guiovanny
 */
public interface Crud {

    public List listar();

    public RecaudoTrans listarID(int id);

    public String add(String idbenefactor,
            String idbeneficiario,
            String idrecaudo,
            String pin,
            String td,
            String telefonosms,
            String valor,
            String log,
            String codigotransaccion,
            String formaPago,
            String franquicia,
            String descripcion,
            String Referencia1,
            String fechaPago,
            String reciboPago,
            String codigo,
            String mensaje
    );

    public String edit(int id, String nombre, String ape);

    public RecaudoTrans drop(int id);
}