package model.chain_of_responsibility.concrete_handlers;

import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Handler responsável por validar a quantidade de fotos do anúncio.
 */
public class FotosHandler extends ModeracaoHandlerBase {

    private int quantidadeMinimaFotos = 1;
    private int quantidadeMaximaFotos = 30;

    public FotosHandler() {
        super("Validador de Fotos");
    }

    public FotosHandler(int quantidadeMinimaFotos) {
        super("Validador de Fotos");
        this.quantidadeMinimaFotos = quantidadeMinimaFotos;
    }

    public void configurarLimites(int minimo, int maximo) {
        this.quantidadeMinimaFotos = minimo;
        this.quantidadeMaximaFotos = maximo;
    }

    @Override
    protected boolean validar(Anuncio anuncio) {
        int quantidadeFotos = anuncio.getFotos().size();

        if (quantidadeFotos < quantidadeMinimaFotos) {
            this.mensagemErro = "Quantidade insuficiente de fotos: " + quantidadeFotos +
                    ". Mínimo exigido: " + quantidadeMinimaFotos;
            return false;
        }

        if (quantidadeFotos > quantidadeMaximaFotos) {
            this.mensagemErro = "Quantidade excessiva de fotos: " + quantidadeFotos +
                    ". Máximo permitido: " + quantidadeMaximaFotos;
            return false;
        }

        this.mensagemErro = null;
        return true;
    }
}