package br.com.thiago.exceptions;

public class TipoDeRemocaoException extends RuntimeException {
    public TipoDeRemocaoException() {
        super("Esse tipo de remoção não está disponível.");
    }
}
