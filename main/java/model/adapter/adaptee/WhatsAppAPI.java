package model.adapter.adaptee;

/**
 * PADRÃO: Adapter
 * PAPEL: Adaptee
 * FUNÇÃO: Representa a integração com API do WhatsApp Business
 */
public class WhatsAppAPI {

    private final String accountSid;
    private final String authToken;
    private final String numeroWhatsApp;

    public WhatsAppAPI(String accountSid, String authToken, String numeroWhatsApp) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.numeroWhatsApp = numeroWhatsApp;
    }

    // Método com interface própria da API do WhatsApp
    public RespostaWhatsApp enviarMensagemWhatsApp(String numeroDestino,
                                                   String templateMensagem,
                                                   String[] parametros) {
        // Validação básica
        if (numeroDestino == null || numeroDestino.isEmpty()) {
            return new RespostaWhatsApp(false, "ERR_INVALID_NUMBER", "Número inválido");
        }

        // Simula envio
        System.out.println("[WhatsAppAPI] Autenticando conta: " + accountSid.substring(0, 4) + "****");
        System.out.println("[WhatsAppAPI] Número remetente: " + numeroWhatsApp);
        System.out.println("[WhatsAppAPI] Destinatário: " + numeroDestino);
        System.out.println("[WhatsAppAPI] Mensagem: " + templateMensagem);
        System.out.println("[WhatsAppAPI] WhatsApp enviado com sucesso!");

        return new RespostaWhatsApp(true, "MSG_SENT", "Mensagem enviada");
    }

    // Classe interna que representa a resposta da API do WhatsApp
    public static class RespostaWhatsApp {
        private final boolean sucesso;
        private final String codigo;
        private final String descricao;

        public RespostaWhatsApp(boolean sucesso, String codigo, String descricao) {
            this.sucesso = sucesso;
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public boolean isSucesso() {
            return sucesso;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}