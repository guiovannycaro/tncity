/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport;


import com.tncity.util.dynreport.chart.AreaChart;
import com.tncity.util.dynreport.chart.BarChart;
import com.tncity.util.dynreport.chart.LineChart;
import com.tncity.util.dynreport.chart.Pie3DChart;
import com.tncity.util.dynreport.chart.SerieChartElement;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author edwar.red@gmail.com
 */
public class TestList {

    static File templateReport = new File("/opt/tncity/module/_informes/informex/common/listas/listas.vsl");
    static File templateSubReport = new File("/opt/tncity/module/_informes/informex/common/listas/listas_subreport.vsl");

    public static void main(String[] args) {
        ModelDynreport mOri = getModel();
        DynreportUtil du = new DynreportUtil(mOri);
        du.buildJasperReportPdf(new File("/opt/test1.pdf"));
        
        
        mOri.setIsIgnorePagination(true);
        du.buildJasperReportPdf(new File("/opt/test2.pdf"));

        //du.buildJasperReportXlsx(new File("/opt/test1.xlsx"));
        //File xlsx = new File("/opt/test.xlsx");
        //du.buildJasperReportXlsx(xlsx);
        //String html=du.buildHtmlContent();
        //System.out.println(html);
    }

    private static ModelDynreport getModel() {
        ModelDynreport m = new ModelDynreport();
        m.setId(1);
        m.setTitle("Reporte de prueba");

        m.centerTable();

        m.setIsIgnorePagination(false);
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
        m.setObservaciones("1. Observación 1\n2. Observación 2\n3. Observación 3");
        m.setObservacionesWidth(400);
        m.locateObservaciones(10, 30);

        ColumnDynreport c = new ColumnDynreport("Nombre", ColumnDynreport.DATATYPE_TEXT, 100);
        m.addColumn(c);

        c = new ColumnDynreport("Apellidos", ColumnDynreport.DATATYPE_TEXT, 100);
        m.addColumn(c);

        c = new ColumnDynreport("Tel.", ColumnDynreport.DATATYPE_TEXT, 70);
        m.addColumn(c);

        c = new ColumnDynreport("Factor 1", ColumnDynreport.DATATYPE_NUMERIC, 50);
        c.setTotalAutoSumEnabled(true);
        m.addColumn(c);

        c = new ColumnDynreport("Factor 2", ColumnDynreport.DATATYPE_NUMERIC, 50);
        c.setTotalAutoSumEnabled(true);
        m.addColumn(c);

        c = new ColumnDynreport("Cartera", ColumnDynreport.DATATYPE_MONEY, 70);
        c.setTotalAutoSumEnabled(true);
        m.addColumn(c);

        c = new ColumnDynreport("F. Nacimiento", ColumnDynreport.DATATYPE_DATE, 100);
        c.setPattern("yyyy-MMM-dd");
        m.addColumn(c);

        //Add Subreport in Datail Band
        c = new ColumnDynreport("SubReport", ColumnDynreport.DATATYPE_MODEL, 90);
        c.setSubreportModel(getSubReportModel(c));
        c.getSubreportModel().setFileJrxmlTemplate(templateSubReport);
        c.getSubreportModel().setShowColumnsHeaders(true);
        m.addColumn(c);

        //Add Data in Detaul Band
        m.addData("Edwar f sdf sd sd sd fsd df sdf sd fsd fsd ds sdf sdf sd END", "Rojas", "3115961112", 10, 15, 250000.0, new Date(), dataListSubreport(1));
        m.addData("Napoleón", "Avila", null, 20, 15, 50000.0, new Date(), dataListSubreport(2));
        m.addData("Luis", "Romero", "", 15, 10, 1000.0, new Date(), dataListSubreport(3));
        m.addData("Rocio", "Ramírez", "3115202020", 20, 30, 25000.0, new Date(), dataListSubreport(4));

        //Add Charts
        m.addChart(getPie3dChart(m));
        m.addChart(getBarChart(m));
        m.addChart(getLineChart(m));
        m.addChart(getAreaChart(m));
        return m;
    }

    private static ModelDynreport getSubReportModel(ColumnDynreport colParent) {
        ModelDynreport m = new ModelDynreport();
        m.setTitle("SubreportTes");
        m.setIsIgnorePagination(true);
        m.locateTable(0, 0);

        //Columns
        ColumnDynreport c = new ColumnDynreport("C1", ColumnDynreport.DATATYPE_TEXT, 30);
        m.addColumn(c);

        c = new ColumnDynreport("C2", ColumnDynreport.DATATYPE_TEXT, 30);
        m.addColumn(c);

        c = new ColumnDynreport("C3", ColumnDynreport.DATATYPE_TEXT, 30);
        m.addColumn(c);

        return m;
    }

    private static List<DataDynreport> dataListSubreport(int i) {
        List<DataDynreport> data = new ArrayList();

        if (i == 1) {
            return data;
        }

        DataDynreport d = new DataDynreport();
        d.getValues().add("A" + i);
        d.getValues().add("B" + i);
        d.getValues().add("C" + i);
        data.add(d);

        d = new DataDynreport();
        d.getValues().add("A" + i);
        d.getValues().add("B" + i);
        d.getValues().add("C" + i);
        data.add(d);

        d = new DataDynreport();
        d.getValues().add("A" + i);
        d.getValues().add("B" + i);
        d.getValues().add("C" + i);
        data.add(d);

        return data;
    }

    private static Pie3DChart getPie3dChart(ModelDynreport model) {
        ColumnDynreport cKey = model.getColumns().get(1);
        ColumnDynreport cLabels = model.getColumns().get(3);
        ColumnDynreport cValues = model.getColumns().get(3);

        Pie3DChart ch = new Pie3DChart(cKey, cLabels, cValues);

        ch.setTitle("PIE 3D!");
        ch.setWidth(200);
        ch.setHeight(150);
        ch.setShowLegend(true);
        ch.setShowLabel(true);

        return ch;
    }

    private static BarChart getBarChart(ModelDynreport model) {
        BarChart ch = new BarChart();

        ch.setTitle("BAR CHART !");
        ch.setWidth(200);
        ch.setHeight(150);
        ch.setShowLegend(true);
        ch.setOrientation(BarChart.ORIENTATION_HORIZONTAL);

        ColumnDynreport cCategory = model.getColumns().get(1);
        ColumnDynreport cValues = model.getColumns().get(3);
        SerieChartElement serie = new SerieChartElement("Factor 1", cCategory, cValues);
        ch.getLstSeries().add(serie);

        cCategory = model.getColumns().get(1);
        cValues = model.getColumns().get(4);
        serie = new SerieChartElement("Factor 2", cCategory, cValues);
        ch.getLstSeries().add(serie);

        return ch;
    }

    private static LineChart getLineChart(ModelDynreport model) {
        LineChart ch = new LineChart();

        ch.setTitle("LINE CHART !");
        ch.setWidth(200);
        ch.setHeight(150);
        ch.setShowLegend(true);
        ch.setOrientation(BarChart.ORIENTATION_VERTICAL);

        ColumnDynreport cCategory = model.getColumns().get(1);
        ColumnDynreport cValues = model.getColumns().get(3);
        SerieChartElement serie = new SerieChartElement("Factor 1", cCategory, cValues);
        ch.getLstSeries().add(serie);

        cCategory = model.getColumns().get(1);
        cValues = model.getColumns().get(4);
        serie = new SerieChartElement("Factor 2", cCategory, cValues);
        ch.getLstSeries().add(serie);

        return ch;
    }

    private static AreaChart getAreaChart(ModelDynreport model) {
        AreaChart ch = new AreaChart();

        ch.setTitle("AREA CHART !");
        ch.setWidth(200);
        ch.setHeight(150);
        ch.setShowLegend(true);
        ch.setOrientation(BarChart.ORIENTATION_VERTICAL);

        ColumnDynreport cCategory = model.getColumns().get(1);
        ColumnDynreport cValues = model.getColumns().get(3);
        SerieChartElement serie = new SerieChartElement("Factor 1", cCategory, cValues);
        ch.getLstSeries().add(serie);

        cCategory = model.getColumns().get(1);
        cValues = model.getColumns().get(4);
        serie = new SerieChartElement("Factor 2", cCategory, cValues);
        ch.getLstSeries().add(serie);

        return ch;
    }
}
