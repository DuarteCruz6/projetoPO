package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during obtaining a veterinarian that supposedly exists, but actually doesn't
 */
public class VeterinarioNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idVeterinario id the user gave that doesn't exist
   **/
  public VeterinarioNaoExiste(String idVeterinario) {
    super("IdVeterinario não existe: " + idVeterinario);
  }
}
