package org.example.pedido;

import java.math.BigDecimal;

/** Linha do pedido: um produto e a quantidade comprada. */
public record ItemPedido(Produto produto, int quantidade) {

    public ItemPedido {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
    }

    public BigDecimal subtotal() {
        return produto.preco().multiply(BigDecimal.valueOf(quantidade));
    }
}
