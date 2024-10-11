package hva.app.employee;

import hva.app.exception.*;
import hva.core.Hotel;
import hva.core.exception.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel>{ 
  private final Hotel _hotel;

  DoRemoveResponsibility(Hotel receiver) throws CommandException {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    _hotel=receiver;
    //guarda o valor do input recebido no prompt com a chave idFuncionario
    addStringField("idFuncionario", Prompt.employeeKey());
    //guarda o valor do input recebido no prompt com a chave idResponsabilidade
    addStringField("idResponsabilidade", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException{
    //carrega o valor do input recebido no prompt com a chave idFuncionario para a variavel idFuncionario
    String idFuncionario = stringField("idFuncionario");
    //carrega o valor do input recebido no prompt com a chave idResponsabilidade para a variavel idResponsabilidade
    String idResponsabilidade = stringField("idResponsabilidade");
      try {
        //retira a responsabilidade com id idResponsabilidade ao funcionario com id idFuncionario
        _hotel.tirarResponsabilidade(idFuncionario, idResponsabilidade);

      } catch (FuncionarioNaoExiste e) {
        //nao ha funcionario com este id
        throw new UnknownEmployeeKeyException(idFuncionario);

      } catch (EspecieNaoExiste e) {
        //caso o funcionário seja um veterinário, lançará esta exceção se a 
        //responsabilidade (espécie) com este id não existir
        throw new UnknownSpeciesKeyException(idResponsabilidade); 

      } catch (HabitatNaoExiste e) {
         //caso o funcionário seja um tratador, lançará esta exceção se a 
         //responsabilidade (habitat) com este idnão existir
        throw new UnknownHabitatKeyException(idResponsabilidade);

      }catch (NaoResponsabilidade ex) {
        //nao ha responsabilidade com este id
        throw new NoResponsibilityException(idFuncionario, idResponsabilidade);
      }
  }
}
