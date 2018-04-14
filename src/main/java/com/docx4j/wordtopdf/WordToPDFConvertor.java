package com.docx4j.wordtopdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Level;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.utils.Log4jConfigurator;

public class WordToPDFConvertor {

	public static void main(String[] args) {
		try {
			// Initiate PDF Setting
			PdfSettings pdfSettings = new PdfSettings();

			// Convert docx file format into WordprocessingMLPackage. This contains all
			// metadata of your document.
			InputStream is = new FileInputStream(new File("K:/demo.docx"));
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(is);

			//Converting docx to PDF and putting into output folder
			OutputStream out = new FileOutputStream(new File("K:/demo.pdf"));
			PdfConversion converter = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(wordMLPackage);
			converter.output(out, pdfSettings);

			// Turning off debug which may get printed on converted PDF
			Docx4jProperties.getProperties().setProperty("docx4j.Log4j.Configurator.disabled", "true");
			Log4jConfigurator.configure();
			org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);

		} catch (Throwable e) {
			System.out.println("Exception detais: " + e.getMessage());
		}
	}

}
