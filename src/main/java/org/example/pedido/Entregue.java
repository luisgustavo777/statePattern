package org.example.pedido;

/** Pedido recebido. Ainda permite uma devolução (direito de arrependimento / troca). */
public class Entregue implements PedidoState {

    @Override
    public String getNome() {
        return "Entregue";
    }

    @Override
    public void devolver(Pedido pedido) {
        pedido.setEstado(new Devolvido(), "devolução aceita e valor reembolsado");
    }
}
