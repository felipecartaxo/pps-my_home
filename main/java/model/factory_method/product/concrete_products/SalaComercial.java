package model.factory_method.product.concrete_products;

import model.factory_method.product.Imovel;

/**
 * PADRÃO: Factory Method
 * PAPEL: Concrete Product
 * FUNÇÃO: Representa um imóvel do tipo Sala Comercial com suas características específicas
 *
 * Características específicas de Sala Comercial:
 * - Número de salas/ambientes
 * - Possui recepção
 * - Tipo de uso (escritório, consultório, loja)
 * - Piso
 */
public class SalaComercial extends Imovel {

    public enum TipoUsoComercial {
        ESCRITORIO("Escritório"),
        CONSULTORIO("Consultório"),
        LOJA("Loja"),
        COWORKING("Coworking"),
        OUTROS("Outros");

        private final String descricao;

        TipoUsoComercial(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    // Atributos específicos de Sala Comercial
    private int numeroAmbientes;
    private boolean possuiRecepcao;
    private TipoUsoComercial tipoUso;
    private int andar;
    private double valorCondominio;
    private boolean possuiArCondicionado;

    public SalaComercial(double area, String localizacao) {
        super("Sala Comercial", area, localizacao);
        this.tipoUso = TipoUsoComercial.ESCRITORIO;
        this.numeroQuartos = 0;
    }

    // Getters e Setters específicos
    public int getNumeroAmbientes() {
        return numeroAmbientes;
    }

    public void setNumeroAmbientes(int numeroAmbientes) {
        this.numeroAmbientes = numeroAmbientes;
    }

    public boolean isPossuiRecepcao() {
        return possuiRecepcao;
    }

    public void setPossuiRecepcao(boolean possuiRecepcao) {
        this.possuiRecepcao = possuiRecepcao;
    }

    public TipoUsoComercial getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(TipoUsoComercial tipoUso) {
        this.tipoUso = tipoUso;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public double getValorCondominio() {
        return valorCondominio;
    }

    public void setValorCondominio(double valorCondominio) {
        this.valorCondominio = valorCondominio;
    }

    public boolean isPossuiArCondicionado() {
        return possuiArCondicionado;
    }

    public void setPossuiArCondicionado(boolean possuiArCondicionado) {
        this.possuiArCondicionado = possuiArCondicionado;
    }

    @Override
    public String getDescricaoDetalhada() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== SALA COMERCIAL ===\n");
        sb.append("Tipo: ").append(tipo).append("\n");
        sb.append("Área: ").append(area).append(" m²\n");
        sb.append("Localização: ").append(localizacao).append("\n");
        sb.append("Banheiros: ").append(numeroBanheiros).append("\n");
        sb.append("Garagem: ").append(possuiGaragem ? "Sim" : "Não").append("\n");
        sb.append("--- Características Específicas ---\n");
        sb.append("Tipo de Uso: ").append(tipoUso.getDescricao()).append("\n");
        sb.append("Andar: ").append(andar).append("º\n");
        sb.append("Ambientes: ").append(numeroAmbientes).append("\n");
        sb.append("Recepção: ").append(possuiRecepcao ? "Sim" : "Não").append("\n");
        sb.append("Ar Condicionado: ").append(possuiArCondicionado ? "Sim" : "Não").append("\n");
        sb.append("Condomínio: R$ ").append(String.format("%.2f", valorCondominio));
        return sb.toString();
    }

    @Override
    public boolean isValido() {
        return super.isValido() &&
                numeroAmbientes > 0 &&
                tipoUso != null;
    }
}