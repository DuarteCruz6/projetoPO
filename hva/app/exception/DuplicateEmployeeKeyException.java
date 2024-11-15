package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando ha um funcionario com o mesmo id que queremos criar
public class DuplicateEmployeeKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public DuplicateEmployeeKeyException(String id) {
    super(Message.duplicateEmployeeKey(id));
  }
}
