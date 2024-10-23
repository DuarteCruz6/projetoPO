package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during obtaining an habitat that supposedly exists, but actually doesn't
 */
public class HabitatNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idHabitat id the user gave that doesn't exist
   **/
  public HabitatNaoExiste(String idHabitat) {
    super("IdHabitat não existe: " + idHabitat);
  }
}
