package Validation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoodreadsAutomationTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.goodreads.com/ap/signin?language=en_US&openid.assoc_handle=amzn_goodreads_web_na&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.goodreads.com%2Fap-handler%2Fsign-in&siteState=57cab89497f2716673ed2ebe80d3b376");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void Login() {
        driver.findElement(By.id("ap_email")).sendKeys("yashpardeshi676@gmail.com");
        driver.findElement(By.id("ap_password")).sendKeys("Kp@1306");
        driver.findElement(By.id("signInSubmit")).click();
        String actualTitle = driver.getTitle();
        String expectedTitle = "Recent updates | Goodreads";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test(priority = 2)
    public void Search() {
        driver.findElement(By.name("q")).sendKeys("ice and fire");
        driver.findElement(By.xpath("//div[contains(@class,'gr-book__title gr-book__title') and text()='A Game of Thrones (A Song of Ice and Fire, #1)']")).click();
        String actualBookName = "A Game of Thrones (A Song of Ice and Fire, #1) by George R.R. Martin | Goodreads";
        String expectedBookName = driver.getTitle();
        Assert.assertEquals(actualBookName, expectedBookName);

    }

    @Test(priority = 3)
    public void wantToRead() throws InterruptedException {
    	driver.findElement(By.xpath("//span[contains(@class,'Button__labelItem') and text()='Want to read']")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//span[text()='Continue to tags']")).click();
    	driver.findElement(By.xpath("//span[contains(@class,'Button__labelItem') and text()='Done']")).click();
       
    }

    @Test(priority = 4)
    public void myBooks() {
        driver.findElement(By.linkText("My Books")).click();
        WebElement Author = driver.findElement(By.xpath("//a[text()='Martin, George R.R.']"));
        Assert.assertEquals(true,Author.isDisplayed());
    }
    
    @Test(priority = 5)
    public void removebook() {
    	WebElement cancel = driver.findElement(By.xpath("//img[@title='Remove from my books']"));
    	cancel.click();
    	driver.switchTo().alert().accept();
    }
    
    @AfterTest
    public void logout() {
    	driver.findElement(By.xpath("//img[contains(@class,'circularIcon circularIcon--border') and @alt='Yash']")).click();
    	driver.findElement(By.linkText("Sign out")).click();
    }
}
