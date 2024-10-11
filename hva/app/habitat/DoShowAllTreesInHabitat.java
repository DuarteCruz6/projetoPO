package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Arvore;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAllTreesInHabitat(Hotel receiver) throws CommandException {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    _hotel=receiver; 
    //guarda o valor do input recebido no prompt com a chave idHabitat
    addStringField("idHabitat", Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    Display display = new Display();
    //carrega o valor do input recebido no prompt com a chave idHabitat para a variavel idHabitat
    String idHabitat = stringField("idHabitat");
    try {
      //carrega o habitat com id idHabitat 
      Habitat habitat = _hotel.getHabitat(idHabitat);
      for(Arvore arvore : _hotel.getArvoresHabitat(habitat)){
        //percorre todas as arvores do habitat

        //inicializa a String string e vai adicionando os elementos necessarios
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
        //da print à string
        display.addLine(string);
      }
      display.display();
    } catch (HabitatNaoExiste e) {
      //nao ha habitat com este id
      throw new UnknownHabitatKeyException(idHabitat);
    }
  }
}
