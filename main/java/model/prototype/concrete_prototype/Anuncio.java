package model.prototype.concrete_prototype;

import model.factory_method.product.Imovel;
import model.observer.GerenciadorObservadores;
import model.observer.ObservadorAnuncio;
import model.prototype.AnuncioPrototype;
import model.state.EstadoAnuncio;
import model.state.concrete_states.EstadoRascunho;
import enums.TipoTransacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * PADRÕES APLICADOS:
 *
 * 1. PROTOTYPE (RF02)
 *    PAPEL: ConcretePrototype
 *    FUNÇÃO: Permite clonagem de anúncios a partir de configurações padrão
 *
 * 2. STATE (RF04)
 *    PAPEL: Context
 *    FUNÇÃO: Mantém referência ao estado atual e delega ações de transição
 *
 * 3. OBSERVER (RF04)
 *    PAPEL: Utiliza Subject por composição (GerenciadorObservadores)
 *    FUNÇÃO: Delega o gerenciamento de observers para classe especializada
 */
public class Anuncio implements AnuncioPrototype {

    // Identificador único do anúncio
    private final String id;

    // Atributos obrigatórios do anúncio (RF01)
    private String titulo;
    private Imovel imovel;
    private double preco;

    // Atributos opcionais
    private String descricao;
    private TipoTransacao tipoTransacao;
    private List<String> fotos;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private String anuncianteId;

    // PROTOTYPE: chave do protótipo para identificação no registry
    private String chavePrototipo;

    // STATE: referência ao estado atual do anúncio
    private EstadoAnuncio estadoAtual;

    // OBSERVER: gerenciador de observadores (composição)
    private final GerenciadorObservadores gerenciadorObservadores;

    // Inicializa o anúncio no estado Rascunho (conforme RF04)
    public Anuncio() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.estadoAtual = new EstadoRascunho();
        this.gerenciadorObservadores = new GerenciadorObservadores();
        this.fotos = new ArrayList<>();
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        this.tipoTransacao = TipoTransacao.VENDA;
    }

    // Construtor com parâmetros obrigatórios (RF01)
    public Anuncio(String titulo, Imovel imovel, double preco) {
        this();
        this.titulo = titulo;
        this.imovel = imovel;
        this.preco = preco;
    }

    /**
     * Construtor de cópia (PROTOTYPE)
     *
     * IMPORTANTE:
     * - Novo ID é gerado para a cópia
     * - Estado sempre inicia em Rascunho
     * - Novo gerenciador de observadores (não herda observers do original)
     * - AnuncianteId não é copiado
     */
    private Anuncio(Anuncio original) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.titulo = original.titulo;
        this.preco = original.preco;
        this.descricao = original.descricao;
        this.tipoTransacao = original.tipoTransacao;
        this.chavePrototipo = original.chavePrototipo;
        this.fotos = new ArrayList<>(original.fotos);
        this.imovel = original.imovel;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        this.anuncianteId = null;

        // Novo anúncio sempre começa em Rascunho
        this.estadoAtual = new EstadoRascunho();

        // Novo gerenciador (não herda observers do original)
        this.gerenciadorObservadores = new GerenciadorObservadores();
    }

    // ========================================================================
    // PROTOTYPE PATTERN - Clonagem
    // ========================================================================

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

    // ========================================================================
    // STATE PATTERN - Métodos que delegam para o estado atual
    // ========================================================================

    // Envia o anúncio para moderação
    public void enviarParaModeracao() {
        String estadoAnterior = estadoAtual.getNomeEstado();
        estadoAtual.enviarParaModeracao(this);
        verificarMudancaEstado(estadoAnterior);
    }

    // Aprova o anúncio (após moderação - RF03)
    public void aprovar() {
        String estadoAnterior = estadoAtual.getNomeEstado();
        estadoAtual.aprovar(this);
        verificarMudancaEstado(estadoAnterior);
    }

    // Reprova o anúncio (na moderação - RF03)
    public void reprovar() {
        String estadoAnterior = estadoAtual.getNomeEstado();
        estadoAtual.reprovar(this);
        verificarMudancaEstado(estadoAnterior);
    }

    // Marca o anúncio como vendido/alugado
    public void vender() {
        String estadoAnterior = estadoAtual.getNomeEstado();
        estadoAtual.vender(this);
        verificarMudancaEstado(estadoAnterior);
    }

    // Suspende o anúncio
    public void suspender() {
        String estadoAnterior = estadoAtual.getNomeEstado();
        estadoAtual.suspender(this);
        verificarMudancaEstado(estadoAnterior);
    }

    // Retorna o anúncio para rascunho
    public void voltarParaRascunho() {
        String estadoAnterior = estadoAtual.getNomeEstado();
        estadoAtual.voltarParaRascunho(this);
        verificarMudancaEstado(estadoAnterior);
    }

    // Verifica se houve mudança de estado e notifica observadores
    private void verificarMudancaEstado(String estadoAnterior) {
        String estadoNovo = estadoAtual.getNomeEstado();
        if (!estadoAnterior.equals(estadoNovo)) {
            this.dataAtualizacao = LocalDateTime.now();
            // Delega a notificação para o gerenciador de observadores
            gerenciadorObservadores.notificar(this, estadoAnterior, estadoNovo);
        }
    }

    // Método usado pelos ConcreteStates para alterar o estado interno
    public void setEstadoInterno(EstadoAnuncio novoEstado) {
        this.estadoAtual = novoEstado;
    }

    // Retorna o nome do estado atual
    public String getEstadoAtual() {
        return estadoAtual.getNomeEstado();
    }

    // ========================================================================
    // OBSERVER PATTERN - Delegação para GerenciadorObservadores
    // ========================================================================

    /**
     * Adiciona um observador à lista de notificação
     * Delega para o GerenciadorObservadores
     */
    public void adicionarObservador(ObservadorAnuncio observador) {
        gerenciadorObservadores.adicionar(observador);
    }

    /**
     * Remove um observador da lista de notificação
     * Delega para o GerenciadorObservadores
     */
    public void removerObservador(ObservadorAnuncio observador) {
        gerenciadorObservadores.remover(observador);
    }

    // ========================================================================
    // GETTERS E SETTERS
    // ========================================================================

    public String getId() {
        return id;
    }

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

    public List<String> getFotos() {
        return new ArrayList<>(fotos);
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

    // Valida se o anúncio possui os atributos obrigatórios preenchidos (RF01)
    public boolean isValido() {
        return titulo != null && !titulo.trim().isEmpty() &&
                imovel != null &&
                preco > 0;
    }

    // Retorna uma representação textual resumida do anúncio
    public String getResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("═".repeat(60)).append("\n");
        sb.append("ANÚNCIO [").append(id).append("]: ");
        sb.append(titulo != null ? titulo : "[Sem título]").append("\n");
        sb.append("═".repeat(60)).append("\n");
        sb.append("Estado: ").append(estadoAtual.getNomeEstado()).append("\n");
        sb.append("Tipo de Transação: ").append(tipoTransacao.getDescricao()).append("\n");
        sb.append("Preço: R$ ").append(String.format("%,.2f", preco)).append("\n");

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