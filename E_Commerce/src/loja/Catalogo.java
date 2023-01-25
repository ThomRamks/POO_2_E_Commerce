package loja;

import impl.produto.Calcado;
import interfaces.IProduto;
import impl.produto.Produto;
import impl.produto.Vestuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalogo {
    private List<IProduto> produtosEstoque = new ArrayList<>();
    private static final Catalogo catalogo = new Catalogo();

    private Catalogo() {
    }

    public static Catalogo getCatalogo() {
        return catalogo;
    }

    public List<IProduto> getProdutosEstoque() {
        return produtosEstoque;
    }

    public void cadastrarProduto() {
        Produto vestuario1 = new Vestuario("CAMISETA", "PRETA", "PP", 25.00);
        Produto vestuario2 = new Vestuario("CAMISETA", "PRETA", "P", 25.00);
        Produto vestuario3 = new Vestuario("CAMISETA", "PRETA", "M",25.00);
        Produto vestuario4 = new Vestuario("CAMISETA", "PRETA",  "G", 25.00);
        Produto vestuario5 = new Vestuario("CAMISETA", "PRETA",  "GG", 25.00);
        Produto vestuario6 = new Vestuario("CAMISETA", "BRANCA",  "PP", 25.00);
        Produto vestuario7 = new Vestuario("CAMISETA", "BRANCA",  "P", 25.00);
        Produto vestuario8 = new Vestuario("CAMISETA", "BRANCA",  "M", 25.00);
        Produto vestuario9 = new Vestuario("CAMISETA", "BRANCA",  "G", 25.00);
        Produto vestuario10 = new Vestuario("CAMISETA", "BRANCA",  "GG", 25.00);
        Produto vestuario11 = new Vestuario("CAMISETA","AZUL", "PP", 29.00);
        Produto vestuario12 = new Vestuario("CAMISETA","AZUL", "P", 29.00);
        Produto vestuario13 = new Vestuario("CAMISETA","AZUL","M", 29.00);
        Produto vestuario14 = new Vestuario("CAMISETA","AZUL","G", 29.00);
        Produto vestuario15 = new Vestuario("CAMISETA","AZUL", "GG", 29.00);
        Produto vestuario16 = new Vestuario("CAMISETA","VERMELHA", "PP", 29.00);
        Produto vestuario17 = new Vestuario("CAMISETA","VERMELHA", "P", 29.00);
        Produto vestuario18 = new Vestuario("CAMISETA","VERMELHA", "M", 29.00);
        Produto vestuario19 = new Vestuario("CAMISETA","VERMELHA", "G", 29.00);
        Produto vestuario20 = new Vestuario("CAMISETA","VERMELHA", "GG", 29.00);
        Produto calcado1 = new Calcado("BOTA","MARROM", "40", 56.00);

        Collections.addAll(produtosEstoque, vestuario1, vestuario2, vestuario3, vestuario4, vestuario5, vestuario6,
                vestuario7, vestuario8, vestuario9, vestuario10, vestuario11, vestuario12, vestuario13, vestuario14,
                vestuario15, vestuario16, vestuario17, vestuario18, vestuario19, vestuario20, calcado1);

    }


}