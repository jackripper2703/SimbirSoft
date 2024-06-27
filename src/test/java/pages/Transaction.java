package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Transaction extends BasePage {

    @FindBy(css = "tr[id*='anchor']")
    private List<WebElement> transactions;
    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(3)")
    private WebElement firstTransaction;
    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(3)")
    private WebElement thirdTransaction;

    public Transaction() {
        PageFactory.initElements(driver, this); // Инициализация элементов страницы
    }

    @Step("Проверка наличия транзакций")
    public Transaction checkTransactions() {
        assertEquals(firstTransaction.getText(), "Credit");
        assertEquals(thirdTransaction.getText(),  "Debit");
        return this;
    }

    @Step("Сбор всех транзакций в список")
    public List<WebElement> getTransactions() {
        return transactions;
    }
}
