package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mirk.forms.CartMIRK;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static org.openqa.selenium.Keys.ESCAPE;

public class CartMIRKHelper {// проверяем формы заявок на главной странице

   // @Name("Форма Корзина")

    private final SelenideElement GoToCartButton = $(By.xpath("//button[contains(@class, 'shop-product-btn') and contains(@class, 'buy')]"));
    private final SelenideElement CartIcon = $(By.xpath("//div[@class='cart-popup-btn__icon']"));
    private final SelenideElement CartOrderButton = $(By.xpath("//div[@class='cart-popup__btns']/a[@class='order-button']"));
    private final SelenideElement CartMirkFieldName = $(By.xpath("//input[@id='user_fio']"));
    private final SelenideElement CartMirkFieldTel = $(By.xpath("//input[@id='user_phone']"));
    private final SelenideElement CartMirkFieldEmail = $(By.xpath("//input[@id='user_email']"));
    private final SelenideElement CartMirkSubmitButton = $(By.xpath("//button[@id='shop2-order-options_btn_1']"));

    @Step("Методы для кнопки Корзина")
    // Методы вставки input значений полей и отправка
    public void inputDataCartMIRK() {

        $(byXpath("//*[text()='В корзину']")).scrollIntoView(true);
        GoToCartButton.click();
        CartIcon.shouldBe(visible, Duration.ofSeconds(5));
        CartIcon.click();
        CartOrderButton.click();

        $(byXpath("//*[text()='Оформление заказа']")).scrollIntoView(true);

        CartMirkFieldName.setValue(CartMIRK.getName());
        CartMirkFieldTel.setValue(CartMIRK.getTel());
        CartMirkFieldEmail.setValue(CartMIRK.getEmail());
        CartMirkSubmitButton.click();

        actions().sendKeys(ESCAPE).perform();

    }
}



