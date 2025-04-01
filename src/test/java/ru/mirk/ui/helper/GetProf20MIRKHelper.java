package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mirk.forms.GetProf20MIRK;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ESCAPE;

public class GetProf20MIRKHelper  {

   // @Name("Форма Получи профессию со скидкой 20%")
   private final SelenideElement GetProf20SubmitButton = $(byXpath("//a[contains(@class, 'decor-frm-btn')]"));
    private final SelenideElement GetProf20MIRKName =$(byXpath("//form[@class='gr-form -visor-no-click' and @data-s3-anketa-id='25474307']//input[@name='d[0]']"));;
    private final SelenideElement GetProf20MIRKTel = $(byXpath("//form[@class='gr-form -visor-no-click' and @data-s3-anketa-id='25474307']//input[@name='d[1]']"));
    private final SelenideElement GetProf20MIRKSendButton = $(byXpath("//form[@class='gr-form -visor-no-click' and @data-s3-anketa-id='25474307']//button[@type='submit' or text()='Отправить']"));

    @Step("Методы для Акции Получи профессию со скидкой 20%")

    public void inputDataGetProf20MIRK() {

     //   $(byXpath("//div[@id='s3-cookie-message__btn' and contains(@class, 'g-button')]")).click();

        $(byXpath("//div[contains(@class, 'keymoments__title') and contains(text(), 'Получи профессию')]")).scrollIntoView(true);
        $(byXpath("//div[contains(@class, 'keymoments__title') and contains(text(), 'Получи профессию')]")).shouldBe(visible, Duration.ofSeconds(15));

      //  $(byXpath("//a[@class='11 decor-frm-btn' and span[text()='Записаться']]")).scrollIntoView(true);
      //  $(byXpath("//a[@class='11 decor-frm-btn' and span[text()='Записаться']]")).shouldBe(visible, Duration.ofSeconds(15));

        GetProf20SubmitButton.click();
//      GetProf20MIRKName.shouldBe(visible, Duration.ofSeconds(30));
        GetProf20MIRKName.setValue(GetProf20MIRK.getName());
        GetProf20MIRKTel.setValue(GetProf20MIRK.getTel());
        GetProf20MIRKSendButton.click();
        actions().sendKeys(ESCAPE).perform();

    }
}
