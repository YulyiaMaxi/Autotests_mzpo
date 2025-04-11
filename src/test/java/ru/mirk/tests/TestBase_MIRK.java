package ru.mirk.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.mzpo.SendingHttpPost;
import ru.mzpo.tests.MzpoTests;
import java.lang.reflect.Method;
import java.util.Arrays;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class TestBase_MIRK {
    SendingHttpPost sendingHttpPost = new SendingHttpPost();
    Logger logger = LoggerFactory.getLogger(MzpoTests.class);

    private WebDriver driver;

    @BeforeTest
    @Step("Запускаем слушателя Allure")
    protected void globalLogs(final ITestContext context) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/chromdriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("page-load-strategy=eager");
        options.addArguments("--headless"); // Включаем режим headless
        options.addArguments("--disable-gpu"); // Отключаем GPU (рекомендуется для headless)

        // Устанавливаем конфигурацию Selenide
        Configuration.browser = "chrome";
        Configuration.headless = true; // Устанавливаем режим headless
        Configuration.pageLoadStrategy = "eager"; // Устанавливаем стратегию загрузки страницы
        Configuration.browserSize = "1864x1080"; // Устанавливаем размер окна
        Configuration.timeout = 10000; // Увеличьте таймаут закрытия браузера до 10 секунд
        Configuration.savePageSource = false; // Отключите сохранение источника страницы
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            try {
                // Проверяем, открыт ли браузер
                if (driver.getWindowHandles().size() > 0) {
                    closeWebDriver(); // Закрывает текущий WebDriver
                    logger.info("Браузер был закрыт успешно.");
                } else {
                    logger.warn("Браузер остался открытым, но не удалось его закрыть.");
                }
            } catch (Exception e) {
                logger.error("Ошибка при закрытии браузера: {}", e.getMessage());
            }
        } else {
            logger.warn("Драйвер не инициализирован или уже закрыт.");
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        // Записываем результат теста в лог
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Тест " + result.getName() + " прошел успешно.");
            logger.info("Тест " + result.getName() + " прошел успешно.");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Тест " + result.getName() + " упал.");
            logger.error("Тест " + result.getName() + " упал.");
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("Тест " + result.getName() + " был проигнорирован.");
            logger.warn("Тест " + result.getName() + " был проигнорирован.");
        }
    }

    protected boolean testPassed; // Убираем инициализацию здесь

    @BeforeMethod
    public void setTestPassed() {
        // Код для настройки перед каждым тестом
        testPassed = true; // Предполагаем, что тест пройдет.
    }

    @BeforeMethod
    @Step("Запускаем логи")
    protected void logTestStart(Method m, Object[] p) {
        logger.info("Тест " + m.getName() + " с параметрами " + Arrays.asList(p) + " запущен:-->");

    }

    }





