package hva.app.habitat;

import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.HabitatJaExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoRegisterHabitat(Hotel receiver) throws CommandException {
    super(Label.REGISTER_HABITAT, receiver);
    _hotel=receiver;
    //guarda o valor do input recebido no prompt com a chave idHabitat
    addStringField("idHabitat", Prompt.habitatKey());
    //guarda o valor do input recebido no prompt com a chave nomeHabitat
    addStringField("nomeHabitat", Prompt.habitatName());
    //guarda o valor do input recebido no prompt com a chave areaHabitat
    addIntegerField("areaHabitat", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    //carrega o valor do input recebido no prompt com a chave idHabitat para a variavel idHabitat
    String idHabitat = stringField("idHabitat");
    //carrega o valor do input recebido no prompt com a chave nomeHabitat para a variavel nomeHabitat
    String nomeHabitat = stringField("nomeHabitat");
    //carrega o valor do input recebido no prompt com a chave areaHabitat para a variavel areaHabitat
    int areaHabitat = integerField("areaHabitat");

    try {
      //cria um habitat com id idHabitat, nome nomeHabitat e area areaHabitat
      _hotel.novoHabitat(idHabitat, nomeHabitat, areaHabitat);

    } catch (HabitatJaExiste e) {
      //ja ha funcionario com este id
      throw new DuplicateHabitatKeyException(idHabitat);
    }
  }
}
