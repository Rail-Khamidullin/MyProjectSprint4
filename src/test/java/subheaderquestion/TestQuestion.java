package subheaderquestion;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.QuestionPage;

import java.util.concurrent.TimeUnit;

import static constants.Constants.URL_SAMOKAT;
import static org.junit.Assert.assertEquals;

// Запуск тестов для проверки наличия вопросов и правильных ответов
@RunWith(Parameterized.class)
public class TestQuestion {

    private WebDriver driver;

    // Порядковый номер вопроса (для локаторов)
    private int numberOfQuestion;
    // Текст, который должен присутствовать на сайте
    private String expectedText;

    public TestQuestion(int numberOfQuestion, String needTextIsVisiable) {
        this.numberOfQuestion = numberOfQuestion;
        this.expectedText = needTextIsVisiable;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameterized.Parameters
    public static Object[][] getAnswer() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void subHeaderTest() {

        // Создаём драйвер для браузера Chrome
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL_SAMOKAT);

        // Создаём экземпляр класса QuestionPage
        QuestionPage questionPage = new QuestionPage(driver);
        // Скролим сайт до подзаголовка с вопросами
        questionPage.scrollQuestion();
        // Получаем текст ответов с сайта
        String actualText = questionPage.tapToQuestionAndReturnAnswer(numberOfQuestion);
        // Сравниваем тексты
        assertEquals("Текст: " + actualText + " НЕ соответствует требованиям" + actualText, expectedText, actualText);
    }

    @After
    public void tearDown() {
        // Закрой браузер
        this.driver.quit();
    }
}
