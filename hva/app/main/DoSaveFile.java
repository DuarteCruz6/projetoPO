package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  private final HotelManager _HotelManager;
  
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
    _HotelManager=receiver;
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      _HotelManager.save();
    } catch (MissingFileAssociationException | IOException e) {
      try {
        String filename = Form.requestString(Prompt.newSaveAs());
        _HotelManager.saveAs(filename);
      } catch (MissingFileAssociationException| UnavailableFileException | IOException ex) {
      }
    } 
  }

}
