package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс с подтверждением заказа самоката
public class OrderWindowPage {

    private WebDriver driver;
    // Локатор кнопки всплывающего окна "Хотите оформить заказ" с названием Да
    private static final By ORDER_BUTTON_YES = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    // Локатор кнопки всплывающего окна "Хотите оформить заказ" с названием Нет
    private static final By ORDER_BUTTON_NO = By.xpath(".//button[text() = 'Нет']");
    // Локатор текста из окна подтверждения успеха заказа самоката
    private static final By orderCreated = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ']");

    public OrderWindowPage(WebDriver driver) {
        this.driver = driver;
    }

    // Нажатие на кнопку Да или Нет в окне финального оформление заказа
    public void tapToFinishOrderButton(String chooseButton) {

        if (chooseButton == "Yes") {
            driver.findElement(ORDER_BUTTON_YES).click();
        } else {
            driver.findElement(ORDER_BUTTON_NO).click();
        }
    }

    // Получаем текст об успешности заказа
    public String textOrderCreated() {
        return driver.findElement(orderCreated).getText();
    }
}
