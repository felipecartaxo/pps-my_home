package enums;

/**
 * Enum que representa os tipos de transação disponíveis no sistema.
 *
 * Conforme especificação do documento:
 * - Venda
 * - Aluguel
 * - Temporada
 */
public enum TipoTransacao {
    VENDA("Venda"),
    ALUGUEL("Aluguel"),
    TEMPORADA("Temporada");

    private final String descricao;

    TipoTransacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}