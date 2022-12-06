package ru.yandex.sashifazya;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.sashifazya.browserDriver.selectDriverEnum;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void before() {
        this.driver = getDriver(selectDriverEnum.valueOf("MOZILLA"));
    }

    private WebDriver getDriver(selectDriverEnum browserName)
    {
        if (selectDriverEnum.CHROME.equals(browserName)) {
            System.setProperty("webdriver.chrome.driver", "/Users/sashun/Documents/WebDriver/bin/chromedriver");
            return new ChromeDriver();
        }

        System.setProperty("webdriver.gecko.driver", "/Users/sashun/Documents/WebDriver/bin/geckodriver");
        return new FirefoxDriver();
    }
}

