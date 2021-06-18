package br.com.thiago.estruturas;

import br.com.thiago.Pessoa;

public interface EstruturaDados {
    void adicionar(Pessoa pessoa);
    void buscar(String nome);
    void remover(Pessoa pessoa);
    void remover(int index);
    void listarTodos();
    Pessoa getPessoa(int index);
}
