/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edwar.red@gmail.com
 */
public class TestFreeJasper {

    static File fileReport = new File("/opt/tncity/module/_informes/informex/common/libres/freeReport.jasper");

    public static void main(String[] args) {
        ModelDynreport mOri = getModel();
        DynreportUtil du = new DynreportUtil(mOri);
        du.buildJasperReportPdf(new File("/opt/tncity/documentos/test1.pdf"));
       // du.buildJasperReportXlsx(new File("/opt/tncity/documentos/reporte.xlsx"));
    }

    private static ModelDynreport getModel() {
        ModelDynreport m = new ModelDynreport();
        m.setFileJrxmlTemplate(fileReport);

        //Parameters
        m.addParam("periodo", "2018-I");

        //Data
        m.addData("Colombia", "Bogotá", 10, 20, getDataSubreport(0));
        m.addData("Brasil", "Brasilia", 5, 50, getDataSubreport(1));
        m.addData("Argentina", "Buenos Aires", 4, 25, getDataSubreport(2));
        m.addData("Chile", "Santiago", 6, 77, getDataSubreport(3));
        m.addData("Perú", "Lima", 15, 35, getDataSubreport(4));
        m.addData("México", "México D.F.", 7, 23, getDataSubreport(5));

        return m;
    }

    private static List<DataDynreport> getDataSubreport(int i) {
        List<DataDynreport> lstData = new ArrayList<>();

        DataDynreport d = new DataDynreport();
        d.getValues().add("A" + i);
        d.getValues().add("B" + i);
        d.getValues().add("C" + i);
        lstData.add(d);

        d = new DataDynreport();
        d.getValues().add("A" + i);
        d.getValues().add("B" + i);
        d.getValues().add("C" + i);
        lstData.add(d);

        d = new DataDynreport();
        d.getValues().add("A" + i);
        d.getValues().add("B" + i);
        d.getValues().add("C" + i);
        lstData.add(d);

        return lstData;
    }

}
