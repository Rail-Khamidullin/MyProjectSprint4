package subheaderquestion;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AboutRentPage;
import pages.OrderWindowPage;
import pages.SamokatMainPage;
import pages.SamokatOrderPage;

import java.util.concurrent.TimeUnit;

import static constants.Constants.URL_SAMOKAT;
import static org.junit.Assert.*;

// Тесты с проверкой формы заказа самоката через выбор кнопки Заказ в хедере
@RunWith(Parameterized.class)
public class TestOrderFirstButtonChrome {

    private WebDriver driver;

    /// Заполнение блока "Для кого самокат"
    // Имя
    private String firstName;
    // Фамилия
    private String secondName;
    // Адрес
    private String addressName;
    // Номер станции метро
    private String subwayName;
    // Номер телефона
    private String phoneNumber;

    /// Заполнение блока "Про аренду"
    // Кол-во дней для аренды
    private String quantityDays;
    // Цвета самоката
    private String color;
    // Текст коммента
    private String comment;
    // Кнопка Да или Нет в финальном окне заказа самоката
    private String chooseButton;

    public TestOrderFirstButtonChrome(String firstName, String secondName, String addressName, String subwayName,
                                      String phoneNumber, String quantityDays, String color, String comment, String chooseButton) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.addressName = addressName;
        this.subwayName = subwayName;
        this.phoneNumber = phoneNumber;
        this.quantityDays = quantityDays;
        this.color = color;
        this.comment = comment;
        this.chooseButton = chooseButton;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameterized.Parameters
    public static Object[][] getAnswer() {
        return new Object[][]{
                {"Иван", "Иванов", "г.Мюнхен, ул. Лейпцига Штрассе, 1", "1", "89979989999", "сутки", "black", "Первый тест", "Yes"},
                {"Олег", "Олегин", "г.Дрезден, ул. Лейпцига Штрассе, 2", "2", "89979901010", "двое суток", "grey", "Второй тест", "No"}
        };
    }

    @Test
    public void orderTest() {

        /// Создаём драйвер для браузера Chrome
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL_SAMOKAT);

        /// Выбор кнопки Заказать (в хедере) на главной странице
        SamokatMainPage samokatMainPage = new SamokatMainPage(driver);
        samokatMainPage.tapToFirstOrderButton();

        /// Заполнение окна "Для кого самокат"
        SamokatOrderPage samokatOrderPage = new SamokatOrderPage(driver);
        samokatOrderPage.checkOrderList(firstName, secondName, addressName, subwayName, phoneNumber);

        /// Заполнение окна "Про аренду"
        AboutRentPage aboutRentPage = new AboutRentPage(driver);
        aboutRentPage.checkOrderList(quantityDays, color, comment);
        aboutRentPage.tapToOrderButton();

        /// Выбор кнопки заказа в всплывающем окне
        OrderWindowPage orderWindowPage = new OrderWindowPage(driver);
        orderWindowPage.tapToFinishOrderButton(chooseButton);
        // Проверяем, что окно с успешным заказом отобразилось
        if (chooseButton.equals("Yes")) {
            // Получаем текст при НЕ успешном заказе
            assertTrue("Окно с успешным заказом НЕ отобразилось",
                    orderWindowPage.isVisibleFinalWindow().isDisplayed());
        } else {
            System.out.println("Заказ самоката отменён");
        }
    }

    @After
    public void tearDown() {
        // Закрой браузер
        this.driver.quit();
    }
}
