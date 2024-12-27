package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Класс с подтверждением заказа самоката
public class OrderWindowPage {

    private WebDriver driver;
    // Локатор кнопки всплывающего окна "Хотите оформить заказ" с названием Да
    private static final By ORDER_BUTTON_YES = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    // Локатор кнопки всплывающего окна "Хотите оформить заказ" с названием Нет
    private static final By ORDER_BUTTON_NO = By.xpath(".//button[text() = 'Нет']");
    // Локатор кнопки окна подтверждения успеха заказа самоката
    public static final By FINAL_WINDOW_BUTTON = By.xpath(".//*[@id = 'root']//button[(@class = 'Button_Button__ra12g Button_Middle__1CSJM') " + "and (text() = 'Посмотреть статус')]");

    public OrderWindowPage(WebDriver driver) {this.driver = driver;}

    // Нажатие на кнопку Да или Нет в окне подтверждения оформление заказа
    public void tapToFinishOrderButton(String chooseButton) {

        if (chooseButton.equals("Yes")) {
            driver.findElement(ORDER_BUTTON_YES).click();
        } else {
            driver.findElement(ORDER_BUTTON_NO).click();
        }
    }

    // Метод, который проверяет отображение окна с успешным заказом
    public WebElement isVisibleFinalWindow() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(FINAL_WINDOW_BUTTON));
        WebElement element = driver.findElement(FINAL_WINDOW_BUTTON);
        return element;
    }
}
