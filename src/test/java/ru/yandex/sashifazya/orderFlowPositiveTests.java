package ru.yandex.sashifazya;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.sashifazya.pageobject.mainPageScooter;
import ru.yandex.sashifazya.pageobject.orderPageScooter;

@RunWith(Parameterized.class)
public class orderFlowPositiveTests {

    private final String clientName;
    private final String clientSurname;
    private final String clientAdress;
    private final String metroStation;
    private final String clientPhone;
    private final String rentDate;
    private final int rentTerm;
    private final String color;
    private final String comment;

    public orderFlowPositiveTests(String clientName, String clientSurname, String clientAdress, String metroStation, String clientPhone, String rentDate, int rentTerm, String color, String comment){
        this.clientName=clientName;
        this.clientSurname=clientSurname;
        this.clientAdress=clientAdress;
        this.metroStation=metroStation;
        this.clientPhone=clientPhone;
        this.rentDate=rentDate;
        this.rentTerm=rentTerm;
        this.color=color;
        this.comment=comment;
    }
    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][] {
                {"Вася", "Пупкин", "Менделеева 5", "Бульвар Рокоссовского", "89709876543", "14.12.2022", 2, "black", "попка - дурак"},
                {"Иван", "Иванов", "Ивановская набережная 77", "Кожуховская", "+79008765432", "06.12.2022", 5, "grey", null},
        };
    }

    @Test
    public void orderUpperButtonTest(){
    //    System.setProperty("webdriver.chrome.driver", "/Users/sashun/Documents/WebDriver/bin/chromedriver");
    //    WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "/Users/sashun/Documents/WebDriver/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // объект класса главной страницы
        mainPageScooter objMainPage = new mainPageScooter(driver);
        orderPageScooter objOrderPage = new orderPageScooter(driver);

        objMainPage.clickCookieCloseButton();
        objMainPage.clickUpperOrderButton();
        objOrderPage.fillUserDetailsForm(clientName, clientSurname, clientAdress, metroStation, clientPhone);
        objOrderPage.clickNextButton();
        Assert.assertTrue(objOrderPage.checkRentDetailsFormIsDisplayed());
        objOrderPage.fillRentDetailsForm(rentDate, rentTerm, color, comment);
        objOrderPage.clickConfirmOrderButton();
        //objOrderPage.declineScooterOrder();
        objOrderPage.confirmScooterOrder();
        Assert.assertTrue(objOrderPage.checkOrderSuccessPopupIsDisplayed());
        objOrderPage.clickCheckOrderStatusButton();
        objOrderPage.checkOrderTrackInfoIsDisplayed();

        driver.quit();
    }

    @Test
    public void orderLowerButtonTest(){
       // System.setProperty("webdriver.chrome.driver", "/Users/sashun/Documents/WebDriver/bin/chromedriver");
       // WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "/Users/sashun/Documents/WebDriver/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // объект класса главной страницы
        mainPageScooter objMainPage = new mainPageScooter(driver);
        orderPageScooter objOrderPage = new orderPageScooter(driver);

        objMainPage.clickCookieCloseButton();
        objMainPage.clickLowerOrderButton();
        objOrderPage.fillUserDetailsForm(clientName, clientSurname, clientAdress, metroStation, clientPhone);
        objOrderPage.clickNextButton();
        Assert.assertTrue(objOrderPage.checkRentDetailsFormIsDisplayed());
        objOrderPage.fillRentDetailsForm(rentDate, rentTerm, color, comment);
        objOrderPage.clickConfirmOrderButton();
        //objOrderPage.declineScooterOrder();
        objOrderPage.confirmScooterOrder();
        Assert.assertTrue(objOrderPage.checkOrderSuccessPopupIsDisplayed());
        objOrderPage.clickCheckOrderStatusButton();
        objOrderPage.checkOrderTrackInfoIsDisplayed();

        driver.quit();


    }
   // @After
   // public void tearDown(){
   //     driver.quit();
   // }

}
