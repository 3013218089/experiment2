package com.example.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WebdriverTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.ncfxy.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testWebdriver() throws Exception {
	  
	  try {  
          BufferedReader reader = new BufferedReader(new FileReader("E:/学习资料/大三/软件测试/info.csv"));//换成你的文件名 
          //reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
          String line = null; 
          
          while((line=reader.readLine())!=null){  
             String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
            if(item!=null){
    	        driver.get(baseUrl + "/"); 
                driver.findElement(By.id("name")).clear();
                driver.findElement(By.id("name")).sendKeys(item[0]);
                driver.findElement(By.id("pwd")).clear();
                driver.findElement(By.id("pwd")).sendKeys(item[0].substring(4));
                driver.findElement(By.id("submit")).click();
                assertEquals(item[1], driver.findElement(By.xpath("//tbody[@id='table-main']/tr/td[2]")).getText());
            }
             } 
          reader.close();
      } catch (Exception e) {  
          e.printStackTrace();  
      }    
	
    
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
