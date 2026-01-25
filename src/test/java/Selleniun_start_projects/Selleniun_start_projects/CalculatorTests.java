package Selleniun_start_projects.Selleniun_start_projects;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalculatorTests {

    static WebDriver browser;

    @Before
    public void setup() {

        WebDriverManager.firefoxdriver().setup();
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        browser.get("https://testpages.eviltester.com/apps/button-calculator/");
    }

    @After
    public void tearDown() {
        if (browser != null) {
            browser.quit();
        }
    }

    // ======= قراءة شاشة العرض الصحيحة =======
    private String readCalculatorDisplayValue() {
        List<WebElement> inputs = browser.findElements(By.cssSelector("input"));
        for (WebElement in : inputs) {
            String type = in.getAttribute("type");
            if (type == null) type = "";
            type = type.trim().toLowerCase();

            // الشاشة عادة input type=text/tel/number
            if ((type.equals("text") || type.equals("tel") || type.equals("number")) && in.isDisplayed()) {
                return in.getAttribute("value");
            }
        }
        // fallback
        WebElement fallback = browser.findElement(By.cssSelector("input[id*='display'], input[class*='display']"));
        return fallback.getAttribute("value");
    }

    private void press(String buttonText) {
        browser.findElement(By.xpath("//button[text()='" + buttonText + "']")).click();
    }

    // ----------- PLUS (2 tests) -----------
    @Test
    public void Test_Add_1() {

        press("AC");

        press("1");
        press("2");
        press("+");
        press("3");
        press("4");
        press("=");

        String result = readCalculatorDisplayValue();
        String expected = "46";
        assertEquals(expected, result);
    }

    @Test
    public void Test_Add_2() {

        press("AC");

        press("9");
        press("+");
        press("8");
        press("=");

        String result = readCalculatorDisplayValue();
        String expected = "17";
        assertEquals(expected, result);
    }

    // ----------- MINUS (2 tests) -----------
    @Test
    public void Test_Sub_1() {

        press("AC");

        press("2");
        press("0");
        press("-");
        press("7");
        press("=");

        String result = readCalculatorDisplayValue();
        String expected = "13";
        assertEquals(expected, result);
    }

    @Test
    public void Test_Sub_2() {

        press("AC");

        press("9");
        press("-");
        press("8");
        press("=");

        String result = readCalculatorDisplayValue();
        String expected = "1";
        assertEquals(expected, result);
    }

    // ----------- MULTIPLY (2 tests) -----------
    @Test
    public void Test_Mul_1() {

        press("AC");

        press("6");
        press("*");
        press("7");
        press("=");

        String result = readCalculatorDisplayValue();
        String expected = "42";
        assertEquals(expected, result);
    }

    @Test
    public void Test_Mul_2() {

        press("AC");

        press("9");
        press("*");
        press("9");
        press("=");

        String result = readCalculatorDisplayValue();
        String expected = "81";
        assertEquals(expected, result);
    }
}
