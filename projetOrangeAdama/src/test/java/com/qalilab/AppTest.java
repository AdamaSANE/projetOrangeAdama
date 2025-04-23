package com.qalilab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangeHRMLoginTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensourcedemo.orangehrmlive.com/");
    }

    @Test
    public void testLogin() {
        // Localisation des éléments
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.className("oxd-button"));

        // Interaction
        username.sendKeys("Admin");
        password.sendKeys("admin123");
        loginBtn.click();

        // Synchronisation + vérification
        WebElement dashboard = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h6[text()='Dashboard']"))
        );
        Assert.assertTrue(dashboard.isDisplayed());
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}