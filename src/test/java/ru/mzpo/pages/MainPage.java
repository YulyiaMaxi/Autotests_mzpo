package ru.mzpo.pages; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.mzpo.forms.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static org.openqa.selenium.Keys.BACK_SPACE;


public class MainPage  {// проверяем формы заявок на главной странице

  //  @Name("Кнопка 'Обратная связь'")
    private final SelenideElement FeedBackButton = $("[data-test-id='feed_back_button']");

  //  @Name("Селенид-значение полей формы Обратная связь")
    private final SelenideElement formNameField = $("[data-test-id='feed_back_input_name']");
    private final SelenideElement formEmailField = $("[data-test-id='feed_back_input_email']");

    private final SelenideElement formTelField = $("[data-test-id='feed_back_input_phone']");
    private final SelenideElement sendButton = $("[data-test-id='feed_back_submit_button']");
    private final SelenideElement ModalWindowSuccess = $("[data-test-id=success_window]");
    //private final SelenideElement ModalWindowFail //= //has Text
    private final SelenideElement closeModalWindow = $("[data-test-id=success_window_close]");
    // private final SelenideElement Mask = $("#callBack > div.modal-body > div:nth-child(3) > div > div > div > div > div.iti__flag.iti__ru");

    // Методы вставки input значений полей и отправка
    public void inputDataFeedBack() {
        FeedBackButton.hover();
        FeedBackButton.click();
        //int position = formNameField.getCaretPosition(0);

        formNameField.setValue(FeedBack.getName());
        formEmailField.setValue(FeedBack.getEmail());

        //formTelField t = new formTelField();
        // t.setHorizontalAlignment(formTelField.LEFT);

        //formTelField.setCaretPosition(0);
        // Mask.click();
        //  formTelField.setValue(" ");
        formTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("2").perform();

        //  JTextComponent comp = (JTextComponent) source;
        // comp.setCaretPosition(0);
        //formTelField.setValue(CursorAtStartFocus.focusGained);
        //formTelField.setValue
      //  formTelField.setValue(FeedBack.getTel());
        // отправляем форму
        sendButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindow() {
        ModalWindowSuccess.shouldBe(visible, Duration.ofSeconds(10));
        closeModalWindow.shouldBe(visible, Duration.ofSeconds(15));
        closeModalWindow.shouldBe(enabled).click();
        // closeModalWindow.hover();
        //  closeModalWindow.click();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  //  @Name("Кнопка Заказать звонок")
    private final SelenideElement CallBackButton = $("[data-test-id = call_back_button]");

  //  @Name("Селенид-значение полей формы Заказаь звонок")
    private final SelenideElement formCallBackNameField = $("[data-test-id = call_back_input_name]");
    // private final SelenideElement formCallBackMask = $("[data-test-id = call_back_input_phone]");
    private final SelenideElement formCallBackTelField =  $(byXpath("/html/body/header/div[3]/div[2]/div[2]/div[2]/div/div/div/form/div[1]/div[2]/div/div/input")); //$("[data-test-id = call_back_input_phone]");
    private final SelenideElement CallBackSendButton = $("[data-test-id = call_back_submit_button]");
    private final SelenideElement CallBackSuccessWindow = $("[data-test-id=success_window]");
    private final SelenideElement CallBackCloseModalWindow = $("[data-test-id=success_window_close]");

    // Методы вставки input значений полей и отправка
    public void inputDataCallBack() {
        CallBackButton.hover();
        CallBackButton.click();
        formCallBackNameField.setValue(CallBack.getName());
        //formCallBackMask.click();
        formCallBackTelField.setValue(CallBack.getTel());;
        formCallBackTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("1").perform();
     //   formCallBackTelField.setValue(CallBack.getTel());
        // отправляем форму
        CallBackSendButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowCallBack() {
        CallBackSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        CallBackCloseModalWindow.hover();
        CallBackCloseModalWindow.click();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


   // @Name("Селенид-значение полей формы Напишите нам")
    private final SelenideElement Write2UsFormNameField = $("[data-test-id = write2us_input_name]");
    private final SelenideElement Write2UsEmailField = $("[data-test-id = write2us_input_email]");
    private final SelenideElement Write2UsFormTelField = $("[data-test-id = write2us_input_phone]");

    private final SelenideElement Write2UsSendButton = $("[data-test-id = write2us_submit_button]");
    private final SelenideElement Write2UsSuccessWindow = $("[data-test-id=success_window]");
    private final SelenideElement Write2UsCloseModalWindow = $("[data-test-id=success_window_close]");

    // Методы вставки input значений полей и отправка
    public void inputDataWrite2Us() {
        $(byText("НАПИШИТЕ НАМ!")).scrollTo();
        Write2UsFormNameField.setValue(Write2Us.getName());
        Write2UsEmailField.setValue(Write2Us.getEmail());

        Write2UsFormTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("1").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
       // Write2UsFormTelField.setValue(Write2Us.getTel());


        // отправляем форму
        Write2UsSendButton.click();
    }

    // проверяем сообщение об отправке
    public void getWrite2UsModalWindow() {
        Write2UsSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        Write2UsCloseModalWindow.hover();
        Write2UsCloseModalWindow.click();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   // @Name("Кнопка Подарочный сертификат")
    private final SelenideElement MenuProfilesTab = $("[data-test-id='51']");
    private final SelenideElement GiftCertButton = $("[data-test-id='119']");

    private final SelenideElement GiftCertFormNameField = $("[data-test-id = gift_cert_input_name]");
    private final SelenideElement GiftCertFormTelField = $("[data-test-id = gift_cert_input_phone]");
    private final SelenideElement GiftCertFormEmailField = $("[data-test-id = gift_cert_input_email]");

    private final SelenideElement GiftCertSubmitButton = $("[data-test-id = gift_cert_submit_button]");
    private final SelenideElement GiftCertSuccessWindow = $("[data-test-id=success_window]");
    private final SelenideElement GiftCertCloseModalWindow = $("[data-test-id=success_window_close]");

    @Step("Кнопка Подарочный сертификат")
    // Методы вставки input значений полей и отправка
    public void inputDataGiftCert() {
        // прописать путь к отрытию формы
        //MenuProfilesTab.hover();// наводим курсор на Меню - Напрвления обучения
       // MenuProfilesTab.click();// кликаем на Меню - Напрвления обучения

       // GiftCertButton.hover();//наводим курсор на Подарочные сертификаты
       // GiftCertButton.click(); // кликаем на Подарочные сертификаты
        // заполняем поля формы

        $(byText("Подари сертификат на обучение родным и близким!")).scrollTo();

        GiftCertFormNameField.setValue(GiftCert.getName());

        GiftCertFormTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("2").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("2").perform();
     //   GiftCertFormTelField.setValue(GiftCert.getTel());
        GiftCertFormEmailField.setValue(GiftCert.getEmail());

        // отправляем форму
        GiftCertSubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowGiftCert() {
        GiftCertSuccessWindow.shouldBe(visible, Duration.ofSeconds(25));
        GiftCertCloseModalWindow.hover();
        GiftCertCloseModalWindow.click();
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  //  @Name("Форма Корзина Оплатить")
   //private final SelenideElement MenuProfilesTab1 = $("[data-test-id='51']"); // Меню Направления обучения
  // private final SelenideElement GiftCertButton1 = $(byXpath("/html/body/div[2]/div/nav/ul/li[3]/ul/li[6]/a/span"));
  //  private final SelenideElement SelectDropdown =$(byXpath("//select[@class='form-control']")); //findBy(text("--- Выберите направление ---"));
  //  private final SelenideElement  SelectDirectionGiftCert = $(byXpath("//option[@value='119']"));
   // private final SelenideElement AddToBasketButton = $(byXpath("/html/body/main/div[1]/div[4]/div[3]/div/button"));
   // private final SelenideElement BasketExecuteButton = $("[data-test-id = basket_execute_button]");
  //  private final SelenideElement BasketBuyButton = $("[data-test-id = gift_cert_buy_button]");
    // кнопка Перейти к оформлению
   // private final SelenideElement BasketIcon = $(byXpath("/html/body/main/div[1]/section[1]/div/div[2]/div[2]/div[1]/div[4]/a"));
  //  private final SelenideElement BasketExecute = $("[data-test-id = basket_execute_button]");
    private final SelenideElement BasketFormNameField = $("[data-test-id = gift_cert_input_name]");
    private final SelenideElement BasketFormTelField = $("[data-test-id = gift_cert_input_phone]");
    private final SelenideElement BasketFormEmailField = $("[data-test-id = gift_cert_input_email]");
    private final SelenideElement BasketFormPayButton = $("[data-test-id = gift_cert_buy_button]");
    //private final SelenideElement PaymentPage = $("[data-qa='logo']");
    @Step("Форма Корзина Оплатить")
    // Методы вставки input значений полей и отправка
    public void inputDataBasket() {
        // прописать путь к отрытию формы
       // MenuProfilesTab1.hover();// наводим курсор на Меню - Напрвления обучения
     //   MenuProfilesTab1.click();// кликаем на Меню - Напрвления обучения

       // GiftCertButton1.hover();//наводим курсор на Подарочные сертификаты
      //  GiftCertButton1.click(); // кликаем на Подарочные сертификаты
        $(byText("Подари сертификат на обучение родным и близким!")).scrollTo();
       // SelectDropdown.click();
      //  SelectDropdown.shouldBe(visible, Duration.ofSeconds(10));
      //  SelectDirectionGiftCert.scrollTo();
      //  SelectDirectionGiftCert.click();
      //  SelectCourse.click();
        //AddToBasketButton.click();
      //  BasketExecuteButton.click();
       // BasketIcon.click();
      //  BasketExecute.click();

        BasketFormNameField.setValue(Basket.getName()); // заполняем поля формы для оплаты
        BasketFormTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("3").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("2").perform();
      //  BasketFormTelField.setValue(Basket.getTel());
        BasketFormEmailField.setValue(Basket.getEmail());

        BasketFormPayButton.click();
    }

   // public void getPaymentPage() {
//        PaymentPage.shouldBe(visible, Duration.ofSeconds(30));
        //GiftCertCloseModalWindow.hover();
        // GiftCertCloseModalWindow.click();


    //прописать закрытие страницы платежа
// появление страницы Юмани - это знак успеха операции

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  //  @Name("Кнопка 'День открытых дверей")

    private final SelenideElement OpenDayNameField = $("[data-test-id = 'openday_input_name_189']");
    private final SelenideElement OpenDaySurnameField = $("[data-test-id = 'openday_input_surname_189']");
    private final SelenideElement OpenDayTelField = $("[data-test-id = 'openday_input_phone_189']");
    private final SelenideElement OpenDayEmailField = $("[data-test-id = 'openday_input_email_189']");
    private final SelenideElement OpenDaySubmitButton = $("[data-test-id = 'openday_submit_button_189']");
    private final SelenideElement OpenDaySuccessWindow = $("[data-test-id = 'success_window_close']");
    private final SelenideElement OpenDayCloseModalWindow = $("[data-test-id = 'success_window_close']");

    @Step("Методы вставки значений и отправки для формы ДОД")
    // Методы вставки input значений полей и отправка
    public void inputDataOpenDay() {

        $(byText("Ваше имя")).scrollTo();

        OpenDayNameField.setValue(OpenDay.getName());
        OpenDaySurnameField.setValue(OpenDay.getSurname());
        OpenDayEmailField.setValue(OpenDay.getEmail());
        OpenDayTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("4").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("2").perform();
        //OpenDayTelField.setValue(OpenDay.getTel());

        OpenDaySubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowOpenDay() {
        OpenDaySuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        OpenDayCloseModalWindow.click();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  @Name("Кнопка 'Бесплатный пробный урок")

    private final SelenideElement TrialLessonButton = $("[data-test-id = banner_trial_lesson_front_182]");  // на нее наводим
    private final SelenideElement TrialLessonDetails = $("[data-test-id = banner_openday_back_details_182]"); // на нее кликаем

   // @Name("Селенид-значение полей формы Бесплатный пробный урок")
    private final SelenideElement TrialLessonNameField = $("[data-test-id = openday_input_name_182]");//.parent().$("[class=\"t-input js-tilda-rule t-input_bbonly\"]");;
    private final SelenideElement TrialLessonSurnameField = $("[data-test-id = openday_input_surname_182]");
    private final SelenideElement TrialLessonTelField = $("[data-test-id = openday_input_phone_182]");
    private final SelenideElement TrialLessonEmailField = $("[data-test-id = openday_input_email_182]");

    private final SelenideElement TrialLessonSubmitButton = $("[data-test-id = openday_submit_button_182]");

    private final SelenideElement TrialLessonSuccessWindow = $("[data-test-id=success_window]");
    private final SelenideElement TrialLessonCloseModalWindow = $("[data-test-id=success_window_close]");

    @Step("Методы вставки значений и отправки для формы ДОД")
    // Методы вставки input значений полей и отправка
    public void inputDataTrialLesson() {

        $(byText("Количество мест в группе ограничено, поэтому не откладывайте регистрацию. Ждем Вас!")).scrollTo();

        TrialLessonNameField.setValue(TrialLesson.getName());
        TrialLessonSurnameField.setValue(TrialLesson.getSurname());
        TrialLessonEmailField.setValue(TrialLesson.getEmail());
        TrialLessonTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("9").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("2").perform();
      //  TrialLessonTelField.setValue(TrialLesson.getTel());

        // отправляем форму
        TrialLessonSubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowTrialLesson() {
        TrialLessonSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        TrialLessonCloseModalWindow.click();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   // @Name("Форма Демо-доступ")

    private final SelenideElement Discount_15NameField = $("[data-test-id='discount_15_input_name']");
    private final SelenideElement Discount_15TelField = $("[data-test-id='discount_15_input_phone']");
    private final SelenideElement Discount_15EmailField = $("[data-test-id='discount_15_input_email']");
    // радиокнопки
    private final SelenideElement Discount_15RadioMassage = $("[data-test-id='discount_15_radio_massage']");
    private final SelenideElement Discount_15RadioInGroup = $("[data-test-id='discount_15_radio_education_yes']");
    private final SelenideElement Discount_15RadioEduYes = $("[data-test-id='discount_15_radio_in_group']");
    private final SelenideElement Discount_15SubmitButton = $("[data-test-id='discount_15_submit_button']");

    private final SelenideElement Discount_15SuccessWindow = $("[data-test-id=success_window]");
    private final SelenideElement Discount_15CloseModalWindow = $("[data-test-id=success_window_close]");

    @Step("Методы вставки значений и отправки для формы ДОД")
    // Методы вставки input значений полей и отправка
    public void inputDataDiscount_15() {

        $(byText("Ответьте на вопросы")).scrollTo();

        Discount_15NameField.setValue(Discount_15.getName());
        Discount_15TelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("6").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("2").perform();
      //  Discount_15TelField.setValue(Discount_15.getTel());
        Discount_15EmailField.setValue(Discount_15.getEmail());

        Discount_15RadioMassage.click();
        Discount_15RadioInGroup.hover();
        Discount_15RadioInGroup.click();
        Discount_15RadioEduYes.hover();
        Discount_15RadioEduYes.click();


        // отправляем форму
        Discount_15SubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowDiscount_15() {
        Discount_15SuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        Discount_15CloseModalWindow.click();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //   @Name("Форма Акция СпортПакет")
    //private final SelenideElement MenuTabActions1 = $("[data-test-id='22']"); // Меню - О нас
   // private final SelenideElement DetailsButton = $("[data-test-id= btn_under_banner_promo_65]"); // на нее наводим

    private final SelenideElement SportPaketNameField = $("[data-test-id=promo_input_name_65]");//.parent().$("[class=\"t-input js-tilda-rule t-input_bbonly\"]");;
    private final SelenideElement SportPaketTelField = $("[data-test-id=promo_input_phone_65]");
    private final SelenideElement SportPaketSubmitButton = $("#promo > div:nth-child(3) > div > button");
    private final SelenideElement SportPaketSuccessWindow = $("[data-test-id = success_window]");
    private final SelenideElement SportPaketCloseModalWindow = $("[data-test-id=success_window_close]");

    @Step("Методы вставки значений и отправки для формы ДОД")
    // Методы вставки input значений полей и отправка  
    public void inputDataSportPaket() {
        //MenuTabActions1.click();
      // DetailsButton.click();
        $(byText("Ваше имя")).scrollTo();

        SportPaketNameField.setValue(SportPaket.getName());
        SportPaketTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("3").perform();
        actions().sendKeys("2").perform();
       // SportPaketTelField.setValue(SportPaket.getTel());

        // отправляем форму
        SportPaketSubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowSportPaket() {
        SportPaketSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        SportPaketCloseModalWindow.click();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  @Name("Форма Бесплатная консультация")

   // private final SelenideElement MenuTabMassage = $("[data-test-id='56']");
  // private final SelenideElement MenuTabPrograms = $("[data-test-id = '57']"); // Меню - О нас

  //  private final SelenideElement ButtonMassageClassic = $("[data-test-id = order_button_266]"); // Меню - О нас

    private final SelenideElement ConsultButton = $("[data-test-id = consult_button]");
    private final SelenideElement ConsultNameField = $("[data-test-id = consult_input_name]");
    private final SelenideElement ConsultTelField = $("[data-test-id = consult_input_phone]");
    private final SelenideElement ConsultSubmitButton = $("[data-test-id = consult_submit_button]");
    private final SelenideElement ConsultSuccessWindow = $("[data-test-id=success_window]");
    private final SelenideElement ConsultCloseModalWindow = $("[data-test-id=success_window_close]");

    @Step("Методы вставки значений и отправки для формы ДОД")
    // Методы вставки input значений полей и отправка
    public void inputDataConsult() {
       // $(byText("Бесплатная консультация")).scrollTo();
        ConsultButton.click();

        ConsultNameField.setValue(Consult.getName());
        ConsultTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("8").perform();
        actions().sendKeys("2").perform();
      //  ConsultTelField.setValue(Consult.getTel());
        // отправляем форму
        ConsultSubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowConsult() {
        ConsultSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        ConsultCloseModalWindow.click();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //   @Name("Селенид-значение полей формы Онлайн запись")
    private final SelenideElement BuyNowButton = $("[data-test-id = buy_now_button]");
    private final SelenideElement BuyNowNameField = $("[data-test-id = buy_now_input_name]");
    private final SelenideElement BuyNowTelField = $("[data-test-id = buy_now_input_phone]");
    private final SelenideElement BuyNowEmailField = $("[data-test-id = buy_now_input_email]");
    private final SelenideElement BuyNowForwardButton = $("[data-test-id = buy_now_forward]");
    // не идем дальше по форме, тк на данном шаге уже форма попадает в АМО
    private final SelenideElement BuyNowCloseModalWindow = $("[data-test-id=close_modal_fastbuy]");
   // private final SelenideElement MainPageButton = $(byXpath("/html/body/main/div[1]/nobr/p/span[1]/a/span"));

    @Step("Методы вставки значений и отправки для формы ДОД")
    // Методы вставки input значений полей и отправка
    public void inputDataBuyNow() {
        $(byXpath("//*[text()='Курсы классического массажа. Медицинское образование не требуется']")).scrollIntoView(true);

        BuyNowButton.hover();
        BuyNowButton.click();

        BuyNowNameField.setValue(BuyNow.getName());
        BuyNowTelField.click();

        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("5").perform();
        actions().sendKeys("2").perform();
      //  BuyNowTelField.setValue(BuyNow.getTel());

        BuyNowEmailField.setValue(BuyNow.getEmail());

        BuyNowForwardButton.hover();
        BuyNowForwardButton.click();

    }

    //не идем по дальнейшем шагам, при нажатии на кнопку Вперед заявка уже отправляется в АМО
    public void getModalWindowBuyNow() {
        BuyNowCloseModalWindow.click();
        $(byText("Главная")).scrollTo();
        //MainPageButton.click();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  @Name("Селенид-значение полей формы НМО Получить консультацию")
   // private final SelenideElement MenuTabFaculties = $("[data-test-id ='51']");
  // private final SelenideElement NMOMenuTab = $("[data-test-id ='45']");
    private final SelenideElement NMOConsultButton = $("[data-test-id = nmo_get_consult_button]");

    // дальше без тест.меток

    private final SelenideElement NMONameField = $("[data-test-id = nmo_get_consult_input_name]");
    private final SelenideElement NMOTelField = $("[data-test-id = nmo_get_consult_input_phone]");
    private final SelenideElement NMOSubmitButton = $("[data-test-id = nmo_get_consult_submit_button]");

    private final SelenideElement NMOSuccessWindow = $("[data-test-id = 'success_window']");
    private final SelenideElement NMOCloseModalWindow = $("[data-test-id = 'success_window_close']");
    //private final SelenideElement MainPageButton1 = $(byXpath("/html/body/main/div[1]/nobr/p/span[1]/a/span"));

    @Step("Методы вставки значений и отправки для формы нмо")
    // Методы вставки input значений полей и отправка
    public void inputDataNMO() {

     //   MenuTabFaculties.hover();
      //  NMOMenuTab.click();

        NMOConsultButton.click();

        NMONameField.setValue(NMO.getName());
        NMOTelField.click();

        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
       actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("8").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("2").perform();
        // NMOTelField.setValue(NMO.getTel());

        // отправляем форму
        NMOSubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowNMO() {
        NMOSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        NMOCloseModalWindow.click();
       // $(byText("Главная")).scrollTo();
       // MainPageButton1.click();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  @Name("Селенид-значение полей формы Трудно сделать выбор?")

    private final SelenideElement HardChoiceNameField = $("[data-test-id = hard_choice_input_name]");
    private final SelenideElement HardChoiceTelField = $("[data-test-id = hard_choice_input_phone]");
    private final SelenideElement HardChoiceSubmitButton = $("[data-test-id = hard_choice_submit_button]");

    private final SelenideElement HardChoiceSuccessWindow = $("[data-test-id=success_window]");
    private final SelenideElement HardChoiceCloseModalWindow = $("[data-test-id=success_window_close]");

    @Step("Методы вставки значений и отправки для формы Трудно сделать выбор?")
    // Методы вставки input значений полей и отправка
    public void inputDataHardChoice() {

        // MenuTabFaculties.hover();
      //  NMOMenuTab.click();
       // NMOConsultButton.click();
        $(byText("Трудно сделать выбор?")).scrollTo();

        HardChoiceNameField.setValue(HardChoice.getName());
        HardChoiceTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();

        actions().sendKeys("1").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("9").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("2").perform();
     //   HardChoiceTelField.setValue(HardChoice.getTel());
        // отправляем форму
        HardChoiceSubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowHardChoice() {
        HardChoiceSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        HardChoiceCloseModalWindow.click();
    }
}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /* @Name("Селенид-значение полей формы Очное обучение в группе - запись в блоках")
    private final SelenideElement MenuTabFaculties1 = $("[data-test-id ='51']");
    private final SelenideElement MenuTabMassage2 = $("[data-test-id = menu_tab_massage]");
    private final SelenideElement MenuTabMassageProg = $("[data-test-id = menu_tab_massage_programs]");
    private final SelenideElement BannerMassageClassic1 = $("[data-test-id = menu_tab_massage_programs]");

    private final SelenideElement InGroupNameField = $("[data-test-id = in_group_input_name]");
    private final SelenideElement InGroupTelField  = $("[data-test-id = in_group_input_phone]");
    private final SelenideElement InGroupSubmitButton = $("[data-test-id = in_group_submit_button]");

    private final SelenideElement InGroupSuccessWindow = $("[data-test-id=success_window]");
    private final SelenideElement InGroupCloseModalWindow = $("[data-test-id=success_window_close]");
    @Step ("Методы вставки значений и отправки для формы ДОД")
    // Методы вставки input значений полей и отправка

    public void inputDataInGroup() {

        MenuTabFaculties1.hover();
        MenuTabMassage2.hover();
        MenuTabMassageProg.click();
        NMOConsultButton.click();
        BannerMassageClassic1.click();

        InGroupNameField.setValue(InGroup.getName());
        InGroupTelField.click();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        actions().sendKeys(BACK_SPACE).perform();
        InGroupTelField.setValue(InGroup.getTel());

        // отправляем форму
        InGroupSubmitButton.click();
    }
    // проверяем сообщение об отправке
    public void getModalWindowInGroup() {
        InGroupSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        InGroupCloseModalWindow.click();
    }

}

 навести мышку на элемент */
   /* @Step("Навести на таб в хэдере {tabButton}")
   public void hoverOnTabButton(String tabButton) {
       app.base().hoverOnElement(app.base().getSelenideCollection(TAB_BUTTONS, tabButton).first());
    }

   /* @Step("Навести на кнопку Обратная связь")
    public void hoverOnTabButton(String FeedBackButton) {
        app.base().hoverOnElement(app.base().getSelenideCollection(TAB_BUTTONS, tabButton).first());*/
  /* @Step("Навести на кнопку Обратная связь")
   public void hoverOnFeedBackButton(String feedBackButton) {
       app.base().hoverOnElement(FeedBackButton);
   }
    @Step("Навести на кнопку Обратная связь")
    public void clickOnFeedBackButton(String feedBackButton) {
        app.base().hoverOnElement(FeedBackButton);
    }*/
//= $(".call").shouldHave(text("Обратная связь"));