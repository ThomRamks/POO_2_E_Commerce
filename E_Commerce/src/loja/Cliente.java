package loja;

public class Cliente {
    private String nome;
    private String login;
    private String senha;
    private String CPF;
    private String endereco;
    private loja.Sacola sacolaCliente = new loja.Sacola();

    protected Cliente(String nome, String login, String CPF, String endereco, String senha) {
        this.nome = nome;
        this.login = login;
        this.CPF = CPF;
        this.endereco = endereco;
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getCPF() {
        return this.CPF;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public loja.Sacola getSacolaCliente(){
        return this.sacolaCliente;
    }

}