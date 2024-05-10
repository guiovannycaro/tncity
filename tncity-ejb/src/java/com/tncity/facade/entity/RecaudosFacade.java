/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.informex.entity.InformeFacade;
import com.tncity.informex.pojos.Recaudos;
import com.tncity.properties.Propiedad;

import com.tncity.util.Cadena;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.hibernate.impl.SessionImpl;

@Stateless
public class RecaudosFacade extends AbstractFacade<Recaudos> {

    private static String[] columns = {
        "nombres",
        " apellidos",
        "tipo_documento",
        " numero_documento",
        " num_telefono",
        " num_telefono2",
        " Email",
        " Estado",
        " Conteo Recaudos",
        " Telefono SmS"

    };

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public RecaudosFacade() {
        super(Recaudos.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idciudad", "nombre", "iddepartamento.iddepartamento", "iddepartamento.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Recaudos parseObject(Object[] o) {
        Recaudos c = new Recaudos();
        c.setNombres(o[0].toString());
        c.setApellidos(o[1].toString());
        c.setConteo_recaudo(o[2].toString());
        c.setEmail(o[3].toString());
        c.setEstado(o[4].toString());
        c.setNumdocumento(o[5].toString());
        c.setNum_telefono(o[6].toString());
        c.setNum_telefono2(o[7].toString());
        return c;
    }

    @Override
    public void delete(Object idciudad, StringBuilder err) {
        deleteGeneral(idciudad, err);
    }

    @Override
    public void create(Recaudos obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Recaudos obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Recaudos> listFullTextLight(String attrOrd, String ascDesc) {
        List<Recaudos> lstC = new ArrayList<>();
        String sql
                = " SELECT "
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento AS tipo_documento,"
                + " persona.numdocumento AS numero_documento,"
                + " persona.num_telefono,"
                + " persona.num_telefono2 AS Celular,"
                + " persona.email,"
                + " recaudo.estado,"
                + " count(recaudo.idrecaudo) as conteo_recaudo,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + " FROM recaudo"
                + " JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + " JOIN persona ON benefactor.idpersona = persona.idpersona"
                + " WHERE recaudo.idpasarela = 1 GROUP BY ("
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento ,"
                + " persona.numdocumento ,"
                + " persona.num_telefono,"
                + " persona.num_telefono2,"
                + " persona.email,"
                + " recaudo.estado,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + ") ORDER BY recaudo." + attrOrd + " " + ascDesc + "";
       
        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            Recaudos c = new Recaudos();
            c.setNombres(o[0].toString());
            c.setApellidos(o[1].toString());
            c.setTipodocumento(o[2].toString());

            c.setNumdocumento(o[3].toString());
            c.setNum_telefono(o[4].toString());
            c.setNum_telefono2(o[5].toString());
            c.setEmail(o[6].toString());

            c.setEstado(o[7].toString());
            c.setConteo_recaudo(o[8].toString());
            c.setTelefonosms(o[9].toString());
            c.setPasareladia(o[10].toString());

            lstC.add(c);
        }
        return lstC;
    }

    public long countTotalRecaudosFechas(String desde, String hasta, String idbenefactor, String idpasarela) {
        String nombres = "";
        String apellidos = "";

        if (idbenefactor.equals("")) {
            nombres = "";
            apellidos = "";
        } else {

            String[] parts = idbenefactor.split(" ");
            nombres = parts[0]; // 123
            apellidos = parts[1]; // 654321
        }

        String sql = " SELECT "
                + " COUNT(recaudo.idrecaudo)"
                + " FROM recaudo "
                + " JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + " JOIN persona ON benefactor.idpersona = persona.idpersona"
                + " where recaudo.idpasarela = 1 "
                + " and recaudo.created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and recaudo.created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and cast(recaudo.pasareladia as  text)  lIKE '%" + idpasarela + "%' "
                + " and cast(persona.nombres as  text)  lIKE '%" + nombres + "%'"
                + " and cast(persona.apellidos as  text)  lIKE '%" + apellidos + "%'";

        
        return numFromSQL(sql, new BigInteger("0")).longValue();

    }

    public long CountTotalRecaudosCompleto() {

        String sql = " SELECT "
                + " COUNT(recaudo.idrecaudo)"
                + " FROM recaudo "
                + " JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + " JOIN persona ON benefactor.idpersona = persona.idpersona"
                + " where recaudo.idpasarela = 1 ";

        return numFromSQL(sql, new BigInteger("0")).longValue();

    }

    public List<Recaudos> ListarInformeFechas(String desde, String hasta, String idbenefactor, String idpasarela, String attrOrd, String ascDesc) {
        String nombres = "";
        String apellidos = "";

        if (idbenefactor.equals("")) {
            nombres = "";
            apellidos = "";
        } else {

            String[] parts = idbenefactor.split(" ");
            nombres = parts[0]; // 123
            apellidos = parts[1]; // 654321
        }

        List<Recaudos> lstC = new ArrayList<>();
        String sql
                = " SELECT "
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento AS tipo_documento,"
                + " persona.numdocumento AS numero_documento,"
                + " persona.num_telefono,"
                + " persona.num_telefono2 AS Celular,"
                + " persona.email,"
                + " recaudo.estado,"
                + " count(recaudo.idrecaudo) as conteo_recaudo,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + " FROM recaudo"
                + " JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + " JOIN persona ON benefactor.idpersona = persona.idpersona"
                + " WHERE recaudo.idpasarela = 1 "
                + " and recaudo.created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and recaudo.created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and cast(recaudo.pasareladia as  text)  lIKE '%" + idpasarela + "%' "
                + " and cast(persona.nombres as  text)  lIKE '%" + nombres + "%'"
                + " and cast(persona.apellidos as  text)  lIKE '%" + apellidos + "%'"
                + " GROUP BY ("
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento ,"
                + " persona.numdocumento ,"
                + " persona.num_telefono,"
                + " persona.num_telefono2,"
                + " persona.email,"
                + " recaudo.estado,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + ") ORDER BY recaudo." + attrOrd + " " + ascDesc + "";

        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            Recaudos c = new Recaudos();
            c.setNombres(o[0].toString());
            c.setApellidos(o[1].toString());
            c.setTipodocumento(o[2].toString());

            c.setNumdocumento(o[3].toString());
            c.setNum_telefono(o[4].toString());
            c.setNum_telefono2(o[5].toString());
            c.setEmail(o[6].toString());

            c.setEstado(o[7].toString());
            c.setConteo_recaudo(o[8].toString());
            c.setTelefonosms(o[9].toString());
            c.setPasareladia(o[10].toString());

            lstC.add(c);
        }
        return lstC;
    }

    public List<Recaudos> ListarInformeFechasCompleto(String attrOrd, String ascDesc) {

        List<Recaudos> lstC = new ArrayList<>();
        String sql
                = " SELECT "
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento AS tipo_documento,"
                + " persona.numdocumento AS numero_documento,"
                + " persona.num_telefono,"
                + " persona.num_telefono2 AS Celular,"
                + " persona.email,"
                + " recaudo.estado,"
                + " count(recaudo.idrecaudo) as conteo_recaudo,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + " FROM recaudo"
                + " JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + " JOIN persona ON benefactor.idpersona = persona.idpersona"
                + " WHERE recaudo.idpasarela = 1 "
                + " GROUP BY ("
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento ,"
                + " persona.numdocumento ,"
                + " persona.num_telefono,"
                + " persona.num_telefono2,"
                + " persona.email,"
                + " recaudo.estado,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + ") ORDER BY recaudo." + attrOrd + " " + ascDesc + "";
      
        List<Object[]> lst = findNativeGeneric(sql);
        for (Object[] o : lst) {
            Recaudos c = new Recaudos();
            c.setNombres(o[0].toString());
            c.setApellidos(o[1].toString());
            c.setTipodocumento(o[2].toString());

            c.setNumdocumento(o[3].toString());
            c.setNum_telefono(o[4].toString());
            c.setNum_telefono2(o[5].toString());
            c.setEmail(o[6].toString());

            c.setEstado(o[7].toString());
            c.setConteo_recaudo(o[8].toString());
            c.setTelefonosms(o[9].toString());
            c.setPasareladia(o[10].toString());

            lstC.add(c);
        }
        return lstC;
    }

    public List<Recaudos> ListarRecaudosFechas(String desde, String hasta, String idbenefactor, String idpasarela, String attrOrder, String ascDesc) {

        String nombres = "";
        String apellidos = "";

        if (idbenefactor.equals("")) {
            nombres = "";
            apellidos = "";
        } else {

            String[] parts = idbenefactor.split(" ");
            nombres = parts[0]; // 123
            apellidos = parts[1]; // 654321
        }
        List<Recaudos> lstC = new ArrayList<>();
        String sql
                = " SELECT "
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento AS tipo_documento,"
                + " persona.numdocumento AS numero_documento,"
                + " persona.num_telefono,"
                + " persona.num_telefono2 AS Celular,"
                + " persona.email,"
                + " recaudo.estado,"
                + " count(recaudo.idrecaudo) as conteo_recaudo,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + " FROM recaudo"
                + " JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + " JOIN persona ON benefactor.idpersona = persona.idpersona"
                + " WHERE recaudo.idpasarela = 1 "
                + " and recaudo.created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and recaudo.created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and cast(recaudo.pasareladia as  text)  lIKE '%" + idpasarela + "%' "
                + " and cast(persona.nombres as  text)  lIKE '%" + nombres + "%'"
                + " and cast(persona.apellidos as  text)  lIKE '%" + apellidos + "%'"
                + " GROUP BY ("
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento ,"
                + " persona.numdocumento ,"
                + " persona.num_telefono,"
                + " persona.num_telefono2,"
                + " persona.email,"
                + " recaudo.estado,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + ") ORDER BY recaudo." + attrOrder + " " + ascDesc + "";

  

        List<Recaudos> lstR = new ArrayList<>();
        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {

            Recaudos r = new Recaudos();

            r.setNombres(o[0].toString());
            r.setApellidos(o[1].toString());
            r.setTipodocumento(o[2].toString());

            r.setNumdocumento(o[3].toString());
            r.setNum_telefono(o[4].toString());
            r.setNum_telefono2(o[5].toString());
            r.setEmail(o[6].toString());

            r.setEstado(o[7].toString());
            r.setConteo_recaudo(o[8].toString());
            r.setTelefonosms(o[9].toString());
            r.setPasareladia(o[10].toString());

            lstR.add(r);
        }
        return lstR;
    }

    public List<Recaudos> ListarRecaudosFechasCompleto(String attrOrder, String ascDesc) {

        List<Recaudos> lstC = new ArrayList<>();
        String sql
                = " SELECT "
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento AS tipo_documento,"
                + " persona.numdocumento AS numero_documento,"
                + " persona.num_telefono,"
                + " persona.num_telefono2 AS Celular,"
                + " persona.email,"
                + " recaudo.estado,"
                + " count(recaudo.idrecaudo) as conteo_recaudo,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + " FROM recaudo"
                + " JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + " JOIN persona ON benefactor.idpersona = persona.idpersona"
                + " WHERE recaudo.idpasarela = 1 "
                + " GROUP BY ("
                + " persona.nombres,"
                + " persona.apellidos,"
                + " persona.tipodocumento ,"
                + " persona.numdocumento ,"
                + " persona.num_telefono,"
                + " persona.num_telefono2,"
                + " persona.email,"
                + " recaudo.estado,"
                + " recaudo.telefonosms,"
                + " recaudo.pasareladia"
                + ") ORDER BY recaudo." + attrOrder + " " + ascDesc + "";



        List<Recaudos> lstR = new ArrayList<>();
        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {

            Recaudos r = new Recaudos();

            r.setNombres(o[0].toString());
            r.setApellidos(o[1].toString());
            r.setTipodocumento(o[2].toString());

            r.setNumdocumento(o[3].toString());
            r.setNum_telefono(o[4].toString());
            r.setNum_telefono2(o[5].toString());
            r.setEmail(o[6].toString());

            r.setEstado(o[7].toString());
            r.setConteo_recaudo(o[8].toString());
            r.setTelefonosms(o[9].toString());
            r.setPasareladia(o[10].toString());

            lstR.add(r);
        }
        return lstR;
    }

    public void generarExcel(List<Recaudos> lstrecaudos) throws IOException {
        String url = "";
        System.out.println("path informes " + Propiedad.getCurrentInstance().getPathInformes());
        String rutaArchivo = "";

        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "recaudosTnPagosFechas.xls";

        System.out.println("ruta archivo " + rutaArchivo);
        File fichero = new File(rutaArchivo);
         if (!fichero.exists()) {
            System.out.println("OJO: ¡¡No existe el archivo !!");
        } else {
            System.out.println("OJO: ¡archivo borrado!");
            fichero.delete();
        }

        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Runtime.getRuntime().exec("chmod 777 " + fichero);
      
        /*Se crea el objeto de tipo File con la ruta del archivo*/


        try {
            HSSFWorkbook wb = new HSSFWorkbook(); // crea libro de excel
            HSSFSheet sheet = wb.createSheet("Reporte Recaudos"); // crea hoja
            Font headerFont = wb.createFont();
            headerFont.setItalic(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.GREY_25_PERCENT.getIndex());

            CellStyle headerCellStyle = wb.createCellStyle();

            headerCellStyle.setFont(headerFont);

            HSSFRow row1 = sheet.createRow((short) 0); // crea fila
            // Create cells
            for (int i = 0; i < columns.length; i++) {
                HSSFCell cell = row1.createCell((short) i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;
            for (Recaudos employee : lstrecaudos) {

                HSSFRow row2 = sheet.createRow(rowNum++);

                row2.createCell((short) 0).setCellValue(employee.getNombres().toString()); // A2
                row2.createCell((short) 1).setCellValue(employee.getApellidos().toString()); // A2
                row2.createCell((short) 2).setCellValue(employee.getTipodocumento().toString()); // A2
                row2.createCell((short) 3).setCellValue(employee.getNumdocumento().toString()); // A2
                row2.createCell((short) 4).setCellValue(employee.getNum_telefono().toString()); // A2
                row2.createCell((short) 5).setCellValue(employee.getNum_telefono2().toString()); // A2
                row2.createCell((short) 6).setCellValue(employee.getEmail().toString()); // A2
                row2.createCell((short) 7).setCellValue(employee.getEstado().toString()); // A2
                row2.createCell((short) 8).setCellValue(employee.getConteo_recaudo().toString()); // A2
                row2.createCell((short) 9).setCellValue(employee.getTelefonosms().toString());
                row2.createCell((short) 10).setCellValue(employee.getPasareladia().toString());
            }

            FileOutputStream archivo = new FileOutputStream(fichero);

            /*Escribimos en el libro*/
            wb.write(archivo);
            /*Cerramos el flujo de datos*/
            archivo.close();
            /*Y abrimos el archivo con la clase Desktop*/

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void GenerarExcelCompleto(List<Recaudos> lstrecaudos) throws IOException {
        String url = "";
        System.out.println("path informes " + Propiedad.getCurrentInstance().getPathInformes());

        String rutaArchivo = "";

        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "recaudosTnPagosFechasCompleto.xls";

        System.out.println("ruta archivo " + rutaArchivo);
        File fichero = new File(rutaArchivo);
        
         if (!fichero.exists()) {
            System.out.println("OJO: ¡¡No existe el archivo !!");
        } else {
            System.out.println("OJO: ¡archivo borrado!");
            fichero.delete();
        }

        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Runtime.getRuntime().exec("chmod 777 " + fichero);
        
        
       
        /*Se crea el objeto de tipo File con la ruta del archivo*/
    

        try {
            HSSFWorkbook wb = new HSSFWorkbook(); // crea libro de excel
            HSSFSheet sheet = wb.createSheet("Reporte Recaudos"); // crea hoja
            Font headerFont = wb.createFont();
            headerFont.setItalic(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.GREY_25_PERCENT.getIndex());

            CellStyle headerCellStyle = wb.createCellStyle();

            headerCellStyle.setFont(headerFont);

            HSSFRow row1 = sheet.createRow((short) 0); // crea fila
            // Create cells
            for (int i = 0; i < columns.length; i++) {
                HSSFCell cell = row1.createCell((short) i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;
            for (Recaudos employee : lstrecaudos) {

                HSSFRow row2 = sheet.createRow(rowNum++);

                row2.createCell((short) 0).setCellValue(employee.getNombres().toString()); // A2
                row2.createCell((short) 1).setCellValue(employee.getApellidos().toString()); // A2
                row2.createCell((short) 2).setCellValue(employee.getTipodocumento().toString()); // A2
                row2.createCell((short) 3).setCellValue(employee.getNumdocumento().toString()); // A2
                row2.createCell((short) 4).setCellValue(employee.getNum_telefono().toString()); // A2
                row2.createCell((short) 5).setCellValue(employee.getNum_telefono2().toString()); // A2
                row2.createCell((short) 6).setCellValue(employee.getEmail().toString()); // A2
                row2.createCell((short) 7).setCellValue(employee.getEstado().toString()); // A2
                row2.createCell((short) 8).setCellValue(employee.getConteo_recaudo().toString()); // A2
                row2.createCell((short) 9).setCellValue(employee.getTelefonosms().toString());
                row2.createCell((short) 10).setCellValue(employee.getPasareladia().toString());
            }

            FileOutputStream archivo = new FileOutputStream(fichero);

            /*Escribimos en el libro*/
            wb.write(archivo);
            /*Cerramos el flujo de datos*/
            archivo.close();
            /*Y abrimos el archivo con la clase Desktop*/

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String generarPdf(List<Recaudos> lstrecaudos) {
        String url = "";

        url = crearPDF(lstrecaudos);

        return url;
    }

    public String GenerarPdfCompleto(List<Recaudos> lstrecaudos) {
        String url = "";

        url = CrearPDFCompleto(lstrecaudos);

        return url;
    }

    public String crearPDF(List<Recaudos> lstrecaudos) {

        String ruta = "";
        String rutasis = "";
        String archivo = "recaudosfechastnpagos.pdf";

        System.out.println("ruta guardar archivo" + Propiedad.getCurrentInstance().getPathInformes() + archivo);

        String rutaArchivo = "";

        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "recaudosfechastnpagosCompleto.pdf";
        System.out.println("ruta archivo " + rutaArchivo);
        File fichero = new File(rutaArchivo);
        
         if (!fichero.exists()) {
            System.out.println("OJO: ¡¡No existe el archivo !!");
        } else {
            System.out.println("OJO: ¡archivo borrado!");
            fichero.delete();
        }

        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Runtime.getRuntime().exec("chmod 777 " + fichero);
            /*Se crea el objeto de tipo File con la ruta del archivo*/
        } catch (IOException ex) {
            Logger.getLogger(InformeFacade.class.getName()).log(Level.SEVERE, null, ex);
        }


      

        Document document = new Document(PageSize.A4, 5, 5, 5, 5);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fichero));
            document.open();

            // Este codigo genera una tabla de 3 columnas
            PdfPTable table = new PdfPTable(4);

            int rowNum = 1;
            for (Recaudos employee : lstrecaudos) {

                table.getDefaultCell().setBorder(1);
                table.getDefaultCell().setPadding(3);

                table.addCell("Nombres");
                table.addCell(employee.getNombres());

                table.addCell("Apellidos");
                table.addCell(employee.getApellidos());
                table.addCell("Tipo Documento");
                table.addCell(employee.getTipodocumento());

                table.addCell("Numero De Documento");
                table.addCell(employee.getNumdocumento());

                table.addCell("Correo");
                table.addCell(employee.getEmail());

                table.addCell("Estado Recarga");
                table.addCell(employee.getEstado());

                table.addCell("Conteo Recarga");
                table.addCell(employee.getConteo_recaudo());
                table.addCell("Telefono Recarga");
                table.addCell(employee.getTelefonosms());

            }
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Final "));

            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(4);
            table.addCell(celdaFinal);

            // Agregamos la tabla al documento            
            document.add(table);

            document.close();

        } catch (Exception e) {
            System.err.println("Ocurrio un error al crear el archivo");
            System.exit(-1);
        }
        return ruta;

    }

    public String CrearPDFCompleto(List<Recaudos> lstrecaudos) {

        String ruta = "";
        String rutasis = "";
        String archivo = "recaudosfechastnpagosCompleto.pdf";

        System.out.println("ruta guardar archivo" + Propiedad.getCurrentInstance().getPathInformes() + archivo);

        String rutaArchivo = "";

        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "recaudosfechastnpagosCompleto.pdf";
        System.out.println("ruta archivo " + rutaArchivo);
        File fichero = new File(rutaArchivo);
        if (!fichero.exists()) {
            System.out.println("OJO: ¡¡No existe el archivo !!");
        } else {
            System.out.println("OJO: ¡archivo borrado!");
            fichero.delete();
        }

        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Runtime.getRuntime().exec("chmod 777 " + fichero);
            /*Se crea el objeto de tipo File con la ruta del archivo*/
        } catch (IOException ex) {
            Logger.getLogger(InformeFacade.class.getName()).log(Level.SEVERE, null, ex);
        }


        Document document = new Document(PageSize.A4, 5, 5, 5, 5);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fichero));
            document.open();

            // Este codigo genera una tabla de 3 columnas
            PdfPTable table = new PdfPTable(4);

            int rowNum = 1;
            for (Recaudos employee : lstrecaudos) {

                table.getDefaultCell().setBorder(1);
                table.getDefaultCell().setPadding(3);

                table.addCell("Nombres");
                table.addCell(employee.getNombres());

                table.addCell("Apellidos");
                table.addCell(employee.getApellidos());
                table.addCell("Tipo Documento");
                table.addCell(employee.getTipodocumento());

                table.addCell("Numero De Documento");
                table.addCell(employee.getNumdocumento());

                table.addCell("Correo");
                table.addCell(employee.getEmail());

                table.addCell("Estado Recarga");
                table.addCell(employee.getEstado());

                table.addCell("Conteo Recarga");
                table.addCell(employee.getConteo_recaudo());
                table.addCell("Telefono Recarga");
                table.addCell(employee.getTelefonosms());

            }
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Final "));

            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(4);
            table.addCell(celdaFinal);

            // Agregamos la tabla al documento            
            document.add(table);

            document.close();

        } catch (Exception e) {
            System.err.println("Ocurrio un error al crear el archivo");
            System.exit(-1);
        }
        return ruta;

    }

    public String rutadescargas() {
        String url = "";
        String archivo = "recaudosfechastnpagos.pdf";
        url = Propiedad.getCurrentInstance().getPathInformes() + archivo;
        System.out.println("ruta guardar archivo" + Propiedad.getCurrentInstance().getPathInformes() + archivo);

        return url;

    }
}
