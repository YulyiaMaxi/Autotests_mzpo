package ru.education.tests;

import lombok.Getter;
import org.testng.ITestContext;
import org.testng.TestNG;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.mirk.tests.MIRKTests;
import ru.mzpo.tests.MzpoTests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TestResultLogger implements ITestListener { // класс, где логируем записи исходя из статуса прохождения тестов
    private static final String FAILED_TESTS_LOG_FILE = "D:\\test\\failedtests.txt"; // путь к файлу, где хранятся записи об упавших тестах
    // Метод для получения списка упавших тестов
    private final List<String> failedTests = new ArrayList<>(); // Список для хранения упавших тестов

    @Override
    public void onStart(ITestContext iTestContext) {
        // Очищаем файл перед началом тестов
        try {
            Files.write(Paths.get(FAILED_TESTS_LOG_FILE), new byte[0]);
        } catch (IOException e) {
            System.err.println("Ошибка при очистке файла: " + e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult result) { // метод получения записи об упавшем тесте и добавления ее в список
        String timestamp = getCurrentTimestamp(); // Получаем текущую дату и время
        String message = String.format("%s - Test %s failed.\n", timestamp, result.getName()); // формат записи об упавшем тесте
        failedTests.add(message); // Добавляем сообщение в массив упавших тестов
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        // Записываем информацию об упавших тестах в файл после завершения всех тестов
        if (!failedTests.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FAILED_TESTS_LOG_FILE, true))) {
                for (String message : failedTests) {
                    writer.write(message);
                }
            } catch (IOException e) {
                // Обработка ошибки записи в файл
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }
    }

    // Метод для получения текущей даты и времени
    private String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public static void getClasses() {
        TestNG testng = new TestNG();

        // Установите массив тестовых классов (замените на ваши классы тестов)
        Class[] testClasses;
        testClasses = new Class[]{EducationTests.class, MIRKTests.class, MzpoTests.class};
        testng.setTestClasses(testClasses);

        // Добавьте слушателя для логирования результатов
        testng.addListener(new TestResultLogger());

        // Запустите тесты
        testng.run();
    }
}









