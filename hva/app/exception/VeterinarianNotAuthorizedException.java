package hva.app.exception;

import java.io.Serial;
import pt.tecnico.uilib.menus.CommandException;

//quando o veterinario de id idVet não é autorizado a vacinar a espécie de id idSpecies
public class VeterinarianNotAuthorizedException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public VeterinarianNotAuthorizedException(String idVet, String idSpecies) {
    super(Message.notAuthorized(idVet, idSpecies));
  }
}
