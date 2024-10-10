package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class FuncionarioJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdFuncionario name of the import file
   **/
  public FuncionarioJaExiste(String IdFuncionario) {
    super("IdFuncionario já existe: " + IdFuncionario);
  }
}
