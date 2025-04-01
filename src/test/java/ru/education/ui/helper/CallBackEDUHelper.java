package ru.education.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
//import ru.education.TestBaseEDU;
import ru.education.forms.CallBackEDU;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;


public class CallBackEDUHelper  {// проверяем формы заявок на главной странице

    //  @Name("Кнопка 'Заказать обратный звонок")
    private final SelenideElement CallBackEDUButton = $("#modalCallbackButton");

    // @Name("Селенид-значение полей формы Обратная связь")
    private final SelenideElement CallBackEDUFieldName = $("[data-tests='callbackName']"); // ищем по тестовой метке
    private final SelenideElement CallBackEDUTelField = $("[data-tests='callbackPhone']");// ищем по тестовой метке
    private final SelenideElement CheckBox = $(".callback-form #form .form-check-input.m-0.policyCheckbox");
    private final SelenideElement SubmitButton = $("[data-tests='callbackSubmit']");

    private final SelenideElement CallBackEDUSuccessWindow = $("#LeadAnswer > div > div > div.modal-body.text-center.answer-modal > p");
    private final SelenideElement CallBackEDUCloseModalWindow = $("#LeadAnswer > div > div > div.modal-header.border-0.justify-content-end > button");

    public void inputDataCallBackEDU() {
        CallBackEDUButton.hover();
        CallBackEDUButton.click();
        CallBackEDUFieldName.setValue(CallBackEDU.getName());
        CallBackEDUTelField.click();
        for (int i = 0; i < 14; i++) { // 14 раз
            actions().sendKeys("BACK_SPACE").perform();

        }

        actions().sendKeys("1").perform();
        actions().sendKeys("5").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("2").perform();
        CheckBox.click();

        // отправляем форму
        SubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowCallBackEDU() {
        CallBackEDUSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        CallBackEDUCloseModalWindow.click();
    }
    //public boolean isModalWindowCallBackEDUOpen() {
    // Проверяем, что модальное окно видно на странице
    //   return CallBackEDUSuccessWindow.isDisplayed();
    // }


}