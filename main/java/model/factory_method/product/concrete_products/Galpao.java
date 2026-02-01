package model.factory_method.product.concrete_products;

import model.factory_method.product.Imovel;

/**
 * PADRÃO: Factory Method
 * PAPEL: Concrete Product
 * FUNÇÃO: Representa um imóvel do tipo Galpão com suas características específicas
 *
 * Características específicas de Galpão:
 * - Pé direito (altura)
 * - Capacidade de carga do piso
 * - Possui doca para carga/descarga
 * - Tipo de uso (logística, industrial, armazenagem)
 */
public class Galpao extends Imovel {

    public enum TipoUsoGalpao {
        LOGISTICA("Logística"),
        INDUSTRIAL("Industrial"),
        ARMAZENAGEM("Armazenagem"),
        COMERCIAL("Comercial");

        private final String descricao;

        TipoUsoGalpao(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    // Atributos específicos de Galpão
    private double peDireito;
    private double capacidadeCargaPiso;
    private boolean possuiDoca;
    private int quantidadeDocas;
    private TipoUsoGalpao tipoUso;
    private boolean possuiAreaAdministrativa;
    private double areaAdministrativa;

    public Galpao(double area, String localizacao) {
        super("Galpão", area, localizacao);
        this.tipoUso = TipoUsoGalpao.ARMAZENAGEM;
        this.numeroQuartos = 0;
    }

    // Getters e Setters específicos
    public double getPeDireito() {
        return peDireito;
    }

    public void setPeDireito(double peDireito) {
        this.peDireito = peDireito;
    }

    public double getCapacidadeCargaPiso() {
        return capacidadeCargaPiso;
    }

    public void setCapacidadeCargaPiso(double capacidadeCargaPiso) {
        this.capacidadeCargaPiso = capacidadeCargaPiso;
    }

    public boolean isPossuiDoca() {
        return possuiDoca;
    }

    public void setPossuiDoca(boolean possuiDoca) {
        this.possuiDoca = possuiDoca;
    }

    public int getQuantidadeDocas() {
        return quantidadeDocas;
    }

    public void setQuantidadeDocas(int quantidadeDocas) {
        this.quantidadeDocas = quantidadeDocas;
        if (quantidadeDocas > 0) {
            this.possuiDoca = true;
        }
    }

    public int getNumeroDocas() {
        return quantidadeDocas;
    }

    public TipoUsoGalpao getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(TipoUsoGalpao tipoUso) {
        this.tipoUso = tipoUso;
    }

    public boolean isPossuiAreaAdministrativa() {
        return possuiAreaAdministrativa;
    }

    public void setPossuiAreaAdministrativa(boolean possuiAreaAdministrativa) {
        this.possuiAreaAdministrativa = possuiAreaAdministrativa;
    }

    public double getAreaAdministrativa() {
        return areaAdministrativa;
    }

    public void setAreaAdministrativa(double areaAdministrativa) {
        this.areaAdministrativa = areaAdministrativa;
        if (areaAdministrativa > 0) {
            this.possuiAreaAdministrativa = true;
        }
    }

    @Override
    public String getDescricaoDetalhada() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== GALPÃO ===\n");
        sb.append("Tipo: ").append(tipo).append("\n");
        sb.append("Área Total: ").append(area).append(" m²\n");
        sb.append("Localização: ").append(localizacao).append("\n");
        sb.append("Banheiros: ").append(numeroBanheiros).append("\n");
        sb.append("--- Características Específicas ---\n");
        sb.append("Tipo de Uso: ").append(tipoUso.getDescricao()).append("\n");
        sb.append("Pé Direito: ").append(peDireito).append(" m\n");
        sb.append("Capacidade do Piso: ").append(capacidadeCargaPiso).append(" kg/m²\n");
        sb.append("Docas: ").append(possuiDoca ? "Sim (" + quantidadeDocas + ")" : "Não").append("\n");
        sb.append("Área Administrativa: ").append(possuiAreaAdministrativa ? areaAdministrativa + " m²" : "Não");
        return sb.toString();
    }

    @Override
    public boolean isValido() {
        return super.isValido() &&
                peDireito > 0 &&
                tipoUso != null;
    }
}