package hva.core;
import java.io.Serializable;
import java.util.*;

//Specie class

public class Especie implements Serializable, Comparable<Especie> {
    private final String _id;
    private final String _nome;
    private int _numVeterinariosSabemVacinar;
    private int _numAnimais;
    private final ArrayList <Habitat> _habitatsAdequados;
    private final ArrayList <Habitat> _habitatsMaus;

    public Especie(String id, String nome){
        //creating a species
        _id=id;
        _nome=nome;
        _numVeterinariosSabemVacinar=0;
        _numAnimais=0;
        _habitatsAdequados=new ArrayList<>();
        _habitatsMaus=new ArrayList<>();
    }

    int getnumVeterinariosSabemVacinar(){
        //returns the numbers of vets who can vaccinate this specie
        return _numVeterinariosSabemVacinar;
    }

    int getNumAnimais(){
        //return the number of animals from this species
        return _numAnimais;
    }

    public String getId(){
        //returns species's id
        return _id;
    }

    String getNome(){
        //returns species's name
        return _nome;
    }

    ArrayList <Habitat> getHabitatsAdequados(){
        //returns the good habitats for this specie
        return _habitatsAdequados;
    }

    ArrayList <Habitat> getHabitatsMaus(){
        //returns the bad habitats for this specie
        return _habitatsMaus;
    }

    void addAnimal(){
        //adds an animal to the number of animals from this species
        _numAnimais++;
    }

    void addVeterinario(){
        //adds a vet to the number of vets who can vaccinate this specie
        _numVeterinariosSabemVacinar++;
    }

    void removeVeterinario(){
        //removes a vet from the number of vets who can vaccinate this specie
        _numVeterinariosSabemVacinar--;
    }

    void removeHabitatAdequado(Habitat habitat){
        //removes an habitat from the good habitats for this specie
        _habitatsAdequados.remove(habitat);
    }

    void addHabitatAdequado(Habitat habitat){
        //adds an habitat from the good habitats for this specie
        _habitatsAdequados.add(habitat);
    }

    void removeHabitatMau(Habitat habitat){
        //removes an habitat from the bad habitats for this specie
        _habitatsMaus.remove(habitat);
    }

    void addHabitatMau(Habitat habitat){
        //adds an habitat from the bad habitats for this specie
        _habitatsMaus.add(habitat);
    }

    @Override
    public int compareTo(Especie outraEspecie){
        //returns the correct lexicographic order when listing animals
        return _id.toLowerCase().compareTo(outraEspecie.getId().toLowerCase());
    }
}   
