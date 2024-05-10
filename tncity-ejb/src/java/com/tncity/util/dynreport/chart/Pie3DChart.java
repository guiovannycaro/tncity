/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport.chart;

import com.tncity.util.dynreport.ColumnDynreport;
import java.io.Serializable;

public class Pie3DChart extends AbstractChart implements Serializable{

    private ColumnDynreport keyColumn;
    private ColumnDynreport labelColumn;
    private ColumnDynreport valueColumn;
    boolean showLabel = true;

    public Pie3DChart(ColumnDynreport keyColumn, ColumnDynreport labelColumn, ColumnDynreport valueColumn) {
        this.keyColumn = keyColumn;
        this.labelColumn = labelColumn;
        this.valueColumn = valueColumn;
    }

    @Override
    public String getType() {
        return TYPE_PIE3D;
    }

    public ColumnDynreport getLabelColumn() {
        return labelColumn;
    }

    public void setLabelColumn(ColumnDynreport labelColumn) {
        this.labelColumn = labelColumn;
    }

    public ColumnDynreport getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(ColumnDynreport keyColumn) {
        this.keyColumn = keyColumn;
    }

    public ColumnDynreport getValueColumn() {
        return valueColumn;
    }

    public void setValueColumn(ColumnDynreport valueColumn) {
        this.valueColumn = valueColumn;
    }

    public boolean isShowLabel() {
        return showLabel;
    }

    public void setShowLabel(boolean showLabel) {
        this.showLabel = showLabel;
    }

}
