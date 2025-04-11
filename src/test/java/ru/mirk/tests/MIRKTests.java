package ru.mirk.tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.path.json.JsonPath;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import ru.mirk.ui.helper.*;
import java.lang.reflect.Method;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.*;
import static ru.mzpo.SendingHttpPost.sendToAmo;

public class MIRKTests {
    CallBackMIRKHelper callBackMIRKHelper = new CallBackMIRKHelper();
    GiftCertMIRKHelper giftCertMIRKHelper = new GiftCertMIRKHelper();
    DemoMIRKHelper demoMIRKHelper = new DemoMIRKHelper();
    CartMIRKHelper cartMIRKHelper = new CartMIRKHelper();
    GetProf20MIRKHelper getProf20MIRKHelper = new GetProf20MIRKHelper();
    OrderConsultMIRKHelper orderConsultMIRKHelper = new OrderConsultMIRKHelper();
    Write2UsMIRKHelper write2UstMIRKHelper = new Write2UsMIRKHelper();
    DodMIRKHelper dodMIRKHelper = new DodMIRKHelper();
    NeedConsultMIRKHelper needConsultMIRKHelper = new NeedConsultMIRKHelper();
    ApplyToCourseMIRKHelper applyToCourseMIRKHelper = new ApplyToCourseMIRKHelper();
    ConsultForFreeMIRKHelper consultForFreeMIRKHelper = new ConsultForFreeMIRKHelper();
    PageChecker checker = new PageChecker();

    private WebDriver driver;

    Logger logger = LoggerFactory.getLogger(MIRKTests.class);

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
    public void TestCallBackMIRK(){
        open("https://mirk.msk.ru/stati");

        executeJavaScript("$('.remodal.special-popup.block_has_image.remodal-is-initialized.remodal-is-opened').remove();");
        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");

        String CallBackButton = "Обратный звонок";
        callBackMIRKHelper.inputDataCallBackMIRK();

        //API проверка

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Callback_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Callback_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Callback_MIRK прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestGiftCertMIRK() {
        open("https://mirk.msk.ru/podari-sertifikat-na-obuchenie-blizkim");
        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");

        String GiftCertMIRKButton = "Подарочный сертификат - Обратная связь (заявка менеджеру";
        giftCertMIRKHelper.inputDataGiftCertMIRK();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_GiftCert_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_GiftCert_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_GiftCert_MIRK прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(20000);
    }



    @SneakyThrows
    @Test()
    public void TestOrderConsultMIRK() {
        open("https://mirk.msk.ru/");
        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");

        String OrderConsultMirkMIRKButton = "Подарочный сертификат - Корзина";
        orderConsultMIRKHelper.inputDataOrderConsultMIRK();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_OrderConsult_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_OrderConsult_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_OrderConsult_MIRK прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestDemoMIRK() {
        open("https://mirk.msk.ru/zabronirui-skidku-15");
        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");

        String DemoMIRKButton = "Подарочный сертификат - Обратная связь (заявка менеджеру";
        demoMIRKHelper.inputDataDemoMIRK();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Demo_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Demo_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Demo_MIRK прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestCartMIRK() {
        open("https://mirk.msk.ru/podari-sertifikat-na-obuchenie-blizkim");
        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();"); //демо с 15%
        executeJavaScript("$('.remodal.special-popup').remove();");// дод
        executeJavaScript("$('.remodal-overlay').remove();");

        String CartMIRKButton = "Подарочный сертификат - Корзина";
        cartMIRKHelper.inputDataCartMIRK();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Cart_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Cart_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Cart_MIRK прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

   @SneakyThrows
    @Test()
    public void TestWrite2UstMIRK() {
        open("https://mirk.msk.ru/napishite-nam");
        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");

        String Write2UsMirkMIRKButton = "Напишите нам из Контактов";
        write2UstMIRKHelper.inputDataWrite2UsMIRK();


        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Write2Us_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Write2Us_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Write2Us_MIRK прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestDodMIRK() {

        checker.checkIfPageExists();

        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");

        // Заполнение полей, если форма присутствует
        dodMIRKHelper.inputDataDodMIRK();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Dod_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");


        // Проверка статуса теста в АМО
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Dod_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Dod_MIRK прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }
        Thread.sleep(10000);
    }

      @SneakyThrows
      @Test()
      public void TestNeedConsultMIRK() {
          open("https://mirk.msk.ru/kafedra-meditsinskogo-massazha");

          executeJavaScript("$('.mapList').remove();");
          executeJavaScript("$('.siteEnter-popup').remove();");
          executeJavaScript("$('.remodal.special-popup').remove();");
          executeJavaScript("$('.remodal-overlay').remove();");

          String NeedConsultMIRKButton = "Нужна консультация";
          needConsultMIRKHelper.inputDataNeedConsultMIRK();

          StringBuilder response = sendToAmo("{\"name\":\"Supertester_NeedConsult_MIRK\"}");
          String responseString = response.toString(); // Преобразуем StringBuilder в String
          System.out.println("Response: " + responseString); // Выводим ответ для отладки
          int status = JsonPath.from(responseString).getInt("status");
      // Проверка статуса теста
      if (status == 0) {
          throw new AssertionError("Тест Supertester_NeedConsult_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
      } else if (status == 1 || status == 2) {
          // Тест считается пройденным при статусах 1 и 2
          System.out.println("Тест Supertester_NeedConsult_MIRK прошел успешно: статус " + status);
          // Продолжайте выполнение кода для пройденного теста
      } else {
          System.out.println("Получен неожиданный статус: " + status);
          // Здесь можно добавить логику для обработки других статусов, если это необходимо
      }

      Thread.sleep(10000);
  }

@SneakyThrows
    @Test()
    public void TestApplyToCourseMIRK() {
        open("https://mirk.msk.ru/kafedra-meditsinskogo-massazha#");

        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");

        String ApplyToCourseMIRKButton = "Записаться на курсы - со страницы Медмассажа";
        applyToCourseMIRKHelper.inputDataApplyToCourseMIRK();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_ApplyToCourse_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

    // Проверка статуса теста
    if (status == 0) {
        throw new AssertionError("Тест Supertester_ApplyToCourse_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
    } else if (status == 1 || status == 2) {
        // Тест считается пройденным при статусах 1 и 2
        System.out.println("Тест Supertester_ApplyToCourse_MIRK прошел успешно: статус " + status);
        // Продолжайте выполнение кода для пройденного теста
    } else {
        System.out.println("Получен неожиданный статус: " + status);
        // Здесь можно добавить логику для обработки других статусов, если это необходимо
    }

    Thread.sleep(10000);
}
    @SneakyThrows
    @Test()
    public void TestGetProf20MIRK() {
        open("https://mirk.msk.ru/poluchi-professiyu?supertester");

        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");

        String GetProf20MIRKButton = "Получите профессию со скидкой 20%";
        getProf20MIRKHelper.inputDataGetProf20MIRK();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_GetProf20%_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_GetProf20%_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_GetProf20%_MIRK прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }

@SneakyThrows
    @Test()
    public void TestConsultForFreeMIRK() {
        open("https://mirk.msk.ru/");

        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");

        String ConsultForFreeMIRKButton = "Получите пконсультацию";
        consultForFreeMIRKHelper.inputDataConsultForFree();
// в этом тесте нет поля Имя, поэтому проверка по апи по другому имени
   StringBuilder response = sendToAmo("{\"name\":\"Supertester_ConsultPhone_MIRK\"}");
    String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
    int status = JsonPath.from(responseString).getInt("status");

// Проверка статуса теста
    if (status == 0) {
        throw new AssertionError("Тест Supertester_Consult_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
    } else if (status == 1 || status == 2) {
        // Тест считается пройденным при статусах 1 и 2
        System.out.println("Тест Supertester_Consult_MIRK прошел успешно: статус " + status);
// Продолжайте выполнение кода для пройденного теста
    } else {
            System.out.println("Получен неожиданный статус: " + status);
// Здесь можно добавить логику для обработки других статусов, если это необходимо
    }

            Thread.sleep(10000);
}

    /* @SneakyThrows
    @Test()
    public void TestWidgetMIRK() {
        Widget_MIRK_Helper helper = new Widget_MIRK_Helper();

        open("https://mirk.msk.ru/");

        executeJavaScript("$('.mapList').remove();");
        executeJavaScript("$('.siteEnter-popup').remove();");
        executeJavaScript("$('.remodal.special-popup').remove();");
        executeJavaScript("$('.remodal-overlay').remove();");
        executeJavaScript("$('.wasCookieMessageShown').remove();");

        helper.inputDataWidget_MIRK();

// в этом тесте нет поля Имя, поэтому проверка по апи по другому имени
        StringBuilder response = sendToAmo("{\"name\":\"Supertester_Widget_MIRK\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

// Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_Widget_MIRK упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_Widget_MIRK прошел успешно: статус " + status);
// Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
// Здесь можно добавить логику для обработки других статусов, если это необходимо
        }

        Thread.sleep(10000);
    }*/

    }




