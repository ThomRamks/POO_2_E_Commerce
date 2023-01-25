package impl.produto;

import interfaces.IProduto;

public abstract class Produto implements IProduto {
    private String tipo;
    private String cor;
    private String tamanho;
    private double valor;

    public String getTipo() {
        return tipo;
    }

    public String getCor() {
        return cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public double getValor() {
        return valor;
    }


    public Produto(String tipo, String cor, String tamanho, double valor) {
        this.tipo = tipo;
        this.cor = cor;
        this.tamanho = tamanho;
        this.valor = valor;
    }
}
