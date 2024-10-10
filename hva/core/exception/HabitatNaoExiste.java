package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class HabitatNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdHabitat name of the import file
   **/
  public HabitatNaoExiste(String IdHabitat) {
    super("IdHabitat não existe: " + IdHabitat);
  }
}
