package hva.core;
import java.io.Serializable;
import java.util.*;

public class RegistoVacina implements Serializable{
    private final int _dano;
    private final Veterinario _veterinario;
    private final Animal _animal;
    private final Vacina _vacina;
    private String _termo;

    public RegistoVacina(Veterinario veterinario, Animal animal, Vacina vacina){
        _veterinario=veterinario;
        _animal=animal;
        _vacina=vacina;
        _dano=calcularDano();
        //addRegistoVacina(vacina);
    }

    //private void addRegistoVacina(Vacina vacina){
    //    vacina.addRegistoVacina(this);
    //}

    final int calcularDano(){
        Especie especieQueLevou = _animal.getEspecie();
        ArrayList <Especie> especieQuePodemLevar = _vacina.getEspeciesSupostas();

        for(Especie especieAtual : especieQuePodemLevar){
            if(especieQueLevou.equals(especieAtual)){
                _termo = "NORMAL";
                return 0;
            }
        }

        // caso passe o for loop, é porque o animal que levou a vacina não pertence às espécies que deveriam levar a vacina, isto é, houve um erro
        int dano=0;
        for(Especie especieAtual : especieQuePodemLevar){
            int maxTamanho= maiorNome(especieAtual,especieQueLevou);
            int numCaracteresComum= caracteresComum(especieAtual,especieQueLevou);
            int danoDesteCaso= maxTamanho-numCaracteresComum;
            if(danoDesteCaso>dano){
                dano=danoDesteCaso;
            }
        }
        _termo=_vacina.calculateTermoVacinacao(dano);
        return dano;
    }

    public Animal getAnimal(){
        return _animal;
    }

    public Veterinario getVeterinario(){
        return _veterinario;
    }

    public Vacina getVacina(){
        return _vacina;
    }

    public int getDano(){
        return _dano;
    }

    private int caracteresComum(Especie especie1, Especie especie2){
        String nome1= especie1.getNome();
        String nome2= especie2.getNome();
        int count=0;
        if(nome1.length()<nome2.length()){      //se sim, entao tem de verificar os caracteres do nome1 que existem em nome2
            for(char c: nome1.toCharArray()){
                if(nome2.indexOf(c)!=-1){
                    count++;
                }
            }
        }else{                                  //se não, entao tem de verificar os caracteres do nome2 que existem em nome1, pois tem de comparar os caracteres da menor string com os da maior string
            for(char c: nome2.toCharArray()){
                if(nome1.indexOf(c)!=-1){
                    count++;
                }
            }
        }
        return count;
    }

    private int maiorNome(Especie especie1, Especie especie2){
        int tamanho1= especie1.getNome().length();
        int tamanho2= especie2.getNome().length();

        if(tamanho1>tamanho2){
            return tamanho1;
        }
        return tamanho2;
    }

    String getTermo(){
        return _termo;
    }
}
