/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

/**
 *
 * @author guiovanny
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Cell; 
 
/** 
 * Utility class, where we will create methods for training read and write excel files,
 * with <a href="https://poi.apache.org/">Apache POI</a>, we use 
 * <a href="https://poi.apache.org/spreadsheet/">POI-HSSF and POI-XSSF - Java API To Access Microsoft</a>
 * HSSF is the POI Project's pure Java implementation of the Excel '97(-2007) file.
 * 
 * Clase de utilidades, donde crearemos métodos
 * para el aprendizaje de la lectura y escritura de ficheros excel con 
 * <a href="https://poi.apache.org/">Apache POI</a>, usaremos
 * <a href="https://poi.apache.org/spreadsheet/">POI-HSSF and POI-XSSF - Java API To Access Microsoft</a>
 * HSSF es el proyecto POI de implementación total en Java para ficheros Excel '97(-2007).
 *
 * @author Xules You can follow me on my website http://www.codigoxules.org/en
 * Puedes seguirme en mi web http://www.codigoxules.org).
 */
public class JavaPoiUtils {
   
    public void readExcelFile(String excelFile) throws FileNotFoundException, IOException{
 File ficheroXLS = new File(excelFile);
FacesContext ctx = FacesContext.getCurrentInstance();
FileInputStream fis = new FileInputStream(ficheroXLS);
byte[] bytes = new byte[1000];
int read = 0;


   String fileName = ficheroXLS.getName();
   String contentType = "application/vnd.ms-excel";
   //String contentType = "application/pdf";
   HttpServletResponse response =(HttpServletResponse) ctx.getExternalContext().getResponse();
   response.setContentType(contentType);
   response.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
   ServletOutputStream out = response.getOutputStream();

   while ((read = fis.read(bytes)) != -1) {
        out.write(bytes, 0, read);
   }

   out.flush();
   out.close();
   System.out.println("\nDescargado\n");
   ctx.responseComplete();

    }

    
    public static void main(String[] args) throws IOException{
        JavaPoiUtils javaPoiUtils = new JavaPoiUtils();
        javaPoiUtils.readExcelFile("/opt/tncity/tncity-war/web/documentos/reporteTnPagosFechas.xls");        
    }    
}
