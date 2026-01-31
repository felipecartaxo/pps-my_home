package model.state.concrete_states;

import model.state.EstadoAnuncio;

/**
 * PADRÃO: State
 * PAPEL: ConcreteState
 * FUNÇÃO: Implementa o comportamento específico do estado Vendido/Alugado.
 *
 * RESPONSABILIDADE:
 * - Representa o estado final do anúncio (imóvel negociado)
 * - Não permite nenhuma transição (estado terminal)
 *
 * TRANSIÇÕES PERMITIDAS:
 * - Nenhuma (estado final)
 *
 * REGRA DE NEGÓCIO (RF04):
 * - O imóvel foi vendido ou alugado
 * - O anúncio é arquivado e não pode mais ser alterado
 * - Não está mais visível nas buscas públicas
 * - Mantido no sistema apenas para histórico
 *
 * NOTA: Esta classe não sobrescreve nenhum método da classe base,
 * pois todas as operações herdam a implementação vazia (não permitidas).
 */
public class EstadoVendido extends EstadoAnuncio {

    public EstadoVendido() {
        super("Vendido/Alugado");
    }
}