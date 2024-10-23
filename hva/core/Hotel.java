package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

/**
 * The Hotel class represents the main system that manages various domain entities
 * such as species, animals, habitats, employees, and vaccines. It handles data 
 * storage and tracking changes to the state of the system.
 * Implements Serializable to allow object persistence.
 */
public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /** A list of species IDs. */
  private final ArrayList<String> _idEspecies;
  
  /** A list of species names. */
  private final ArrayList<String> _nomeEspecies;
  
  /** A list of vaccine IDs. */
  private final ArrayList<String> _idVacinas;
  
  /** A list of animal IDs. */
  private final ArrayList<String> _idAnimais;
  
  /** A list of habitat IDs. */
  private final ArrayList<String> _idHabitats;
  
  /** A list of employee IDs. */
  private final ArrayList<String> _idFuncionarios;
  
  /** A list of tree IDs. */
  private final ArrayList<String> _idArvores;
  
  /** Represents the current station (season) of the hotel. */
  private final Estacao _estacao;
  
  /** A sorted set of trees in the hotel. */
  private final SortedSet<Arvore> _arvores;
  
  /** A sorted set of animals in the hotel. */
  private final SortedSet<Animal> _animais;
  
  /** A sorted set of employees in the hotel. */
  private final SortedSet<Funcionario> _funcionarios;
  
  /** A sorted set of species in the hotel. */
  private final SortedSet<Especie> _especies;
  
  /** A sorted set of habitats in the hotel. */
  private final SortedSet<Habitat> _habitats;
  
  /** A sorted set of vaccines in the hotel. */
  private final SortedSet<Vacina> _vacinas;
  
  /** A list of vaccine records. */
  private final ArrayList<RegistoVacina> _registoVacinas;
  
  /** A flag indicating whether there have been changes in the hotel system. */
  private boolean _alteracoes;

  /**
   * Constructor for Hotel, initializing all entity lists and the station.
   */
  public Hotel() {
    _idEspecies = new ArrayList<>();
    _nomeEspecies = new ArrayList<>();
    _idVacinas = new ArrayList<>();
    _idAnimais = new ArrayList<>();
    _idHabitats = new ArrayList<>();
    _idFuncionarios = new ArrayList<>();
    _idArvores = new ArrayList<>();
    _estacao = new Estacao();
    _arvores = new TreeSet<>();
    _animais = new TreeSet<>();
    _funcionarios = new TreeSet<>();
    _especies = new TreeSet<>();
    _habitats = new TreeSet<>();
    _vacinas = new TreeSet<>();
    _registoVacinas = new ArrayList<>();
    _alteracoes = false;
  }

  /**
   * Reads data from a file and creates corresponding domain entities.
   * 
   * @param filename The name of the file to import data from.
   * @throws IOException if there is an error reading the file.
   * @throws UnrecognizedEntryException if an entry in the file is unrecognized.
   * @throws FuncionarioJaExiste if an employee already exists.
   * @throws AnimalJaExiste if an animal already exists.
   * @throws ArvoreJaExiste if a tree already exists.
   * @throws HabitatJaExiste if a habitat already exists.
   * @throws NaoResponsabilidade if there is a responsibility issue.
   * @throws VacinaJaExiste if a vaccine already exists.
   * @throws ArvoreNaoExiste if a tree does not exist.
   * @throws HabitatNaoExiste if a habitat does not exist.
   * @throws EspecieNaoExiste if a species does not exist.
   * @throws FuncionarioNaoExiste if an employee does not exist.
   */
  void importFile(String filename) throws IOException, UnrecognizedEntryException, FuncionarioJaExiste, 
      AnimalJaExiste, ArvoreJaExiste, HabitatJaExiste, NaoResponsabilidade, VacinaJaExiste, 
      ArvoreNaoExiste, HabitatNaoExiste, EspecieNaoExiste, FuncionarioNaoExiste {

    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

  /**
   * Checks if there have been changes in the hotel system.
   * 
   * @return true if changes were made, false otherwise.
   */
  boolean houveAlteracoes() {
    return _alteracoes;
  }

  /**
   * Toggles the change flag indicating modifications to the hotel system.
   */
  void changeAlteracoes() {
    _alteracoes = !_alteracoes;
  }

  /**
   * Gets the list of species IDs.
   * 
   * @return A list of species IDs.
   */
  public ArrayList<String> getIdEspecies() {
    return _idEspecies;
  }

  /**
   * Gets the current station (season) of the hotel.
   * 
   * @return The current station.
   */
  public Estacao getEstacao() {
    return _estacao;
  }

  /**
   * Adds a new species ID to the list and marks the system as altered if necessary.
   * 
   * @param idNovo The new species ID to add.
   */
  private void addIdEspecie(String idNovo) {
    if (!_alteracoes) {
      changeAlteracoes();
    }
    _idEspecies.add(idNovo);
  }

  /**
   * Verifies if a species name is unique, adding it to the list if it is.
   * 
   * @param nomeNovo The new species name to verify.
   * @return true if the species name is unique, false otherwise.
   */
  boolean verificarNomeEspecie(String nomeNovo) {
    for (String nomeAtual : _nomeEspecies) {
      //loops through all species and checks if the name in nomeNovo is the same as the specie's name
      if (nomeAtual.toLowerCase().equals(nomeNovo.toLowerCase())) {
        //the name is the same, so return false
        return false;
      }
    }
    //the name doesnt exist, so we are going to create a new species with that name
    addNomeEspecie(nomeNovo);
    return true;
  }
  /**
   * Adds a new species name to the list and marks the system as altered if necessary.
   * 
   * @param nomeNovo The new species name to add.
   */
  private void addNomeEspecie(String nomeNovo) {
    if (!_alteracoes) {
        changeAlteracoes();
    }
    _nomeEspecies.add(nomeNovo);
  }

  /**
  * Verifies if an animal ID is unique, adding it to the list if it is.
  * 
  * @param idNovo The new animal ID to verify.
  * @return true if the animal ID is unique, false otherwise.
  */
  boolean verificarIdAnimal(String idNovo) {
    for (String idAtual : _idAnimais) {
      //loops through all animals and checks if the name in idNovo is the same as the animal's id
        if (idAtual.toLowerCase().equals(idNovo.toLowerCase())) {
            return false;
        }
    }
    addIdAnimal(idNovo);
    return true;
  }

  /**
  * Adds a new animal ID to the list and marks the system as altered if necessary.
  * 
  * @param idNovo The new animal ID to add.
  */
  private void addIdAnimal(String idNovo) {
    if (!_alteracoes) {
        changeAlteracoes();
    }
    _idAnimais.add(idNovo);
  }

  /**
  * Verifies if a vaccine ID is unique, adding it to the list if it is.
  * 
  * @param idNovo The new vaccine ID to verify.
  * @return true if the vaccine ID is unique, false otherwise.
  */
  boolean verificarIdVacina(String idNovo) {
    for (String idAtual : _idVacinas) {
      //loops through all vaccines and checks if the name in idNovo is the same as the vaccine's id
        if (idAtual.toLowerCase().equals(idNovo.toLowerCase())) {
            return false;
        }
    }
    addIdVacina(idNovo);
    return true;
  }

  /**
  * Adds a new vaccine ID to the list and marks the system as altered if necessary.
  * 
  * @param idNovo The new vaccine ID to add.
  */
  private void addIdVacina(String idNovo) {
    if (!_alteracoes) {
        changeAlteracoes();
    }
    _idVacinas.add(idNovo);
  }

  /**
  * Verifies if a habitat ID is unique, adding it to the list if it is.
  * 
  * @param idNovo The new habitat ID to verify.
  * @return true if the habitat ID is unique, false otherwise.
  */
  boolean verificarIdHabitat(String idNovo) {
    for (String idAtual : _idHabitats) {
      //loops through all habitats and checks if the name in idNovo is the same as the habitat's id
        if (idAtual.toLowerCase().equals(idNovo.toLowerCase())) {
            return false;
        }
    }
    addIdHabitat(idNovo);
    return true;
  }

  /**
  * Adds a new habitat ID to the list and marks the system as altered if necessary.
  * 
  * @param idNovo The new habitat ID to add.
  */
  private void addIdHabitat(String idNovo) {
    if (!_alteracoes) {
        changeAlteracoes();
    }
    _idHabitats.add(idNovo);
  }

  /**
  * Verifies if an employee ID is unique, adding it to the list if it is.
  * 
  * @param idNovo The new employee ID to verify.
  * @return true if the employee ID is unique, false otherwise.
  */
  boolean verificarIdFuncionario(String idNovo) {
    for (String idAtual : _idFuncionarios) {
      //loops through all employees and checks if the name in idNovo is the same as the employees's id
        if (idAtual.toLowerCase().equals(idNovo.toLowerCase())) {
            return false;
        }
    }
    addIdFuncionario(idNovo);
    return true;
  }

  /**
  * Adds a new employee ID to the list and marks the system as altered if necessary.
  * 
  * @param idNovo The new employee ID to add.
  */
  private void addIdFuncionario(String idNovo) {
    if (!_alteracoes) {
        changeAlteracoes();
    }
    _idFuncionarios.add(idNovo);
  }

  /**
  * Verifies if a tree ID is unique, adding it to the list if it is.
  * 
  * @param idNovo The new tree ID to verify.
  * @return true if the tree ID is unique, false otherwise.
  */
  boolean verificarIdArvore(String idNovo) {
    for (String idAtual : _idArvores) {
        if (idAtual.toLowerCase().equals(idNovo.toLowerCase())) {
            return false;
        }
    }
    addIdArvore(idNovo);
    return true;
  }

  /**
  * Adds a new tree ID to the list and marks the system as altered if necessary.
  * 
  * @param idNovo The new tree ID to add.
  */
  private void addIdArvore(String idNovo) {
    if (!_alteracoes) {
        changeAlteracoes();
    }
    _idArvores.add(idNovo);
  }

  /**
  * Advances the station (season) and updates the age of all trees based on the new station.
  * Marks the system as altered if necessary.
  */
  public int avancarEstacao() {
    if (!_alteracoes) {
        changeAlteracoes();
    }
    _estacao.skipEstacao();
    for (Arvore arvore : _arvores) {
        arvore.checkAumentarIdade(_estacao);
    }
    return _estacao.getEstacaoAtual();
  }

  /**
  * Calculates and returns the total satisfaction of all animals and employees.
  * 
  * @return The total satisfaction level.
  */
  public double getSatisfacaoTotal() {
    double satisfacao = 0;
    for (Animal animal : _animais) {
        satisfacao += animal.getSatisfacao();
    }
    for (Funcionario funcionario : _funcionarios) {
        satisfacao += funcionario.getSatisfacao(_estacao);
    }
    return satisfacao;
  }

  /**
  * Returns the sorted set of all animals in the hotel.
  * 
  * @return A sorted set of animals.
  */
  public SortedSet<Animal> getAllAnimals() {
    return _animais;
  }

  /**
  * Retrieves the habitat corresponding to the given ID.
  * 
  * @param idHabitat The ID of the habitat to retrieve.
  * @return The habitat with the given ID.
  * @throws HabitatNaoExiste If the habitat with the given ID does not exist.
  */
  public Habitat getHabitat(String idHabitat) throws HabitatNaoExiste {
    for (Habitat habitat : _habitats) {
        if (habitat.getId().toLowerCase().equals(idHabitat.toLowerCase())) {
            return habitat;
        }
    }
    throw new HabitatNaoExiste(idHabitat);
  }

  /**
   * Retrieves the species corresponding to the given ID.
   * 
   * @param idEspecie The ID of the species to retrieve.
   * @return The species with the given ID.
   * @throws EspecieNaoExiste If the species with the given ID does not exist.
   */
  private Especie getEspecie(String idEspecie) throws EspecieNaoExiste {
    for (Especie especie : _especies) {
        if (especie.getId().toLowerCase().equals(idEspecie.toLowerCase())) {
            return especie;
        }
    }
    throw new EspecieNaoExiste(idEspecie);
  }

  /**
  * Creates a new animal associated with an existing species and habitat.
  * 
  * @param idAnimal   The ID of the new animal.
  * @param nome       The name of the new animal.
  * @param idEspecie  The ID of the species the animal belongs to.
  * @param idHabitat  The ID of the habitat where the animal resides.
  * @throws AnimalJaExiste  If the animal already exists.
  * @throws EspecieNaoExiste If the species does not exist.
  * @throws HabitatNaoExiste If the habitat does not exist.
  */
  public void novoAnimal(String idAnimal, String nome, String idEspecie, String idHabitat) 
        throws AnimalJaExiste, EspecieNaoExiste, HabitatNaoExiste {
    if (verificarIdAnimal(idAnimal)) {
        Habitat habitatAnimal = getHabitat(idHabitat);
        Especie especieAnimal = getEspecie(idEspecie);
        Animal newAnimal = new Animal(idAnimal, nome, habitatAnimal, especieAnimal);
        _animais.add(newAnimal);
    } else {
        throw new AnimalJaExiste(idAnimal);
    }
  }

  /**
  * Creates a new species and a new animal associated with that species and habitat.
  * 
  * @param idAnimal    The ID of the new animal.
  * @param nome        The name of the new animal.
  * @param idEspecie   The ID of the new species.
  * @param idHabitat   The ID of the habitat where the animal resides.
  * @param nomeEspecie The name of the new species.
  * @throws AnimalJaExiste If the animal already exists.
  * @throws HabitatNaoExiste If the habitat does not exist.
  * @throws EspecieNaoExiste If the species does not exist.
  */
  public void novoAnimal(String idAnimal, String nome, String idEspecie, String idHabitat, String nomeEspecie) 
        throws AnimalJaExiste, HabitatNaoExiste, EspecieNaoExiste {
    if (verificarIdAnimal(idAnimal)) {
        Habitat habitatAnimal = getHabitat(idHabitat);
        novaEspecie(idEspecie, nomeEspecie);
        Especie especieAnimal = getEspecie(idEspecie);
        Animal newAnimal = new Animal(idAnimal, nome, habitatAnimal, especieAnimal);
        _animais.add(newAnimal);
    } else {
        throw new AnimalJaExiste(idAnimal);
    }
  }

  /**
  * Creates a new species with the given ID and name.
  * 
  * @param id    The ID of the new species.
  * @param nome  The name of the new species.
  */
  public void novaEspecie(String id, String nome) {
    if (verificarNomeEspecie(nome)) {
        Especie newEspecie = new Especie(id, nome);
        _especies.add(newEspecie);
        addIdEspecie(id);
    }
  }

  /**
  * Retrieves the animal corresponding to the given ID.
  * 
  * @param idAnimal The ID of the animal to retrieve.
  * @return The animal with the given ID.
  * @throws AnimalNaoExiste If the animal with the given ID does not exist.
  */
  public Animal getAnimal(String idAnimal) throws AnimalNaoExiste {
    for (Animal animal : _animais) {
        if (animal.getId().toLowerCase().equals(idAnimal.toLowerCase())) {
            return animal;
        }
    }
    throw new AnimalNaoExiste(idAnimal);
  }

  /**
  * Changes the habitat of an existing animal.
  * 
  * @param idAnimal     The ID of the animal whose habitat is to be changed.
  * @param habitatNovo  The new habitat for the animal.
  * @throws AnimalNaoExiste If the animal does not exist.
  */
  public void mudarHabitatDoAnimal(String idAnimal, Habitat habitatNovo) throws AnimalNaoExiste {
    if (!_alteracoes) {
        changeAlteracoes();
    }
    Animal animal = getAnimal(idAnimal);
    Habitat habitatAntigo = animal.getHabitat();
    habitatAntigo.removeAnimal(animal);
    animal.mudarHabitat(habitatNovo);
    habitatNovo.addAnimal(animal);
  }

  /**
  * Gets the satisfaction level of a specific animal.
  * 
  * @param idAnimal The ID of the animal.
  * @return The satisfaction level of the animal, rounded to the nearest integer.
  * @throws AnimalNaoExiste If the animal does not exist.
  */
  public int getSatisfacaoAnimal(String idAnimal) throws AnimalNaoExiste {
    Animal animal = getAnimal(idAnimal);
    if (animal != null) {
        return (int) Math.round(animal.getSatisfacao());
    }
    throw new AnimalNaoExiste(idAnimal);
  }

  /**
  * Retrieves all employees (sorted set).
  * 
  * @return A sorted set of all employees.
  */
  public SortedSet<Funcionario> getFuncionarios() {
    return _funcionarios;
  }

  /**
  * Creates a new employee of type Veterinarian or Treater.
  * 
  * @param idFuncionario The ID of the new employee.
  * @param nome          The name of the new employee.
  * @param tipo          The type of the employee, either "VET" (Veterinarian) or "TRT" (Treater).
  * @throws FuncionarioJaExiste If the employee already exists.
  */
  public void novoFuncionario(String idFuncionario, String nome, String tipo) throws FuncionarioJaExiste {
    if (verificarIdFuncionario(idFuncionario)) {
        if (tipo.equals("VET")) {
            Veterinario newVeterinario = new Veterinario(idFuncionario, nome);
            _funcionarios.add(newVeterinario);
        } else {
            Tratador newTratador = new Tratador(idFuncionario, nome);
            _funcionarios.add(newTratador);
        }
    } else {
        throw new FuncionarioJaExiste(idFuncionario);
    }
  }

  /**
  * Retrieves the employee corresponding to the given ID.
  * 
  * @param idFuncionario The ID of the employee to retrieve.
  * @return The employee with the given ID.
  * @throws FuncionarioNaoExiste If the employee with the given ID does not exist.
  */
  private Funcionario getFuncionario(String idFuncionario) throws FuncionarioNaoExiste {
    for (Funcionario funcionario : _funcionarios) {
        if (funcionario.getId().toLowerCase().equals(idFuncionario.toLowerCase())) {
            return funcionario;
        }
    }
    throw new FuncionarioNaoExiste(idFuncionario);
  }

  /**
  * Adds a new responsibility to the given employee, based on their type (Veterinarian or Treater).
  * The responsibility can be a species or a habitat.
  * 
  * @param idFuncionario      The ID of the employee.
  * @param idResponsabilidade The ID of the responsibility (species or habitat).
  * @throws NaoResponsabilidade If the responsibility cannot be assigned.
  * @throws FuncionarioNaoExiste If the employee does not exist.
  * @throws EspecieNaoExiste If the species does not exist (for veterinarians).
  * @throws HabitatNaoExiste If the habitat does not exist (for Treaters).
  */
  public void novaResponsabilidade(String idFuncionario, String idResponsabilidade)
        throws NaoResponsabilidade, FuncionarioNaoExiste, EspecieNaoExiste, HabitatNaoExiste {
    boolean notAdded = true;
    Funcionario funcionario = getFuncionario(idFuncionario);
    if (funcionario.getTipo().equals("VET")) {
        //this employee is a Vet, so we can downcast from Funcionario to Veterinario
        Veterinario veterinario = (Veterinario) funcionario;  //downcast
        Especie especie = getEspecie(idResponsabilidade);
        if (especie != null) {
            //we can add this specie to the species that the Vet treats
            veterinario.addEspecie(especie);
            especie.addVeterinario();
            notAdded = false;
            if (!_alteracoes) {
                changeAlteracoes();
            }
        }
    } else {
        //this employee is a Treater, so we can downcast from Funcionario to Tratador
        Tratador tratador = (Tratador) funcionario; //downcast
        Habitat habitat = getHabitat(idResponsabilidade);
        if (habitat != null) {
            //we can add this habitat to the habitats that the Treator treats
            tratador.addHabitat(habitat);
            habitat.addTratador();
            notAdded = false;
            if (!_alteracoes) {
                changeAlteracoes();
            }
        }
    }
    if (notAdded) {
        throw new NaoResponsabilidade(idFuncionario, idResponsabilidade);
    }
  }

    /**
   * Removes a responsibility from an employee. The responsibility can either be a species for a veterinarian 
   * or a habitat for a caretaker. 
   * @param idFuncionario The ID of the employee.
   * @param idResponsabilidade The ID of the species or habitat.
   * @throws NaoResponsabilidade If the responsibility does not exist.
   * @throws FuncionarioNaoExiste If the employee does not exist.
   * @throws EspecieNaoExiste If the species does not exist.
   * @throws HabitatNaoExiste If the habitat does not exist.
   */
  public void tirarResponsabilidade(String idFuncionario, String idResponsabilidade) throws NaoResponsabilidade, FuncionarioNaoExiste, EspecieNaoExiste, HabitatNaoExiste {
    Funcionario funcionario = getFuncionario(idFuncionario);
    // Check if the employee is a veterinarian
    if(funcionario.getTipo().equals("VET")){
      //This employee is a Vet, so we can downcast from Funcionario to Veterinario
      Veterinario veterinario = (Veterinario) funcionario;  //downcast
      Especie especie = getEspecie(idResponsabilidade);
      if(especie == null){
        throw new NaoResponsabilidade(idFuncionario, idResponsabilidade);
      }
      // Remove species from the veterinarian's responsibilities
      veterinario.removeEspecie(especie);
      especie.removeVeterinario();
      if(!_alteracoes){
        changeAlteracoes();
      }
    } else {
      //This employee is a Treater, so we can downcast from Funcionario to Tratadaor
      Tratador tratador = (Tratador) funcionario; //downcast
      Habitat habitat = getHabitat(idResponsabilidade);
      if(habitat == null){
        throw new NaoResponsabilidade(idFuncionario, idResponsabilidade);
      }
      // Remove habitat from the caretaker's responsibilities
      tratador.removeHabitat(habitat);
      habitat.removeTratador();
      if(!_alteracoes){
        changeAlteracoes();
      }
    }
  }

  /**
   * Gets the satisfaction level of an employee based on the current station.
   * @param idFuncionario The ID of the employee.
   * @return The satisfaction level as an integer.
   * @throws FuncionarioNaoExiste If the employee does not exist.
   */
  public int getSatisfacaoFuncionario(String idFuncionario) throws FuncionarioNaoExiste {
    Funcionario funcionario = getFuncionario(idFuncionario);
    if(funcionario != null){
      return (int) Math.round(funcionario.getSatisfacao(_estacao));
    }
    throw new FuncionarioNaoExiste(idFuncionario);
  }

  /**
   * Returns all habitats in the system.
   * @return A sorted set of all habitats.
   */
  public SortedSet<Habitat> getAllHabitats(){
    return _habitats;
  }

  /**
   * Adds a new habitat to the system.
   * @param idHabitat The ID of the habitat.
   * @param nome The name of the habitat.
   * @param area The area of the habitat.
   * @throws HabitatJaExiste If the habitat already exists.
   */
  public void novoHabitat(String idHabitat, String nome, int area) throws HabitatJaExiste {
    if(verificarIdHabitat(idHabitat)){
      Habitat newHabitat = new Habitat(idHabitat, nome, area);
      _habitats.add(newHabitat);
    } else {
      throw new HabitatJaExiste(idHabitat);
    }
  }

  /**
   * Changes the area of an existing habitat.
   * @param idHabitat The ID of the habitat.
   * @param novaArea The new area to set.
   * @throws HabitatNaoExiste If the habitat does not exist.
   */
  public void alterarAreaHabitat(String idHabitat, int novaArea) throws HabitatNaoExiste {
    Habitat habitat = getHabitat(idHabitat);
    habitat.changeArea(novaArea);
    if(!_alteracoes){
      changeAlteracoes();
    }
  }

  /**
   * Changes the influence of a habitat on a species. Influence can be positive, negative, or neutral.
   * @param idHabitat The ID of the habitat.
   * @param idEspecie The ID of the species.
   * @param influencia The type of influence: "POS", "NEG", or "NEU". POS=20, NEG=-20, NEU=0;
   * @throws HabitatNaoExiste If the habitat does not exist.
   * @throws EspecieNaoExiste If the species does not exist.
   */
  public void mudarInfluenciaHabitatEspecie(String idHabitat, String idEspecie, String influencia) throws HabitatNaoExiste, EspecieNaoExiste {
    Habitat habitatParaMudar = getHabitat(idHabitat);
    Especie especie = getEspecie(idEspecie);
    switch (influencia) {
      //the habitat now has positive influence in the species
      case "POS" -> mudarInfluenciaHabitatEspeciePOS(especie, habitatParaMudar);
      //the habitat now has negative influence in the species
      case "NEG" -> mudarInfluenciaHabitatEspecieNEG(especie, habitatParaMudar);
      //the habitat now has neutral influence in the species
      default -> mudarInfluenciaHabitatEspecieNEUTRA(especie, habitatParaMudar);
    }
    for(Animal animal : _animais){
      //loops through all animals in the hotel and checks if they are from the species and in the habitat
      if(animal.getHabitat().getId().equals(idHabitat)){
        //the animal is in the habitat, so we can now check if it is from the same specie
        if(animal.getEspecie().equals(especie)){
          //the habitat is from the species whose influence changed, so we now need to change the animal's adequation
          animal.changeAdequacaoHabitat();
        }
      }
    }
  }

  /**
   * Adds positive influence of a habitat on a species.
   * @param especie The species.
   * @param habitatParaMudar The habitat.
   */
  private void mudarInfluenciaHabitatEspeciePOS(Especie especie, Habitat habitatParaMudar) {
    especie.addHabitatAdequado(habitatParaMudar);
    for(Habitat habitat : especie.getHabitatsMaus()){
      //loops through all bad habitats for the specie and checks if the habitat was a bad one before changing
      if(habitat.equals(habitatParaMudar)){
        //the habitat had bad influence in the specie, so now we need to remove
        especie.removeHabitatMau(habitat);
        if(!_alteracoes){
          changeAlteracoes();
        }
      }
    }
  }

  /**
   * Adds negative influence of a habitat on a species.
   * @param especie The species.
   * @param habitatParaMudar The habitat.
   */
  private void mudarInfluenciaHabitatEspecieNEG(Especie especie, Habitat habitatParaMudar) {
    especie.addHabitatMau(habitatParaMudar);
    for(Habitat habitat : especie.getHabitatsAdequados()){
      //loops through all good habitats for the specie and checks if the habitat was a good one before changing
      if(habitat.equals(habitatParaMudar)){
        //the habitat had good influence in the specie, so now we need to remove
        especie.removeHabitatAdequado(habitat);
        if(!_alteracoes){
          changeAlteracoes();
        }
      }
    }
  }

  /**
   * Sets neutral influence of a habitat on a species.
   * @param especie The species.
   * @param habitatParaMudar The habitat.
   */
  private void mudarInfluenciaHabitatEspecieNEUTRA(Especie especie, Habitat habitatParaMudar) {
    for(Habitat habitat : especie.getHabitatsAdequados()){
      //loops through all good habitats for the specie and checks if the habitat was a good one before changing
      if(habitat.equals(habitatParaMudar)){
        //the habitat had good influence in the specie, so now we need to remove
        especie.removeHabitatAdequado(habitat);
        if(!_alteracoes){
          changeAlteracoes();
        }
        //ends the function since if it was a good one, it can not be a bad one too, so there is no need to continue
        return;
      }
    }
    for(Habitat habitat : especie.getHabitatsMaus()){
      //loops through all bad habitats for the specie and checks if the habitat was a bad one before changing
      if(habitat.equals(habitatParaMudar)){
        //the habitat had bad influence in the specie, so now we need to remove
        especie.removeHabitatMau(habitat);
        if(!_alteracoes){
          changeAlteracoes();
        }
      }
    }
  }

  /**
   * Adds a new tree to the system.
   * @param idArvore The ID of the tree.
   * @param nome The name of the tree.
   * @param idade The age of the tree.
   * @param dificuldadeBase The base difficulty of growing the tree.
   * @param tipo The type of tree: "PERENE" for perennial or "CADUCA" for deciduous.
   * @throws ArvoreJaExiste If the tree already exists.
   */
  public void novaArvore(String idArvore, String nome, int idade, int dificuldadeBase, String tipo) throws ArvoreJaExiste {
    if(verificarIdArvore(idArvore)){
      Arvore newArvore;
      if(tipo.equals("PERENE")){  // Perene
        newArvore = new Perene(idArvore, nome, idade, dificuldadeBase, _estacao);
        _arvores.add(newArvore);
      } else {  // Caduca
        newArvore = new Caduca(idArvore, nome, idade, dificuldadeBase, _estacao);
        _arvores.add(newArvore);
      }
    } else {
      throw new ArvoreJaExiste(idArvore);
    }
  }

  /**
   * Gets a tree by its ID.
   * @param idArvore The ID of the tree.
   * @return The tree object.
   * @throws ArvoreNaoExiste If the tree does not exist.
   */
  public Arvore getArvore(String idArvore) throws ArvoreNaoExiste {
    for(Arvore arvore : _arvores){
      if(arvore.getId().toLowerCase().equals(idArvore.toLowerCase())){
        return arvore;
      }
    }
    throw new ArvoreNaoExiste(idArvore);
  }

  /**
   * Plants an existing tree in a habitat. 
   * Used by the parser for tree creation, since it creates the trees before creating the habitat they are in.
   * @param idHabitat The ID of the habitat.
   * @param idArvore The ID of the tree.
   * @throws ArvoreNaoExiste If the tree does not exist.
   * @throws HabitatNaoExiste If the habitat does not exist.
   */
  public void plantarArvoreExistente(String idHabitat, String idArvore) throws ArvoreNaoExiste, HabitatNaoExiste { 
    if(!verificarIdArvore(idArvore)){
      plantarArvore(idHabitat, idArvore);
    } else {
      throw new ArvoreNaoExiste(idArvore);
    }
  }

  /**
   * Plants a non-existent tree in a habitat. 
   * Used by plantarArvores for tree creation, since we first create the Habitat and then we plant the tree in it.
   * @param idHabitat The ID of the habitat.
   * @param idArvore The ID of the tree.
   * @param nome The name of the tree.
   * @param idade The age of the tree.
   * @param dificuldadeBase The base difficulty of growing the tree.
   * @param tipo The type of tree: "PER" for perennial or "CAD" for deciduous.
   * @throws HabitatNaoExiste If the habitat does not exist.
   * @throws ArvoreNaoExiste If the tree does not exist.
   * @throws ArvoreJaExiste If the tree already exists.
   */
  public Arvore plantarArvoreNaoExistente(String idHabitat, String idArvore, String nome, int idade, int dificuldadeBase, String tipo) throws HabitatNaoExiste, ArvoreNaoExiste, ArvoreJaExiste {
    novaArvore(idArvore, nome, idade, dificuldadeBase, tipo);
    return plantarArvore(idHabitat, idArvore);
  }

  /**
   * Plants a tree in a habitat.
   * @param idHabitat The ID of the habitat.
   * @param idArvore The ID of the tree.
   * @throws ArvoreNaoExiste If the tree does not exist.
   * @throws HabitatNaoExiste If the habitat does not exist.
   */
  private Arvore plantarArvore(String idHabitat, String idArvore) throws ArvoreNaoExiste, HabitatNaoExiste {
    Arvore arvore = getArvore(idArvore);
    Habitat habitat = getHabitat(idHabitat);
    habitat.addArvore(arvore);
    if(!_alteracoes){
      changeAlteracoes();
    }
    return arvore;
  }


    /**
   * Retrieves the list of trees in a specific habitat.
   * @param habitat The habitat from which to retrieve the trees.
   * @return A list of trees in the habitat.
   */
  public SortedSet<Arvore> getArvoresHabitat(Habitat habitat){
    return habitat.getArvores();
  }

  /**
   * Retrieves all vaccines in the system.
   * @return A sorted set of all vaccines.
   */
  public SortedSet<Vacina> getAllVacinas(){
    return _vacinas;
  }

  /**
   * Adds a new vaccine to the system and associates it with species.
   * @param idVacina The ID of the vaccine.
   * @param nome The name of the vaccine.
   * @param idEspecies Array of species IDs that this vaccine can be applied to.
   * @throws VacinaJaExiste If the vaccine already exists.
   * @throws EspecieNaoExiste If one of the species does not exist.
   */
  public void novaVacina(String idVacina, String nome, String[] idEspecies) throws VacinaJaExiste, EspecieNaoExiste {
    if(verificarIdVacina(idVacina)){
      Vacina newVacina = new Vacina(idVacina, nome);
      for(String idEspecieSuposta : idEspecies){
        Especie especie = getEspecie(idEspecieSuposta);
        if(especie == null){
          throw new EspecieNaoExiste(idEspecieSuposta);
        }  
        newVacina.addEspecieSuposta(especie);
      }
      _vacinas.add(newVacina);
    } else {
      throw new VacinaJaExiste(idVacina);
    }
  }

  /**
   * Retrieves a vaccine by its ID.
   * @param idVacina The ID of the vaccine.
   * @return The vaccine object.
   * @throws VacinaNaoExiste If the vaccine does not exist.
   */
  private Vacina getVacina(String idVacina) throws VacinaNaoExiste {
    for(Vacina vacina : _vacinas){
      if(vacina.getId().toLowerCase().equals(idVacina.toLowerCase())){
        return vacina;
      }
    }
    throw new VacinaNaoExiste(idVacina);
  }

  /**
   * Vaccinates an animal with a specific vaccine administered by a veterinarian.
   * @param idVacina The ID of the vaccine.
   * @param idVeterinario The ID of the veterinarian.
   * @param idAnimal The ID of the animal.
   * @throws VeterinarioNaoAutorizado If the veterinarian is not authorized to vaccinate the animal's species.
   * @throws FuncionarioNaoExiste If the veterinarian does not exist.
   * @throws VacinaNaoExiste If the vaccine does not exist.
   * @throws AnimalNaoExiste If the animal does not exist.
   * @throws VeterinarioNaoExiste If the veterinarian does not exist.
   */
  public String vacinarAnimal(String idVacina, String idVeterinario, String idAnimal) throws VeterinarioNaoAutorizado, FuncionarioNaoExiste, VacinaNaoExiste, AnimalNaoExiste, VeterinarioNaoExiste {
    try {
      Animal animalVacinado = getAnimal(idAnimal);
      Veterinario veterinarioDeuVacina = (Veterinario) getFuncionario(idVeterinario);
      Vacina vacinaDada = getVacina(idVacina);
      boolean naoPodeDarVacina = true;
      for(Especie especie : veterinarioDeuVacina.getEspeciesPode()){
        if(especie.equals(animalVacinado.getEspecie())){
          naoPodeDarVacina = false;
          break;
        }
      }
      if(naoPodeDarVacina){
        throw new VeterinarioNaoAutorizado(idVeterinario, animalVacinado.getEspecie().getId());
      }
      vacinaDada.addVacinaContagem();
      if(!_alteracoes){
        changeAlteracoes();
      }
      RegistoVacina newRegisto = new RegistoVacina(veterinarioDeuVacina, animalVacinado, vacinaDada);
      animalVacinado.addVacina(newRegisto);
      veterinarioDeuVacina.addVacinacao(newRegisto);
      _registoVacinas.add(newRegisto);
      return newRegisto.getTermo();
    } catch (ClassCastException e) {
      throw new VeterinarioNaoExiste(idVeterinario);
    }
  }

  /**
   * Retrieves the list of animals in a specific habitat.
   * @param idHabitat The ID of the habitat.
   * @return A list of animals in the habitat.
   * @throws HabitatNaoExiste If the habitat does not exist.
   */
  public SortedSet<Animal> getAnimaisHabitat(String idHabitat) throws HabitatNaoExiste {
    Habitat habitat = getHabitat(idHabitat);
    return habitat.getAnimais();
  }

  /**
   * Retrieves the vaccination records of a specific animal.
   * @param idAnimal The ID of the animal.
   * @return A list of vaccination records for the animal.
   * @throws AnimalNaoExiste If the animal does not exist.
   */
  public ArrayList<RegistoVacina> getVacinasAnimal(String idAnimal) throws AnimalNaoExiste {
    Animal animal = getAnimal(idAnimal);
    return animal.getVacinacoes();
  }

  /**
   * Retrieves the vaccination records of a specific veterinarian.
   * @param idVeterinario The ID of the veterinarian.
   * @return A list of vaccination records administered by the veterinarian.
   * @throws VeterinarioNaoExiste If the veterinarian does not exist.
   * @throws FuncionarioNaoExiste If the employee does not exist.
   */
  public ArrayList<RegistoVacina> getVacinasVeterinario(String idVeterinario) throws VeterinarioNaoExiste, FuncionarioNaoExiste {
    if(!verificarIdFuncionario(idVeterinario)){
      Veterinario veterinario = (Veterinario) getFuncionario(idVeterinario);  // Downcast from Funcionario to Veterinario
      return veterinario.getVacinacoes();
    } else {
      throw new VeterinarioNaoExiste(idVeterinario);
    }
  }



  public ArrayList<RegistoVacina> getRegistosVacinas(){
    return _registoVacinas;
  }
}