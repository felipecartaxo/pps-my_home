package model.adapter.concrete_adapters;

import model.adapter.NotificadorAdapter;
import model.adapter.adaptee.TelegramBot;

/**
 * PADRÃO: Adapter
 * PAPEL: Adapter (Concrete)
 * FUNÇÃO: Adapta a interface do TelegramBot para a interface NotificadorAdapter
 *
 * IMPLEMENTAÇÃO REAL (RF05):
 * Este adapter utiliza o TelegramBot que faz integração REAL com a API do Telegram
 *
 * REQUISITOS PARA USO:
 * 1. Criar um bot no Telegram via @BotFather
 * 2. Obter o token do bot
 * 3. O usuário deve iniciar conversa com o bot para obter o chatId
 */
public class TelegramAdapter implements NotificadorAdapter {

    private final TelegramBot telegramBot;

    // Construtor que recebe o bot do Telegram a ser adaptado
    public TelegramAdapter(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    // Construtor que recebe apenas o token do bot
    public TelegramAdapter(String botToken) {
        this.telegramBot = new TelegramBot(botToken);
    }

    // Adapta a chamada genérica para o formato do TelegramBot
    @Override
    public boolean enviar(String destinatario, String mensagem) {
        // Formata a mensagem com cabeçalho do MyHome
        String mensagemFormatada = "<b>🏠 MyHome - Notificação</b>\n\n" + mensagem;

        // Adapta a chamada para o TelegramBot
        return telegramBot.sendMessage(destinatario, mensagemFormatada);
    }

    @Override
    public String getNomeCanal() {
        return "Telegram";
    }
}