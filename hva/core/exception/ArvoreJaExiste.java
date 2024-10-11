package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during the creation of a tree, when the user submits an ID that already exists.
 */
public class ArvoreJaExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdArvore id the user gave that already exists
   **/
  public ArvoreJaExiste(String IdArvore) {
    super("IdArvore já existe: " + IdArvore);
  }
}