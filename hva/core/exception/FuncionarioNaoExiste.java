package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during obtaining an employee that supposedly exists, but actually doesn't
 */
public class FuncionarioNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idFuncionario id the user gave that doesn't exist
   **/
  public FuncionarioNaoExiste(String idFuncionario) {
    super("IdFuncionario não existe: " + idFuncionario);
  }
}