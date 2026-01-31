package model.chain_of_responsibility.concrete_handlers;

import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;
import model.singleton.ConfiguracaoSistema;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Valida a quantidade de fotos do anúncio
 *
 * INTEGRAÇÃO COM RF07 (Singleton):
 * - O limite máximo de fotos é carregado do Singleton ConfiguracaoSistema
 */
public class FotosHandler extends ModeracaoHandlerBase {

    private int quantidadeMinima;
    private int quantidadeMaxima;

    public FotosHandler() {
        super("Validador de Fotos");
        this.quantidadeMinima = 1; // Mínimo fixo
        carregarConfiguracoes();
    }

    public FotosHandler(int quantidadeMinima) {
        super("Validador de Fotos");
        this.quantidadeMinima = quantidadeMinima;
        carregarConfiguracoes();
    }

    private void carregarConfiguracoes() {
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();
        this.quantidadeMaxima = config.getLimiteFotos();
    }

    @Override
    protected boolean validar(Anuncio anuncio) {
        int quantidadeFotos = anuncio.getFotos().size();

        if (quantidadeFotos < quantidadeMinima) {
            this.mensagemErro = "Quantidade insuficiente de fotos: " + quantidadeFotos +
                    ". Mínimo exigido: " + quantidadeMinima;
            return false;
        }

        if (quantidadeFotos > quantidadeMaxima) {
            this.mensagemErro = "Quantidade excessiva de fotos: " + quantidadeFotos +
                    ". Máximo permitido: " + quantidadeMaxima;
            return false;
        }

        this.mensagemErro = null;
        return true;
    }
}