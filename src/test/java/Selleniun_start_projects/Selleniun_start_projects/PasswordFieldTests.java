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

    // ✅ Valid (Exactly 7 chars + allowed)
    @Test
    public void Test_ValidPassword_Exactly7Chars() throws Exception {

        browser.findElement(By.name("characters")).clear();
        browser.findElement(By.name("characters")).sendKeys("Ab12*Z9");
        browser.findElement(By.name("validate")).click();

        Thread.sleep(500);

        String result = browser.findElement(By.name("validation_message")).getAttribute("value");
        String expected = "Valid Value";
        assertEquals(expected, result);
    }

    // ❌ Invalid (Less than 7 chars)
    @Test
    public void Test_InvalidPassword_ShortLength() throws Exception {

        browser.findElement(By.name("characters")).clear();
        browser.findElement(By.name("characters")).sendKeys("Ab12*Z");
        browser.findElement(By.name("validate")).click();

        Thread.sleep(500);

        String result = browser.findElement(By.name("validation_message")).getAttribute("value");
        String expected = "Invalid Value";
        assertEquals(expected, result);
    }
}
