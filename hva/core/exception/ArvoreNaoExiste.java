package hva.core.exception;

import java.io.Serial;

/**
* Class for representing an problem occuring during obtaining a tree that supposedly exists, but actually doesn't
 */
public class ArvoreNaoExiste extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param IdArvore id the user gave that doesn't exist
   **/
  public ArvoreNaoExiste(String IdArvore) {
    super("IdArvore não existe: " + IdArvore);
  }
}
