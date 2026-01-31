package model.observer;

import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Observer
 * PAPEL: Subscriber (Interface)
 * FUNÇÃO: Define o contrato para todos os observadores de mudanças em anúncios.
 *
 * RESPONSABILIDADE:
 * - Definir o método de notificação que todos os observadores devem implementar
 *
 * CONTEXTO (RF04):
 * - Sempre que um anúncio mudar de estado, observadores registrados são notificados
 * - Exemplos de observadores: AnuncianteObserver, LogObserver
 */
public interface ObservadorAnuncio {

    /**
     * Chamado quando o estado do anúncio é alterado.
     *
     * Este método é invocado pelo Anuncio sempre que ocorre
     * uma transição de estado bem-sucedida.
     */
    void notificar(Anuncio anuncio, String estadoAnterior, String estadoNovo);
}