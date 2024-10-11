package hva.app.search;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Animal;
import hva.core.Hotel;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAnimalsInHabitat(Hotel receiver) throws CommandException {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    _hotel = receiver;
    //guarda o valor do input recebido no prompt com a chave idHabitat
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
      Display display = new Display();
      //carrega o valor do input recebido no prompt com a chave idHabitat para a variavel idHabitat
      String idHabitat = stringField("idHabitat");
    
    try {
      for(Animal animal : _hotel.getAnimaisHabitat(idHabitat)){
        //percorre todos os animais do habitat com id idHabitat

        //inicializa a String string e vai adicionando os elementos necessarios
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
        //da print à string
        display.addLine(string);
      }
    } catch (HabitatNaoExiste e) {
      //nao ha habitat com este id
      throw new UnknownHabitatKeyException(idHabitat);
    }
    display.display();
  }
}
