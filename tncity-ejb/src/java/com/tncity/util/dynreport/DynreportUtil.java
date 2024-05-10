/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util.dynreport;


import com.tncity.util.Archivo;
import com.tncity.util.Cadena;
import com.tncity.util.JasperUtil;
import com.tncity.util.VelocityUtil;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author inmoticamaster
 */
public class DynreportUtil {

    private ModelDynreport model;
    private long maxSizeHtmlPreview = 2 * 1000 * 1024; //2M

    public DynreportUtil(ModelDynreport model) {
        this.model = model;
    }

    private String generateJrxmlFromTemplate(File template, ModelDynreport m) {
        HashMap<String, ModelDynreport> map = new HashMap<>();
        map.put("model", m);
        String xml = new Archivo(template).getContent();
        //Drop attribute uuid
        xml = Cadena.reemplazarPatronAll("uuid=\"[a-z|0-9|-]*\"", xml, "");
        String newXml = VelocityUtil.render(xml, map);
        return newXml;
    }

    private List<DataDynreport> cloneData(ModelDynreport model) {
        List<DataDynreport> data = new ArrayList<>();
        for (DataDynreport dr : model.getData()) {
            DataDynreport dNew = new DataDynreport();
            for (Object val : dr.getValues()) {
                if (val instanceof JRDataSource) {
                    if (val instanceof JRBeanCollectionDataSource) {
                        JRBeanCollectionDataSource bcOri = (JRBeanCollectionDataSource) val;
                        JRBeanCollectionDataSource bc = new JRBeanCollectionDataSource(bcOri.getData());
                        dNew.getValues().add(bc);
                    }
                    if (val instanceof JREmptyDataSource) {
                        JREmptyDataSource e = new JREmptyDataSource();
                        dNew.getValues().add(e);
                    }

                } else {
                    dNew.getValues().add(val);
                }
            }
            data.add(dNew);
        }
        return data;
    }

    private boolean haySubreportes() {
        for (ColumnDynreport column : model.getColumns()) {
            if (column.getDataType() == ColumnDynreport.DATATYPE_MODEL) {
                return true;
            }
        }
        return false;
    }

    private String buildJasperReport() {
        model.fixRender();

        compileSubreportsColumns();
        if (model.getData().isEmpty()) {
            model.addData(emptyData());
            //model.addData(new JREmptyDataSource());
            model.setTotalReg(0);
        } else {
            model.setTotalReg(model.getData().size());
        }

        //IMPORTANTE CLONAR: (BUG jasper subreport JRBeanCollectionDataSource, new Instance)
        if (haySubreportes()) {
            model.setData(cloneData(model));
        }

        return generateJrxmlFromTemplate(model.getFileJrxmlTemplate(), model);
    }

    private void endBuildReport() {
        dropTmpFileJasperSubreport();
    }

    public boolean isJasperTemplate() {
        String ext = Archivo.getExtension(model.getFileJrxmlTemplate().getName());
        return ext.trim().toUpperCase().equals("JASPER");
    }

    public void buildJasperReportPdf(File fileOutPdf) {
        if (!isJasperTemplate()) {
            buildJasperReportPdfFromTemplate(fileOutPdf);
        } else {
            buildJasperReportPdfFromJasper(fileOutPdf, model.getFileJrxmlTemplate());
        }
    }

    public void buildJasperReportPdfStream(OutputStream out) {
        if (!isJasperTemplate()) {
            buildJasperReportPdfStreamFromTemplate(out);
        } else {
            buildJasperReportPdfStreamFromJasper(out, model.getFileJrxmlTemplate());
        }
    }

    private void buildJasperReportPdfFromTemplate(File fileOutPdf) {
        String newXml = buildJasperReport();
        JasperUtil.buildPdf(newXml, fileOutPdf, model.getData(), model.getParams());
        endBuildReport();
    }

    private void buildJasperReportPdfStreamFromTemplate(OutputStream out) {
        String newXml = buildJasperReport();
        JasperUtil.buildPdfStream(newXml, out, model.getData(), model.getParams());
        endBuildReport();
    }

    private void buildJasperReportPdfFromJasper(File fileOutPdf, File fileJasper) {
        new JasperUtil(fileJasper, model.getData(), model.getParams(), false).generarPDF(fileOutPdf);
    }

    private void buildJasperReportPdfStreamFromJasper(OutputStream out, File fileJasper) {
        new JasperUtil(fileJasper, model.getData(), model.getParams(), false).generarPDF(out);
    }

    public void buildJasperReportXlsx(File fileOutxls) {
        if (!isJasperTemplate()) {
            buildJasperReportXlsxFromTemplate(fileOutxls);
        } else {
            buildJasperReportXlsxFromJasper(fileOutxls, model.getFileJrxmlTemplate());
        }
    }

    public void buildJasperReportXlsxStream(OutputStream out) {
        if (!isJasperTemplate()) {
            buildJasperReportXlsxStreamFromTemplate(out);
        } else {
            buildJasperReportXlsxStream(out, model.getFileJrxmlTemplate());
        }
    }

    private void buildJasperReportXlsxFromJasper(File fileOutxls, File fileJasper) {
        new JasperUtil(fileJasper, model.getData(), model.getParams(), false).generarXLSX(fileOutxls);
    }

    private void buildJasperReportXlsxStream(OutputStream out, File fileJasper) {
        new JasperUtil(fileJasper, model.getData(), model.getParams(), false).generarXLSX(out);
    }

    private void buildJasperReportXlsxFromTemplate(File fileOutxls) {
        String newXml = buildJasperReport();
        JasperUtil.buildXlsx(newXml, fileOutxls, model.getData(), model.getParams());
        endBuildReport();
    }

    private void buildJasperReportXlsxStreamFromTemplate(OutputStream out) {
        String newXml = buildJasperReport();
        JasperUtil.buildXlsxStream(newXml, out, model.getData(), model.getParams());
        endBuildReport();
    }

    public String buildHtmlContent(String protocolHostPortPath) {
        if (!isJasperTemplate()) {
            return buildHtmlContentFromTemplate(protocolHostPortPath);
        } else {
            return buildHtmlContentFromJasper(model.getFileJrxmlTemplate(), protocolHostPortPath);
        }
    }

    private String buildHtmlContentFromTemplate(String protocolHostPortPath) {
        String newXml = buildJasperReport();
        String html = JasperUtil.buildHtmlContent(newXml, model.getData(), model.getParams(), protocolHostPortPath, maxSizeHtmlPreview);
        endBuildReport();
        return html;
    }

    private String buildHtmlContentFromJasper(File fileJasper, String protocolHostPortPath) {
        return new JasperUtil(fileJasper, model.getData(), model.getParams(), false).generarHTML(protocolHostPortPath, maxSizeHtmlPreview);
    }

    private Object[] emptyData() {
        Object[] arr = new Object[model.getColumns().size()];
        int i = 0;
        for (ColumnDynreport c : model.getColumns()) {
            arr[i] = "";

            if (c.getDataType() == ColumnDynreport.DATATYPE_MONEY
                    || c.getDataType() == ColumnDynreport.DATATYPE_NUMERIC) {
                arr[i] = 0;
            }
            if (c.getDataType() == ColumnDynreport.DATATYPE_MODEL) {
                arr[i] = new JREmptyDataSource();
            }
            i++;
        }
        return arr;
    }

    private void dropTmpFileJasperSubreport() {
        for (ColumnDynreport c : model.getColumns()) {
            if (c.getDataType() == ColumnDynreport.DATATYPE_MODEL) {
                c.getSubreportModel().getFileJasperCompiled().delete();
            }
        }
    }

    private void compileSubreportsColumns() {
        for (ColumnDynreport c : model.getColumns()) {
            if (c.getDataType() == ColumnDynreport.DATATYPE_MODEL) {
                String tmpPath = c.getSubreportModel().getFileJrxmlTemplate().getParent() + "/tmp-subreport-" + c.getId() + "-" + new Date().getTime() + "-" + (int) (Math.random() * 10000000) + ".jasper";
                File f = new File(tmpPath);
                c.getSubreportModel().setFileJasperCompiled(f);

                c.getSubreportModel().fixRender();

                String xml = generateJrxmlFromTemplate(c.getSubreportModel().getFileJrxmlTemplate(), c.getSubreportModel());
                JasperUtil.compileJasper(xml, c.getSubreportModel().getFileJasperCompiled());
            }
        }
    }

}
