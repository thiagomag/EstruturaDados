package br.com.thiago.estruturas;

import br.com.thiago.Pessoa;
import br.com.thiago.exceptions.IndiceInexistenteException;
import br.com.thiago.exceptions.PessoaNaoEcontradaException;
import br.com.thiago.exceptions.TipoDeRemocaoException;


public class LinkedList implements EstruturaDados{ //nào pode deixar a calsse publica, pois só pode ter uma por arquivo.

    private static class Node {
        private Pessoa data;
        private Node next;
        public Node(Pessoa pessoa) {
            this.data = pessoa;
        }
        public Pessoa getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;

    @Override
    public void adicionar(Pessoa pessoa) {
        Node node = new Node(pessoa);
        if (this.head == null){
            this.head = node;
        } else if (this.head.getNext() == null){
            this.head.setNext(node);
        } else {
            node.setNext(this.head.getNext()); //o novo obj entra apontando para o obj que o head apontava
            this.head.setNext(node); //o head aponta para o novo obj
        }
    }

    public void adicionarInicio(Pessoa pessoa) {
        Node node = new Node(pessoa);
        if (this.head != null) {
            node.setNext(this.head);
        }
        this.head = node;
    }

    public void adicionarFinal(Pessoa pessoa) {
        Node newNode = new Node(pessoa);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node node = this.head;
            while (node.getNext() != null) {
                node = node.getNext();
            }
            node.setNext(newNode);
        }
    }

    @Override
    public void buscar(String nome) {
        Node node = this.head;
        while (node != null) {
            if(node.getData().getNome().equals(nome)){
                System.out.println(node.getData());
            } else {
                throw new PessoaNaoEcontradaException();
            }
            node = node.getNext();
        }
    }

    public void remover() {
        throw new TipoDeRemocaoException();
    }

    @Override
    public void remover(Pessoa pessoa) {
        Node node = this.head;
        Node previous = null;
        while (node != null) {
            if(node.getData().equals(pessoa)){
                if(previous == null) {
                    this.head = this.head.getNext();
                } else {
                    previous.setNext(node.getNext());
                }
                System.out.println("Nova Linked List:");
                listarTodos();
                break;
            }
            previous = node;
            node = node.getNext();
        }
    }

    @Override
    public void remover(int index) {
        Pessoa pessoa = this.getPessoa(index);
        if(pessoa != null) {
            this.remover(pessoa);
        } else {
            throw new IndiceInexistenteException();
        }
    }

    @Override
    public void listarTodos() {
        Node node = this.head;
        System.out.println("==========================================");
        while(node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
        System.out.println("==========================================");
    }

    @Override
    public Pessoa getPessoa(int index) {
        int cont = 0;
        Node node = this.head;
        while(node != null) {
            if (cont++ == index) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public int tamanhoLinkedList() {
        int cont = 0;
        Node node = this.head;
        while(node != null) {
            cont++;
            node = node.getNext();
        }
        return cont;
    }
}
