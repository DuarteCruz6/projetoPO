package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando ha uma vacina com o mesmo id que queremos criar
public class DuplicateVaccineKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public DuplicateVaccineKeyException(String id) {
    super(Message.duplicateVaccineKey(id));
  }
}
