package ru.mzpo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.w3c.dom.Text;
import ru.mzpo.forms.Widget_MZPO;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.xpath;


public class Widget_MZPO_Helper {

        private final SelenideElement Widget_Box = $(".wof-pop");

        private final SelenideElement formNameField = $(".widget-text-name.widget-text-input-name");// если обращаемся сразу к 2м классам, то пишем через точку


        private final SelenideElement formTelField = $(".widget-text-input.widget-text-input-phone");
        private final SelenideElement checkBox = $("#privacy-chbx");
        private final SelenideElement sendButton = $(".widget-btn.widget-btn-1");
        private final SelenideElement ModalWindowSuccess = $(".widget.widget-wrap");
        private final SelenideElement closeModalWindow = $(".widget-btn.widget-btn-2");

        public void inputDataWidget_MZPO() {

        Widget_Box.hover();
        Widget_Box.click();

                switchTo().frame("wheel-of-fortune-id");

                // Взаимодействие с элементами внутри iframe
                formNameField.setValue(Widget_MZPO.getName());
                formTelField.setValue(Widget_MZPO.getTel());
                checkBox.click();
                sendButton.click();
                Configuration.timeout = 15000;
                ModalWindowSuccess.shouldBe(Condition.visible);
                //closeModalWindow.click();

                // Возврат к основному контенту страницы
                switchTo().defaultContent();
    }
}