package de.training.dateiaufladen;



import static org.testng.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DateiAufladenKlasse {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\seleniumtraining\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void test() {
		File datei = new File("C:\\seleniumtraining\\DATEIEN\\DATEI_FUERS_AUFLADEN.txt");
		String pfad = datei.getAbsolutePath();// BEKOMME DEN PFAD VON DER DATEI

		driver.get("https://the-internet.herokuapp.com/upload");
		driver.findElement(By.id("file-upload")).sendKeys(pfad);// LADE DIE DATEI AUF MIT DEM PFAD, GEH ZUM PFAD UND LADE ES AUF																
		driver.findElement(By.id("file-submit")).click();
		
		String text=driver.findElement(By.id("uploaded-files")).getText();//ANDERE SEITE ABER IM GLEICHEN TEST
		assertEquals("DATEI_FUERS_AUFLADEN.txt", text);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
