package hva.app.search;

import hva.core.Hotel;
import hva.core.RegistoVacina;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowWrongVaccinations(Hotel receiver) throws CommandException {
    super(Label.WRONG_VACCINATIONS, receiver);
    _hotel=receiver;
  }

  @Override
  protected void execute() throws CommandException {
    for(RegistoVacina registo : _hotel.getRegistosVacinas()){
      //percorre todos os registos de vacina do hotel

      if (registo.getDano()>0){
        //a vacina causou dano ao animal, entao é para dar print
        //inicializa a String string e vai adicionando os elementos necessarios
        String string ="";
        string+="REGISTO-VACINA";
        string+="|";
        string+=registo.getVacina().getId();
        string+="|";
        string+=registo.getVeterinario().getId();
        string+="|";
        string+=registo.getAnimal().getEspecie().getId();
        //da print à string
        _display.addLine(string);
      }
    }
    _display.display();
  }
}
