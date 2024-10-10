package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class NaoResponsabilidade extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idFuncionario name of the import file
   * @param idResponsabilidade name of the import file
   **/
  public NaoResponsabilidade(String idFuncionario, String idResponsabilidade) {
    super("O Funcionario "+idFuncionario+ " nao tem a responsabilidade " + idResponsabilidade);
  }
}