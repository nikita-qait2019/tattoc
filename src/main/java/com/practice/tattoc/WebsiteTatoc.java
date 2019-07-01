package com.practice.tattoc;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
public class WebsiteTatoc {

public WebDriver driver;
    
@BeforeSuite(alwaysRun = true)
public void setupSuite() {    
System.setProperty("webdriver.chrome.driver","D:\\driver\\chromedriver.exe");
driver = new ChromeDriver();
String baseUrl = "http://10.0.1.86/tatoc/basic/grid/gate";
driver.manage().window().maximize();
driver.get(baseUrl);
} 
//@AfterSuite(alwaysRun = true)
public void tearDown() {
driver.quit();
}   
    
}
