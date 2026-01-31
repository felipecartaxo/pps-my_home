package model.prototype;

/**
 * PADRÃO: Prototype
 * PAPEL: Prototype (Interface)
 * FUNÇÃO: Define o contrato de clonagem que todos os anúncios devem seguir
*/
public interface AnuncioPrototype {

    // Permite criar cópias independentes do objeto
    AnuncioPrototype clonar();

    /**
     * Retorna uma chave identificadora do tipo de protótipo.
     * Usado para registro e recuperação de protótipos.
     */
    String getChavePrototipo();
}