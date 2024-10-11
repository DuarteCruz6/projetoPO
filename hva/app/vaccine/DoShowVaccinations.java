package hva.app.vaccine;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.core.Funcionario;
import hva.core.Hotel;
import hva.core.RegistoVacina;
import hva.core.Veterinario;
import hva.core.exception.FuncionarioNaoExiste;
import hva.core.exception.VeterinarioNaoExiste;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;

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
      //percorre todos os funcionarios do hotel

      if(funcionario instanceof Veterinario){
        //se o funcionario for um veterinario
        String idVeterinario = funcionario.getId();
        try {
          for(RegistoVacina registo : _hotel.getVacinasVeterinario(idVeterinario)){
            //percorre todos os registos de vacina do veterinario de id idVeterinario

            //inicializa a String string e vai adicionando os elementos necessarios
            String string="";
            string+="REGISTO-VACINA";
            string+="|";
            string+=registo.getVacina().getId();
            string+="|";
            string+= idVeterinario;
            string+="|";
            string+=registo.getAnimal().getEspecie().getId();
            //da print à string
            display.addLine(string);
          }
        } catch (FuncionarioNaoExiste e) {
          //nao ha funcionario com este id
          throw new UnknownEmployeeKeyException(idVeterinario);

        }catch (VeterinarioNaoExiste e){
          //ha funcionario com este id, mas é tratador e não veterinário
          throw new UnknownVeterinarianKeyException(idVeterinario);
        }
      }
    } 
    display.display();
  }
}
