package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando ha um erro a abrir o ficheiro desejado
public class FileOpenFailedException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public FileOpenFailedException(Exception e) {
    super(Message.problemOpeningFile(e), e);
  }
}
