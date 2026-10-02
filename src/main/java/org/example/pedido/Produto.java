package org.example.pedido;

import java.math.BigDecimal;

/** Produto do catálogo da loja. */
public record Produto(String sku, String nome, BigDecimal preco) {
}
