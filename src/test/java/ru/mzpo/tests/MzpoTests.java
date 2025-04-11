package ru.mzpo.tests;

import io.restassured.path.json.JsonPath;
import lombok.SneakyThrows;
import org.testng.annotations.Test;
import ru.mzpo.pages.MainPage;
import ru.mzpo.pages.Widget_MZPO_Helper;
import ru.mzpo.tests.api.*; // заимпортированы все классы этого пакета автоматически
import ru.mzpo.tests.page_checker.*; // заимпортированы все классы этого пакета автоматически
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static ru.mzpo.SendingHttpPost.sendToAmo;



public class MzpoTests extends TestBase_MZPO {
    MainPage mainPage = new MainPage();

    @SneakyThrows
    @Test()
    public void TestFeedBack_MZPO() {

        Check_API_FeedBack checkApiFeedBack = new Check_API_FeedBack();

        Page_Checker_FeedBack checker = new Page_Checker_FeedBack();

        checker.checkIfPageExists();

        //open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");
        //executeJavaScript("$('.lp9-widget').remove();");

        mainPage.inputDataFeedBack();
        mainPage.getModalWindow();

        Check_API_FeedBack.checkResponse();

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestCallBack_MZPO() {
        Check_API_CallBack checkApiCallBack = new Check_API_CallBack();
        Page_Checker_CallBack checker = new Page_Checker_CallBack();
        //open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");

        checker.checkIfPageExists();

        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        // executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        String CallBackButton = "Заказать звонок";
        mainPage.inputDataCallBack();
        mainPage.getModalWindowCallBack();

        // Отправка запроса к AmoCRM

        Check_API_CallBack.checkResponse();

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestWrite2Us_MZPO() {
        Check_API_Write2Us checkApiWrite2Us = new Check_API_Write2Us();
        Page_Checker_Write2Us checker = new Page_Checker_Write2Us();

        checker.checkIfPageExists();
       // open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");

       // executeJavaScript("$('.lp9-widget').remove();");

        String write2UsButton = "Напишите нам";
        mainPage.inputDataWrite2Us();
        mainPage.getWrite2UsModalWindow();

        // Отправка запроса к AmoCRM
        Check_API_Write2Us.checkResponse();
        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test()
    public void TestGiftCert_MZPO() {
        Check_API_GiftCert checkApiGiftCert = new Check_API_GiftCert();
        Page_Checker_GiftCert checker = new Page_Checker_GiftCert();

        checker.checkIfPageExists();

        //open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        // $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        String GiftCertButton = "Подарочный сертификат"; // создаем экземпляр класса
        mainPage.inputDataGiftCert();
        // mainPage.getModalWindowGiftCert();
        // Отправка запроса к AmoCRM

        Check_API_GiftCert.checkResponse();

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_GitfCert_MZPO\"}");// оскольку метод sendToAmo включает и отправку, и ответ от сервера, то задержку поставила внутри метода этого

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void TestOpenDay_MZPO() {
       // open("https://www.mzpo-s.ru/activities/den-otkrytyh-dverey-v-mcpo?supertester");
        Page_Checker_OpenDay checker = new Page_Checker_OpenDay();
        Check_API_OpenDay checkApiOpenDay = new Check_API_OpenDay();

        checker.checkIfPageExists();

        executeJavaScript("$('.lp9-widget').remove();");
        executeJavaScript("$('._app_ready._map_ready._font_loaded._vector_ready').remove();");
        executeJavaScript("$('.embed-responsive.embed-responsive-16by9').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        // executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        mainPage.inputDataOpenDay();
        mainPage.getModalWindowOpenDay();
        sendingHttpPost.sendToAmo("{\"name\":\"Supertester_OpenDay_MZPO\"}");

        // Отправка запроса к AmoCRM
        Check_API_OpenDay.checkResponse();

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void TestTrialLesson_MZPO() {
        Check_API_TrialLesson checkApiTrialLesson = new Check_API_TrialLesson();
        Page_Checker_TrialLesson checker = new Page_Checker_TrialLesson();

        checker.checkIfPageExists();

        //open("https://www.mzpo-s.ru/activities/besplatnyy-probnyy-urok?supertester");
        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        // executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        String TrialLesson = "Пробный урок";
        mainPage.inputDataTrialLesson();
        mainPage.getModalWindowTrialLesson();

        Check_API_TrialLesson.checkResponse();

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void TestDiscount_15_MZPO() {
        Check_API_Discount15 checkApiDiscount15 = new Check_API_Discount15();
        Page_Checker_Discount15 checker = new Page_Checker_Discount15();

        checker.checkIfPageExists();

       // open("https://www.mzpo-s.ru/promotions/skidka15-podarok?supertester");

        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        mainPage.inputDataDiscount_15();
        mainPage.getModalWindowDiscount_15();

        Check_API_Discount15.checkResponse();

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void TestSportPaket_MZPO() {
        //open("https://www.mzpo-s.ru/promotions/sportivnyy-paket?supertester");
        Page_Checker_SportPaket checker = new Page_Checker_SportPaket();
        Check_API_SportPaket check_API = new Check_API_SportPaket();

        checker.checkIfPageExists();

        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        String SportPaket = "Спортпакет";
        mainPage.inputDataSportPaket();
        mainPage.getModalWindowSportPaket();

        Check_API_SportPaket.checkResponse();

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void TestConsult_MZPO() {
        Page_Checker_Consult checker = new Page_Checker_Consult();
        Check_API_Consult check_API = new  Check_API_Consult();

        checker.checkIfPageExists();

        //open("https://www.mzpo-s.ru/faculties/massag/massag-i-reabilitaciya/klassicheskiy-massazh?supertester");

        executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        String Consult = "Бесплатная консультация = на странице Курс класс.массажа";
        mainPage.inputDataConsult();
        mainPage.getModalWindowConsult();

        Check_API_Consult.checkResponse();

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
        //open("https://www.mzpo-s.ru/faculties/nmo?supertester");
        Page_Checker_NMO checker = new Page_Checker_NMO();
        Check_API_NMO check_API = new Check_API_NMO();

        checker.checkIfPageExists();

        //executeJavaScript("$('.lp9-widget').remove();");
        // $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        // executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        executeJavaScript("$('.section.bg-mzpo.rad-10').remove();"); //
        String NMO = "НМО Консультация";
        mainPage.inputDataNMO();
        mainPage.getModalWindowNMO();

        Check_API_NMO.checkResponse();

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void Test13HardChoice_MZPO() {
        //open("https://www.mzpo-s.ru/faculties/distancionnoe-obuchenie?supertester");
        Page_Checker_HardChoice checker = new Page_Checker_HardChoice();
        Check_API_HardChoice check_api = new Check_API_HardChoice();

        checker.checkIfPageExists();

       // executeJavaScript("$('.lp9-widget').remove();");
        // $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //   executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //   executeJavaScript("$('.modal-backdrop.fade.show').remove();");
        String HardChoice = "Трудно сделать выбор?";
        mainPage.inputDataHardChoice();
        mainPage.getModalWindowHardChoice();

        Check_API_HardChoice.checkResponse();

        Thread.sleep(10000);
    }

    @SneakyThrows
    @Test
    public void TestBasketPay_MZPO() {
        //open("https://www.mzpo-s.ru/faculties/podarochnye-sertifikaty?supertester");

        Page_Checker_Basket checker = new Page_Checker_Basket();
        Check_API_Basket check_api = new Check_API_Basket();

        checker.checkIfPageExists();

       // executeJavaScript("$('.lp9-widget').remove();");
        // $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //   executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        mainPage.inputDataBasket();

        Check_API_Basket.checkResponse();

        Thread.sleep(10000);
    }

  /*  @SneakyThrows
    @Test
    public void TestWidget_MZPO() {
        open("https://www.mzpo-s.ru/svedeniya-ob-obrazovatelnoj-organizatsii");
       // Page_Checker_SportPaket checker = new Page_Checker_SportPaket();
       // Check_API_SportPaket check_API = new Check_API_SportPaket();
        Widget_MZPO_Helper widget = new Widget_MZPO_Helper();
       // checker.checkIfPageExists();

       // executeJavaScript("$('.lp9-widget').remove();");
        //  $("#loadModal").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('#loadModal').remove();"); // отключаем модалку
        //$(".modal-backdrop.fade.show").shouldBe(visible, Duration.ofSeconds(30));
        //  executeJavaScript("$('.modal-backdrop.fade.show').remove();");

        widget.inputDataWidget_MZPO();

       // Check_API_SportPaket.checkResponse();

        Thread.sleep(10000);
    }*/

}





