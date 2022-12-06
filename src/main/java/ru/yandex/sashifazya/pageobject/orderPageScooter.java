package ru.yandex.sashifazya.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class orderPageScooter {
    private WebDriver driver;
    //форма заказа с данными клиента
    private By orderUserDetailsForm = By.className("Order_Content__bmtHS");
    //форма заказа с деталями аренды
    private By orderRentDetailsForm = By.className("Order_Header__BZXOb");
    //поле Имя
    private By orderClientName = By.xpath(".//input[@placeholder='* Имя']");
    //поле Фамилия
    private By orderClientSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    //поле Адрес
    private By orderClientAdress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле Станция метро
    private By orderClientMetro = By.className("select-search__input");
    //поле Телефон
    private By orderClientPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    private By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");
    //поле Дата
    private By orderRentDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //выпадашка Срок аренды
    private By orderRentTermList = By.xpath(".//span[@class='Dropdown-arrow']");
    //поле Цвет
    private String orderScooterColor = ".//label/input[@id='%s']";
    //поле Коммент
    private By orderCourierComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка Заказать
    private By confirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //поп-ап Хотите заказать?
    private By askConfirmationPopup = By.className("Order_ModalHeader__3FDaJ");
    //кнопка подтверждения заказа
    private By yesConfirmationButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //отменить заказ
    private By noConfirmationButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");
   //поп-ап при успешном заказе
    private By orderSuccessPopup = By.className("Order_Modal__YZ-d3");
    //кнопка Посмотреть статус
    private By checkOrderStatusButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");
    //страница с отслеживанием статуса
    private By orderTrackInfo = By.className("Track_Content__St6Kn");
    public orderPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    //локатор станции метро делаем параметризированным
    public void fillOrderClientStation (String stationName){
        driver.findElement(orderClientMetro).sendKeys(stationName);
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.className("select-search__select")));
        driver.findElement(By.className("select-search__select")).click();
    }

    public void fillOrderClientName(String clientName){
        driver.findElement(orderClientName).sendKeys(clientName);
    }

    public void fillOrderClientSurname(String clientSurname){
        driver.findElement(orderClientSurname).sendKeys(clientSurname);
    }

    public void fillOrderClientAdress(String clientAdress){
        driver.findElement(orderClientAdress).sendKeys(clientAdress);
    }

    public void fillOrderClientPhone(String clientPhone){
        driver.findElement(orderClientPhone).sendKeys(clientPhone);
    }

    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }

    public void fillRentDate(String rentDate){
        driver.findElement(orderRentDate).sendKeys(rentDate);
    }

    public void chooseRentTerm(int rentTerm){
        driver.findElement(orderRentTermList).click(); //открылась выпадашка
        String rentTermLocator = String.format(".//div[@class='Dropdown-menu']/div[%s]", rentTerm);
        driver.findElement(By.xpath(rentTermLocator)).click();
    }

    public void chooseScooterColor(String color){
        String colorLocator = String.format(orderScooterColor, color);
        driver.findElement(By.xpath(colorLocator)).click();
    }

    public void fillCourierComment (String comment){
        driver.findElement(orderCourierComment).sendKeys(comment);
    }

    public void clickConfirmOrderButton(){
        driver.findElement(confirmOrderButton).click();
    }

    public void clickYesConfirmOrderButton(){
        driver.findElement(yesConfirmationButton).click();
    }

    public void clickNoConfirmOrderButton(){
        driver.findElement(noConfirmationButton).click();
    }
    public void fillUserDetailsForm(String clientName, String clientSurname, String clientAdress, String metroStation, String clientPhone){
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(orderUserDetailsForm));
        fillOrderClientName(clientName);
        fillOrderClientSurname(clientSurname);
        fillOrderClientAdress(clientAdress);
        fillOrderClientStation(metroStation);
        fillOrderClientPhone(clientPhone);
    }

    public boolean checkRentDetailsFormIsDisplayed() {
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(orderRentDetailsForm));
        return driver.findElement(orderRentDetailsForm).isDisplayed();
    }
    public void fillRentDetailsForm(String rentDate, int rentTerm, String color, String comment){
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(orderRentDetailsForm));
        fillRentDate(rentDate);
        chooseRentTerm(rentTerm);
        chooseScooterColor(color);
        if (comment!=null)
            fillCourierComment(comment);
    }

    public void confirmScooterOrder(){
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(askConfirmationPopup));
        clickYesConfirmOrderButton();
    }

    public void declineScooterOrder(){
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(askConfirmationPopup));
        clickNoConfirmOrderButton();
    }

    public boolean checkOrderSuccessPopupIsDisplayed(){
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(orderSuccessPopup));
        return driver.findElement(orderSuccessPopup).isDisplayed();
    }

    public void clickCheckOrderStatusButton(){
        driver.findElement(checkOrderStatusButton).click();
    }

    public boolean checkOrderTrackInfoIsDisplayed(){
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(orderTrackInfo));
        return driver.findElement(orderTrackInfo).isDisplayed();
    }
}
