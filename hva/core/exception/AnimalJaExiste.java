package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class AnimalJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idAnimal name of the import file
   **/
  public AnimalJaExiste(String idAnimal) {
    super( "IdAnimal já existe: " + idAnimal);
  }
}

