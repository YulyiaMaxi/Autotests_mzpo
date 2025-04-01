package ru.education.tests;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.io.File;

public class EmailSender {

    public static void main(String[] args) {
        TestResultLogger logger = new TestResultLogger();
        String[] to = {
                "agroagro-123@yandex.ru",
                "J.Guliyev@mzpo.info",
                "j.maximova@mzpo.info"
        };
        String from = "morskoitest@yandex.ru"; // ваш адрес электронной почты
        String host = "smtp.yandex.ru"; // SMTP сервер
        String logFilePath = "D:/test/failedtests.txt"; // путь к вашему файлу лога

        TestResultLogger.getClasses();

        sendEmail(to, from, host, logFilePath); // отправляем email
    }

    public static void sendEmail(String[] recipients, String from, String host, String logFilePath) {
        // Настройки свойств
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465"); // порт для SSL
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.starttls.enable", "true");

        System.setProperty("javax.net.ssl.keyStorePassword", "Melek138");
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Получаем сессию с аутентификацией
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "xvwxcczmmcqmegfi"); // Ваш пароль
            }
        });

        try {
            // Создаем объект MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            // Установка получателей
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            message.setRecipients(Message.RecipientType.TO, addressTo);
            message.setSubject("Список упавших тестов");

            // Создание тела сообщения
            BodyPart messageBodyPart = new MimeBodyPart();
            String bodyText = "В приложении - список упавших тестов";
            messageBodyPart.setText(bodyText);

            // Создание объекта MimeMultipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Добавление вложения, если файл существует и не пустой
            File logFile = new File(logFilePath);
            boolean hasAttachment = false;
            if (logFile.exists() && logFile.length() > 0) {

                BodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.setFileName("Список упавших тестов.txt");
                DataSource source = new FileDataSource(logFilePath);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                multipart.addBodyPart(attachmentBodyPart);
                hasAttachment = true; // Устанавливаем флаг наличия вложения
            }

            // Установка содержимого сообщения
            message.setContent(multipart);

            // Проверка на пустое сообщение или отсутствие вложений перед отправкой
            if (bodyText.trim().isEmpty() || !hasAttachment) {
                System.out.println("Письмо пустое или нет вложений. Отправка отменена.");
                return; // Не отправляем письмо
            }

            // Отправка сообщения
            Transport.send(message);
            System.out.println("Сообщение успешно отправлено!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}



