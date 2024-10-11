package hva.core;
import java.io.Serializable;
import java.util.*;

//Vacina class

public class Vacina implements Serializable, Comparable <Vacina>{
    private final String _id;
    private final String _nome;
    private int _numAdministracoes;
    private final String[] _termosVacinacao = {"CONFUSÃO","ACIDENTE","ERRO"}; 
    private final ArrayList <Especie> _especieQuePodemLevar;

    public Vacina(String id, String nome){
        //creating a vaccine
        _id=id;
        _nome=nome;
        _numAdministracoes=0;
        _especieQuePodemLevar= new ArrayList<>();
    }

    public String getId(){
        //returns the vaccine's id
        return _id;
    }

    public String getNome(){
        //returns the vaccine's name
        return _nome;
    }

    public int getNumAdministracoes(){
        //returns the number of times the vaccine was used 
        return _numAdministracoes;
    }

    void addVacinaContagem(){
        //adds a use to the number of times the vaccine was used 
        _numAdministracoes++;
    }

    String calculateTermoVacinacao(int dano){
        //returns the term to add to the animal's health history
        //only used if the specie of the animal is different than the species that can get this vaccine,
        //so there is no time we need to add the term "NORMAL"

        //POSSIBLE TERMS:
            /**
             NORMAL: same species, damage=0            
             CONFUSÃO: different species, damage=0              
             ACIDENTE: damage 1-4            
             ERRO: damage 5+
            */

        //default case: term = CONFUSÃO
        int indexTermo=0;

        if(dano>=1 && dano<=4){
            //ACIDENTE
            indexTermo=1;

        }else if(dano>=5){
            //ERRO
            indexTermo=2;
        }

        return _termosVacinacao[indexTermo];
    }

    public ArrayList <Especie> getEspeciesSupostas(){
        return _especieQuePodemLevar;
    }

    void addEspecieSuposta(Especie especie){
        //returns the species that can get the vaccine
        _especieQuePodemLevar.add(especie);
    }

    @Override
    public int compareTo(Vacina outraVacina){
        //returns the correct lexicographic order when listing vaccines
        return _id.toLowerCase().compareTo(outraVacina.getId().toLowerCase());
    }
}
