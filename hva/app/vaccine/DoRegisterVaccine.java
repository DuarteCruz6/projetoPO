package hva.app.vaccine;

import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.EspecieNaoExiste;
import hva.core.exception.VacinaJaExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {
  private final Hotel _hotel;

  DoRegisterVaccine(Hotel receiver) throws CommandException {
    super(Label.REGISTER_VACCINE, receiver);
    _hotel=receiver;
    addStringField("idVacina", Prompt.vaccineKey());
    addStringField("nomeVacina", Prompt.vaccineName());
    addStringField("idEspecies", hva.app.animal.Prompt.speciesKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String idVacina = stringField("idVacina");
    String nome = stringField("nomeVacina");
    String[] idEspecies = stringField("idEspecies").split(",");
    try {
      _hotel.novaVacina(idVacina, nome, idEspecies);
    } catch (VacinaJaExiste e) {
      throw new DuplicateVaccineKeyException(idVacina);
    } catch (EspecieNaoExiste e) {
      throw new UnknownSpeciesKeyException("um dos Id's das espécies");
    }
  }
}
