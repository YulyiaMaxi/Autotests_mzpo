package ru.mirk.ui.helper;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mirk.forms.OrderConsultMIRK;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class OrderConsultMIRKHelper {

    //@Name("Форма Заказать консультацию специалиста на гл странице")

    private final SelenideElement OrderConsultMIRKName = $(By.xpath("//form[@id='25478907_form_2']//input[@name='d[0]' and @placeholder='Имя']"));
    private final SelenideElement OrderConsultMIRKTel = $(By.xpath("//form[@id='25478907_form_2']//input[@name='d[1]' and @placeholder='Телефон *']"));
    private final SelenideElement OrderConsultMIRKSubmitButton = $(By.xpath("//form[@id='25478907_form_2']//button[@type='submit']"));

    @Step("Методы для формы Заказать консультацию специалиста на гл страниц")

    public void inputDataOrderConsultMIRK() {

        $(byText("Заказать консультацию специалиста")).scrollTo();

        OrderConsultMIRKName.setValue(OrderConsultMIRK.getName());
        OrderConsultMIRKTel.setValue(OrderConsultMIRK.getTel());
        OrderConsultMIRKSubmitButton.click();

          }
}