package enums;

public enum Topografia {
    PLANO("Plano"),
    ACLIVE("Aclive"),
    DECLIVE("Declive"),
    IRREGULAR("Irregular");

    private final String descricao;

    Topografia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}