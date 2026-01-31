package model.chain_of_responsibility.concrete_handlers;

import enums.TipoTransacao;
import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Handler responsável por validar o preço do anúncio
 */
public class PrecoHandler extends ModeracaoHandlerBase {

    private double precoMinimoVenda = 10000.0;
    private double precoMinimoAluguel = 100.0;
    private double precoMinimoTemporada = 50.0;
    private double precoMaximo = 500000000.0;

    public PrecoHandler() {
        super("Validador de Preço");
    }

    public void configurarLimites(double minimoVenda, double minimoAluguel,
                                  double minimoTemporada, double maximo) {
        this.precoMinimoVenda = minimoVenda;
        this.precoMinimoAluguel = minimoAluguel;
        this.precoMinimoTemporada = minimoTemporada;
        this.precoMaximo = maximo;
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