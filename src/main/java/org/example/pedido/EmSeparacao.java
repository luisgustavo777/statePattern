package org.example.pedido;

/** Itens sendo separados e embalados no centro de distribuição. */
public class EmSeparacao implements PedidoState {

    @Override
    public String getNome() {
        return "Em separação";
    }

    @Override
    public void enviar(Pedido pedido, String codigoRastreio) {
        if (codigoRastreio == null || codigoRastreio.isBlank()) {
            throw new IllegalArgumentException("Informe o código de rastreio para enviar o pedido.");
        }
        pedido.setCodigoRastreio(codigoRastreio);
        pedido.setEstado(new Enviado(), "rastreio " + codigoRastreio);
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado(), "pagamento estornado e itens devolvidos ao estoque");
    }
}
