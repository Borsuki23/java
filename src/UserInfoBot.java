import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;

public class UserInfoBot extends TelegramLongPollingBot {

    private enum State {
        ASK_BIRTHDAY, ASK_COLOR, ASK_SECRET, ASK_CITY, COMPLETED
    }

    private static class UserData {
        State state = State.ASK_BIRTHDAY;
        String birthday;
        String color;
        String secret;
        String city;
    }

    private final Map<Long, UserData> userState = new HashMap<>();

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
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();
        UserData data = userState.computeIfAbsent(chatId, id -> new UserData());
        String reply = "";

        switch (data.state) {
            case ASK_BIRTHDAY:
                reply = "Дата народження?";
                data.state = State.ASK_COLOR;
                break;

            case ASK_COLOR:
                data.birthday = messageText;
                reply = "Улюблений колір?";
                data.state = State.ASK_SECRET;
                break;

            case ASK_SECRET:
                data.color = messageText;
                reply = "Секретне слово?";
                data.state = State.ASK_CITY;
                break;

            case ASK_CITY:
                data.secret = messageText;
                reply = "Місто проживання?";
                data.state = State.COMPLETED;
                break;

            case COMPLETED:
                data.city = messageText;
                reply = String.format(
                        "✅ Дякую! Ось що ти надіслав:\n- Дата: %s\n- Колір: %s\n- Секрет: %s\n- Місто: %s",
                        data.birthday, data.color, data.secret, data.city
                );
                userState.remove(chatId); // очищення для наступного разу
                break;
        }

        SendMessage message = new SendMessage(chatId.toString(), reply);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new UserInfoBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
