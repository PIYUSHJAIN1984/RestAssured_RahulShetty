import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TEE1 {

	public static void main(String[] args) throws InterruptedException {

		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\PJain15\\OneDrive - Schlumberger\\DOWNLOAD\\SLB\\Software\\Selenium_RahulShetty\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://fmp.qa.slb.com/#/home");
		driver.manage().window().maximize();
		
		Thread.sleep(20000);
		driver.findElement(By.cssSelector("span[class='k-icon k-i-close ng-star-inserted']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@name=\"fmpLogo\"]")));

		
		
		WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
		// using local storage
		LocalStorage localStorage = webStorage.getLocalStorage();
		String token = localStorage.getItem("FMP_AUTH_AUTHORIZATIONDATA");
System.out.println(token);
		
		
	}

}
