package model.prototype;

/**
 * PADRÃO: Prototype
 * PAPEL: Prototype (Interface)
 * FUNÇÃO: Define o contrato de clonagem que todos os anúncios devem seguir.
 *
 * O método clonar() permite criar cópias independentes do objeto.
 */
public interface AnuncioPrototype {

    /**
     * Cria uma cópia profunda (deep copy) do anúncio.
     * A cópia deve ser completamente independente do original.
     *
     * @return Nova instância com os mesmos valores do original
     */
    AnuncioPrototype clonar();

    /**
     * Retorna uma chave identificadora do tipo de protótipo.
     * Usado para registro e recuperação de protótipos.
     *
     * @return Chave única do protótipo (ex: "APARTAMENTO_PADRAO_2Q")
     */
    String getChavePrototipo();
}