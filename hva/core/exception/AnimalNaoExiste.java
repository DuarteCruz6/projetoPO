package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during obtaining an animal that supposedly exists, but actually doesn't
 */
public class AnimalNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idAnimal id the user gave that doesn't exist
   **/
  public AnimalNaoExiste(String idAnimal) {
    super("IdAnimal não existe: " + idAnimal);
  }
}
