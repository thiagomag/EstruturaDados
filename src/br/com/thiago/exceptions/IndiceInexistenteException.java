package br.com.thiago.exceptions;

public class IndiceInexistenteException extends RuntimeException {

    public IndiceInexistenteException() {
        super("Índice não encontrado.");
    }
}
