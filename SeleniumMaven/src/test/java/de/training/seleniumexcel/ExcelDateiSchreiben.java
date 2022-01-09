package de.training.seleniumexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDateiSchreiben {

	public ExcelDateiSchreiben() {
		// TODO Auto-generated constructor stub
	}

	public void excelSchreiben(String dateipfad, String blattname, String[] datenFuersSchreiben) throws IOException {
		File datei = new File(dateipfad);
		FileInputStream eingabestrom = new FileInputStream(datei);
		XSSFWorkbook arbeitsmappe = new XSSFWorkbook(eingabestrom);
		XSSFSheet blatt = arbeitsmappe.getSheet(blattname);
		int zeilenanzahl = blatt.getLastRowNum() - blatt.getFirstRowNum();
		XSSFRow reihe = blatt.getRow(0);
		XSSFRow neueReihe = blatt.createRow(zeilenanzahl + 1);
		for (int x = 0; x < reihe.getLastCellNum(); x++) {
			XSSFCell zelle = neueReihe.createCell(x);
			zelle.setCellValue(datenFuersSchreiben[x]);
		}
		eingabestrom.close();
		FileOutputStream ausgabestrom = new FileOutputStream(datei);
		arbeitsmappe.write(ausgabestrom);
		ausgabestrom.close();
	}

	public void schreibeZellenWert(String dateipfad, String blattname, int zeilennumer, int zellennummer, String ergebnisText) throws IOException {
		File datei = new File(dateipfad);
		FileInputStream eingabestrom = new FileInputStream(datei);
		XSSFWorkbook arbeitsmappe = new XSSFWorkbook(eingabestrom);
		XSSFSheet blatt = arbeitsmappe.getSheet(blattname);
		XSSFRow reihe = blatt.getRow(zeilennumer);
		XSSFCell ersteZelle = reihe.getCell(zellennummer - 1);
		System.out.println("Wert erster gecheckten Zelle ist: " + ersteZelle.getStringCellValue());

		XSSFCell naechsteZelle = reihe.createCell(zellennummer);
		naechsteZelle.setCellValue(ergebnisText);
		System.out.println("Naechster Wert von Zelle daneben ist: " + naechsteZelle.getStringCellValue() + "\n");

		eingabestrom.close();
		FileOutputStream ausgabestrom = new FileOutputStream(datei);
		arbeitsmappe.write(ausgabestrom);
		ausgabestrom.close();
		
	}

}
