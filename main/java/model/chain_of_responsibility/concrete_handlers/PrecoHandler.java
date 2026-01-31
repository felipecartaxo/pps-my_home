package model.chain_of_responsibility.concrete_handlers;

import enums.TipoTransacao;
import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;
import model.singleton.ConfiguracaoSistema;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Valida se o preço do anúncio está dentro dos limites aceitáveis
 *
 * INTEGRAÇÃO COM RF07 (Singleton):
 * - Os limites de preço são carregados do Singleton ConfiguracaoSistema,
 * que por sua vez carrega do arquivo config.properties
 *
 * Isso permite que os limites sejam alterados sem modificar código.
 */
public class PrecoHandler extends ModeracaoHandlerBase {

    private double precoMinimoVenda;
    private double precoMinimoAluguel;
    private double precoMinimoTemporada;
    private double precoMaximo;

    public PrecoHandler() {
        super("Validador de Preço");
        carregarConfiguracoes();
    }

    /**
     * Carrega os limites de preço do Singleton de configuração.
     *
     * INTEGRAÇÃO RF07: Usa ConfiguracaoSistema.getInstancia() para
     * obter os limites do arquivo de configuração.
     */
    private void carregarConfiguracoes() {
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();
        this.precoMinimoVenda = config.getPrecoMinimoVenda();
        this.precoMinimoAluguel = config.getPrecoMinimoAluguel();
        this.precoMinimoTemporada = config.getPrecoMinimoTemporada();
        this.precoMaximo = config.getPrecoMaximo();
    }

    @Override
    protected boolean validar(Anuncio anuncio) {
        double preco = anuncio.getPreco();
        TipoTransacao tipo = anuncio.getTipoTransacao();

        if (preco <= 0) {
            this.mensagemErro = "Preço inválido: R$ " + String.format("%.2f", preco) +
                    ". O preço deve ser maior que zero.";
            return false;
        }

        if (preco > precoMaximo) {
            this.mensagemErro = "Preço suspeito: R$ " + String.format("%,.2f", preco) +
                    ". Valor excede o limite máximo.";
            return false;
        }

        double precoMinimo = getPrecoMinimo(tipo);
        if (preco < precoMinimo) {
            this.mensagemErro = "Preço muito baixo para " + tipo.getDescricao() +
                    ": R$ " + String.format("%.2f", preco) +
                    ". Mínimo: R$ " + String.format("%.2f", precoMinimo);
            return false;
        }

        this.mensagemErro = null;
        return true;
    }

    private double getPrecoMinimo(TipoTransacao tipo) {
        switch (tipo) {
            case VENDA: return precoMinimoVenda;
            case ALUGUEL: return precoMinimoAluguel;
            case TEMPORADA: return precoMinimoTemporada;
            default: return precoMinimoVenda;
        }
    }
}