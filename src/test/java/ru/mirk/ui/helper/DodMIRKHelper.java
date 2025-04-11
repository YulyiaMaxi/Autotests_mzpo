package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mirk.forms.DodMIRK;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DodMIRKHelper  {

    //@Name("Форма ДОД")

    private final SelenideElement DodFieldName = $(By.xpath("//form[@id='25473707_form_1']//input[@name='d[0]']"));

    private final SelenideElement DodFieldTel = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div/div[3]/div/div[2]/form/div[3]/div[2]/input"));
    private final SelenideElement DodFieldSurname = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div/div[3]/div/div[2]/form/div[2]/div[2]/input"));
    private final SelenideElement DodFieldEmail = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div/div[3]/div/div[2]/form/div[4]/div[2]/input"));
    private final SelenideElement DodSendButton = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div/div[3]/div/div[2]/form/div[7]/button"));

    @Step("Методы для формы ДОД")
    // Методы вставки input значений полей и отправка
    public void inputDataDodMIRK() {
        $(byXpath("//*[text()='Регистрация на мероприятия']")).scrollTo();

        DodFieldSurname.setValue(DodMIRK.getSurname());
        DodFieldName.setValue(DodMIRK.getName());
        DodFieldTel.setValue(DodMIRK.getTel());
        DodFieldEmail.setValue(DodMIRK.getEmail());

        $$("select[name='d[4]'] option")
                .filter(Condition.text("День открытых дверей")) // Фиксированная часть текста
                .first() // Берем первый найденный элемент
                .click(); // Кликаем на него

        DodSendButton.click();
    }
}
