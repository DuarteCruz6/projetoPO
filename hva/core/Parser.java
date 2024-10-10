package hva.core;

import hva.core.exception.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// FIXME add other imports if needed

/**
 * Esta solução assume que a classe Hotel já tem a seguinte funcionalidade

public class Hotel {
  public void registerAnimal(animalId, name, habitatId, speciesId) throws OneOrMoreCoreExceptions { ... }
  public void registerSpecies(speciesId, name) throws OneOrMoreCoreExceptions { ... }
  public void registerEmployee(employeeId, name, empType) throws OneOrMoreCoreExceptions { ... }
  public void addResponsibility(employeeId, responsibility) throws OneOrMoreCoreExceptions { ... }
  public void registerVaccine(vaccineId, name, String[] speciesIds) throws someCoreExceptionsOneOrMoreCoreExceptions { ... }
  public void createTree(TreeId, name, type, age, diff) throws OneOrMoreCoreExceptions { ... }
  public Habitat registerHabitat(habitatId, name, area) throws OneOrMoreCoreExceptions { ... }

Note-se que esta funcionalidade pode ser utilizada na concretização de alguns dos comandos.
Caso Hotel não tenha esta funcionalidade, então deverão substituir a invocação destes métodos
na classe Parser por uma ou mais linhas com uma funcionalidade semelhante.
Cada um destes métodos pode lançar uma ou mais excepções que irão corresponder aos erros que
podem acontecer ao nível do domínio surante a concretização da funcionalidade em causa.
**/

public class Parser{
  private final Hotel _hotel;

  Parser(Hotel h) {
    _hotel = h;
  }

  public void parseFile(String filename) throws IOException, UnrecognizedEntryException, FuncionarioJaExiste, AnimalJaExiste,
  ArvoreJaExiste, HabitatJaExiste, NaoResponsabilidade, VacinaJaExiste, ArvoreNaoExiste, HabitatNaoExiste, EspecieNaoExiste, FuncionarioNaoExiste {

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);

    }
  }

  private void parseLine(String line) throws UnrecognizedEntryException, FuncionarioJaExiste, AnimalJaExiste, 
  ArvoreJaExiste, HabitatJaExiste, NaoResponsabilidade, VacinaJaExiste, ArvoreNaoExiste, HabitatNaoExiste, EspecieNaoExiste, FuncionarioNaoExiste {

    String[] components = line.split("\\|");
    switch(components[0]) {
    case "ESPÉCIE" -> parseSpecies(components);
    case "ANIMAL" -> parseAnimal(components);
    case "ÁRVORE" -> parseTree(components,components[1]);
    case "HABITAT" -> parseHabitat(components,components[1]);
    case "TRATADOR" -> parseEmployee(components, "TRT");
    case "VETERINÁRIO" -> parseEmployee(components, "VET");
    case "VACINA" -> parseVaccine(components,components[1]);
    default -> throw new UnrecognizedEntryException ("tipo de entrada inválido: " + components[0]);
    }
  }

  // Parse a line with format ANIMAL|id|nome|idEspécie|idHabitat
  private void parseAnimal(String[] components) throws UnrecognizedEntryException, AnimalJaExiste, HabitatNaoExiste, EspecieNaoExiste {
    try {
      String id = components[1];
      String name = components[2];
      String speciesId = components[3];
      String habitatId = components[4];

      _hotel.novoAnimal(id, name, speciesId, habitatId);
    } catch (AnimalJaExiste e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format ESPÉCIE|id|nome
  // Parse a line with format ESPÉCIE|id|nome
  private void parseSpecies(String[] components) throws UnrecognizedEntryException {
    String id = components[1];
    String name = components[2];

    _hotel.novaEspecie(id, name);
  }
  
  // Parse a line with format TRATADOR|id|nome|idHabitat1,...,idHabitatN or
  // VETERINÁRIO|id|nome|idEspécie1,...,idEspécieN
  private void parseEmployee(String[] components, String empType) throws UnrecognizedEntryException, FuncionarioJaExiste, NaoResponsabilidade, FuncionarioNaoExiste, EspecieNaoExiste, HabitatNaoExiste {
    try {
      String id = components[1];
      String name = components[2];

      _hotel.novoFuncionario(id, name, empType);

      if (components.length == 4) {
        for(String responsibility : components[3].split(","))
          _hotel.novaResponsabilidade(components[1], responsibility);
      }
    } catch (FuncionarioJaExiste | NaoResponsabilidade e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format VACINA|id|nome|idEspécie1,...,idEspécieN
  // Parse a line with format VACINA|id|nome|idEspécie1,...,idEspécieN
  private void parseVaccine(String[] components, String empType) throws VacinaJaExiste, UnrecognizedEntryException, EspecieNaoExiste{
    try {
      String id = components[1];
      String name = components[2];
      String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
      _hotel.novaVacina(id, name, speciesIds);
    } catch (VacinaJaExiste  e){
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format ÁRVORE|id|nome|idade|dificuldade|tipo
  private void parseTree(String[] components, String line) throws UnrecognizedEntryException, ArvoreJaExiste {                           
    try {
      String id = components[1];                                                                                          // VERIFICAR BUG AQUI, NÃO É SUPOSTO RECEBER O ID DO HABITAT???
      String name = components[2];                                                                                        //
      int age = Integer.parseInt(components[3]);
      int diff = Integer.parseInt(components[4]);
      String type = components[5];

      _hotel.novaArvore(id, name, age, diff, type);
    }catch (ArvoreJaExiste e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format HABITAT|id|nome|área|idÁrvore1,...,idÁrvoreN
  private void parseHabitat(String[] components, String line) throws UnrecognizedEntryException, HabitatJaExiste, ArvoreJaExiste, ArvoreNaoExiste, HabitatNaoExiste {
    try {
      String idHabitat = components[1];
      String name = components[2];
      int area = Integer.parseInt(components[3]);
      _hotel.novoHabitat(idHabitat, name, area);
      if (components.length == 5) {
        String[] listOfTree = components[4].split(",");
        for (String idArvore : listOfTree){
          _hotel.plantarArvore(idHabitat, idArvore);
        }
      }
    } catch (HabitatJaExiste e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }
}

/**
 * Nota: O bloco catch presente nos vários métodos parse desta classe deverá ter em conta
 * as várias excepções que podem acontecer no contexto do bloco try em questão.
 * Estas excepções do domínio têm que ser definidas por cada grupo e devem representar situações
 * de erro específicas do domínio.
 **/