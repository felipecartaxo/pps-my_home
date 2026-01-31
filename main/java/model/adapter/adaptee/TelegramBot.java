package model.adapter.adaptee;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * PADRÃO: Adapter
 * PAPEL: Adaptee
 * FUNÇÃO: Representa a integração com a API do Telegram Bot
 *
 * IMPLEMENTAÇÃO REAL (RF05):
 * Esta classe implementa a integração REAL com a API do Telegram
 *
 * REQUISITOS PARA USO:
 * 1. Criar um bot no Telegram via @BotFather
 * 2. Obter o token do bot
 * 3. Iniciar conversa com o bot para obter o chatId
 */
public class TelegramBot {

    private static final String TELEGRAM_API_URL = "https://api.telegram.org/bot";
    private final String botToken;

    // Construtor que recebe o token do bot
    public TelegramBot(String botToken) {
        this.botToken = botToken;
    }

    // Envia mensagem para um chat específico via API do Telegram
    public boolean sendMessage(String chatId, String texto) {
        try {
            String urlString = TELEGRAM_API_URL + botToken + "/sendMessage";
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Monta o JSON da requisição
            String jsonPayload = String.format(
                    "{\"chat_id\": \"%s\", \"text\": \"%s\", \"parse_mode\": \"HTML\"}",
                    chatId,
                    escapeJson(texto)
            );

            // Envia a requisição
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Verifica resposta
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("[TelegramBot] Mensagem enviada com sucesso para chatId: " + chatId);
                return true;
            } else {
                System.out.println("[TelegramBot] Erro ao enviar mensagem. Código: " + responseCode);
                return false;
            }

        } catch (Exception e) {
            System.out.println("[TelegramBot] Erro de conexão: " + e.getMessage());
            return false;
        }
    }

    // "Traduz" caracteres especiais para JSON
    private String escapeJson(String texto) {
        return texto
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    public String getBotToken() {
        return botToken;
    }
}