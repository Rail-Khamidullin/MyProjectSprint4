package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

// Класс со страницей "Для кого самокат"
public class AboutRentPage {

    private WebDriver driver;
    /// Заполнение блока "Про аренду"
    // Поле Даты привоза самоката
    private static final By DATE_ORDER_FIELD = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    // Поле срок аренды
    private static final By RENT_PERIOD = By.xpath(".//div[text() = '* Срок аренды']");
    // Поле для ввода комментария
    private static final By COMMENT_ORDER_FIELD = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    // Локатор кнопки Заказать
    private static final By ORDER_BUTTON = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");
    // Окно дата пикера для скрытия
    public static final By DATA_PICKER = By.className("react-datepicker-popper");

    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    // Скрытие всплывающих элементов
    public void willHiddenElement(By elementOnWindow) {
        // Скрыть элемент с помощью JavaScript
        WebElement element = driver.findElement(elementOnWindow);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", element);
    }

    // Установка даты
    public void putCurrentDate() {
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.YYYY");
        String date = formatter.format(current);
        driver.findElement(DATE_ORDER_FIELD).sendKeys(date);
        willHiddenElement(DATA_PICKER);
    }

    // Нажатие на кнопку Заказ
    public void tapToOrderButton() {
        driver.findElement(ORDER_BUTTON).click();
    }

    // Заполнение блока "Про аренду"
    public void checkOrderList(String quantityDays, String color, String comment) {
        putCurrentDate();
        driver.findElement(RENT_PERIOD).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(RENT_PERIOD));
        driver.findElement(By.xpath(".//div[text() = '" + quantityDays + "']")).click();
        driver.findElement(By.id(""+ color +"")).click();
        driver.findElement(COMMENT_ORDER_FIELD).sendKeys(comment);
    }
}
