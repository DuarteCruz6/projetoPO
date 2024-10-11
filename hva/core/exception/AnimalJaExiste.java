package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the creation of an animal, when the user submits an ID that already exists.
 */
public class AnimalJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idAnimal id the user gave that already exists
   **/
  public AnimalJaExiste(String idAnimal) {
    super( "IdAnimal já existe: " + idAnimal);
  }
}

