package org.example.pedido;

/** Estado final: pedido cancelado. Nenhuma ação é permitida. */
public class Cancelado implements PedidoState {

    @Override
    public String getNome() {
        return "Cancelado";
    }
}
