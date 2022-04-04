package ru.netology.domain.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.page.DashboardPage;
import ru.netology.domain.page.LoginPage;
import ru.netology.domain.page.TransferPage;
import ru.netology.domain.page.VerificationPage;

import static ru.netology.domain.data.DataHelper.getFirstCardInfo;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static TransferPage transferPage = new TransferPage();

    @Пусть("открыта страница с формой авторизации {string}")
    public void openAuthPage(String url) {
        loginPage = Selenide.open(url, LoginPage.class);
    }

    @Когда("пользователь пытается авторизоваться с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        verificationPage = loginPage.validLogin(login, password);
    }

    @И("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(String verificationCode) {
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Тогда("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
    public void verifyDashboardPage() {
        dashboardPage.verifyIsDashboardPage();
    }

    @Тогда("появляется ошибка о неверном коде проверки")
    public void verifyCodeIsInvalid() {
        verificationPage.verifyCodeIsInvalid();
    }

    @Тогда("осуществляется выбор карты {string}")
    public void selectCard(String card) {
        dashboardPage.selectCardToTransfer(card);
    }

    @Тогда("происходит перевод с карты на карту с суммой {string} и номером карты {string}")
    public void transfer(String amount, String cardNumber) {
        transferPage.makeTransfer(amount, cardNumber);
    }

}