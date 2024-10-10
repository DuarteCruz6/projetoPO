package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
    _hotel=receiver;
    //execute();
  }
  
  @Override
  protected final void execute() {
    Display display = new Display();
    for (Animal animal: _hotel.getAllAnimals()){
      String string ="ANIMAL|";
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
    display.display();
  }
}
