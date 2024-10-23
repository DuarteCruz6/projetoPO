package hva.app.employee;

import hva.core.Funcionario;
import hva.core.Hotel;
import hva.core.Tratador;
import hva.core.Veterinario;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;

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
      //percorre todos os funcionarios do hotel

      //inicializa a String string e vai adicionando os elementos necessarios
      String string = "";
      string+=funcionario.getTipo();
      string+="|";
      string+=funcionario.getId();
      string+="|";
      string+=funcionario.getNome();
      string+="|";
      if(funcionario.getTipo().equals("VET")){     //Cast para Veterinario caso seja veterinario
        Veterinario veterinario = (Veterinario) funcionario;
        for(String id : veterinario.getIdEspeciesTratadas()){
          //percorre todas as especies que o veterinario trata
          string+=id;
          string+=",";
        }
      }else{
        Tratador tratador = (Tratador) funcionario;  // Cast para Tratador caso seja tratador
        for(String id : tratador.getIdHabitats()){
          //percorre todos os habitats que o tratador trata
          string+=id;
          string+=",";
        }
      }
      //da print à string, exceto o ultimo caracter, pois adiciona sempre um a mais (ou virgula ou um |)
      display.addLine(string.substring(0,string.length()-1));
    }
  display.display();
  }
}
