package model.decorator.concrete_decorators;

import model.decorator.FiltroBusca;
import model.decorator.FiltroDecorator;
import model.factory_method.product.ImovelBase;
import model.prototype.concrete_prototype.Anuncio;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PADRÃO: Decorator
 * PAPEL: ConcreteDecorator
 * FUNÇÃO: Adiciona filtro por número de quartos à busca
 */
public class FiltroQuartos extends FiltroDecorator {

    private final Integer quartosMinimo;
    private final Integer quartosMaximo;

    // Construtor com faixa de quartos
    public FiltroQuartos(FiltroBusca filtroDecorado, Integer quartosMinimo, Integer quartosMaximo) {
        super(filtroDecorado);
        this.quartosMinimo = quartosMinimo;
        this.quartosMaximo = quartosMaximo;
    }

    // Construtor para número exato de quartos
    public static FiltroQuartos exatamente(FiltroBusca filtroDecorado, int quartos) {
        return new FiltroQuartos(filtroDecorado, quartos, quartos);
    }

    // Construtor para número mínimo de quartos
    public static FiltroQuartos minimo(FiltroBusca filtroDecorado, int quartosMinimo) {
        return new FiltroQuartos(filtroDecorado, quartosMinimo, null);
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> anuncios) {
        List<Anuncio> resultadoParcial = super.filtrar(anuncios);

        return resultadoParcial.stream()
                .filter(this::dentroFaixaQuartos)
                .collect(Collectors.toList());
    }

    private boolean dentroFaixaQuartos(Anuncio anuncio) {
        if (anuncio.getImovel() == null) {
            return false;
        }

        // Verifica se o imóvel tem a propriedade de quartos
        if (!(anuncio.getImovel() instanceof ImovelBase)) {
            return false;
        }

        ImovelBase imovel = (ImovelBase) anuncio.getImovel();
        int quartos = imovel.getNumeroQuartos();

        // Imóveis sem quartos (terrenos, galpões) não passam no filtro
        if (quartos == 0 && (quartosMinimo != null && quartosMinimo > 0)) {
            return false;
        }

        if (quartosMinimo != null && quartos < quartosMinimo) {
            return false;
        }

        if (quartosMaximo != null && quartos > quartosMaximo) {
            return false;
        }

        return true;
    }

    @Override
    public String getDescricao() {
        StringBuilder sb = new StringBuilder();
        sb.append(filtroDecorado.getDescricao());
        sb.append(" + Quartos: ");

        if (quartosMinimo != null && quartosMaximo != null) {
            if (quartosMinimo.equals(quartosMaximo)) {
                sb.append(quartosMinimo).append(" quarto(s)");
            } else {
                sb.append(quartosMinimo).append(" a ").append(quartosMaximo);
            }
        } else if (quartosMinimo != null) {
            sb.append("mínimo ").append(quartosMinimo);
        } else if (quartosMaximo != null) {
            sb.append("máximo ").append(quartosMaximo);
        }

        return sb.toString();
    }
}