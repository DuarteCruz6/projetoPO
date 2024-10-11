package hva.app.search;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import hva.core.RegistoVacina;
import hva.core.exception.AnimalNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowMedicalActsOnAnimal(Hotel receiver) throws CommandException {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    _hotel=receiver;
    //guarda o valor do input recebido no prompt com a chave idAnimal
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected void execute() throws CommandException {
      Display display = new Display();
      //carrega o valor do input recebido no prompt com a chave idAnimal para a variavel idAnimal
      String idAnimal = stringField("idAnimal");
      try {
        for(RegistoVacina registo : _hotel.getVacinasAnimal(idAnimal)){
          //percorre todos os registo de vacinas do animal com id idAnimal

          //inicializa a String string e vai adicionando os elementos necessarios
          String string = "";
          string+="REGISTO-VACINA";
          string+="|";
          string+=registo.getVacina().getId();
          string+="|";
          string+=registo.getVeterinario().getId();
          string+="|";
          string+=registo.getAnimal().getEspecie().getId();
          //da print à string
          display.addLine(string);
        }
      } catch (AnimalNaoExiste e) {
        //nao ha animal com este id
        throw new UnknownAnimalKeyException(idAnimal);
      }
      display.display();
  }
}
