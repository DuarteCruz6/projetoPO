package hva.core;
import java.io.Serializable;
import java.util.*;

public class Vacina implements Serializable, Comparable <Vacina>{
    private final String _id;
    private final String _nome;
    private int _numAdministracoes;
    private final String[] _termosVacinacao = {"CONFUSÃO","ACIDENTE","ERRO"}; //normal: especies iguais, dano=0            confusao: especies diferentes, dano=0              acidente: dano 1 a 4            erro: dano 5+
    private final ArrayList <Especie> _especieQuePodemLevar;

    public Vacina(String id, String nome){
        _id=id;
        _nome=nome;
        _numAdministracoes=0;
        //_registosVacina= new ArrayList<>();
        _especieQuePodemLevar= new ArrayList<>();
    }

    public String getId(){
        return _id;
    }

    public String getNome(){
        return _nome;
    }

    public int getNumAdministracoes(){
        return _numAdministracoes;
    }

    void addVacinaContagem(){
        _numAdministracoes++;
    }

    //ArrayList <RegistoVacina> getRegistosVacina(){
    //    return _registosVacina;
    //}
//
    //void addRegistoVacina(RegistoVacina registo){
    //    _registosVacina.add(registo);
    //}

    String calculateTermoVacinacao(int dano){
        int indexTermo=0;
        if(dano>=1 && dano<=4){
            indexTermo=1;
        }else if(dano>=5){
            indexTermo=2;
        }
        return _termosVacinacao[indexTermo];
    }

    public ArrayList <Especie> getEspeciesSupostas(){
        return _especieQuePodemLevar;
    }

    void addEspecieSuposta(Especie especie){
        _especieQuePodemLevar.add(especie);
    }

    @Override
    public int compareTo(Vacina outraVacina){
        return _id.toLowerCase().compareTo(outraVacina.getId().toLowerCase());
    }
}
