package org.example.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Contexto do State Pattern (pedido de e-commerce). Não contém nenhum
 * if/switch sobre o estado: cada ação é delegada ao estado atual.
 */
public class Pedido {

    private final int id;
    private final String cliente;
    private final List<ItemPedido> itens = new ArrayList<>();
    private FormaPagamento formaPagamento;
    private String codigoRastreio;
    private PedidoState estado;
    private final List<String> historico = new ArrayList<>();

    public Pedido(int id, String cliente) {
        this.id = id;
        this.cliente = cliente;
        setEstado(new AguardandoPagamento(), "pedido criado");
    }

    // ---- ações delegadas ao estado atual ----
    public void adicionarItem(Produto produto, int quantidade) { estado.adicionarItem(this, produto, quantidade); }
    public void pagar(FormaPagamento forma)                    { estado.pagar(this, forma); }
    public void separar()                                      { estado.separar(this); }
    public void enviar(String codigoRastreio)                  { estado.enviar(this, codigoRastreio); }
    public void entregar()                                     { estado.entregar(this); }
    public void cancelar()                                     { estado.cancelar(this); }
    public void devolver()                                     { estado.devolver(this); }

    // ---- usados somente pelos estados (visibilidade de pacote) ----
    void setEstado(PedidoState novoEstado, String detalhe) {
        this.estado = novoEstado;
        historico.add(novoEstado.getNome() + " (" + detalhe + ")");
    }

    void incluirItem(ItemPedido item) {
        itens.add(item);
    }

    void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    // ---- consultas ----
    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public String getEstadoAtual() { return estado.getNome(); }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public String getCodigoRastreio() { return codigoRastreio; }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public List<String> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public BigDecimal getValorTotal() {
        return itens.stream()
                .map(ItemPedido::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return String.format(Locale.of("pt", "BR"), "Pedido #%d de %s | %d item(ns) | R$ %.2f | %s",
                id, cliente, itens.size(), getValorTotal(), estado.getNome());
    }
}
