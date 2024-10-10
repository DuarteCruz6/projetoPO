package hva.app.search;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.core.Hotel;
import hva.core.RegistoVacina;
import hva.core.exception.FuncionarioNaoExiste;
import hva.core.exception.VeterinarioNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowMedicalActsByVeterinarian(Hotel receiver) throws CommandException {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    _hotel=receiver;
    addStringField("idFuncionario", hva.app.employee.Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    Display display = new Display();
    String idVeterinario = stringField("idFuncionario");
    try {
      for(RegistoVacina registo : _hotel.getVacinasVeterinario(idVeterinario)){
        String string ="";
        string+="REGISTO-VACINA";
        string+="|";
        string+=registo.getVacina().getId();
        string+="|";
        string+=idVeterinario;
        string+="|";
        string+=registo.getAnimal().getEspecie().getId();
        display.addLine(string);
      }
    } catch (FuncionarioNaoExiste e) {
      //caso nem sequer haja um funcionário com este id, lança esta exceção
      throw new UnknownEmployeeKeyException(idVeterinario);
    } catch (VeterinarioNaoExiste e) {
      //caso haja um funcionário com este id, mas é um tratador e não um veterinário
      throw new UnknownVeterinarianKeyException(idVeterinario);
    }

  display.display();
  }
}
