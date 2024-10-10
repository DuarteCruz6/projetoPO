package hva.app.employee;

import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.FuncionarioJaExiste;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {
  private final Hotel _hotel;

  DoRegisterEmployee(Hotel receiver) throws CommandException {
    super(Label.REGISTER_EMPLOYEE, receiver);
    _hotel = receiver;
    addStringField("idFuncionario", Prompt.employeeKey());
    addStringField("nomeFuncionario", Prompt.employeeName());
    addStringField("tipoFuncionario", Prompt.employeeType());
  }
  
  @Override
  protected void execute() throws CommandException {
      String idFuncionario = stringField("idFuncionario");
      String nome = stringField("nomeFuncionario");
      String tipo = stringField("tipoFuncionario");
      while(!tipo.equals("TRT") && !tipo.equals("VET")){
        tipo = Form.requestString(Prompt.employeeType());
      }
      try {
          _hotel.novoFuncionario(idFuncionario, nome, tipo);
      } catch (FuncionarioJaExiste ex) {
        throw new DuplicateEmployeeKeyException(idFuncionario);
      }
  }
}
