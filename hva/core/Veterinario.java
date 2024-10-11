package hva.core;

import java.util.*;

//Veterinario class
//Funcionario class son

public class Veterinario extends Funcionario{
    //specific attributes of vets
    private final ArrayList <Especie> _especiesPode;
    private final ArrayList <RegistoVacina> _vacinasFeitas;

    public Veterinario(String id, String nome){
        //creating the Veterinario employee, which is a son of class Funcionario
        super(id,nome,"VET");
        _especiesPode= new ArrayList<>();
        _vacinasFeitas= new ArrayList<>();
    }

    void removeEspecie(Especie especie){
        //removes a specie from the ones the vet already treats
        _especiesPode.remove(especie);
    }

    void addEspecie(Especie especie){
        //adds a specie to the ones the vet already treats
        _especiesPode.add(especie);
    }

    @Override
    double getSatisfacao(Estacao estacao){
        //returns the vet's satisfaction
        return calcularSatisfacao();
    }

    private double calcularSatisfacao(){
        //calculates the vet's satisfaction
        //satisfaction = 20 - (the sum of (number of animal for each specie they treat)/(number of vets that treat each specie they treat) 
        //                     for all species the vet treats)
        
        double satisfacao=20;
        for(Especie especie : _especiesPode){
            //loops through each specie the vet has

            //substracts ((number of animal the specie has)/(number of vets that treat the specie))
            satisfacao-= especie.getNumAnimais()/especie.getnumVeterinariosSabemVacinar();
        }

        //returns the satisfaction
        return satisfacao;
    }

    public ArrayList <String> getIdEspeciesTratadas(){
        //returns the id of all species's id that the vet treats in an arrayList of strings
        ArrayList <String> idEspeciesTratadas= new ArrayList<>();
        
        for(Especie especieAtual: _especiesPode){
            //loops through each specie the vet treats
            //gets their id
            String idEspecie = especieAtual.getId();
            //adds their id to the arrayList
            idEspeciesTratadas.add(idEspecie);
        }
        return idEspeciesTratadas;
    }

    ArrayList <RegistoVacina> getVacinacoes(){
        //returns the number of vaccinations the vet did
        return _vacinasFeitas;
    }

    void addVacinacao(RegistoVacina registo){
        //adds a vaccination the number of vaccinations the vet did
        _vacinasFeitas.add(registo);
    }

    ArrayList <Especie> getEspeciesPode(){
        //returns all the species the vet is capable of vaccinating
        return _especiesPode;
    }
}
