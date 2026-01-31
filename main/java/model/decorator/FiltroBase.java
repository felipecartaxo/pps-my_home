package model.decorator;

import model.prototype.concrete_prototype.Anuncio;

import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO: Decorator
 * PAPEL: ConcreteComponent
 * FUNÇÃO: Implementação base que retorna todos os anúncios sem aplicar filtro
 * Serve como ponto de partida para a cadeia de decoradores
 *
 * Esta classe representa a "busca sem filtros", retornando todos os anúncios
 * disponíveis. Os decoradores serão aplicados sobre ela para refinar os resultados
 */
public class FiltroBase implements FiltroBusca {

    // Retorna todos os anúncios sem aplicar nenhum filtro
    @Override
    public List<Anuncio> filtrar(List<Anuncio> anuncios) {
        // Retorna cópia da lista para evitar modificações externas
        return new ArrayList<>(anuncios);
    }

    @Override
    public String getDescricao() {
        return "Busca base (sem filtros)";
    }
}