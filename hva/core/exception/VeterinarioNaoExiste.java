package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class VeterinarioNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdVeterinario name of the import file
   **/
  public VeterinarioNaoExiste(String IdVeterinario) {
    super("IdVeterinario não existe: " + IdVeterinario);
  }
}
