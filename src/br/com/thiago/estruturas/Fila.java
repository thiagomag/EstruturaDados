package br.com.thiago.estruturas;

import br.com.thiago.Pessoa;
import br.com.thiago.exceptions.FilaVaziaException;
import br.com.thiago.exceptions.IndiceInexistenteException;
import br.com.thiago.exceptions.PessoaNaoEcontradaException;
import br.com.thiago.exceptions.TipoDeRemocaoException;

public class Fila implements EstruturaDados {
    protected Pessoa[] pessoas = new Pessoa[10];
    protected int qtdElementos = 0;


    @Override
    public void adicionar(Pessoa pessoa) {
        if (this.qtdElementos == this.pessoas.length) {
            Pessoa[] temp = pessoas;
            this.pessoas = new Pessoa[this.qtdElementos << 1];
            System.arraycopy(temp, 0, this.pessoas, 0, this.qtdElementos);
        }
        this.pessoas[this.qtdElementos] = pessoa;
        qtdElementos++;
    }

    @Override
    public void buscar(String nome) {
        int count = 0;
        for (int i = 0; i < qtdElementos; i++) {
            if (nome.equals(this.pessoas[i].getNome())) {
                System.out.println(this.pessoas[i]);
                count++;
            }
        }
        if (count == 0) {
            throw new PessoaNaoEcontradaException();
        }
    }

    public void remover() {
        Pessoa temp = this.pessoas[0];
        if (qtdElementos - 1 >= 0) {
            System.arraycopy(this.pessoas, 1, this.pessoas, 0, qtdElementos - 1);
        } else {
            throw new FilaVaziaException();
        }
        this.pessoas[qtdElementos - 1] = null;
        qtdElementos--;
        System.out.println("Valor retirado da fila: " + temp);
        System.out.println("Nova fila:");
        listarTodos();
    }

    @Override
    public void remover(Pessoa pessoa) {
        throw new TipoDeRemocaoException();
    }

    @Override
    public void remover(int index) {
        throw new TipoDeRemocaoException();
    }

    @Override
    public void listarTodos() { //O visual da fila está conforme uma fila de supermercado ultimo ----> primeiro.
        for (int i = this.pessoas.length - 1; i >= 0; i--) {
            if (this.pessoas[i] != null) {
                if (i != 0) {
                    System.out.print(i+1 + "º da fila: " + this.pessoas[i] + " / ");
                } else {
                    System.out.print(i+1 + "º da fila: " + this.pessoas[i]);
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    @Override
    public Pessoa getPessoa(int index) {
        if (index >= 0 && index < this.qtdElementos) {
            return this.pessoas[index];
        } else {
            throw new IndiceInexistenteException();
        }
    }
}
