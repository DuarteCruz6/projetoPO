package hva.core;
import java.util.ArrayList;

public class TratadorStrategy implements Strategy {
    @Override
    public double execute(ParametrosSatisfacao parametros){
        //calculates the treater's satisfaction
        //satisfaction = 300 - (the sum of (work on each habitat they treat)/(number of treaters that treat the habitat) 
        //                      for all habitats the tratador treats)
        
        Estacao estacao = parametros.getEstacao();
        ArrayList<Habitat> habitats = parametros.getHabitats();

        double satisfacao=300;
        for(Habitat habitat: habitats){
            //loops through each habitat the treater has

            //gets the work on the habitat
            int trabalhoHabitat= getTrabalhoHabitat(habitat, estacao);

            //gets the number of treaters of the habitat
            int trabalhadoresHabitat = habitat.getNumTratadores();

            //subtracts (work on the habitat)/(number of treaters that treat the habitat
            satisfacao -= trabalhoHabitat/trabalhadoresHabitat;
        }

        //returns the satisfaction
        return satisfacao;
    }

    private int getTrabalhoHabitat(Habitat habitat, Estacao estacao){
        //returns the work on the habitat
        //work on the habitat = area of the habitat + 3*(number of animals of the habitat) + 
        //                      (the sum of the effort to clean every tree on the habitat)

        //gets the habitat's area
        int area = habitat.getArea();
        //gets the number of animal on the habitat
        int populacao = habitat.getNumAnimais();
        //sum of the effort to clean every tree on the habitat
        int esforcoLimpeza = 0;

        for(Arvore arvore : habitat.getArvores()){
            //loops through each tree on the habitat and gets the total effort to clean them and adds to the sum
            esforcoLimpeza+= arvore.getEsforcoTotal(estacao);
        }

        return area + 3*populacao + esforcoLimpeza;
    }
}
