package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Класс с выбором кнопок главной страницы
public class SamokatMainPage {

    private final WebDriver driver;
    // Локатор первой кнопки Заказ (в хедере)
    private static final By FIRST_ORDER_BUTTON = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button[@class = 'Button_Button__ra12g']");
    // Локатор второй кнопки Заказ
    public static final By SECOND_ORDER_BUTTON = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");
    // Локтор кнопки Cookie
    private static final By COOKIE_BUTTON = By.id("rcc-confirm-button");
    // Локатор для проверки отображения открывшейся страницы "Для кого самокат"
    public static final By ORDER_LIST_IS = By.xpath(".//div[text() = 'Для кого самокат']");

    public SamokatMainPage(WebDriver driver) {this.driver = driver;}

    // Скрол для второй кнопки
    public void scrollToSecondButton() {
        WebElement element = driver.findElement(SECOND_ORDER_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Нажатие на первую кнопку Заказ (в хедере)
    public void tapToFirstOrderButton() {
        // Скрытие окна с cookie
        driver.findElement(COOKIE_BUTTON).click();
        driver.findElement(FIRST_ORDER_BUTTON).click();
    }

    // Нажатие на вторую кнопку Заказ
    public void tapToSecondOrderButton() {
        // Скрытие окна с cookie
        driver.findElement(COOKIE_BUTTON).click();
        scrollToSecondButton();
        driver.findElement(SECOND_ORDER_BUTTON).click();
    }

    // Метод, который проверяет отображение необходимой страницы
    public WebElement isVivsableListMetod() {
        WebElement element = driver.findElement(ORDER_LIST_IS);
        return element;
    }
}
