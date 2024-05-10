/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author guiovanny
 */
public class FicheroExel {

      public static void main(String[] args) {
 
        String fileName = "Productos.xlsx";
        String filePath = "/home/guiovanny/Descargas/" + fileName;
        
        File files = new File(filePath);
        //Seteando el nombre de la hoja donde agregaremos los items
        String hoja = "Hoja1"; 
         
        //Creando objeto libro de Excel
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet hoja1 = book.createSheet(hoja);
         
        //Cabecera de la hoja de excel
        String[] header = new String[]{"Código", "Producto", "Descripción"};
 
        //Contenido de la hoja de excel
        String[][] document = new String[][]{
            {"C001", "Computador Laptop HP.", "Compurtador marca HP color negro."},
            {"C002", "Computador Escritorio Lenovo.", "Compurtador marca lenovo color negro con monitor integrado."},
            {"C003", "Impresora HP.", "Impresora marca HP, multifuncional color negro."},
            {"C004", "Mouse Inalambrico Logitec.", "Mouse Inalambrico color azul con negro."},
            {"C005", "Teclado Inalambrico Lenovo.", "Teclado Inalambrico color blanco."}
        };
 
        //Aplicando estilo color negrita a los encabezados
        CellStyle style = book.createCellStyle();
        Font font = book.createFont();
     
        style.setFont(font);
 
        //Generando el contenido del archivo de Excel
        for (int i = 0; i <= document.length; i++) {
            XSSFRow row = hoja1.createRow(i);//se crea las filas
            for (int j = 0; j < header.length; j++) {
                if (i == 0) {//Recorriendo cabecera
                    XSSFCell cell = row.createCell(j);//Creando la celda de la cabecera en conjunto con la posición
                    cell.setCellStyle(style); //Añadiendo estilo a la celda creada anteriormente
                    cell.setCellValue(header[j]);//Añadiendo el contenido de nuestra lista de productos
                } else {//para el contenido
                    XSSFCell cell = row.createCell(j);//Creando celda para el contenido del producto
                    cell.setCellValue(document[i - 1][j]); //Añadiendo el contenido
                }
            }
        }
 
        File excelFile;
        excelFile = new File(filePath); // Referenciando a la ruta y el archivo Excel a crear
        try (FileOutputStream fileOuS = new FileOutputStream(excelFile)) {
//            if (excelFile.exists()) { // Si el archivo existe lo eliminaremos
//                excelFile.delete();
//                System.out.println("Archivo eliminado.!");
//            }
            book.write(fileOuS);
            fileOuS.flush();
            fileOuS.close();
            System.out.println("Archivo Creado.!");
 
            
              try {

        
            File objetofile = new File("/home/guiovanny/Descargas/Productos.xlsx");
            Desktop.getDesktop().open(objetofile);

     }catch (IOException ex) {

            System.out.println(ex);

     }
              
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
}