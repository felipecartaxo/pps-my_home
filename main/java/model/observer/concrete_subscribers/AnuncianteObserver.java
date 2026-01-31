package model.observer.concrete_subscribers;

import model.adapter.NotificadorAdapter;
import model.observer.ObservadorAnuncio;
import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Observer
 * PAPEL: ConcreteObserver
 * FUNÇÃO: Observador responsável por notificar o anunciante sobre mudanças
 * no estado do seu anúncio
 *
 * INTEGRAÇÃO COM RF05 (Adapter):
 * - Utiliza NotificadorAdapter para enviar notificações
 * - O canal de notificação é configurável (Email, SMS, Telegram, WhatsApp)
 * - Se nenhum adapter for configurado, apenas armazena a mensagem
 */
public class AnuncianteObserver implements ObservadorAnuncio {

    // Adapter utilizado para enviar notificações
    private NotificadorAdapter notificadorAdapter;

    // Destinatário das notificações (email, telefone, chatId, etc.)
    private String destinatario;

    // Armazena a última notificação para consulta
    private String ultimaNotificacao;

    /**
     * Construtor padrão, sem adapter configurado
     * Apenas armazena as mensagens
     */
    public AnuncianteObserver() {
        this.notificadorAdapter = null;
        this.destinatario = null;
    }

    // Construtor com adapter e destinatário
    public AnuncianteObserver(NotificadorAdapter notificadorAdapter, String destinatario) {
        this.notificadorAdapter = notificadorAdapter;
        this.destinatario = destinatario;
    }

    // Configura o canal de notificação
    public void configurarCanal(NotificadorAdapter notificadorAdapter, String destinatario) {
        this.notificadorAdapter = notificadorAdapter;
        this.destinatario = destinatario;
    }

    @Override
    public void notificar(Anuncio anuncio, String estadoAnterior, String estadoNovo) {
        String mensagem = formatarMensagem(anuncio, estadoAnterior, estadoNovo);
        this.ultimaNotificacao = mensagem;

        // Se há adapter configurado, envia a notificação
        if (notificadorAdapter != null && destinatario != null) {
            boolean enviado = notificadorAdapter.enviar(destinatario, mensagem);
            if (!enviado) {
                System.out.println("[AnuncianteObserver] Falha ao enviar notificação via " +
                        notificadorAdapter.getNomeCanal());
            }
        }
    }

    // Formata a mensagem de notificação para o anunciante
    private String formatarMensagem(Anuncio anuncio, String estadoAnterior, String estadoNovo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Seu anúncio '").append(anuncio.getTitulo()).append("' ");
        sb.append("mudou de '").append(estadoAnterior).append("' ");
        sb.append("para '").append(estadoNovo).append("'.");

        switch (estadoNovo) {
            case "Pendente de Moderação":
                sb.append("\n\nAguarde a revisão. Você será notificado em breve.");
                break;
            case "Ativo":
                sb.append("\n\nParabéns! Seu anúncio está visível para todos.");
                break;
            case "Suspenso":
                sb.append("\n\nVerifique os motivos e corrija para republicar.");
                break;
            case "Vendido/Alugado":
                sb.append("\n\nParabéns! O negócio foi fechado.");
                break;
            case "Rascunho":
                sb.append("\n\nVocê pode editar e enviar novamente para moderação.");
                break;
        }

        return sb.toString();
    }

    // Retorna a última notificação gerada
    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }

    // Retorna o nome do canal configurado, se houver
    public String getCanalConfigurado() {
        return notificadorAdapter != null ? notificadorAdapter.getNomeCanal() : "Nenhum";
    }
}