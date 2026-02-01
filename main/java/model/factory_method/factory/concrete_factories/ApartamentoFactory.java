package model.factory_method.factory.concrete_factories;

import model.factory_method.factory.ImovelFactory;
import model.factory_method.product.Imovel;
import model.factory_method.product.concrete_products.Apartamento;

/**
 * PADRÃO: Factory Method
 * PAPEL: Concrete Creator
 * FUNÇÃO: Factory responsável pela criação de imóveis do tipo Apartamento
 * Encapsula toda a lógica de instanciação e configuração inicial de apartamentos
 */
public class ApartamentoFactory implements ImovelFactory {

    @Override
    public Imovel criarImovel(double area, String localizacao) {
        return new Apartamento(area, localizacao);
    }

    /**
     * Método específico para criar apartamento com configurações detalhadas.
     */
    public Apartamento criarApartamentoCompleto(double area, String localizacao,
                                                int andar, String numeroApto,
                                                String nomeCondominio,
                                                boolean temElevador,
                                                double valorCondominio,
                                                int quartos, int banheiros) {
        Apartamento apto = new Apartamento(area, localizacao);
        apto.setAndar(andar);
        apto.setNumeroApartamento(numeroApto);
        apto.setNomeCondominio(nomeCondominio);
        apto.setPossuiElevador(temElevador);
        apto.setValorCondominio(valorCondominio);
        apto.setNumeroQuartos(quartos);
        apto.setNumeroBanheiros(banheiros);

        return apto;
    }

    @Override
    public String getTipoImovel() {
        return "Apartamento";
    }
}