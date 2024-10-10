package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownTreeKeyException;
import hva.core.Hotel;
import hva.core.exception.ArvoreJaExiste;
import hva.core.exception.ArvoreNaoExiste;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoAddTreeToHabitat(Hotel receiver) throws CommandException {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    _hotel=receiver;
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("idArvore", Prompt.treeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idHabitat = stringField("idHabitat");
    String idArvore = stringField("idArvore");
    try {
      _hotel.plantarArvore(idHabitat, idArvore);
    } catch (ArvoreJaExiste e) {
      throw new DuplicateTreeKeyException(idArvore);
    } catch (ArvoreNaoExiste e) {
      throw new UnknownTreeKeyException(idArvore);
    } catch (HabitatNaoExiste e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
  }
}
