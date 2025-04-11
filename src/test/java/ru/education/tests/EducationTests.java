package ru.education.tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.path.json.JsonPath;
import lombok.SneakyThrows;
//import org.testng.annotations.AfterMethod;
import org.apache.commons.logging.Log;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import ru.education.TestBaseEDU;
//import ru.education.ui.helper.BasketPayEDUHelper;
import ru.education.ui.helper.*;
//import ru.education.ui.helper.ConsultEDUHelper;
//import ru.education.ui.helper.Discount15EDUHelper;
//import ru.education.ui.modals.BasketEDUModalWindow;
//import ru.education.ui.modals.CallBackEDUModalWindow;
//import ru.education.ui.modals.ConsultEDUModalWindow;
//import ru.education.ui.modals.Discount15EDUModalWindow;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.lang.reflect.Method;
import java.util.Arrays;

import java.lang.reflect.Method;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;
import static ru.mzpo.SendingHttpPost.sendToAmo;


public class EducationTests /*extends TestBaseEDU*/ {
    private WebDriver driver;

    CallBackEDUHelper callBackEDUHelper = new CallBackEDUHelper();
    Discount15EDUHelper discount15EDUHelper = new Discount15EDUHelper();
    ConsultEDUHelper consultEDUHelper = new ConsultEDUHelper();
    BasketPayEDUHelper basketPayEDUHelper = new BasketPayEDUHelper();
    BasketApplyEDUHelper basketApplyEDUHelper = new BasketApplyEDUHelper();

    Logger logger = LoggerFactory.getLogger(EducationTests.class);


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
        //Configuration.reportsFolder = "target/surefire-reports"; // Укажите папку для отчетов
        Configuration.savePageSource = false; // Отключите сохранение источника страницы

    }
 
    @BeforeMethod
    @Step("Запускаем логи")
    protected void logTestStart(Method m, Object[] p) {
        logger.info("Тест " + m.getName() + " с параметрами " + Arrays.asList(p) + " запущен:-->");

    }

    protected boolean testPassed; // Убираем инициализацию здесь

    @BeforeMethod
    public void setTestPassed() {
        // Код для настройки перед каждым тестом
        testPassed = true; // Предполагаем, что тест пройдет.
    }

    @SneakyThrows
    @Test
    public void TestCallBackEDU() {

        open("https://www.mzpo.education/trudoustroystvo");

        callBackEDUHelper.inputDataCallBackEDU();
        callBackEDUHelper.getModalWindowCallBackEDU();

    StringBuilder response = sendToAmo("{\"name\":\"Supertester_CallBack_EDU\"}");
    String responseString = response.toString(); // Преобразуем StringBuilder в String
    System.out.println("Response: " + responseString); // Выводим ответ для отладки

    int status = JsonPath.from(responseString).getInt("status");

    // Проверка статуса теста
    if (status == 0) {
        throw new AssertionError("Тест Supertester_CallBack_EDU упал: статус " + status); // Если статус 0, выбрасываем AssertionError
    } else if (status == 1 || status == 2) {
        // Тест считается пройденным при статусах 1 и 2
        System.out.println("Тест Supertester_CallBack_EDU прошел успешно: статус " + status);
        // Продолжайте выполнение кода для пройденного теста
    } else {
        System.out.println("Получен неожиданный статус: " + status);
    }
    // Здесь можно добавить логику для обработки других статусов, если это необходимо


        // Закрываем драйвер после завершения теста (можно добавить в блок finally)


        // задержка перед выполнением след. теста в 20 сек
        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestDiscount15EDU() {

        open("https://www.mzpo.education/o-nas/aktsii/akciya-zabroniruj-skidku");
        executeJavaScript("$('.mapYandex').remove();");
        String DiscountEDUButton = "Забронировать скидку 15%";
        discount15EDUHelper.inputDataDiscount15EDU();
        discount15EDUHelper.getModalWindowDiscount15EDU();// Здесь можете добавить проверки для подтверждения успешного выполнения

       StringBuilder response = sendToAmo("{\"name\":\"Supertester_Discount15_EDU\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Discount15_EDU упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Discount15_EDU прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }


    @SneakyThrows
    @Test()
    public void TestConsultEDU() {
        open("https://www.mzpo.education/akkreditatsiya/periodicheskaya-akkreditaciya?supertester");
        // Configuration.pageLoadStrategy = "eager";
        //Configuration.pageLoadTimeout = 20000;
        executeJavaScript("$('.mapYandex').remove();");
        //ConsultEDUModalWindow modalWindow = new ConsultEDUModalWindow();
        String ConsultEDUButton = "Получить информацию";
        consultEDUHelper.inputDataConsultEDU();
        consultEDUHelper.getModalWindowConsultEDU();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Consult_EDU\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Consult_EDU упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Consult_EDU прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }


    @SneakyThrows
    @Test()
    public void TestBasketPayEDU() {
        open("https://www.mzpo.education/obuchenie/prof-perepodgotovka-bez-medobrazovaniya/fizkultura-sport-reabilitaciya");
        //openAndSetWindowSize("https://www.mzpo.education/obuchenie/prof-perepodgotovka-bez-medobrazovaniya/fizkultura-sport-reabilitaciya",1864,1080);
        executeJavaScript("$('.mapYandex').remove();");
        String ConsultEDUButton = "Забронировать скидку 15%";
        basketPayEDUHelper.inputDataBasketEDU(driver);

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Basket_EDU\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Basket_EDU упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Basket_EDU прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }
        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestBasketApplyEDU() {
        open("https://www.mzpo.education/obuchenie/prof-perepodgotovka-bez-medobrazovaniya/fizkultura-sport-reabilitaciya");
        executeJavaScript("$('.mapYandex').remove();");
        String ConsultEDUButton = "Забронировать скидку 15%";
        basketApplyEDUHelper.inputDataBasketEDU();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Basket_Apply_EDU\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Cart_Apply_EDU упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Cart_Apply_EDU прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @AfterClass
    public void tearDown() {
        // Закрываем драйвер после завершения теста
        if (driver != null) {
            closeWebDriver(); // Закрывает текущий WebDriver
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
}



