package hva.core;
import java.util.*;

//Tratador class
//Funcionario class son

public class Tratador extends Funcionario{
    //specific attributes of treaters
    private final ArrayList <Habitat> _habitats;

    public Tratador(String id, String nome){
        //creating the Tratador employee, which is a son of class Funcionario
        super(id,nome,"TRT");
        _habitats= new ArrayList<>();
    }

    void addHabitat(Habitat habitat){
        //adds an habitat to the ones the treater already treats
        _habitats.add(habitat);
    }

    void removeHabitat(Habitat habitat){
        //removes an habitat to the ones the treater already treats
        _habitats.remove(habitat);
    }

    @Override
    double getSatisfacao(Estacao estacao){
        //returns the treater's satisfaction
        return calcularSatisfacao(estacao);
    }

    private double calcularSatisfacao(Estacao estacao){
        //calculates the treater's satisfaction
        //satisfaction = 300 - (the sum of (work on each habitat they treat)/(number of treaters that treat the habitat) 
        //                      for all habitats the tratador treats)
        
        double satisfacao=300;
        for(Habitat habitat: _habitats){
            //loops through each habitat the treater has

            //gets the work on the habitat
            int trabalhoHabitat= getTrabalhoHabitat(habitat, estacao);

            //gets the number of treaters of the habitat
            int trabalhadoresHabitat = habitat.getNumTratadores();

            //subtracts (work on the habitat)/(number of treaters that treat the habitat
            satisfacao -= trabalhoHabitat/trabalhadoresHabitat;
        }

        //returns the satisfaction
        return satisfacao;
    }

    public ArrayList <String> getIdHabitats(){
        //returns the id of all habitat's id that the treater treats in an arrayList of strings
        ArrayList <String> idHabitatsTratados= new ArrayList<>();
        
        for(Habitat habitatAtual: _habitats){
            //loops through each habitat the treater treats
            //gets their id
            String idHabitat = habitatAtual.getId();
            //adds their id to the arrayList
            idHabitatsTratados.add(idHabitat);
        }
        return idHabitatsTratados;
    }

    private int getTrabalhoHabitat(Habitat habitat, Estacao estacao){
        //returns the work on the habitat
        //work on the habitat = area of the habitat + 3*(number of animals of the habitat) + 
        //                      (the sum of the effort to clean every tree on the habitat)

        //gets the habitat's area
        int area = habitat.getArea();
        //gets the number of animal on the habitat
        int populacao = habitat.getNumAnimais();
        //sum of the effort to clean every tree on the habitat
        int esforcoLimpeza = 0;

        for(Arvore arvore : habitat.getArvores()){
            //loops through each tree on the habitat and gets the total effort to clean them and adds to the sum
            esforcoLimpeza+= arvore.getEsforcoTotal(estacao);
        }

        return area + 3*populacao + esforcoLimpeza;
    }
}
