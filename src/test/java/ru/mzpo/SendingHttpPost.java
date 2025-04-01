package ru.mzpo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendingHttpPost {
    public static StringBuilder sendToAmo(String arg) {
        try {
            String url = "https://amo.mzpo-s.ru/api/autotesting"; // Замените на ваш URL
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Установите метод запроса на POST
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json"); // Установите тип содержимого

            // Включите потоки ввода/вывода50
            con.setDoOutput(true);

            String jsonInputString = arg;

            // Запишите данные в выходной поток
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Thread.sleep(40000); // Задержка в миллисекундах
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Здесь установить задержку на 50000 миллисекунд (50 секунды)


            // Выведите тело ответа
//            System.out.println("Response Body: " + response.toString());

            return response;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
