package ru.netology.domain.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");


    public TransferPage selectCardToTransfer(String cardInfo) {
        cards.findBy(text(cardInfo.substring(12, 16))).$("button").click();
        return new TransferPage();
    }

    public void verifyIsDashboardPage() {
        heading.shouldBe(visible);
    }

}
