package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando ha um habitat com o mesmo id que queremos criar
public class DuplicateHabitatKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public DuplicateHabitatKeyException(String id) {
    super(Message.duplicateHabitatKey(id));
  }
}
