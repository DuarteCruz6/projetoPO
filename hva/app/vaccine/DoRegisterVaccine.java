package hva.app.vaccine;

import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.EspecieNaoExiste;
import hva.core.exception.VacinaJaExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {
  private final Hotel _hotel;

  DoRegisterVaccine(Hotel receiver) throws CommandException {
    super(Label.REGISTER_VACCINE, receiver);
    _hotel=receiver;
    //guarda o valor do input recebido no prompt com a chave idVacina
    addStringField("idVacina", Prompt.vaccineKey());
    //guarda o valor do input recebido no prompt com a chave nomeVacina
    addStringField("nomeVacina", Prompt.vaccineName());
    //guarda o valor do input recebido no prompt com a chave idEspecies
    addStringField("idEspecies", Prompt.listOfSpeciesKeys());
  }

  @Override
  protected final void execute() throws CommandException {
    //carrega o valor do input recebido no prompt com a chave idVacina para a variavel idFuncionario
    String idVacina = stringField("idVacina");
    //carrega o valor do input recebido no prompt com a chave nomeVacina para a variavel nomeVacina
    String nomeVacina = stringField("nomeVacina");
    //carrega o valor do input recebido no prompt com a chave idEspecies, dividindo a string a cada vírgula
    //passando para lista de Strings, para a variavel idEspecies
    String[] idEspecies = stringField("idEspecies").split(",");

    try {
      //cria uma vacina com id idVacina, nome nomeVacina e cujas especies que podem levar de id idEspecies
      _hotel.novaVacina(idVacina, nomeVacina, idEspecies);
    } catch (VacinaJaExiste e) {
      //ja ha vacina com este id
      throw new DuplicateVaccineKeyException(idVacina);

    } catch (EspecieNaoExiste e) {
      //nao ha especie com este id
      throw new UnknownSpeciesKeyException(e.getMessage());
    }
  }
}
