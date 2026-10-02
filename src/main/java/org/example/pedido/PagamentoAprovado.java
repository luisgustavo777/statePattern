package org.example.pedido;

/** Pagamento confirmado; o pedido segue para a separação do estoque. */
public class PagamentoAprovado implements PedidoState {

    @Override
    public String getNome() {
        return "Pagamento aprovado";
    }

    @Override
    public void separar(Pedido pedido) {
        pedido.setEstado(new EmSeparacao(), "itens sendo separados no estoque");
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado(), "pagamento estornado");
    }
}
