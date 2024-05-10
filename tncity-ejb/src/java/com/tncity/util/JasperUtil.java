/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.tncity.properties.Propiedad;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;

/**
 *
 * @author root
 */
public class JasperUtil {

    private File fJasper;
    private List lstPojo;
    private HashMap param;
    //Jasper
    private JRBeanCollectionDataSource ds;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    boolean isOld;

    public JasperUtil(File fJasper, List lstPojo, HashMap param, boolean isOld) {
        this.fJasper = fJasper;
        this.lstPojo = lstPojo;
        this.param = param;
        this.isOld = isOld;

        //Cargando Archivo JASPER
        cargarJasper();
    }

    private void cargarJasper() {
        try {
            InputStream in = new FileInputStream(this.fJasper);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(in);

            ds = new JRBeanCollectionDataSource(lstPojo);
            jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);

            if (!isOld) {
                jasperPrint = JasperFillManager.fillReport(jasperReport, newInstanceParams(param), ds);
            } else {
                jasperPrint = JasperFillManager.fillReport(jasperReport, param, ds);
            }
        } catch (Exception e) {
            System.out.println("FALLA, cargando .jasper->" + fJasper.getPath() + "::" + e);
            e.printStackTrace();
        }
    }

    public void compilaJRXML(File jrxml) {
        try {
            jasperReport = JasperCompileManager.compileReport(jrxml.getPath());
        } catch (Exception e) {
            System.out.println("FALLA, compilando '" + jrxml.getPath() + "'->" + e);
            e.printStackTrace();
        }
    }

    public void generarPDF(File out) {
        try {
            JasperExportManager.exportReportToPdfFile(jasperPrint, out.getPath());
        } catch (Exception e) {
            System.out.println("FALLA, generando reporte->" + out.getPath() + "-> " + e);
            e.printStackTrace();
        }
    }

    public void generarPDF(OutputStream out) {
        try {
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        } catch (Exception e) {
            System.out.println("FALLA, generando reporte -> " + e);
            e.printStackTrace();
        }
    }

    public void generarXLSX(OutputStream out) {
        try {

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarHTML(File out) {
        try {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, out.getPath());
        } catch (Exception e) {
            System.out.println("FALLA, generando reporte->" + out.getPath() + " -> " + e);
            e.printStackTrace();
        }
    }

    public void generarXLSX(File out) {
        try {
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, out.getPath());
            exporter.exportReport();
        } catch (Exception e) {
            System.out.println("FALLA, generando reporte->" + out.getPath() + " -> " + e);
            e.printStackTrace();
        }
    }

    public void generarXML(File out, boolean isEmbImg) {
        try {
            JasperExportManager.exportReportToXmlFile(jasperPrint, out.getPath(), isEmbImg);
        } catch (Exception e) {
            System.out.println("FALLA, generando reporte->" + out.getPath() + " -> " + e);
            e.printStackTrace();
        }
    }

    public void generarCSV(File out) {
        try {
            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, out.toString());
            exporter.exportReport();
        } catch (Exception e) {
            System.out.println("FALLA, generando reporte(CSV)->" + out.getPath() + " -> " + e);
            e.printStackTrace();
        }
    }

    /**
     * STATIC METHODS
     */
    /**
     *
     * @return File .jasper
     */
    private static JasperReport compileXml(String jrxmlContent) throws Exception {
        JasperReport jr = null;
        //InputStream stream = new ByteArrayInputStream(jrxmlContent.getBytes(StandardCharsets.UTF_8));
        InputStream stream = new ByteArrayInputStream(jrxmlContent.getBytes());
        jr = JasperCompileManager.compileReport(stream);
        return jr;
    }

    private static HashMap newInstanceParams(HashMap<String, Object> params) {
        HashMap<String, Object> pAux = new HashMap<>();
        Iterator<String> it = pAux.keySet().iterator();
        while (it.hasNext()) {
            String k = it.next();
            pAux.put(k, params.get(k));
        }
        return pAux;
    }

    private static JasperPrint getPrint(String jrxmlContent, List pojos, HashMap params) throws Exception {
        JasperReport jr = compileXml(jrxmlContent);
        JRDataSource ds = new JRBeanCollectionDataSource(pojos);
        //IMPORTANTE NUEVA INSTANCIA DE PARAMETROS PARA SEGUNDAS EJECUCIONES
        return JasperFillManager.fillReport(jr, newInstanceParams(params), ds);
    }

    public static void buildPdf(String jrxmlContent, File fileOutPdf, List pojos, HashMap params) {
        try {
            JasperExportManager.exportReportToPdfFile(getPrint(jrxmlContent, pojos, params), fileOutPdf.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildPdfStream(String jrxmlContent, OutputStream out, List pojos, HashMap params) {
        try {
            JasperExportManager.exportReportToPdfStream(getPrint(jrxmlContent, pojos, params), out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildXlsx(String jrxmlContent, File fileOutXls, List pojos, HashMap params) {
        try {

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, getPrint(jrxmlContent, pojos, params));
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, fileOutXls.getAbsolutePath());
            exporter.exportReport();
            /*JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(getPrint(jrxmlContent, pojos, params)));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileOutXls));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);//Set configuration as you like it!!
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildXlsxStream(String jrxmlContent, OutputStream out, List pojos, HashMap params) {
        try {

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, getPrint(jrxmlContent, pojos, params));
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void compileJasper(String jrxmlContent, File filoOutJasper) {
        try {
            JasperReport jr = compileXml(jrxmlContent);
            JRSaver.saveObject(jr, filoOutJasper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File tmpHtml() {
        File r = new File(Propiedad.getCurrentInstance().getPathTmp() + "/tncity/informes");
        Archivo.crearCarpeta(r);
        File f = new File(r.getAbsolutePath() + "/inf-" + new Date().getTime() + "-" + (int) (Math.random() * (double) 1000000000) + ".html");
        return f;
    }

    /**
     *
     * @param jrxmlContent
     * @param pojos
     * @param params
     * @return HTML content
     */
    public static String buildHtmlContent(String jrxmlContent, List pojos, HashMap params, long maxSize) {
        return buildHtmlContent(jrxmlContent, pojos, params, null, maxSize);
    }

    public static String buildHtmlContent(String jrxmlContent, List pojos, HashMap params, String protocolHostPortPath, long maxSize) {
        String html = "";
        try {

            /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
            HtmlExporter exporter = new HtmlExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, getPrint(jrxmlContent, pojos, params));
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.exportReport();
            html = baos.toString();*/
            File f = tmpHtml();
            JasperExportManager.exportReportToHtmlFile(getPrint(jrxmlContent, pojos, params), f.getAbsolutePath());
            if (f.length() > maxSize) {
                return "<div align='center'><i style='color:#0066cc;padding:3px; background:white;'>Vista previa no disponible !</i></div>";
            }

            html = new Archivo(f).getContent();
            html = processHtml(html, f, protocolHostPortPath);

            //f.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    public String generarHTML(long maxSize) {
        return generarHTML("", maxSize);
    }

    public String generarHTML(String protocolHostPortPath, long maxSize) {
        String html = "";
        try {

            /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
            HtmlExporter exporter = new HtmlExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, getPrint(jrxmlContent, pojos, params));
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.exportReport();
            html = baos.toString();*/
            File f = tmpHtml();
            JasperExportManager.exportReportToHtmlFile(jasperPrint, f.getAbsolutePath());

            if (f.length() > maxSize) {
                return "<div align='center'><i style='color:#0066cc;padding:3px; background:white;'>Vista previa no disponible !</i></div>";
            }

            html = new Archivo(f).getContent();
            html = processHtml(html, f, protocolHostPortPath);

            //f.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    private static String processHtml(String html, File fHtml, String protocolHostPortPath) {
        if (protocolHostPortPath == null || protocolHostPortPath.trim().isEmpty()) {
            return html;
        }
        String html2 = html;

        List<String> lstImgSrc = HtmlUtil.listImgSrc(html2);
        for (String src : lstImgSrc) {
            String p = fHtml.getParent() + "/" + src;
            String url = protocolHostPortPath + "/download?f=" + EncryptionUtil.encryptAES(p, Propiedad.getCurrentInstance().getEncryptAesKey());
            html2 = html2.replaceAll(src, url);
        }

        return html2;
    }

}
