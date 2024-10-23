package hva.core;
import java.io.Serializable;
import java.util.*;

//Habitat class

public class Habitat implements Serializable, Comparable<Habitat>{
    private final String _id;
    private final String _nome;
    private int _numArvores;
    private int _area;
    private int _numTratadores;
    private int _numAnimais;
    private final SortedSet <Arvore> _arvoresPresentes;
    private final SortedSet <Animal> _animaisPresentes;

    public Habitat(String id, String nome, int area){
        //creating an habitat
        _id=id;
        _nome=nome;
        _area=area;
        _numArvores=0;
        _numTratadores=0;
        _numAnimais=0;
        _arvoresPresentes=new TreeSet<>();
        _animaisPresentes=new TreeSet<>();
    }

    public int getNumArvores(){
        //returns the habitat's number of trees
        return _numArvores;
    }

    int getNumTratadores(){
        //returns the habitat's number of treaters
        return _numTratadores;
    }

    public int getArea(){
        //returns the habitat's area
        return _area;
    }

    public String getId(){
        //returns the habitat's id
        return _id;
    }

    public String getNome(){
        //returns the habitat's name
        return _nome;
    }

    int getNumAnimais(){
        //returns the habitat's number of animals
        return _numAnimais;
    }

    int getNumAnimais(Especie especie){
        //returns the habitat's number of animals from a specific specie
        int count=0;
        for(Animal animalAtual: _animaisPresentes){
            //loops through the all the animals in the habitat and checks if their specie corresponds to the wanted specie
            if(animalAtual.getEspecie().equals(especie)){
                //the animal is from the wanted species, so we add to the count
                count++;
            }
        }
        return count;
    }

    void addArvore(Arvore arvore){
        //adds a tree to the habitat
        _arvoresPresentes.add(arvore);
        _numArvores++;
    }

    SortedSet <Arvore> getArvores(){
        //returns the habitat's trees
        return _arvoresPresentes;
    }

    void changeArea(int novaArea){
        //changes the area of the habitat
        _area=novaArea;
    }

    double getEspacoMedio(){
        //returns the average space per animal on the habitat
        return _area/_numAnimais;
    }

    SortedSet <Animal> getAnimais(){
        //returns the habitat's animals
        return _animaisPresentes;
    }

    void addTratador(){
        //adds a treator to the habitat's number of treaters
        _numTratadores++;
    }

    void removeTratador(){
        //removes a treator to the habitat's number of treaters
        _numTratadores--;
    }

    void addAnimal(Animal animal){
        //adds an animal to the habitat
        _animaisPresentes.add(animal);
        _numAnimais++;
    }

    void removeAnimal(Animal animal){
        //removes an animal to the habitat
        _animaisPresentes.remove(animal);
        _numAnimais--;
    }

    @Override
    public int compareTo(Habitat outroHabitat){
        //returns the correct lexicographic order when listing habitats
        return _id.toLowerCase().compareTo(outroHabitat.getId().toLowerCase());
    }
}
