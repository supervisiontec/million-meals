package com.millionmeals.report.print.service;///*

/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kavish manjitha
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class PrintService {

    @Autowired
    private DataSource dataSource;

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public Integer printInvoice(Integer jobCard) {
        try {
            JasperCompileManager.compileReportToFile("./reports/invoice.jrxml", "./reports/invoice.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File("./reports/invoice.jasper"));

            Map<String, Object> params = new HashMap<>();
            params.put("invoice_date", "2018-02-19");
            params.put("branch", 1);
            params.put("invoice_no", 1);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, getConnection());

            //export jasper report to pdf
            //String path = System.getProperty("user.dir");
            //JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/reports/kavish.pdf");
            //jasper report print default printer
            //JasperPrintManager.printReport(jasperPrint, false);
            JRExporter exporter = new JRPrintServiceExporter();

            //--- Set print properties
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(MediaSizeName.ISO_A4);

            //----------------------------------------------------
            //printRequestAttributeSet.add(new Destination(new java.net.URI("file:d:/output/report.ps")));
            //----------------------------------------------------
            PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
            printServiceAttributeSet.add(new PrinterName("EPSON TM-U220 Receipt", null));

            //--- Set print parameters
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);

            //--- Print the document
            try {
                exporter.exportReport();
            } catch (JRException e) {
                e.printStackTrace();
            }
        } catch (JRException | SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
}
