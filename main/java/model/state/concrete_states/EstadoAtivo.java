package model.state.concrete_states;

import model.prototype.concrete_prototype.Anuncio;
import model.state.EstadoAnuncio;

    /**
     * PADRÃO: State
     * PAPEL: ConcreteState
     * FUNÇÃO: Implementa o comportamento específico do estado Ativo.
     *
     * RESPONSABILIDADE:
     * - Representa o anúncio publicado e visível publicamente
     * - Permite transições para Vendido (negócio fechado) ou Suspenso (retirado)
     *
     * TRANSIÇÕES PERMITIDAS:
     * - vender() -> EstadoVendido
     * - suspender() -> EstadoSuspenso
     *
     * REGRA DE NEGÓCIO (RF04):
     * - O anúncio está visível para todos os usuários do sistema
     * - Pode ser encontrado nas buscas (RF06)
     * - Pode ser marcado como vendido/alugado
     * - Pode ser suspenso pelo anunciante a qualquer momento
     */
    public class EstadoAtivo extends EstadoAnuncio {

        public EstadoAtivo() {
            super("Ativo");
        }

        /**
         * Marca o anúncio como vendido/alugado - transição para Vendido.
         * Chamado quando o negócio é fechado.
         */
        @Override
        public void vender(Anuncio anuncio) {
            // Transição: Ativo -> Vendido/Alugado
            anuncio.setEstadoInterno(new EstadoVendido());
        }

        /**
         * Suspende o anúncio - transição para Suspenso.
         * Chamado quando o anunciante decide retirar o anúncio.
         */
        @Override
        public void suspender(Anuncio anuncio) {
            // Transição: Ativo -> Suspenso
            anuncio.setEstadoInterno(new EstadoSuspenso());
        }
    }
