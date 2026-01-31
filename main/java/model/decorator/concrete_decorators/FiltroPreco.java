package model.decorator.concrete_decorators;

import model.decorator.FiltroBusca;
import model.decorator.FiltroDecorator;
import model.prototype.concrete_prototype.Anuncio;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PADRÃO: Decorator
 * PAPEL: ConcreteDecorator
 * FUNÇÃO: Filtra anúncios cujo preço está dentro da faixa especificada
 *
 * Pode ser combinado com outros decoradores para refinar a busca
 */
public class FiltroPreco extends FiltroDecorator {

    private final Double precoMinimo;
    private final Double precoMaximo;

    // Construtor com faixa de preço completa
    public FiltroPreco(FiltroBusca filtroDecorado, Double precoMinimo, Double precoMaximo) {
        super(filtroDecorado);
        this.precoMinimo = precoMinimo;
        this.precoMaximo = precoMaximo;
    }

    // Construtor para filtro apenas com preço máximo
    public static FiltroPreco ate(FiltroBusca filtroDecorado, double precoMaximo) {
        return new FiltroPreco(filtroDecorado, null, precoMaximo);
    }

    // Construtor para filtro apenas com preço mínimo
    public static FiltroPreco apartirDe(FiltroBusca filtroDecorado, double precoMinimo) {
        return new FiltroPreco(filtroDecorado, precoMinimo, null);
    }

    /**
     * Filtra anúncios por faixa de preço.
     * Primeiro delega para o filtro decorado, depois aplica seu critério.
     */
    @Override
    public List<Anuncio> filtrar(List<Anuncio> anuncios) {
        // Primeiro aplica o filtro decorado
        List<Anuncio> resultadoParcial = super.filtrar(anuncios);

        // Depois aplica o filtro de preço
        return resultadoParcial.stream()
                .filter(this::dentroFaixaPreco)
                .collect(Collectors.toList());
    }

    // Verifica se o anúncio está dentro da faixa de preço
    private boolean dentroFaixaPreco(Anuncio anuncio) {
        double preco = anuncio.getPreco();

        if (precoMinimo != null && preco < precoMinimo) {
            return false;
        }

        if (precoMaximo != null && preco > precoMaximo) {
            return false;
        }

        return true;
    }

    @Override
    public String getDescricao() {
        StringBuilder sb = new StringBuilder();
        sb.append(filtroDecorado.getDescricao());
        sb.append(" + Preço: ");

        if (precoMinimo != null && precoMaximo != null) {
            sb.append("R$ ").append(String.format("%,.0f", precoMinimo));
            sb.append(" a R$ ").append(String.format("%,.0f", precoMaximo));
        } else if (precoMinimo != null) {
            sb.append("a partir de R$ ").append(String.format("%,.0f", precoMinimo));
        } else if (precoMaximo != null) {
            sb.append("até R$ ").append(String.format("%,.0f", precoMaximo));
        }

        return sb.toString();
    }
}