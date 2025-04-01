/*package ru.mzpokurs.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.path.json.JsonPath;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.mirk.ui.helper.*;
import ru.mzpokurs.ui.helper.Discount15_KURS_Helper;
import ru.mzpokurs.ui.helper.Dod_KURS_Helper;
import ru.mzpokurs.ui.helper.CallBack_KURS_Helper;
import ru.mzpokurs.ui.helper.GiftCert_KURS_Helper;

import java.lang.reflect.Method;
import java.util.Arrays;
import static com.codeborne.selenide.Selenide.open;
import static ru.mzpo.SendingHttpPost.sendToAmo;

//import static ru.mzpos.checkViaUrl.SendingHttpPost.sendToAmo;

//при тестировании МИРК надо отключать моадальное окно ДОД (Влад)

// первый раз прогнать любой тест и вручную отключить куки, второй прогон - папку target не чистить

public class KURSTests {

    CallBack_KURS_Helper callBack_KURS_Helper = new CallBack_KURS_Helper();
    Dod_KURS_Helper dod_KURS_Helper = new Dod_KURS_Helper();
    Discount15_KURS_Helper discount15_KURS_Helper = new Discount15_KURS_Helper();
    GiftCert_KURS_Helper giftCert_KURS_Helper = new GiftCert_KURS_Helper();

    private WebDriver driver;

    Logger logger = LoggerFactory.getLogger(KURSTests.class);

    @BeforeTest
    @Step("Запускаем слушателя Allure")
    protected void globalLogs(final ITestContext context) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/chromdriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("page-load-strategy=eager");
        //options.addArguments("--headless"); // Включаем режим headless
        options.addArguments("--disable-gpu"); // Отключаем GPU (рекомендуется для headless)
        // Устанавливаем конфигурацию Selenide
        Configuration.browser = "chrome";
        Configuration.headless = false; // Устанавливаем режим headless
        Configuration.pageLoadStrategy = "eager"; // Устанавливаем стратегию загрузки страницы
        Configuration.browserSize = "1864x1080"; // Устанавливаем размер окна
    }

    @BeforeMethod
    @Step("Запускаем логи")
    protected void logTestStart(Method m, Object[] p) {
        logger.info("Тест " + m.getName() + " с параметрами " + Arrays.asList(p) + " запущен:-->");

    }

    protected boolean testPassed;

    @BeforeMethod
    public void setTestPassed() {
        // Код для настройки перед каждым тестом
        testPassed = true; // Предполагаем, что тест пройдет.
    }

    @SneakyThrows
    @Test()
    public void TestCallBack_KURS() {
        open("https://www.mzpokurs.com/svedeniya");
        callBack_KURS_Helper.inputDataCallBack_KURS();

        //API проверка

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Callback_KURS\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Callback_KURS упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Callback_KURS прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestDod_KURS() {
        open("https://www.mzpokurs.com/meropriyatiya/den-otkrytyh-dverej");

        dod_KURS_Helper.inputDataDod_KURS();

        //API проверка

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_DOD_KURS\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_DOD_KURS упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_DOD_KURS прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestDiscount15_KURS() {
        open("https://www.mzpokurs.com/aktcii/zabroniruj-skidku");

        discount15_KURS_Helper.inputDiscount15_KURS();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Discount15_KURS\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Discount15_KURS упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Discount15_KURS прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestGiftCert_KURS() {
        open("https://www.mzpokurs.com/aktcii/zabroniruj-skidku");

        giftCert_KURS_Helper.inputGiftCert_KURS();

        //API проверка

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_GiftCert_KURS\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_GiftCert_KURS упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_GiftCert_KURS прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }
}*/


