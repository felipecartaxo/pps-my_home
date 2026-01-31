package model.chain_of_responsibility.concrete_handlers;

import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;
import model.singleton.ConfiguracaoSistema;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Valida o tamanho e conteúdo da descrição do anúncio
 *
 * INTEGRAÇÃO COM RF07 (Singleton):
 * - Os limites de tamanho são carregados do Singleton ConfiguracaoSistema.
 */
public class DescricaoHandler extends ModeracaoHandlerBase {

    private int tamanhoMinimo;
    private int tamanhoMaximo;

    public DescricaoHandler() {
        super("Validador de Descrição");
        carregarConfiguracoes();
    }

    private void carregarConfiguracoes() {
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();
        this.tamanhoMinimo = config.getTamanhoMinimoDescricao();
        this.tamanhoMaximo = config.getTamanhoMaximoDescricao();
    }

    @Override
    protected boolean validar(Anuncio anuncio) {
        String descricao = anuncio.getDescricao();

        if (descricao == null || descricao.trim().isEmpty()) {
            this.mensagemErro = "Descrição não pode estar vazia";
            return false;
        }

        String descricaoLimpa = descricao.trim();

        if (descricaoLimpa.length() < tamanhoMinimo) {
            this.mensagemErro = "Descrição muito curta: " + descricaoLimpa.length() +
                    " caracteres. Mínimo: " + tamanhoMinimo;
            return false;
        }

        if (descricaoLimpa.length() > tamanhoMaximo) {
            this.mensagemErro = "Descrição muito longa: " + descricaoLimpa.length() +
                    " caracteres. Máximo: " + tamanhoMaximo;
            return false;
        }

        this.mensagemErro = null;
        return true;
    }
}