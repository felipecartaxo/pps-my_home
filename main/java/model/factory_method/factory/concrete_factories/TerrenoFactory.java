package model.factory_method.factory.concrete_factories;

import model.factory_method.factory.ImovelFactory;
import model.factory_method.product.Imovel;
import model.factory_method.product.concrete_products.Terreno;
import enums.Topografia;

/**
 * PADRÃO: Factory Method - Role: Concrete Creator
 *
 * Factory responsável pela criação de imóveis do tipo Terreno.
 * Encapsula toda a lógica de instanciação e configuração inicial de terrenos.
 *
 * PRINCÍPIOS APLICADOS:
 * - SRP (Single Responsibility): Responsável apenas por criar Terrenos
 * - GRASP - Creator: Possui a responsabilidade de criar objetos Terreno
 */
public class TerrenoFactory implements ImovelFactory {

    @Override
    public Imovel criarImovel(double area, String localizacao) {
        Terreno terreno = new Terreno(area, localizacao);
        // Frente padrão estimada (pode ser ajustada depois)
        terreno.setFrenteMetros(Math.sqrt(area) * 0.5);
        return terreno;
    }

    /**
     * Método específico para criar terreno com configurações detalhadas.
     */
    public Terreno criarTerrenoCompleto(double area, String localizacao,
                                        Terreno.TipoTerreno tipoTerreno,
                                        Topografia topografia,
                                        double frenteMetros,
                                        boolean possuiEscritura,
                                        boolean cercado) {
        Terreno terreno = new Terreno(area, localizacao);
        terreno.setTipoTerreno(tipoTerreno);
        terreno.setTopografia(topografia);
        terreno.setFrenteMetros(frenteMetros);
        terreno.setPossuiEscritura(possuiEscritura);
        terreno.setPossuiCercamento(cercado);

        return terreno;
    }

    @Override
    public String getTipoImovel() {
        return "Terreno";
    }
}