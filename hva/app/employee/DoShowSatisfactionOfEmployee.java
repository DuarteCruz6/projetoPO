package hva.app.employee;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.FuncionarioNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowSatisfactionOfEmployee(Hotel receiver) throws CommandException {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    _hotel = receiver;
    addStringField("idFuncionario", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
      Display display = new Display();
      String idFuncionario = stringField("idFuncionario");
      try {
        display.addLine(_hotel.getSatisfacaoFuncionario(idFuncionario));
      } catch (FuncionarioNaoExiste e) {
        throw new UnknownEmployeeKeyException(idFuncionario);
      }
      display.display();
  }
}
