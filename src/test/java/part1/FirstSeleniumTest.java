package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstSeleniumTest {

  WebDriver driver;

  @BeforeClass
  public void setUp() throws MalformedURLException {
//    driver = new ChromeDriver();
//    driver.manage().window().maximize();
//    Capabilities cap = new DesiredCapabilities();
//    cap.setBrowserName("chrome");
    ChromeOptions options = new ChromeOptions();
    options.setCapability("browserName", "chrome");
    options.setCapability("platformName", "LINUX");
    driver = new RemoteWebDriver(new URL("http://sel-chrome:4444/wd/hub"), options);

    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
  }

  @AfterClass
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void testLoggingIntoApplication() throws InterruptedException {
    Thread.sleep(2000);
    WebElement username = driver.findElement(By.name("username"));
    username.sendKeys("Admin");

    var password = driver.findElement(By.name("password"));
    password.sendKeys("admin123");

    driver.findElement(By.tagName("button")).click();
    Thread.sleep(2000);
    String actualResult = driver.findElement(By.tagName("h6")).getText();
    String expectedResult = "Dashboard d";
    Assert.assertEquals(actualResult, expectedResult);
  }
}
