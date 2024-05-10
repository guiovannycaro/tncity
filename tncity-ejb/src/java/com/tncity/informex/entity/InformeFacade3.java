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
import com.tncity.facade.general.AbstractFacade;
import com.tncity.informex.pojos.VistaWSpagos;
import com.tncity.jpa.pojo.Informes;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import com.lowagie.text.pdf.PdfWriter;

import com.lowagie.text.Document;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.tncity.informex.pojos.VistaTnpagos;
import com.tncity.properties.Propiedad;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;


@Stateless
public class InformeFacade3 extends AbstractFacade<Informes> {

    private static String[] columns = {
        "idvista",
        "nombres",
        "apellidos",
        "tipo_documento",
        "numero_documento",
        "num_telefono",
        "email",
        "direccion",
        "ciudad_familiar",
        "departamento_familiar",
        "pais_familiar",
        "tipodocumento_ppl",
        "numero_documento_ppl",
        "nombre_ppl",
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

    public InformeFacade3() {
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

    public long countTotalInformeFechas(String desde, String hasta) {
        String sql = "SELECT "
                + " COUNT(*)"
                + " FROM vista_recargas_webservices where "
                + " created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss.SSS')"
                + " and created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss.SSS')";

        System.out.println("todos informes :" + sql);
        return numFromSQL(sql, new BigInteger("0")).longValue();

    }

    public List<VistaWSpagos> ListarInformeFechas(String desde, String hasta, String attrOrder, String ascDesc) {

        String sql = "SELECT "
                + "  vista_recargas_webservices.idvista,"
                + "  vista_recargas_webservices.nombres,"
                + "  vista_recargas_webservices.apellidos,"
                + "  vista_recargas_webservices.tipo_documento,"
                + "  vista_recargas_webservices.numero_documento,"
                + "  vista_recargas_webservices.num_telefono,"
                + "  vista_recargas_webservices.email,"
                + "  vista_recargas_webservices.ciudad_familiar,"
                + "  vista_recargas_webservices.departamento_familiar,"
                + "  vista_recargas_webservices.pais_familiar,"
                + "  vista_recargas_webservices.tipodocumento_ppl,"
                + "  vista_recargas_webservices.numero_documento_ppl,"
                + "  vista_recargas_webservices.nombre_ppl,"
                + "  vista_recargas_webservices.idrecaudo,"
                + "  vista_recargas_webservices.valor,"
                + "  vista_recargas_webservices.estado,"
                + "  vista_recargas_webservices.log,"
                + "  vista_recargas_webservices.created_at,"
                + "  vista_recargas_webservices.check_telefonia_at,"
                + "  vista_recargas_webservices.establecimiento,"
                + "  vista_recargas_webservices.cod_pago,"
                + "  vista_recargas_webservices.observacion,"
                + "  vista_recargas_webservices.observacion_adicional,"
                + "  vista_recargas_webservices.ciudad_ppl,"
                + "  vista_recargas_webservices.nombre_pasarela,"
                + "  vista_recargas_webservices.codtransaccion,"
                + "  vista_recargas_webservices.formapago,"
                + "  vista_recargas_webservices.franquicia,"
                + "  vista_recargas_webservices.descripcion,"
                + "  vista_recargas_webservices.referencia1,"
                + "  vista_recargas_webservices.fechapago,"
                + "  vista_recargas_webservices.numerorecibo,"
                + "  vista_recargas_webservices.codigo,"
                + "  vista_recargas_webservices.mensajeerror"
                + " FROM vista_recargas_webservices where "
                + " created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss.SSS')"
                + " and created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss.SSS')";

        System.out.println("toda informes: " + sql);

        List<VistaWSpagos> lstR = new ArrayList<>();

        List<Object[]> lst = findNativeGeneric(sql);

        for (Object[] o : lst) {
            VistaWSpagos i = new VistaWSpagos();
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
            i.setObservacion(o[21].toString());
            i.setCiudad_ppl(o[22].toString());
            i.setNombre_pasarela(o[23].toString());
            i.setCodtransaccion(o[24].toString());
            i.setFormapago(o[25].toString());
            i.setFranquicia(o[26].toString());
            i.setDescripcion(o[27].toString());
            i.setReferencia1(o[28].toString());
            i.setFechapago(o[29].toString());
            i.setNumerorecibo(o[30].toString());
            i.setCodigo(o[31].toString());
            i.setMensajeerror(o[32].toString());

            lstR.add(i);

        }
        return lstR;

    }

    public String generarPdf(List<VistaWSpagos> lstInformesWS) {

        String ruta = "";
        String rutasis = "";
        String archivo = "reportewsfechas.pdf";

        System.out.println("ruta guardar archivo" + Propiedad.getCurrentInstance().getPathInformes() + archivo);

        String rutaArchivo = "";

        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "reportewsfechas.pdf";
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

        Document documento = new Document();

        try {

            PdfWriter.getInstance(documento, new FileOutputStream(fichero));
            documento.open();

            PdfPTable table = new PdfPTable(3);

            int rowNum = 1;
            for (VistaWSpagos employee : lstInformesWS) {

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

                table.addCell("Ciudad PPL");
                table.addCell(employee.getCiudad_ppl());

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

            }
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span

            // Agregamos la tabla al documento            
            documento.add(table);

            documento.close();

        } catch (Exception e) {
            System.err.println("Ocurrio un error al crear el archivo");
            System.exit(-1);
        }

        System.out.println("donde va el archivo " + ruta);

//Declaramos un documento como un objecto Document. 
        return ruta;
    }

    public void generarExcel(List<VistaWSpagos> lstInformesWS) throws IOException {

        System.out.println("path informes " + Propiedad.getCurrentInstance().getPathInformes());

        String rutaArchivo = "";

        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "reporteWsFechas.xls";
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
            for (VistaWSpagos employee : lstInformesWS) {

                HSSFRow row2 = sheet.createRow(rowNum++);

                row2.createCell((short) 0).setCellValue(employee.getIdvista().toString()); // A2
                row2.createCell((short) 1).setCellValue(employee.getNombres().toString()); // B2
                row2.createCell((short) 2).setCellValue(employee.getApellidos()); // A2
                row2.createCell((short) 3).setCellValue(employee.getTipo_documento());
                row2.createCell((short) 4).setCellValue(employee.getNumero_documento());
                row2.createCell((short) 5).setCellValue(employee.getNum_telefono());
                row2.createCell((short) 6).setCellValue(employee.getEmail());
                row2.createCell((short) 7).setCellValue(employee.getCodigo());
                row2.createCell((short) 8).setCellValue(employee.getDireccion());
                row2.createCell((short) 9).setCellValue(employee.getCiudad_familiar());
                row2.createCell((short) 10).setCellValue(employee.getDepartamento_familiar());
                row2.createCell((short) 11).setCellValue(employee.getPais_familiar());
                row2.createCell((short) 12).setCellValue(employee.getTipodocumento_ppl());
                row2.createCell((short) 13).setCellValue(employee.getNumero_documento_ppl());
                row2.createCell((short) 14).setCellValue(employee.getNombre_ppl());
                row2.createCell((short) 15).setCellValue(employee.getIdrecaudo());

                row2.createCell((short) 16).setCellValue(employee.getValor());
                row2.createCell((short) 17).setCellValue(employee.getLog());
                row2.createCell((short) 18).setCellValue(employee.getCreated_at());
                row2.createCell((short) 19).setCellValue(employee.getCheck_telefonia_at());
                row2.createCell((short) 20).setCellValue(employee.getEstablecimiento());
                row2.createCell((short) 21).setCellValue(employee.getCod_pago());
                row2.createCell((short) 22).setCellValue(employee.getTelefonosms());
                row2.createCell((short) 23).setCellValue(employee.getObservacion());

                row2.createCell((short) 25).setCellValue(employee.getCiudad_ppl());

                row2.createCell((short) 27).setCellValue(employee.getNombre_pasarela());
                row2.createCell((short) 28).setCellValue(employee.getCodtransaccion());
                row2.createCell((short) 29).setCellValue(employee.getFormapago());
                row2.createCell((short) 30).setCellValue(employee.getFranquicia());
                row2.createCell((short) 31).setCellValue(employee.getDescripcion());
                row2.createCell((short) 32).setCellValue(employee.getReferencia1());
                row2.createCell((short) 33).setCellValue(employee.getFechapago());
                row2.createCell((short) 34).setCellValue(employee.getNumerorecibo());
                row2.createCell((short) 35).setCellValue(employee.getCodigo());
                row2.createCell((short) 36).setCellValue(employee.getMensajeerror());
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

    public String rutadescargas() {
        String url = "";
        String archivo = "reportewsfechas.pdf";
        url = Propiedad.getCurrentInstance().getPathUpload() + archivo;
        System.out.println("ruta guardar archivo" + Propiedad.getCurrentInstance().getPathUpload() + archivo);

        return url;

    }
}
