package org.example.pedido;

/**
 * Interface State: declara todas as ações que o contexto (Pedido) pode delegar.
 * Por padrão toda ação é inválida; cada estado concreto sobrescreve apenas
 * as transições/operações que ele permite.
 */
public interface PedidoState {

    String getNome();

    default void adicionarItem(Pedido pedido, Produto produto, int quantidade) {
        throw new TransicaoInvalidaException("adicionar item", getNome());
    }

    default void pagar(Pedido pedido, FormaPagamento forma) {
        throw new TransicaoInvalidaException("pagar", getNome());
    }

    default void separar(Pedido pedido) {
        throw new TransicaoInvalidaException("separar", getNome());
    }

    default void enviar(Pedido pedido, String codigoRastreio) {
        throw new TransicaoInvalidaException("enviar", getNome());
    }

    default void entregar(Pedido pedido) {
        throw new TransicaoInvalidaException("entregar", getNome());
    }

    default void cancelar(Pedido pedido) {
        throw new TransicaoInvalidaException("cancelar", getNome());
    }

    default void devolver(Pedido pedido) {
        throw new TransicaoInvalidaException("devolver", getNome());
    }
}
