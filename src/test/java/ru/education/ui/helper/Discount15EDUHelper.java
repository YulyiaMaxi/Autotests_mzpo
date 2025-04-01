package ru.education.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import ru.education.forms.Discount15EDU;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;


public class Discount15EDUHelper  {// проверяем формы заявок на главной странице

  //  @Name("Форма Забронируй скидку 15%")
    //кнопки на пути к форме
    private final SelenideElement Discount15EDUMenuTabAboutUs = $("#maximenuck140 > div.maxiroundedcenter > ul > li.maximenuck.item109.parent.level1.noBefore.maximenuckanimation > a > span");
    private final SelenideElement Discount15EDUMenuTabActions = $("#maximenuck140 > div.maxiroundedcenter > ul > li.maximenuck.item109.parent.level1.noBefore.maximenuckanimation > div > div.maxidrop-main > div > div.maximenuck2.first > ul > li.maximenuck.nodropdown.item315.level2 > a > span");
    private final SelenideElement Discount15EDUPoster = $("#yoo-zoo > div > div > div > div > div > div > div > div > a:nth-child(1) > img");
   //поля формы
  //  @Name("Селенид-значение полей формы Забронируй скидку 15%")
    private final SelenideElement Discount15EDUFieldName = $("#promo > input[type=text]:nth-child(3)");
    private final SelenideElement Discount15EDUTelField = $("#promo > input[type=text]:nth-child(4)"); // ищем по тестовой метке
    private final SelenideElement CheckBox = $("#promo .form-check-input.m-0.policyCheckbox");
    private final SelenideElement Discount15SubmitButton = $("#promo > input.btn.btn-red.btn-lg.mb-0");
    //окно успешной отправки
    private final SelenideElement Discount15EDUSuccessWindow = $("#LeadAnswer > div > div > div.modal-body.text-center.answer-modal");
    private final SelenideElement Discount15EDUCloseModalWindow = $("#LeadAnswer > div > div > div.modal-header.border-0.justify-content-end > button");
    public boolean isModalWindowDiscount15EDUOpen;

    @SneakyThrows
    @Step("Методы для кнопки Заказать обратный звонок")
    // Методы вставки input значений полей и отправка
    public void inputDataDiscount15EDU() {

        $(byXpath("//*[text()='Оставить заявку']")).scrollIntoView(true);
        Thread.sleep(2000);
        Discount15EDUFieldName.setValue(Discount15EDU.getName());
        Discount15EDUTelField.click();
        Discount15EDUTelField.setValue(Discount15EDU.getTel());
        CheckBox.click();
        // отправляем форму
        Discount15SubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowDiscount15EDU() {
        Discount15EDUSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        Discount15EDUCloseModalWindow.click();
    }
    public boolean isModalWindowDiscount15EDUOpen() {
        // Проверяем, что модальное окно видно на странице
        return Discount15EDUSuccessWindow.isDisplayed();
    }
}
