package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.mirk.forms.CallBackMIRK;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ESCAPE;

public class CallBackMIRKHelper  {// проверяем формы заявок на главной странице

   // @Name("Форма Обратный звонок")

    private final SelenideElement CallBackMIRKButton = $$(".recall-btn__wrap").findBy(matchText("Обратный звонок"));
    private final SelenideElement CallBackMIRKName = $("form[data-s3-anketa-id='25477507'] .alias_name input[placeholder='Имя']");
    private final SelenideElement CallBackMIRKTel = $("form[data-s3-anketa-id='25477507'] .alias_phone input[placeholder='Телефон *']");
    private final SelenideElement CallBackMIRKSubmitButton = $(byXpath("//form[@data-s3-anketa-id='25477507']//button[@type='submit']"));         //("button[type='submit'][id='25477507_btn_4'][data-form-id='25477507_form_4']");
    @Step("Методы для кнопки Обратный звонок")
    // Методы вставки input значений полей и отправка
    public void inputDataCallBackMIRK() {
        CallBackMIRKButton.hover();
        CallBackMIRKButton.click();
       //заполняем поля

        CallBackMIRKName.setValue(CallBackMIRK.getName());
        CallBackMIRKTel.setValue(CallBackMIRK.getTel());
        CallBackMIRKSubmitButton.hover();
        CallBackMIRKSubmitButton.click();

        actions().sendKeys(ESCAPE).perform();
    }
    }