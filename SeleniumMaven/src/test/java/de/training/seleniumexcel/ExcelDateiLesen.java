package de.training.seleniumexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDateiLesen {

	public ExcelDateiLesen() {
		// TODO Auto-generated constructor stub
	}

	public void excelLesen(String dateipfad, String blattname) {
		try {
			File datei = new File(dateipfad);
			FileInputStream eingabestrom;
			eingabestrom = new FileInputStream(datei);
			XSSFWorkbook arbeitsmappe = new XSSFWorkbook(eingabestrom);
			XSSFSheet blatt = arbeitsmappe.getSheet(blattname);
			int zeilenanzahl = blatt.getLastRowNum() - blatt.getFirstRowNum();
			for (int x = 0; x <= zeilenanzahl; x++) {
				XSSFRow reihe = blatt.getRow(x);
				for (int y = 0; y < reihe.getLastCellNum(); y++) {
					System.out.println("Naechste Zelle mit Wert: " + reihe.getCell(y).getStringCellValue() + "||");
				}
			}
		} catch (Exception ex) {
			System.out.println("Fehle: " + ex.getMessage());
		}
	}

	public String BekommeZellenWert(String dateipfad, String blattname, int zeilennummer, int zellennummer) throws IOException {
		File datei = new File(dateipfad);
		FileInputStream eingabestrom = new FileInputStream(datei);
		XSSFWorkbook arbeitsmappe = new XSSFWorkbook(eingabestrom);
		XSSFSheet blatt = arbeitsmappe.getSheet(blattname);
		XSSFRow reihe = blatt.getRow(zeilennummer);
		XSSFCell zelle = reihe.getCell(zellennummer);

		return zelle.getStringCellValue();
	}

}
