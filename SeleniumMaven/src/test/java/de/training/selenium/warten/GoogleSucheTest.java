package de.training.selenium.warten;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.util.Timeout;

public class GoogleSucheTest {

	private WebDriver driver;
	By videoLinkLokator = By.cssSelector("a[href='https://www.youtube.com/watch?v=R_hh3jAqn8M']");

	public GoogleSucheTest() {
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\seleniumtraining\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver.manage().window().maximize();
		driver.get("https://www.google.com/");
	}

	@Test
	public void googleSeiteTesten() {
		WebElement suchfeld = driver.findElement(By.name("q"));
		suchfeld.clear();
		suchfeld.sendKeys("quality-stream Introducción a la Automatización de Pruebas de Software");
		suchfeld.submit();

		// Implizites Warten
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// assertEquals("quality-stream Introducción a la Automatización de Pruebas de Software - Google Suche",driver.getTitle());

		// Explizites Warten
		// WebDriverWait explizitesWarten = new WebDriverWait(driver, 10);//Bis es Zehn Sekunden gibt, suche jede 0,5 Sekunden ob die Bedingung gefolgt wurde
		// explizitesWarten.until(ExpectedConditions.titleContains("quality-stream"));//Steht teilweise auf dem Titel von der Ergebnisseite von der Suche
		
		// assertEquals("quality-stream Introducción a la Automatización de Pruebas de Software - Google Suche",driver.getTitle());
		
		// Fluessiges Warten
		Wait<WebDriver> fluessigesWarten = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2)) //revisandoCada 2 segundos
				.ignoring(NoSuchElementException.class);	
		
		WebElement video = fluessigesWarten.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(videoLinkLokator);
			}
		}
		);	
		assertTrue(driver.findElement(videoLinkLokator).isDisplayed());		
	}

	@After
	public void tearDown() {
		// driver.quit();
	}
}
