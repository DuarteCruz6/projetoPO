package hva.core;

import java.io.Serializable;

//Estacao class

// Primavera:0
// Verao:1
// Outono:2
// Inverno:3

public class Estacao implements Serializable{
    private EstacaoEnumerator _estacaoAtual;

    public Estacao(){
        //initiates a new year
        _estacaoAtual=EstacaoEnumerator.PRIMAVERA;
    }

    int getEstacaoAtual(){
        //returns the present season
        return _estacaoAtual.getValor();
    }

    void skipEstacao(){
        //skips the present season
        switch (_estacaoAtual) {
            case PRIMAVERA -> _estacaoAtual = EstacaoEnumerator.VERAO;
            case VERAO -> _estacaoAtual = EstacaoEnumerator.OUTONO;
            case OUTONO -> _estacaoAtual = EstacaoEnumerator.INVERNO;
            case INVERNO -> _estacaoAtual = EstacaoEnumerator.PRIMAVERA;
        }
    }
}
