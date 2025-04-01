package ru.mirk.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mirk.forms.Write2UsMIRK;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static org.openqa.selenium.Keys.ESCAPE;

public class Write2UsMIRKHelper {

    //@Name("Форма Напишите нам в Контактах")

    private final SelenideElement Write2UsMIRKName = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div/form/div[1]/div[2]/input"));                                  //"//form[@data-s3-anketa-id='25478107']//label[text()='Ваше имя']/following-sibling::div//input[@name='d[0]']"));
    private final SelenideElement Write2UsMIRKTel = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div/form/div[2]/div[2]/input"));   //form[@data-s3-anketa-id='25478107']//label[text()='Телефон']/following-sibling::div//input[@name='d[1]']"));
    private final SelenideElement Write2UsMIRKEmail = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div/form/div[3]/div[2]/input"));//form[@data-s3-anketa-id='25478107']//label[text()='E-mail']/following-sibling::div//input[@name='d[2]']"));
    private final SelenideElement Write2UsMIRKSubmitButton = $(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[3]/div/form/div[6]/input"));
            //("//input[@id='25478107_btn_1' and @value='Отправить']"));

    @Step("Напишите нам в Контактах")
    public void inputDataWrite2UsMIRK() {
         $(byXpath("//*[text()='Напишите нам']")).scrollIntoView(true);
        //$(byXpath("//*[text()='Ваше имя']")).scrollIntoView(true);
        //Write2UsMIRKName.shouldBe(visible,Duration.ofSeconds(20));
        Write2UsMIRKName.setValue(Write2UsMIRK.getName());
        Write2UsMIRKTel.setValue(Write2UsMIRK.getTel());
        Write2UsMIRKEmail.setValue(Write2UsMIRK.getEmail());
        Write2UsMIRKSubmitButton.click();

        actions().sendKeys(ESCAPE).perform();

           }
}
