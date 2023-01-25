import loja.Catalogo;
import loja.Loja;

public class Aplicacao {
    public static void main(String[] args) {
        Catalogo.getCatalogo().cadastrarProduto();
        Loja.getInstance().inicio();
    }
}