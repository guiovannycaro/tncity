/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author masterdev
 */
public class LineChart extends AbstractChart implements Serializable{

    public static final String ORIENTATION_VERTICAL = "Vertical";
    public static final String ORIENTATION_HORIZONTAL = "Horizontal";

    List<SerieChartElement> lstSeries = new ArrayList<>();
    String orientation = ORIENTATION_HORIZONTAL;

    @Override
    public String getType() {
        return TYPE_LINE;
    }

    public List<SerieChartElement> getLstSeries() {
        return lstSeries;
    }

    public void setLstSeries(List<SerieChartElement> lstSeries) {
        this.lstSeries = lstSeries;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

}
