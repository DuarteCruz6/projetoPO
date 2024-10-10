package hva.app.habitat;

import hva.core.Arvore;
import hva.core.Habitat;
import hva.core.Hotel;
import java.util.*;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;


/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
    _hotel=receiver;
    //execute();
  }
  
  @Override
  protected void execute() {
    SortedSet <Habitat> allHabitats = _hotel.getAllHabitats();
    Display display = new Display();
    for(Habitat habitat : allHabitats){
      String string = "";
      string+="HABITAT";
      string+="|";
      string+=habitat.getId();
      string+="|";
      string+=habitat.getNome();
      string+="|";
      string+=habitat.getArea();
      string+="|";
      string+=habitat.getNumArvores();
      for(Arvore arvore : _hotel.getArvoresHabitat(habitat)){
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
      }
      display.addLine(string);
    }
    display.display();
  }
}
