package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class VacinaJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdVacina name of the import file
   **/
  public VacinaJaExiste(String IdVacina) {
    super("IdVacina já existe: " + IdVacina);
  }
}
