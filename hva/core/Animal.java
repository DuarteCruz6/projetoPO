package hva.core;
import java.io.Serializable;
import java.util.*;

//Animal class

public class Animal implements Serializable,Comparable<Animal>{
    private final String _id;
    private final String _nome;
    private String _historicoSaude;
    private int _adequacaoHabitat;
    private final Especie _especie;
    private Habitat _habitat;
    private final ArrayList <RegistoVacina> _vacinacoes;

    public Animal(String id, String nome, Habitat habitat, Especie especie){
        //creating an animal
        _id=id;
        _nome=nome;
        _habitat= habitat;
        _especie=especie;
        _historicoSaude="";
        _vacinacoes=new ArrayList<>();
        changeAdequacaoHabitat();

    }

    public int getSatisfacao(){
        //returns the animal's satisfaction
        return calcularSatisfacao();
    }

    private int calcularSatisfacao(){
        //calculates the aimal's satisfaction
        // satisfaction = 20 + 3*(number of animals from the same habitat from the same species) - 2*(number of animals from the same habitat from different species)
        //                + (total habitat area)/(number of animals of the habitat) + habitat's adequation to the species
        
        int satisfacao=20;
        //gets the number of animals from the same habitat from the same species
        int numAnimaisMesmaEspecieMesmoHabitat= _habitat.getNumAnimais(_especie);

        satisfacao+= 3*(numAnimaisMesmaEspecieMesmoHabitat-1);                 //-1 pois não se pode contar com ele mesmo

        //gets the number of animals from the same habitat from different species
        satisfacao-= 2*(_habitat.getNumAnimais()-numAnimaisMesmaEspecieMesmoHabitat);
        
        //(total habitat area)/(number of animals of the habitat)
        satisfacao+= _habitat.getEspacoMedio();

        //gets the habitat's adequation to the species
        satisfacao+= getAdequacaoHabitat();

        //returns the satisfaction
        return satisfacao;
    }

    public Especie getEspecie(){
        //returns the species of the animal
        return _especie;
    }

    public String getId(){
        //returns animal's id
        return _id;
    }

    public String getNome(){
        //returns animal's name
        return _nome;
    }

    public Habitat getHabitat(){
        //returns the animal's habitat
        return _habitat;
    }

    void mudarHabitat(Habitat novoHabitat){
        //changes the animal's habitat and therefore it's adequation to the habitat
        _habitat=novoHabitat;
        changeAdequacaoHabitat();
    }

    final void changeAdequacaoHabitat(){
        //changes the animal's adequation to the habitas

        for(Habitat habitatAtual : _especie.getHabitatsAdequados()){
            //loops through the good habitats and checks if it corresponds to the habitat
            if(habitatAtual.equals(_habitat)){
                //the habitat is good for the animal, which means the adequation = 20
                _adequacaoHabitat=20;
                return;
            }
        }
        for(Habitat habitatAtual : _especie.getHabitatsMaus()){
            //loops through the bad habitats and checks if it corresponds to the habitat
            if(habitatAtual.equals(_habitat)){
                //the habitat is bad for the animal, which means the adequation = -20
                _adequacaoHabitat=-20;
                return;
            }
        }
        //the habitat isn't good or bad, which means the adequation = 0
        _adequacaoHabitat=0;
    }

    public String getHistoricoSaude(){
        //returns animal's health history
        return _historicoSaude;
    }

    void addHistoricoSaude(String termo){
        //adds a vaccine term to the animal's health history
        _historicoSaude+=termo;
    }

    void addVacina(RegistoVacina registoVacina){
        //adds a vaccine to the animal's vaccines history
        _vacinacoes.add(registoVacina);
        addHistoricoSaude(registoVacina.getTermo());
    }

    ArrayList<RegistoVacina> getVacinacoes(){
        //returns the animal's vaccines history
        return _vacinacoes;
    }

    public int getAdequacaoHabitat(){
        //returns the animal's adequation to the habitat
        return _adequacaoHabitat;
    }

    @Override
    public int compareTo(Animal outroAnimal){
        //returns the correct lexicographic order when listing animals
        return _id.toLowerCase().compareTo(outroAnimal.getId().toLowerCase());
    }
}
