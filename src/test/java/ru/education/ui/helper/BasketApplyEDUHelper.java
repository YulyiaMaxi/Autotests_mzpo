package ru.education.ui.helper;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import ru.education.forms.BasketEDU;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class BasketApplyEDUHelper  {// проверяем формы заявок на главной странице

  //  @Name("Форма Корзина Оплатить")
    private final SelenideElement AddToBasketEDUButton = $(".btn.btn-success.jsAddToCart.jbprice-buttons-add");
    private final SelenideElement GoToBasketEDUButton = $(".btn.btn-success.jsPriceButton.jsGoTo.jbprice-buttons-goto");
    private final SelenideElement BasketEDUFieldSurname = $("#jbcart-a14c47f6-0f5f-437d-966b-abf7e218d356");
    private final SelenideElement BasketEDUFieldName = $("#jbcart-19554833-9521-482b-802d-fa5697c45945");
    private final SelenideElement BasketEDUFieldPatronimic = $("#jbcart-107ab8b7-d52c-4014-b44a-8e457ac3f814");
    private final SelenideElement BasketEDUFieldTel = $("#jbcart-94831247-edd3-45b4-82ca-45b77d8c2c71");
    private final SelenideElement BasketEDUFieldEmail = $("#jbcart-993b1b96-ad82-4a9d-b771-baa8847cc4fd");
    private final SelenideElement CheckBox = $(".ownerFormCart input[type ='checkbox']");
    private final SelenideElement BasketEDUSubmitButton = $("#yoo-zoo > div.ownerFormCart > div > div > div > form > div > div.col-xl-5.col-lg-5.col-md-10.col-sm-12.col-12.colShipping > div.jbform-actions > div > div:nth-child(2) > input"); // кнопка Создать заказ

    @SneakyThrows
    public void inputDataBasketEDU() {
        $$(byXpath("//*[text()='Подробнее ']")).first().scrollIntoView(true);
        AddToBasketEDUButton.click();
        GoToBasketEDUButton.shouldBe(visible, Duration.ofSeconds(10));
        GoToBasketEDUButton.click();

        $(byXpath("//*[text()='Тренер по футболу - 288 часов']")).scrollIntoView(true);
        $(byXpath("//*[text()='Тренер по футболу - 288 часов']")).shouldBe(visible, Duration.ofSeconds(15));

        $(byXpath("//*[text()='Заполните форму ниже чтобы оформить заказ']")).scrollIntoView(true);
        $(byXpath("//*[text()='Заполните форму ниже чтобы оформить заказ']")).shouldBe(visible, Duration.ofSeconds(15));
        BasketEDUFieldSurname.setValue(BasketEDU.getSurname());
        BasketEDUFieldName.setValue(BasketEDU.getName1());
        BasketEDUFieldPatronimic.setValue(BasketEDU.getPatronimic());

        BasketEDUFieldTel.click();
        for (int i = 0; i < 14; i++) { //
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
        actions().sendKeys("3").perform();
        actions().sendKeys("1").perform();

        BasketEDUFieldEmail.setValue(BasketEDU.getEmail());
        CheckBox.click();
        BasketEDUSubmitButton.click();

    }
}
