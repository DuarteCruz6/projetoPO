package hva.app.employee;

import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.FuncionarioJaExiste;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {
  private final Hotel _hotel;

  DoRegisterEmployee(Hotel receiver) throws CommandException {
    super(Label.REGISTER_EMPLOYEE, receiver);
    _hotel = receiver;
    //guarda o valor do input recebido no prompt com a chave idFuncionario
    addStringField("idFuncionario", Prompt.employeeKey());
    //guarda o valor do input recebido no prompt com a chave nomeFuncionario
    addStringField("nomeFuncionario", Prompt.employeeName());
    //guarda o valor do input recebido no prompt com a chave tipoFuncionario
    addStringField("tipoFuncionario", Prompt.employeeType());
  }
  
  @Override
  protected void execute() throws CommandException {
    //carrega o valor do input recebido no prompt com a chave idFuncionario para a variavel idFuncionario
    String idFuncionario = stringField("idFuncionario");
    //carrega o valor do input recebido no prompt com a chave nomeFuncionario para a variavel nome
    String nomeFuncionario = stringField("nomeFuncionario");
    //carrega o valor do input recebido no prompt com a chave tipoFuncionario para a variavel tipo
    String tipoFuncionario = stringField("tipoFuncionario");

    while(!tipoFuncionario.equals("TRT") && !tipoFuncionario.equals("VET")){
      //enquanto tipoFuncionario for diferente de "TRT" (tratador) ou "VET" (veterinário), pede 
      //repetidamente para o user corrigir, até ser um valor válido
      tipoFuncionario = Form.requestString(Prompt.employeeType());
    }

    try {
      //cria um funcionario com id idFuncionario, nome nomeFuncionario e tipo tipoFuncionario
       _hotel.novoFuncionario(idFuncionario, nomeFuncionario, tipoFuncionario);
    } catch (FuncionarioJaExiste ex) {
      //ja ha funcionario com este id
      throw new DuplicateEmployeeKeyException(idFuncionario);
    }
  }
}
