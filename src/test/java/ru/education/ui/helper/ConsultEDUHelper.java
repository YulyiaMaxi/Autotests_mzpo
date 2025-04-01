package ru.education.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import ru.education.forms.ConsultEDU;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class ConsultEDUHelper {
// проверяем формы заявок на главной странице

  //  @Name("Кнопка Консультация - форма Получить информацию")

    private final SelenideElement ConsultEDUFieldName = $("#accreditation input[name ='name']"); //$("[data-tests=nmoName]");
    private final SelenideElement ConsultEDUTelField = $("#accreditation input[type='tel']");
    private final SelenideElement CheckBox = $("#accreditation .form-check-input.m-0.policyCheckbox");
    private final SelenideElement ConsultSubmitButton = $("#accreditation input[type ='submit']");

    private final SelenideElement ConsultEDUSuccessWindow = $("#LeadAnswer > div > div > div.modal-body.text-center.answer-modal > div");
    private final SelenideElement ConsultEDUCloseModalWindow = $("#LeadAnswer > div > div > div.modal-header.border-0.justify-content-end > button");
   // private final SelenideElement ScrollElement = $(byXpath("//*[text()='Оценка портфолио:']"));

    @SneakyThrows
    @Step("Консультация")

    public  void inputDataConsultEDU() {

        //$(byText("Кому необходима:")).scrollTo();
       // $(byText("Оценка портфолио:")).scrollTo();
       // $(byText("Оценка портфолио:")).shouldBe(visible, Duration.ofSeconds(15));
        //executeScript("arguments[0].scrollIntoView();", ScrollElement);

       /* $(byXpath("//*[text()='Подготовка включает в себя:']")).shouldBe(visible, Duration.ofSeconds(15));

        $(byXpath("//*[text()='Подготовка включает в себя:']")).scrollIntoView(true);

        $(byXpath("//*[text()='Кому необходима:']")).shouldBe(visible, Duration.ofSeconds(15));

        $(byXpath("//*[text()='Кому необходима:']")).scrollIntoView(true);
        $(byXpath("//*[text()='Всего нужно набрать ']")).shouldBe(visible, Duration.ofSeconds(15));

        $(byXpath("//*[text()='Всего нужно набрать ']")).scrollIntoView(true);*/
        $(byXpath("//*[text()='Бесплатно проконсультируем по всем вопросам периодической аккредитации']")).scrollIntoView(true);
        $(byXpath("//*[text()='Бесплатно проконсультируем по всем вопросам периодической аккредитации']")).shouldBe(visible, Duration.ofSeconds(15));
        Thread.sleep(2000);

       // $(byXpath("//*[text()='Оценка портфолио:']").scrollTo();

       // $("#my-button").scrollIntoView(true);
       // ScrollElement.scrollIntoCenter();)

        ConsultEDUTelField.click();
        for (int i = 0; i < 14; i++) { // 14 раз
            actions().sendKeys("BACK_SPACE").perform();
        }

        actions().sendKeys("1").perform();
        actions().sendKeys("3").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("0").perform();
        actions().sendKeys("7").perform();
        actions().sendKeys("2").perform();

        //ConsultEDUTelField.setValue(ConsultEDU.getTel());
        ConsultEDUFieldName.setValue(ConsultEDU.getName());
        CheckBox.click();

        // отправляем форму
        ConsultSubmitButton.click();
    }
        // проверяем сообщение об отправке
        public  void getModalWindowConsultEDU() {
            ConsultEDUSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
            ConsultEDUSuccessWindow.hover();
            ConsultEDUCloseModalWindow.click();

        }
    }

