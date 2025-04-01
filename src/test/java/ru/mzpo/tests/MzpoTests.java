
package ru.mzpo.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.path.json.JsonPath;
import lombok.SneakyThrows;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import ru.mzpo.checkViaUrl.SendingHttpPost;
import ru.education.tests.EducationTests;
import ru.education.ui.helper.BasketPayEDUHelper;
import ru.education.ui.helper.CallBackEDUHelper;
import ru.education.ui.helper.ConsultEDUHelper;
import ru.education.ui.helper.Discount15EDUHelper;
import ru.mzpo.SendingHttpPost;
import ru.mzpo.pages.MainPage;
import ru.mzpo.pages.PageChecker_MZPO;

import java.lang.reflect.Method;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static ru.mzpo.SendingHttpPost.sendToAmo;


public class MzpoTests {
    MainPage mainPage = new MainPage();
    SendingHttpPost sendingHttpPost = new SendingHttpPost();
    Logger logger = LoggerFactory.getLogger(MzpoTests.class);

    private WebDriver driver;

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
        options.addArguments("--headless"); // Включаем режим headless
        options.addArguments("--disable-gpu"); // Отключаем GPU (рекомендуется для headless)

        // Устанавливаем конфигурацию Selenide
        Configuration.browser = "chrome";
        Configuration.headless = true; // Устанавливаем режим headless
        Configuration.pageLoadStrategy = "eager"; // Устанавливаем стратегию загрузки страницы
        Configuration.browserSize = "1864x1080"; // Устанавливаем размер окна

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

    @SneakyThrows
    @Test()
    public void TestFeedBack_MZPO() {
        open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");
        executeJavaScript("$('.lp9-widget').remove();");

        String feedBackButton = "Обратная связь";
        mainPage.inputDataFeedBack();
        mainPage.getModalWindow();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Feedback_MZPO\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_FeedBack_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Feedback_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);

    }

    @SneakyThrows

    @Test()
    public void TestCallBack_MZPO() {
        open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        // executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        String CallBackButton = "Заказать звонок";
        mainPage.inputDataCallBack();
        mainPage.getModalWindowCallBack();
        // Отправка запроса к AmoCRM

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Callback_MZPO\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Callback_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Callback_MZPOпрошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);

    }

    @SneakyThrows
    @Test()
    public void TestWrite2Us_MZPO() {
        open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");
        executeJavaScript("$('.lp9-widget').remove();");

        String write2UsButton = "Напишите нам";
        mainPage.inputDataWrite2Us();
        mainPage.getWrite2UsModalWindow();

        // Отправка запроса к AmoCRM
        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Write2Us_MZPO\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Write2Us_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Write2Us_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);

    }

    @SneakyThrows
    @Test()
    public void TestGiftCert_MZPO() {
        open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        // $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        String GiftCertButton = "Подарочный сертификат"; // создаем экземпляр класса
        mainPage.inputDataGiftCert();
        // mainPage.getModalWindowGiftCert();

        // Отправка запроса к AmoCRM
        StringBuilder response = sendToAmo("{\"name\":\"Supertester_GitfCert_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        String responseString = String.valueOf(response);
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_GitfCert_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_GitfCert_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test //тестируем только тогда, когда на сайте есть ДОД!!! тестируем форму в низу страницы! там нет капчи
    public void TestOpenDay_MZPO() {
       // open("https://www.mzpo-s.ru/activities/den-otkrytyh-dverey-v-mcpo?supertester");
        PageChecker_MZPO checker = new PageChecker_MZPO();
        checker.checkIfPageExists();

        executeJavaScript("$('.lp9-widget').remove();");
        executeJavaScript("$('._app_ready._map_ready._font_loaded._vector_ready').remove();");
        executeJavaScript("$('.embed-responsive.embed-responsive-16by9').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        // executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        String OpenDay = "ДОД";
        mainPage.inputDataOpenDay();
        mainPage.getModalWindowOpenDay();
        sendingHttpPost.sendToAmo("{\"name\":\"Supertester_OpenDay_MZPO\"}");

        // Отправка запроса к AmoCRM
        StringBuilder response = sendToAmo("{\"name\":\"Supertester_OpenDay_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        String responseString = String.valueOf(response);
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_GitfCert_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_GitfCert_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test //тестируем только тогда, когда на сайте есть ДОД!!!
    public void TestTrialLesson_MZPO() {
        open("https://www.mzpo-s.ru/activities/besplatnyy-probnyy-urok?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        // executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        String TrialLesson = "Пробный урок";
        mainPage.inputDataTrialLesson();
        mainPage.getModalWindowTrialLesson();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_TrialLesson_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        String responseString = String.valueOf(response);
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_TrialLesson_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_TrialLesson_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test //тестируем только тогда, когда на сайте есть ДОД!!!
    public void TestDiscount_15_MZPO() {
        open("https://www.mzpo-s.ru/promotions/skidka15-podarok?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        String Discount15 = "Демо-доступ";
        mainPage.inputDataDiscount_15();
        mainPage.getModalWindowDiscount_15();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Discount_15_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        String responseString = String.valueOf(response);
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Discount_15_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Discount_15_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void TestSportPaket_MZPO() {
        open("https://www.mzpo-s.ru/promotions/sportivnyy-paket?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        String SportPaket = "Спортпакет";
        mainPage.inputDataSportPaket();
        mainPage.getModalWindowSportPaket();

       StringBuilder response = sendToAmo("{\"name\":\"Supertester_SportPaket_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        String responseString = String.valueOf(response);
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_SportPaket_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_SportPaket_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void TestConsult_MZPO() {
        open("https://www.mzpo-s.ru/faculties/massag/massag-i-reabilitaciya/klassicheskiy-massazh?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        String Consult = "Бесплатная консультация = на странице Курс класс.массажа";
        mainPage.inputDataConsult();
        mainPage.getModalWindowConsult();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Consult_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        String responseString = String.valueOf(response);
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Consult_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Consult_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    /* @Test сняли формы онлай записи
    public void TestOnline() { // Онлайн запись
        open("https://www.mzpo-s.ru/faculties/massag/massag-i-reabilitaciya/klassicheskiy-massazh?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
       // $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
       // executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
      //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        String Consult = "Онлайн запись";
        mainPage.inputDataBuyNow();
        mainPage.getModalWindowBuyNow();
    }*/
    @SneakyThrows
    @Test
    public void TestNMO_MZPO() {
        open("https://www.mzpo-s.ru/faculties/nmo?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        // $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        // executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        executeJavaScript("$('.section.bg-mzpo.rad-10').remove();"); //
        String NMO = "НМО Консультация";
        mainPage.inputDataNMO();
        mainPage.getModalWindowNMO();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_NMO_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        String responseString = String.valueOf(response);
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_NMO_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_NMO_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void Test13HardChoice_MZPO() {
        open("https://www.mzpo-s.ru/faculties/distancionnoe-obuchenie?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        // $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //   executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //   executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        String HardChoice = "Трудно сделать выбор?";
        mainPage.inputDataHardChoice();
        mainPage.getModalWindowHardChoice();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_HardChoice_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        String responseString = String.valueOf(response);
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_HardChoice_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_HardChoice_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void TestBasketPay_MZPO() {
        open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        // $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //   executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        String Basket = "Корзина Купить";
        mainPage.inputDataBasket();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Basket_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        String responseString = String.valueOf(response);
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_HardChoice_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_HardChoice_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @AfterMethod
    public void tearDown() {
        // Закрываем драйвер после завершения теста
        if (driver != null) {
            driver.quit();
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





