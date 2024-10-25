package hva.core;
import java.util.*;

//Tratador class
//Funcionario class son

public class Tratador extends Funcionario{
    //specific attributes of treaters
    private final ArrayList <Habitat> _habitats;

    public Tratador(String id, String nome){
        //creating the Tratador employee, which is a son of class Funcionario
        super(id,nome,"TRT", new TratadorStrategy());
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
        ParametrosSatisfacao parametros = new ParametrosSatisfacao();
        parametros.setEstacao(estacao);
        parametros.setHabitats(_habitats);
        return super._strategy.execute(parametros);
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
}
