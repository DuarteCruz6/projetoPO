package hva.app.search;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import hva.core.RegistoVacina;
import hva.core.exception.AnimalNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowMedicalActsOnAnimal(Hotel receiver) throws CommandException {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    _hotel=receiver;
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected void execute() throws CommandException {
      Display display = new Display();
      String idAnimal = stringField("idAnimal");
      try {
        for(RegistoVacina registo : _hotel.getVacinasAnimal(idAnimal)){
          String string = "";
          string+="REGISTO-VACINA";
          string+="|";
          string+=registo.getVacina().getId();
          string+="|";
          string+=registo.getVeterinario().getId();
          string+="|";
          string+=registo.getAnimal().getEspecie().getId();
          display.addLine(string);
        }
      } catch (AnimalNaoExiste e) {
        throw new UnknownAnimalKeyException(idAnimal);
      }
      display.display();
  }
}
