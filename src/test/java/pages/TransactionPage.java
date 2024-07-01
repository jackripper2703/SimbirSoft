package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TransactionPage extends BasePage {

    @FindBy(css = "tr[id*='anchor']")
    private List<WebElement> transactions;
    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(3)")
    private WebElement firstTransaction;
    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(3)")
    private WebElement thirdTransaction;

    private By dataTime = By.cssSelector("td:nth-child(1)");
    private By amount  = By.cssSelector("td:nth-child(2)");
    private By transactionType= By.cssSelector("td:nth-child(3)");

    public TransactionPage() {
        PageFactory.initElements(driver, this); // Инициализация элементов страницы
    }

    public WebElement getThirdTransaction() {
        return thirdTransaction;
    }

    public WebElement getFirstTransaction() {
        return firstTransaction;
    }

    @Step("Сбор всех транзакций в список")
    public List<String[]> creatingListTransactions() {
        List<String[]> listOfArrays = new ArrayList<>();
        if (transactions.isEmpty()) {
            return listOfArrays;
        }
        for (WebElement transaction : transactions) {
            String[] transactionArray  = new String[3];
            transactionArray[0] = transaction.findElement(dataTime).getText();
            transactionArray[1] = transaction.findElement(amount).getText();
            transactionArray[2] = transaction.findElement(transactionType).getText();
            listOfArrays.add(transactionArray);
        }
        return listOfArrays;
    }
}
