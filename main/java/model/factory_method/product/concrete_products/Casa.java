package model.factory_method.product.concrete_products;

import model.factory_method.product.Imovel;

/**
 * PADRÃO: Factory Method
 * PAPEL: Concrete Product
 * FUNÇÃO: Representa um imóvel do tipo Casa com suas características específicas
 *
 * Características específicas de Casa:
 * - Possui quintal
 * - Número de andares
 * - Área do terreno (pode ser maior que a área construída)
 */
public class Casa extends Imovel {

    // Atributos específicos de Casa
    private boolean possuiQuintal;
    private double areaQuintal;
    private int numeroAndares;
    private double areaTerreno;

    public Casa(double area, String localizacao) {
        super("Casa", area, localizacao);
        this.numeroAndares = 1; // Valor padrão
    }

    // Getters e Setters específicos
    public boolean isPossuiQuintal() {
        return possuiQuintal;
    }

    public void setPossuiQuintal(boolean possuiQuintal) {
        this.possuiQuintal = possuiQuintal;
    }

    public double getAreaQuintal() {
        return areaQuintal;
    }

    public void setAreaQuintal(double areaQuintal) {
        this.areaQuintal = areaQuintal;
        if (areaQuintal > 0) {
            this.possuiQuintal = true;
        }
    }

    public int getNumeroAndares() {
        return numeroAndares;
    }

    public void setNumeroAndares(int numeroAndares) {
        this.numeroAndares = numeroAndares;
    }

    public double getAreaTerreno() {
        return areaTerreno;
    }

    public void setAreaTerreno(double areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

    @Override
    public String getDescricaoDetalhada() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CASA ===\n");
        sb.append(getDescricaoBase()).append("\n");
        sb.append("--- Características Específicas ---\n");
        sb.append("Número de Andares: ").append(numeroAndares).append("\n");
        sb.append("Área do Terreno: ").append(areaTerreno).append(" m²\n");
        sb.append("Quintal: ").append(possuiQuintal ? "Sim (" + areaQuintal + " m²)" : "Não");
        return sb.toString();
    }

    @Override
    public boolean isValido() {
        return super.isValido() &&
                numeroAndares > 0 &&
                areaTerreno >= area;
    }
}