package model.prototype.concrete_prototype;

import enums.EstadoAnuncio;
import enums.TipoTransacao;
import model.factory_method.product.Imovel;
import model.prototype.AnuncioPrototype;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO: Prototype
 * PAPEL: Concrete Prototype
 * FUNÇÃO: Representa um anúncio de imóvel
 *
 * Implementa a interface AnuncioPrototype para permitir clonagem
 *
 * ATRIBUTOS OBRIGATÓRIOS (conforme RF01):
 * - título
 * - tipo do imóvel
 * - preço
 */
public class Anuncio implements AnuncioPrototype {

    // Atributos obrigatórios
    private String titulo;
    private Imovel imovel;
    private double preco;

    // Atributos adicionais
    private String descricao;
    private TipoTransacao tipoTransacao;
    private EstadoAnuncio estado;
    private List<String> fotos;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private String anuncianteId;

    // Chave do protótipo (para identificação no registro)
    private String chavePrototipo;

    // Construtor padrão - cria anúncio em estado Rascunho
    public Anuncio() {
        this.estado = EstadoAnuncio.RASCUNHO;
        this.fotos = new ArrayList<>();
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        this.tipoTransacao = TipoTransacao.VENDA; // Padrão
    }

    // Construtor com parâmetros obrigatórios
    public Anuncio(String titulo, Imovel imovel, double preco) {
        this();
        this.titulo = titulo;
        this.imovel = imovel;
        this.preco = preco;
    }

    /**
     * Construtor de cópia - usado internamente pelo método clonar()
     *
     * Realiza deep copy para garantir independência entre o original e a cópia.
     */
    private Anuncio(Anuncio original) {
        this.titulo = original.titulo;
        this.preco = original.preco;
        this.descricao = original.descricao;
        this.tipoTransacao = original.tipoTransacao;
        this.estado = EstadoAnuncio.RASCUNHO; // Cópia sempre começa como rascunho
        this.chavePrototipo = original.chavePrototipo;

        // Deep copy da lista de fotos
        this.fotos = new ArrayList<>(original.fotos);

        // Deep copy do imóvel (se existir)
        // Nota: O imóvel também precisa ser clonável para deep copy completo
        // Por ora, assumimos que o imóvel será configurado após a clonagem
        this.imovel = original.imovel; // Shallow copy por enquanto

        // Novas datas para a cópia
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();

        // AnuncianteId não é copiado - será definido pelo novo anunciante
        this.anuncianteId = null;
    }

    /**
     * PADRÃO PROTOTYPE: Método de clonagem
     *
     * Cria uma cópia independente do anúncio atual.
     * A cópia começa no estado RASCUNHO e com novas datas.
     */
    @Override
    public AnuncioPrototype clonar() {
        return new Anuncio(this);
    }

    @Override
    public String getChavePrototipo() {
        return chavePrototipo;
    }

    public void setChavePrototipo(String chavePrototipo) {
        this.chavePrototipo = chavePrototipo;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public EstadoAnuncio getEstado() {
        return estado;
    }

    public void setEstado(EstadoAnuncio estado) {
        this.estado = estado;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public List<String> getFotos() {
        return new ArrayList<>(fotos); // Retorna cópia para encapsulamento
    }

    public void adicionarFoto(String urlFoto) {
        this.fotos.add(urlFoto);
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void removerFoto(String urlFoto) {
        this.fotos.remove(urlFoto);
        this.dataAtualizacao = LocalDateTime.now();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public String getAnuncianteId() {
        return anuncianteId;
    }

    public void setAnuncianteId(String anuncianteId) {
        this.anuncianteId = anuncianteId;
        this.dataAtualizacao = LocalDateTime.now();
    }

    // Valida se o anúncio possui os atributos obrigatórios preenchidos.
    public boolean isValido() {
        return titulo != null && !titulo.trim().isEmpty() &&
                imovel != null &&
                preco > 0;
    }

    // Retorna uma representação textual do anúncio.
    public String getResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("═".repeat(60)).append("\n");
        sb.append("ANÚNCIO: ").append(titulo != null ? titulo : "[Sem título]").append("\n");
        sb.append("═".repeat(60)).append("\n");
        sb.append("Tipo de Transação: ").append(tipoTransacao.getDescricao()).append("\n");
        sb.append("Preço: R$ ").append(String.format("%,.2f", preco)).append("\n");
        sb.append("Estado: ").append(estado.getDescricao()).append("\n");

        if (imovel != null) {
            sb.append("Tipo de Imóvel: ").append(imovel.getTipo()).append("\n");
            sb.append("Área: ").append(imovel.getArea()).append(" m²\n");
            sb.append("Localização: ").append(imovel.getLocalizacao()).append("\n");
        }

        if (descricao != null && !descricao.isEmpty()) {
            sb.append("Descrição: ").append(descricao).append("\n");
        }

        sb.append("Fotos: ").append(fotos.size()).append(" imagem(ns)\n");
        sb.append("─".repeat(60));

        return sb.toString();
    }

    // Retorna descrição detalhada incluindo informações do imóvel
    public String getDescricaoCompleta() {
        StringBuilder sb = new StringBuilder();
        sb.append(getResumo()).append("\n\n");

        if (imovel != null) {
            sb.append("DETALHES DO IMÓVEL:\n");
            sb.append(imovel.getDescricaoDetalhada());
        }

        return sb.toString();
    }
}