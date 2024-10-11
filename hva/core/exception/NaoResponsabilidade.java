package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during obtaining an employee responsability that supposedly exists, but actually doesn't
 */
public class NaoResponsabilidade extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idFuncionario id the user gave that corresponds to an employee
   * @param idResponsabilidade id the user gave that doesn't exist
   **/
  public NaoResponsabilidade(String idFuncionario, String idResponsabilidade) {
    super("O Funcionario "+idFuncionario+ " nao tem a responsabilidade " + idResponsabilidade);
  }
}