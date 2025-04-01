package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mirk.forms.ConsultForFreeMIRK;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ConsultForFreeMIRKHelper  {// проверяем формы заявок на главной странице

    //@Name("Форма Получите бесплатную консультацию специалиста  по поводу обучения")
    //кнопки на пути к форме
    private final SelenideElement ConsultForFreeMIRKTel = $(By.xpath("//form[@id='25478507_form_1']//div[@class='tpl-field type-phone first-inline alias_phone']//input[@name='d[0]' and @placeholder='Телефон']"));
    private final SelenideElement ConsultForFreeSubmitButton = $(By.xpath("//button[@data-form-id='25478507_form_1']"));

    @Step("Методы Получите бесплатную консультацию специалиста")
    // Методы вставки input значений полей и отправка
    public void inputDataConsultForFree() {
        $(byText("Получите бесплатную консультацию по поводу обучения")).scrollTo();

        ConsultForFreeMIRKTel.setValue(ConsultForFreeMIRK.getTel());

        ConsultForFreeSubmitButton.click();
      //  $(byText("г. Москва, ул. Кузнецкий мост, 21/5, под. 1, эт. 4, оф. 4002")).scrollTo();

    }
}
