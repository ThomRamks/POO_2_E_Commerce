package impl.pagamento;

import interfaces.IAutenticar;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PagamentoCartaoVista extends PagamentoCartao implements IAutenticar {
    @Override
    public void pagar(double valor) {
        Scanner input = new Scanner(System.in);
        System.out.println("Valor a ser pago: R$ " + valor);
        try {
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