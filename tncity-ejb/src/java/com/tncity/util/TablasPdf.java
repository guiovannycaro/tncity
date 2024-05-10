/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;



import java.io.FileOutputStream;

/**
 *
 * @author guiovanny
 */
public class TablasPdf {
  
       public static void main(String[] args) throws FileNotFoundException
   {
      Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/guiovanny/Descargas/HelloWorld.pdf"));
         document.open();
         document.add(new Paragraph("A Hello World PDF document."));
         document.close();
         writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } 
   }
   
}
