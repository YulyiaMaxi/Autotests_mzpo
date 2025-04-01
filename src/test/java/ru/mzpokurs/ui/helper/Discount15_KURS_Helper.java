package ru.mzpokurs.ui.helper;

import com.codeborne.selenide.SelenideElement;
import ru.mzpokurs.forms.CallBack_KURS;
import ru.mzpokurs.forms.Discount15_KURS;

import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

    public class Discount15_KURS_Helper {

        private final SelenideElement Discount15_KURS_Name = $("[data-test-id = discount_15_input_name]");
        private final SelenideElement Discount15_KURS_Tel = $("[data-test-id = discount_15_input_phone]");
        private final SelenideElement Discount15_KURS_SubmitButton = $("[data-test-id = discount_15_submit_button]");

        private final SelenideElement Discount15_KURS_Success = $("[data-test-id = discount_15_success_sent]");

        public void inputDiscount15_KURS() {


            $("[data-test-id = discount_15_input_name]").scrollIntoView(true);
            $("[data-test-id = discount_15_input_name]").shouldBe(visible, Duration.ofSeconds(5));

            Discount15_KURS_Name.setValue(Discount15_KURS.getName());
            Discount15_KURS_Tel.setValue(Discount15_KURS.getTel());
           // CallBack_KURS_SubmitButton.hover();
            Discount15_KURS_SubmitButton.click();

            Discount15_KURS_Success.shouldBe(visible);

        }


}
