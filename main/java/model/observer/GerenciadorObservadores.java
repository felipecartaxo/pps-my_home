package model.observer;

import model.prototype.concrete_prototype.Anuncio;

import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO: Observer
 * PAPEL: Subject
 * FUNÇÃO: Gerencia a lista de observadores e coordena as notificações
 *
 * Esta classe extrai a responsabilidade de gerenciamento de observers
 * do Anuncio, promovendo maior separação de responsabilidades
 */
public class GerenciadorObservadores {

    private final List<ObservadorAnuncio> observadores;

    public GerenciadorObservadores() {
        this.observadores = new ArrayList<>();
    }

    // Adiciona um observador à lista de notificação
    public void adicionar(ObservadorAnuncio observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    // Remove um observador da lista de notificação
    public void remover(ObservadorAnuncio observador) {
        observadores.remove(observador);
    }

    // Notifica todos os observadores sobre uma mudança de estado
    public void notificar(Anuncio anuncio, String estadoAnterior, String estadoNovo) {
        for (ObservadorAnuncio observador : observadores) {
            observador.notificar(anuncio, estadoAnterior, estadoNovo);
        }
    }

    // Retorna a quantidade de observadores registrados
    public int getQuantidadeObservadores() {
        return observadores.size();
    }
}