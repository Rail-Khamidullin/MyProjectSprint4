package subheaderquestion;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SamokatMainPage;

import java.util.concurrent.TimeUnit;

import static constants.Constants.URL_SAMOKAT;
import static org.junit.Assert.assertTrue;

// Запуск теста для второй кнопки Заказ на главной странице
public class TestOrderSecondButton {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void orderSecondButtonTest() {
        /// Создаём драйвер для браузера Chrome
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL_SAMOKAT);

        /// Выбор кнопки Заказать после описания (2-ая кнопка)
        SamokatMainPage samokatMainPage = new SamokatMainPage(driver);
        samokatMainPage.tapToSecondOrderButton();
        WebElement isVisiableList = samokatMainPage.isVivsableListMetod();
        // Страница отображается
        assertTrue(isVisiableList.isDisplayed());
    }

    @After
    public void tearDown() {
        // Закрой браузер
        this.driver.quit();
    }
}
