package hva.app.main;

import hva.app.exception.FileOpenFailedException;
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
      String filenameToOpen = stringField("filename");
      if(_hotelManager.houveAlteracoes()){      
        //se der true é porque houve alterações no hotel, então temos de perguntar ao user se quer guardar antes de sair                           
        Boolean userQuerGuardar = Form.confirm(Prompt.saveBeforeExit());
        if(userQuerGuardar){
          //o user disse que quer guardar
          try {
            //da save no ficheiro atual
            _hotelManager.save();
          } catch (FileNotFoundException | MissingFileAssociationException e) {
            //não estamos em nenhum ficheiro, então criamos um ficheiro e perguntamos ao user qual o nome, sendo que se guarda neste novo ficheiro
            String filename = Form.requestString(Prompt.newSaveAs());
            try {
              //da save no ficheiro criado com o nome que o user deu
              _hotelManager.saveAs(filename);
            } catch (FileNotFoundException | MissingFileAssociationException | UnavailableFileException e1) {
            }catch (IOException e1) {
            }  
          } catch (IOException e) {
          }      
        }
      }
      try {
        //abre o ficheiro que o user quer
          _hotelManager.load(filenameToOpen);
      } catch (UnavailableFileException ex) {
        //nao ha esse ficheiro
        throw new FileOpenFailedException(ex);
      }
  }
}
