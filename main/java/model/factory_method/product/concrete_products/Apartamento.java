package model.factory_method.product.concrete_products;

import model.factory_method.product.ImovelBase;

/**
 * PADRÃO: Factory Method
 * PAPEL: Concrete Product
 * FUNÇÃO: Representa um imóvel do tipo Apartamento com suas características específicas.
 *
 * Características específicas de Apartamento:
 * - Número do andar
 * - Possui elevador
 * - Valor do condomínio
 * - Número do apartamento
 */
public class Apartamento extends ImovelBase {

    // Atributos específicos de Apartamento
    private int andar;
    private boolean possuiElevador;
    private double valorCondominio;
    private String numeroApartamento;
    private String nomeCondominio;

    public Apartamento(double area, String localizacao) {
        super("Apartamento", area, localizacao);
    }

    // Getters e Setters específicos
    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public boolean isPossuiElevador() {
        return possuiElevador;
    }

    public void setPossuiElevador(boolean possuiElevador) {
        this.possuiElevador = possuiElevador;
    }

    public double getValorCondominio() {
        return valorCondominio;
    }

    public void setValorCondominio(double valorCondominio) {
        this.valorCondominio = valorCondominio;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public String getNomeCondominio() {
        return nomeCondominio;
    }

    public void setNomeCondominio(String nomeCondominio) {
        this.nomeCondominio = nomeCondominio;
    }

    @Override
    public String getDescricaoDetalhada() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== APARTAMENTO ===\n");
        sb.append(getDescricaoBase()).append("\n");
        sb.append("--- Características Específicas ---\n");
        sb.append("Condomínio: ").append(nomeCondominio != null ? nomeCondominio : "Não informado").append("\n");
        sb.append("Apartamento Nº: ").append(numeroApartamento != null ? numeroApartamento : "Não informado").append("\n");
        sb.append("Andar: ").append(andar).append("º\n");
        sb.append("Elevador: ").append(possuiElevador ? "Sim" : "Não").append("\n");
        sb.append("Condomínio: R$ ").append(String.format("%.2f", valorCondominio));
        return sb.toString();
    }

    @Override
    public boolean isValido() {
        return super.isValido() &&
                andar >= 0 &&
                valorCondominio >= 0;
    }
}