package hva.core;
import java.io.Serializable;
import java.util.*;

public class Especie implements Serializable, Comparable<Especie> {
    private final String _id;
    private final String _nome;
    private int _numVeterinariosSabemVacinar;
    private int _numAnimais;
    private final ArrayList <Habitat> _habitatsAdequados;
    private final ArrayList <Habitat> _habitatsMaus;

    public Especie(String id, String nome){
        _id=id;
        _nome=nome;
        _numVeterinariosSabemVacinar=0;
        _numAnimais=0;
        _habitatsAdequados=new ArrayList<>();
        _habitatsMaus=new ArrayList<>();
    }

    int getnumVeterinariosSabemVacinar(){
        return _numVeterinariosSabemVacinar;
    }

    int getNumAnimais(){
        return _numAnimais;
    }

    public String getId(){
        return _id;
    }

    String getNome(){
        return _nome;
    }

    ArrayList <Habitat> getHabitatsAdequados(){
        return _habitatsAdequados;
    }

    ArrayList <Habitat> getHabitatsMaus(){
        return _habitatsMaus;
    }

    void addAnimal(){
        _numAnimais++;
    }

    void addVeterinario(){
        _numVeterinariosSabemVacinar++;
    }

    void removeVeterinario(){
        _numVeterinariosSabemVacinar--;
    }

    void removeHabitatAdequado(Habitat habitat){
        _habitatsAdequados.remove(habitat);
    }

    void addHabitatAdequado(Habitat habitat){
        _habitatsAdequados.add(habitat);
    }

    void removeHabitatMau(Habitat habitat){
        _habitatsMaus.remove(habitat);
    }

    void addHabitatMau(Habitat habitat){
        _habitatsMaus.add(habitat);
    }

    @Override
    public int compareTo(Especie outraEspecie){
        return _id.toLowerCase().compareTo(outraEspecie.getId().toLowerCase());
    }
}   
