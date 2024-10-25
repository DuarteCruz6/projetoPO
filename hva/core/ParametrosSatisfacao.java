package hva.core;

import java.io.Serializable;
import java.util.ArrayList;

public class ParametrosSatisfacao implements Serializable{
    private Estacao _estacao;
    private ArrayList<Habitat> _habitats;
    private ArrayList <Especie> _especiesPode;

    public Estacao getEstacao() {
        //specific for treaters
        return _estacao; 
    }

    public ArrayList<Habitat> getHabitats() {
        //specific for treaters
        return _habitats; 
    }

    public ArrayList <Especie> getEspecies() { 
        //specific for vets
        return _especiesPode; 
    }
    
    public void setEspecies(ArrayList <Especie> especiesPode) { 
        //specific for vets
        this._especiesPode = especiesPode;
    }

    public void setHabitats(ArrayList <Habitat> habitats) { 
        //specific for treaters
        this._habitats = habitats;
    }

    public void setEstacao(Estacao estacao) { 
        //specific for treaters
        this._estacao = estacao;
    }
}
