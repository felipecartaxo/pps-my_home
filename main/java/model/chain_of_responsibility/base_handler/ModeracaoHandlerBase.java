package model.chain_of_responsibility.base_handler;

import model.chain_of_responsibility.Handler;
import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: Handler
 * FUNÇÃO: Classe abstrata que define a estrutura base para todos os handlers
 * de moderação.
 */
public abstract class ModeracaoHandlerBase implements Handler {

    // Referência para o próximo handler na cadeia (successor)
    protected Handler proximo;

    // Nome identificador do handler
    protected final String nomeHandler;

    // Mensagem de erro da última validação
    protected String mensagemErro;

    // Construtor protegido - apenas subclasses podem instanciar.
    protected ModeracaoHandlerBase(String nomeHandler) {
        this.nomeHandler = nomeHandler;
    }

    /**
     * Define o próximo handler na cadeia.
     * Retorna o próximo para permitir encadeamento fluente.
     */
    @Override
    public Handler setProximo(Handler proximo) {
        this.proximo = proximo;
        return proximo;
    }

    /**
     * Processa a requisição de moderação.
     *
     * Implementa o algoritmo do Chain of Responsibility:
     * 1. Executa validação específica
     * 2. Se reprovado, interrompe a cadeia
     * 3. Se aprovado e existe próximo, delega para ele
     */
    @Override
    public boolean processar(Anuncio anuncio) {
        // Executa a validação específica deste handler
        boolean aprovado = validar(anuncio);

        // Se reprovado, interrompe a cadeia
        if (!aprovado) {
            return false;
        }

        // Se aprovado e existe próximo, passa adiante
        if (proximo != null) {
            return proximo.processar(anuncio);
        }

        // Fim da cadeia, tudo aprovado
        return true;
    }

    /**
     * Método abstrato que cada handler concreto deve implementar.
     *
     * Contém a lógica de validação específica.
     */
    protected abstract boolean validar(Anuncio anuncio);

    @Override
    public String getNomeHandler() {
        return nomeHandler;
    }

    @Override
    public String getMensagemErro() {
        return mensagemErro;
    }
}