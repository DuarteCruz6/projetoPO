package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando ha um animal com o mesmo id que queremos criar
public class DuplicateAnimalKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public DuplicateAnimalKeyException(String id) {
    super(Message.duplicateAnimalKey(id));
  }
}
