package ru.mirk.ui.helper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import ru.mirk.forms.Widget_MIRK;
import static com.codeborne.selenide.Selenide.*;



public class Widget_MIRK_Helper {

    private final SelenideElement Cookie_Button = $(".g-button");
    private final SelenideElement Widget_Box = $(".wof-pop");

    private final SelenideElement formNameField = $(".widget-text-name.widget-text-input-name");// если обращаемся сразу к 2м классам, то пишем через точку

    private final SelenideElement formTelField = $(".widget-text-input.widget-text-input-phone");
    private final SelenideElement checkBox = $("#privacy-chbx");
    private final SelenideElement sendButton = $(".widget-btn.widget-btn-1");
    private final SelenideElement ModalWindowSuccess = $(".widget.widget-wrap");
    private final SelenideElement closeModalWindow = $(".widget-btn.widget-btn-2");

    public void inputDataWidget_MIRK() {

        Cookie_Button.click();
        Widget_Box.hover();
        Widget_Box.click();

        switchTo().frame("wheel-of-fortune-id");

        // Взаимодействие с элементами внутри iframe
        formNameField.setValue(Widget_MIRK.getName());
        formTelField.setValue(Widget_MIRK.getTel());
        checkBox.click();
        sendButton.click();
        Configuration.timeout = 15000;
        ModalWindowSuccess.shouldBe(Condition.visible);
        //closeModalWindow.click();

        // Возврат к основному контенту страницы
        switchTo().defaultContent();
    }

    }
