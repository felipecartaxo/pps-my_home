package model.decorator.concrete_decorators;

import model.decorator.FiltroBusca;
import model.decorator.FiltroDecorator;
import model.prototype.concrete_prototype.Anuncio;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PADRÃO: Decorator
 * PAPEL: ConcreteDecorator
 * FUNÇÃO: Adiciona filtro por estado do anúncio à busca
 */
public class FiltroEstadoAnuncio extends FiltroDecorator {

    private final String estadoDesejado;

    // Construtor do filtro por estado do anúncio
    public FiltroEstadoAnuncio(FiltroBusca filtroDecorado, String estadoDesejado) {
        super(filtroDecorado);
        this.estadoDesejado = estadoDesejado;
    }

    // Cria filtro para mostrar apenas anúncios ativos (padrão para buscas públicas)
    public static FiltroEstadoAnuncio apenasAtivos(FiltroBusca filtroDecorado) {
        return new FiltroEstadoAnuncio(filtroDecorado, "Ativo");
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> anuncios) {
        List<Anuncio> resultadoParcial = super.filtrar(anuncios);

        if (estadoDesejado == null || estadoDesejado.isEmpty()) {
            return resultadoParcial;
        }

        return resultadoParcial.stream()
                .filter(a -> estadoDesejado.equalsIgnoreCase(a.getEstadoAtual()))
                .collect(Collectors.toList());
    }

    @Override
    public String getDescricao() {
        return filtroDecorado.getDescricao() + " + Estado: " + estadoDesejado;
    }
}