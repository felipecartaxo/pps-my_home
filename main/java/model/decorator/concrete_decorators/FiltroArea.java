package model.decorator.concrete_decorators;

import model.decorator.FiltroBusca;
import model.decorator.FiltroDecorator;
import model.prototype.concrete_prototype.Anuncio;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PADRÃO: Decorator
 * PAPEL: ConcreteDecorator
 * FUNÇÃO: Adiciona filtro por área à busca
 */
public class FiltroArea extends FiltroDecorator {

    private final Double areaMinima;
    private final Double areaMaxima;

    // Construtor com faixa de área completa
    public FiltroArea(FiltroBusca filtroDecorado, Double areaMinima, Double areaMaxima) {
        super(filtroDecorado);
        this.areaMinima = areaMinima;
        this.areaMaxima = areaMaxima;
    }

    // Construtor para filtro apenas com área mínima
    public static FiltroArea minimo(FiltroBusca filtroDecorado, double areaMinima) {
        return new FiltroArea(filtroDecorado, areaMinima, null);
    }

    // Construtor para filtro apenas com área máxima
    public static FiltroArea maximo(FiltroBusca filtroDecorado, double areaMaxima) {
        return new FiltroArea(filtroDecorado, null, areaMaxima);
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> anuncios) {
        List<Anuncio> resultadoParcial = super.filtrar(anuncios);

        return resultadoParcial.stream()
                .filter(this::dentroFaixaArea)
                .collect(Collectors.toList());
    }

    private boolean dentroFaixaArea(Anuncio anuncio) {
        if (anuncio.getImovel() == null) {
            return false;
        }

        double area = anuncio.getImovel().getArea();

        if (areaMinima != null && area < areaMinima) {
            return false;
        }

        if (areaMaxima != null && area > areaMaxima) {
            return false;
        }

        return true;
    }

    @Override
    public String getDescricao() {
        StringBuilder sb = new StringBuilder();
        sb.append(filtroDecorado.getDescricao());
        sb.append(" + Área: ");

        if (areaMinima != null && areaMaxima != null) {
            sb.append(areaMinima).append("m² a ").append(areaMaxima).append("m²");
        } else if (areaMinima != null) {
            sb.append("mínimo ").append(areaMinima).append("m²");
        } else if (areaMaxima != null) {
            sb.append("máximo ").append(areaMaxima).append("m²");
        }

        return sb.toString();
    }
}