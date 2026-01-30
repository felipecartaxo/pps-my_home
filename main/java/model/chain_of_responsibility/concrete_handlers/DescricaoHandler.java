package model.chain_of_responsibility.concrete_handlers;

import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Handler responsável por validar a descrição do anúncio.
 */
public class DescricaoHandler extends ModeracaoHandlerBase {

    private int tamanhoMinimoDescricao = 20;
    private int tamanhoMaximoDescricao = 5000;

    public DescricaoHandler() {
        super("Validador de Descrição");
    }

    public DescricaoHandler(int tamanhoMinimo) {
        super("Validador de Descrição");
        this.tamanhoMinimoDescricao = tamanhoMinimo;
    }

    public void configurarLimites(int minimo, int maximo) {
        this.tamanhoMinimoDescricao = minimo;
        this.tamanhoMaximoDescricao = maximo;
    }

    @Override
    protected boolean validar(Anuncio anuncio) {
        String descricao = anuncio.getDescricao();

        // Validação: Descrição não pode ser nula ou vazia
        if (descricao == null || descricao.trim().isEmpty()) {
            this.mensagemErro = "Descrição não pode estar vazia";
            return false;
        }

        String descricaoLimpa = descricao.trim();

        // Validação: Tamanho mínimo
        if (descricaoLimpa.length() < tamanhoMinimoDescricao) {
            this.mensagemErro = "Descrição muito curta: " + descricaoLimpa.length() +
                    " caracteres. Mínimo: " + tamanhoMinimoDescricao;
            return false;
        }

        // Validação: Tamanho máximo
        if (descricaoLimpa.length() > tamanhoMaximoDescricao) {
            this.mensagemErro = "Descrição muito longa: " + descricaoLimpa.length() +
                    " caracteres. Máximo: " + tamanhoMaximoDescricao;
            return false;
        }

        // Aprovado
        this.mensagemErro = null;
        return true;
    }
}