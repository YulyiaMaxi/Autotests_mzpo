package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mirk.forms.GiftCertMIRK;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static org.openqa.selenium.Keys.ESCAPE;

public class GiftCertMIRKHelper {// проверяем формы заявок на главной странице

 //   @Name("Форма Подарочный сертификат - Обратная связь")

    private final SelenideElement GiftCertMIRKFeedBackButton = $(By.xpath("//span[@class='one-click-btn' and contains(@onclick, 'id: 3529564')]"));//локатор не трогать!!!
    private final SelenideElement GiftCertMIRKName = $(By.xpath("//form[@data-s3-anketa-id='25477307']//input[@placeholder='Имя']"));

    private final SelenideElement GiftCertMIRKTel= $(By.xpath("//form[@data-s3-anketa-id='25477307']//input[@placeholder='Телефон *']"));
    private final SelenideElement GiftCertMIRKSubmitButton = $(By.xpath("//form[@data-s3-anketa-id='25477307']//button[@type='submit']")); // хорошие локаторы не срабатывают

    @Step("Методы для кнопки Подарочный сертификат - Обратная связь")
    // Методы вставки input значений полей и отправка// хорошие локато
    public void inputDataGiftCertMIRK() {
        $(byXpath("//*[text()='Подари сертификат на обучение близким!']")).scrollIntoView(true);
        GiftCertMIRKFeedBackButton.shouldBe(visible,Duration.ofSeconds(20));
        GiftCertMIRKFeedBackButton.click();

       GiftCertMIRKName.shouldBe(visible,Duration.ofSeconds(20));
        GiftCertMIRKName.setValue(GiftCertMIRK.getName());
        GiftCertMIRKTel.setValue(GiftCertMIRK.getTel());
        GiftCertMIRKSubmitButton.click();

        actions().sendKeys(ESCAPE).perform();
         }
}


