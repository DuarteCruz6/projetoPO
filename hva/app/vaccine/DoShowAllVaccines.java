package hva.app.vaccine;

import hva.core.Especie;
import hva.core.Hotel;
import hva.core.Vacina;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
    _hotel=receiver;
  }
  
  @Override
  protected final void execute() {
    Display display = new Display();
    for(Vacina vacina : _hotel.getAllVacinas()){
      //percorre todas as vacinas do hotel

      //inicializa a String string e vai adicionando os elementos necessario
      String string ="";
      string+="VACINA";
      string+="|";
      string+=vacina.getId();
      string+="|";
      string+=vacina.getNome();
      string+="|";
      string+=vacina.getNumAdministracoes();
      string+="|";
      for(Especie especie : vacina.getEspeciesSupostas()){
        //percorre todas as especies que podem levar a vacina
        string+=especie.getId();
        string+=",";
      }
      //da print à string, exceto o ultimo caracter, pois adiciona sempre um a mais (ou virgula ou um |)
      display.addLine(string.substring(0,string.length()-1));    
    }
    display.display();
  }
}
