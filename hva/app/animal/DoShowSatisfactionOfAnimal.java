package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import hva.core.exception.AnimalNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowSatisfactionOfAnimal(Hotel receiver) throws CommandException{
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    _hotel = receiver;
    addStringField("idAnimal", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
      Display display = new Display();
      String idAnimal = stringField("idAnimal");
      try {
        display.addLine(_hotel.getSatisfacaoAnimal(idAnimal));
      } catch (AnimalNaoExiste e) {
        throw new UnknownAnimalKeyException(idAnimal);
      }
      display.display();
  }
}
