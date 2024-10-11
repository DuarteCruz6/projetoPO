package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  private final Hotel _hotel;

  DoChangeHabitatArea(Hotel receiver) throws CommandException {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    _hotel=receiver;
    //guarda o valor do input recebido no prompt com a chave idHabitat
    addStringField("idHabitat", Prompt.habitatKey());
    //guarda o valor do input recebido no prompt com a chave novaArea
    addIntegerField("novaArea", Prompt.treeAge());
  }
  
  @Override
  protected void execute() throws CommandException {
    //carrega o valor do input recebido no prompt com a chave idHabitat para a variavel idHabitat
    String idHabitat = stringField("idHabitat");
    //carrega o valor do input recebido no prompt com a chave novaArea para a variavel novaArea
    int novaArea = integerField("novaArea");
    
    try {
      //altera a area do habitat de id idHabitat com o valor novaArea
      _hotel.alterarAreaHabitat(idHabitat, novaArea);

    } catch (HabitatNaoExiste e) {
      //nao ha habitat com este id
      throw new UnknownHabitatKeyException(idHabitat);
    }
  }
}
