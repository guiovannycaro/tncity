/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport;

import java.io.Serializable;

/**
 *
 * @author inmoticamaster
 */
public class ColumnDynreport implements Serializable{

    public final static int DATATYPE_NONE = -1;
    public final static int DATATYPE_NUMERIC = 0;
    public final static int DATATYPE_DATE = 1;
    public final static int DATATYPE_TEXT = 2;
    public final static int DATATYPE_HTML = 3;
    public final static int DATATYPE_MONEY = 4;
    public final static int DATATYPE_MODEL = 5;

    final static String TEXT_ALIGNMENT_LEFT = "Left";
    final static String TEXT_ALIGNMENT_RIGHT = "Right";
    final static String TEXT_ALIGNMENT_CENTER = "Center";

    final static String VERTICAL_ALIGNMENT_TOP = "Top";
    final static String VERTICAL_ALIGNMENT_MIDDLE = "Middle";
    final static String VERTICAL_ALIGNMENT_BOTTOM = "Bottom";

    private int id;
    private String headerText;
    private int dataType;
    private int x, y, width;
    private String pattern = "";
    private String textAlignment = TEXT_ALIGNMENT_LEFT;
    private String verticalAlignment = VERTICAL_ALIGNMENT_MIDDLE;
    private boolean totalAutoSumEnabled = false;
    private ModelDynreport subreportModel;

    public ColumnDynreport(String headerText, int dataType, int width) {
        this.dataType = dataType;
        this.headerText = headerText;
        this.width = width;
    }

    public String getPathJasperFile() {
        String p = subreportModel.getFileJasperCompiled().getAbsolutePath();
        return p;
    }

    public void locate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getTextAlignment() {
        return textAlignment;
    }

    public void setTextAlignment(String textAlignment) {
        this.textAlignment = textAlignment;
    }

    public String getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public boolean isTotalAutoSumEnabled() {
        return totalAutoSumEnabled;
    }

    public void setTotalAutoSumEnabled(boolean totalAutoSumEnabled) {
        this.totalAutoSumEnabled = totalAutoSumEnabled;
    }

    public int getDATATYPE_MODEL() {
        return DATATYPE_MODEL;
    }

    public ModelDynreport getSubreportModel() {
        return subreportModel;
    }

    public void setSubreportModel(ModelDynreport subreportModel) {
        this.subreportModel = subreportModel;
    }

}
