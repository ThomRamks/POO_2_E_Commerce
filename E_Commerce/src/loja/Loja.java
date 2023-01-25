package loja;

import java.util.*;

import erros.InvalidPasswordException;
import erros.UserNotFoundException;
import impl.pagamento.PagamentoBoleto;
import impl.pagamento.PagamentoCartao;
import impl.pagamento.PagamentoPayPal;
import interfaces.IProduto;

public class Loja {

    private List<Cliente> perfis = new ArrayList<>();
    private List<String> logins = new ArrayList<>();
    private List<String> CPFs = new ArrayList<>();

    private static final Loja INSTANCE = new Loja();

    private Loja() {
    }

    public static Loja getInstance() {
        return INSTANCE;
    }

    public void inicio() {
        System.out.println("******************************\n** Bem vindo à Dev_Basics! ** \n******************************");
        telaInicial(menuInicial());
    }

    private String menuInicial() {
        Scanner input = new Scanner(System.in);
        System.out.println("Escolha uma das opções: \nV para Visualizar o Catalogo; \nC para Cadastrar; " +
                "\nE para Entrar; \nF para Fechar.");
        String opcao = input.next();
        if (opcao.equalsIgnoreCase("V") || opcao.equalsIgnoreCase("C")
                || opcao.equalsIgnoreCase("E") || opcao.equalsIgnoreCase("F")) {
            return opcao;
        } else {
            System.out.println(" >> Insira uma opção válida << \n");
            return menuInicial();
        }
    }

    private void telaInicial(String opcao) {
        switch (opcao) {
            case "V":
            case "v":
                mostrarCatalogo();
                System.out.println("\nCaso queira comprar algum produto, faça seu login ou se cadastre primeiro!\n");
                Loja.getInstance().inicio();
                break;
            case "C":
            case "c":
                cadastro();
                break;
            case "E":
            case "e":
                entrar();
                break;
            case "F":
            case "f":
                System.out.println("**************************************\n**     Obrigado pela sua visita!    ** " +
                        "\n**************************************");
                System.exit(0);
        }
    }

    private void mostrarCatalogo() {
        System.out.println("Os seguintes produtos estão disponíveis: ");
        System.out.print("\n");
        for (IProduto produto : Catalogo.getCatalogo().getProdutosEstoque()) {
            System.out.println(produto.getTipo() + " de cor " + produto.getCor() +
                    ", tamanho " + produto.getTamanho() +
                    " no valor de R$ " + produto.getValor());
        }
    }

    private void cadastro() {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira seu nome: ");
        String nome = input.nextLine();
        checarDado(nome);
        System.out.print("Insira seu e-mail: ");
        String login = input.nextLine();
        checarLogin(login);
        System.out.print("Insira seu CPF (Formato: 000.000.000-00): ");
        String CPF = input.nextLine();
        checarCPF(CPF);
        System.out.print("Insira seu endereço: ");
        String endereco = input.nextLine();
        checarDado(endereco);
        System.out.print("Insira uma senha (deve conter, ao menos, 6 caracteres): ");
        String senha = input.nextLine();
        checarSenha(senha);

        System.out.println("\n**Cadastro realizado!**\n");
        logins.add(login);
        CPFs.add(CPF);
        perfis.add(new Cliente(nome, login, CPF, endereco, senha));
        Loja.getInstance().inicio();
    }

    private void checarDado(String dado) {
        if (dado.isBlank()) {
            System.out.println("\n >> O campo não pode ser deixado em branco! <<");
            System.out.println("** Voltando ao menu... **\n");
            Loja.getInstance().inicio();
        }
    }

    private void checarLogin(String login) {
        checarDado(login);
        if (logins.contains(login)) {
            System.out.println("\n >> Este e-mail já foi cadastrado previamente! <<");
            System.out.println("** Voltando ao menu... **\n");
            Loja.getInstance().inicio();
        }
    }

    private void checarCPF(String CPF) {
        if (CPF.length() < 14 || CPF.isBlank()) {
            System.out.println("\n >> CPF inválido: você deve digitar seu CPF no formato apontado! <<");
            System.out.println("** Voltando ao menu... **\n");
            Loja.getInstance().inicio();
        } else if (CPFs.contains(CPF)) {
            System.out.println("\n >> Este CPF já foi cadastrado previamente! <<");
            System.out.println("** Voltando ao menu... **\n");
            Loja.getInstance().inicio();

        }
    }

    private void checarSenha(String senha) {
        if (senha.length() < 6 || senha.isBlank()) {
            System.out.println("\n >> Senha inválida (deve conter, ao menos, 6 caracteres) <<");
            System.out.println("** Voltando ao menu... **\n");
            Loja.getInstance().inicio();
        }
    }

    private void entrar() {
        try {
            Cliente usuario = verificarLogin();
            areaLogada(usuario);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            Loja.getInstance().inicio();
        } catch (InvalidPasswordException e) {
            System.out.println(e.getMessage());
            Loja.getInstance().inicio();
        }
    }

    private Cliente verificarLogin() throws UserNotFoundException, InvalidPasswordException {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira seu e-mail: ");
        String log = input.next();
        System.out.print("Insira sua senha: ");
        String sen = input.next();
        String nome = null;
        boolean contem = false;
        Cliente usuario = null;
        int z = 0;
        if (perfis.size() == 0) {
            System.out.println("\n >> Usuário não encontrado! << ");
            System.out.println("** Voltando ao menu... **\n");
            Loja.getInstance().inicio();
        } else {
            for (int i = 0; i < perfis.size(); i++) {
                if (perfis.get(i).getLogin().contentEquals(log)) {
                    contem = true;
                    nome = perfis.get(i).getNome();
                    z = i;
                }
            }
            if (contem) {
                if (perfis.get(z).getSenha().contentEquals(sen)) {
                    usuario = perfis.get(z);
                    System.out.println("******************************\n Seja bem vindo(a) " + nome
                            + "! \n******************************");
                    return usuario;
                } else {
                    throw new InvalidPasswordException();
                }
            } else {
                throw new UserNotFoundException();
            }
        }
        return usuario;
    }

    private String menuCliente() {
        Scanner input = new Scanner(System.in);
        System.out.println("Escolha uma das opções: \nC para Colocar produto na Sacola; \nV para Visualizar e Fechar Sacola; " +
                "\nS para Sair.");
        String es = input.next();
        if (es.equalsIgnoreCase("C") || es.equalsIgnoreCase("V") || es.equalsIgnoreCase("S")) {
            return es;
        } else {
            System.out.println(" >> Insira uma opção válida << \n");
            return menuCliente();
        }
    }

    private void areaLogada(Cliente usuario) {
        String es = menuCliente();
        switch (es) {
            case "C":
            case "c":
                mostrarCatalogo();
                menuCompra(usuario);
                break;
            case "V":
            case "v":
                visualizarSacola(usuario);
                menuPagamento(usuario);
                break;
            case "S":
            case "s":
                System.out.println("Deslogando...\n");
                Loja.getInstance().inicio();
        }
    }

    private void menuCompra(Cliente usuario) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nDeseja colocar um produto na sacola? Digite S para Sim e N para Não");
        String escolha = input.nextLine();
        if (escolha.equalsIgnoreCase("s")) {
            escolherItemCatalogo(usuario);
            menuCompra(usuario);
        } else if (escolha.equalsIgnoreCase("n")) {
            System.out.println("** Voltando ao menu... **\n");
            Loja.getInstance().areaLogada(usuario);
        } else {
            System.out.println(" >> Digite uma opção válida <<\n");
            System.out.println("** Voltando ao menu... **\n");
            Loja.getInstance().areaLogada(usuario);
        }
    }

    private void escolherItemCatalogo(Cliente usuario) {
        Scanner input = new Scanner(System.in);
        System.out.println("Qual tipo de produto gostaria de adicionar?");
        String tipo = input.nextLine();
        checarTipo(tipo, usuario);
        System.out.println("Qual a cor do produto que gostaria de adicionar?");
        String cor = input.nextLine();
        checarCor(cor, usuario);
        System.out.println("Qual o tamanho do produto que gostaria de adicionar?");
        String tamanho = input.nextLine();
        checarTamanho(tamanho, usuario);
        pegarItemCatalogo(usuario, cor, tamanho, tipo);
        menuCompra(usuario);
    }

    private void checarTipo(String tipo, Cliente usuario) {
        boolean contem = false;
        for (IProduto produto : Catalogo.getCatalogo().getProdutosEstoque()) {
            if (produto.getTipo().equalsIgnoreCase(tipo)) {
                contem = true;
            }
        }
        if (!contem) {
            System.out.println("Tipo não disponível.");
            escolherItemCatalogo(usuario);
        }
    }

    private void checarCor(String cor, Cliente usuario) {
        boolean contem = false;
        for (IProduto produto : Catalogo.getCatalogo().getProdutosEstoque()) {
            if (produto.getCor().equalsIgnoreCase(cor)) {
                contem = true;
            }
        }
        if (!contem) {
            System.out.println("Cor não disponível.");
            escolherItemCatalogo(usuario);
        }

    }

    private void checarTamanho(String tamanho, Cliente usuario) {
        boolean contem = false;
        for (IProduto produto : Catalogo.getCatalogo().getProdutosEstoque()) {
            if (produto.getTamanho().equalsIgnoreCase(tamanho)) {
                contem = true;
            }
        }
        if (!contem) {
            System.out.println("Tamanho não encontrado.");
            escolherItemCatalogo(usuario);
        }
    }

    private void pegarItemCatalogo(Cliente usuario, String cor, String tamanho, String tipo) {
        boolean contem = false;
        IProduto produtoEscolhido = null;
        for (IProduto produto : Catalogo.getCatalogo().getProdutosEstoque()) {
            if (produto.getCor().equalsIgnoreCase(cor) &&
                    produto.getTamanho().equalsIgnoreCase(tamanho) &&
                    produto.getTipo().equalsIgnoreCase(tipo)) {
                    contem = true;
                    produtoEscolhido = produto;
            }
        } if (contem){
            usuario.getSacolaCliente().adicionar(produtoEscolhido);
            System.out.println("Produto adicionado à sacola!");
        }else{
            System.out.println("Produto não encontrado.");
        }
    }

    private void visualizarSacola(Cliente usuario) {
        if (usuario.getSacolaCliente().produtos.size() == 0){
            System.out.println("\nNão há produtos em sua sacola!\n");
            areaLogada(usuario);
        } else {
            HashMap<IProduto,Integer>sacolaCliente = usuario.getSacolaCliente().sacolaAtualizada();
            System.out.print("Os produtos que se encontram em sua sacola são: \n");
            for (Map.Entry<IProduto, Integer> entry : sacolaCliente.entrySet()) {
                System.out.println(entry.getValue() + " " + entry.getKey().getTipo() + "(s) da cor " + entry.getKey().getCor() +
                        " - Tamanho " + entry.getKey().getTamanho() +
                        " no valor de R$" + (entry.getValue() * entry.getKey().getValor()));
            }
        }
    }

    private void menuPagamento(Cliente usuario) {
        Scanner input = new Scanner(System.in);
        System.out.println("Deseja fechar a sacola e continuar para o pagamento? Digite S para Sim e N para Não");
        String escolha = input.nextLine();
        if (escolha.equalsIgnoreCase("s")) {
            double total = usuario.getSacolaCliente().valorFinal(usuario.getSacolaCliente().sacolaAtualizada());
            telaPagamento(formaPagamento(), total, usuario);
            usuario.getSacolaCliente().getProdutos().clear();
            System.out.println("");
            Loja.getInstance().areaLogada(usuario);
        } else if (escolha.equalsIgnoreCase("n")) {
            Loja.getInstance().areaLogada(usuario);
        } else {
            System.out.println(" >> Digite uma opção válida <<\n");
            System.out.println("** Voltando ao menu... **\n");
            Loja.getInstance().areaLogada(usuario);
        }

    }

    private String formaPagamento() {
        Scanner input = new Scanner(System.in);
        System.out.println("Escolha a forma de pagamento: \n");
        System.out.println("Digite B para Boleto \nDigite C para Cartão (à Vista ou Parcelado - Sem juros) \nDigite P para PayPal");
        String forma = input.nextLine();
        if (forma.equalsIgnoreCase("B") || forma.equalsIgnoreCase("C")
                || forma.equalsIgnoreCase("P")) {
            return forma;
        } else {
            System.out.println(" >> Insira uma opção válida << \n");
            return formaPagamento();
        }
    }

    private void telaPagamento(String forma, double total, Cliente usuario) {
        System.out.println("\nCliente: " + usuario.getNome() + " - CPF: " + usuario.getCPF());
        System.out.println("Sua compra será entregue no endereço: " + usuario.getEndereco());
        switch (forma) {
            case "B":
            case "b":
                new PagamentoBoleto().pagar(total);
                break;
            case "C":
            case "c":
                new PagamentoCartao().pagar(total);
                break;
            case "P":
            case "p":
                new PagamentoPayPal().pagar(total);
                break;
        }

    }

}
