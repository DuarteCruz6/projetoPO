package hva.app.vaccine;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import hva.core.Hotel;
import hva.core.exception.AnimalNaoExiste;
import hva.core.exception.FuncionarioNaoExiste;
import hva.core.exception.VacinaNaoExiste;
import hva.core.exception.VeterinarioNaoAutorizado;
import hva.core.exception.VeterinarioNaoExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  private final Hotel _hotel;

  DoVaccinateAnimal(Hotel receiver) throws CommandException {
    super(Label.VACCINATE_ANIMAL, receiver);
    _hotel=receiver;
    addStringField("idVacina", Prompt.vaccineKey());
    addStringField("idVeterinario", hva.app.employee.Prompt.employeeKey());
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String idVacina = stringField("idVacina");
    String idVeterinario = stringField("idVeterinario");
    String idAnimal = stringField("idAnimal");
    String idEspecie=""; //precisa de estar inicializada, senão dá erro caso haja uma exceção VeterinarioNaoAutorizado
    try {
      idEspecie = _hotel.getAnimal(idAnimal).getEspecie().getId();
      _hotel.vacinarAnimal(idVacina, idVeterinario, idAnimal);
    } catch (AnimalNaoExiste e) {
      throw new UnknownAnimalKeyException(idAnimal);
    } catch (VeterinarioNaoAutorizado e) {
      throw new VeterinarianNotAuthorizedException(idVeterinario, idEspecie);
    } catch (VacinaNaoExiste e) {
      throw new UnknownVaccineKeyException(idVacina);
    } catch (FuncionarioNaoExiste e) {
      //quando nem sequer existe funcionário com este Id
      throw new UnknownEmployeeKeyException(idVeterinario);
    } catch (VeterinarioNaoExiste e) {
      //quando existe funcionário mas não existe veterinário com este Id
      throw new UnknownVeterinarianKeyException(idVeterinario);
    }
  }
}
