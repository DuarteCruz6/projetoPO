package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando nao ha vacina com o id que queremos 
public class UnknownVaccineKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownVaccineKeyException(String id) {
    super(Message.unknownVaccineKey(id));
  }
}
