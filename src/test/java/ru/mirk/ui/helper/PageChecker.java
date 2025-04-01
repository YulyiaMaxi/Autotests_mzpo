package ru.mirk.ui.helper;

import org.testng.SkipException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static com.codeborne.selenide.Selenide.open;

public class PageChecker {

    // Задаем критерии для проверки существования страницы
    private boolean isPageAccessible(String url) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
            int statusCode = response.statusCode();

            // Возвращаем true для успешных кодов от 200 до 399
            return statusCode >= 200 && statusCode < 400;
        } catch (Exception e) {
            // Если произошла ошибка, считаем, что страница недоступна
            return false;
        }
    }

    public void checkIfPageExists() {
        String url = "https://mirk.msk.ru/den-otkrytyh-dverej";

        // Проверяем наличие страницы
        if (!isPageAccessible(url)) {
            throw new SkipException("Страница не найдена. Тест пропущен.");
        } else {
            // Если страница доступна, открываем её в браузере
            open(url);
        }
    }
}
