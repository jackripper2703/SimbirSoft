package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

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

    public WebElement getThirdTransaction() {
        return thirdTransaction;
    }

    public WebElement getFirstTransaction() {
        return firstTransaction;
    }

    @Step("Сбор всех транзакций в список")
    public List<String[]> creatingListTransactions() {
        List<String[]> listOfArrays = new ArrayList<>();
        for (WebElement transaction : transactions) {
            String[] transactionArray  = new String[3];
            transactionArray[0] = transaction.findElement(By.cssSelector("td:nth-child(1)")).getText();
            transactionArray[1] = transaction.findElement(By.cssSelector("td:nth-child(2)")).getText();
            transactionArray[2] = transaction.findElement(By.cssSelector("td:nth-child(3)")).getText();
            listOfArrays.add(transactionArray);
        }
        return listOfArrays;
    }
}
