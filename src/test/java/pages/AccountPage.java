package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountPage extends BasePage {

    @FindBy(css = ".center[ng-hide='noAccount'] .ng-binding:nth-child(2)")
    private WebElement balance;
    @FindBy(css = "[ng-show='message']")
    private WebElement message;

    @FindBy(css = "[ng-click='deposit()']")
    private WebElement depositButton;
    @FindBy(css = "[ng-submit='deposit()'] input")
    private WebElement depositInput;
    @FindBy(css = "[ng-submit='deposit()'] [type='submit']")
    private WebElement depositSubmit;

    @FindBy(css = "[ng-click='withdrawl()']")
    private WebElement withdrawlButton;
    @FindBy(css = "[ng-submit='withdrawl()'] input")
    private WebElement withdrawlInput;
    @FindBy(css = "[ng-submit='withdrawl()']  [type='submit']")
    private WebElement withdrawlSubmit;

    @FindBy(css = "[ng-click='transactions()']")
    private WebElement transactionsButton;

    public AccountPage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Пополнить баланса на {amount}")
    public AccountPage amountDeposited(long amount) {
        assertEquals(balance.getText(), "0", "Баланс не равен нулю!");
        depositButton.click();
        depositInput.sendKeys(String.valueOf(amount));
        depositSubmit.click();
        assertEquals(message.getText(), "Deposit Successful", "Проблема в сообщении успешного пополнения!");
        assertEquals(balance.getText(), String.valueOf(amount), "Сумма кошелка неверная после пополнения!");
        return this;
    }

    @Step("Списание с баланса на  {amount}")
    public AccountPage amountWithdrawn(long amount) {

        withdrawlButton.click();
        withdrawlInput.sendKeys(String.valueOf(amount));
        withdrawlSubmit.click();
        assertEquals(message.getText(), "Transaction successful", "Проблема в сообщении успешного списания!");
        assertEquals(balance.getText(), "0", "Баланс не равен нулю!");
        return this;
    }

    @Step("Переход к списку транзакций на балансе")
    public Transaction goToTransactionsList() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(2));
        driver.navigate().refresh();
        transactionsButton.click();
        return new Transaction();
    }
}