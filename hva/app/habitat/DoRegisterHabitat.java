package hva.app.habitat;

import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.HabitatJaExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoRegisterHabitat(Hotel receiver) throws CommandException {
    super(Label.REGISTER_HABITAT, receiver);
    _hotel=receiver;
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("nomeHabitat", Prompt.habitatName());
    addStringField("areaHabitat", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idHabitat = stringField("idHabitat");
    String nome = stringField("nomeHabitat");
    int area = Integer.parseInt(stringField("areaHabitat"));
    try {
      _hotel.novoHabitat(idHabitat, nome, area);
    } catch (HabitatJaExiste e) {
      throw new DuplicateHabitatKeyException(idHabitat);
    }
  }
}
