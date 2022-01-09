package de.training.dateiherunterladen;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;

public class DateiHerunterladenKlasse {

	private WebDriver driver;
	private String downloadPfad = "C:\\seleniumtraining\\DATEIEN";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\seleniumtraining\\chromedriver.exe");

		HashMap<String, Object> chromeVorlieben = new HashMap<String, Object>();
		chromeVorlieben.put("profile.default_content_settings.popups", 0); // FENSTER FUERS HERUNTERLADEN BLOCKIEREN, ES
																			// WIRD SPAETER ANGELEITET WO ES
																			// HERUNTERGELADEN WIRD
		chromeVorlieben.put("download.default_directory", downloadPfad); // HIER HERUNTERLADEN, EIN ORDNEN IN C,
																			// SELENIUMTRAINING, DEN DATEIEN HEISST

		ChromeOptions chromeOptionen = new ChromeOptions();
		chromeOptionen.setExperimentalOption("prefs", chromeVorlieben);

		driver = new ChromeDriver(chromeOptionen);
	}

	@Test
	public void dateiHerunterladen() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/download");
		driver.findElement(By.cssSelector("div.example>a")).click();
		Thread.sleep(5000); // 5 SEKUNDEN WARTEN, IN DER HOFFNUNG DAS DIE DATEI IN 5 SEKUNDEN
							// HERUNTERGELADEN WURDE

		File ordner = new File(downloadPfad);
		File[] dateiliste = ordner.listFiles();

		assertTrue(dateiliste.length > 0, "Datei wurde nicht korrekt heruntergeladen");

	}

	@AfterClass
	public void afterClass() {

	}

}
