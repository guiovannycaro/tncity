/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inmoticamaster
 */
public class DataDynreport implements Serializable{

    private List values = new ArrayList<>();

    public List getValues() {
        return values;
    }

    public void setValues(List values) {
        this.values = values;
    }

}
