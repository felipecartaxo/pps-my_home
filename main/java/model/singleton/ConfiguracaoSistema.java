package model.singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * PADRÃO: Singleton
 * PAPEL: Singleton
 * FUNÇÃO: Gerencia as configurações do projeto
 *
 * CONTEXTO (RF07):
 * - Carrega configurações de um arquivo config.properties
 * - Fornece acesso global às configurações do sistema
 * - Configurações incluem: taxas, limites, termos proibidos, URLs de serviços
 */
public class ConfiguracaoSistema {

    // Nome do arquivo de configuração
    private static final String ARQUIVO_CONFIG = "config.properties";

    // Propriedades carregadas do arquivo
    private final Properties propriedades;

    // Indica se as configurações foram carregadas com sucesso
    private final boolean carregadoComSucesso;

    /**
     * Construtor PRIVADO - Impede que outras classes criem instâncias diretamente
     *
     * Carrega as configurações do arquivo config.properties na inicialização
     */
    private ConfiguracaoSistema() {
        this.propriedades = new Properties();
        this.carregadoComSucesso = carregarPropriedades();
    }

    // Classe interna estática (Holder) para implementação thread-safe
    private static class SingletonHolder {
        private static final ConfiguracaoSistema INSTANCIA = new ConfiguracaoSistema();
    }

    /**
     * Ponto de acesso global à instância única
     * Este é o método que clientes usam para obter a configuração
     */
    public static ConfiguracaoSistema getInstancia() {
        return SingletonHolder.INSTANCIA;
    }

    // Carrega as propriedades do arquivo de configuração
    private boolean carregarPropriedades() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(ARQUIVO_CONFIG)) {
            if (input == null) {
                // Tenta carregar de caminho alternativo
                return carregarPropriedadesPadrao();
            }
            propriedades.load(input);
            return true;
        } catch (IOException e) {
            return carregarPropriedadesPadrao();
        }
    }

    /**
     * Carrega propriedades padrão caso o arquivo não seja encontrado
     *
     * Garante que o sistema funcione mesmo sem o arquivo de configuração
     */
    private boolean carregarPropriedadesPadrao() {
        // Configurações gerais
        propriedades.setProperty("sistema.nome", "MyHome");
        propriedades.setProperty("sistema.versao", "1.0.0");

        // Configurações de anúncios
        propriedades.setProperty("anuncio.limite.fotos", "30");
        propriedades.setProperty("anuncio.descricao.tamanho.minimo", "20");
        propriedades.setProperty("anuncio.descricao.tamanho.maximo", "5000");
        propriedades.setProperty("anuncio.titulo.tamanho.minimo", "10");
        propriedades.setProperty("anuncio.titulo.tamanho.maximo", "100");

        // Configurações de preços
        propriedades.setProperty("preco.minimo.venda", "10000");
        propriedades.setProperty("preco.minimo.aluguel", "100");
        propriedades.setProperty("preco.minimo.temporada", "50");
        propriedades.setProperty("preco.maximo", "500000000");

        // Taxas e comissões
        propriedades.setProperty("comissao.venda.percentual", "6.0");
        propriedades.setProperty("comissao.aluguel.percentual", "100.0");
        propriedades.setProperty("taxa.administrativa", "150.0");

        // Termos proibidos
        propriedades.setProperty("moderacao.termos.proibidos",
                "golpe,fraude,enganação,spam,grátis,urgente venda");

        // URLs de serviços
        propriedades.setProperty("servico.email.url", "https://api.myhome.com/email");
        propriedades.setProperty("servico.sms.url", "https://api.myhome.com/sms");
        propriedades.setProperty("servico.telegram.url", "https://api.telegram.org/bot");
        propriedades.setProperty("servico.whatsapp.url", "https://api.myhome.com/whatsapp");

        // Notificação
        propriedades.setProperty("notificacao.canal.padrao", "EMAIL");
        propriedades.setProperty("notificacao.timeout", "5000");

        // Busca
        propriedades.setProperty("busca.resultados.por.pagina", "20");
        propriedades.setProperty("busca.resultados.maximo", "1000");

        return true;
    }

    // ========================================================================
    // MÉTODOS DE ACESSO ÀS CONFIGURAÇÕES
    // ========================================================================

    // --- Configurações Gerais ---

    // Retorna o nome do sistema
    public String getNomeSistema() {
        return propriedades.getProperty("sistema.nome", "MyHome");
    }

    // Retorna a versão do sistema
    public String getVersaoSistema() {
        return propriedades.getProperty("sistema.versao", "1.0.0");
    }

    // --- Configurações de Anúncios ---

    // Retorna o limite máximo de fotos por anúncio
    public int getLimiteFotos() {
        return getIntProperty("anuncio.limite.fotos", 30);
    }

    // Retorna o tamanho mínimo da descrição
    public int getTamanhoMinimoDescricao() {
        return getIntProperty("anuncio.descricao.tamanho.minimo", 20);
    }

    // Retorna o tamanho máximo da descrição
    public int getTamanhoMaximoDescricao() {
        return getIntProperty("anuncio.descricao.tamanho.maximo", 5000);
    }

    // Retorna o tamanho mínimo do título
    public int getTamanhoMinimoTitulo() {
        return getIntProperty("anuncio.titulo.tamanho.minimo", 10);
    }

    // Retorna o tamanho máximo do título
    public int getTamanhoMaximoTitulo() {
        return getIntProperty("anuncio.titulo.tamanho.maximo", 100);
    }

    // --- Configurações de Preços ---

    // Retorna o preço mínimo para venda
    public double getPrecoMinimoVenda() {
        return getDoubleProperty("preco.minimo.venda", 10000.0);
    }

    // Retorna o preço mínimo para aluguel
    public double getPrecoMinimoAluguel() {
        return getDoubleProperty("preco.minimo.aluguel", 100.0);
    }

    // Retorna o preço mínimo para temporada
    public double getPrecoMinimoTemporada() {
        return getDoubleProperty("preco.minimo.temporada", 50.0);
    }

    // Retorna o preço máximo permitido
    public double getPrecoMaximo() {
        return getDoubleProperty("preco.maximo", 500000000.0);
    }

    // --- Taxas e Comissões ---

    // Retorna a taxa de comissão para venda (em percentual)
    public double getComissaoVenda() {
        return getDoubleProperty("comissao.venda.percentual", 6.0);
    }

    // Retorna a taxa de comissão para aluguel (em percentual)
    public double getComissaoAluguel() {
        return getDoubleProperty("comissao.aluguel.percentual", 100.0);
    }

    // Retorna a taxa administrativa
    public double getTaxaAdministrativa() {
        return getDoubleProperty("taxa.administrativa", 150.0);
    }

    // --- Moderação (RF03) ---

    /**
     * Retorna a lista de termos proibidos para moderação
     * Usada pelo TermosProibidosHandler no Chain of Responsibility (RF03)
     */
    public List<String> getTermosProibidos() {
        String termos = propriedades.getProperty("moderacao.termos.proibidos", "");
        return Arrays.stream(termos.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    // --- URLs de Serviços Externos ---

    // Retorna a URL do serviço de email
    public String getUrlServicoEmail() {
        return propriedades.getProperty("servico.email.url", "");
    }

    // Retorna a URL do serviço de SMS
    public String getUrlServicoSMS() {
        return propriedades.getProperty("servico.sms.url", "");
    }

    // Retorna a URL da API do Telegram
    public String getUrlServicoTelegram() {
        return propriedades.getProperty("servico.telegram.url", "");
    }

    // Retorna a URL da API do WhatsApp
    public String getUrlServicoWhatsApp() {
        return propriedades.getProperty("servico.whatsapp.url", "");
    }

    // --- Configurações de Notificação ---

    // Retorna o canal de notificação padrão
    public String getCanalNotificacaoPadrao() {
        return propriedades.getProperty("notificacao.canal.padrao", "EMAIL");
    }

    // Retorna o timeout para envio de notificação (em ms)
    public int getTimeoutNotificacao() {
        return getIntProperty("notificacao.timeout", 5000);
    }

    // --- Configurações de Busca ---

    // Retorna o número de resultados por página na busca
    public int getResultadosPorPagina() {
        return getIntProperty("busca.resultados.por.pagina", 20);
    }

    // Retorna o número máximo de resultados de busca
    public int getResultadosMaximo() {
        return getIntProperty("busca.resultados.maximo", 1000);
    }

    // ========================================================================
    // MÉTODOS AUXILIARES
    // ========================================================================

    // Obtém uma propriedade como inteiro
    private int getIntProperty(String chave, int valorPadrao) {
        try {
            return Integer.parseInt(propriedades.getProperty(chave, String.valueOf(valorPadrao)));
        } catch (NumberFormatException e) {
            return valorPadrao;
        }
    }

    // Obtém uma propriedade como double
    private double getDoubleProperty(String chave, double valorPadrao) {
        try {
            return Double.parseDouble(propriedades.getProperty(chave, String.valueOf(valorPadrao)));
        } catch (NumberFormatException e) {
            return valorPadrao;
        }
    }

    /**
     * Obtém uma propriedade genérica como String
     * Útil para acessar propriedades não mapeadas por métodos específicos
     */
    public String getPropriedade(String chave, String valorPadrao) {
        return propriedades.getProperty(chave, valorPadrao);
    }

    // Verifica se as configurações foram carregadas com sucesso
    public boolean isCarregadoComSucesso() {
        return carregadoComSucesso;
    }

    /**
     * Retorna um resumo das configurações para exibição.
     */
    public String getResumoConfiguracoes() {
        StringBuilder sb = new StringBuilder();
        sb.append("═".repeat(60)).append("\n");
        sb.append("CONFIGURAÇÕES DO SISTEMA - ").append(getNomeSistema());
        sb.append(" v").append(getVersaoSistema()).append("\n");
        sb.append("═".repeat(60)).append("\n");

        sb.append("\n[Anúncios]\n");
        sb.append("  Limite de fotos: ").append(getLimiteFotos()).append("\n");
        sb.append("  Título: ").append(getTamanhoMinimoTitulo()).append("-");
        sb.append(getTamanhoMaximoTitulo()).append(" caracteres\n");
        sb.append("  Descrição: ").append(getTamanhoMinimoDescricao()).append("-");
        sb.append(getTamanhoMaximoDescricao()).append(" caracteres\n");

        sb.append("\n[Preços]\n");
        sb.append("  Mínimo venda: R$ ").append(String.format("%,.0f", getPrecoMinimoVenda())).append("\n");
        sb.append("  Mínimo aluguel: R$ ").append(String.format("%,.0f", getPrecoMinimoAluguel())).append("\n");
        sb.append("  Máximo: R$ ").append(String.format("%,.0f", getPrecoMaximo())).append("\n");

        sb.append("\n[Comissões]\n");
        sb.append("  Venda: ").append(getComissaoVenda()).append("%\n");
        sb.append("  Aluguel: ").append(getComissaoAluguel()).append("%\n");
        sb.append("  Taxa administrativa: R$ ").append(String.format("%.2f", getTaxaAdministrativa())).append("\n");

        sb.append("\n[Moderação]\n");
        sb.append("  Termos proibidos: ").append(getTermosProibidos().size()).append(" termo(s)\n");

        sb.append("\n[Notificação]\n");
        sb.append("  Canal padrão: ").append(getCanalNotificacaoPadrao()).append("\n");

        sb.append("─".repeat(60));
        return sb.toString();
    }
}