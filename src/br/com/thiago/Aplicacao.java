package br.com.thiago;

import br.com.thiago.estruturas.EstruturaDados;
import br.com.thiago.estruturas.Fila;
import br.com.thiago.exceptions.FilaVaziaException;
import br.com.thiago.exceptions.IndiceInexistenteException;
import br.com.thiago.estruturas.LinkedList;
import br.com.thiago.exceptions.PessoaNaoEcontradaException;
import br.com.thiago.estruturas.Pilha;
import br.com.thiago.exceptions.TipoDeRemocaoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aplicacao {

    public static void main(String[] args) throws InterruptedException, IOException {
        menuInicial(new Scanner(System.in), new Aplicacao());

    }

    private static void menuInicial(Scanner input, Aplicacao aplicacao) throws InterruptedException, IOException {
        boolean acabou = false;
        while (!acabou) {
            switch (opcaoMenuInicial(input)) {
                case 1:
                    aplicacao.menu(new LinkedList(), input);
                    break;
                case 2:
                    aplicacao.menu(new Fila(), input);
                    break;
                case 3:
                    aplicacao.menu(new Pilha(), input);
                    break;
                case 4:
                    acabou = true;
                    break;
                default:
                    System.out.println("Informe uma opção valida");
                    break;
            }
        }
    }

    private static int opcaoMenuInicial(Scanner input) {
        int opc;
        do {
            System.out.println("Escolha a estrutura de dados desejada\n" +
                    "1 - Linked List\n" +
                    "2 - Fila\n" +
                    "3 - Pilha\n" +
                    "4 - Sair\n" +
                    "Entre com a opção desejada");
            opc = input.nextInt();
        } while (opc < 1 || opc > 4);
        return opc;
    }

    private static int opcaoMenu(Scanner input, EstruturaDados estruturaDados) {
        int opc;
        String nome;
        if (estruturaDados instanceof LinkedList) {
            nome = "Linked List";
        } else if (estruturaDados instanceof Fila) {
            nome = "Fila";
        } else {
            nome = "Pilha";
        }
        do {
            System.out.println("1 - Adicionar na " + nome + "\n" +
                    "2 - Mostrar a " + nome + "\n" +
                    "3 - Mostrar a pessoa da " + nome + " por índice\n" +
                    "4 - Mostrar a pessoa da " + nome + " por nome\n" +
                    "5 - Remover \n" +
                    "6 - Remover pelo nome\n" +
                    "7 - Remover por índice\n" +
                    "8 - Sair\n" +
                    "Entre com a opção desejada");
            opc = input.nextInt();
        } while (opc < 1 || opc > 8);
        return opc;
    }

    private void menu(EstruturaDados estruturaDados, Scanner input) throws InterruptedException, IOException {
        boolean continua = true;
        dadosIniciais(estruturaDados);
        while (continua) {
            switch (opcaoMenu(input, estruturaDados)) {
                case 1: //Adicionar pessoa
                    opcaoAdicionar(estruturaDados, input);
                    break;
                case 2: //Mostrar todos da lista
                    listarTodos(estruturaDados);
                    break;
                case 3: //Mostrar pessoa por índice
                    opcaoMostarPessoa(estruturaDados, input);
                    break;
                case 4: //Mostrar pessoa por nome
                    opcaoMostarNome(estruturaDados, input);
                    break;
                case 5: //Remover
                    opcaoRemover(estruturaDados);
                    break;
                case 6: //Remover por nome
                    removerNome(estruturaDados, input);
                    break;
                case 7: //Remover por índice
                    removerIndice(estruturaDados, input);
                    break;
                case 8: //Sair
                    continua = false;
                    break;
                default:
                    System.out.println("Informe uma opção valida");
                    break;
            }
        }
    }

    private static void dadosIniciais(EstruturaDados estruturaDados) throws IOException {
        Path path = Paths.get("dados.txt");
        try (Stream<String> stream = Files.lines(path)) {
            List<String> lines = stream.collect(Collectors.toUnmodifiableList());
            for (int i = 0; i < lines.size(); i += 2) {
                String nome = lines.get(i);
                int idade = Integer.parseInt(lines.get(i + 1));
                estruturaDados.adicionar(new Pessoa(nome, idade));
            }
        }
    }

    private void opcaoAdicionar(EstruturaDados estruturaDados, Scanner input) {
        System.out.println("Qual o nome da pessoa?");
        input.nextLine();
        String nome = input.nextLine();
        System.out.println("Qual a idade da pessoa?");
        int idade = 0;
        try {
            idade = input.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Informe um valor válido.");
        }
        if (estruturaDados instanceof LinkedList) {
            opcaoAdicionarLinkedList(estruturaDados, input, nome, idade);
        } else {
            estruturaDados.adicionar(new Pessoa(nome, idade));
        }
    }

    private int opcaoMenuAdicionar(Scanner input) {
        int opc;
        do {
            System.out.println("1 - Adicionar ao início\n" +
                    "2 - Adicionar ao fim\n" +
                    "3 - Adicionar após o head\n" +
                    "Entre com a opção desejada");
            opc = input.nextInt();
        } while (opc < 1 || opc > 3);
        return opc;
    }

    private void opcaoAdicionarLinkedList(EstruturaDados estruturaDados, Scanner input, String nome, int idade) {
        int n = opcaoMenuAdicionar(input);
        switch (n) {
            case 1:
                ((LinkedList) estruturaDados).adicionarInicio(new Pessoa(nome, idade));
                break;
            case 2:
                ((LinkedList) estruturaDados).adicionarFinal(new Pessoa(nome, idade));
                break;
            case 3:
                estruturaDados.adicionar(new Pessoa(nome, idade));
                break;
        }
    }

    private void listarTodos(EstruturaDados estruturaDados) throws InterruptedException {
        estruturaDados.listarTodos();
        Thread.sleep(2000);
    }

    private void opcaoMostarPessoa(EstruturaDados estruturaDados, Scanner input) throws InterruptedException {
        try {
            System.out.println("Qual o índice da pessoa que deseja mostrar?");
            final var index = input.nextInt();
            if(estruturaDados.getPessoa(index) != null) {
                System.out.println(estruturaDados.getPessoa(index));
            } else {
                throw new IndiceInexistenteException();
            }
        } catch (IndiceInexistenteException e) {
            System.err.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Informe um valor válido.");
        }
        Thread.sleep(2000);
    }

    private void opcaoMostarNome(EstruturaDados estruturaDados, Scanner input) throws InterruptedException {
        try {
            System.out.println("Qual o nome da pessoa que deseja mostrar?");
            input.nextLine();
            estruturaDados.buscar(input.nextLine());
        } catch (PessoaNaoEcontradaException e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(2000);
    }

    private void opcaoRemover(EstruturaDados estruturaDados) throws InterruptedException {
        try {
            if (estruturaDados instanceof LinkedList) {
                LinkedList linkedList = (LinkedList) estruturaDados;
                linkedList.remover();
            } else if (estruturaDados instanceof Fila) {
                Fila fila = (Fila) estruturaDados;
                fila.remover();
            } else {
                Pilha pilha = (Pilha) estruturaDados;
                pilha.remover();
            }
        } catch (FilaVaziaException | TipoDeRemocaoException e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(2000);
    }

    private void removerNome(EstruturaDados estruturaDados, Scanner input) throws InterruptedException {
        try {
            if (estruturaDados instanceof LinkedList) {
                LinkedList linkedList = (LinkedList) estruturaDados;
                System.out.println("Informe o nome da pessoa a ser removida.");
                input.nextLine();
                Pessoa pessoa = null;
                final var nomePessoa = input.nextLine();
                for (int i = 0; i < linkedList.tamanhoLinkedList(); i++) {
                    if (nomePessoa.equals(linkedList.getPessoa(i).getNome())) {
                        pessoa = linkedList.getPessoa(i);
                        estruturaDados.remover(pessoa);
                        break;
                    }
                }
                if(pessoa == null) {
                    throw new PessoaNaoEcontradaException();
                }
            } else {
                estruturaDados.remover(new Pessoa());
            }
        } catch (TipoDeRemocaoException | NullPointerException | PessoaNaoEcontradaException e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(2000);
    }

    private void removerIndice(EstruturaDados estruturaDados, Scanner input) throws InterruptedException {
        try {
            if (estruturaDados instanceof LinkedList) {
                System.out.println("Qual o índice da pessoa que deseja remover?");
                int index = input.nextInt();
                estruturaDados.remover(index);
            } else {
                estruturaDados.remover(-1);
            }
        } catch (TipoDeRemocaoException | IndiceInexistenteException e) {
            System.err.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Informe um valor válido.");
        }
        Thread.sleep(2000);
    }
}