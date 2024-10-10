package hva.core;

import java.io.Serializable;

public abstract  class Funcionario implements Serializable, Comparable<Funcionario>{
    private final String _id;
    private final String _nome;
    private final String _tipo;

    public Funcionario(String id, String nome, String tipo){
        _id=id;
        _nome=nome;
        _tipo=tipo;
    }

    abstract double getSatisfacao(Estacao estacao);

    public String getTipo(){
        return _tipo;
    }

    public String getNome(){
        return _nome;
    }

    public String getId(){
        return _id;
    }

    @Override
    public int compareTo(Funcionario outroFuncionario){
        return _id.toLowerCase().compareTo(outroFuncionario.getId().toLowerCase());
    }
}
