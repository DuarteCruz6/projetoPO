package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  private final Hotel _hotel;

  DoChangeHabitatArea(Hotel receiver) throws CommandException {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    _hotel=receiver;
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("novaArea", Prompt.treeAge());
  }
  
  @Override
  protected void execute() throws CommandException {
      String idHabitat = stringField("idHabitat");
      int novaArea = Integer.parseInt(stringField("novaArea"));
     
    
    try {
      _hotel.alterarAreaHabitat(idHabitat, novaArea);
    } catch (HabitatNaoExiste e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
  }
}
