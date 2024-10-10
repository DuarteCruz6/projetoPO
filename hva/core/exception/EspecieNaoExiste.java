package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class EspecieNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdEspecie name of the import file
   **/
  public EspecieNaoExiste(String IdEspecie) {
    super("IdEspecie não existe: " + IdEspecie);
  }
}
