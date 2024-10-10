package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Arvore;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAllTreesInHabitat(Hotel receiver) throws CommandException {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    _hotel=receiver; 
    addStringField("idHabitat", Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {
      Display display = new Display();
      String idHabitat = stringField("idHabitat");
    Habitat habitat;
    try {
      habitat = _hotel.getHabitat(idHabitat);
    } catch (HabitatNaoExiste e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
    for(Arvore arvore : _hotel.getArvoresHabitat(habitat)){
      String string = "";
        string+="ARVORE";
        string+="|";
        string+=arvore.getId();
        string+="|";
        string+=arvore.getNome();
        string+="|";
        string+=arvore.getIdade();
        string+="|";
        string+=arvore.getDificuldadeBase();
        string+="|";
        string+=arvore.getTipo();
        string+="|";
        string+=arvore.getCicloBiologico(_hotel.getEstacao());
        display.addLine(string);
      }
      display.display();
  }
}
