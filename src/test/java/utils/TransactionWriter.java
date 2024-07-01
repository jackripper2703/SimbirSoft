package utils;

import io.qameta.allure.Allure;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TransactionWriter {

    // Форматтер для парсинга и форматирования даты и времени
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a", Locale.ENGLISH);
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss", Locale.forLanguageTag("ru-RU"));

    /**
     * Метод для записи транзакций в CSV файл.
     *
     * @param transactions список веб-элементов, представляющих транзакции
     * @param fileName     имя файла CSV, в который будут записаны транзакции
     */
    public static void writeTransactionsToCSV(List<String[]> transactions, String fileName) {
        // Формирование имени CSV файла
        String csvFile = fileName + ".csv";

        try (Writer writer = new FileWriter(csvFile)) {
            // Запись заголовка CSV файла
            writer.write("Дата и время,Сумма,Тип транзакции\n");

            // Итерация по списку транзакций
            for (String[] transaction : transactions) {

                // Форматирование даты и времени
                String formattedDateTime = formatDateTime(transaction[0]);

                // Формирование строки CSV
                String csvLine = String.format("%s,%s,%s\n", formattedDateTime, transaction[1], transaction[2]);
                writer.write(csvLine); // Запись строки в файл
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в CSV файл: " + e.getMessage());
        }
    }

    /**
     * Метод для форматирования даты и времени в определенный формат.
     *
     * @param dateTime строка с датой и временем в формате "MMM d, yyyy h:mm:ss a"
     * @return отформатированная строка даты и времени в формате "dd MMMM yyyy HH:mm:ss"
     */
    public static String formatDateTime(String dateTime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime, INPUT_FORMATTER);
        return parsedDateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Метод для добавления CSV файла в отчет Allure.
     *
     * @param fileName имя CSV файла без расширения
     */
    public static void attachCsvToAllureReport(String fileName) {
        try {
            // Формирование пути к файлу CSV
            String filePath = System.getProperty("user.dir") + "/" + fileName + ".csv";
            // Чтение содержимого файла
            String csvContent = new String(Files.readAllBytes(Paths.get(filePath)));
            // Добавление содержимого файла в отчет Allure как вложение
            Allure.addAttachment("Транзакции", "text/csv", csvContent);
        } catch (IOException e) {
            System.err.println("Ошибка при добавлении CSV файла в отчет Allure: " + e.getMessage());
        }
    }
}
