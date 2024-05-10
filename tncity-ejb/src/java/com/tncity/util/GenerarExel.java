/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.tncity.properties.Propiedad;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author guiovanny
 */
public class GenerarExel {

    public static void main(String args[]) {
        //Crear libro de trabajo en blanco
        Workbook workbook = new XSSFWorkbook();
        //Crea hoja nueva
         Sheet pagina = workbook.createSheet("Informe de Recargas");

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        Font font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);//Seteando fuente negrita al encabezado del archivo excel
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);

        style.setFont(font);
        
        //Por cada línea se crea un arreglo de objetos (Object[])
        Map<String, Object[]> datos = new TreeMap<String, Object[]>();
        datos.put("1", new Object[]{"Identificador", "Nombre", "Apellidos"});
        datos.put("2", new Object[]{1, "María", "Remen"});
        datos.put("3", new Object[]{2, "David", "Allos"});
        datos.put("4", new Object[]{3, "Carlos", "Caritas"});
        datos.put("5", new Object[]{4, "Luisa", "Vitz"});
        //Iterar sobre datos para escribir en la hoja
        Set keyset = datos.keySet();
        int numeroRenglon = 0;
        for (Object key : keyset) {
            Row row =  pagina.createRow(numeroRenglon++);
            Object[] arregloObjetos = datos.get(key);
            int numeroCelda = 0;
            for (Object obj : arregloObjetos) {
                
                Cell cell = row.createCell(numeroCelda++);
                 
                if (obj instanceof String) {
                  
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                     cell.setCellStyle(style);
                }
            }
        }
        try {
            //Se genera el documento
            FileOutputStream out = new FileOutputStream("c:/Descargas/" + "ejemplo.xlsx");
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
