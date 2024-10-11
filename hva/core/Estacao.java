package hva.core;

import java.io.Serializable;

//Estacao class

// Primavera:0
// Verao:1
// Outono:2
// Inverno:3

public class Estacao implements Serializable{
    private int _estacaoAtual;

    public Estacao(){
        //initiates a new year
        _estacaoAtual=0;
    }

    int getEstacaoAtual(){
        //returns the present season
        return _estacaoAtual;
    }

    void skipEstacao(){
        //skips the present season
        _estacaoAtual++;
        if(_estacaoAtual==4){
            _estacaoAtual=0;
        }
    }
}
