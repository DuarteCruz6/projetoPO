package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class FuncionarioNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idFuncionario name of the import file
   **/
  public FuncionarioNaoExiste(String idFuncionario) {
    super("IdFuncionario não existe: " + idFuncionario);
  }
}