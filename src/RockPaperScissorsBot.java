import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.*;

public class RockPaperScissorsBot extends TelegramLongPollingBot {

    enum State { WAITING, PLAYING }

    private static class Player {
        Long chatId;
        String move; // "камінь", "ножиці", "папір"

        Player(Long chatId) {
            this.chatId = chatId;
        }
    }

    private final List<Player> players = new ArrayList<>();
    private final Map<Long, Player> playerMap = new HashMap<>();
    private State gameState = State.WAITING;

    @Override
    public String getBotUsername() {
        return "@boberchik_bot"; // замінити
    }

    @Override
    public String getBotToken() {
        return "5579061130:AAEiIBroucLkh0b6xarE52m81zH9XsyA_Hk"; // замінити
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText().toLowerCase();

        if (text.equals("/join")) {
            if (playerMap.containsKey(chatId)) {
                send(chatId, "Ти вже в грі!");
                return;
            }

            if (players.size() >= 2) {
                send(chatId, "Гра вже розпочата. Спробуй пізніше.");
                return;
            }

            Player newPlayer = new Player(chatId);
            players.add(newPlayer);
            playerMap.put(chatId, newPlayer);
            send(chatId, "✅ Ти приєднався до гри!");

            if (players.size() == 2) {
                gameState = State.PLAYING;
                for (Player p : players) {
                    send(p.chatId, "🎮 Починаємо гру! Напиши: камінь, ножиці або папір.");
                }
            }
            return;
        }

        if (gameState == State.PLAYING && playerMap.containsKey(chatId)) {
            Player player = playerMap.get(chatId);

            if (player.move != null) {
                send(chatId, "Ти вже зробив хід. Очікуй іншого гравця.");
                return;
            }

            if (!List.of("камінь", "ножиці", "папір").contains(text)) {
                send(chatId, "❌ Введи тільки: камінь, ножиці або папір.");
                return;
            }

            player.move = text;
            send(chatId, "✅ Хід прийнято!");

            if (players.get(0).move != null && players.get(1).move != null) {
                showResult();
                resetGame();
            }
        }
    }

    private void showResult() {
        Player p1 = players.get(0);
        Player p2 = players.get(1);

        String move1 = p1.move;
        String move2 = p2.move;

        String result;
        if (move1.equals(move2)) {
            result = "🤝 Нічия!";
        } else if (
                (move1.equals("камінь") && move2.equals("ножиці")) ||
                        (move1.equals("ножиці") && move2.equals("папір")) ||
                        (move1.equals("папір") && move2.equals("камінь"))
        ) {
            result = "🎉 Гравець 1 переміг!";
        } else {
            result = "🎉 Гравець 2 переміг!";
        }

        send(p1.chatId, String.format("Ти: %s, суперник: %s\n%s", move1, move2, result));
        send(p2.chatId, String.format("Ти: %s, суперник: %s\n%s", move2, move1, result));
    }

    private void resetGame() {
        players.clear();
        playerMap.clear();
        gameState = State.WAITING;
    }

    private void send(Long chatId, String text) {
        try {
            execute(new SendMessage(chatId.toString(), text));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new RockPaperScissorsBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

