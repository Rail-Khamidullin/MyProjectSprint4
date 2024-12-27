package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SamokatOrderPage {

    private final WebDriver driver;

    /// Заполнение блока "Для кого самокат"
    // Локатор кнопки Заказ в хедере
    private static final By FIRST_ORDER_BUTTON = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button[@class = 'Button_Button__ra12g']");
    // Поле для ввода Имени
    private static final By FIRST_NAME_FIELD = By.xpath(".//input[@placeholder = '* Имя']");
    // Поле для ввода Фамилии
    private static final By SECOND_NAME_FIELD = By.xpath(".//input[@placeholder = '* Фамилия']");
    // Поле для ввода Адреса
    private static final By ADDRESS_ORDER_FIELD = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    // Поле для ввода Станции метро
    private static final By SUBWAY_ORDER_FIELD = By.className("select-search__input");
    // Поле для ввода Телефона
    private static final By PHONE_NUMBER_FIELD = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    // Локтор кнопки Далее
    private static final By NEXT_BUTTON = By.xpath(".//button[text() = 'Далее']");

    public SamokatOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Выбор станции метро
    public void tapToSubwayStation(String name) {
        By station = By.xpath(".//li[@class = 'select-search__row' and @data-index = '" + name + "']");
        // Ожидание появления необходимой станции
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(station));
        driver.findElement(station).click();
    }

    // Заполнение блока "Для кого самокат"
    public void checkOrderList(String firstName, String secondName, String addressName, String subwayName, String phoneNumber) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(SECOND_NAME_FIELD).sendKeys(secondName);
        driver.findElement(ADDRESS_ORDER_FIELD).sendKeys(addressName);
        driver.findElement(SUBWAY_ORDER_FIELD).click();
        tapToSubwayStation(subwayName);
        driver.findElement(PHONE_NUMBER_FIELD).sendKeys(phoneNumber);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(NEXT_BUTTON));
        driver.findElement(NEXT_BUTTON).click();
    }
}