import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GuessNumberBot extends TelegramLongPollingBot {

    private final Map<Long, Integer> targetNumbers = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "boberchik_bot";
    }

    @Override
    public String getBotToken() {
        return "5579061130:AAEiIBroucLkh0b6xarE52m81zH9XsyA_Hk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText().trim();

            if (messageText.equalsIgnoreCase("/start")) {
                int randomNumber = new Random().nextInt(100) + 1;
                targetNumbers.put(chatId, randomNumber);
                sendMessage(chatId, "Привіт! Я загадав число від 1 до 100. Спробуй вгадати!");
            } else if (targetNumbers.containsKey(chatId)) {
                try {
                    int guess = Integer.parseInt(messageText);
                    int target = targetNumbers.get(chatId);

                    if (guess < target) {
                        sendMessage(chatId, "Більше!");
                    } else if (guess > target) {
                        sendMessage(chatId, "Менше!");
                    } else {
                        sendMessage(chatId, "🎉 Вітаю! Ти вгадав число " + target + "! Напиши /start щоб зіграти ще.");
                        targetNumbers.remove(chatId);
                    }
                } catch (NumberFormatException e) {
                    sendMessage(chatId, "Будь ласка, введи число від 1 до 100.");
                }
            } else {
                sendMessage(chatId, "Напиши /start щоб почати гру.");
            }
        }
    }

    private void sendMessage(long chatId, String text) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(text);
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main-метод для запуску бота
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new GuessNumberBot());
        } catch (Exception e) {
            // Приховуємо тільки помилку про Webhook 404
            if (e.getMessage() == null || !e.getMessage().contains("404")) {
                e.printStackTrace();//Замахала мене ця помилка,харить жостко я її вручну знищив скажем так)
            }
        }
    }
}
