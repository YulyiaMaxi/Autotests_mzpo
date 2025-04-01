package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mirk.forms.NeedConsultMIRK;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static org.openqa.selenium.Keys.ESCAPE;

public class NeedConsultMIRKHelper {// проверяем формы заявок на главной странице

    //@Name("Форма Нужна консультация")

    private final SelenideElement NeedConsultButton = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[4]/div/div[2]/div[2]/div/a"));

    private final SelenideElement NeedConsultName = $(By.xpath("//form[@id='25478307_form_5']//input[@name='d[0]' and @placeholder='Ваше имя, из какого Вы города']"));
    private final SelenideElement NeedConsultTel = $(By.xpath("//form[@id='25478307_form_5']//input[@name='d[2]' and @placeholder='Телефон *']"));
    private final SelenideElement NeedConsultEmail = $(By.xpath("//form[@id='25478307_form_5']//input[@name='d[3]' and @placeholder='E-mail']"));

    private final SelenideElement NeedConsultSubmitButton = $(By.xpath("//button[@id='25478307_btn_4' and text()='Отправить']"));

    @Step("Методы для кнопки Нужна консультация")
    // Методы вставки input значений полей и отправка
    public void inputDataNeedConsultMIRK() {

        $(byText("Нужна консультация")).scrollTo();

        NeedConsultButton.click();
        //заполняем поля
        NeedConsultName.setValue(NeedConsultMIRK.getName());
        NeedConsultTel.setValue(NeedConsultMIRK.getTel());
        NeedConsultEmail.setValue(NeedConsultMIRK.getEmail());
        NeedConsultSubmitButton.click();
        actions().sendKeys(ESCAPE).perform();
           }
}