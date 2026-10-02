package org.example;

import org.example.pedido.FormaPagamento;
import org.example.pedido.Pedido;
import org.example.pedido.Produto;
import org.example.pedido.TransicaoInvalidaException;

import java.math.BigDecimal;

public class Main {

    private static final Produto TECLADO = new Produto("TEC-001", "Teclado mecânico", new BigDecimal("299.90"));
    private static final Produto MOUSE = new Produto("MOU-002", "Mouse sem fio", new BigDecimal("89.90"));
    private static final Produto MONITOR = new Produto("MON-003", "Monitor 24\"", new BigDecimal("899.00"));

    public static void main(String[] args) {
        fluxoCompleto();
        cancelamentoAposPagamento();
        devolucao();
        operacoesInvalidas();
    }

    private static void fluxoCompleto() {
        System.out.println("=== Cenário 1: compra completa ===");
        Pedido pedido = new Pedido(1, "Ana");
        pedido.adicionarItem(TECLADO, 1);
        pedido.adicionarItem(MOUSE, 2);
        System.out.println(pedido);

        pedido.pagar(FormaPagamento.PIX);        System.out.println(pedido);
        pedido.separar();                        System.out.println(pedido);
        pedido.enviar("BR123456789XX");          System.out.println(pedido);
        pedido.entregar();                       System.out.println(pedido);
        imprimirHistorico(pedido);
    }

    private static void cancelamentoAposPagamento() {
        System.out.println("\n=== Cenário 2: cancelamento durante a separação ===");
        Pedido pedido = new Pedido(2, "Bruno");
        pedido.adicionarItem(MONITOR, 1);
        pedido.pagar(FormaPagamento.CARTAO_CREDITO);
        pedido.separar();
        pedido.cancelar();
        System.out.println(pedido);
        imprimirHistorico(pedido);
    }

    private static void devolucao() {
        System.out.println("\n=== Cenário 3: devolução após a entrega ===");
        Pedido pedido = new Pedido(3, "Carla");
        pedido.adicionarItem(MOUSE, 1);
        pedido.pagar(FormaPagamento.BOLETO);
        pedido.separar();
        pedido.enviar("BR987654321XX");
        pedido.entregar();
        pedido.devolver();
        System.out.println(pedido);
        imprimirHistorico(pedido);
    }

    private static void operacoesInvalidas() {
        System.out.println("\n=== Cenário 4: operações inválidas ===");
        Pedido pedido = new Pedido(4, "Diego");
        tentar("pagar pedido vazio", () -> pedido.pagar(FormaPagamento.PIX));
        tentar("enviar sem pagar", () -> pedido.enviar("BR000"));

        pedido.adicionarItem(TECLADO, 1);
        pedido.pagar(FormaPagamento.PIX);
        tentar("adicionar item depois de pago", () -> pedido.adicionarItem(MOUSE, 1));

        pedido.separar();
        tentar("enviar sem código de rastreio", () -> pedido.enviar(" "));
        pedido.enviar("BR555");
        tentar("cancelar depois de enviado", pedido::cancelar);

        pedido.entregar();
        pedido.devolver();
        tentar("devolver duas vezes", pedido::devolver);
        System.out.println(pedido);
    }

    private static void imprimirHistorico(Pedido pedido) {
        System.out.println("Histórico:");
        pedido.getHistorico().forEach(h -> System.out.println("  - " + h));
    }

    private static void tentar(String descricao, Runnable acao) {
        try {
            acao.run();
        } catch (TransicaoInvalidaException | IllegalStateException | IllegalArgumentException e) {
            System.out.println("[" + descricao + "] -> " + e.getMessage());
        }
    }
}
