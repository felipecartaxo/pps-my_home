package model.chain_of_responsibility.concrete_handlers;

import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;

import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Handler responsável por verificar termos proibidos no anúncio
 */
public class TermosProibidosHandler extends ModeracaoHandlerBase {

    private List<String> termosProibidos;

    public TermosProibidosHandler() {
        super("Validador de Termos Proibidos");
        carregarTermosProibidos();
    }

    // Defina os termos proibidos aqui
    private void carregarTermosProibidos() {
        // TODO: Integrar com ConfiguracaoSistema (RF07 - Singleton)
        this.termosProibidos = new ArrayList<>();
        termosProibidos.add("golpe");
        termosProibidos.add("fraude");
        termosProibidos.add("spam");
        termosProibidos.add("grátis");
    }

    public void setTermosProibidos(List<String> termos) {
        this.termosProibidos = new ArrayList<>(termos);
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