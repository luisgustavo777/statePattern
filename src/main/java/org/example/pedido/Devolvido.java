package org.example.pedido;

/** Estado final: pedido devolvido pelo cliente. Nenhuma ação é permitida. */
public class Devolvido implements PedidoState {

    @Override
    public String getNome() {
        return "Devolvido";
    }
}
