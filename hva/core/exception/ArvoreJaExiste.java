package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class ArvoreJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdArvore name of the import file
   **/
  public ArvoreJaExiste(String IdArvore) {
    super("IdArvore já existe: " + IdArvore);
  }
}