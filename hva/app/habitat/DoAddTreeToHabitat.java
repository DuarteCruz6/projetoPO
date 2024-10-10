package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.ArvoreNaoExiste;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.forms.Form;
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
    addStringField("nomeArvore", Prompt.treeName());
    addIntegerField("idadeArvore", Prompt.treeAge());
    addIntegerField("dificuldadeArvore", Prompt.treeDifficulty());
    addStringField("tipoArvore", Prompt.treeType());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idHabitat = stringField("idHabitat");
    String idArvore = stringField("idArvore");
    String nomeArvore = stringField("nomeArvore");
    int idadeArvore = integerField("idadeArvore");
    int dificuldadeArvore = integerField("dificuldadeArvore");
    String tipoArvore = stringField("tipoArvore");
    while(!tipoArvore.equals("PER") && !tipoArvore.equals("CAD")){
      tipoArvore = Form.requestString(Prompt.treeType());
      }
    try {
      _hotel.plantarArvoreNaoExistente(idHabitat, idArvore, nomeArvore,idadeArvore, dificuldadeArvore,tipoArvore);
    } catch (ArvoreNaoExiste e) {
      throw new DuplicateTreeKeyException(idArvore);
    } catch (HabitatNaoExiste e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
  }
}
