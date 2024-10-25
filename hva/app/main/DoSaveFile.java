package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import java.io.IOException;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  private final HotelManager _hotelManager;
  
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
    _hotelManager=receiver;
  }

  @Override
  protected final void execute() throws CommandException {
    Display display = new Display();
    try {
      //da save no ficheiro atual
      _hotelManager.save();
    } catch (MissingFileAssociationException | IOException e) {
      //nao estamos em nenhum ficheiro
      try {
        //entao perguntamos ao user qual o nome que quer dar ao ficheiro novo
        //no qual vamos guardar as informacoes
        String filename = Form.requestString(Prompt.newSaveAs());
        _hotelManager.saveAs(filename);
      } catch (MissingFileAssociationException| UnavailableFileException  ex) {
        throw new FileOpenFailedException(ex);
      } catch (IOException e1) {
        display.addLine(e1.getMessage());
      }
    } 
    display.display();
  }

}
