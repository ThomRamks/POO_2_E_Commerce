package impl.pagamento;

import interfaces.IPagamentos;

import java.math.BigInteger;
import java.util.Random;

public class PagamentoBoleto implements IPagamentos {
    @Override
    public void pagar(double valor) {
        System.out.println("Valor a ser pago: R$ " + valor);
        Random randNum = new Random();
        byte[] b = new byte[6];
        randNum.nextBytes(b);
        BigInteger bigInt = new BigInteger(b);
        System.out.println("|||| ||| |||| || |||| ||||| || |||| |");
        System.out.println(bigInt);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Pague seu boleto em seu banco de preferência e o pedido será processado em até 3 dias úteis\n");
    }
}
