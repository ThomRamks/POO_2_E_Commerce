package impl.pagamento;

import interfaces.IAutenticar;
import interfaces.IPagamentos;

import java.util.Scanner;

public class PagamentoPayPal implements IPagamentos, IAutenticar {
    @Override
    public void pagar(double valor) {
        Scanner input = new Scanner(System.in);
        System.out.println("Valor a ser pago: R$ " + valor);
        System.out.print("Digite a sua conta PayPal: ");
        String conta = input.nextLine();
        autenticar(conta);
        System.out.print("Digite a sua senha: ");
        String senha = input.nextLine();
        autenticar(senha);
        System.out.println("Seu pagamento será processado em breve e você receberá um comprovante\n");
    }

    @Override
    public void autenticar(String dado) {
        System.out.println("Checando dado...");
        System.out.println("........");
        System.out.println("Dado autenticado!");
    }
}