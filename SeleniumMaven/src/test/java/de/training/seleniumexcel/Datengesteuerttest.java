package de.training.seleniumexcel;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Datengesteuerttest {

	private WebDriver driver;
	private ExcelDateiLesen leseDatei;
	private ExcelDateiSchreiben schreibeDatei;
	By suchfeldLokator = By.id("search_query_top");
	By suchknopfLokator = By.name("submit_search");
	By ergebnistextLokator = By.cssSelector("span.heading-counter");

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\seleniumtraining\\chromedriver.exe");
		driver = new ChromeDriver();
		leseDatei = new ExcelDateiLesen();
		schreibeDatei = new ExcelDateiSchreiben();
		driver.get("http://automationpractice.com/index.php");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		try {
			String dateipfad = "C:\\Users\\joseg\\Desktop\\Test01.xlsx";
			for (int reihe = 0; reihe < 3; reihe++) {
				driver.findElement(suchfeldLokator).clear();
				String suchText = leseDatei.BekommeZellenWert(dateipfad, "Tabelle1", reihe, 0);
				driver.findElement(suchfeldLokator).sendKeys(suchText);
				driver.findElement(suchknopfLokator).click();
				String ergebnisText = driver.findElement(ergebnistextLokator).getText();
				System.out.println("Ergebnistext: "+ergebnisText);
				
				leseDatei.excelLesen(dateipfad, "Tabelle1");
				schreibeDatei.schreibeZellenWert(dateipfad, "Tabelle1", reihe, 1, ergebnisText);
				leseDatei.excelLesen(dateipfad, "Tabelle1");
			}
		} catch (IOException ex) {
			System.out.println("Fehler: " + ex.getMessage());
		}
	}

}
