package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
    _hotel=receiver;
  }
  
  @Override
  protected final void execute() {
    Display display = new Display();
    for (Animal animal: _hotel.getAllAnimals()){
      //percorre todos os animais do hotel 
      
      //inicializa a string com o valor "ANIMAL|"
      String string ="ANIMAL|";

      //vai adicionando o conteudo necessario à string
      string+=animal.getId();
      string+="|";
      string+=animal.getNome();
      string+="|";
      string+=animal.getEspecie().getId();
      string+="|";
      if(animal.getHistoricoSaude().equals("")){
        //o animal não tem historico de saude, então adiciona "VOID"
        string+="VOID";
      }
      else{
        //o animal tem historico de saude, então adiciona-o
        string+=animal.getHistoricoSaude();
      }
      string+="|";
      string+=animal.getHabitat().getId();
      //dá print à String
      display.addLine(string);
    }
    display.display();
  }
}
