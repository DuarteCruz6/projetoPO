package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class VeterinarioNaoAutorizado extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idAnimal name of the import file
   **/
  public VeterinarioNaoAutorizado(String idVeterinario, String idEspecie) {
    super("Veterinario nao pode vacinar essa especie: " + idVeterinario +" "+ idEspecie);
  }
}