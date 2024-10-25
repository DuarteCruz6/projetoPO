package hva.app.employee;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.FuncionarioNaoExiste;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowSatisfactionOfEmployee(Hotel receiver) throws CommandException {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    _hotel = receiver;
    //guarda o valor do input recebido no prompt com a chave idFuncionario
    addStringField("idFuncionario", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
      //carrega o valor do input recebido no prompt com a chave idFuncionario para a variavel idFuncionario
      String idFuncionario = stringField("idFuncionario");
      try {
        //imprime o valor da satisfacao do funcionario com id idFuncionario
        _display.addLine(_hotel.getSatisfacaoFuncionario(idFuncionario));

      } catch (FuncionarioNaoExiste e) {
        //nao ha funcionario com este id
        throw new UnknownEmployeeKeyException(idFuncionario);
      }
      _display.display();
  }
}
