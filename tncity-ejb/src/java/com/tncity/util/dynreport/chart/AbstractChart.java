/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport.chart;

import java.io.Serializable;

public abstract class AbstractChart implements Serializable{

    public final static String TYPE_PIE3D = "PIE3D";
    public final static String TYPE_BAR = "BAR";
    public final static String TYPE_LINE = "LINE";
    public final static String TYPE_AREA = "AREA";

    String title = "";
    boolean showLegend = true;
    int width = 200;
    int height = 150;
    int x = 0;
    int y = 0;
    private boolean center = false;

    public abstract String getType();

    public void locate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void center() {
        center = true;
    }
    public boolean isCenter(){
        return center;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowLegend() {
        return showLegend;
    }

    public void setShowLegend(boolean showLegend) {
        this.showLegend = showLegend;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static String getTYPE_PIE3D() {
        return TYPE_PIE3D;
    }

    public static String getTYPE_BAR() {
        return TYPE_BAR;
    }

    public static String getTYPE_LINE() {
        return TYPE_LINE;
    }

    public static String getTYPE_AREA() {
        return TYPE_AREA;
    }

}
