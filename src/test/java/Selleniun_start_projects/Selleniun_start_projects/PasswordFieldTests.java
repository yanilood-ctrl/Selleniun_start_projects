package Selleniun_start_projects.Selleniun_start_projects;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PasswordFieldTests {

    static WebDriver browser;

    @Before
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("https://testpages.eviltester.com/apps/7-char-val/");
    }

    @After
    public void tearDown() {
        if (browser != null) {
            browser.quit();
        }
    }

    // EP1: Valid – exactly 7 chars, allowed characters only
    @Test
    public void ep_valid_exact7_allowed() throws Exception {

        browser.findElement(By.name("characters")).clear();
        browser.findElement(By.name("characters")).sendKeys("Abc12*9");
        browser.findElement(By.name("validate")).click();

        Thread.sleep(500);

        String result = browser.findElement(By.name("validation_message")).getAttribute("value");
        assertEquals("Valid Value", result);
    }

    // EP2: Invalid – shorter than 7 characters
    @Test
    public void ep_invalid_shorter_than_7() throws Exception {

        browser.findElement(By.name("characters")).clear();
        browser.findElement(By.name("characters")).sendKeys("abc12");
        browser.findElement(By.name("validate")).click();

        Thread.sleep(500);

        String result = browser.findElement(By.name("validation_message")).getAttribute("value");
        assertEquals("Invalid Value", result);
    }

    // EP3: Invalid – longer than 7 characters
    @Test
    public void ep_invalid_longer_than_7() throws Exception {

        browser.findElement(By.name("characters")).clear();
        browser.findElement(By.name("characters")).sendKeys("abc123456");
        browser.findElement(By.name("validate")).click();

        Thread.sleep(500);

        String result = browser.findElement(By.name("validation_message")).getAttribute("value");
        assertEquals("Invalid Value", result);
    }

    // EP4: Invalid – exactly 7 chars but contains illegal character
    @Test
    public void ep_invalid_contains_illegal_char() throws Exception {

        browser.findElement(By.name("characters")).clear();
        browser.findElement(By.name("characters")).sendKeys("abc12@#");
        browser.findElement(By.name("validate")).click();

        Thread.sleep(500);

        String result = browser.findElement(By.name("validation_message")).getAttribute("value");
        assertEquals("Invalid Value", result);
    }
}
