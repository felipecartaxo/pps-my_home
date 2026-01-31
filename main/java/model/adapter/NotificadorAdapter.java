package model.adapter;

/**
 * PADRÃO: Adapter
 * PAPEL: Client (Interface)
 * FUNÇÃO: Define a interface comum que o sistema utiliza para enviar notificações,
 * independente do canal específico (Email, SMS, Telegram, WhatsApp)
 *
 * CONTEXTO (RF05):
 * - O usuário define sua preferência de canal de notificação
 * - O sistema envia notificações através da interface comum
 * - Novos canais podem ser adicionados criando novos Adapters
 */
public interface NotificadorAdapter {

    // Envia uma notificação para o destinatário especificado
    boolean enviar(String destinatario, String mensagem);

    // Retorna o nome do canal de notificação
    String getNomeCanal();
}