package hva.app.vaccine;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.core.Funcionario;
import hva.core.Hotel;
import hva.core.RegistoVacina;
import hva.core.exception.FuncionarioNaoExiste;
import hva.core.exception.VeterinarioNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
    _hotel=receiver;
  }
  
  @Override
  protected final void execute() throws UnknownVeterinarianKeyException, UnknownEmployeeKeyException {
    Display display = new Display();
    for(Funcionario funcionario : _hotel.getFuncionarios()){
      String idVeterinario = funcionario.getId();
      if(funcionario.getTipo().equals("VET")){
        try {
          for(RegistoVacina registo : _hotel.getVacinasVeterinario(idVeterinario)){
            String string="";
            string+="REGISTO-VACINA";
            string+="|";
            string+=registo.getVacina().getId();
            string+="|";
            string+= idVeterinario;
            string+="|";
            string+=registo.getAnimal().getEspecie().getId();
            display.addLine(string);
          }
        } catch (FuncionarioNaoExiste e) {
          throw new UnknownEmployeeKeyException(idVeterinario);
        }catch (VeterinarioNaoExiste e){
          throw new UnknownVeterinarianKeyException(idVeterinario);
        }
      }
    } 
    display.display();
  }
}
