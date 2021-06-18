package br.com.thiago.exceptions;

public class FilaVaziaException extends RuntimeException {
    public FilaVaziaException() {
        super("Fila vazia, por favor adicionar um valor.");
    }
}
