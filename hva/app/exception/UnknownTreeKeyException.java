package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando nao ha arvore com o id que queremos 
public class UnknownTreeKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownTreeKeyException(String id) {
    super(Message.unknownTreeKey(id));
  }
}
