/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.telefonia.client;

import com.tncity.jpa.pojoaux.BeneficiarioWS;
import com.tncity.jpa.pojoaux.TelefoniaManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guiovanny
 */
public class Datos {

    private int maxUsuarios = 5;

    private TelefoniaManager misBeneficiarios[] = new TelefoniaManager[maxUsuarios];

    private int contadorbeneficiarios = 0;

    TelefoniaManager beneficiario1;
    TelefoniaManager beneficiario2;
    TelefoniaManager beneficiario3;
    TelefoniaManager beneficiario4;
    TelefoniaManager beneficiario5;

    public Datos() {

        TelefoniaManager beneficiario;

        beneficiario = new TelefoniaManager("1","1","Cedula", "79848026", "Guiovanny", "Caro", "Bogota");
        misBeneficiarios[contadorbeneficiarios] = beneficiario;
        contadorbeneficiarios++;

        beneficiario = new TelefoniaManager("2","1","Cedula", "41410317", "Pedro", "Infante", "Medellin");
        misBeneficiarios[contadorbeneficiarios] = beneficiario;
        contadorbeneficiarios++;

        beneficiario = new TelefoniaManager("3","1","Cedula", "52431796", "Laura", "Caro", "Cartagena");
        misBeneficiarios[contadorbeneficiarios] = beneficiario;
        contadorbeneficiarios++;

    }

    public int numerobeneficiarios() {
        return contadorbeneficiarios;

    }

    public TelefoniaManager[] getMisBeneficiarios() {
        return misBeneficiarios;
    }

    public void setMisBeneficiarios(TelefoniaManager[] misBeneficiarios) {
        this.misBeneficiarios = misBeneficiarios;
    }

    public TelefoniaManager posicionBeneficiarios(String numdoc,String tdoc) {
        TelefoniaManager Beneficiarios = null;
        for (int i = 0; i < contadorbeneficiarios; i++) {

            if (misBeneficiarios[i].getNumerodocumento().equals(numdoc) &&  misBeneficiarios[i].getTipodocumento().equals(tdoc)) {
                Beneficiarios = misBeneficiarios[i];
            }
        }

        return Beneficiarios;
    }

    public TelefoniaManager posicionBeneficiarioDoc(String numdoc,String tdoc) {
        TelefoniaManager Beneficiarios = null;
        beneficiario1 = new TelefoniaManager("1","1","Cedula", "79848026", "Guiovanny", "Caro", "Bogota");
        beneficiario2 = new TelefoniaManager("2","1","Cedula", "41410317", "MIgel", "Duarte", "Medellin");
        beneficiario3 = new TelefoniaManager("3","1","Cedula", "52431792", "Esperanza", "Acosta", "Bogota");
        beneficiario4 = new TelefoniaManager("4","1","Cedula", "45697812", "Silvia", "Cano", "Cartagena");
        beneficiario5 = new TelefoniaManager("5","1","Cedula", "187985633", "Catalina", "Ortiz", "Pereira");

        List<TelefoniaManager> lstR = new ArrayList<>();

        lstR.add(beneficiario1);
        lstR.add(beneficiario2);
        lstR.add(beneficiario3);
        lstR.add(beneficiario4);
        lstR.add(beneficiario5);
        for (int i = 0; i < lstR.size(); i++) {
            
                TelefoniaManager aux = (TelefoniaManager) lstR.get(i);
                
                
            if(aux.getNumerodocumento() == numdoc && aux.getTipodocumento() == tdoc) return Beneficiarios = misBeneficiarios[i];
        }
        return Beneficiarios;

    }

}
