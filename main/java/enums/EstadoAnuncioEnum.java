package enums;

/**
 * Enum que representa os estados do ciclo de vida do anúncio.
 *
 * Conforme RF04:
 * - Rascunho: Estado inicial
 * - Pendente de Moderação: Em revisão
 * - Ativo: Aprovado e visível
 * - Vendido: Estado final (arquivado)
 * - Suspenso: Reprovado ou retirado
 */
public enum EstadoAnuncio {

    RASCUNHO("Rascunho"),
    PENDENTE_MODERACAO("Pendente de Moderação"),
    ATIVO("Ativo"),
    VENDIDO("Vendido"),
    SUSPENSO("Suspenso");

    private final String descricao;

    EstadoAnuncio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
