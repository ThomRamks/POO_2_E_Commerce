package impl.pagamento;

import interfaces.IPagamentos;

import java.util.Scanner;

public class PagamentoCartao implements IPagamentos {
    @Override
    public void pagar(double valor) {
        Scanner input = new Scanner(System.in);
        System.out.println("Valor a ser pago: R$ " + valor);
        System.out.println("Este valor pode ser parcelado em até 3x");
        System.out.println("Deseja parcelar a compra? Digite S para Sim e N para Não");
        String escolha = input.nextLine();
        if (escolha.equalsIgnoreCase("S")) {
            new PagamentoCartaoParcelado().pagar(valor);
        } else if (escolha.equalsIgnoreCase("N")) {
            new PagamentoCartaoVista().pagar(valor);
        } else {
            System.out.println("Opção inválida");
            pagar(valor);
        }
    }
}
