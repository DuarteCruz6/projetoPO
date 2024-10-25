package hva.app.habitat;

import hva.core.Arvore;
import hva.core.Habitat;
import hva.core.Hotel;
import java.util.*;
import pt.tecnico.uilib.menus.Command;


/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
    _hotel=receiver;
  }
  
  @Override
  protected void execute() {
    //guarda todos os habitats do hotel em allHabitats
    SortedSet <Habitat> allHabitats = _hotel.getAllHabitats();
    for(Habitat habitat : allHabitats){
      //percorre todos os habitats do hotel

      //inicializa a String string e vai adicionando os elementos necessarios
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
      //da print à string com as informacoes do habitat
      _display.addLine(string);

      for(Arvore arvore : _hotel.getArvoresHabitat(habitat)){
        //percorre todas as arvores pertencentes ao habitat
        //inicializa a String string e vai adicionando os elementos necessarios
        string="ÁRVORE|";
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
        //da print à string com as informacoes da arvore
        _display.addLine(string);
      }

    }
    _display.display();
  }
}
