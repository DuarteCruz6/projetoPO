package hva.app.employee;

import hva.app.exception.*;
import hva.core.Hotel;
import hva.core.exception.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {
  private final Hotel _hotel;

  DoAddResponsibility(Hotel receiver) throws CommandException {
    super(Label.ADD_RESPONSABILITY, receiver);
    _hotel = receiver;
    //guarda o valor do input recebido no prompt com a chave idFuncionario e idResponsabilidade
    addStringField("idFuncionario", Prompt.employeeKey());
    addStringField("idResponsabilidade", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    //carrega o valor do input recebido no prompt com a chave idFuncionario para a variavel idFuncionario
    String idFuncionario = stringField("idFuncionario");
    //carrega o valor do input recebido no prompt com a chave idResponsabilidade para a variavel idResponsabilidade
    String idResponsabilidade = stringField("idResponsabilidade");

    try {
      //adiciona a responsabilidade com id idResponsabilidade ao funcionario com id idFuncionario
      _hotel.novaResponsabilidade(idFuncionario, idResponsabilidade);

    } catch (FuncionarioNaoExiste e) {
      //nao ha funcionario com este id
      throw new UnknownEmployeeKeyException(idFuncionario);

    } catch (EspecieNaoExiste e) {
      //caso o funcionário seja um veterinário, lançará esta exceção se a responsabilidade (espécie) não existir
      throw new UnknownSpeciesKeyException(idResponsabilidade);  

    } catch (HabitatNaoExiste e) {
       //caso o funcionário seja um tratador, lançará esta exceção se a responsabilidade (habitat) não existir
      throw new UnknownHabitatKeyException(idResponsabilidade);

    } catch (NaoResponsabilidade e) {
      //nao ha habitat ou animal com este id
      throw new NoResponsibilityException(idFuncionario, idResponsabilidade);
    }
  }
}
