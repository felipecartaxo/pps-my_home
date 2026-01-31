package model.chain_of_responsibility.concrete_handlers;

import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;
import model.singleton.ConfiguracaoSistema;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Valida o tamanho e formato do título do anúncio
 *
 * INTEGRAÇÃO COM RF07 (Singleton):
 * - Os limites de tamanho são carregados do Singleton ConfiguracaoSistema
 */
public class TituloHandler extends ModeracaoHandlerBase {

    private int tamanhoMinimo;
    private int tamanhoMaximo;

    public TituloHandler() {
        super("Validador de Título");
        carregarConfiguracoes();
    }

    private void carregarConfiguracoes() {
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();
        this.tamanhoMinimo = config.getTamanhoMinimoTitulo();
        this.tamanhoMaximo = config.getTamanhoMaximoTitulo();
    }

    @Override
    protected boolean validar(Anuncio anuncio) {
        String titulo = anuncio.getTitulo();

        if (titulo == null || titulo.trim().isEmpty()) {
            this.mensagemErro = "Título não pode estar vazio";
            return false;
        }

        String tituloLimpo = titulo.trim();

        if (tituloLimpo.length() < tamanhoMinimo) {
            this.mensagemErro = "Título muito curto: " + tituloLimpo.length() +
                    " caracteres. Mínimo: " + tamanhoMinimo;
            return false;
        }

        if (tituloLimpo.length() > tamanhoMaximo) {
            this.mensagemErro = "Título muito longo: " + tituloLimpo.length() +
                    " caracteres. Máximo: " + tamanhoMaximo;
            return false;
        }

        if (isTodoMaiusculas(tituloLimpo)) {
            this.mensagemErro = "Título não pode estar todo em maiúsculas";
            return false;
        }

        this.mensagemErro = null;
        return true;
    }

    private boolean isTodoMaiusculas(String texto) {
        String apenasLetras = texto.replaceAll("[^a-zA-Z]", "");
        if (apenasLetras.isEmpty() || apenasLetras.length() <= 5) {
            return false;
        }
        return apenasLetras.equals(apenasLetras.toUpperCase());
    }
}