package ru.mzpokurs.ui.helper;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.mzpokurs.forms.CallBack_KURS;

import java.time.Duration;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ESCAPE;

public class CallBack_KURS_Helper {

     private final SelenideElement CallBack_KURS_Button = $(byXpath("//div[contains(@class, 'cont-4')]//a[contains(text(), 'Заказать обратный звонок')]"));

     private final SelenideElement CallBack_KURS_Name = $("[data-test-id = call_back_input_name]");
     private final SelenideElement CallBack_KURS_Tel = $("[data-test-id = call_back_input_phone]");
     private final SelenideElement CallBack_KURS_SubmitButton = $("[data-test-id = call_back_submit_button]");

     private final SelenideElement CallBack_KURS_Success = $("[data-test-id = call_back_success_window]");
     private final SelenideElement CallBack_KURS_Success_Close = $("[data-test-id = call_back_success_window_close]");

     public void inputDataCallBack_KURS() {
            CallBack_KURS_Button.shouldBe(visible, Duration.ofSeconds(5));
            CallBack_KURS_Button.click();
            //заполняем поля

            CallBack_KURS_Name.setValue(CallBack_KURS.getName());
            CallBack_KURS_Tel.setValue(CallBack_KURS.getTel());
            CallBack_KURS_SubmitButton.hover();
            CallBack_KURS_SubmitButton.click();

            CallBack_KURS_Success.shouldBe(visible);
            CallBack_KURS_Success_Close.click();
        }
    }

