package hva.core;

import java.util.ArrayList;

public class VetStrategy implements Strategy {
    @Override
    public double execute(ParametrosSatisfacao parametros){
        //calculates the vet's satisfaction
        //satisfaction = 20 - (the sum of (number of animal for each specie they treat)/(number of vets that treat each specie they treat) 
        //                     for all species the vet treats)
        
        ArrayList<Especie> especiesPode = parametros.getEspecies();

        double satisfacao=20;
        for(Especie especie : especiesPode){
            //loops through each specie the vet has

            //substracts ((number of animal the specie has)/(number of vets that treat the specie))
            satisfacao-= especie.getNumAnimais()/especie.getnumVeterinariosSabemVacinar();
        }

        //returns the satisfaction
        return satisfacao;
    }
}
