package model.decorator.concrete_decorators;

import model.decorator.FiltroBusca;
import model.decorator.FiltroDecorator;
import model.prototype.concrete_prototype.Anuncio;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PADRÃO: Decorator
 * PAPEL: ConcreteDecorator
 * FUNÇÃO: Adiciona filtro por localização
 */
public class FiltroLocalizacao extends FiltroDecorator {

    private final String termoBusca;

    // Construtor do filtro de localização
    public FiltroLocalizacao(FiltroBusca filtroDecorado, String termoBusca) {
        super(filtroDecorado);
        this.termoBusca = termoBusca != null ? termoBusca.toLowerCase() : "";
    }

    /**
     * Filtra anúncios por localização.
     * Primeiro delega para o filtro decorado, depois aplica seu critério.
     */
    @Override
    public List<Anuncio> filtrar(List<Anuncio> anuncios) {
        List<Anuncio> resultadoParcial = super.filtrar(anuncios);

        if (termoBusca.isEmpty()) {
            return resultadoParcial;
        }

        return resultadoParcial.stream()
                .filter(this::contemLocalizacao)
                .collect(Collectors.toList());
    }

    // Verifica se a localização do imóvel contém o termo buscado
    private boolean contemLocalizacao(Anuncio anuncio) {
        if (anuncio.getImovel() == null) {
            return false;
        }

        String localizacao = anuncio.getImovel().getLocalizacao();
        if (localizacao == null) {
            return false;
        }

        return localizacao.toLowerCase().contains(termoBusca);
    }

    @Override
    public String getDescricao() {
        return filtroDecorado.getDescricao() + " + Localização: '" + termoBusca + "'";
    }
}