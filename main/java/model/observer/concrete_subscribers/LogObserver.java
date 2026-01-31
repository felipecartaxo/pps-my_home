package model.observer.concrete_subscribers;

import model.observer.ObservadorAnuncio;
import model.prototype.concrete_prototype.Anuncio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO: Observer
 * PAPEL: ConcreteObserver
 * FUNÇÃO: Observador responsável por manter um log de todas as mudanças
 * de estado dos anúncios do sistema.
 *
 * RESPONSABILIDADE:
 * - Receber notificações de mudanças de estado do Subject (Anuncio)
 * - Registrar todas as transições em uma lista (histórico)
 * - Fornecer acesso ao histórico completo
 *
 * REGRA DE NEGÓCIO (RF04):
 * - Um mecanismo de Log deve reter a informação sobre mudanças de status
 *
 * PRINCÍPIOS APLICADOS:
 * - SRP: Responsável apenas por registrar logs
 * - GRASP - Information Expert: Sabe como formatar e armazenar logs
 */
public class LogObserver implements ObservadorAnuncio {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final List<String> registros;

    public LogObserver() {
        this.registros = new ArrayList<>();
    }

    @Override
    public void notificar(Anuncio anuncio, String estadoAnterior, String estadoNovo) {
        String registro = formatarRegistro(anuncio, estadoAnterior, estadoNovo);
        registros.add(registro);
    }

    private String formatarRegistro(Anuncio anuncio, String estadoAnterior, String estadoNovo) {
        StringBuilder sb = new StringBuilder();
        sb.append("[LOG] ");
        sb.append(LocalDateTime.now().format(FORMATTER));
        sb.append(" | Anúncio ID: ").append(anuncio.getId());
        sb.append(" | Título: '").append(anuncio.getTitulo()).append("'");
        sb.append(" | Transição: ").append(estadoAnterior);
        sb.append(" → ").append(estadoNovo);

        return sb.toString();
    }

    public List<String> getRegistros() {
        return new ArrayList<>(registros);
    }

    public String getUltimoRegistro() {
        if (registros.isEmpty()) {
            return null;
        }
        return registros.get(registros.size() - 1);
    }

    public int getTotalRegistros() {
        return registros.size();
    }

    public void limpar() {
        registros.clear();
    }
}