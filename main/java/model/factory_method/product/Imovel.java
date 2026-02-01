package model.factory_method.product;

/**
 * PADRÃO: Factory Method
 * PAPEL: Produto Abstrato
 * FUNÇÃO: Classe abstrata que implementa comportamentos comuns a todos os imóveis.
 */
public abstract class Imovel {

    // Atributos comuns a todos os tipos de imóveis
    protected String tipo;
    protected double area;
    protected String localizacao;
    protected int numeroQuartos;
    protected int numeroBanheiros;
    protected boolean possuiGaragem;

    // Construtor protegido - só pode ser chamado pelas subclasses
    protected Imovel(String tipo, double area, String localizacao) {
        this.tipo = tipo;
        this.area = area;
        this.localizacao = localizacao;
    }

    /**
     * Retorna o tipo do imóvel.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna a área do imóvel em metros quadrados.
     */
    public double getArea() {
        return area;
    }

    /**
     * Retorna a localização/endereço do imóvel.
     */
    public String getLocalizacao() {
        return localizacao;
    }

    public int getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(int numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public int getNumeroBanheiros() {
        return numeroBanheiros;
    }

    public void setNumeroBanheiros(int numeroBanheiros) {
        this.numeroBanheiros = numeroBanheiros;
    }

    public boolean isPossuiGaragem() {
        return possuiGaragem;
    }

    public void setPossuiGaragem(boolean possuiGaragem) {
        this.possuiGaragem = possuiGaragem;
    }

    /**
     * Validação comum a todos os imóveis
     * Subclasses podem sobrescrever para adicionar validações específicas
     */
    public boolean isValido() {
        return area > 0 &&
                localizacao != null &&
                !localizacao.trim().isEmpty();
    }

    /**
     * Retorna descrição base comum a todos os imóveis
     * Subclasses devem complementar com suas características específicas
     */
    protected String getDescricaoBase() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(tipo).append("\n");
        sb.append("Área: ").append(area).append(" m²\n");
        sb.append("Localização: ").append(localizacao).append("\n");
        sb.append("Quartos: ").append(numeroQuartos).append("\n");
        sb.append("Banheiros: ").append(numeroBanheiros).append("\n");
        sb.append("Garagem: ").append(possuiGaragem ? "Sim" : "Não");
        return sb.toString();
    }

    /**
     * Método abstrato que cada ConcreteProduct deve implementar
     * Retorna uma descrição detalhada incluindo características específicas
     */
    public abstract String getDescricaoDetalhada();
}