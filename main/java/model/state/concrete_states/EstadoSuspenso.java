package model.state.concrete_states;

import model.prototype.concrete_prototype.Anuncio;
import model.state.EstadoAnuncio;

/**
 * PADRÃO: State
 * PAPEL: ConcreteState
 * FUNÇÃO: Implementa o comportamento específico do estado Suspenso.
 *
 * RESPONSABILIDADE:
 * - Representa o anúncio reprovado na moderação OU retirado pelo anunciante
 * - Permite transição para Rascunho (para correções e reenvio)
 *
 * TRANSIÇÕES PERMITIDAS:
 * - voltarParaRascunho() -> EstadoRascunho
 *
 * REGRA DE NEGÓCIO (RF04):
 * - O anúncio foi reprovado na moderação (RF03) OU suspenso pelo anunciante
 * - Não está visível publicamente
 * - Pode voltar para rascunho para correções e nova submissão
 * - Anunciante deve corrigir os problemas antes de reenviar
 */
public class EstadoSuspenso extends EstadoAnuncio {

    public EstadoSuspenso() {
        super("Suspenso");
    }

    /**
     * Retorna o anúncio para rascunho - transição para Rascunho.
     * Chamado quando o anunciante deseja corrigir o anúncio e tentar novamente
     */
    @Override
    public void voltarParaRascunho(Anuncio anuncio) {
        // Transição: Suspenso -> Rascunho
        anuncio.setEstadoInterno(new EstadoRascunho());
    }
}