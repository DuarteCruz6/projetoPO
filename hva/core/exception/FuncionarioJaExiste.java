package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the creation of an employee, when the user submits an ID that already exists.
 */
public class FuncionarioJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdFuncionario id the user gave that already exists
   **/
  public FuncionarioJaExiste(String IdFuncionario) {
    super("IdFuncionario já existe: " + IdFuncionario);
  }
}
