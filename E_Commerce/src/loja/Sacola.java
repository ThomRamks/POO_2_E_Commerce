package loja;

import interfaces.IProduto;
import interfaces.ISacola;
import impl.produto.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sacola implements ISacola {
    List<IProduto> produtos = new ArrayList<>();
    @Override
    public void adicionar(IProduto produto) {
        produtos.add(produto);
    }

    public List<IProduto> getProdutos() {
        return produtos;
    }
    protected HashMap sacolaAtualizada () {
        HashMap<IProduto, Integer> novaSacola = new HashMap<>();
        for (IProduto produto : getProdutos()) {
            if (novaSacola.containsKey(produto)) {
                Integer contador = novaSacola.get(produto);
                novaSacola.put(produto, contador + 1);
            } else {
                novaSacola.put(produto, 1);
            }
        }
        return novaSacola;
    }

    protected double valorFinal(HashMap<Produto, Integer> sacola) {
        double total = 0;
        for (Map.Entry<Produto, Integer> item : sacola.entrySet()) {
            total = total + (item.getValue() * item.getKey().getValor());
        }
        return total;
    }


}
