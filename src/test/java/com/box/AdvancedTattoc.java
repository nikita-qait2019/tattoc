package com.box;

//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
public class AdvancedTattoc {
    
    public WebDriver driver;
    @BeforeSuite(alwaysRun = true)
public void setupSuite() {    
System.setProperty("webdriver.chrome.driver","D:\\driver\\chromedriver.exe");
driver = new ChromeDriver();
String baseUrl = "http://10.0.1.86/tatoc/";
driver.manage().window().maximize();
driver.get(baseUrl);
driver.findElement(By.xpath("//a[text()='Advanced Course']")).click();
}
@Test
public void dropDown(){
    
        WebElement element = driver.findElement(By.xpath("//div[@class='menutop m2']"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//span[text()='Go Next']")).click();
 
}

}
