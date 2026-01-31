package model.adapter.concrete_adapters;

import model.adapter.NotificadorAdapter;
import model.adapter.adaptee.WhatsAppAPI;

/**
 * PADRÃO: Adapter
 * PAPEL: Adapter (Concrete)
 * FUNÇÃO: Adapta a interface do WhatsAppAPI para a interface NotificadorAdapter
 */
public class WhatsAppAdapter implements NotificadorAdapter {

    private final WhatsAppAPI whatsAppAPI;

    // Construtor que recebe a API do WhatsApp a ser adaptada
    public WhatsAppAdapter(WhatsAppAPI whatsAppAPI) {
        this.whatsAppAPI = whatsAppAPI;
    }

    // Construtor com API padrão (simulada)
    public WhatsAppAdapter() {
        this.whatsAppAPI = new WhatsAppAPI("ACCOUNT_SID", "AUTH_TOKEN", "+5500000000000");
    }

    // Adapta a chamada genérica para o formato do WhatsAppAPI
    @Override
    public boolean enviar(String destinatario, String mensagem) {
        // Adapta os parâmetros para o formato do WhatsAppAPI
        WhatsAppAPI.RespostaWhatsApp resposta = whatsAppAPI.enviarMensagemWhatsApp(
                destinatario,
                mensagem,
                new String[]{} // Sem parâmetros adicionais
        );

        // Converte a resposta complexa para boolean
        return resposta.isSucesso();
    }

    @Override
    public String getNomeCanal() {
        return "WhatsApp";
    }
}