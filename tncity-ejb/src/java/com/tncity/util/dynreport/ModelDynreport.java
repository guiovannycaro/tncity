/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport;


import com.tncity.util.dynreport.chart.AbstractChart;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author inmoticamaster
 */
public class ModelDynreport implements Serializable {

    public static final String ORIENTATION_LANDSCAPE = "Landscape";
    public static final String ORIENTATION_PORTRAIT = "Portrait";

    private int id;
    private String title;
    private List<ColumnDynreport> columns = new ArrayList<>();
    private List<AbstractChart> charts = new ArrayList<>();
    private List<DataDynreport> data = new ArrayList<>();
    private HashMap<String, Object> params = new HashMap<>();
    private int xTable;
    private int yTable;
    private int xTitle = 0;
    private int yTitle = 0;
    private String orientation = ORIENTATION_PORTRAIT;
    private int pageWidth = 595;
    private int pageHeight = 842;
    private boolean isIgnorePagination = false;
    private boolean showAutoSum = false;
    private boolean pageWidthAutoSize = true;
    private boolean showColumnsHeaders = true;
    private long totalReg = 0;
    private File fileJrxmlTemplate;
    private File fileJasperCompiled;
    private int observacionesX = 0;
    private int observacionesY = 25;
    private int observacionesWidth = 0;
    private String observaciones = null;
    private int sumaryHeight = 100;
    private int detailHeight = 20;
    private int chatsSpace = 20;
    private boolean fixedColumns = true;
    private boolean fixedCharts = true;
    private boolean centerTable = false;
    private boolean centerTitle = false;
    private boolean centerObs = false;

    public void setData(List<DataDynreport> data) {
        this.data = data;
    }

    public void addColumn(String headerText, int dataType, int width) {
        ColumnDynreport c = new ColumnDynreport(headerText, dataType, width);
        c.setId(columns.size());
        columns.add(c);
    }

    public void addColumn(ColumnDynreport col) {
        col.setId(columns.size());
        columns.add(col);
    }

    public void addData(Object... reg) {
        DataDynreport di = new DataDynreport();
        for (Object d : reg) {
            if (d instanceof List) {
                List<DataDynreport> l = (List<DataDynreport>) d;

                JRDataSource ds;
                if (l.isEmpty()) {
                    //l.add(emptyReg(3));
                    ds = new JREmptyDataSource();
                } else {
                    ds = new JRBeanCollectionDataSource(l);
                }

                di.getValues().add(ds);
            } else {
                di.getValues().add(d);
            }
        }
        data.add(di);
    }

    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    public void addChart(AbstractChart chart) {
        charts.add(chart);
    }

    public Object getParam(String key) {
        return params.get(key);
    }

    public void locateTable(int x, int y) {
        xTable = x;
        yTable = y;
    }

    public void centerTable() {
        centerTable = true;
    }

    public void centerTitle() {
        centerTitle = true;
    }

    public void centerObservaciones() {
        centerObs = true;
    }

    public void setPageSize(int w, int h) {
        pageWidth = w;
        pageHeight = h;
    }

    private int xi = 0;
    private int yi = 0;

    public void fixRender() {
        centerCharts();
        centerTableFix();
        centerTitleFix();
        centerObservacionesFix();

        if (fixedColumns) {
            fixRenderColumns();
        }
        if (fixedCharts) {
            fixRenderCharts();
        }

        fixRenderSumary();
        fixRenderDetail();
        fixRenderPage();
    }

    private void centerObservacionesFix() {
        if (!centerObs) {
            return;
        }
        double x = pageWidth / 2.0;
        x = x - observacionesWidth / 2.0;
        observacionesX = (int) x;
    }

    private void centerTitleFix() {
        if (!centerTitle) {
            return;
        }
        double x = pageWidth / 2.0;
        x = x - 10.0 * title.length() / 2.0;
        xTitle = (int) x;
    }

    private void centerCharts() {
        for (AbstractChart ch : charts) {
            if (ch.isCenter()) {
                double x = pageWidth / 2.0;
                x = x - ch.getWidth() / 2.0;
                ch.locate((int) x, ch.getY());
            }
        }
    }

    private void centerTableFix() {
        if (!centerTable) {
            return;
        }
        double x = pageWidth / 2.0;
        double acum = 0;
        for (ColumnDynreport column : columns) {
            acum += column.getWidth();
        }
        x = x - acum / 2.0;
        xTable = (int) x;
    }

    private void fixRenderColumns() {
        xi = xTable;
        yi = yTable;

        int wt = 20 * 2;//margin

        for (ColumnDynreport c : columns) {
            c.locate(xi, yi);

            xi += c.getWidth();

            fixTextAlign(c);

            if (c.isTotalAutoSumEnabled()) {
                showAutoSum = true;
            }
            wt += c.getWidth();
        }

        if (pageWidthAutoSize) {
            if (wt > pageWidth) {
                pageWidth = wt + 30;
            }
        }

        if (observacionesWidth <= 0) {
            observacionesWidth = pageWidth - 50;
        }
    }

    private void fixTextAlign(ColumnDynreport c) {
        if (c.getDataType() == ColumnDynreport.DATATYPE_NUMERIC
                || c.getDataType() == ColumnDynreport.DATATYPE_MONEY) {
            c.setTextAlignment(ColumnDynreport.TEXT_ALIGNMENT_RIGHT);
        }
        if (c.getDataType() == ColumnDynreport.DATATYPE_MONEY) {
            c.setPattern("$#,##0.00");
        }
        if (c.getDataType() == ColumnDynreport.DATATYPE_DATE) {
            c.setTextAlignment(ColumnDynreport.TEXT_ALIGNMENT_CENTER);
        }
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
        addParam("observaciones", observaciones);
    }

    public String getObservacionesWithoutLn() {
        return observaciones.replaceAll("\n", "\\\\n");
    }

    private void fixRenderCharts() {
        int yj = observacionesY + 50;
        for (AbstractChart ch : charts) {

            //Center
            int xj = (int) (pageWidth * 1.0 / 2.0 - ch.getWidth() * 1.0 / 2.0);

            ch.locate(xj, yj);

            yj += ch.getHeight() + chatsSpace;
        }
    }

    private void fixRenderSumary() {
        int yMax = 0;

        for (AbstractChart ch : charts) {
            if (yMax < ch.getY() + ch.getHeight()) {
                yMax = ch.getY() + ch.getHeight();
            }
        }

        sumaryHeight = yMax + observacionesY + 25;
    }

    private void fixRenderDetail() {
        int yMax = 0;
        for (ColumnDynreport column : columns) {
            if (column.getY() > yMax) {
                yMax = column.getY() + 20;
            }
        }
        detailHeight = yMax + 20;
        //System.out.println("D(" + this.title + "):" + detailHeight);
    }

    private void fixRenderPage() {
        if (sumaryHeight + detailHeight > pageHeight) {
            pageHeight = sumaryHeight + detailHeight + observacionesY + 100;
        }
    }

    public void locateObservaciones(int x, int y) {
        observacionesX = x;
        observacionesY = y;
    }

    public void locateTitle(int x, int y) {
        xTitle = x;
        yTitle = y;
    }

    public boolean getObservacionesShow() {
        return observaciones != null;
    }

    public String getPathJasperFile() {
        return fileJasperCompiled.getAbsolutePath();
    }

    public List<DataDynreport> getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public int getxTable() {
        return xTable;
    }

    public int getyTable() {
        return yTable;
    }

    public List<ColumnDynreport> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnDynreport> columns) {
        this.columns = columns;
    }

    public int getSumaryHeight() {
        return sumaryHeight;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public int getPageWidth() {
        return pageWidth;
    }

    public void setPageWidth(int pageWidth) {
        this.pageWidth = pageWidth;
    }

    public int getPageHeight() {
        return pageHeight;
    }

    public void setPageHeight(int pageHeight) {
        this.pageHeight = pageHeight;
    }

    public boolean isIsIgnorePagination() {
        return isIgnorePagination;
    }

    public boolean getIsIgnorePagination() {
        return isIgnorePagination;
    }

    public void setIsIgnorePagination(boolean isIgnorePagination) {
        this.isIgnorePagination = isIgnorePagination;
    }

    public boolean isShowAutoSum() {
        return showAutoSum;
    }

    public boolean getShowAutoSum() {
        return showAutoSum;
    }

    public boolean isPageAutoSize() {
        return pageWidthAutoSize;
    }

    public boolean getPageAutoSize() {
        return pageWidthAutoSize;
    }

    public void setPageWidthAutoSize(boolean pageAutoSize) {
        this.pageWidthAutoSize = pageAutoSize;
    }

    public boolean isShowColumnsHeaders() {
        return showColumnsHeaders;
    }

    public boolean getShowColumnsHeaders() {
        return showColumnsHeaders;
    }

    public void setShowColumnsHeaders(boolean showColumnsHeaders) {
        this.showColumnsHeaders = showColumnsHeaders;
    }

    public long getTotalReg() {
        return totalReg;
    }

    public void setTotalReg(long totalReg) {
        this.totalReg = totalReg;
    }

    public File getFileJrxmlTemplate() {
        return fileJrxmlTemplate;
    }

    public void setFileJrxmlTemplate(File fileJrxmlTemplate) {
        this.fileJrxmlTemplate = fileJrxmlTemplate;
    }

    public File getFileJasperCompiled() {
        return fileJasperCompiled;
    }

    public void setFileJasperCompiled(File fileJasperCompiled) {
        this.fileJasperCompiled = fileJasperCompiled;
    }

    public int getObservacionesX() {
        return observacionesX;
    }

    public int getObservacionesY() {
        return observacionesY;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public int getObservacionesWidth() {
        return observacionesWidth;
    }

    public void setObservacionesWidth(int observacionesWidth) {
        this.observacionesWidth = observacionesWidth;
    }

    public List<AbstractChart> getCharts() {
        return charts;
    }

    public boolean isFixedColumns() {
        return fixedColumns;
    }

    public void setFixedColumns(boolean fixedColumns) {
        this.fixedColumns = fixedColumns;
    }

    public boolean isFixedCharts() {
        return fixedCharts;
    }

    public void setFixedCharts(boolean fixedCharts) {
        this.fixedCharts = fixedCharts;
    }

    public int getDetailHeight() {
        return detailHeight;
    }

    public void setDetailHeight(int detailHeight) {
        this.detailHeight = detailHeight;
    }

    public int getxTitle() {
        return xTitle;
    }

    public int getyTitle() {
        return yTitle;
    }

}
