package ru.yandex.sashifazya.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class mainPageScooter {
    private WebDriver driver;
    //faq list
    private By faqList = By.xpath(".//div[@class='accordion']");
    //вопрос
    private By costQuestion;
    //ответ
    private By costQuestionAnswer;
    //кнопка закрыть поп-ап про куки
    private By cookieCloseButton = By.className("App_CookieButton__3cvqF");
    //верхняя кнопка Заказать
    private By upperOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //нижняя кнопка Заказать
    private By lowerOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //локаторы вопросов и ответов делаем параметризированными
    private String costQuestionLocator (int questionNumber){
        return String.format("accordion__heading-%s", questionNumber);
    }
    private String costQuestionAnswerLocator (int questionNumber){
        return String.format("accordion__panel-%s", questionNumber);
    }

    //конструктор
    public mainPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForFAQLoading() {
        //new WebDriverWait(driver, 5).until(driver -> (driver.findElement(faqList).isEnabled() == true));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(faqList));
    }
    public void clickCookieCloseButton() {

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(cookieCloseButton));
        driver.findElement(cookieCloseButton).click();
    }
    public void clickQuestion(int questionNumber) {
        costQuestion = By.id(costQuestionLocator(questionNumber));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(costQuestion));
        driver.findElement(costQuestion).click();
    }
    public String getQuestionAnswerText(int questionNumber) {
        costQuestionAnswer = By.id(costQuestionAnswerLocator(questionNumber));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(costQuestionAnswer));
        return driver.findElement(costQuestionAnswer).getText();
    }

    public void clickUpperOrderButton(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(upperOrderButton));
        new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(upperOrderButton));
        driver.findElement(upperOrderButton).click();
    }

    public void clickLowerOrderButton(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(lowerOrderButton));
        new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(lowerOrderButton));
        driver.findElement(lowerOrderButton).click();
    }
}
