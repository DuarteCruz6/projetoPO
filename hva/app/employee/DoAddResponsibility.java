package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.EspecieNaoExiste;
import hva.core.exception.FuncionarioNaoExiste;
import hva.core.exception.HabitatNaoExiste;
import hva.core.exception.NaoResponsabilidade;
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
    addStringField("idFuncionario", Prompt.employeeKey());
    addStringField("idResponsabilidade", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
      String idFuncionario = stringField("idFuncionario");
      String idResponsabilidade = stringField("idFuncionario");
    try {
      try {
        _hotel.novaResponsabilidade(idFuncionario, idResponsabilidade);
      } catch (FuncionarioNaoExiste e) {
        throw new UnknownEmployeeKeyException(idFuncionario);
      } catch (EspecieNaoExiste e) {
        //caso o funcionário seja um veterinário, lançará esta exceção se a responsabilidade (espécie) não existir
        throw new UnknownSpeciesKeyException(idResponsabilidade);    
      } catch (HabitatNaoExiste e) {
         //caso o funcionário seja um tratador, lançará esta exceção se a responsabilidade (habitat) não existir
        throw new UnknownHabitatKeyException(idResponsabilidade);      
      }
    } catch (NaoResponsabilidade e) {
      throw new NoResponsibilityException(idFuncionario, idResponsabilidade);
    }
  }
}
