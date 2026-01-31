package model.chain_of_responsibility;

import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: Handler (Interface)
 * FUNÇÃO: Define o contrato que todos os handlers de moderação devem seguir.
 */
public interface Handler {

    // Define o próximo handler na cadeia
    Handler setProximo(Handler proximo);

    // Processa a requisição de moderação
    boolean processar(Anuncio anuncio);

    // Retorna o nome do handler
    String getNomeHandler();

    // Retorna a mensagem de erro, se houver
    String getMensagemErro();
}