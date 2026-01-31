package model.decorator;

import model.prototype.concrete_prototype.Anuncio;

import java.util.List;

/**
 * PADRÃO: Decorator
 * PAPEL: Component (Interface)
 * FUNÇÃO: Define a interface comum para todos os filtros de busca,
 * tanto o componente base quanto os decoradores
 *
 * CONTEXTO (RF06):
 * - Usuários podem aplicar múltiplos critérios de filtragem
 * - Filtros podem ser combinados dinamicamente
 * - Novos filtros podem ser adicionados sem modificar código existente
 */
public interface FiltroBusca {

    // Aplica o filtro sobre a lista de anúncios
    List<Anuncio> filtrar(List<Anuncio> anuncios);

    // Retorna uma descrição do filtro aplicado
    String getDescricao();
}