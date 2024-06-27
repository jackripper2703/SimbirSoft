package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Transaction extends BasePage {

    @FindBy(css = "tr[id*='anchor']")
    private List<WebElement> transactions;

    public Transaction() {
        PageFactory.initElements(driver, this); // Инициализация элементов страницы
    }

    @Step("Сбор всех транзакций в список")
    public List<WebElement> getTransactions() {
        return transactions;
    }
}
