package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class HabitatJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdHabitat name of the import file
   **/
  public HabitatJaExiste(String IdHabitat) {
    super("IdHabitat já existe: " + IdHabitat);
  }
}