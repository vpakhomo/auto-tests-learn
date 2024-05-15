package ru.iaygi.ui.service;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.iaygi.ui.data.TestData;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static ru.iaygi.common.EndPoints.baseUrl;

public class TestBaseUi {
    public static ChromeOptions options;
    private static RemoteWebDriver driver;

    @Step("Настройка конфигурации браузера")
    public static void initDriver(boolean useSelenoid) {

        Configuration.baseUrl = baseUrl;
        if (!useSelenoid) {
            Configuration.holdBrowserOpen = true;
        }
        Configuration.browserSize = "1920x1080";

        options = new ChromeOptions();
        options.setCapability("browserVersion", "113.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {
            {
                put("name", "Website Test");
                put("sessionTimeout", "30m");
                put("enableVNC", TestData.enableVNC);
                put("screenResolution", "1920x1080x24");
                put("env", new ArrayList<String>() {
                    {
                        add("TZ=UTC");
                    }
                });
                put("labels", new HashMap<String, Object>() {
                    {
                        put("manual", "true");
                    }
                });
                put("enableVideo", TestData.enableVideo);
            }
        });

        if (useSelenoid) {
            try {
                driver = new RemoteWebDriver(new URL(TestData.selenoid), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver.manage().window().setSize(new Dimension(1920, 1080));
            WebDriverRunner.setWebDriver(driver);
        }

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Step("Закрытие драйвера")
    public static void closeDriver(boolean useSelenoid) {
        if (useSelenoid) {
            driver.quit();
        }
    }
}