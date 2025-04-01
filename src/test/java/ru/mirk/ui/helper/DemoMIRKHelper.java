package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mirk.forms.DemoMirk;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DemoMIRKHelper {// проверяем формы заявок на главной странице

   // @Name("Форма Скидка 15% - Демо ЛК")

    private final SelenideElement DemoMIRKFieldName= $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div[1]/div/div[2]/div[2]/div/div/form/div[1]/div/input"));
    private final SelenideElement DemoMIRKFieldTel= $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div[1]/div/div[2]/div[2]/div/div/form/div[2]/div/input"));

    private final SelenideElement DemoMIRKFieldEmail= $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div[1]/div/div[2]/div[2]/div/div/form/div[3]/div/input"));
    private final SelenideElement DemoMIRKRadioMassage = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[1]/span"));;
    private final SelenideElement DemoMIRKRadioInGroup= $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div[1]/div/div[1]/div[2]/div[2]/div[2]/div[1]/span"));
    private final SelenideElement DemoMIRKRadioYes = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div[1]/div/div[1]/div[2]/div[3]/div[2]/div[1]/span"));
    private final SelenideElement DemoMIRKSubmitButton = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div[1]/div/div[2]/div[2]/div/div/form/div[7]/button"));

    @Step("Методы для кнопки Обратный звонок")
    // Методы вставки input значений полей и отправка
    public void inputDataDemoMIRK() {

        $(byText("Забронируй скидку до 15%")).scrollIntoView(true);

        DemoMIRKFieldName.setValue(DemoMirk.getName());
        DemoMIRKFieldTel.click();

        DemoMIRKFieldTel.setValue(DemoMirk.getTel());
        DemoMIRKFieldEmail.setValue(DemoMirk.getEmail());
        DemoMIRKRadioMassage.click();
        DemoMIRKRadioInGroup.click();
        DemoMIRKRadioYes.click();
        DemoMIRKSubmitButton.click();

    }
}
