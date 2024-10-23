package hva.core;
import java.io.Serializable;
import java.util.*;

//RegistoVacina class

public class RegistoVacina implements Serializable{
    private final int _dano;
    private final Veterinario _veterinario;
    private final Animal _animal;
    private final Vacina _vacina;
    private String _termo;

    public RegistoVacina(Veterinario veterinario, Animal animal, Vacina vacina){
        //creating a vaccination
        _veterinario=veterinario;
        _animal=animal;
        _vacina=vacina;
        _dano=calcularDano();
    }

    final int calcularDano(){
        //returns the damage the vaccine caused to the animal

        //gets the animal's specie
        Especie especieQueLevou = _animal.getEspecie();
        //gets the species that can take the vaccine
        SortedSet <Especie> especieQuePodemLevar = _vacina.getEspeciesSupostas();

        for(Especie especieAtual : especieQuePodemLevar){
            //loops through the species that can take the vaccine and checks if the animal's specie corresponds to any of them
            if(especieQueLevou.equals(especieAtual)){
                //it corresponds 
                //so the vaccine did no damage to the animal
                //and the term to add to the animal health history is "NORMAL"
                _termo = "NORMAL";
                return 0;
            }
        }

        //in case the code passes the for loop, it means that the animmal's specie
        //doesn't belong to the group of species that can take the vaccine
        //so we need to calculate the damage suffered by the animal
        //damage = max value ((biggest name of both species)-(letters in common in the name of both species)) 
        //         comparing all species that can take the vaccine and the animal's specie

        //to save the biggest damage
        int dano=0;
        for(Especie especieAtual : especieQuePodemLevar){
            //loops through the all the species that can take the vaccine

            //gets the length of the biggest name, when comparing the specie that can take 
            //the vaccine and the animal's specie
            int maxTamanho= maiorNome(especieAtual,especieQueLevou);

            //gets the number of letters in common, when comparing the specie that can take 
            //the vaccine and the animal's specie
            int numCaracteresComum= caracteresComum(especieAtual,especieQueLevou);

            //damage on this case = max length - number of letters in common
            int danoDesteCaso= maxTamanho-numCaracteresComum;

            //if the damage on this case (using one of the species that can take a vaccine) if bigger
            //than the current biggest value, it saves the damage on this case in the variable dano, to
            //make sure only the biggest damage gets saved
            if(danoDesteCaso>dano){
                //the damage on this case is bigger than the current biggest value, so it saves this cases's 
                //damage on the variable dano
                dano=danoDesteCaso;
            }
        }
        //after getting the biggest damage case, it saves the term to add to the animal's health history on the variable _termo
        _termo=_vacina.calculateTermoVacinacao(dano);

        //returns the damage 
        return dano;
    }

    public Animal getAnimal(){
        //returns the animal from this vaccination
        return _animal;
    }

    public Veterinario getVeterinario(){
        //returns the vet from this vaccination
        return _veterinario;
    }

    public Vacina getVacina(){
        //returns the vaccine used on this vaccination
        return _vacina;
    }

    public int getDano(){
        //returns the damage from this vaccination
        return _dano;
    }

    String getTermo(){
        //returns the term from this vaccination
        return _termo;
    }

    private int caracteresComum(Especie especie1, Especie especie2){
        //calculates the number of common letters in both species name
        //gets the name of species1 and stores its value on nome1
        String nome1= especie1.getNome();
        //gets the name of species2 and stores its value on nome2
        String nome2= especie2.getNome();

        int count=0;

        //checks the biggest name, either name1 or name2
        //we need to check the letters of the smaller name on the biggest name, otherwise there might be a bug
        if(nome1.length()<nome2.length()){      
            //name of species1 is smaller than the name of species2, so we have to 
            //check the letters of name1 that exist in name2
            for(char c: nome1.toCharArray()){
                //loops through the letters of name1 and checks if they exist in name2
                if(nome2.indexOf(c)!=-1){
                    //the letter exists on name2, so we need to increment our count
                    count++;
                }
            }
        }else{    
            //name of species1 is biger than the name of species2, so we have to 
            //check the letters of name2 that exist in name1    
            for(char c: nome2.toCharArray()){
                //loops through the letters of name2 and checks if they exist in name1
                if(nome1.indexOf(c)!=-1){
                    //the letter exists on name1, so we need to increment our count
                    count++;
                }
            }
        }
        //returns the number of letters in common
        return count;
    }

    private int maiorNome(Especie especie1, Especie especie2){
        //calculates the species name with the biggest length
        //gets the length of the name of species1 and stores its value on tamanho1
        int tamanho1= especie1.getNome().length();
        //gets the length of the name of species2 and stores its value on tamanho2
        int tamanho2= especie2.getNome().length();

        //checks if the name1's length is bigger than name2's length
        if(tamanho1>tamanho2){
            //the name1's length is bigger, so we return it
            return tamanho1;
        }
        //if the code comes here, it means that the name2's length is bigger, so we return it
        return tamanho2;
    }
}
