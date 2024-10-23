package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during the creation of a tree, when the user submits an ID that already exists.
 */
public class ArvoreJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idArvore id the user gave that already exists
   **/
  public ArvoreJaExiste(String idArvore) {
    super("IdArvore já existe: " + idArvore);
  }
}