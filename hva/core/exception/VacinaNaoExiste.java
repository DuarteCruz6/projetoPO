package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during obtaining a vaccine that supposedly exists, but actually doesn't
 */
public class VacinaNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idVacina id the user gave that doesn't exist
   **/
  public VacinaNaoExiste(String idVacina) {
    super("IdVacina não existe: " + idVacina);
  }
}
