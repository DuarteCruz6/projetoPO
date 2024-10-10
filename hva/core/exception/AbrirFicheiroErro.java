package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class AbrirFicheiroErro extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param filename name of the import file
   **/
  public AbrirFicheiroErro(String filename) {
    super("Erro a abrir ficheiro: " + filename);
  }
}
