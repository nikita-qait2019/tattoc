package com.box;

import java.util.Set;



import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Cookie;

public class TestTT {
    
public WebDriver driver;
        
@BeforeSuite(alwaysRun = true)
public void setupSuite() {    
System.setProperty("webdriver.chrome.driver","D:\\driver\\chromedriver.exe");
driver = new ChromeDriver();
String baseUrl = "http://10.0.1.86/tatoc/";
driver.manage().window().maximize();
driver.get(baseUrl);
driver.findElement(By.xpath("//a[text()='Basic Course']")).click();
}

@Test
public void greenBox(){
driver.findElement(By.xpath("//div[@class='greenbox']")).click();
String actualTitle=driver.getTitle();
String expectedTitle="Frame Dungeon - Basic Course - T.A.T.O.C";
Assert.assertEquals(actualTitle,expectedTitle);
}
@Test(dependsOnMethods=("greenBox"))
public void change()
{
    driver.switchTo().frame("main");
    String box1=driver.findElement(By.id("answer")).getAttribute("class");
    driver.switchTo().frame("child");
    String box2=driver.findElement(By.id("answer")).getAttribute("class");
    driver.switchTo().parentFrame(); 
    while(!box1.equals(box2))
    {
         driver.findElement(By.xpath("//a[text()='Repaint Box 2']")).click();
         driver.switchTo().frame("child");
         box2=driver.findElement(By.id("answer")).getAttribute("class");
         driver.switchTo().parentFrame();
    }
    driver.findElement(By.xpath("//a[text()='Proceed']")).click();   
}
@Test(dependsOnMethods=("change"))
public void dragDrop()
{
    WebElement From=driver.findElement(By.xpath("//div[@class='ui-draggable']"));	
    WebElement To=driver.findElement(By.xpath("//div[@id='dropbox']"));							
    Actions act=new Actions(driver);							
    act.dragAndDrop(From, To).build().perform();     
    driver.findElement(By.xpath("//a[text()='Proceed']")).click();
    String a="Windows - Basic Course - T.A.T.O.C";
    String b=driver.getTitle();
    Assert.assertEquals(b, a);
}
@Test(dependsOnMethods=("dragDrop"))
public void popup()
{
    driver.findElement(By.xpath("//a[text()='Launch Popup Window']")).click();
    
    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    String val1="Windows - Basic Course - T.A.T.O.C";
    String val2=driver.getTitle();
    Assert.assertEquals(val2,val1);
}
@Test(dependsOnMethods=("popup"))
public void form() throws InterruptedException
{
        String parentWinHandle = driver.getWindowHandle();
        System.out.println("Parent window handle: " + parentWinHandle);
        WebElement newWindowBtn = driver.findElement(By.xpath("//a[text()='Launch Popup Window']"));
        newWindowBtn.click();
        Set<String> winHandles = driver.getWindowHandles();
        for(String handle: winHandles){
        if(!handle.equals(parentWinHandle)){
            driver.switchTo().window(handle);
            Thread.sleep(1000);
            System.out.println("Title of the new window: " + driver.getTitle());
            }
        }
    System.out.println(driver.getTitle());
    WebElement name=driver.findElement(By.xpath("//input[@type='text']"));
    name.click();
    name.sendKeys("nikita");
    driver.findElement(By.xpath("//input[@id='submit']")).click();
    driver.switchTo().window(parentWinHandle);
}
@Test(dependsOnMethods=("form"))
public void token(){
    
    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    driver.findElement(By.xpath("//a[text()='Proceed']")).click();
    driver.findElement(By.xpath("//a[text()='Generate Token']")).isDisplayed();
    driver.findElement(By.xpath("//a[text()='Generate Token']")).click();
    
}
@Test(dependsOnMethods=("token"))
public void addCookie() {
    WebElement t=driver.findElement(By.xpath("//span[@id='token']"));
    t.isDisplayed();
    String str=t.getText();    
    System.out.println("token:"+str);
    
    
    String token = str.substring(7);
    Cookie name = new Cookie("Token",token);
    driver.manage().addCookie(name);
        driver.findElement(By.xpath("//a[text()='Proceed']")).click();

    System.out.println("-----------------------------------------Cookie name:-----------------------------"+driver.manage().getCookieNamed("Token").getValue());
    
}

@AfterSuite(alwaysRun = true)
public void tearDown() {
driver.quit();
} 
}
