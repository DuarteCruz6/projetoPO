package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando nao ha funcionario com o id que queremos 
public class UnknownEmployeeKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownEmployeeKeyException(String id) {
    super(Message.unknownEmployeeKey(id));
  }
}
