/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport.chart;

import com.tncity.util.dynreport.ColumnDynreport;
import java.io.Serializable;

/**
 *
 * @author masterdev
 */
public class SerieChartElement implements Serializable {

    String serieExpression;
    ColumnDynreport categoryColumn;
    ColumnDynreport valueColumn;

    public SerieChartElement(String serieExpression, ColumnDynreport categoryColumn, ColumnDynreport valueColumn) {
        this.serieExpression = serieExpression;
        this.categoryColumn = categoryColumn;
        this.valueColumn = valueColumn;
    }

    public String getSerieExpression() {
        return serieExpression;
    }

    public void setSerieExpression(String serieExpression) {
        this.serieExpression = serieExpression;
    }

    public ColumnDynreport getCategoryColumn() {
        return categoryColumn;
    }

    public void setCategoryColumn(ColumnDynreport categoryColumn) {
        this.categoryColumn = categoryColumn;
    }

    public ColumnDynreport getValueColumn() {
        return valueColumn;
    }

    public void setValueColumn(ColumnDynreport valueColumn) {
        this.valueColumn = valueColumn;
    }

}
