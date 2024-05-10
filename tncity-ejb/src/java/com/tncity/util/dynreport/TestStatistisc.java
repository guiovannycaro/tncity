/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport;


import com.tncity.util.dynreport.chart.BarChart;
import com.tncity.util.dynreport.chart.LineChart;
import com.tncity.util.dynreport.chart.Pie3DChart;
import com.tncity.util.dynreport.chart.SerieChartElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edwar.red@gmail.com
 */
public class TestStatistisc {

    static File templateReport = new File("/opt/tncity/module/_informes/informex/common/estadisticas/estadisticas.vsl");
    static File templateSubReport = new File("/opt/tncity/module/_informes/informex/common/estadisticas/estadisticas_subreport.vsl");

    public static void main(String[] args) {
        ModelDynreport mOri = getModel();
        DynreportUtil du = new DynreportUtil(mOri);
        du.buildJasperReportPdf(new File("/opt/test1.pdf"));
        //du.buildJasperReportXlsx(new File("/opt/test1.xlsx"));
    }

    private static ModelDynreport getModel() {
        ModelDynreport m = new ModelDynreport();
        m.setId(1);
        m.setTitle("Reporte de prueba - ESTADISTICAS");
        m.locateTable(10, 0);
        m.setShowColumnsHeaders(true);
        m.addParam("path_logo", "/opt/sobre5/module/_informes/img/nophoto2.jpg");
        m.addParam("impl_name", "SINCAP");
        m.addParam("params_text0", "Periodo: ");
        m.addParam("params_text1", "2018-II");
        m.addParam("params_text2", "2");
        m.addParam("params_text3", "3");
        m.addParam("params_text4", "4");
        m.addParam("params_text5", "5");
        m.addParam("params_text6", "6");
        m.addParam("params_text7", "7");
        m.setFileJrxmlTemplate(templateReport);
        m.setObservaciones("1. Observación General 1\n2. Observación General 2\n3. Observación General 3");
        m.setObservacionesWidth(400);
        m.locateObservaciones(10, 30);
        m.setFixedColumns(false);//IMPORTANTE PARA ESTADISTICAS

        //Add SubReport 1
        ColumnDynreport c = new ColumnDynreport("", ColumnDynreport.DATATYPE_MODEL, 200);
        c.setSubreportModel(getSubReportModel_1());
        c.getSubreportModel().setFileJrxmlTemplate(templateSubReport);
        c.getSubreportModel().setShowColumnsHeaders(true);
        m.addColumn(c);

        //Add SubReport 2
        c = new ColumnDynreport("", ColumnDynreport.DATATYPE_MODEL, 200);
        c.setSubreportModel(getSubReportModel_2());
        c.getSubreportModel().setFileJrxmlTemplate(templateSubReport);
        c.getSubreportModel().setShowColumnsHeaders(true);
        c.locate(0, 250);
        m.addColumn(c);

        //Add Data
        m.addData(dataListSubreport_1(), dataListSubreport_2());

        return m;
    }

    private static ModelDynreport getSubReportModel_1() {
        ModelDynreport m = new ModelDynreport();
        m.setTitle("PAISES");
        m.locateTable(0, 0);
        m.setObservacionesWidth(400);
        m.locateObservaciones(10, 25);
        m.setObservaciones("1. Observación 1\n2. Observación 2\n3. Observación 3");
        m.setFixedCharts(false);//IMPORTANTE PARA ESTADISTICAS

        //Columns
        ColumnDynreport c = new ColumnDynreport("PAIS", ColumnDynreport.DATATYPE_TEXT, 100);
        m.addColumn(c);

        c = new ColumnDynreport("V1", ColumnDynreport.DATATYPE_NUMERIC, 50);
        m.addColumn(c);
        c.setTotalAutoSumEnabled(true);

        c = new ColumnDynreport("V2", ColumnDynreport.DATATYPE_NUMERIC, 50);
        m.addColumn(c);
        c.setTotalAutoSumEnabled(true);

        //Graphics
        m.addChart(getPie3dChart_1(m));

        return m;
    }

    private static List<DataDynreport> dataListSubreport_1() {
        List<DataDynreport> data = new ArrayList<>();

        DataDynreport d = new DataDynreport();
        d.getValues().add("COLOMBIA");
        d.getValues().add(30);
        d.getValues().add(20);
        data.add(d);

        d = new DataDynreport();
        d.getValues().add("USA");
        d.getValues().add(10);
        d.getValues().add(4);
        data.add(d);

        d = new DataDynreport();
        d.getValues().add("PERÚ");
        d.getValues().add(50);
        d.getValues().add(70);
        data.add(d);

        d = new DataDynreport();
        d.getValues().add("MÉXICO");
        d.getValues().add(25);
        d.getValues().add(45);
        data.add(d);

        return data;
    }

    private static Pie3DChart getPie3dChart_1(ModelDynreport model) {
        ColumnDynreport cKey = model.getColumns().get(0);
        ColumnDynreport cLabels = model.getColumns().get(0);
        ColumnDynreport cValues = model.getColumns().get(1);

        Pie3DChart ch = new Pie3DChart(cKey, cLabels, cValues);

        ch.setTitle("PIE 3D!");
        ch.setWidth(250);
        ch.setHeight(200);
        ch.setShowLegend(true);
        ch.setShowLabel(true);

        ch.locate(250, -120);

        return ch;
    }

    private static ModelDynreport getSubReportModel_2() {
        ModelDynreport m = new ModelDynreport();
        m.setTitle("CIUDADES");
        m.centerTitle();

        m.centerTable();

        m.setObservacionesWidth(400);
        m.locateObservaciones(200, 25);
        m.setObservaciones("1. Observación 1\n2. Observación 2\n3. Observación 3");
        m.setFixedCharts(false);//IMPORTANTE PARA ESTADISTICAS

        //Columns
        ColumnDynreport c = new ColumnDynreport("CIUDAD", ColumnDynreport.DATATYPE_TEXT, 100);
        m.addColumn(c);

        c = new ColumnDynreport("V1", ColumnDynreport.DATATYPE_NUMERIC, 50);
        m.addColumn(c);
        c.setTotalAutoSumEnabled(true);

        c = new ColumnDynreport("V2", ColumnDynreport.DATATYPE_NUMERIC, 50);
        m.addColumn(c);
        c.setTotalAutoSumEnabled(true);

        //Graphics
        m.addChart(getLineChart_2(m));
        return m;
    }

    private static List<DataDynreport> dataListSubreport_2() {
        List<DataDynreport> data = new ArrayList<>();

        DataDynreport d = new DataDynreport();
        d.getValues().add("BOGOTÁ");
        d.getValues().add(30);
        d.getValues().add(20);
        data.add(d);

        d = new DataDynreport();
        d.getValues().add("BUENOS AIRES");
        d.getValues().add(10);
        d.getValues().add(4);
        data.add(d);

        d = new DataDynreport();
        d.getValues().add("LIMA");
        d.getValues().add(50);
        d.getValues().add(70);
        data.add(d);

        d = new DataDynreport();
        d.getValues().add("NEW YORK");
        d.getValues().add(25);
        d.getValues().add(45);
        data.add(d);

        return data;
    }

    private static LineChart getLineChart_2(ModelDynreport model) {
        LineChart ch = new LineChart();

        ch.setTitle("LINE CHART !");
        ch.setWidth(400);
        ch.setHeight(300);
        ch.setShowLegend(true);
        ch.setOrientation(BarChart.ORIENTATION_VERTICAL);

        ch.locate(0, 70);
        ch.center();

        ColumnDynreport cCategory = model.getColumns().get(0);
        ColumnDynreport cValues = model.getColumns().get(1);
        SerieChartElement serie = new SerieChartElement("Factor 1", cCategory, cValues);
        ch.getLstSeries().add(serie);

        cCategory = model.getColumns().get(0);
        cValues = model.getColumns().get(2);
        serie = new SerieChartElement("Factor 2", cCategory, cValues);
        ch.getLstSeries().add(serie);

        return ch;
    }

}
