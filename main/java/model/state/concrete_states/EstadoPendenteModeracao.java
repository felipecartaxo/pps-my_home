package model.state.concrete_states;

import model.prototype.concrete_prototype.Anuncio;
import model.state.EstadoAnuncio;

/**
 * PADRÃO: State
 * PAPEL: ConcreteState
 * FUNÇÃO: Implementa o comportamento específico do estado Pendente de Moderação.
 *
 * RESPONSABILIDADE:
 * - Representa o anúncio aguardando revisão (automática ou manual)
 * - Permite transições para Ativo (aprovado) ou Suspenso (reprovado)
 * - Permite que anunciante suspenda (desista) durante a moderação
 *
 * TRANSIÇÕES PERMITIDAS:
 * - aprovar() -> EstadoAtivo
 * - reprovar() -> EstadoSuspenso
 * - suspender() -> EstadoSuspenso (anunciante desiste)
 *
 * REGRA DE NEGÓCIO (RF04):
 * - O anúncio está aguardando revisão automática ou manual
 * - Pode ser aprovado, reprovado ou suspenso pelo próprio anunciante
 * - Não está visível publicamente enquanto pendente
 *
 * INTEGRAÇÃO COM RF03:
 * - A moderação automática (Chain of Responsibility) decide se aprova ou reprova
 */
public class EstadoPendenteModeracao extends EstadoAnuncio {

    public EstadoPendenteModeracao() {
        super("Pendente de Moderação");
    }

    /**
     * Aprova o anúncio - transição para Ativo.
     * Chamado pelo sistema de moderação (RF03) quando todas validações passam.
     */
    @Override
    public void aprovar(Anuncio anuncio) {
        // Transição: Pendente de Moderação -> Ativo
        anuncio.setEstadoInterno(new EstadoAtivo());
    }

    /**
     * Reprova o anúncio - transição para Suspenso.
     * Chamado pelo sistema de moderação (RF03) quando validações falham.
     */
    @Override
    public void reprovar(Anuncio anuncio) {
        // Transição: Pendente de Moderação -> Suspenso
        anuncio.setEstadoInterno(new EstadoSuspenso());
    }

    /**
     * Suspende o anúncio - transição para Suspenso.
     * Chamado quando o anunciante desiste durante a moderação.
     */
    @Override
    public void suspender(Anuncio anuncio) {
        // Transição: Pendente de Moderação -> Suspenso (anunciante desistiu)
        anuncio.setEstadoInterno(new EstadoSuspenso());
    }
}