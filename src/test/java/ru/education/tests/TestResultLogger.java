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
public class TestResultLogger implements ITestListener {

    private static final String FAILED_TESTS_LOG_FILE = "D:/test/failedtests.txt"; // путь к файлу, где хранятся записи об упавших тестах
    private final List<String> failedTests = new ArrayList<>(); // Список для хранения упавших тестов
    private final List<String> skippedTests = new ArrayList<>(); // Список для хранения игнорируемых тестов

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
        // Можно добавить логику при запуске теста, если необходимо
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        // Можно добавить логику при успешном выполнении теста, если необходимо
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String timestamp = getCurrentTimestamp(); // Получаем текущую дату и время
        String message = String.format("%s - Test %s failed.\n", timestamp, result.getName()); // формат записи об упавшем тесте
        failedTests.add(message); // Добавляем сообщение в массив упавших тестов
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String timestamp = getCurrentTimestamp(); // Получаем текущую дату и время
        String message = String.format("%s - Test %s was skipped.\n", timestamp, iTestResult.getName()); // формат записи об игнорируемом тесте
        skippedTests.add(message); // Добавляем сообщение в массив игнорируемых тестов
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // Можно добавить логику для обработки тестов, которые не прошли, но находятся в пределах процента успешности
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        // Записываем информацию об упавших и игнорируемых тестах в файл после завершения всех тестов
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FAILED_TESTS_LOG_FILE, true))) {
            if (!failedTests.isEmpty()) {
                for (String message : failedTests) {
                    writer.write(message);
                }
            }
            if (!skippedTests.isEmpty()) {
                for (String message : skippedTests) {
                    writer.write(message);
                }
            }
        } catch (IOException e) {
            // Обработка ошибки записи в файл
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
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










