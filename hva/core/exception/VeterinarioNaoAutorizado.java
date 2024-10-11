package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the vaccination of an animal, in which the Vet is not capable of vaccinating the species of the animal
 */
public class VeterinarioNaoAutorizado extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param idVeterinario id the user gave that corresponds to the VET
   * @param idEspecie id the user gave that corresponds to the species of the animal the VET is trying to vaccinate but isn't capable to
   **/
  public VeterinarioNaoAutorizado(String idVeterinario, String idEspecie) {
    super("Veterinario nao pode vacinar essa especie: " + idVeterinario +" "+ idEspecie);
  }
}