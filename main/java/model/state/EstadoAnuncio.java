package model.state;

import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: State
 * PAPEL: State (Abstract Class)
 * FUNÇÃO: Define a interface para encapsular o comportamento associado
 * a um estado particular do Context (Anuncio).
 *
 * Fluxo de estados:
 * 1. Rascunho -> Pendente de Moderação (enviarParaModeracao)
 * 2. Pendente -> Ativo (aprovar) ou Suspenso (reprovar)
 * 3. Ativo -> Vendido (vender) ou Suspenso (suspender)
 * 4. Suspenso -> Rascunho (voltarParaRascunho)
 * 5. Vendido -> Estado final (sem transições)
 */
public abstract class EstadoAnuncio {

    // Nome do estado (para identificação)
    protected final String nomeEstado;

    // Construtor protegido - apenas subclasses podem instanciar
    protected EstadoAnuncio(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    // Envia o anúncio para moderação.
    public void enviarParaModeracao(Anuncio anuncio) {
        // Estados concretos que permitem esta ação devem sobrescrever
    }

    // Aprova o anúncio na moderação.
    public void aprovar(Anuncio anuncio) {
        // Transição válida: Pendente de Moderação -> Ativo
    }

    // Reprova o anúncio na moderação
    public void reprovar(Anuncio anuncio) {
        // Transição válida: Pendente de Moderação -> Suspenso
    }

    // Marca o anúncio como vendido/alugado
    public void vender(Anuncio anuncio) {
        // Transição válida: Ativo -> Vendido/Alugado
    }

    // Suspende o anúncio
    public void suspender(Anuncio anuncio) {
        /* Transições válidas:
          - Pendente de Moderação -> Suspenso (anunciante desiste)
          - Ativo -> Suspenso (anunciante retira)
        */
    }

    // Retorna o anúncio para rascunho (para correções)
    public void voltarParaRascunho(Anuncio anuncio) {
        // Transição válida: Suspenso -> Rascunho
    }

    /**
     * Retorna o nome do estado atual.
     * Método concreto - não precisa ser sobrescrito.
     */
    public String getNomeEstado() {
        return nomeEstado;
    }
}