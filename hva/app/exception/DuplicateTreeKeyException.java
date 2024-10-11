package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando ha uma arvore com o mesmo id que queremos criar
public class DuplicateTreeKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;

  public DuplicateTreeKeyException(String id) {
    super(Message.duplicateTreeKey(id));
  }
}
