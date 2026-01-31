package model.observer.concrete_subscribers;

import model.observer.ObservadorAnuncio;
import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Observer
 * PAPEL: ConcreteObserver
 * FUNÇÃO: Observador responsável por notificar o anunciante sobre mudanças no estado do seu anúncio.
 *
 * RESPONSABILIDADE:
 * - Receber notificações de mudanças de estado do Subject (Anuncio)
 * - Formatar mensagens adequadas para o anunciante
 * - Armazenar a última notificação (para demonstração)
 *
 * REGRA DE NEGÓCIO (RF04):
 * - Sempre que um anúncio mudar de estado, o anunciante deve ser notificado
 *
 * INTEGRAÇÃO FUTURA (RF05):
 * - Este observer será integrado com o Adapter de notificação (Email, SMS, etc.)
 */
public class AnuncianteObserver implements ObservadorAnuncio {

    /** Armazena a última notificação para consulta */
    private String ultimaNotificacao;

    @Override
    public void notificar(Anuncio anuncio, String estadoAnterior, String estadoNovo) {
        String mensagem = formatarMensagem(anuncio, estadoAnterior, estadoNovo);
        this.ultimaNotificacao = mensagem;

        // TODO: Integrar com RF05 (Adapter) para enviar notificação real
    }

    // Formata a mensagem de notificação para o anunciante
    private String formatarMensagem(Anuncio anuncio, String estadoAnterior, String estadoNovo) {
        StringBuilder sb = new StringBuilder();
        sb.append("[NOTIFICAÇÃO ANUNCIANTE] ");
        sb.append("Seu anúncio '").append(anuncio.getTitulo()).append("' ");
        sb.append("mudou de '").append(estadoAnterior).append("' ");
        sb.append("para '").append(estadoNovo).append("'");

        switch (estadoNovo) {
            case "Pendente de Moderação":
                sb.append(" - Aguarde a revisão. Você será notificado em breve.");
                break;
            case "Ativo":
                sb.append(" - Parabéns! Seu anúncio está visível para todos.");
                break;
            case "Suspenso":
                sb.append(" - Verifique os motivos e corrija para republicar.");
                break;
            case "Vendido/Alugado":
                sb.append(" - Parabéns! O negócio foi fechado.");
                break;
            case "Rascunho":
                sb.append(" - Você pode editar e enviar novamente para moderação.");
                break;
        }

        return sb.toString();
    }

    /**
     * Retorna a última notificação gerada.
     */
    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }
}