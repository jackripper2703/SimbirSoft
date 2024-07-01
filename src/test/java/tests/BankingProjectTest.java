package tests;

import core.BaseTest;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.MainPage;
import pages.TransactionPage;
import utils.TransactionWriter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        // Вход в профиль Harry Potter
        AccountPage accountPage = mainPage.choosingRoleCustomer().selectUserHarryPotter();
        // Баланс профиля Harry Potter должен быть 0
        assertEquals(accountPage.getBalance().getText(), "0");
        // Внесение средств на счет
        AccountPage accountPageDeposits = accountPage.amountDeposited(fibNumber);
        assertEquals(accountPageDeposits.getBalance().getText(), String.valueOf(fibNumber));
        // Вывод средств со счета
        AccountPage accountPageWithdraws = accountPageDeposits.amountWithdrawn(fibNumber);
        assertEquals(accountPageWithdraws.getBalance().getText(), "0");
        // Переход на страницу транзакций
        TransactionPage transactionPage = accountPage.goToTransactionsList();
        // Проверка наличия транзакций
        assertEquals(transactionPage.getFirstTransaction().getText(), "Credit");
        assertEquals(transactionPage.getThirdTransaction().getText(), "Debit");
        // Получаем список транзакций и конвертируем его в список массивов строк
        List<String[]> transactionsList = transactionPage.creatingListTransactions();
        // Записываем транзакции в CSV файл
        TransactionWriter.writeTransactionsToCSV(transactionsList, "transactions");
        // Добавляем CSV файл как вложение в отчет Allure
        attachCsvToAllureReport("transactions");
    }
}
