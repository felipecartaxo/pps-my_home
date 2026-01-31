package model.observer;

import model.prototype.concrete_prototype.Anuncio;

import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO: Observer
 * PAPEL: Subject
 * FUNÇÃO: Gerencia a lista de observadores e coordena as notificações.
 *
 * Esta classe extrai a responsabilidade de gerenciamento de observers
 * do Anuncio, promovendo maior separação de responsabilidades.
 *
 * JUSTIFICATIVA:
 * - O GoF não exige que o Subject seja a própria classe de domínio
 * - O importante é que o papel de Subject seja cumprido
 * - Usar composição permite que Anuncio foque em suas responsabilidades de domínio
 *
 * PRINCÍPIOS APLICADOS:
 * - SRP: Esta classe tem apenas uma responsabilidade (gerenciar observers)
 * - OCP: Novos observers podem ser adicionados sem modificar esta classe
 * - GRASP - Pure Fabrication: Classe criada para melhorar coesão do design
 * - GRASP - Low Coupling: Desacopla Anuncio dos detalhes de notificação
 */
public class GerenciadorObservadores {

    private final List<ObservadorAnuncio> observadores;

    public GerenciadorObservadores() {
        this.observadores = new ArrayList<>();
    }

    /**
     * Adiciona um observador à lista de notificação.
     *
     * @param observador Observador a ser adicionado
     */
    public void adicionar(ObservadorAnuncio observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    /**
     * Remove um observador da lista de notificação.
     *
     * @param observador Observador a ser removido
     */
    public void remover(ObservadorAnuncio observador) {
        observadores.remove(observador);
    }

    /**
     * Notifica todos os observadores sobre uma mudança de estado.
     *
     * @param anuncio Anúncio que teve o estado alterado
     * @param estadoAnterior Estado antes da mudança
     * @param estadoNovo Estado após a mudança
     */
    public void notificar(Anuncio anuncio, String estadoAnterior, String estadoNovo) {
        for (ObservadorAnuncio observador : observadores) {
            observador.notificar(anuncio, estadoAnterior, estadoNovo);
        }
    }

    /**
     * Retorna a quantidade de observadores registrados.
     */
    public int getQuantidadeObservadores() {
        return observadores.size();
    }
}