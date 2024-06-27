package tests;

import core.BaseTest;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import utils.TransactionWriter;

import java.util.List;

import static utils.Fibonacci.fibonacci;
import static utils.TransactionWriter.attachCsvToAllureReport;

public class BankingProjectTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Issue("https://github.com/jackripper2703/SimbirSoft")
    @DisplayName("[POSITIVE] Проверка ведения списка транзакций")
    public void startTest() throws InterruptedException {
        MainPage mainPage = new MainPage();

        long fibNumber = fibonacci(); // Вычисляем число Фибоначчи

        List<WebElement> transactionsList = mainPage
                .choosingRoleCustomer()
                .selectUserHarryPotter()
                .amountDeposited(fibNumber)
                .amountWithdrawn(fibNumber)
                .goToTransactionsList()
                .checkTransactions()
                .getTransactions();

        // Записываем транзакции в CSV файл
        TransactionWriter.writeTransactionsToCSV(transactionsList, "transactions");

        // Добавляем CSV файл как вложение в отчет Allure
        attachCsvToAllureReport("transactions");
    }
}
