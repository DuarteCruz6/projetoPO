package hva.core;

import java.io.Serializable;

public abstract class Arvore implements Serializable, Comparable<Arvore>{
    private final String _id;
    private final String _nome;
    private int _idade;
    private final int _estacaoNasceu;
    private final int _dificuldadeBase;
    private final String _tipo;

    public Arvore(String id, String nome, int dificuldadeBase, String tipo, int estacaoNasceu){
        _id=id;
        _nome=nome;
        _estacaoNasceu=estacaoNasceu;
        _dificuldadeBase=dificuldadeBase;
        _tipo=tipo;
    }

    double getEsforcoTotal(Estacao estacao){
        return getDificuldadeBase()*getEsforcoSazonal(estacao)*Math.log(_idade+1);
    }

    void checkAumentarIdade(Estacao estacao){
        if(_estacaoNasceu==estacao.getEstacaoAtual()){
            aumentarIdade();
        }
    }

    private void aumentarIdade(){
        _idade++;
    }

    public String getId(){
        return _id;
    }

    public String getNome(){
        return _nome;
    }

    public int getIdade(){
        return _idade;
    }

    public String getTipo(){
        return _tipo;
    }

    public int getDificuldadeBase(){
        return _dificuldadeBase;
    }

    public abstract int getEsforcoSazonal(Estacao estacao);

    public abstract String getCicloBiologico(Estacao estacao);

    @Override
    public int compareTo(Arvore outraArvore){
        return _id.toLowerCase().compareTo(outraArvore.getId().toLowerCase());
    }
}
