package hva.app.search;

import hva.core.Hotel;
import hva.core.RegistoVacina;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowWrongVaccinations(Hotel receiver) throws CommandException {
    super(Label.WRONG_VACCINATIONS, receiver);
    _hotel=receiver;
    //execute();
  }

  @Override
  protected void execute() throws CommandException {
    Display display = new Display();
    for(RegistoVacina registo : _hotel.getVacinasMas()){
      if (registo.getDano()>0){
        String string ="";
        string+="REGISTO-VACINA";
        string+="|";
        string+=registo.getVacina().getId();
        string+="|";
        string+=registo.getVeterinario().getId();
        string+="|";
        string+=registo.getAnimal().getEspecie().getId();
        display.addLine(string);
      }
    }
    display.display();
  }
}
