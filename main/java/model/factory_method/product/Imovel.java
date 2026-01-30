package model.factory_method.product;

/**
 * PADRÃO: Factory Method - Role: Product (Interface)
 * PAPEL: Inferface do produto
 * FUNÇÃO: Esta interface define o contrato que todos os tipos de imóveis devem seguir.
 */
public interface Imovel {

    // Retorna o tipo do imóvel (ex: "Casa", "Apartamento", "Terreno")
    String getTipo();

    // Retorna a área do imóvel em metros quadrados
    double getArea();

    // Retorna a localização/endereço do imóvel
    String getLocalizacao();

    // Retorna uma descrição detalhada do imóvel, incluindo suas características específicas
    String getDescricaoDetalhada();

    // Valida se o imóvel possui todas as informações obrigatórias preenchidas corretamente
    boolean isValido();
}