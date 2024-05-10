/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.webservice.pasarelaspago;

import com.tncity.control.pasarelas.Modelos.RecaudoDao;
import com.tncity.facade.entity.RecaudoFacade;
import com.tncity.jpa.pojoaux.TelefoniaJson;
import com.tncity.util.BeanUtil;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author guiovanny
 */
@WebService(serviceName = "WSRecargasPayZend")
public class WSRecargasPayZend {

      RecaudoDao dao = new RecaudoDao();
    RecaudoFacade recaudoFacade = BeanUtil.lookupFacadeBean(RecaudoFacade.class);

    /**
     * Web CONSULTAR PERSON PRIVADA LIBERTAD PAGOS PAYZEND
     */
    @WebMethod(operationName = "consultarPPL")
    public TelefoniaJson consultarPPL(
            @WebParam(name = "docben") String docben) {
        //TODO write your implementation code here:

        TelefoniaJson tel = recaudoFacade.consPplDocPagos(docben);
        return tel;
    }

    /**
     * Web R3CARGAR PERSON PRIVADA LIBERTAD PAGOS PAYZEND
     */
    @WebMethod(operationName = "RecargaTNPagosPayzend")
    public String RecargaTNPagosPayzend(
            @WebParam(name = "Reference1") String Reference1,//ID RECAUDO 
            @WebParam(name = "Reference2") String Reference2,//CEDULA PPL
            @WebParam(name = "Reference3") String Reference3, //cedula del familiar/subdistribuidor
            @WebParam(name = "Amount") String Amount, // MONTO del recaudo de 20000 mil en adelante
            @WebParam(name = "Description") String Description,// DESCRIPCION MEDIO PAGO 
            @WebParam(name = "MerchantOption2") String MerchantOption2,//TELEFONO RECARGA a donde llega el mensaje de texto
            @WebParam(name = "City") String City,// CIUDAD RECARGA
            @WebParam(name = "IdType") String IdType,//TIPO DE DOCUMENTO

             @WebParam(name = "codigotransaccion") String codigotransaccion,//CODIGO TRANSACCION
            @WebParam(name = "formapago") String formaPago,//FORMA DE PAGO 
            @WebParam(name = "franquicia") String franquicia,// PSE,DEBITO,CREDITO
            @WebParam(name = "descripcion") String descripcion,//pagos web tn pagos
            @WebParam(name = "Referencia1") String Referencia1,//MASTERCARD , VISAMASTERCARD,VISA GOLD
            @WebParam(name = "fechaPago") String fechaPago,//fecha del pago
            @WebParam(name = "reciboPago") String reciboPago,//numero del recibo
            @WebParam(name = "codigo") String codigo,//coidigo mensaje error
            @WebParam(name = "mensaje") String mensaje//texto del error
    ) {

        String error_messaage = "Alerta : " + recaudoFacade.RecargarWebTNPagos(
                Reference1,
                Reference2,
                Reference3,
                Amount,
                Description,
                MerchantOption2,
                City,
                IdType,
                codigotransaccion,
                formaPago,
                franquicia,
                descripcion,
                Referencia1,
                fechaPago,
                reciboPago,
                codigo,
                mensaje);
        return error_messaage;

    }
}
