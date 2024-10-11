package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during obtaining an habitat that supposedly exists, but actually doesn't
 */
public class HabitatNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdHabitat id the user gave that doesn't exist
   **/
  public HabitatNaoExiste(String IdHabitat) {
    super("IdHabitat não existe: " + IdHabitat);
  }
}
