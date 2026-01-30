package model.factory_method.factory.concrete_factories;

import model.factory_method.factory.ImovelFactory;
import model.factory_method.product.Imovel;
import model.factory_method.product.concrete_products.SalaComercial;

/**
 * PADRÃO: Factory Method
 * PAPEL: Concrete CreatorFactory responsável pela criação de imóveis do tipo Sala Comercial.
 * FUNÇÃO: Encapsula toda a lógica de instanciação e configuração inicial de salas comerciais.
 */
public class SalaComercialFactory implements ImovelFactory {

    @Override
    public Imovel criarImovel(double area, String localizacao) {
        SalaComercial sala = new SalaComercial(area, localizacao);
        // Configuração padrão: pelo menos 1 ambiente
        sala.setNumeroAmbientes(1);
        return sala;
    }

    /**
     * Método específico para criar sala comercial com configurações detalhadas.
     */
    public SalaComercial criarSalaCompleta(double area, String localizacao,
                                           SalaComercial.TipoUsoComercial tipoUso,
                                           int andar, int ambientes,
                                           boolean temRecepcao,
                                           boolean temAr,
                                           double valorCondominio) {
        SalaComercial sala = new SalaComercial(area, localizacao);
        sala.setTipoUso(tipoUso);
        sala.setAndar(andar);
        sala.setNumeroAmbientes(ambientes);
        sala.setPossuiRecepcao(temRecepcao);
        sala.setPossuiArCondicionado(temAr);
        sala.setValorCondominio(valorCondominio);

        return sala;
    }

    @Override
    public String getTipoImovel() {
        return "Sala Comercial";
    }
}