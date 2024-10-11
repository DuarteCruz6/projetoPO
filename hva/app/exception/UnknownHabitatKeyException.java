package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando nao ha habitat com o id que queremos 
public class UnknownHabitatKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownHabitatKeyException(String id) {
    super(Message.unknownHabitatKey(id));
  }
}
