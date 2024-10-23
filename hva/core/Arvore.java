package hva.core;

import java.io.Serializable;

//Tree Abstract Class
//The tree can be Caduca or Perene

public abstract class Arvore implements Serializable, Comparable<Arvore>{
    private final String _id;
    private final String _nome;
    private int _idade;
    private final int _estacaoNasceu;
    private final int _dificuldadeBase;
    private final String _tipo;
    private final int[] _esforcoSazonal;
    private final String[] _cicloBiologico;


    public Arvore(String id, String nome, int dificuldadeBase, String tipo, int estacaoNasceu, int[] esforcoSazunal, String[] cicloBiologico){
        //creating a tree
        _id=id;
        _nome=nome;
        _estacaoNasceu=estacaoNasceu;
        _dificuldadeBase=dificuldadeBase;
        _tipo=tipo;
        _esforcoSazonal=esforcoSazunal;
        _cicloBiologico=cicloBiologico;
    }

    double getEsforcoTotal(Estacao estacao){
        //returns the total effort to clean the tree
        //total effor = base difficulty * seasonal effort * log(age+1)
        return getDificuldadeBase()*getEsforcoSazonal(estacao)*Math.log(_idade+1);
    }

    void checkAumentarIdade(Estacao estacao){
        //checks if the tree's age needs to be +1
        if(_estacaoNasceu==estacao.getEstacaoAtual()){
            //it's the tree's aniversary, since the present season is equal to the season the tree was born
            aumentarIdade();
        }
    }

    private void aumentarIdade(){
        //makes the tree 1 year older
        _idade++;
    }

    public String getId(){
        //returns the tree's id
        return _id;
    }

    public String getNome(){
        //returns the tree's name
        return _nome;
    }

    public int getIdade(){
        //returns the tree's age
        return _idade;
    }

    public String getTipo(){
        //returns the tree's type
        return _tipo;
    }

    public int getDificuldadeBase(){
        //returns the tree's base difficulty to clean
        return _dificuldadeBase;
    }

    public int getEsforcoSazonal(Estacao estacao){
        int estacaoAtual=estacao.getEstacaoAtual();
        return _esforcoSazonal[estacaoAtual];
    };

    public String getCicloBiologico(Estacao estacao){
        int estacaoAtual=estacao.getEstacaoAtual();
        return _cicloBiologico[estacaoAtual];
    };

    @Override
    public int compareTo(Arvore outraArvore){
        //returns the correct lexicographic order when listing trees
        return _id.toLowerCase().compareTo(outraArvore.getId().toLowerCase());
    }
}
