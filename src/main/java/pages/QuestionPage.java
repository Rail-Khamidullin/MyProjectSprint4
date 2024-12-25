package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Вопросы на главной странице
public class QuestionPage {

    private final WebDriver driver;
    /// Для вопросов в подзаголовке
    // Локатор заголовка с вопросами (для скрола)
    private static final By SUB_HEADER = By.xpath(".//div[text() = 'Вопросы о важном']");
    // Локатор с блоком вопросов
    private static final By IS_VISIBLE_QUESTION = By.xpath(".//div[@class = 'Home_FAQ__3uVm4']");

    public QuestionPage(WebDriver driver) {
        this.driver = driver;
    }

    // Скрол до подзаголовка с вопросами и ожиданием
    public void scrollQuestion() {
        WebElement element = driver.findElement(SUB_HEADER);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Ожидание отображения блока с вопросами
    public void waitForQuestionModule() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(IS_VISIBLE_QUESTION));
    }

    // Выбор вопроса, ожидание и взятие ответа
    public String tapToQuestionAndReturnAnswer(int numberOfQuestion) {
        driver.findElement(By.id("accordion__heading-" + numberOfQuestion + "")).click();
        waitForQuestionModule();
        return driver.findElement(By.xpath(".//div[@id = 'accordion__panel-" + numberOfQuestion + "']//p")).getText();
    }
}
