package hva.core;
import java.util.*;

public class Tratador extends Funcionario{
    private final ArrayList <Habitat> _habitats;

    public Tratador(String id, String nome){
        super(id,nome,"TRT");
        _habitats= new ArrayList<>();
    }

    void addHabitat(Habitat habitat){
        _habitats.add(habitat);
    }

    void removeHabitat(Habitat habitat){
        _habitats.remove(habitat);
    }

    @Override
    double getSatisfacao(Estacao estacao){
        return calcularSatisfacao(estacao);
    }

    private double calcularSatisfacao(Estacao estacao){
        double satisfacao=300;
        for(Habitat habitat: _habitats){
            int trabalhoHabitat= getTrabalhoHabitat(habitat, estacao);
            int trabalhadoresHabitat = habitat.getNumTratadores();
            satisfacao -= trabalhoHabitat/trabalhadoresHabitat;
        }
        return satisfacao;
    }

    public ArrayList <String> getIdHabitats(){
        ArrayList <String> idHabitatsTratados= new ArrayList<>();
        
        for(Habitat habitatAtual: _habitats){
            String idHabitat = habitatAtual.getId();
            idHabitatsTratados.add(idHabitat);
        }
        return idHabitatsTratados;
    }

    private int getTrabalhoHabitat(Habitat habitat, Estacao estacao){
        int area = habitat.getArea();
        int populacao = habitat.getNumAnimais();
        int esforcoLimpeza = 0;

        for(Arvore arvore : habitat.getArvores()){
            esforcoLimpeza+= arvore.getEsforcoTotal(estacao);
        }

        return area + 3*populacao + esforcoLimpeza;
    }
}
