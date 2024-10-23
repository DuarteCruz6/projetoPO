package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during obtaining a species that supposedly exists, but actually doesn't
 */
public class EspecieNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idEspecie id the user gave that doesn't exist
   **/
  public EspecieNaoExiste(String idEspecie) {
    super(idEspecie);
  }
}
