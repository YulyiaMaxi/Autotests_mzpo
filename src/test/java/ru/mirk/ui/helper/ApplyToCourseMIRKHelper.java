package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.mirk.forms.ApplyToCourseMIRK;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static org.openqa.selenium.Keys.ESCAPE;

public class ApplyToCourseMIRKHelper  {// проверяем формы заявок на главной странице

    //@Name("Форма Записаться на курсы")

    private final SelenideElement ApplyToCourseButton = $("a.recall-btn.w-button[href='#folder_form2']");
    private final SelenideElement ApplyToCourseName =$(byXpath("//form[@id='25479307_form_6']//input[@name='d[0]']")) ;
    private final SelenideElement ApplyToCourseTel = $(byXpath("//form[@id='25479307_form_6']//input[@name='d[1]']")) ;
    private final SelenideElement ApplyToCourseEmail = $(byXpath("//form[@id='25479307_form_6']//input[@name='d[2]']"));
    private final SelenideElement ApplyToCourseSubmitButton = $(byXpath("//button[@id='25479307_btn_5']"));

    @Step("Методы для кнопки Записаться на курс")
    // Методы вставки input значений полей и отправка
    public void inputDataApplyToCourseMIRK() {

        $(byXpath("//*[text()='5 причин учиться именно в МИРК!']")).scrollIntoView(true);

        ApplyToCourseButton.click();

        ApplyToCourseName.shouldBe(visible, Duration.ofSeconds(15));
        ApplyToCourseName.setValue(ApplyToCourseMIRK.getName());
        ApplyToCourseTel.setValue(ApplyToCourseMIRK.getTel());
        ApplyToCourseEmail.setValue(ApplyToCourseMIRK.getEmail());

        ApplyToCourseSubmitButton.click();

        actions().sendKeys(ESCAPE).perform();

    }
}
