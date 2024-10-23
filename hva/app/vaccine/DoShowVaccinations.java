package hva.app.vaccine;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.core.Hotel;
import hva.core.RegistoVacina;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
    _hotel=receiver;
  }
  
  @Override
  protected final void execute() throws UnknownVeterinarianKeyException, UnknownEmployeeKeyException {
    Display display = new Display();
    for (RegistoVacina registo : _hotel.getRegistosVacinas()){
      String string="";
      string+="REGISTO-VACINA";
      string+="|";
      string+=registo.getVacina().getId();
      string+="|";
      string+= registo.getVeterinario().getId();
      string+="|";
      string+=registo.getAnimal().getEspecie().getId();
      //da print à string
      display.addLine(string);
    }
    display.display();
  }
}
