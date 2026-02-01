package application;

import model.adapter.NotificadorAdapter;
import model.adapter.concrete_adapters.EmailAdapter;
import model.adapter.concrete_adapters.TelegramAdapter;
import model.chain_of_responsibility.Handler;
import model.chain_of_responsibility.concrete_handlers.*;
import model.decorator.FiltroBase;
import model.decorator.FiltroBusca;
import model.decorator.concrete_decorators.FiltroArea;
import model.decorator.concrete_decorators.FiltroEstadoAnuncio;
import model.decorator.concrete_decorators.FiltroLocalizacao;
import model.decorator.concrete_decorators.FiltroPreco;
import model.factory_method.factory.ImovelFactory;
import model.factory_method.factory.concrete_factories.ApartamentoFactory;
import model.factory_method.factory.concrete_factories.CasaFactory;
import model.factory_method.product.Imovel;
import model.observer.concrete_subscribers.AnuncianteObserver;
import model.observer.concrete_subscribers.LogObserver;
import model.prototype.concrete_prototype.Anuncio;
import model.prototype.prototype_manager.AnuncioPrototypeRegistry;
import model.singleton.ConfiguracaoSistema;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE MAIN - CLIENT
 *
 * Demonstra todos os padrões de projeto GoF implementados no sistema MyHome
 * funcionando em conjunto para resolver os requisitos funcionais.
 *
 * PADRÕES DEMONSTRADOS:
 * 1. Factory Method (RF01) - Criação de diferentes tipos de imóveis
 * 2. Prototype (RF02) - Clonagem de anúncios padrão
 * 3. Chain of Responsibility (RF03) - Moderação de anúncios
 * 4. State + Observer (RF04) - Gerenciamento de estados e notificações
 * 5. Adapter (RF05) - Múltiplos canais de notificação
 * 6. Decorator (RF06) - Busca com filtros combinados
 * 7. Singleton (RF07) - Configuração centralizada
 */
public class Main {

    public static void main(String[] args) {
        imprimirCabecalho();

        // ================================================================
        // SINGLETON (RF07) - Configuração Centralizada
        // ================================================================
        demonstrarSingleton();

        // ================================================================
        // FACTORY METHOD (RF01) - Criação de Imóveis
        // ================================================================
        List<Anuncio> todosAnuncios = demonstrarFactoryMethod();

        // ================================================================
        // PROTOTYPE (RF02) - Clonagem de Configurações Padrão
        // ================================================================
        demonstrarPrototype(todosAnuncios);

        // ================================================================
        // OBSERVER + STATE (RF04) - Estados e Notificações
        // ================================================================
        demonstrarObserverEState(todosAnuncios);

        // ================================================================
        // CHAIN OF RESPONSIBILITY (RF03) - Moderação
        // ================================================================
        demonstrarChainOfResponsibility(todosAnuncios);

        // ================================================================
        // ADAPTER (RF05) - Canais de Notificação
        // ================================================================
        demonstrarAdapter(todosAnuncios);

        // ================================================================
        // DECORATOR (RF06) - Busca com Filtros Combinados
        // ================================================================
        demonstrarDecorator(todosAnuncios);

        imprimirRodape();
    }

    // ====================================================================
    // DEMONSTRAÇÃO 1: SINGLETON (RF07)
    // ====================================================================

    private static void demonstrarSingleton() {
        imprimirSecao("SINGLETON - Configuração Centralizada (RF07)");

        System.out.println("Obtendo instância única de ConfiguracaoSistema...\n");

        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();

        // Demonstra que sempre retorna a mesma instância
        ConfiguracaoSistema config2 = ConfiguracaoSistema.getInstancia();
        System.out.println("Mesma instância? " + (config == config2) + "\n");

        // Exibe resumo das configurações
        System.out.println(config.getResumoConfiguracoes());

        pausar();
    }

    // ====================================================================
    // DEMONSTRAÇÃO 2: FACTORY METHOD (RF01)
    // ====================================================================

    private static List<Anuncio> demonstrarFactoryMethod() {
        imprimirSecao("FACTORY METHOD - Criação de Diferentes Tipos de Imóveis (RF01)");

        List<Anuncio> anuncios = new ArrayList<>();

        // Factory de Apartamento
        System.out.println("1. Criando Apartamento usando ApartamentoFactory:");
        ImovelFactory apartamentoFactory = new ApartamentoFactory();
        Imovel apartamento = apartamentoFactory.criarImovel(85.0, "Boa Vista, Recife - PE");

        Anuncio anuncioApto = new Anuncio(
                "Apartamento 3 Quartos - Boa Vista",
                apartamento,
                450000.0
        );
        anuncioApto.setDescricao("Apartamento espaçoso em localização privilegiada, próximo ao centro.");
        anuncioApto.adicionarFoto("foto1.jpg");
        anuncioApto.adicionarFoto("foto2.jpg");
        anuncios.add(anuncioApto);

        System.out.println("✓ Apartamento criado: " + apartamento.getTipo());
        System.out.println("  Área: " + apartamento.getArea() + "m²");
        System.out.println("  Localização: " + apartamento.getLocalizacao() + "\n");

        // Factory de Casa
        System.out.println("2. Criando Casa usando CasaFactory:");
        ImovelFactory casaFactory = new CasaFactory();
        Imovel casa = casaFactory.criarImovel(150.0, "Casa Forte, Recife - PE");

        Anuncio anuncioCasa = new Anuncio(
                "Casa Ampla com Quintal",
                casa,
                580000.0
        );
        anuncioCasa.setDescricao("Casa espaçosa com 3 quartos, quintal e área de lazer completa.");
        anuncioCasa.adicionarFoto("casa1.jpg");
        anuncioCasa.adicionarFoto("casa2.jpg");
        anuncioCasa.adicionarFoto("casa3.jpg");
        anuncios.add(anuncioCasa);

        System.out.println("✓ Casa criada: " + casa.getTipo());
        System.out.println("  Área: " + casa.getArea() + "m²");
        System.out.println("  Localização: " + casa.getLocalizacao() + "\n");

        System.out.println("EXTENSIBILIDADE: Novos tipos de imóveis podem ser adicionados");
        System.out.println("criando novas factories sem modificar código existente (OCP).\n");

        pausar();
        return anuncios;
    }

    // ====================================================================
    // DEMONSTRAÇÃO 3: PROTOTYPE (RF02)
    // ====================================================================

    private static void demonstrarPrototype(List<Anuncio> anuncios) {
        imprimirSecao("PROTOTYPE - Clonagem de Anúncios Padrão (RF02)");

        // Inicializa o registro de protótipos
        AnuncioPrototypeRegistry registry = new AnuncioPrototypeRegistry();

        System.out.println("Protótipos disponíveis no sistema:");
        System.out.println("Total: " + registry.getQuantidadePrototipos() + " protótipos registrados\n");

        // Demonstra clonagem de apartamento padrão
        System.out.println("1. Clonando protótipo APARTAMENTO_PADRAO_2Q:");
        Anuncio anuncioClonado = (Anuncio) registry.clonar("APARTAMENTO_PADRAO_2Q");

        if (anuncioClonado != null) {
            // Personaliza o clone
            anuncioClonado.getImovel().getLocalizacao(); // Original: "[A definir]"
            anuncioClonado.setDescricao("Apartamento 2 quartos próximo ao metrô, excelente acabamento.");

            System.out.println("✓ Clone criado com sucesso!");
            System.out.println("  Título: " + anuncioClonado.getTitulo());
            System.out.println("  Preço: R$ " + String.format("%,.2f", anuncioClonado.getPreco()));
            System.out.println("  Área: " + anuncioClonado.getImovel().getArea() + "m²");
            System.out.println("  Estado inicial: " + anuncioClonado.getEstadoAtual() + "\n");

            anuncios.add(anuncioClonado);
        }

        // Demonstra outro protótipo
        System.out.println("2. Clonando protótipo CASA_SOBRADO:");
        Anuncio sobradoClonado = (Anuncio) registry.clonar("CASA_SOBRADO");

        if (sobradoClonado != null) {
            System.out.println("✓ Clone criado com sucesso!");
            System.out.println("  Título: " + sobradoClonado.getTitulo());
            System.out.println("  Preço: R$ " + String.format("%,.2f", sobradoClonado.getPreco()));
            System.out.println("  Descrição: " + sobradoClonado.getDescricao().substring(0, 50) + "...\n");

            anuncios.add(sobradoClonado);
        }

        System.out.println("BENEFÍCIO: Protótipos permitem criar anúncios rapidamente");
        System.out.println("a partir de configurações pré-definidas.\n");

        pausar();
    }

    // ====================================================================
    // DEMONSTRAÇÃO 4: OBSERVER + STATE (RF04)
    // ====================================================================

    private static void demonstrarObserverEState(List<Anuncio> anuncios) {
        imprimirSecao("STATE + OBSERVER - Ciclo de Vida e Notificações (RF04)");

        if (anuncios.isEmpty()) {
            System.out.println("Nenhum anúncio disponível para demonstração.\n");
            return;
        }

        Anuncio anuncio = anuncios.get(0);

        // Cria observers
        System.out.println("Configurando observadores...\n");

        LogObserver logObserver = new LogObserver();
        AnuncianteObserver anuncianteObserver = new AnuncianteObserver();

        // Registra observers
        anuncio.adicionarObservador(logObserver);
        anuncio.adicionarObservador(anuncianteObserver);

        System.out.println("✓ LogObserver registrado (RF04 - mantém histórico)");
        System.out.println("✓ AnuncianteObserver registrado (RF04 - notifica anunciante)\n");

        // Demonstra transições de estado
        System.out.println("FLUXO DE ESTADOS DO ANÚNCIO:\n");

        System.out.println("Estado inicial: " + anuncio.getEstadoAtual());
        System.out.println("─".repeat(60));

        // Transição 1: Rascunho -> Pendente de Moderação
        System.out.println("\n1. Enviando para moderação...");
        anuncio.enviarParaModeracao();
        System.out.println("   Novo estado: " + anuncio.getEstadoAtual());
        System.out.println("   Log: " + logObserver.getUltimoRegistro());

        // Transição 2: Pendente -> Ativo (aprovação)
        System.out.println("\n2. Aprovando anúncio...");
        anuncio.aprovar();
        System.out.println("   Novo estado: " + anuncio.getEstadoAtual());
        System.out.println("   Log: " + logObserver.getUltimoRegistro());

        // Transição 3: Ativo -> Suspenso
        System.out.println("\n3. Suspendendo anúncio...");
        anuncio.suspender();
        System.out.println("   Novo estado: " + anuncio.getEstadoAtual());
        System.out.println("   Log: " + logObserver.getUltimoRegistro());

        // Transição 4: Suspenso -> Rascunho
        System.out.println("\n4. Retornando para rascunho...");
        anuncio.voltarParaRascunho();
        System.out.println("   Novo estado: " + anuncio.getEstadoAtual());
        System.out.println("   Log: " + logObserver.getUltimoRegistro());

        System.out.println("\n" + "─".repeat(60));
        System.out.println("Total de transições registradas: " + logObserver.getTotalRegistros());
        System.out.println("\nBENEFÍCIO: State encapsula comportamento por estado,");
        System.out.println("Observer notifica automaticamente sobre mudanças.\n");

        pausar();
    }

    // ====================================================================
    // DEMONSTRAÇÃO 5: CHAIN OF RESPONSIBILITY (RF03)
    // ====================================================================

    private static void demonstrarChainOfResponsibility(List<Anuncio> anuncios) {
        imprimirSecao("CHAIN OF RESPONSIBILITY - Moderação de Anúncios (RF03)");

        // Monta a cadeia de validação
        System.out.println("Construindo cadeia de moderação...\n");

        Handler tituloHandler = new TituloHandler();
        Handler descricaoHandler = new DescricaoHandler();
        Handler precoHandler = new PrecoHandler();
        Handler fotosHandler = new FotosHandler();
        Handler termosHandler = new TermosProibidosHandler();
        Handler imovelHandler = new ImovelHandler();

        // Encadeia os handlers
        tituloHandler
                .setProximo(descricaoHandler)
                .setProximo(precoHandler)
                .setProximo(fotosHandler)
                .setProximo(termosHandler)
                .setProximo(imovelHandler);

        System.out.println("✓ Cadeia montada:");
        System.out.println("  1. TituloHandler");
        System.out.println("  2. DescricaoHandler");
        System.out.println("  3. PrecoHandler");
        System.out.println("  4. FotosHandler");
        System.out.println("  5. TermosProibidosHandler");
        System.out.println("  6. ImovelHandler\n");

        // Testa moderação com anúncio válido
        if (!anuncios.isEmpty()) {
            Anuncio anuncioValido = anuncios.get(0);

            System.out.println("Teste 1: Anúncio VÁLIDO");
            System.out.println("─".repeat(60));
            System.out.println("Título: " + anuncioValido.getTitulo());

            boolean aprovado = tituloHandler.processar(anuncioValido);

            if (aprovado) {
                System.out.println("✓ APROVADO: Todas as validações passaram!\n");
            } else {
                System.out.println("✗ REPROVADO\n");
            }
        }

        // Cria anúncio inválido para testar reprovação
        System.out.println("Teste 2: Anúncio INVÁLIDO (título muito curto)");
        System.out.println("─".repeat(60));

        ApartamentoFactory factory = new ApartamentoFactory();
        Imovel imovelTeste = factory.criarImovel(50.0, "Recife - PE");

        Anuncio anuncioInvalido = new Anuncio("Apto", imovelTeste, 100000.0);
        anuncioInvalido.setDescricao("Apartamento com localização privilegiada");
        anuncioInvalido.adicionarFoto("foto.jpg");

        System.out.println("Título: " + anuncioInvalido.getTitulo());

        boolean aprovadoInvalido = tituloHandler.processar(anuncioInvalido);

        if (!aprovadoInvalido) {
            System.out.println("✗ REPROVADO");

            // Encontra qual handler reprovou
            Handler atual = tituloHandler;
            while (atual != null) {
                if (atual.getMensagemErro() != null) {
                    System.out.println("  Handler: " + atual.getNomeHandler());
                    System.out.println("  Motivo: " + atual.getMensagemErro());
                    break;
                }
                atual = null; // Simplificação para o exemplo
            }
        }

        System.out.println("\nBENEFÍCIO: Validações podem ser adicionadas/removidas");
        System.out.println("dinamicamente sem alterar código existente (OCP).\n");

        pausar();
    }

    // ====================================================================
    // DEMONSTRAÇÃO 6: ADAPTER (RF05)
    // ====================================================================

    private static void demonstrarAdapter(List<Anuncio> anuncios) {
        imprimirSecao("ADAPTER - Múltiplos Canais de Notificação (RF05)");

        if (anuncios.isEmpty()) {
            System.out.println("Nenhum anúncio disponível.\n");
            return;
        }

        Anuncio anuncio = anuncios.get(0);

        System.out.println("Configurando diferentes canais de notificação...\n");

        // Adapter Email
        System.out.println("1. Canal: EMAIL");
        NotificadorAdapter emailAdapter = new EmailAdapter();
        AnuncianteObserver observadorEmail = new AnuncianteObserver(
                emailAdapter,
                "anunciante@example.com"
        );

        anuncio.adicionarObservador(observadorEmail);
        System.out.println("   ✓ EmailAdapter configurado");
        System.out.println("   Destinatário: anunciante@example.com\n");

        // Adapter Telegram
        System.out.println("2. Canal: TELEGRAM");
        NotificadorAdapter telegramAdapter = new TelegramAdapter("BOT_TOKEN_EXEMPLO");
        AnuncianteObserver observadorTelegram = new AnuncianteObserver(
                telegramAdapter,
                "123456789"
        );

        anuncio.adicionarObservador(observadorTelegram);
        System.out.println("   ✓ TelegramAdapter configurado");
        System.out.println("   Chat ID: 123456789\n");

        System.out.println("─".repeat(60));
        System.out.println("Simulando mudança de estado para testar notificações...\n");

        // Remove observers anteriores para evitar duplicação
        LogObserver logTemp = new LogObserver();

        // Limpa e adiciona só os novos
        Anuncio novoAnuncio = new Anuncio(
                "Teste Notificação",
                anuncio.getImovel(),
                anuncio.getPreco()
        );
        novoAnuncio.setDescricao("Teste de notificação multi-canal");
        novoAnuncio.adicionarFoto("foto1.jpg");

        novoAnuncio.adicionarObservador(observadorEmail);
        novoAnuncio.adicionarObservador(logTemp);

        // Transição de estado
        novoAnuncio.enviarParaModeracao();

        System.out.println("\nBENEFÍCIO: Adapters permitem integrar serviços externos");
        System.out.println("com interfaces incompatíveis de forma transparente.\n");
        System.out.println("EXTENSIBILIDADE: Novos canais (SMS, WhatsApp) podem ser");
        System.out.println("adicionados criando novos Adapters.\n");

        pausar();
    }

    // ====================================================================
    // DEMONSTRAÇÃO 7: DECORATOR (RF06)
    // ====================================================================

    private static void demonstrarDecorator(List<Anuncio> anuncios) {
        imprimirSecao("DECORATOR - Busca com Filtros Combinados (RF06)");

        // Garante que temos anúncios ativos
        for (Anuncio a : anuncios) {
            if (a.getEstadoAtual().equals("Rascunho")) {
                a.enviarParaModeracao();
                a.aprovar();
            }
        }

        System.out.println("Base de anúncios disponíveis: " + anuncios.size() + " anúncios\n");

        // Busca 1: Filtro simples
        System.out.println("BUSCA 1: Apenas anúncios ativos");
        System.out.println("─".repeat(60));

        FiltroBusca filtro1 = new FiltroBase();
        filtro1 = FiltroEstadoAnuncio.apenasAtivos(filtro1);

        List<Anuncio> resultado1 = filtro1.filtrar(anuncios);
        System.out.println("Filtros: " + filtro1.getDescricao());
        System.out.println("Resultados: " + resultado1.size() + " anúncios\n");

        // Busca 2: Múltiplos filtros combinados
        System.out.println("BUSCA 2: Combinando múltiplos filtros");
        System.out.println("─".repeat(60));

        FiltroBusca filtro2 = new FiltroBase();
        filtro2 = FiltroEstadoAnuncio.apenasAtivos(filtro2);
        filtro2 = new FiltroPreco(filtro2, 200000.0, 500000.0);
        filtro2 = FiltroArea.minimo(filtro2, 60.0);
        filtro2 = new FiltroLocalizacao(filtro2, "Recife");

        List<Anuncio> resultado2 = filtro2.filtrar(anuncios);
        System.out.println("Filtros aplicados:");
        System.out.println(filtro2.getDescricao());
        System.out.println("\nResultados: " + resultado2.size() + " anúncios");

        if (!resultado2.isEmpty()) {
            System.out.println("\nPrimeiro resultado:");
            Anuncio primeiro = resultado2.get(0);
            System.out.println("  • " + primeiro.getTitulo());
            System.out.println("  • R$ " + String.format("%,.2f", primeiro.getPreco()));
            System.out.println("  • " + primeiro.getImovel().getArea() + "m²");
            System.out.println("  • " + primeiro.getImovel().getLocalizacao());
        }

        System.out.println("\n" + "─".repeat(60));

        // Busca 3: Demonstra flexibilidade
        System.out.println("\nBUSCA 3: Ordem diferente (mesmos filtros)");
        System.out.println("─".repeat(60));

        FiltroBusca filtro3 = new FiltroBase();
        filtro3 = new FiltroLocalizacao(filtro3, "Recife");
        filtro3 = FiltroArea.minimo(filtro3, 60.0);
        filtro3 = new FiltroPreco(filtro3, 200000.0, 500000.0);
        filtro3 = FiltroEstadoAnuncio.apenasAtivos(filtro3);

        List<Anuncio> resultado3 = filtro3.filtrar(anuncios);
        System.out.println("Filtros: " + filtro3.getDescricao());
        System.out.println("Resultados: " + resultado3.size() + " anúncios");

        System.out.println("\nBENEFÍCIO: Decorators permitem combinar filtros de forma");
        System.out.println("flexível em tempo de execução, sem modificar código existente.\n");
        System.out.println("EXTENSIBILIDADE: Novos filtros (quartos, tipo de imóvel) podem");
        System.out.println("ser adicionados como novos Decorators.\n");

        pausar();
    }

    // ====================================================================
    // MÉTODOS AUXILIARES
    // ====================================================================

    private static void imprimirCabecalho() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("                  SISTEMA MYHOME - DEMONSTRAÇÃO");
        System.out.println("           Padrões de Projeto GoF Aplicados em Java");
        System.out.println("═".repeat(70) + "\n");
    }

    private static void imprimirSecao(String titulo) {
        System.out.println("\n" + "═".repeat(70));
        System.out.println(titulo);
        System.out.println("═".repeat(70) + "\n");
    }

    private static void imprimirRodape() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("                    DEMONSTRAÇÃO CONCLUÍDA");
        System.out.println("═".repeat(70));
        System.out.println("\nTodos os 7 padrões GoF foram demonstrados com sucesso:");
        System.out.println("  ✓ Singleton (RF07) - Configuração centralizada");
        System.out.println("  ✓ Factory Method (RF01) - Criação de imóveis");
        System.out.println("  ✓ Prototype (RF02) - Clonagem de protótipos");
        System.out.println("  ✓ State (RF04) - Gerenciamento de estados");
        System.out.println("  ✓ Observer (RF04) - Sistema de notificações");
        System.out.println("  ✓ Chain of Responsibility (RF03) - Moderação");
        System.out.println("  ✓ Adapter (RF05) - Múltiplos canais");
        System.out.println("  ✓ Decorator (RF06) - Filtros combinados");
        System.out.println("\n" + "═".repeat(70) + "\n");
    }

    private static void pausar() {
        System.out.println("[Pressione ENTER para continuar...]");
        try {
            System.in.read();
            // Limpa buffer
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Ignora exceção
        }
    }
}