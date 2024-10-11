package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando nao ha veterinario com o id que queremos 
public class UnknownVeterinarianKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;

  public UnknownVeterinarianKeyException(String id) {
    super(Message.unknownVeterinarianKey(id));
  }
}
