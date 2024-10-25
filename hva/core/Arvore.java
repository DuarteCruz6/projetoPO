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
    private int _esforcoSazonalAtual;
    private String _cicloBiologicoAtual;
    private EstadoArvore _estado;


    public Arvore(String id, String nome, int idade, int dificuldadeBase, String tipo, Estacao estacaoNasceu){
        //creating a tree
        _id=id;
        _nome=nome;
        _estacaoNasceu=estacaoNasceu.getEstacaoAtual();
        _dificuldadeBase=dificuldadeBase;
        _tipo=tipo;
        _idade=idade;
        atualizarEstado(estacaoNasceu);
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

    public void setEsforcoSazonal(int esforcoSazonalAtual){
        _esforcoSazonalAtual=esforcoSazonalAtual;
    }

    public int getEsforcoSazonal(Estacao estacao){
        return _esforcoSazonalAtual;
    };

    public void setCicloBiologico(String cicloBiologicoAtual){
        _cicloBiologicoAtual = cicloBiologicoAtual;
    };

    public String getCicloBiologico(Estacao estacao){
        return _cicloBiologicoAtual;
    };

    private EstadoArvore getEstadoPorEstacao(Estacao estacao) {
        switch (estacao.getEstacaoAtual()) {
            case 0 -> {
                return new Primavera();
            }
            case 1 -> {
                return new Verao();
            }
            case 2 -> {
                return new Outono();
            }
        }
        return new Inverno();
    }

    public final void atualizarEstado(Estacao estacao) {
        _estado = getEstadoPorEstacao(estacao);
        _estado.atualizarEstado(this); // Chama o método do estado atual
    }

    @Override
    public int compareTo(Arvore outraArvore){
        //returns the correct lexicographic order when listing trees
        return _id.toLowerCase().compareTo(outraArvore.getId().toLowerCase());
    }
    
}
