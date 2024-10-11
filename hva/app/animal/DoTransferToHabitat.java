package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.exception.AnimalNaoExiste;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoTransferToHabitat(Hotel hotel) throws CommandException {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    _hotel = hotel;
    //guarda o valor do input recebido no prompt com a chave idAnimal
    addStringField("idAnimal", Prompt.animalKey());
    //guarda o valor do input recebido no prompt com a chave idHabitat
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    //carrega o valor do input recebido no prompt com a chave idAnimal para a variavel idAnimal
    String idAnimal = stringField("idAnimal");
    //carrega o valor do input recebido no prompt com a chave idHabitat para a variavel idHabitat
    String idHabitat = stringField("idHabitat");
    Habitat habitat;
    try {
      //obtem o habitat com o idHabitat
      habitat = _hotel.getHabitat(idHabitat);
      //muda o animal de habitat
      _hotel.mudarHabitatDoAnimal(idAnimal, habitat);

    } catch (HabitatNaoExiste e) {
      //nao ha habitat com este id
      throw new UnknownHabitatKeyException(idHabitat);

    } catch (AnimalNaoExiste e) {
      //nao ha animal com este id
      throw new UnknownAnimalKeyException(idAnimal);
    }
  }
}
