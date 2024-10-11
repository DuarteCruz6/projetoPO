package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the creation of an habitat, when the user submits an ID that already exists.
 */
public class HabitatJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdHabitat id the user gave that already exists
   **/
  public HabitatJaExiste(String IdHabitat) {
    super("IdHabitat já existe: " + IdHabitat);
  }
}