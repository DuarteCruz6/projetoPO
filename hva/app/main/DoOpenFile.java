package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {

  private final HotelManager _hotelManager;

  DoOpenFile(HotelManager receiver) throws CommandException {
    super(Label.OPEN_FILE, receiver);
    _hotelManager=receiver;
    addStringField("filename", Prompt.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
      String filename = stringField("filename");
      if(_hotelManager.houveAlteracoes()){      
        //se der true é porque houve alterações no hotel, então temos de perguntar ao user se quer guardar antes de sair                           
        Boolean userQuerGuardar = Form.confirm(Prompt.saveBeforeExit());
        if(userQuerGuardar){
          //o user disse que quer guardar
          try {
            _hotelManager.save();
          } catch (FileNotFoundException e) {
          } catch (MissingFileAssociationException | IOException e) {
          }      
        }
      }
    
      try {
          _hotelManager.load(filename);
      } catch (UnavailableFileException ex) {
      }
  }
}
