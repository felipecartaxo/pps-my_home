package model.factory_method.factory;

import model.factory_method.product.Imovel;

/**
 * PADRÃO: Factory Method
 * PAPEL: Creator (Interface)
 * FUNÇÃO: Define o contrato para todas as fábricas de imóveis
 *
 * O método criarImovel() é o "Factory Method" que será implementado pelas factories concretas.
 */
public interface ImovelFactory {

    // Factory Method: Cria um imóvel com os parâmetros básicos obrigatórios.
    Imovel criarImovel(double area, String localizacao);

    // Retorna o tipo de imóvel que esta factory cria
    String getTipoImovel();
}