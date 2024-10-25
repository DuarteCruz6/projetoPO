package hva.app.animal;

import hva.app.exception.*;
import hva.app.habitat.Prompt;
import hva.core.Hotel;
import hva.core.exception.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {
  private final Hotel _hotel;

  DoRegisterAnimal(Hotel receiver) throws DuplicateAnimalKeyException, CommandException {
    super(Label.REGISTER_ANIMAL, receiver);
    _hotel = receiver;

    //guarda o valor do input recebido no prompt com a chave idAnimal
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
    //guarda o valor do input recebido no prompt com a chave nomeAnimal
    addStringField("nomeAnimal", hva.app.animal.Prompt.animalName());
    //guarda o valor do input recebido no prompt com a chave idEspecie
    addStringField("idEspecie", hva.app.animal.Prompt.speciesKey());
    //guarda o valor do input recebido no prompt com a chave idHabitat
    addStringField("idHabitat", Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException, DuplicateAnimalKeyException {
      //carrega o valor do input recebido no prompt com a chave idAnimal para a variavel idAnimal
      String idAnimal=stringField("idAnimal");

      //carrega o valor do input recebido no prompt com a chave nomeAnimal para a variavel nomeAnimal
      String nomeAnimal=stringField("nomeAnimal");

      //carrega o valor do input recebido no prompt com a chave idEspecie para a variavel idEspecie
      String idEspecie=stringField("idEspecie");

      //carrega o valor do input recebido no prompt com a chave idHabitat para a variavel idHabitat
      String idHabitat=stringField("idHabitat");

      //usado para verificar se a espécie já existe ou não
      boolean especieNaoExiste = true;

      //verifica se a especie existe ou nao, se existir, especieNaoExiste= false
      for(String idEspecieAtual : _hotel.getIdEspecies()){
        if(idEspecieAtual.equals(idEspecie)){
          especieNaoExiste=false;
          break;
        }
      }

      try{
        if(especieNaoExiste){
        //especie ainda nao existe, entao temos de pedir tambem o nome da especie usando o prompt como input                        
        String nomeEspecie = Form.requestString(hva.app.animal.Prompt.speciesName());
        //cria o animal com id idAnimal, nome nomeAnimal, especie de id idEspecie,
        //habitat de id idHabitat, especie de nome nomeEspecie
        _hotel.novoAnimal(idAnimal, nomeAnimal, idEspecie, idHabitat, nomeEspecie);

      }else{    
        //especie ja existe, entao é só criar o animal de id idAnimal, nome nomeAnimal, 
        //especie de id idEspecie, habitat de id idHabitat                                                   
        _hotel.novoAnimal(idAnimal, nomeAnimal, idEspecie, idHabitat);
      }

    } catch (AnimalJaExiste e) {
      //ja ha um animal com este id
      throw new DuplicateAnimalKeyException(idAnimal);

    } catch (EspecieNaoExiste e) {
      //nunca acontece
      throw new UnknownSpeciesKeyException(idEspecie);

    } catch (HabitatNaoExiste e) {
      //nao existe habitat com este id
      throw new UnknownHabitatKeyException(idHabitat);
    } 
  }
}
