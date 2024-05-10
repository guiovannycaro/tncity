/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.security;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.GeneralBenefactorConfig;
import com.tncity.facade.entity.PerfilFacade;
import com.tncity.facade.entity.BenefactorFacade;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SecurityBenefactorFacade {

    @EJB
    PerfilFacade perfilFacade;
    @EJB
    ParamFacade paramFacade;

    /**
     * Key: idperfil, Value: Map<idfunc,idfunc>
     */
    private static HashMap<Integer, HashMap<Integer, Integer>> mapFunc;
    /**
     * Key: idusuario, Val Map<idperfil,idperfil>
     */
    private static HashMap<Integer, HashMap<Integer, Integer>> mapUsers;

    public void loadPerfilesFunc() {
        mapFunc = perfilFacade.mapPerfilesFuncionalidades();
    }

    public void loadPerfilesUsuarios() {
        mapUsers = perfilFacade.mapPerfilesUsuarios();
    }

    public boolean isFuncAutorizada(Long idbenefactor, Integer idfunc) {

        //User AdminAdmin
        if (idbenefactor.equals(BenefactorFacade.USUARIO_ADMIN)) {
            return true;
        }

        //User Admin
        GeneralBenefactorConfig gc = paramFacade.getParamFromCache(GeneralBenefactorConfig.class);
        if (gc != null && idbenefactor.equals(gc.getIdusuarioBenefactor())) {
            return true;
        }

        //User NO-ADMIN
        if (mapFunc == null) {
            loadPerfilesFunc();
        }
        if (mapUsers == null) {
            loadPerfilesUsuarios();
        }

        HashMap<Integer, Integer> mapP = mapUsers.get(idbenefactor);
        if (mapP != null) {
            for (Integer idperfil : mapP.values()) {
                HashMap<Integer, Integer> mapF = mapFunc.get(idperfil);
                if (mapF.get(idfunc) != null) {
                    return true;
                }
            }
        }

        return false;

    }

}
