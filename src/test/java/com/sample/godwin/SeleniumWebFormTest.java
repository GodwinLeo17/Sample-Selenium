package com.sample.godwin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.time.Duration;

public class SeleniumWebFormTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        // Make sure chromedriver is on your PATH (or set webdriver.chrome.driver)
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(description = "Fill out the Selenium.dev web form and submit")
    public void testFillWebForm() throws InterruptedException {
        // 1. Go to the form page
        driver.get("https://selenium.dev/selenium/web/web-form.html");
        Thread.sleep (5000);

        // 2. Text input
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        textInput.clear();
        textInput.sendKeys("George");

        // 3. Password
        WebElement password = driver.findElement(By.name("my-password"));
        password.sendKeys("LEO!");

        // 4. Textarea
        WebElement textarea = driver.findElement(By.name("my-textarea"));
        textarea.sendKeys("This is a sample comment.\nLine two of comment.");
        Thread.sleep (5000);

        // 5. (Disabled input is not interactable—just assert it’s disabled)
        WebElement disabled = driver.findElement(By.name("my-disabled"));
        Assert.assertFalse(disabled.isEnabled(), "Expected the disabled input to be non-editable");

        // 6. (Readonly input—assert readonly attribute)
        WebElement readonly = driver.findElement(By.name("my-readonly"));
        Assert.assertEquals(readonly.getAttribute("readonly"), "true");

        // 7. Dropdown <select>
        Select select = new Select(driver.findElement(By.name("my-select")));
        select.selectByVisibleText("Two");

        // 8. Datalist (just type one of the options)
        WebElement datalist = driver.findElement(By.name("my-datalist"));
        datalist.sendKeys("Cheese");

        // 9. File upload (points to a file on your local machine)
        WebElement fileInput = driver.findElement(By.name("my-file"));
        String pathToFile = Paths.get("E:\\Godwin George\\Resume",  "Untitled.JPG").toAbsolutePath().toString();
        fileInput.sendKeys(pathToFile);

        // 10. Checkbox — check the second one if not already checked
        WebElement defaultCheckbox = driver.findElement(By.id("my-check-2"));
        if (!defaultCheckbox.isSelected()) {
            defaultCheckbox.click();
        }

        // 11. Radio button — select the second radio
        WebElement radio2 = driver.findElement(By.id("my-radio-2"));
        radio2.click();

        // 12. Color picker — send hex
        WebElement color = driver.findElement(By.name("my-colors"));
        color.sendKeys("#563d7c");

        // 13. Date picker — send yyyy-mm-dd
        WebElement date = driver.findElement(By.name("my-date"));
        date.sendKeys("2025-05-20");

        // 14. Range slider — send a number in range
        WebElement range = driver.findElement(By.name("my-range"));
        range.sendKeys("10");


        // 15. Submit
        WebElement submit = driver.findElement(By.cssSelector("body > main > div > form > div > div:nth-child(2) > button"));
        submit.click();


        // 16. Verify we’re on the acknowledgement page
        String currentUrl = driver.getCurrentUrl();
        Thread.sleep (7000);
        Assert.assertTrue(
                driver.getPageSource().contains("Form submitted"),
                "Page should show a submission acknowledgement."
        );
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    }