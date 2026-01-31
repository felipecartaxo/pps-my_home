package model.factory_method.factory.concrete_factories;

import model.factory_method.factory.ImovelFactory;
import model.factory_method.product.concrete_products.Casa;
import model.factory_method.product.Imovel;

/**
 * PADRÃO: Factory Method
 * PAPEL: Concrete Creator
 * FUNÇÃO: Factory responsável pela criação de imóveis do tipo Casa. Encapsula toda a lógica de instanciação e configuração inicial de casas
 */
public class CasaFactory implements ImovelFactory {

    @Override
    public Imovel criarImovel(double area, String localizacao) {
        Casa casa = new Casa(area, localizacao);

        // Configurações padrão para uma casa
        // Área do terreno por padrão é igual à área construída
        casa.setAreaTerreno(area);

        return casa;
    }

    /**
     * Método específico para criar casa com configurações detalhadas.
     * Demonstra que factories podem ter métodos adicionais para
     * cenários específicos de criação.
     */
    public Casa criarCasaCompleta(double area, String localizacao,
                                  int quartos, int banheiros,
                                  int andares, double areaTerreno,
                                  boolean temQuintal, double areaQuintal) {
        Casa casa = new Casa(area, localizacao);
        casa.setNumeroQuartos(quartos);
        casa.setNumeroBanheiros(banheiros);
        casa.setNumeroAndares(andares);
        casa.setAreaTerreno(areaTerreno);
        casa.setPossuiQuintal(temQuintal);
        casa.setAreaQuintal(areaQuintal);

        return casa;
    }

    @Override
    public String getTipoImovel() {
        return "Casa";
    }
}