package model.prototype.prototype_manager;

import enums.TipoTransacao;
import enums.Topografia;
import model.factory_method.factory.concrete_factories.*;
import model.factory_method.product.concrete_products.*;
import model.prototype.AnuncioPrototype;
import model.prototype.concrete_prototype.Anuncio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * PADRÃO: Prototype
 * PAPEL: Prototype Manager
 * FUNÇÃO: Registro centralizado de protótipos de anúncios pré-configurados. Permite registrar, recuperar e clonar protótipos por chave.
 *
 * Esta classe funciona como um "catálogo" de configurações padrão que podem ser usadas para criar novos anúncios rapidamente.
 *
 * OBS.: Dado que esta classe acaba deixando o código mais complexo, a mesma pode ser removida, no entanto, caso optassemos apenas pela abordagem padrão, sempre que um novo protótipo fosse criado, o cliente também teria que ser modificado.
 *
 * Esta classe é mencionada no GOF, segue o trecho do livro:
 *
 * "When the number of prototypes in a system isn't fixed (that is, they can be created and destroyed dynamically), keep a registry of available prototypes. Clients won't manage prototypes themselves but will store and retrieve them from the registry. A client will ask the registry for a prototype before cloning it. We call this registry a prototype manager."
 *
 * A refereência pode ser encontrada aqui: https://www.cs.unc.edu/~stotts/GOF/hires/pat3dfso.htm
 */
public class AnuncioPrototypeRegistry {

    // Mapa de protótipos registrados
    private final Map<String, AnuncioPrototype> prototipos;

    /**
     * Construtor que inicializa o registro com protótipos padrão do sistema.
     */
    public AnuncioPrototypeRegistry() {
        this.prototipos = new HashMap<>();
        registrarPrototiposPadrao();
    }

    /**
     * Registra os protótipos padrão conforme especificado no RF02.
     *
     * Exemplos do requisito:
     * - Apartamento padrão: 2 quartos, 60m²
     * - Casa padrão: configuração típica residencial
     * - E outras configurações podem surgir no futuro
     */
    private void registrarPrototiposPadrao() {
        // ============================================================
        // PROTÓTIPOS DE APARTAMENTO
        // ============================================================

        // Apartamento Padrão 2 Quartos (conforme exemplo do RF02)
        Anuncio aptoPadrao2Q = criarPrototipoApartamento(
                "APARTAMENTO_PADRAO_2Q",
                "Apartamento 2 Quartos - Padrão",
                60.0,  // área
                2,     // quartos
                1,     // banheiros
                250000.0  // preço sugerido
        );
        registrar(aptoPadrao2Q);

        // Apartamento Compacto (Studio)
        Anuncio aptoStudio = criarPrototipoApartamento(
                "APARTAMENTO_STUDIO",
                "Studio/Apartamento Compacto",
                35.0,
                1,
                1,
                180000.0
        );
        registrar(aptoStudio);

        // Apartamento Família (3 quartos)
        Anuncio aptoFamilia = criarPrototipoApartamento(
                "APARTAMENTO_FAMILIA_3Q",
                "Apartamento Família 3 Quartos",
                90.0,
                3,
                2,
                400000.0
        );
        registrar(aptoFamilia);

        // Apartamento Alto Padrão
        Anuncio aptoAltoPadrao = criarPrototipoApartamentoAltoPadrao();
        registrar(aptoAltoPadrao);

        // ============================================================
        // PROTÓTIPOS DE CASA
        // ============================================================

        // Casa Padrão
        Anuncio casaPadrao = criarPrototipoCasa(
                "CASA_PADRAO",
                "Casa Residencial Padrão",
                120.0,
                250.0,
                3,
                2,
                350000.0
        );
        registrar(casaPadrao);

        // Casa Térrea Compacta
        Anuncio casaTerrea = criarPrototipoCasa(
                "CASA_TERREA_COMPACTA",
                "Casa Térrea Compacta",
                70.0,
                150.0,
                2,
                1,
                220000.0
        );
        registrar(casaTerrea);

        // Casa Sobrado
        Anuncio casaSobrado = criarPrototipoCasaSobrado();
        registrar(casaSobrado);

        // ============================================================
        // PROTÓTIPOS DE TERRENO
        // ============================================================

        // Terreno Urbano Padrão
        Anuncio terrenoPadrao = criarPrototipoTerreno(
                "TERRENO_URBANO_PADRAO",
                "Terreno Urbano",
                300.0,
                10.0,
                Terreno.TipoTerreno.URBANO,
                150000.0
        );
        registrar(terrenoPadrao);

        // Terreno para Construção
        Anuncio terrenoConstrucao = criarPrototipoTerreno(
                "TERRENO_CONSTRUCAO",
                "Terreno Pronto para Construção",
                450.0,
                15.0,
                Terreno.TipoTerreno.URBANO,
                250000.0
        );
        registrar(terrenoConstrucao);

        // ============================================================
        // PROTÓTIPOS DE SALA COMERCIAL
        // ============================================================

        // Sala Comercial Pequena
        Anuncio salaPequena = criarPrototipoSalaComercial(
                "SALA_COMERCIAL_PEQUENA",
                "Sala Comercial Compacta",
                30.0,
                1,
                SalaComercial.TipoUsoComercial.ESCRITORIO,
                1500.0,  // preço aluguel
                TipoTransacao.ALUGUEL
        );
        registrar(salaPequena);

        // Sala Comercial Média
        Anuncio salaMedia = criarPrototipoSalaComercial(
                "SALA_COMERCIAL_MEDIA",
                "Sala Comercial",
                50.0,
                2,
                SalaComercial.TipoUsoComercial.ESCRITORIO,
                2500.0,
                TipoTransacao.ALUGUEL
        );
        registrar(salaMedia);

        // ============================================================
        // PROTÓTIPOS DE GALPÃO
        // ============================================================

        // Galpão Logístico
        Anuncio galpaoLogistico = criarPrototipoGalpao(
                "GALPAO_LOGISTICO",
                "Galpão Logístico",
                1000.0,
                8.0,
                Galpao.TipoUsoGalpao.LOGISTICA,
                15000.0,
                TipoTransacao.ALUGUEL
        );
        registrar(galpaoLogistico);

        // Galpão Industrial
        Anuncio galpaoIndustrial = criarPrototipoGalpao(
                "GALPAO_INDUSTRIAL",
                "Galpão Industrial",
                2000.0,
                10.0,
                Galpao.TipoUsoGalpao.INDUSTRIAL,
                25000.0,
                TipoTransacao.ALUGUEL
        );
        registrar(galpaoIndustrial);
    }

    // ============================================================
    // MÉTODOS AUXILIARES PARA CRIAÇÃO DE PROTÓTIPOS
    // ============================================================

    private Anuncio criarPrototipoApartamento(String chave, String titulo,
                                              double area, int quartos,
                                              int banheiros, double preco) {
        ApartamentoFactory factory = new ApartamentoFactory();
        Apartamento apto = (Apartamento) factory.criarImovel(area, "[A definir]");
        apto.setNumeroQuartos(quartos);
        apto.setNumeroBanheiros(banheiros);

        Anuncio anuncio = new Anuncio(titulo, apto, preco);
        anuncio.setChavePrototipo(chave);
        anuncio.setTipoTransacao(TipoTransacao.VENDA);
        anuncio.setDescricao("Apartamento com " + quartos + " quarto(s), " +
                banheiros + " banheiro(s), " + area + "m² de área útil.");

        return anuncio;
    }

    private Anuncio criarPrototipoApartamentoAltoPadrao() {
        ApartamentoFactory factory = new ApartamentoFactory();
        Apartamento apto = factory.criarApartamentoCompleto(
                150.0,           // área
                "[A definir]",   // localização
                10,              // andar
                "[A definir]",   // número apto
                "[A definir]",   // nome condomínio
                true,            // tem elevador
                1500.0,          // valor condomínio
                4,               // quartos
                3                // banheiros
        );
        apto.setPossuiGaragem(true);

        Anuncio anuncio = new Anuncio("Apartamento Alto Padrão", apto, 800000.0);
        anuncio.setChavePrototipo("APARTAMENTO_ALTO_PADRAO");
        anuncio.setTipoTransacao(TipoTransacao.VENDA);
        anuncio.setDescricao("Apartamento de alto padrão com 4 suítes, " +
                "varanda gourmet, 2 vagas de garagem. " +
                "Condomínio com área de lazer completa.");

        return anuncio;
    }

    private Anuncio criarPrototipoCasa(String chave, String titulo,
                                       double areaConstruida, double areaTerreno,
                                       int quartos, int banheiros, double preco) {
        CasaFactory factory = new CasaFactory();
        Casa casa = factory.criarCasaCompleta(
                areaConstruida,
                "[A definir]",
                quartos,
                banheiros,
                1,           // andares
                areaTerreno,
                true,        // tem quintal
                areaTerreno - areaConstruida  // área quintal
        );
        casa.setPossuiGaragem(true);

        Anuncio anuncio = new Anuncio(titulo, casa, preco);
        anuncio.setChavePrototipo(chave);
        anuncio.setTipoTransacao(TipoTransacao.VENDA);
        anuncio.setDescricao("Casa com " + quartos + " quarto(s), " +
                banheiros + " banheiro(s), " +
                "área construída de " + areaConstruida + "m², " +
                "terreno de " + areaTerreno + "m².");

        return anuncio;
    }

    private Anuncio criarPrototipoCasaSobrado() {
        CasaFactory factory = new CasaFactory();
        Casa casa = factory.criarCasaCompleta(
                180.0,          // área construída
                "[A definir]",
                4,              // quartos
                3,              // banheiros
                2,              // andares (sobrado)
                300.0,          // área terreno
                true,           // tem quintal
                80.0            // área quintal
        );
        casa.setPossuiGaragem(true);

        Anuncio anuncio = new Anuncio("Sobrado 4 Quartos", casa, 550000.0);
        anuncio.setChavePrototipo("CASA_SOBRADO");
        anuncio.setTipoTransacao(TipoTransacao.VENDA);
        anuncio.setDescricao("Sobrado espaçoso com 4 quartos sendo 2 suítes, " +
                "sala ampla, cozinha planejada, " +
                "quintal com churrasqueira, 2 vagas de garagem.");

        return anuncio;
    }

    private Anuncio criarPrototipoTerreno(String chave, String titulo,
                                          double area, double frente,
                                          Terreno.TipoTerreno tipo, double preco) {
        TerrenoFactory factory = new TerrenoFactory();
        Terreno terreno = factory.criarTerrenoCompleto(
                area,
                "[A definir]",
                tipo,
                Topografia.PLANO,
                frente,
                true,    // possui escritura
                true     // cercado
        );

        Anuncio anuncio = new Anuncio(titulo, terreno, preco);
        anuncio.setChavePrototipo(chave);
        anuncio.setTipoTransacao(TipoTransacao.VENDA);
        anuncio.setDescricao("Terreno " + tipo.getDescricao().toLowerCase() +
                " com " + area + "m², " +
                frente + "m de frente. Documentação em dia.");

        return anuncio;
    }

    private Anuncio criarPrototipoSalaComercial(String chave, String titulo,
                                                double area, int ambientes,
                                                SalaComercial.TipoUsoComercial tipoUso,
                                                double preco, TipoTransacao transacao) {
        SalaComercialFactory factory = new SalaComercialFactory();
        SalaComercial sala = factory.criarSalaCompleta(
                area,
                "[A definir]",
                tipoUso,
                1,        // andar
                ambientes,
                false,    // recepção
                true,     // ar condicionado
                300.0     // condomínio
        );

        Anuncio anuncio = new Anuncio(titulo, sala, preco);
        anuncio.setChavePrototipo(chave);
        anuncio.setTipoTransacao(transacao);
        anuncio.setDescricao("Sala comercial para " + tipoUso.getDescricao().toLowerCase() +
                " com " + area + "m², " +
                ambientes + " ambiente(s), ar condicionado incluso.");

        return anuncio;
    }

    private Anuncio criarPrototipoGalpao(String chave, String titulo,
                                         double area, double peDireito,
                                         Galpao.TipoUsoGalpao tipoUso,
                                         double preco, TipoTransacao transacao) {
        GalpaoFactory factory = new GalpaoFactory();
        Galpao galpao = factory.criarGalpaoCompleto(
                area,
                "[A definir]",
                tipoUso,
                peDireito,
                5000,     // capacidade carga
                2,        // docas
                50.0      // área administrativa
        );

        Anuncio anuncio = new Anuncio(titulo, galpao, preco);
        anuncio.setChavePrototipo(chave);
        anuncio.setTipoTransacao(transacao);
        anuncio.setDescricao("Galpão " + tipoUso.getDescricao().toLowerCase() +
                " com " + area + "m², " +
                "pé direito de " + peDireito + "m, " +
                "com docas e área administrativa.");

        return anuncio;
    }

    // ============================================================
    // MÉTODOS PÚBLICOS DO REGISTRY
    // ============================================================

    /**
     * Registra um novo protótipo no sistema.
     *
     * EXTENSIBILIDADE: Novos protótipos podem ser adicionados
     * em tempo de execução sem modificar código existente.
     *
     * @param prototipo O protótipo a ser registrado
     */
    public void registrar(AnuncioPrototype prototipo) {
        prototipos.put(prototipo.getChavePrototipo().toUpperCase(), prototipo);
    }

    /**
     * Obtém um protótipo pelo sua chave identificadora.
     *
     * @param chave Chave do protótipo (case insensitive)
     * @return O protótipo ou null se não encontrado
     */
    public AnuncioPrototype obter(String chave) {
        return prototipos.get(chave.toUpperCase());
    }

    /**
     * Clona um protótipo registrado, retornando uma nova instância.
     *
     * Este é o método principal para criar anúncios a partir de
     * configurações padrão.
     *
     * @param chave Chave do protótipo a ser clonado
     * @return Nova instância clonada ou null se não encontrado
     */
    public AnuncioPrototype clonar(String chave) {
        AnuncioPrototype prototipo = obter(chave);
        if (prototipo != null) {
            return prototipo.clonar();
        }
        return null;
    }

    /**
     * Verifica se existe um protótipo com a chave informada.
     */
    public boolean existe(String chave) {
        return prototipos.containsKey(chave.toUpperCase());
    }

    /**
     * Retorna todas as chaves de protótipos disponíveis.
     */
    public Set<String> getChavesDisponiveis() {
        return prototipos.keySet();
    }

    /**
     * Retorna o número de protótipos registrados.
     */
    public int getQuantidadePrototipos() {
        return prototipos.size();
    }
    /**
     * Remove um protótipo do registro.
     */
    public void remover(String chave) {
        prototipos.remove(chave.toUpperCase());
    }
}