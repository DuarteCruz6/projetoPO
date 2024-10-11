package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during the creation of a vaccine, when the user submits an ID that already exists.
 */
public class VacinaJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdVacina id the user gave that already exists
   **/
  public VacinaJaExiste(String IdVacina) {
    super("IdVacina já existe: " + IdVacina);
  }
}
