package hva.app;

import hva.core.exception.*;
import pt.tecnico.uilib.Dialog;
import pt.tecnico.uilib.menus.CommandException;

public class App {

  public static void main(String[] args) throws CommandException, 
  HabitatJaExiste, AnimalJaExiste, VacinaJaExiste, FuncionarioJaExiste, ArvoreJaExiste, 
  NaoResponsabilidade, ArvoreNaoExiste, HabitatNaoExiste, EspecieNaoExiste, FuncionarioNaoExiste {
    try (var ui = Dialog.UI) {
      var manager = new hva.core.HotelManager();
      String datafile = System.getProperty("import");
      if (datafile != null) {
        try {
          manager.importFile(datafile);
        } catch (ImportFileException e) {
        }
      }

      (new hva.app.main.Menu(manager)).open();
    }
  }
}
