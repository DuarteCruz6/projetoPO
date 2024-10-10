package hva.core;
import java.io.Serializable;
import java.util.*;

public class Habitat implements Serializable, Comparable<Habitat>{
    private final String _id;
    private final String _nome;
    private int _numArvores;
    private int _area;
    private int _numTratadores;
    private int _numAnimais;
    private final ArrayList <Arvore> _arvoresPresentes;
    private final ArrayList <Animal> _animaisPresentes;

    public Habitat(String id, String nome, int area){
        _id=id;
        _nome=nome;
        _area=area;
        _numArvores=0;
        _numTratadores=0;
        _numAnimais=0;
        _arvoresPresentes=new ArrayList<>();
        _animaisPresentes=new ArrayList<>();
    }

    public int getNumArvores(){
        return _numArvores;
    }

    int getNumTratadores(){
        return _numTratadores;
    }

    public int getArea(){
        return _area;
    }

    public String getId(){
        return _id;
    }

    public String getNome(){
        return _nome;
    }

    int getNumAnimais(){
        return _numAnimais;
    }

    int getNumAnimais(Especie especie){
        int count=0;
        for(Animal animalAtual: _animaisPresentes){
            if(animalAtual.getEspecie().equals(especie)){
                count++;
            }
        }
        return count;
    }

    void addArvore(Arvore arvore){
        _arvoresPresentes.add(arvore);
        _numArvores++;
    }

    ArrayList <Arvore> getArvores(){
        return _arvoresPresentes;
    }

    void changeArea(int novaArea){
        _area=novaArea;
    }

    double getEspacoMedio(){
        return _area/_numAnimais;
    }

    ArrayList <Animal> getAnimais(){
        return _animaisPresentes;
    }

    void addTratador(){
        _numTratadores++;
    }

    void removeTratador(){
        _numTratadores--;
    }

    void addAnimal(Animal animal){
        _animaisPresentes.add(animal);
        _numAnimais++;
    }

    void removeAnimal(Animal animal){
        _animaisPresentes.remove(animal);
        _numAnimais--;
    }

    @Override
    public int compareTo(Habitat outroHabitat){
        return _id.toLowerCase().compareTo(outroHabitat.getId().toLowerCase());
    }
}
