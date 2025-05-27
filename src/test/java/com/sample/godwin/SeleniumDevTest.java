package com.sample.godwin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumDevTest {

    WebDriver driver;

    @BeforeTest
    public void setup () {
        //We are going to call web driver
     driver=new ChromeDriver();
    }

    @Test(description = "Loading the webpage")
    public void testSeleniumDevNavigation () {
      driver.get("https://selenium.dev");
    }

    @Test
    public void testFormSelenium() {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.getTitle();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();
        WebElement message = driver.findElement(By.id("message"));
        message.getText();
        Assert.assertEquals("Received!", "Received fine!");

    }

    @AfterTest
    public void closeSession () {
        driver.close();
    }
}
