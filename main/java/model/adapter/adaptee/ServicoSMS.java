package model.adapter.adaptee;

/**
 * PADRÃO: Adapter
 * PAPEL: Adaptee
 * FUNÇÃO: Representa um serviço externo de envio de SMS
 */
public class ServicoSMS {

    private final String apiKey;
    private final String numeroRemetente;

    public ServicoSMS(String apiKey, String numeroRemetente) {
        this.apiKey = apiKey;
        this.numeroRemetente = numeroRemetente;
    }

    // Método com interface própria do serviço de SMS
    public int transmitirSMS(String numeroTelefone, String textoMensagem) {
        // Validação básica de telefone
        if (numeroTelefone == null || numeroTelefone.length() < 10) {
            return -1; // Erro: número inválido
        }

        // Trunca mensagem se necessário (limite SMS)
        String mensagemTruncada = textoMensagem;
        if (textoMensagem.length() > 160) {
            mensagemTruncada = textoMensagem.substring(0, 157) + "...";
        }

        // Simula envio
        System.out.println("[ServicoSMS] Autenticando com API Key: " + apiKey.substring(0, 4) + "****");
        System.out.println("[ServicoSMS] Remetente: " + numeroRemetente);
        System.out.println("[ServicoSMS] Destinatário: " + numeroTelefone);
        System.out.println("[ServicoSMS] Mensagem: " + mensagemTruncada);
        System.out.println("[ServicoSMS] SMS enviado com sucesso!");

        return 0; // Sucesso
    }
}