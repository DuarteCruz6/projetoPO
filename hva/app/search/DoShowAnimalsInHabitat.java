package hva.app.search;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Animal;
import hva.core.Hotel;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAnimalsInHabitat(Hotel receiver) throws CommandException {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    _hotel = receiver;
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
      Display display = new Display();
      String idHabitat = stringField("idHabitat");
    
    try {
      for(Animal animal : _hotel.getAnimaisHabitat(idHabitat)){
        String string = "";
        string+="ANIMAL| ";
        string+=animal.getId();
        string+="|";
        string+=animal.getNome();
        string+="|";
        string+=animal.getEspecie().getId();
        string+="|";
        if(animal.getHistoricoSaude().equals("")){
          string+="VOID";
        }
        else{
          string+=animal.getHistoricoSaude();
        }
        string+="|";
        string+=animal.getHabitat().getId();
        display.addLine(string);
      }
    } catch (HabitatNaoExiste e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
    display.display();
  }
}
