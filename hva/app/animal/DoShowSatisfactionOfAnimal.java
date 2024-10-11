package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import hva.core.exception.AnimalNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowSatisfactionOfAnimal(Hotel receiver) throws CommandException{
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    _hotel = receiver;
    //guarda o valor do input recebido no prompt com a chave idAnimal
    addStringField("idAnimal", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
      Display display = new Display();
      //carrega o valor do input recebido no prompt com a chave idAnimal para a variavel idAnimal
      String idAnimal = stringField("idAnimal");
      try {
        //imprime o valor da satisfacao do animal com id idAnimal
        display.addLine(_hotel.getSatisfacaoAnimal(idAnimal));

      } catch (AnimalNaoExiste e) {
        //nao ha animal com este id
        throw new UnknownAnimalKeyException(idAnimal);
      }
      display.display();
  }
}
