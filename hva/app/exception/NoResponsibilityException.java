package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando nao ha responsabilidade com o id que queremos 
public class NoResponsibilityException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;

  public NoResponsibilityException(String employeeKey, String responsibilityKey) {
    super(Message.noResponsibility(employeeKey, responsibilityKey));
  }
}
