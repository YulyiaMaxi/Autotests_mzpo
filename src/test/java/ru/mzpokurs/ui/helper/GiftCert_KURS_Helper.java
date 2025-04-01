package ru.mzpokurs.ui.helper;

import com.codeborne.selenide.SelenideElement;
import ru.mzpo.forms.GiftCert;
import ru.mzpokurs.forms.Discount15_KURS;
import ru.mzpokurs.forms.GiftCert_KURS;

import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ESCAPE;

public class GiftCert_KURS_Helper {

        private final SelenideElement GiftCert_KURS_Name = $("[data-test-id = /////////////]");
        private final SelenideElement GiftCert_KURS_Tel = $("[data-test-id = //////////////]");
        private final SelenideElement GiftCert_KURS_Email = $("[data-test-id = //////////////]");

        private final SelenideElement GiftCert_KURS_SubmitButton = $("[data-test-id = ///////////////]");

        private final SelenideElement GiftCert_KURS_Success = $("[data-test-id = ////////////]");

        public void inputGiftCert_KURS() {

            $("[data-test-id = /////////////]").scrollIntoView(true);
            $("[data-test-id = /////////////////]").shouldBe(visible, Duration.ofSeconds(5));

            GiftCert_KURS_Name.setValue(GiftCert_KURS.getName());
            GiftCert_KURS_Tel.setValue(GiftCert.getTel());
            GiftCert_KURS_Email.setValue(GiftCert_KURS.getEmail());
            // тесты проходят и без выбора номинала

            // CallBack_KURS_SubmitButton.hover();
            GiftCert_KURS_SubmitButton.click();

            GiftCert_KURS_Success.shouldBe(visible);
            actions().sendKeys(ESCAPE).perform();

        }


}
