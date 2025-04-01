package ru.mzpokurs.ui.helper;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.mzpokurs.forms.DOD_KURS;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ESCAPE;

public class Dod_KURS_Helper {

    private final SelenideElement Dod_KURS_Name = $("[data-test-id = openday_input_name]");
    private final SelenideElement Dod_KURS_Tel = $("[data-test-id = openday_input_phone]");
    private final SelenideElement Dod_KURS_Email = $("[data-test-id = openday_input_email]");
    private final SelenideElement Dod_KURS_SubmitButton = $("[data-test-id = openday_submit_button]");

        @Step("Методы для кнопки Обратный звонок")

        public void inputDataDod_KURS() {

            $("[data-test-id = openday_input_name]").scrollIntoView(true);
            $("[data-test-id = openday_input_name]").shouldBe(visible, Duration.ofSeconds(5));

            Dod_KURS_Name.setValue(DOD_KURS.getName());
            Dod_KURS_Tel.setValue(DOD_KURS.getTel());
            Dod_KURS_Email.setValue(DOD_KURS.getEmail());

            Dod_KURS_SubmitButton.hover();
            Dod_KURS_SubmitButton.click();

            actions().sendKeys(ESCAPE).perform();
        }
}
