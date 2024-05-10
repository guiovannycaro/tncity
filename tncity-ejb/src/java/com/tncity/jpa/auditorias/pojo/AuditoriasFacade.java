/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.auditorias.pojo;

/**
 *
 * @author guiovanny
 */
import com.tncity.informex.entity.*;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Rectangle;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.auditorias.pojos.tnpagos;
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
public class AuditoriasFacade extends AbstractFacade<tnpagos> {
    
    public static final File PLANTILLA_EXEL_GENERIC = new File(Propiedad.getCurrentInstance().getPathRecaudos() + "ReporteRecaudos.pdf");
    private static final Logger LOGGER = Logger.getLogger("mx.com.hash.newexcel.Exel");
    public static String archivo = "/opt/tncity/tncity-war/web/documentos/ReporteRecaudosTnpagos.pdf";
    
    private static String[] columns = {
        "Codigo Recaudo",
        "Valor Recaudo",
        "Fecha Recaudo",
        "Numero Recaudo",
        "Estado Recaudo",
        "Log Recaudo",
        "Nombre Benefactor",
        "Apellidos Benefactor",
        "Nombres Beneficiario",
        "TD Beneficiario",
        "PIN Beneficiario",
        "ciudad_ppl",
        "Pasarela Pago",
        "nombre_pasarela",
        "created_at",
        "check_telefonia_at",
        "establecimiento",
        "cod_pago",
        "telefonosms",
        "observacion",
        "observacion_adicional",
        "codtransaccion",
        "formapago",
        "franquicia",
        "descripcion",
        "referencia1",
        "fechapago",
        "codigo",
        "mensajeerror"
    };
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public AuditoriasFacade() {
        super(tnpagos.class);
    }
    
    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idrecaudo", "idbenefactor", "idbeneficiario",
            "valor", "estado", "log", "created_at", "check_telefonia_at",
            "establecimiento", "cod_pago", "token_wtnpagos", "idpasarela",
            "telefonosms", "ciudad", "observacion", "observacion_adicional",
            "pasareladia", "valorcomision", "numtransaccion", "valorpromocion", "promo",
            "idtransaccion", "codtransaccion", "formapago", "franquicia", "descripcion",
            "referencia1", "fechapago", "numerorecibo", "codigo", "mensajeerror", "idrecaudo",
            "codigoint"
        };
        return attrs;
    }
    
    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }
    
    @Override
    protected tnpagos parseObject(Object[] o) {
        tnpagos c = new tnpagos();
        // c.setIdciudad((Integer) o[0]);

        return c;
    }
    
    @Override
    public void create(tnpagos obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void edit(tnpagos obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
     

    
    
    
    public long countTotalRecaudoFechas(String desde, String hasta, String idbenefactor, String documento, String idpasarela, String estado) {
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
                + " FROM recaudo "
                + "  JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + "  JOIN beneficiario ON recaudo.idbeneficiario = beneficiario.idbeneficiario"
                + "  JOIN persona ON benefactor.idpersona = persona.idpersona"
                + "  JOIN ciudad ON persona.idciudad = ciudad.idciudad"
                + "  JOIN departamentoestado ON ciudad.iddepartamento = departamentoestado.iddepartamento"
                + "  JOIN pais ON departamentoestado.idpais = pais.idpais"
                + "  JOIN pasarelapago ON recaudo.idpasarela = pasarelapago.idpasarela"
                + "  JOIN transacciones ON recaudo.idrecaudo = transacciones.idrecaudo"
                + " recaudo.created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and recaudo.created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss') "
                + " and cast(persona.numdocumento as  text)  lIKE '%" + documento + "%' "
                + " and cast(recaudo.pasareladia as  text)  lIKE '%" + idpasarela + "%' "
                + " and cast(persona.nombres, as  text)  lIKE '%" + nombres + "%'"
                + " and cast(persona.apellidos as  text)  lIKE '%" + apellidos + "%'"
                + " and cast(recaudo.estado as  text)  lIKE '%" + estado + "%'"
                + " and recaudo.idpasarela = 1";
        
        System.out.println("todos informes :" + sql);
        return numFromSQL(sql, new BigInteger("0")).longValue();
        
    }
    
      
     
    
    public List<tnpagos> ListarRecaudoFechas(String desde, String hasta, String idbenefactor, String documento, String idpasarela, String estado, String attrOrder, String ascDesc) {
        
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
                + "  recaudo.idrecaudo AS codigo_recaudo,"
                + "  recaudo.valor,"
                + "  recaudo.estado,"
                + "  recaudo.log,"
                + "  recaudo.created_at,"
                + "  recaudo.check_telefonia_at,"
                + "  recaudo.establecimiento,"
                + "  recaudo.cod_pago,"
                + "  recaudo.telefonosms,"
                + "  recaudo.observacion,"
                + "  recaudo.observacion_adicional,"
                + "  recaudo.ciudad AS ciudad_ppl,"
                + "  recaudo.valorcomision,"
                + "  recaudo.numtransaccion,"
                + "  recaudo.valorpromocion,"
                + "  recaudo.promo,"
                + "  pasarelapago.nombre AS nombre_pasarela,"
                + "  persona.nombres,"
                + "  persona.apellidos,"
                + "  persona.tipodocumento AS tipo_documento,"
                + "  persona.numdocumento AS numero_documento,"
                + "  persona.num_telefono,"
                + "  persona.email,"
                + "  persona.direccion,"
                + "  ciudad.nombre AS ciudad_familiar,"
                + "  departamentoestado.nombre AS departamento_familiar,"
                + "  pais.nombre AS pais_familiar,"
                + "  beneficiario.td AS tipodocumento_ppl,"
                + "  beneficiario.pin AS numero_documento_ppl,"
                + "  beneficiario.nombres_apellidos AS nombre_ppl,"
                + "  transacciones.codtransaccion,"
                + "  transacciones.formapago,"
                + "  transacciones.franquicia,"
                + "  transacciones.descripcion,"
                + "  transacciones.referencia1,"
                + "  transacciones.fechapago,"
                + "  transacciones.numerorecibo,"
                + "  transacciones.codigo,"
                + "  transacciones.mensajeerror"
                + "  FROM recaudo "
                + "  JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + "  JOIN beneficiario ON recaudo.idbeneficiario = beneficiario.idbeneficiario"
                + "  JOIN persona ON benefactor.idpersona = persona.idpersona"
                + "  JOIN ciudad ON persona.idciudad = ciudad.idciudad"
                + "  JOIN departamentoestado ON ciudad.iddepartamento = departamentoestado.iddepartamento"
                + "  JOIN pais ON departamentoestado.idpais = pais.idpais"
                + "  JOIN pasarelapago ON recaudo.idpasarela = pasarelapago.idpasarela"
                + "  JOIN transacciones ON recaudo.idrecaudo = transacciones.idrecaudo"
                + " recaudo.created_at >= to_timestamp('" + desde + "', 'yyyy-mm-dd hh24:mi:ss')"
                + " and recaudo.created_at <= to_timestamp('" + hasta + "', 'yyyy-mm-dd hh24:mi:ss') "
                + " and cast(persona.numdocumento as  text)  lIKE '%" + documento + "%' "
                + " and cast(recaudo.pasareladia as  text)  lIKE '%" + idpasarela + "%' "
                + " and cast(persona.nombres, as  text)  lIKE '%" + nombres + "%'"
                + " and cast(persona.apellidos as  text)  lIKE '%" + apellidos + "%'"
                + " and cast(recaudo.estado as  text)  lIKE '%" + estado + "%'"
                + " and recaudo.idpasarela = 1";
        
        System.out.println("toda informes: " + sql);
        
        List<tnpagos> lstR = new ArrayList<>();
        
        List<Object[]> lst = findNativeGeneric(sql);
        
        for (Object[] o : lst) {
            tnpagos r = new tnpagos();
            r.setCodigo_recaudo(o[0].toString());
            r.setValor(o[1].toString());
            r.setEstado(o[2].toString());
            r.setLog(o[3].toString());
            r.setCreated_at(o[4].toString());
            r.setCheck_telefonia_at(o[5].toString());
            
            r.setEstablecimiento(o[6].toString());
            r.setCod_pago(o[7].toString());
            r.setTelefonosms(o[8].toString());
            r.setObservacion(o[9].toString());
            r.setObservacion_adicional(o[10].toString());
            
            r.setCiudad(o[11].toString());
            r.setValorcomision(o[12].toString());
            r.setNumtransaccion(o[13].toString());
            r.setValorpromocion(o[14].toString());
            r.setPromo(o[15].toString());
            r.setNombre_pasarela(o[16].toString());
            r.setNombres(o[17].toString());
            r.setApellidos(o[18].toString());
            
            r.setTipo_documento(o[19].toString());
            r.setNumero_documento(o[20].toString());
            
            r.setNum_telefono(o[21].toString());
            r.setEmail(o[22].toString());
            r.setDireccion(o[23].toString());
            
            r.setCiudad_familiar(o[24].toString());
            r.setDepartamento_familiar(o[25].toString());
            
            r.setPais_familiar(o[26].toString());
            r.setTipodocumento_ppl(o[27].toString());
            r.setNumero_documento_ppl(o[28].toString());
            
            r.setNombre_ppl(o[29].toString());
            r.setCodtransaccion(o[30].toString());
            r.setFormapago(o[31].toString());
            
            r.setFranquicia(o[32].toString());
            r.setDescripcion(o[33].toString());
            r.setReferencia1(o[34].toString());
            r.setFechapago(o[35].toString());
            r.setNumerorecibo(o[36].toString());
            r.setCodigo(o[37].toString());
            r.setMensajeerror(o[38].toString());
            
            lstR.add(r);
            
        }
        return lstR;
        
    }
    
    public long countTotalRecargasCompleto() {
        
        String sql = "SELECT "
                + " COUNT(*)"
                + " FROM recaudo ";
        
        System.out.println("todos recaudos :" + sql);
        return numFromSQL(sql, new BigInteger("0")).longValue();
        
    }
    
    public void generarExcel(List<tnpagos> lstRecaudosTnPagos, String desde, String hasta) throws IOException {
        
        String url = "";
        System.out.println("path recaudo " + Propiedad.getCurrentInstance().getPathInformes());
        String rutaArchivo = "";
        Date fIni = null;
        Date fFin = null;
        try {
            DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
            fIni = fecha.parse(desde);
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "ReporteRecaudosFechasTnpagos.xls";
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
            for (tnpagos employee : lstRecaudosTnPagos) {
                
                HSSFRow row2 = sheet.createRow(rowNum++);
                
                row2.createCell((short) 0).setCellValue(employee.getCodigo_recaudo().toString()); // A2 
                row2.createCell((short) 1).setCellValue(employee.getValor().toString()); // B2
                row2.createCell((short) 2).setCellValue(employee.getEstado()); // C2
                row2.createCell((short) 3).setCellValue(employee.getLog());// D2
                row2.createCell((short) 4).setCellValue(employee.getCreated_at());// E2
                row2.createCell((short) 5).setCellValue(employee.getCheck_telefonia_at());// F2
                row2.createCell((short) 6).setCellValue(employee.getEstablecimiento());// G2
                row2.createCell((short) 7).setCellValue(employee.getCod_pago());// H2
                row2.createCell((short) 8).setCellValue(employee.getTelefonosms());// I2
                row2.createCell((short) 9).setCellValue(employee.getObservacion());// J2
                row2.createCell((short) 10).setCellValue(employee.getObservacion_adicional());// K2
                row2.createCell((short) 11).setCellValue(employee.getCiudad());// L2
                row2.createCell((short) 12).setCellValue(employee.getValorcomision());// M2
                row2.createCell((short) 13).setCellValue(employee.getNumtransaccion());// N2
                row2.createCell((short) 14).setCellValue(employee.getValorpromocion());// O2
                row2.createCell((short) 15).setCellValue(employee.getPromo());// P2
                
                row2.createCell((short) 16).setCellValue(employee.getNombre_pasarela());// Q2
                row2.createCell((short) 17).setCellValue(employee.getNombres());// R2
                row2.createCell((short) 18).setCellValue(employee.getApellidos());// S2
                row2.createCell((short) 19).setCellValue(employee.getTipo_documento());// T2
                row2.createCell((short) 20).setCellValue(employee.getNumero_documento());// U2
                row2.createCell((short) 21).setCellValue(employee.getNum_telefono());// V2
                row2.createCell((short) 22).setCellValue(employee.getEmail());// W2
                row2.createCell((short) 23).setCellValue(employee.getDireccion());// X2
                row2.createCell((short) 24).setCellValue(employee.getCiudad_familiar());// Y2
                row2.createCell((short) 25).setCellValue(employee.getDepartamento_familiar());// Z2
                row2.createCell((short) 26).setCellValue(employee.getPais_familiar());// AA2
                row2.createCell((short) 27).setCellValue(employee.getTipodocumento_ppl());// AB2
                row2.createCell((short) 28).setCellValue(employee.getNumero_documento_ppl());// AC2
                row2.createCell((short) 29).setCellValue(employee.getNombre_ppl());// AC2
       
                row2.createCell((short) 30).setCellValue(employee.getCodtransaccion());// AI2
                row2.createCell((short) 31).setCellValue(employee.getFormapago());// AJ2
                row2.createCell((short) 32).setCellValue(employee.getFranquicia());// AK2
                
                
                row2.createCell((short) 33).setCellValue(employee.getDescripcion());// AH2
                row2.createCell((short) 34).setCellValue(employee.getReferencia1());// AI2
                row2.createCell((short) 35).setCellValue(employee.getFechapago());// AJ2
                row2.createCell((short) 36).setCellValue(employee.getNumerorecibo());// AK2
                row2.createCell((short) 37).setCellValue(employee.getCodigo());// AJ2
                row2.createCell((short) 38).setCellValue(employee.getMensajeerror());// AK
                
                
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
    
    public String generarPdf(List<tnpagos> lstRecaudosTnPagos) {
        String url = "";
        url = crearPDF(lstRecaudosTnPagos);
        return url;
    }
    
    public String crearPDF(List<tnpagos> lstRecaudosTnPagos) {
        
        String ruta = "";
        String rutasis = "";
        String archivo = "ReporteRecaudosFechasTnpagos.pdf";
        
        System.out.println("ruta guardar archivo" + Propiedad.getCurrentInstance().getPathInformes() + archivo);
        
        String rutaArchivo = "";
        
        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "ReporteRecaudosFechasTnpagos.pdf";
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
            Logger.getLogger(AuditoriasFacade.class.getName()).log(Level.SEVERE, null, ex);
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
            for (tnpagos employee : lstRecaudosTnPagos) {
                
                table.getDefaultCell().setBorder(1);
                table.getDefaultCell().setPadding(3);
                table.addCell("Cod Recaudo");
                table.addCell(employee.getCod_pago().toString());
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
                table.addCell(employee.getNumero_documento_ppl());
                table.addCell("Ciudad Familiar");
                table.addCell(employee.getCiudad_familiar());
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
                table.addCell(employee.getObservacion_adicional());
                table.addCell("Ciudad PPL");
                table.addCell(employee.getCiudad());
         
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
    
    
  //recaudos completo
    
    public long countTotalInformeCompleto() {
        String sql = "SELECT "
                + " COUNT(*)"
                + " FROM recaudos";

        System.out.println("todos los recaudos  :" + sql);
        return numFromSQL(sql, new BigInteger("0")).longValue();

    }
      
     public List<tnpagos> lstRecaudosTnPagosCompleto( String estado, String attrOrder, String ascDesc) {

        
        String sql = "SELECT "
                + "  recaudo.idrecaudo AS codigo_recaudo,"
                + "  recaudo.valor,"
                + "  recaudo.estado,"
                + "  recaudo.log,"
                + "  recaudo.created_at,"
                + "  recaudo.check_telefonia_at,"
                + "  recaudo.establecimiento,"
                + "  recaudo.cod_pago,"
                + "  recaudo.telefonosms,"
                + "  recaudo.observacion,"
                + "  recaudo.observacion_adicional,"
                + "  recaudo.ciudad AS ciudad_ppl,"
                + "  recaudo.valorcomision,"
                + "  recaudo.numtransaccion,"
                + "  recaudo.valorpromocion,"
                + "  recaudo.promo,"
                + "  pasarelapago.nombre AS nombre_pasarela,"
                + "  persona.nombres,"
                + "  persona.apellidos,"
                + "  persona.tipodocumento AS tipo_documento,"
                + "  persona.numdocumento AS numero_documento,"
                + "  persona.num_telefono,"
                + "  persona.email,"
                + "  persona.direccion,"
                + "  ciudad.nombre AS ciudad_familiar,"
                + "  departamentoestado.nombre AS departamento_familiar,"
                + "  pais.nombre AS pais_familiar,"
                + "  beneficiario.td AS tipodocumento_ppl,"
                + "  beneficiario.pin AS numero_documento_ppl,"
                + "  beneficiario.nombres_apellidos AS nombre_ppl,"
                + "  transacciones.codtransaccion,"
                + "  transacciones.formapago,"
                + "  transacciones.franquicia,"
                + "  transacciones.descripcion,"
                + "  transacciones.referencia1,"
                + "  transacciones.fechapago,"
                + "  transacciones.numerorecibo,"
                + "  transacciones.codigo,"
                + "  transacciones.mensajeerror"
                + "  FROM recaudo "
                + "  JOIN benefactor ON recaudo.idbenefactor = benefactor.idbenefactor"
                + "  JOIN beneficiario ON recaudo.idbeneficiario = beneficiario.idbeneficiario"
                + "  JOIN persona ON benefactor.idpersona = persona.idpersona"
                + "  JOIN ciudad ON persona.idciudad = ciudad.idciudad"
                + "  JOIN departamentoestado ON ciudad.iddepartamento = departamentoestado.iddepartamento"
                + "  JOIN pais ON departamentoestado.idpais = pais.idpais"
                + "  JOIN pasarelapago ON recaudo.idpasarela = pasarelapago.idpasarela"
                + "  JOIN transacciones ON recaudo.idrecaudo = transacciones.idrecaudo"
                + " where cast(recaudo.estado as  text)  lIKE '%" + estado + "%'"
                + " and recaudo.idpasarela = 1";
        
        System.out.println("toda informes: " + sql);
        
        List<tnpagos> lstR = new ArrayList<>();
        
        List<Object[]> lst = findNativeGeneric(sql);
        
        for (Object[] o : lst) {
            tnpagos r = new tnpagos();
            r.setCodigo_recaudo(o[0].toString());
            r.setValor(o[1].toString());
            r.setEstado(o[2].toString());
            r.setLog(o[3].toString());
            r.setCreated_at(o[4].toString());
            r.setCheck_telefonia_at(o[5].toString());
            
            r.setEstablecimiento(o[6].toString());
            r.setCod_pago(o[7].toString());
            r.setTelefonosms(o[8].toString());
            r.setObservacion(o[9].toString());
            r.setObservacion_adicional(o[10].toString());
            
            r.setCiudad(o[11].toString());
            r.setValorcomision(o[12].toString());
            r.setNumtransaccion(o[13].toString());
            r.setValorpromocion(o[14].toString());
            r.setPromo(o[15].toString());
            r.setNombre_pasarela(o[16].toString());
            r.setNombres(o[17].toString());
            r.setApellidos(o[18].toString());
            
            r.setTipo_documento(o[19].toString());
            r.setNumero_documento(o[20].toString());
            
            r.setNum_telefono(o[21].toString());
            r.setEmail(o[22].toString());
            r.setDireccion(o[23].toString());
            
            r.setCiudad_familiar(o[24].toString());
            r.setDepartamento_familiar(o[25].toString());
            
            r.setPais_familiar(o[26].toString());
            r.setTipodocumento_ppl(o[27].toString());
            r.setNumero_documento_ppl(o[28].toString());
            
            r.setNombre_ppl(o[29].toString());
            r.setCodtransaccion(o[30].toString());
            r.setFormapago(o[31].toString());
            
            r.setFranquicia(o[32].toString());
            r.setDescripcion(o[33].toString());
            r.setReferencia1(o[34].toString());
            r.setFechapago(o[35].toString());
            r.setNumerorecibo(o[36].toString());
            r.setCodigo(o[37].toString());
            r.setMensajeerror(o[38].toString());
            
            lstR.add(r);
            
        }
        return lstR;
        
    }
     
      public void generarExcelCompleto(List<tnpagos> lstRecaudosTnPagosCompleto) throws IOException {
           String url = "";

        System.out.println("path informes " + Propiedad.getCurrentInstance().getPathRecaudos());

        String rutaArchivo = "";
        rutaArchivo = Propiedad.getCurrentInstance().getPathRecaudos()+ "reporteTnPagosCompleto.xls";
        
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
               for (tnpagos employee : lstRecaudosTnPagosCompleto) {
                
                HSSFRow row2 = sheet.createRow(rowNum++);
                
                row2.createCell((short) 0).setCellValue(employee.getCodigo_recaudo().toString()); // A2 
                row2.createCell((short) 1).setCellValue(employee.getValor().toString()); // B2
                row2.createCell((short) 2).setCellValue(employee.getEstado()); // C2
                row2.createCell((short) 3).setCellValue(employee.getLog());// D2
                row2.createCell((short) 4).setCellValue(employee.getCreated_at());// E2
                row2.createCell((short) 5).setCellValue(employee.getCheck_telefonia_at());// F2
                row2.createCell((short) 6).setCellValue(employee.getEstablecimiento());// G2
                row2.createCell((short) 7).setCellValue(employee.getCod_pago());// H2
                row2.createCell((short) 8).setCellValue(employee.getTelefonosms());// I2
                row2.createCell((short) 9).setCellValue(employee.getObservacion());// J2
                row2.createCell((short) 10).setCellValue(employee.getObservacion_adicional());// K2
                row2.createCell((short) 11).setCellValue(employee.getCiudad());// L2
                row2.createCell((short) 12).setCellValue(employee.getValorcomision());// M2
                row2.createCell((short) 13).setCellValue(employee.getNumtransaccion());// N2
                row2.createCell((short) 14).setCellValue(employee.getValorpromocion());// O2
                row2.createCell((short) 15).setCellValue(employee.getPromo());// P2
                
                row2.createCell((short) 16).setCellValue(employee.getNombre_pasarela());// Q2
                row2.createCell((short) 17).setCellValue(employee.getNombres());// R2
                row2.createCell((short) 18).setCellValue(employee.getApellidos());// S2
                row2.createCell((short) 19).setCellValue(employee.getTipo_documento());// T2
                row2.createCell((short) 20).setCellValue(employee.getNumero_documento());// U2
                row2.createCell((short) 21).setCellValue(employee.getNum_telefono());// V2
                row2.createCell((short) 22).setCellValue(employee.getEmail());// W2
                row2.createCell((short) 23).setCellValue(employee.getDireccion());// X2
                row2.createCell((short) 24).setCellValue(employee.getCiudad_familiar());// Y2
                row2.createCell((short) 25).setCellValue(employee.getDepartamento_familiar());// Z2
                row2.createCell((short) 26).setCellValue(employee.getPais_familiar());// AA2
                row2.createCell((short) 27).setCellValue(employee.getTipodocumento_ppl());// AB2
                row2.createCell((short) 28).setCellValue(employee.getNumero_documento_ppl());// AC2
                row2.createCell((short) 29).setCellValue(employee.getNombre_ppl());// AC2
       
                row2.createCell((short) 30).setCellValue(employee.getCodtransaccion());// AI2
                row2.createCell((short) 31).setCellValue(employee.getFormapago());// AJ2
                row2.createCell((short) 32).setCellValue(employee.getFranquicia());// AK2
                
                
                row2.createCell((short) 33).setCellValue(employee.getDescripcion());// AH2
                row2.createCell((short) 34).setCellValue(employee.getReferencia1());// AI2
                row2.createCell((short) 35).setCellValue(employee.getFechapago());// AJ2
                row2.createCell((short) 36).setCellValue(employee.getNumerorecibo());// AK2
                row2.createCell((short) 37).setCellValue(employee.getCodigo());// AJ2
                row2.createCell((short) 38).setCellValue(employee.getMensajeerror());// AK
                
                
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
      
       public String generarPdfCompleto(List<tnpagos> lstRecaudosTnPagosCompletos) {
        String url = "";

        url = crearPDFCompleto(lstRecaudosTnPagosCompletos);

        return url;
    }
       
     public String crearPDFCompleto(List<tnpagos> lstRecaudosTnPagosCompletos) {

        String url = "";
//Declaramos un documento como un objecto Document. 
        String archivo = "reportecompletotnpagos.pdf";

        String rutaArchivo = "";

        rutaArchivo = Propiedad.getCurrentInstance().getPathInformes() + "reportecompletotnpagos.pdf";
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
            for (tnpagos employee : lstRecaudosTnPagosCompletos) {

                table.getDefaultCell().setBorder(1);
                table.getDefaultCell().setPadding(3);
                table.addCell("Id Recaudo");
                table.addCell(employee.getCodigo_recaudo().toString());
                table.addCell("Nombres");
                table.addCell(employee.getNombres());

                table.addCell("Apellidos");
                table.addCell(employee.getApellidos());
                table.addCell("Tipo Documento");
                table.addCell(employee.getTipo_documento());

                table.addCell("Numero De Documento");
                table.addCell(employee.getNumero_documento());
                table.addCell("NUmero De Telfono");
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
                table.addCell(employee.getObservacion_adicional());
                table.addCell("Ciudad PPL");
                table.addCell(employee.getCiudad_familiar());
        
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
        return url;
    }   
       
     
    public String rutadescargas() {
        String url = "";
        String archivo = "ReporteRecaudosFechasTnpagos.pdf";
        url = Propiedad.getCurrentInstance().getPathInformes() + archivo;
        System.out.println("ruta guardar archivo" + Propiedad.getCurrentInstance().getPathInformes());
        
        return url;
        
    }

    public long countTotalRecaudos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
