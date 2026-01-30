package model.chain_of_responsibility.concrete_handlers;

import model.chain_of_responsibility.base_handler.ModeracaoHandlerBase;
import model.factory_method.product.Imovel;
import model.prototype.concrete_prototype.Anuncio;

/**
 * PADRÃO: Chain of Responsibility
 * PAPEL: ConcreteHandler
 * FUNÇÂO: Handler responsável por validar o imóvel associado ao anúncio.
 */
public class ImovelHandler extends ModeracaoHandlerBase {

    public ImovelHandler() {
        super("Validador de Imóvel");
    }

    @Override
    protected boolean validar(Anuncio anuncio) {
        Imovel imovel = anuncio.getImovel();

        if (imovel == null) {
            this.mensagemErro = "Anúncio deve ter um imóvel associado";
            return false;
        }

        if (!imovel.isValido()) {
            this.mensagemErro = "Imóvel com dados incompletos ou inválidos";
            return false;
        }

        String localizacao = imovel.getLocalizacao();
        if (localizacao == null || localizacao.trim().isEmpty() ||
                localizacao.equals("[A definir]")) {
            this.mensagemErro = "Localização do imóvel deve ser informada";
            return false;
        }

        this.mensagemErro = null;
        return true;
    }
}