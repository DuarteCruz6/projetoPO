package hva.app.employee;

import hva.core.Funcionario;
import hva.core.Hotel;
import hva.core.Tratador;
import hva.core.Veterinario;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {
  private final Hotel _hotel;

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
    _hotel=receiver;
  }
  
  @Override
  protected void execute(){
  Display display = new Display();
    for(Funcionario funcionario : _hotel.getFuncionarios()){
      String string = "";
      string+=funcionario.getTipo();
      string+="|";
      string+=funcionario.getId();
      string+="|";
      string+=funcionario.getNome();
      string+="|";
      if(funcionario instanceof Veterinario veterinario){     //Cast para Veterinario
        for(String id : veterinario.getIdEspeciesTratadas()){
          string+=id;
          string+=",";
        }
      }else{
        Tratador tratador = (Tratador) funcionario;  // Cast para Tratador
        for(String id : tratador.getIdHabitats()){
          string+=id;
          string+=",";
        }
      }
      display.addLine(string.substring(0,string.length()-1));     //adiciona sempre um caracter a mais (ou virgula ou um |)
    }
  display.display();
  }
}
