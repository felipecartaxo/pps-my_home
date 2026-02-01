package model.factory_method.factory.concrete_factories;

import model.factory_method.factory.ImovelFactory;
import model.factory_method.product.Imovel;
import model.factory_method.product.concrete_products.Galpao;

/**
 * PADRÃO: Factory Method
 * PAPEL: Concrete Creator
 * FUNÇÃO: Factory responsável pela criação de imóveis do tipo Galpão. Encapsula toda a lógica de instanciação e configuração inicial de galpões
 */
public class GalpaoFactory implements ImovelFactory {

    @Override
    public Imovel criarImovel(double area, String localizacao) {
        Galpao galpao = new Galpao(area, localizacao);
        // Configurações padrão para galpão
        galpao.setPeDireito(8.0); // 8 metros é comum para galpões
        galpao.setCapacidadeCargaPiso(5000); // 5 ton/m²
        return galpao;
    }

    // Método específico para criar galpão com configurações detalhadas
    public Galpao criarGalpaoCompleto(double area, String localizacao,
                                      Galpao.TipoUsoGalpao tipoUso,
                                      double peDireito,
                                      double capacidadeCarga,
                                      int quantidadeDocas,
                                      double areaAdministrativa) {
        Galpao galpao = new Galpao(area, localizacao);
        galpao.setTipoUso(tipoUso);
        galpao.setPeDireito(peDireito);
        galpao.setCapacidadeCargaPiso(capacidadeCarga);
        galpao.setQuantidadeDocas(quantidadeDocas);
        galpao.setAreaAdministrativa(areaAdministrativa);

        return galpao;
    }

    @Override
    public String getTipoImovel() {
        return "Galpão";
    }
}