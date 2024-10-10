package hva.core;
import java.io.Serializable;
import java.util.*;

public class Animal implements Serializable,Comparable<Animal>{
    private final String _id;
    private final String _nome;
    private String _historicoSaude;
    private int _adequacaoHabitat;
    private final Especie _especie;
    private Habitat _habitat;
    private final ArrayList <RegistoVacina> _vacinacoes;

    public Animal(String id, String nome, Habitat habitat, Especie especie){
        _id=id;
        _nome=nome;
        _habitat= habitat;
        _especie=especie;
        _historicoSaude="";
        _vacinacoes=new ArrayList<>();
        changeAdequacaoHabitat();

    }

    public int getSatisfacao(){
        return calcularSatisfacao();
    }

    private int calcularSatisfacao(){
        int satisfacao=20;

        int numAnimaisMesmaEspecieMesmoHabitat= _habitat.getNumAnimais(_especie);
        satisfacao+= 3*(numAnimaisMesmaEspecieMesmoHabitat-1);                                      //-1 pois não se pode contar com ele mesmo

        satisfacao-= 2*(_habitat.getNumAnimais()-numAnimaisMesmaEspecieMesmoHabitat);

        satisfacao+= _habitat.getEspacoMedio();

        satisfacao+= getAdequacaoHabitat();

        return satisfacao;
    }

    public Especie getEspecie(){
        return _especie;
    }

    public String getId(){
        return _id;
    }

    public String getNome(){
        return _nome;
    }

    void mudarHabitat(Habitat novoHabitat){
        _habitat=novoHabitat;
        changeAdequacaoHabitat();
    }

    final void changeAdequacaoHabitat(){
        for(Habitat habitatAtual : _especie.getHabitatsAdequados()){
            if(habitatAtual.equals(_habitat)){
                _adequacaoHabitat=20;
            }
        }
        for(Habitat habitatAtual : _especie.getHabitatsMaus()){
            if(habitatAtual.equals(_habitat)){
                _adequacaoHabitat=-20;
            }
        }
        _adequacaoHabitat=0;
    }

    public String getHistoricoSaude(){
        return _historicoSaude;
    }

    void addHistoricoSaude(String termo){
        _historicoSaude+=termo;
    }

    void addVacina(RegistoVacina registoVacina){
        _vacinacoes.add(registoVacina);
        addHistoricoSaude(registoVacina.getTermo());
    }

    ArrayList<RegistoVacina> getVacinacoes(){
        return _vacinacoes;
    }

    public int getAdequacaoHabitat(){
        return _adequacaoHabitat;
    }

    public Habitat getHabitat(){
        return _habitat;
    }

    @Override
    public int compareTo(Animal outroAnimal){
        return _id.toLowerCase().compareTo(outroAnimal.getId().toLowerCase());
    }
}
