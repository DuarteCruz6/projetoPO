package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.exception.AnimalNaoExiste;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoTransferToHabitat(Hotel hotel) throws CommandException {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    _hotel = hotel;
    addStringField("idAnimal", Prompt.animalKey());
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
      String idAnimal = stringField("idAnimal");
      String idHabitat = stringField("idHabitat");
      Habitat habitat;
      try {
        habitat = _hotel.getHabitat(idHabitat);
      } catch (HabitatNaoExiste e) {
        throw new UnknownHabitatKeyException(idHabitat);
      }
      try {
        _hotel.mudarHabitatDoAnimal(idAnimal, habitat);
      } catch (AnimalNaoExiste e) {
        throw new UnknownAnimalKeyException(idAnimal);
      }
  }
}
