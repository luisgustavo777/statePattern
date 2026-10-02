package org.example.pedido;

/**
 * Lançada quando uma ação não é permitida no estado atual do pedido
 * (por exemplo, enviar um pedido que ainda não foi pago).
 */
public class TransicaoInvalidaException extends RuntimeException {

    public TransicaoInvalidaException(String acao, String estado) {
        super("Não é possível '" + acao + "' quando o pedido está no estado '" + estado + "'.");
    }
}
