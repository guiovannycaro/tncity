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
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tncity.informex.pojos.VistaTnpagos;
import com.tncity.jpa.pojo.Informes;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;

/**
 *
 * @author david
 */
public class ExelUtil {

    private static final Logger LOGGER = Logger.getLogger("mx.com.hash.newexcel.Exel");

    private static final String nombre_archivo = "reportefechastnpagos.xlsx";

    private static String[] columns = {"idvista",
        "nombres",
        " apellidos",
        "tipo_documento",
        " numero_documento",
        " num_telefono",
        " email",
        " ciudad_familiar",
        " departamento_familiar",
        " pais_familiar",
        " tipodocumento_ppl",
        " numero_documento_ppl",
        " nombre_ppl",
        " idrecaudo",
        " idbenefactor",
        " valor",
        " estado",
        "log",
        "created_at",
        " check_telefonia_at",
        " establecimiento",
        " cod_pago",
        "telefonosms",
        " observacion",
        "observacion_adicional",
        " ciudad_ppl",
        " nombre_pasarela",
        "codtransaccion",
        " formapago",
        " franquicia",
        " descripcion",
        " referencia1",
        " fechapago",
        " numerorecibo",
        " codigo",
        " mensajeerror"
    };

    //  private static List<VistaTnpagos> employees = new ArrayList<>();
    public static String crearExel(String destination, List<VistaTnpagos> lstInformesTnPagos) throws IOException, InvalidFormatException {
        String url = "";
        String a = "";
        File file = new File(destination + "reportefechastnpagos.xlsx");
        System.out.println("crearExel linea 86 " + file);
        String msg = "";
        if (!file.createNewFile()) {
            msg = "FALLA, creando archivo \"" + file.getPath() + "/" + file.getName() + "\", chequee los permisos !";
        }

        //Crear libro de trabajo en blanco
        File img = new File(destination);
        System.out.println("pathb " + img);

        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Reporte Tn Pagos");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setItalic(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for (VistaTnpagos employee : lstInformesTnPagos) {
            Row row = sheet.createRow(rowNum++);

            System.out.println("datos lista " + lstInformesTnPagos.toString());
            row.createCell(0).setCellValue(employee.getNombres());
            row.createCell(1).setCellValue(employee.getEmail());
            row.createCell(2).setCellValue(employee.getApellidos());
            row.createCell(3).setCellValue(employee.getCodigo());

            row.createCell(4).setCellValue(employee.getNombres());
            row.createCell(5).setCellValue(employee.getEmail());
            row.createCell(6).setCellValue(employee.getApellidos());
            row.createCell(7).setCellValue(employee.getCodigo());

            row.createCell(8).setCellValue(employee.getNombres());
            row.createCell(9).setCellValue(employee.getEmail());
            row.createCell(10).setCellValue(employee.getApellidos());
            row.createCell(11).setCellValue(employee.getCodigo());

            row.createCell(12).setCellValue(employee.getNombres());
            row.createCell(13).setCellValue(employee.getEmail());
            row.createCell(14).setCellValue(employee.getApellidos());
            row.createCell(15).setCellValue(employee.getCodigo());

            row.createCell(16).setCellValue(employee.getNombres());
            row.createCell(17).setCellValue(employee.getEmail());
            row.createCell(18).setCellValue(employee.getApellidos());
            row.createCell(19).setCellValue(employee.getCodigo());

            row.createCell(20).setCellValue(employee.getNombres());
            row.createCell(21).setCellValue(employee.getEmail());
            row.createCell(22).setCellValue(employee.getApellidos());
            row.createCell(23).setCellValue(employee.getCodigo());

            row.createCell(24).setCellValue(employee.getNombres());
            row.createCell(25).setCellValue(employee.getEmail());
            row.createCell(26).setCellValue(employee.getApellidos());
            row.createCell(27).setCellValue(employee.getCodigo());

            row.createCell(28).setCellValue(employee.getNombres());
            row.createCell(29).setCellValue(employee.getEmail());
            row.createCell(30).setCellValue(employee.getApellidos());
            row.createCell(31).setCellValue(employee.getCodigo());

            row.createCell(32).setCellValue(employee.getNombres());
            row.createCell(33).setCellValue(employee.getEmail());
            row.createCell(34).setCellValue(employee.getApellidos());
            row.createCell(35).setCellValue(employee.getCodigo());

        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        String sSistemaOperativo = System.getProperty("os.name");
        System.out.println(sSistemaOperativo);
        try {
            // Write the output to a file
            OutputStream out = new FileOutputStream(new File(destination + "/reportefechastnpagos.xlsx"));

            workbook.write(out);
            out.flush();
            out.close();
            url = destination + "/reportefechastnpagos.xlsx";

            System.out.println("datos sobreescribe " + url);
            // Closing the workbook
        } catch (IOException e) {
            System.err.println("Error at file writing");
            e.printStackTrace();
        }
        return a = url;
    }

    public static String abrir(String file) {
        //ruta del archivo en el pc
        String ruta = "";
        try {
            Desktop myDesktop = null;
            if (Desktop.isDesktopSupported()) {
                myDesktop = Desktop.getDesktop();
            }

            if (myDesktop != null) {

                File myFile = new File(file);

                if (myFile.exists()) {
                    myDesktop.open(myFile);
                } else {
                    System.out.println("This file is not a valid one");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ruta;
    }

    public static void main(String[] args) {
//        try {
//            ExelUtil e = new ExelUtil();
//            e.crearExel("/opt/tncity/tncity-war/web/documentos/");
//
//            String file = "/opt/tncity/tncity-war/web/documentos/ejemplo.xlsx";
//            e.abrir(file);
//        } catch (IOException ex) {
//            Logger.getLogger(ExelUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
