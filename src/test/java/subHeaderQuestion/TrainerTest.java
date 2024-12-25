package subHeaderQuestion;//package ru.yandex.subHeaderQuestion;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import static org.junit.Assert.assertEquals;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.util.concurrent.TimeUnit;
//
//@RunWith(Parameterized.class)
//public class TrainerTest {
//
//
//    private WebDriver driver;
//
//    // Локатор для выбора 1-го вопроса
//
//    private By subHeader = By.xpath(".//div[text() = 'Вопросы о важном']");
//    // Локатор для проверки наличия текста 1-го вопроса
//    private By question;
//    private By answer;
//    private  String needText;
//
//    @Before
//    public void startUp() {
//        WebDriverManager.chromedriver().setup();
//    }
//
//    public TrainerTest(String needText, By answer, By question) {
//        this.question = question;
//        this.answer = answer;
//        this.needText = needText;
//    }
//
//    @Parameterized.Parameters
//    public static Object[][] getAnswer() {
//        return new Object[][]{
//                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
//                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."}
//                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
//                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
//                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
//                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
//                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
//                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
//        };
//    }
//
//    @Test
//    public void test() {
//
//        this.driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        driver.get("https://qa-scooter.praktikum-services.ru/");
//
//        WebElement element1 = driver.findElement(subHeader);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element1);
//
//        driver.findElement(question).click();
////        driver.findElement(answer).getText();
//
//        new WebDriverWait(driver, 3)
//                .until(ExpectedConditions.visibilityOfElementLocated(answer));
//
//        assertEquals(needText, driver.findElement(answer).getText());
//
//    }
//
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
//}
