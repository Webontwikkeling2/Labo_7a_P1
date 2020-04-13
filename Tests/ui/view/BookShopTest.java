package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class BookShopTest {
    private static final String url = "http://localhost:8080/";
    private WebDriver driver;

    @Before
    public void setUp () {
        System.setProperty("webdriver.chrome.driver", "/Applications/Servers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void test_Navigeren_Naar_Form(){
        driver.get(url+"bookForm.jsp");

        assertEquals("Book Info", driver.getTitle());
    }

    @Test
    public void test_Navigeren_naar_form_Velden_niet_invullen_Fout_melding_verwacht(){
        driver.get(url+"bookForm.jsp");

        assertEquals("Book Info", driver.getTitle());

        driver.findElement(By.id("calculate")).click();

        assertEquals("Book Info", driver.getTitle());
        assertEquals("Vul alle velden in.", driver.findElement(By.tagName("p")).getText());
    }

    @Test
    public void test_Velden_ingevuld_Resultaat_correct_wordt_verwacht(){
        driver.get(url+"bookForm.jsp");

        WebElement titel = driver.findElement(By.id("title"));
        titel.clear();
        titel.sendKeys("Alles Komt Goed");

        WebElement prijs = driver.findElement(By.id("price"));
        prijs.clear();
        prijs.sendKeys("10");

        WebElement aantal = driver.findElement(By.id("number"));
        aantal.clear();
        aantal.sendKeys("7");

        WebElement button = driver.findElement(By.id("calculate"));
        button.click();

        assertEquals("Book", driver.getTitle());
        assertEquals("Voor 7 exemplaren van het boek Alles Komt Goed moet je €70 betalen.", driver.findElement(By.tagName("p")).getText());
    }

    //
    // @After
    public void tearDown () {
        driver.quit();
    }
}
