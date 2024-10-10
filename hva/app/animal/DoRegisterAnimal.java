package hva.app.animal;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.habitat.Prompt;
import hva.core.Hotel;
import hva.core.exception.AnimalJaExiste;
import hva.core.exception.EspecieNaoExiste;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {
  private final Hotel _hotel;

  DoRegisterAnimal(Hotel receiver) throws DuplicateAnimalKeyException, CommandException {
    super(Label.REGISTER_ANIMAL, receiver);
    _hotel = receiver;
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
    addStringField("nomeAnimal", hva.app.animal.Prompt.animalName());
    addStringField("idEspecie", hva.app.animal.Prompt.speciesKey());
    addStringField("idHabitat", Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException, DuplicateAnimalKeyException {
      Display display = new Display();

      String idAnimal=stringField("idAnimal");
      String nome=stringField("nomeAnimal");
      String idEspecie=stringField("idEspecie");
      String idHabitat=stringField("idHabitat");

      boolean especieNaoExiste = true;

      for(String idEspecieAtual : _hotel.getIdEspecies()){
        if(idEspecieAtual.equals(idEspecie)){
          especieNaoExiste=false;
          break;
        }
      }

      if(especieNaoExiste){                          //especie ainda nao existe
        String nomeEspecie = Form.requestString(hva.app.animal.Prompt.speciesName());
        try {
          _hotel.novoAnimal(idAnimal, nome, idEspecie, idHabitat, nomeEspecie);
        } catch (AnimalJaExiste e) {
          throw new DuplicateAnimalKeyException(idAnimal);
        } catch (HabitatNaoExiste e) {
          throw new UnknownHabitatKeyException(idHabitat);
        } catch (EspecieNaoExiste e) {
          //nunca irá acontecer
          }
      }else{                                                            
        try {
          _hotel.novoAnimal(idAnimal, nome, idEspecie, idHabitat);
        } catch (AnimalJaExiste e) {
          throw new DuplicateAnimalKeyException(idAnimal);
        } catch (EspecieNaoExiste e) {
          throw new UnknownSpeciesKeyException(idEspecie);
        } catch (HabitatNaoExiste e) {
          throw new UnknownHabitatKeyException(idHabitat);
        }    
      }
      display.display();
  }
}
