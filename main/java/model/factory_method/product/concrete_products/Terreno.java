package model.factory_method.product.concrete_products;

import enums.Topografia;
import model.factory_method.product.ImovelBase;

/**
 * PADRÃO: Factory Method
 * PAPEL: Concrete Product
 * FUNÇÃO: Representa um imóvel do tipo Terreno com suas características específicas.
 *
 * Características específicas de Terreno:
 * - Tipo de terreno (urbano, rural)
 * - Se possui escritura
 * - Topografia (plano, aclive, declive)
 * - Frente do terreno em metros
 */
public class Terreno extends ImovelBase {

    public enum TipoTerreno {
        URBANO("Urbano"),
        RURAL("Rural"),
        INDUSTRIAL("Industrial");

        private final String descricao;

        TipoTerreno(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    // Atributos específicos de Terreno
    private TipoTerreno tipoTerreno;
    private boolean possuiEscritura;
    private Topografia topografia;
    private double frenteMetros;
    private boolean possuiCercamento;

    public Terreno(double area, String localizacao) {
        super("Terreno", area, localizacao);
        this.tipoTerreno = TipoTerreno.URBANO; // Padrão
        this.topografia = Topografia.PLANO;    // Padrão
        // Terreno não tem quartos/banheiros/garagem por padrão
        this.numeroQuartos = 0;
        this.numeroBanheiros = 0;
        this.possuiGaragem = false;
    }

    // Getters e Setters específicos
    public TipoTerreno getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(TipoTerreno tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public boolean isPossuiEscritura() {
        return possuiEscritura;
    }

    public void setPossuiEscritura(boolean possuiEscritura) {
        this.possuiEscritura = possuiEscritura;
    }

    public Topografia getTopografia() {
        return topografia;
    }

    public void setTopografia(Topografia topografia) {
        this.topografia = topografia;
    }

    public double getFrenteMetros() {
        return frenteMetros;
    }

    public void setFrenteMetros(double frenteMetros) {
        this.frenteMetros = frenteMetros;
    }

    public boolean isPossuiCercamento() {
        return possuiCercamento;
    }

    public void setPossuiCercamento(boolean possuiCercamento) {
        this.possuiCercamento = possuiCercamento;
    }

    @Override
    public String getDescricaoDetalhada() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== TERRENO ===\n");
        sb.append("Tipo: ").append(tipo).append("\n");
        sb.append("Área: ").append(area).append(" m²\n");
        sb.append("Localização: ").append(localizacao).append("\n");
        sb.append("--- Características Específicas ---\n");
        sb.append("Tipo de Terreno: ").append(tipoTerreno.getDescricao()).append("\n");
        sb.append("Topografia: ").append(topografia.getDescricao()).append("\n");
        sb.append("Frente: ").append(frenteMetros).append(" m\n");
        sb.append("Escritura: ").append(possuiEscritura ? "Sim" : "Não").append("\n");
        sb.append("Cercado: ").append(possuiCercamento ? "Sim" : "Não");
        return sb.toString();
    }

    @Override
    public boolean isValido() {
        return super.isValido() &&
                tipoTerreno != null &&
                frenteMetros > 0;
    }
}