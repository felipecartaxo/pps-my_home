package model.decorator;

import model.prototype.concrete_prototype.Anuncio;

import java.util.List;

/**
 * PADRÃO: Decorator
 * PAPEL: Decorator (Abstract)
 * FUNÇÃO: Classe abstrata que mantém referência ao componente decorado
 * e define a estrutura base para todos os decoradores concretos
 */
public abstract class FiltroDecorator implements FiltroBusca {

    // Referência ao componente decorado
    protected final FiltroBusca filtroDecorado;

    // Construtor que recebe o componente a ser decorado
    protected FiltroDecorator(FiltroBusca filtroDecorado) {
        this.filtroDecorado = filtroDecorado;
    }

    /**
     * Implementação padrão que delega para o componente decorado
     * Decoradores concretos devem sobrescrever para adicionar comportamento
     */
    @Override
    public List<Anuncio> filtrar(List<Anuncio> anuncios) {
        return filtroDecorado.filtrar(anuncios);
    }

    // Retorna descrição combinada do filtro decorado e do atual
    @Override
    public String getDescricao() {
        return filtroDecorado.getDescricao();
    }
}