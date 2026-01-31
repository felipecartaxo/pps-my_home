package model.decorator.concrete_decorators;

import model.decorator.FiltroBusca;
import model.decorator.FiltroDecorator;
import model.prototype.concrete_prototype.Anuncio;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PADRÃO: Decorator
 * PAPEL: ConcreteDecorator
 * FUNÇÃO: Adiciona filtro por tipo de imóvel à busca
 */
public class FiltroTipoImovel extends FiltroDecorator {

    private final String tipoImovel;

    // Construtor do filtro por tipo de imóvel
    public FiltroTipoImovel(FiltroBusca filtroDecorado, String tipoImovel) {
        super(filtroDecorado);
        this.tipoImovel = tipoImovel != null ? tipoImovel.toLowerCase() : "";
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> anuncios) {
        List<Anuncio> resultadoParcial = super.filtrar(anuncios);

        if (tipoImovel.isEmpty()) {
            return resultadoParcial;
        }

        return resultadoParcial.stream()
                .filter(this::tipoCorreto)
                .collect(Collectors.toList());
    }

    private boolean tipoCorreto(Anuncio anuncio) {
        if (anuncio.getImovel() == null) {
            return false;
        }

        String tipo = anuncio.getImovel().getTipo();
        if (tipo == null) {
            return false;
        }

        return tipo.toLowerCase().contains(tipoImovel);
    }

    @Override
    public String getDescricao() {
        return filtroDecorado.getDescricao() + " + Tipo: " + tipoImovel;
    }
}