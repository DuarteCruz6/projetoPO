package hva.app.vaccine;

import hva.app.exception.*;
import hva.core.Hotel;
import hva.core.exception.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  private final Hotel _hotel;

  DoVaccinateAnimal(Hotel receiver) throws CommandException {
    super(Label.VACCINATE_ANIMAL, receiver);
    _hotel=receiver;
    //guarda o valor do input recebido no prompt com a chave idVacina
    addStringField("idVacina", Prompt.vaccineKey());
    //guarda o valor do input recebido no prompt com a chave idVeterinario
    addStringField("idVeterinario", Prompt.veterinarianKey());
    //guarda o valor do input recebido no prompt com a chave idAnimal
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    //carrega o valor do input recebido no prompt com a chave idVacina para a variavel idVacina
    String idVacina = stringField("idVacina");
    //carrega o valor do input recebido no prompt com a chave idVeterinario para a variavel idVeterinario
    String idVeterinario = stringField("idVeterinario");
    //carrega o valor do input recebido no prompt com a chave idAnimal para a variavel idAnimal
    String idAnimal = stringField("idAnimal");

    String idEspecie="";  //precisa de estar inicializada, senão dá erro caso 
                          //haja uma exceção VeterinarioNaoAutorizado
    try {
      //carrega o id da especie do animal de id idAnimal para a variavel idEspecie
     idEspecie = _hotel.getAnimal(idAnimal).getEspecie().getId();
     //o veterinario de id idVeterinario vacina o animal de id idAnimal com a vacina de id idVacina
      _hotel.vacinarAnimal(idVacina, idVeterinario, idAnimal);

    } catch (AnimalNaoExiste e) {
      //nao ha animal com este id
      throw new UnknownAnimalKeyException(idAnimal);

    } catch (VeterinarioNaoAutorizado e) {
      //o veterinario de id idVeterinario nao pode vacinar a especie de id idEspecie
      throw new VeterinarianNotAuthorizedException(idVeterinario, idEspecie);

    } catch (VacinaNaoExiste e) {
      //nao ha vacina com este id
      throw new UnknownVaccineKeyException(idVacina);

    } catch (FuncionarioNaoExiste e) {
      //nao ha funcionario com este id 
      throw new UnknownEmployeeKeyException(idVeterinario);

    } catch (VeterinarioNaoExiste e) {
      //ha funcionário com este id mas é tratador e não veterinário
      throw new UnknownVeterinarianKeyException(idVeterinario);
    }
  }
}
