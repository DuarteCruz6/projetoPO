package hva.core;

import java.util.*;

public class Veterinario extends Funcionario{
    private final ArrayList <Especie> _especiesPode;
    private final ArrayList <RegistoVacina> _vacinasFeitas;

    public Veterinario(String id, String nome){
        super(id,nome,"VET");
        _especiesPode= new ArrayList<>();
        _vacinasFeitas= new ArrayList<>();
    }

    void removeEspecie(Especie especie){
        _especiesPode.remove(especie);
    }

    void addEspecie(Especie especie){
        _especiesPode.add(especie);
    }

    @Override
    double getSatisfacao(Estacao estacao){
        return calcularSatisfacao();
    }

    private double calcularSatisfacao(){
        double satisfacao=20;
        for(Especie especie : _especiesPode){
            satisfacao-= especie.getNumAnimais()/especie.getnumVeterinariosSabemVacinar();
        }
        return satisfacao;
    }

    public ArrayList <String> getIdEspeciesTratadas(){
        ArrayList <String> idEspeciesTratadas= new ArrayList<>();
        
        for(Especie especieAtual: _especiesPode){
            String idEspecie = especieAtual.getId();
            idEspeciesTratadas.add(idEspecie);
        }
        return idEspeciesTratadas;
    }

    ArrayList <RegistoVacina> getVacinacoes(){
        return _vacinasFeitas;
    }

    void addVacinacao(RegistoVacina registo){
        _vacinasFeitas.add(registo);
    }

    ArrayList <Especie> getEspeciesPode(){
        return _especiesPode;
    }
}
