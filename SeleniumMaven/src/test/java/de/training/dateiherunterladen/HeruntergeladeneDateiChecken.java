package de.training.dateiherunterladen;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class HeruntergeladeneDateiChecken {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\seleniumtraining\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/download");
	}

	@Test
	public void heruntergeladeneDateiChecken() throws MalformedURLException, IOException {

		String link = driver.findElement(By.cssSelector("div.example>a")).getAttribute("href");

		HttpURLConnection httpVerbindung = (HttpURLConnection) (new URL(link).openConnection());

		httpVerbindung.setRequestMethod("HEAD");
		httpVerbindung.connect();

		String inhaltstyp = httpVerbindung.getContentType();
		int inhaltslaenge = httpVerbindung.getContentLength();

		System.out.println("Inhaltstyp: " + inhaltstyp);
		System.out.println("Inhaltslaenge: " + inhaltslaenge);

		assertEquals(inhaltstyp, "application/octet-stream"); // INHALTSTYP SOLL APPLICATION/OCTET-STREAM SEIN WEIL ES
																// EINE DATEI WAERE DIE IRGENDWO GEOEFFNET WAERE UND
																// NICHT MIT EINER SPEZIFISCHEN ANWENDUNG VERBUNDEN
																// WAERE
		assertNotEquals(inhaltslaenge, 0); // INHALTSLAENGE SOLL GROESSER ALS 0 SEIN, ES SOLL NICHT 0 SEIN

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
