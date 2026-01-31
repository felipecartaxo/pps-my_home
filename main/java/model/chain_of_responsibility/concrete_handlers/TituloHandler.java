package model.chain_of_responsibility.concrete_handlers;

import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÃO: Handler responsável por validar o título do anúncio
 */
public class TituloHandler extends ModeracaoHandlerBase {

    private int tamanhoMinimoTitulo = 10;
    private int tamanhoMaximoTitulo = 100;

    public TituloHandler() {
        super("Validador de Título");
    }

    public void configurarLimites(int minimo, int maximo) {
        this.tamanhoMinimoTitulo = minimo;
        this.tamanhoMaximoTitulo = maximo;
    }

    @Override
    protected boolean validar(Anuncio anuncio) {
        String titulo = anuncio.getTitulo();

        // Validação: Título não pode ser nulo ou vazio
        if (titulo == null || titulo.trim().isEmpty()) {
            this.mensagemErro = "Título não pode estar vazio";
            return false;
        }

        String tituloLimpo = titulo.trim();

        // Validação: Tamanho mínimo
        if (tituloLimpo.length() < tamanhoMinimoTitulo) {
            this.mensagemErro = "Título muito curto: " + tituloLimpo.length() +
                    " caracteres. Mínimo: " + tamanhoMinimoTitulo;
            return false;
        }

        // Validação: Tamanho máximo
        if (tituloLimpo.length() > tamanhoMaximoTitulo) {
            this.mensagemErro = "Título muito longo: " + tituloLimpo.length() +
                    " caracteres. Máximo: " + tamanhoMaximoTitulo;
            return false;
        }

        // Validação: Não pode ser todo em maiúsculas (spam)
        if (isTodoMaiusculas(tituloLimpo)) {
            this.mensagemErro = "Título não pode estar todo em maiúsculas";
            return false;
        }

        // Aprovado
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