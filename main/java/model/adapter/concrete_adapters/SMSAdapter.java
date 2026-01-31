package model.adapter.concrete_adapters;

import model.adapter.NotificadorAdapter;
import model.adapter.adaptee.ServicoSMS;

/**
 * PADRÃO: Adapter
 * PAPEL: Adapter (Concrete)
 * FUNÇÃO: Adapta a interface do ServicoSMS para a interface NotificadorAdapter
 */
public class SMSAdapter implements NotificadorAdapter {

    private final ServicoSMS servicoSMS;

    /**
     * Construtor que recebe o serviço de SMS a ser adaptado.
     *
     * @param servicoSMS Instância do serviço de SMS (Adaptee)
     */
    public SMSAdapter(ServicoSMS servicoSMS) {
        this.servicoSMS = servicoSMS;
    }

    /**
     * Construtor com serviço de SMS padrão.
     */
    public SMSAdapter() {
        this.servicoSMS = new ServicoSMS("API_KEY_SMS", "+5500000000000");
    }

    /**
     * Adapta a chamada genérica para o formato do ServicoSMS.
     *
     * Interface esperada: enviar(destinatario, mensagem) -> boolean
     * Interface do Adaptee: transmitirSMS(numero, texto) -> int (código status)
     */
    @Override
    public boolean enviar(String destinatario, String mensagem) {
        // Adapta os parâmetros e o retorno
        int codigoRetorno = servicoSMS.transmitirSMS(destinatario, mensagem);

        // Converte código de status para boolean
        return codigoRetorno == 0;
    }

    @Override
    public String getNomeCanal() {
        return "SMS";
    }
}