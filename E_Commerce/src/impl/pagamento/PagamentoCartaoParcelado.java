package impl.pagamento;

import interfaces.IAutenticar;

import java.util.InputMismatchException;
import java.util.Scanner;


public class PagamentoCartaoParcelado extends PagamentoCartao implements IAutenticar {
    @Override
    public void pagar(double valor) {
        double parcela = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Em quantas vezes deseja parcelar a compra?");
        try {
            int parcelas = input.nextInt();
            if (parcelas == 2) {
                parcela = valor / 2;
            } else if (parcelas == 3) {
                parcela = valor / 3;
            } else {
                System.out.println("Valor inválido");
                pagar(valor);
            }
            System.out.println("Valor de cada parcela: R$ " + parcela);

            System.out.print("Digite a bandeira do seu cartão: ");
            String cartao = input.next();
            System.out.print("Digite o número do seu cartão: ");
            int numero = input.nextInt();
            autenticar(Integer.toString(numero));
            System.out.print("Digite o código de segurança: ");
            int codigo = input.nextInt();
            autenticar(Integer.toString(codigo));
            System.out.print("\nCartão " + cartao + " - " + numero + " aceito.");
            System.out.print("\nSeu pagamento será processado em breve e você receberá um comprovante\n");
        } catch (InputMismatchException e) {
            System.out.print("Insira os dados númericos de seu cartão.\n");
            pagar(valor);
        }
    }

    @Override
    public void autenticar(String dado) {
        System.out.println("Checando dado...");
        System.out.println("........");
        System.out.println("Dado autenticado!");
    }
}