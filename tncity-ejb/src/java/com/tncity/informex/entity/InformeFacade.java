/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.informex.entity;

/**
 *
 * @author guiovanny
 */
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Rectangle;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.informex.pojos.VistaTnpagos;
import com.tncity.jpa.pojo.Informes;
import com.tncity.properties.Propiedad;
import com.tncity.util.Tiempo;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import javax.swing.JFileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;

@Stateless
public class InformeFacade extends AbstractFacade<Informes> {

    public static final File PLANTILLA_EXEL_GENERIC = new File(Propiedad.getCurrentInstance().getPathInformes() + "reportefechastnpagos.pdf");
    private static final Logger LOGGER = Logger.getLogger("mx.com.hash.newexcel.Exel");
    public static String archivo = "/opt/tncity/tncity-war/web/documentos/reportefechastnpagos.pdf";

    private static String[] columns = {"idvista",
        "nombres",
        "apellidos",
        "tipo documento",
        "numero documento",
        "num telefono",
        "email",
        "ciudad familiar",
        "departamento familiar",
        "pais familiar",
        "tipodocumento ppl",
        "numero documento ppl",
        "nombre ppl",
        "idrecaudo",
        "valor",
        "estado",
        "log",
        "created_at",
        "check_telefonia_at",
        "establecimiento",
        "cod_pago",
        "telefonosms",
        "observacion",
        "observacion_adicional",
        "ciudad_ppl",
        "nombre_pasarela",
        "codtransaccion",
        "formapago",
        "franquicia",
        "descripcion",
        "referencia1",
        "fechapago",
        "numerorecibo",
        "codigo",
        "mensajeerror"
    };

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public InformeFacade() {
        super(Informes.class);
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
    protected Informes parseObject(Object[] o) {
        Informes c = new Informes();
        // c.setIdciudad((Integer) o[0]);

        return c;
    }

    @Override
    public void create(Informes obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Informes obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long countTotalInformeFechas(String desde, String hasta, String idbenefactor, String documento, String idpasarela) {
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

        String sql = "SELECT "
                + " COUNT(*)"
                + " FROM vista_recargas_tnpagos where "
                + " created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss') "
                + " and cast(numero_documento as  text)  lIKE '%" + documento + "%' "
                + " and cast(pasareladia as  text)  lIKE '%" + idpasarela + "%' "
                + " and cast(nombres as  text)  lIKE '%" + nombres + "%'"
                + " and cast(apellidos as  text)  lIKE '%" + apellidos + "%'";

        System.out.println("todos informes :" + sql);
        return numFromSQL(sql, new BigInteger("0")).longValue();

    }

    public List<VistaTnpagos> ListarInformeFechas(String desde, String hasta, String idbenefactor, String documento, String idpasarela, String attrOrder, String ascDesc) {

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

        String sql = "SELECT "
                + " vista_recargas_tnpagos.idvista, "
                + " vista_recargas_tnpagos.nombres, "
                + " vista_recargas_tnpagos.apellidos, "
                + " vista_recargas_tnpagos.tipo_documento, "
                + " vista_recargas_tnpagos.numero_documento, "
                + " vista_recargas_tnpagos.num_telefono, "
                + " vista_recargas_tnpagos.email, "
                + " vista_recargas_tnpagos.ciudad_familiar, "
                + " vista_recargas_tnpagos.departamento_familiar, "
                + " vista_recargas_tnpagos.pais_familiar, "
                + " vista_recargas_tnpagos.tipodocumento_ppl, "
                + " vista_recargas_tnpagos.numero_documento_ppl, "
                + " vista_recargas_tnpagos.nombre_ppl, "
                + " vista_recargas_tnpagos.idrecaudo, "
                + " vista_recargas_tnpagos.valor, "
                + " vista_recargas_tnpagos.estado, "
                + " vista_recargas_tnpagos.log, "
                + " vista_recargas_tnpagos.created_at, "
                + " vista_recargas_tnpagos.check_telefonia_at, "
                + " vista_recargas_tnpagos.establecimiento, "
                + " vista_recargas_tnpagos.cod_pago, "
                + " vista_recargas_tnpagos.telefonosms, "
                + " vista_recargas_tnpagos.observacion, "
                + " vista_recargas_tnpagos.observacion_adicional, "
                + " vista_recargas_tnpagos.ciudad_ppl, "
                + " vista_recargas_tnpagos.pasareladia, "
                + " vista_recargas_tnpagos.nombre_pasarela, "
                + " vista_recargas_tnpagos.codtransaccion, "
                + " vista_recargas_tnpagos.formapago, "
                + " vista_recargas_tnpagos.franquicia, "
                + " vista_recargas_tnpagos.descripcion, "
                + " vista_recargas_tnpagos.referencia1, "
                + " vista_recargas_tnpagos.fechapago, "
                + " vista_recargas_tnpagos.numerorecibo, "
                + " vista_recargas_tnpagos.codigo, "
                + " vista_recargas_tnpagos.mensajeerror "
                + " FROM vista_recargas_tnpagos where "
                + " created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss.SSS')"
                + " and created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss.SSS')"
                + " and cast(numero_documento as  text)  lIKE '%" + documento + "%' "
                + " and cast(pasareladia as  text)  lIKE '%" + idpasarela + "%' "
                + " and cast(nombres as  text)  lIKE '%" + nombres + "%'"
                + " and cast(apellidos as  text)  lIKE '%" + apellidos + "%'";

        System.out.println("toda informes: " + sql);

        List<VistaTnpagos> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            VistaTnpagos i = new VistaTnpagos();
            i.setIdvista(o[0].toString());
            i.setNombres(o[1].toString());
            i.setApellidos(o[2].toString());
            i.setTipo_documento(o[3].toString());
            i.setNumero_documento(o[4].toString());
            i.setNum_telefono(o[5].toString());
            i.setEmail(o[6].toString());
            i.setCiudad_familiar(o[7].toString());
            i.setDepartamento_familiar(o[8].toString());
            i.setPais_familiar(o[9].toString());
            i.setTipo_documento(o[10].toString());
            i.setNumero_documento_ppl(o[11].toString());
            i.setNombre_ppl(o[12].toString());
            i.setIdrecaudo(o[13].toString());
            i.setValor(o[14].toString());
            i.setEstado(o[15].toString());
            i.setLog(o[16].toString());
            i.setCreated_at(o[17].toString());
            i.setCheck_telefonia_at(o[18].toString());
            i.setEstablecimiento(o[19].toString());
            i.setCod_pago(o[20].toString());
            i.setTelefonosms(o[21].toString());
            i.setObservacion(o[22].toString());
            i.setObservacionAdicional(o[23].toString());
            i.setCiudad_ppl(o[24].toString());
            i.setPasareladia(o[25].toString());
            i.setNombre_pasarela(o[26].toString());
            i.setCodtransaccion(o[27].toString());
            i.setFormapago(o[28].toString());
            i.setFranquicia(o[29].toString());
            i.setDescripcion(o[30].toString());
            i.setReferencia1(o[31].toString());
            i.setFechapago(o[32].toString());
            i.setNumerorecibo(o[33].toString());
            i.setCodigo(o[34].toString());
            i.setMensajeerror(o[35].toString());

            lstR.add(i);

        }
        return lstR;

    }

    public long countTotalInformeCompleto() {

        String sql = "SELECT "
                + " COUNT(*)"
                + " FROM vista_recargas_tnpagos ";

        System.out.println("todos informes :" + sql);
        return numFromSQL(sql, new BigInteger("0")).longValue();

    }

    public void generarExcel(List<VistaTnpagos> lstInformesTnPagos, String desde, String hasta) throws IOException {

        String url = "";
        System.out.println("path informes " + Propiedad.getCurrentInstance().getPathInformes());
        String rutaArchivo = "";
 Date fIni = null;
        Date fFin = null;
        try {
            DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
           fIni = fecha.parse(desde);
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "reporteTnPagosFechas.xls";
        System.out.println("ruta archivo " + rutaArchivo);
        File fichero = new File(rutaArchivo);

//
//        if (!fichero.exists()) {
//            System.out.println("OJO: ¡¡No existe el archivo !!");
//        } else {
//            System.out.println("OJO: ¡archivo borrado!");
//            fichero.delete();
//        }
//
//        File nfichero = new File(rutaArchivo);
//        if (!nfichero.exists()) {
//            try {
//                nfichero.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        Runtime.getRuntime().exec("chmod 777 " + nfichero);
        /*Se crea el objeto de tipo File con la ruta del archivo*/
        try {
            HSSFWorkbook wb = new HSSFWorkbook(); // crea libro de excel
            HSSFSheet sheet = wb.createSheet("Reporte Recargas"); // crea hoja

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

            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

            int rowNum = 1;
            for (VistaTnpagos employee : lstInformesTnPagos) {

                HSSFRow row2 = sheet.createRow(rowNum++);

                row2.createCell((short) 0).setCellValue(employee.getIdvista().toString()); // A2
                row2.createCell((short) 1).setCellValue(employee.getNombres().toString()); // B2
                row2.createCell((short) 2).setCellValue(employee.getApellidos()); // C2
                row2.createCell((short) 3).setCellValue(employee.getTipo_documento());// D2
                row2.createCell((short) 4).setCellValue(employee.getNumero_documento());// E2
                row2.createCell((short) 5).setCellValue(employee.getNum_telefono());// F2
                row2.createCell((short) 6).setCellValue(employee.getEmail());// G2
                row2.createCell((short) 7).setCellValue(employee.getCodigo());// H2
                row2.createCell((short) 8).setCellValue(employee.getDireccion());// I2
                row2.createCell((short) 9).setCellValue(employee.getCiudad_familiar());// J2
                row2.createCell((short) 10).setCellValue(employee.getDepartamento_familiar());// K2
                row2.createCell((short) 11).setCellValue(employee.getPais_familiar());// L2
                row2.createCell((short) 12).setCellValue(employee.getTipodocumento_ppl());// M2
                row2.createCell((short) 13).setCellValue(employee.getNumero_documento_ppl());// N2
                row2.createCell((short) 14).setCellValue(employee.getNombre_ppl());// O2
                row2.createCell((short) 15).setCellValue(employee.getIdrecaudo());// P2
                row2.createCell((short) 16).setCellValue(employee.getValor());// Q2
                row2.createCell((short) 17).setCellValue(employee.getLog());// R2
                row2.createCell((short) 18).setCellValue(employee.getCreated_at());// S2
                row2.createCell((short) 19).setCellValue(employee.getCheck_telefonia_at());// T2
                row2.createCell((short) 20).setCellValue(employee.getEstablecimiento());// U2
                row2.createCell((short) 21).setCellValue(employee.getCod_pago());// V2
                row2.createCell((short) 22).setCellValue(employee.getTelefonosms());// W2
                row2.createCell((short) 23).setCellValue(employee.getObservacion());// X2
                row2.createCell((short) 24).setCellValue(employee.getObservacionAdicional());// Y2
                row2.createCell((short) 25).setCellValue(employee.getCiudad_ppl());// Z2
                row2.createCell((short) 26).setCellValue(employee.getPasareladia());// AA2
                row2.createCell((short) 27).setCellValue(employee.getNombre_pasarela());// AB2
                row2.createCell((short) 28).setCellValue(employee.getCodtransaccion());// AC2
                row2.createCell((short) 29).setCellValue(employee.getFormapago());// AD2
                row2.createCell((short) 30).setCellValue(employee.getFranquicia());// AE2
                row2.createCell((short) 31).setCellValue(employee.getDescripcion());// AF2
                row2.createCell((short) 32).setCellValue(employee.getReferencia1());// AG2
                row2.createCell((short) 33).setCellValue(employee.getFechapago());// AH2
                row2.createCell((short) 34).setCellValue(employee.getNumerorecibo());// AI2
                row2.createCell((short) 35).setCellValue(employee.getCodigo());// AJ2
                row2.createCell((short) 36).setCellValue(employee.getMensajeerror());// AK2
            }


            /*Se inicializa el flujo de datos con el archivo xls*/
            FileOutputStream archivo = new FileOutputStream(fichero);
            System.out.println("se ha creado el archivo " + archivo);
            /*Escribimos en el libro*/
            wb.write(archivo);

            /*Cerramos el flujo de datos*/
            archivo.close();
            /*Y abrimos el archivo con la clase Desktop*/

            File micarpeta = new File("/opt/tncity/tncity-war/web/documentos");

            File[] listaFicheros = micarpeta.listFiles();
            System.out.println("listado de archivos ");
            for (int i = 0; i < listaFicheros.length; i++) {

                System.out.println(listaFicheros[i].getName());

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

//        response.getOutputStream().flush();
//        FacesContext.getCurrentInstance().responseComplete();
    }

    public void urlReques(String pathFile) throws FileNotFoundException, IOException {
        //inicio de descarga
        File download = new File(pathFile);
        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(download);
        byte[] bytes = new byte[10000];
        int read = 0;
        if (!ctx.getResponseComplete()) {
            String fileName = download.getName();
            String contentType = "application/vnd.ms-excel";
            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            response.setContentType(contentType);
            response.setHeader("content-disposition", "attachment;filename=\"" + fileName + "\"");
            ServletOutputStream out = response.getOutputStream();

            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            ctx.responseComplete();
        }
    }

    public String generarPdf(List<VistaTnpagos> lstInformesTnPagos) {
        String url = "";
        url = crearPDF(lstInformesTnPagos);
        return url;
    }

    public String crearPDF(List<VistaTnpagos> lstInformesTnPagos) {

        String ruta = "";
        String rutasis = "";
        String archivo = "reportefechastnpagos.pdf";

        System.out.println("ruta guardar archivo" + Propiedad.getCurrentInstance().getPathInformes() + archivo);

        String rutaArchivo = "";

        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "reportefechastnpagos.pdf";
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

            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
            int rowNum = 1;
            for (VistaTnpagos employee : lstInformesTnPagos) {

                table.getDefaultCell().setBorder(1);
                table.getDefaultCell().setPadding(3);
                table.addCell("Id Recaudo");
                table.addCell(employee.getIdvista().toString());
                table.addCell("Nombres");
                table.addCell(employee.getNombres());

                table.addCell("Apellidos");
                table.addCell(employee.getApellidos());
                table.addCell("Tipo Documento");
                table.addCell(employee.getTipo_documento());

                table.addCell("Numero De Documento");
                table.addCell(employee.getNumero_documento());
                table.addCell("NUmero De Telefono");
                table.addCell(employee.getNum_telefono());

                table.addCell("Correo");
                table.addCell(employee.getEmail());
                table.addCell("Codigo Interno");
                table.addCell(employee.getCodigo());
                table.addCell("NDireccion");
                table.addCell(employee.getDireccion());
                table.addCell("Ciudad");
                table.addCell(employee.getCiudad_familiar());
                table.addCell("Departamento");
                table.addCell(employee.getDepartamento_familiar());
                table.addCell("Pais");
                table.addCell(employee.getPais_familiar());
                table.addCell("Documento PPL");
                table.addCell(employee.getTipodocumento_ppl());
                table.addCell("Num. documento PPL");
                table.addCell(employee.getNumero_documento_ppl());
                table.addCell("Nombre PPL");
                table.addCell(employee.getNombre_ppl());
                table.addCell("Numero De Documento");
                table.addCell(employee.getIdrecaudo());
                table.addCell("Codigo Familiar");
                table.addCell(employee.getIdbenefactor());
                table.addCell("Valor Recarga");
                table.addCell(employee.getValor());

                table.addCell("Fecha de Recarga");
                table.addCell(employee.getCreated_at());

                table.addCell("Estableciomiento");
                table.addCell(employee.getEstablecimiento());
                table.addCell("Codigo Pago");
                table.addCell(employee.getCod_pago());
                table.addCell("Telefono Mensaje");
                table.addCell(employee.getTelefonosms());
                table.addCell("Observaciones");
                table.addCell(employee.getObservacion());
                table.addCell("Observacion Adicional");
                table.addCell(employee.getObservacionAdicional());
                table.addCell("Ciudad PPL");
                table.addCell(employee.getCiudad_ppl());
                table.addCell("Pasarela Pago");
                table.addCell(employee.getPasareladia());
                table.addCell("Pasarela Pago");
                table.addCell(employee.getNombre_pasarela());
                table.addCell("Cod.Transaccion");
                table.addCell(employee.getCodtransaccion());
                table.addCell("Forma Pago");
                table.addCell(employee.getFormapago());
                table.addCell("Franquicia");
                table.addCell(employee.getFranquicia());
                table.addCell("Descripcion");
                table.addCell(employee.getDescripcion());
                table.addCell("Referencia");
                table.addCell(employee.getReferencia1());
                table.addCell("Fecha Pago");
                table.addCell(employee.getFechapago());
                table.addCell("Numero De Recibo");
                table.addCell(employee.getNumerorecibo());
                table.addCell("Codigo Transaccion");
                table.addCell(employee.getCodigo());
                table.addCell("Descripcion Transaccion");
                table.addCell(employee.getMensajeerror());

                table.addCell("");
                table.addCell("");

                Paragraph saltoDeLinea2 = new Paragraph("");
                table.addCell(saltoDeLinea2);

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
        String archivo = "reportefechastnpagos.pdf";
        url = Propiedad.getCurrentInstance().getPathInformes() + archivo;
        System.out.println("ruta guardar archivo" + Propiedad.getCurrentInstance().getPathInformes());

        return url;

    }

}
