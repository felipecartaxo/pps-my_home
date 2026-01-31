package model.state.concrete_states;

import model.prototype.concrete_prototype.Anuncio;
import model.state.EstadoAnuncio;

/**
 * PADRÃO: State
 * PAPEL: ConcreteState
 * FUNÇÃO: Implementa o comportamento específico do estado Rascunho
 *
 * RESPONSABILIDADE:
 * - Representa o estado inicial do anúncio
 * - Permite transição para Pendente de Moderação
 *
 * TRANSIÇÕES PERMITIDAS:
 * - enviarParaModeracao() -> EstadoPendenteModeracao
 *
 * REGRA DE NEGÓCIO (RF04):
 * - O anúncio permanece em rascunho até ser enviado para moderação
 * - Neste estado, o anunciante pode editar livremente o anúncio
 * - Não está visível publicamente
 */
public class EstadoRascunho extends EstadoAnuncio {

    public EstadoRascunho() {
        super("Rascunho");
    }

    /**
     * Sobrescreve o comportamento padrão para permitir envio para moderação
     *
     * Esta é a única transição válida a partir do estado Rascunho
     */
    @Override
    public void enviarParaModeracao(Anuncio anuncio) {
        // Transição: Rascunho -> Pendente de Moderação
        anuncio.setEstadoInterno(new EstadoPendenteModeracao());
    }

    // Outros métodos (aprovar, reprovar, vender, suspender, voltarParaRascunho) não são sobrescritos - eles herdam a implementação vazia da classe base, pois essas operações não são permitidas no estado Rascunho
}