package br.com.thiago.exceptions;

public class PessoaNaoEcontradaException extends RuntimeException {

    public PessoaNaoEcontradaException() {
        super("Pessoa não encontrada.");
    }
}
