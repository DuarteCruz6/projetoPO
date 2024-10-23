package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.*;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  private final HotelManager _hotelManager;

  DoNewFile(HotelManager receiver) throws CommandException {
    super(Label.NEW_FILE, receiver);
    _hotelManager = receiver;
  }

  @Override
  protected final void execute() throws CommandException {
    if(_hotelManager.houveAlteracoes()){
      //se der true é porque houve alterações no hotel, então temos de perguntar ao user 
      //se quer guardar antes de sair                           
      Boolean userQuerGuardar = Form.confirm(Prompt.saveBeforeExit());
      if(userQuerGuardar){
          //o user disse que quer guardar
          try {
            //da save
            _hotelManager.save();
          } catch (FileNotFoundException | MissingFileAssociationException e) {
            //nao estamos em nenhum ficheiro
            try {
              //entao pedimos o nome do novo ficheiro no qual vamos guardar as informacoes
              String filename = Form.requestString(Prompt.newSaveAs());
              _hotelManager.saveAs(filename);
            } catch (MissingFileAssociationException | UnavailableFileException | IOException e1) {
              throw new FileOpenFailedException(e1);
            }
          } catch ( IOException e) {
            throw new FileOpenFailedException(e);
          }      
        }
    }
      _hotelManager.novoHotel();
  }
}
