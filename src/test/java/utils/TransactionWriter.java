package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionWriter {

    // Форматтер для парсинга и форматирования даты и времени
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");

    /**
     * Метод для записи транзакций в CSV файл.
     *
     * @param transactions список веб-элементов, представляющих транзакции
     * @param fileName     имя файла CSV, в который будут записаны транзакции
     */
    public static void writeTransactionsToCSV(List<WebElement> transactions, String fileName) {
        // Формирование имени CSV файла
        String csvFile = fileName + ".csv";

        try (Writer writer = new FileWriter(csvFile)) {
            // Итерация по списку транзакций
            for (WebElement transaction : transactions) {
                WebElement dateTimeElement = transaction.findElement(By.cssSelector("td:nth-child(1)"));
                WebElement amountElement = transaction.findElement(By.cssSelector("td:nth-child(2)"));
                WebElement transactionTypeElement = transaction.findElement(By.cssSelector("td:nth-child(3)"));

                String dateTime = dateTimeElement.getText();
                String amount = amountElement.getText();
                String transactionType = transactionTypeElement.getText();

                // Формирование строки CSV
                String csvLine = String.format("%s,%s\n", amount, transactionType);
                writer.write(csvLine); // Запись строки в файл
            }

            System.out.println("CSV файл успешно создан: " + csvFile);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в CSV файл: " + e.getMessage());
        }
    }

    /**
     * Метод для форматирования даты и времени в определенный формат.
     *
     * @param dateTime строка с датой и временем в формате "MMM dd, yyyy hh:mm:ss a"
     * @return отформатированная строка даты и времени в формате "dd MMMM yyyy HH:mm:ss"
     */
    private static String formatDateTime(String dateTime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        return parsedDateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss"));
    }

    /**
     * Метод для добавления CSV файла в отчет Allure.
     *
     * @param fileName имя CSV файла без расширения
     */
    public static void attachCsvToAllureReport(String fileName) {
        // Формирование пути к файлу CSV
        String filePath = System.getProperty("user.dir") + "/" + fileName + ".csv";
        // Добавление файла в отчет Allure как вложение
        Allure.addAttachment("Транзакции", "text/csv", filePath);
    }
}
