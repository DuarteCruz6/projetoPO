package hva.core;

import java.io.Serializable;

//Employee abstract class
//The employee can be Veterinario or Tratador

public abstract  class Funcionario implements Serializable, Comparable<Funcionario>{
    private final String _id;
    private final String _nome;
    private final String _tipo;

    public Funcionario(String id, String nome, String tipo){
        //creating an employee
        _id=id;
        _nome=nome;
        _tipo=tipo;
    }

    //for inheritance
    abstract double getSatisfacao(Estacao estacao);

    public String getTipo(){
        //returns the employee's type
        return _tipo;
    }

    public String getNome(){
        //returns the employee's name
        return _nome;
    }

    public String getId(){
        //returns the employee's id
        return _id;
    }

    @Override
    public int compareTo(Funcionario outroFuncionario){
        //returns the correct lexicographic order when listing employees
        return _id.toLowerCase().compareTo(outroFuncionario.getId().toLowerCase());
    }
}
