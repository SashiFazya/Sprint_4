package ru.yandex.sashifazya;

import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.sashifazya.pageobject.mainPageScooter;

@RunWith(Parameterized.class)
public class FAQTests extends BaseTest {
    private final int questionNumber;
    private final String expectedAnswer;

        public FAQTests(int questionNumber, String expectedAnswer){
            this.questionNumber=questionNumber;
            this.expectedAnswer=expectedAnswer;
        }

    @Parameterized.Parameters
    public static Object[][] getAnswerText() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void checkFAQText(){
      //  System.setProperty("webdriver.chrome.driver", "/Users/sashun/Documents/WebDriver/bin/chromedriver");
      //  WebDriver driver = new ChromeDriver();
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // объект класса главной страницы
        mainPageScooter objMainPage = new mainPageScooter(driver);

        objMainPage.waitForFAQLoading();
        objMainPage.clickCookieCloseButton();
        objMainPage.clickQuestion(questionNumber);
        String actualAnswer = objMainPage.getQuestionAnswerText(questionNumber);
        Assert.assertEquals(actualAnswer, expectedAnswer);
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
