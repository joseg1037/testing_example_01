package de.training.seleniumbildschirmaufnahme;

import static org.junit.Assert.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Bildschirmaufnametest {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\seleniumtraining\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	public String datumBekommen() {// DAS DATUM ZURUECKKUEHREN FUR DIE BILDSCHIRMAUFNAHME
		DateFormat datumsformat = new SimpleDateFormat("dd.MM.yyyy");
		Date datum = new Date();
		return datumsformat.format(datum);
	}

	public String uhrzeitBekommen() {// OPTIONAL, DIE UHZREIT ZURUECKKUEHREN FUR DIE BILDSCHIRMAUFNAHME
		DateFormat uhrzeitformat = new SimpleDateFormat("HH.mm.ss");
		Date datum = new Date();
		return uhrzeitformat.format(datum);
	}

	@Rule
	public TestRule beobachter = new TestWatcher() {
		@Override
		protected void failed(Throwable throwable, Description beschreibung) {
			File bildschirmaufnahme = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(bildschirmaufnahme, new File("FEHLER_" + beschreibung.getMethodName() + "_"
						+ datumBekommen() + "-" + uhrzeitBekommen() + ".png"));
			} catch (Exception ex) {
				System.out.println("FEHLER: " + ex.getMessage());
			}
		}

		@Override // WIRD NACH FAILED DUERCHGEFUEHRT
		protected void finished(Description beschreibung) {
			File bildschirmaufnahme = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(bildschirmaufnahme, new File("ERFOLG_" + beschreibung.getMethodName() + "_"
						+ datumBekommen() + "-" + uhrzeitBekommen() + ".png"));
			} catch (Exception ex) {
				System.out.println("FEHLER: " + ex.getMessage());
			}
			driver.quit();
		}
	};

	@Test
	public void googlesucheTest() {
		driver.get("https://www.google.com/");
		WebElement suchfeld = driver.findElement(By.name("q"));
		suchfeld.clear();
		suchfeld.sendKeys("quality-stream Introducción a la Automatización de Pruebas de Software");
		suchfeld.submit();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// assertEquals("Poner justo este texto en vez de nombre(título) de página ocasionará un error", driver.getTitle()); DAMIT EIN FEHLER VERURSACHT WIRD

		assertEquals("quality-stream Introducción a la Automatización de Pruebas de Software - Google Suche", driver.getTitle()); // DAMIT ALLES IN ORDNUNG LAEUFT
	}

}
