package ru.mzpo.tests.api;

import io.restassured.path.json.JsonPath;
import static ru.mzpo.SendingHttpPost.sendToAmo;

public class Check_API_TrialLesson {
    public static void checkResponse() {

        StringBuilder response = sendToAmo("{\"name\":\"Supertester_TrialLesson_MZPO\"}");
        String responseString = response.toString(); // Преобразуем StringBuilder в String
        System.out.println("Response: " + responseString); // Выводим ответ для отладки
        int status = JsonPath.from(responseString).getInt("status");

        // Проверка статуса теста
        if (status == 0) {
            throw new AssertionError("Тест Supertester_TrialLesson_MZPO упал: статус " + status); // Если статус 0, выбрасываем AssertionError
        } else if (status == 1 || status == 2) {
            // Тест считается пройденным при статусах 1 и 2
            System.out.println("Тест Supertester_TrialLesson_MZPO прошел успешно: статус " + status);
            // Продолжайте выполнение кода для пройденного теста
        } else {
            System.out.println("Получен неожиданный статус: " + status);
            // Здесь можно добавить логику для обработки других статусов, если это необходимо
        }
    }
}