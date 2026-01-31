package model.chain_of_responsibility.concrete_handlers;

import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;
import model.singleton.ConfiguracaoSistema;

import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Verifica se o anúncio contém termos proibidos no título ou descrição.
 *
 * INTEGRAÇÃO COM RF07 (Singleton):
 * - Os termos proibidos são carregados do Singleton ConfiguracaoSistema,
 * que por sua vez carrega do arquivo config.properties
 *
 * Isso permite que os termos proibidos sejam alterados sem modificar código
 */
public class TermosProibidosHandler extends ModeracaoHandlerBase {

    private List<String> termosProibidos;

    public TermosProibidosHandler() {
        super("Validador de Termos Proibidos");
        carregarTermosProibidos();
    }

    // Carrega os termos proibidos do Singleton de configuração
    private void carregarTermosProibidos() {
        // Obtém termos do Singleton (RF07)
        this.termosProibidos = new ArrayList<>(
                ConfiguracaoSistema.getInstancia().getTermosProibidos()
        );
    }

    // Permite adicionar termos proibidos dinamicamente
    public void adicionarTermo(String termo) {
        if (termo != null && !termo.trim().isEmpty()) {
            termosProibidos.add(termo.toLowerCase().trim());
        }
    }

    @Override
    protected boolean validar(Anuncio anuncio) {
        String titulo = anuncio.getTitulo() != null ? anuncio.getTitulo().toLowerCase() : "";
        String descricao = anuncio.getDescricao() != null ? anuncio.getDescricao().toLowerCase() : "";
        String textoCompleto = titulo + " " + descricao;

        List<String> termosEncontrados = new ArrayList<>();

        for (String termo : termosProibidos) {
            if (textoCompleto.contains(termo.toLowerCase())) {
                termosEncontrados.add(termo);
            }
        }

        if (!termosEncontrados.isEmpty()) {
            this.mensagemErro = "Termos proibidos encontrados: " + String.join(", ", termosEncontrados);
            return false;
        }

        this.mensagemErro = null;
        return true;
    }
}