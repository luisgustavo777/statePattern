package org.example.pedido;

/** Estado inicial: carrinho aberto. É o único estado em que se pode alterar os itens. */
public class AguardandoPagamento implements PedidoState {

    @Override
    public String getNome() {
        return "Aguardando pagamento";
    }

    @Override
    public void adicionarItem(Pedido pedido, Produto produto, int quantidade) {
        pedido.incluirItem(new ItemPedido(produto, quantidade));
    }

    @Override
    public void pagar(Pedido pedido, FormaPagamento forma) {
        if (pedido.getItens().isEmpty()) {
            throw new IllegalStateException("Não é possível pagar um pedido sem itens.");
        }
        pedido.setFormaPagamento(forma);
        pedido.setEstado(new PagamentoAprovado(), "pago via " + forma);
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado(), "cancelado antes do pagamento");
    }
}
