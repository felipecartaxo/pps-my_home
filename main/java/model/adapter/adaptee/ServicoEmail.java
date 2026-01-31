package model.adapter.adaptee;

/**
 * PADRÃO: Adapter
 * PAPEL: Adaptee
 * FUNÇÃO: Representa um serviço externo de envio de emails
 */
public class ServicoEmail {

    private final String servidorSmtp;
    private final int porta;

    public ServicoEmail(String servidorSmtp, int porta) {
        this.servidorSmtp = servidorSmtp;
        this.porta = porta;
    }

    // Método com interface própria do serviço de email
    public boolean enviarEmail(String emailDestino, String assunto, String corpo) {
        // Em produção, aqui seria a integração real com servidor SMTP

        // Validação básica de email
        if (emailDestino == null || !emailDestino.contains("@")) {
            return false;
        }

        // Simula envio de email
        System.out.println("[ServicoEmail] Conectando ao servidor " + servidorSmtp + ":" + porta);
        System.out.println("[ServicoEmail] Enviando para: " + emailDestino);
        System.out.println("[ServicoEmail] Assunto: " + assunto);
        System.out.println("[ServicoEmail] Corpo: " + corpo);
        System.out.println("[ServicoEmail] Email enviado com sucesso!");

        return true;
    }

    public String getServidorSmtp() {
        return servidorSmtp;
    }
}