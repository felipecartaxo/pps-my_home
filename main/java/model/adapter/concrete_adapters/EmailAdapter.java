package model.adapter.concrete_adapters;

import model.adapter.NotificadorAdapter;
import model.adapter.adaptee.ServicoEmail;

/**
 * PADRÃO: Adapter
 * PAPEL: Adapter (Concrete)
 * FUNÇÃO: Adapta a interface do ServicoEmail para a interface NotificadorAdapter
 */
public class EmailAdapter implements NotificadorAdapter {

    private final ServicoEmail servicoEmail;
    private final String assuntoPadrao;

    // Construtor que recebe o serviço de email a ser adaptado
    public EmailAdapter(ServicoEmail servicoEmail, String assuntoPadrao) {
        this.servicoEmail = servicoEmail;
        this.assuntoPadrao = assuntoPadrao;
    }

    // Construtor com serviço de email padrão
    public EmailAdapter() {
        this.servicoEmail = new ServicoEmail("smtp.myhome.com", 587);
        this.assuntoPadrao = "[MyHome] Notificação";
    }

    /**
     * Adapta a chamada genérica para o formato do ServicoEmail.
     *
     * Interface esperada: enviar(destinatario, mensagem)
     * Interface do Adaptee: enviarEmail(emailDestino, assunto, corpo)
     */
    @Override
    public boolean enviar(String destinatario, String mensagem) {
        // Adapta os parâmetros para o formato do ServicoEmail
        return servicoEmail.enviarEmail(destinatario, assuntoPadrao, mensagem);
    }

    @Override
    public String getNomeCanal() {
        return "Email";
    }
}