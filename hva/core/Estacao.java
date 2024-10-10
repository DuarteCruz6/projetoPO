package hva.core;

import java.io.Serializable;

public class Estacao implements Serializable{
    private int _estacaoAtual;

    public Estacao(){
        _estacaoAtual=0;
    }

    int getEstacaoAtual(){
        return _estacaoAtual;
    }

    void skipEstacao(){
        _estacaoAtual++;
        if(_estacaoAtual==4){
            _estacaoAtual=0;
        }
    }
}

// Primavera:0
// Verao:1
// Outono:2
// Inverno:3