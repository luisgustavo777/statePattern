package org.example.pedido;

/** Pedido com a transportadora. Depois de enviado, não pode mais ser cancelado. */
public class Enviado implements PedidoState {

    @Override
    public String getNome() {
        return "Enviado";
    }

    @Override
    public void entregar(Pedido pedido) {
        pedido.setEstado(new Entregue(), "recebido pelo cliente");
    }
}
