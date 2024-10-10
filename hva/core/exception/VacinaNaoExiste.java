package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class VacinaNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdVacina name of the import file
   **/
  public VacinaNaoExiste(String IdVacina) {
    super("IdVacina não existe: " + IdVacina);
  }
}
