package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  private final ArrayList <String> _idEspecies;
  private final ArrayList <String> _nomeEspecies;
  private final ArrayList <String> _idVacinas;
  private final ArrayList <String> _idAnimais;
  private final ArrayList <String> _idHabitats;
  private final ArrayList <String> _idFuncionarios;
  private final ArrayList <String> _idArvores;
  private final Estacao _estacao;
  private final SortedSet <Arvore> _arvores;
  private final SortedSet <Animal> _animais;
  private final SortedSet <Funcionario> _funcionarios;
  private final SortedSet <Especie> _especies;
  private final SortedSet <Habitat> _habitats;
  private final SortedSet <Vacina> _vacinas;
  private final ArrayList <RegistoVacina> _registoVacinas;
  private boolean _alteracoes;

  public Hotel(){
    _idEspecies= new ArrayList<>();
    _nomeEspecies= new ArrayList<>();
    _idVacinas= new ArrayList<>();
    _idAnimais= new ArrayList<>();
    _idHabitats= new ArrayList<>();
    _idFuncionarios= new ArrayList<>();
    _idArvores= new ArrayList<>();
    _estacao = new Estacao();
    _arvores = new TreeSet<>();
    _animais = new TreeSet<>();
    _funcionarios = new TreeSet<>();
    _especies = new TreeSet<>();
    _habitats = new TreeSet<>();
    _vacinas = new TreeSet<>();
    _registoVacinas = new ArrayList<>();
    _alteracoes=false;
  }

  /**
   * Read text input file and create corresponding domain entities.
   * @throws FuncionarioNaoExiste 
   * @throws EspecieNaoExiste 
   * @throws HabitatNaoExiste 
   * @throws ArvoreNaoExiste 
   * @throws VacinaJaExiste 
   * @throws NaoResponsabilidade 
   * @throws HabitatJaExiste 
   * @throws ArvoreJaExiste 
   * @throws AnimalJaExiste 
   * @throws FuncionarioJaExiste 
   * @throws UnrecognizedEntryException 
   * @throws IOException 
   * @throws NoResponsibilityException 
   * @throws UnknownEmployeeKeyException 
   * @throws UnknownSpeciesKeyException 
   * @throws HabitatJaExisteException 
   * @throws UnknownTreeKeyException 
   **/

  void importFile(String filename) throws IOException, UnrecognizedEntryException, FuncionarioJaExiste,
  AnimalJaExiste, ArvoreJaExiste, HabitatJaExiste, NaoResponsabilidade, VacinaJaExiste, ArvoreNaoExiste, 
  HabitatNaoExiste, EspecieNaoExiste, FuncionarioNaoExiste{

    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

  boolean houveAlteracoes(){
    return _alteracoes;
  }

  void changeAlteracoes(){
    _alteracoes=!_alteracoes;
  }

  public ArrayList <String> getIdEspecies(){
    return _idEspecies;
  }

  public Estacao getEstacao(){
    return _estacao;
  }

  private void addIdEspecie(String idNovo){
    if(!_alteracoes){
      changeAlteracoes();
    }
    _idEspecies.add(idNovo);
  }

  boolean verificarNomeEspecie(String nomeNovo){
    for(String nomeAtual: _nomeEspecies){
      if(nomeAtual.toLowerCase().equals(nomeNovo.toLowerCase())){
        return false;
      }
    }
    addNomeEspecie(nomeNovo);
    return true;
  }

  private void addNomeEspecie(String nomeNovo){
    if(!_alteracoes){
      changeAlteracoes();
    }
    _nomeEspecies.add(nomeNovo);
  }

  boolean verificarIdAnimal(String idNovo){
    for(String idAtual: _idAnimais){
      if(idAtual.toLowerCase().equals(idNovo.toLowerCase())){
        return false;
      }
    }
    addIdAnimal(idNovo);
    return true;
  }

  private void addIdAnimal(String idNovo){
    if(!_alteracoes){
      changeAlteracoes();
    }
    _idAnimais.add(idNovo);
  }

  boolean verificarIdVacina(String idNovo){
    for(String idAtual: _idVacinas){
      if(idAtual.toLowerCase().equals(idNovo.toLowerCase())){
        return false;
      }
    }
    addIdVacina(idNovo);
    return true;
  }

  private void addIdVacina(String idNovo){
    if(!_alteracoes){
      changeAlteracoes();
    }
    _idVacinas.add(idNovo);
  }

  boolean verificarIdHabitat(String idNovo){
    for(String idAtual: _idHabitats){
      if(idAtual.toLowerCase().equals(idNovo.toLowerCase())){
        return false;
      }
    }
    addIdHabitat(idNovo);
    return true;
  }

  private void addIdHabitat(String idNovo){
    if(!_alteracoes){
      changeAlteracoes();
    }
    _idHabitats.add(idNovo);
  }

  boolean verificarIdFuncionario(String idNovo){
    for(String idAtual: _idFuncionarios){
      if(idAtual.toLowerCase().equals(idNovo.toLowerCase())){
        return false;
      }
    }
    addIdFuncionario(idNovo);
    return true;
  }

  private void addIdFuncionario(String idNovo){
    if(!_alteracoes){
      changeAlteracoes();
    }
    _idFuncionarios.add(idNovo);
  }

  boolean verificarIdArvore(String idNovo){
    for(String idAtual: _idArvores){
      if(idAtual.toLowerCase().equals(idNovo.toLowerCase())){
        return false;
      }
    }
    addIdArvore(idNovo);
    return true;
  }

  private void addIdArvore(String idNovo){
    if(!_alteracoes){
      changeAlteracoes();
    }
    _idArvores.add(idNovo);
  }

  public void avancarEstacao(){
    if(!_alteracoes){
      changeAlteracoes();
    }
    _estacao.skipEstacao();
    for(Arvore arvore : _arvores){
      arvore.checkAumentarIdade(_estacao);
    }
  }

  public double getSatisfacaoTotal(){
    double satisfacao=0;
    for (Animal animal: _animais){
      satisfacao+= animal.getSatisfacao();
    }
    for(Funcionario funcionario : _funcionarios){
      satisfacao += funcionario.getSatisfacao(_estacao);
    }
    return satisfacao;
  }

  public SortedSet<Animal> getAllAnimals(){
    return _animais;
  }

  public Habitat getHabitat(String idHabitat) throws HabitatNaoExiste{
    for(Habitat habitat : _habitats){
      if(habitat.getId().equals(idHabitat)){
        return habitat;
      }
    }
    throw new HabitatNaoExiste(idHabitat);
  }

  private Especie getEspecie(String idEspecie) throws EspecieNaoExiste{
    for(Especie especie : _especies){
      if(especie.getId().equals(idEspecie)){
        return especie;
      }
    }
    throw new EspecieNaoExiste(idEspecie);
  }

  //Criar animal com especie ja existente
  public void novoAnimal(String idAnimal, String nome, String idEspecie, String idHabitat) throws AnimalJaExiste,EspecieNaoExiste, HabitatNaoExiste{
    if(verificarIdAnimal(idAnimal)){
      Habitat habitatAnimal = getHabitat(idHabitat);
      Especie especieAnimal = getEspecie(idEspecie);
      especieAnimal.addAnimal();
      Animal newAnimal = new Animal(idAnimal,nome,habitatAnimal,especieAnimal);
      _animais.add(newAnimal);
    }else{
        throw new AnimalJaExiste(idAnimal);
    }
  }

  //Criar especie e animal novos 
  public void novoAnimal(String idAnimal, String nome, String idEspecie, String idHabitat, String nomeEspecie) throws AnimalJaExiste, HabitatNaoExiste, EspecieNaoExiste{
    if(verificarIdAnimal(idAnimal)){    
      Habitat habitatAnimal = getHabitat(idHabitat); 
      novaEspecie(idEspecie, nomeEspecie);     
      Especie especieAnimal = getEspecie(idEspecie);
      Animal newAnimal = new Animal(idAnimal,nome,habitatAnimal,especieAnimal);
      _animais.add(newAnimal);
      especieAnimal.addAnimal();
    }else{
        throw new AnimalJaExiste(idAnimal);
    }
  }

  public void novaEspecie(String id, String nome){
    if(verificarNomeEspecie(nome)){
      Especie newEspecie = new Especie(id,nome);
      _especies.add(newEspecie);
      newEspecie.addAnimal();
      addIdEspecie(id);
    }
  }

  public Animal getAnimal(String idAnimal) throws AnimalNaoExiste{
    for(Animal animal : _animais){
      if(animal.getId().equals(idAnimal)){
        return animal;
      }
    }
    throw new AnimalNaoExiste(idAnimal);
  }

  public void mudarHabitatDoAnimal(String idAnimal, Habitat habitatNovo) throws AnimalNaoExiste{
    if(!_alteracoes){
      changeAlteracoes();
    }
    Animal animal = getAnimal(idAnimal);
    Habitat habitatAntigo = animal.getHabitat();
    habitatAntigo.removeAnimal(animal);
    animal.mudarHabitat(habitatNovo);
    habitatNovo.addAnimal(animal);
  }

  public int getSatisfacaoAnimal(String idAnimal) throws AnimalNaoExiste{
    Animal animal = getAnimal(idAnimal);
    if(animal!=null){
      return (int) Math.round(animal.getSatisfacao());
    }
    throw new AnimalNaoExiste(idAnimal);
  }

  public SortedSet<Funcionario> getFuncionarios(){
    return _funcionarios;
  }


  public void novoFuncionario(String idFuncionario, String nome, String tipo) throws FuncionarioJaExiste{          //TIPO = VET ou TRT
    if(verificarIdFuncionario(idFuncionario)){
      if(tipo.equals("VET")){
        Veterinario newVeterinario = new Veterinario(idFuncionario,nome);
        _funcionarios.add(newVeterinario);
      }else{
        Tratador newTratador = new Tratador(idFuncionario,nome);
        _funcionarios.add(newTratador);
      }
    }else{
      throw new FuncionarioJaExiste(idFuncionario);
    }
  }

  private Funcionario getFuncionario(String idFuncionario) throws FuncionarioNaoExiste{
    for(Funcionario funcionario : _funcionarios){
      if(funcionario.getId().equals(idFuncionario)){
        return funcionario;
      }
    }
    throw new FuncionarioNaoExiste(idFuncionario);
  }
  public void novaResponsabilidade(String idFuncionario, String idResponsabilidade) throws NaoResponsabilidade, FuncionarioNaoExiste, EspecieNaoExiste, HabitatNaoExiste{    //idResponsabilidade pode ser um idAnimal ou idHabitat
    boolean notAdded = true;
    Funcionario funcionario = getFuncionario(idFuncionario);
    if(funcionario.getTipo().equals("VET")){                                       //este funcionario é um veterinario, logo vamos fazer downcast de funcionario para veterinario
      Veterinario veterinario = (Veterinario) funcionario;
      Especie especie = getEspecie(idResponsabilidade);
      if(especie!=null){
        veterinario.addEspecie(especie);                                            //vamos adicionar uma espécie às responsabilidades deste veterinario
        especie.addVeterinario();
        notAdded= false;
        if(!_alteracoes){
          changeAlteracoes();
        }
      }
    }else{                                                                        //este funcionario é um tratador, logo vamos fazer downcast de funcionario para tratador
      Tratador tratador = (Tratador) funcionario;
      Habitat habitat = getHabitat(idResponsabilidade);
      if(habitat!=null){
        tratador.addHabitat(habitat);                                             //vamos adicionar um habitat às responsabilidades deste veterinario
        habitat.addTratador();
        notAdded = false;
        if(!_alteracoes){
          changeAlteracoes();
        }
      }
    }
    if(notAdded){
      throw new NaoResponsabilidade(idFuncionario, idResponsabilidade);
    }
  }

  public void tirarResponsabilidade(String idFuncionario, String idResponsabilidade) throws NaoResponsabilidade, FuncionarioNaoExiste, EspecieNaoExiste, HabitatNaoExiste{  //idResponsabilidade pode ser um idAnimal ou idHabitat
    Funcionario funcionario = getFuncionario(idFuncionario);
    if(funcionario.getTipo().equals("VET")){                                       //este funcionario é um veterinario, logo vamos fazer downcast de funcionario para veterinario
      Veterinario veterinario = (Veterinario) funcionario;
      Especie especie = getEspecie(idResponsabilidade);
      if(especie==null){
        throw new NaoResponsabilidade(idFuncionario, idResponsabilidade);
      }
      veterinario.removeEspecie(especie);                                         //vamos remover uma espécie das responsabilidades deste veterinario
      especie.removeVeterinario();
      if(!_alteracoes){
        changeAlteracoes();
      }
    }
    else{                                                                  //este funcionario é um tratador, logo vamos fazer downcast de funcionario para tratador
      Tratador tratador = (Tratador) funcionario;
      Habitat habitat = getHabitat(idResponsabilidade);
      if(habitat==null){
        throw new NaoResponsabilidade(idFuncionario, idResponsabilidade);
      }
      tratador.removeHabitat(habitat);                                          //vamos remover um habitat das responsabilidades deste veterinario
      habitat.removeTratador();
      if(!_alteracoes){
        changeAlteracoes();
      }
    }
  }

  public int getSatisfacaoFuncionario(String idFuncionario) throws FuncionarioNaoExiste{
    Funcionario funcionario = getFuncionario(idFuncionario);
    if(funcionario!=null){
      return (int)Math.round(funcionario.getSatisfacao(_estacao));
    }
    throw new FuncionarioNaoExiste(idFuncionario);
  }

  public SortedSet <Habitat> getAllHabitats(){
    return _habitats;
  }

  public void novoHabitat(String idHabitat, String nome, int area) throws HabitatJaExiste{
    if (verificarIdHabitat(idHabitat)){
      Habitat newHabitat = new Habitat(idHabitat,nome,area);
      _habitats.add(newHabitat);
    }
  }

  public void alterarAreaHabitat(String idHabitat, int novaArea) throws HabitatNaoExiste{
    Habitat habitat = getHabitat(idHabitat);
    habitat.changeArea(novaArea);
    if(!_alteracoes){
      changeAlteracoes();
    }
  }

  public void mudarInfluenciaHabitatEspecie(String idHabitat, String idEspecie, String influencia) throws HabitatNaoExiste, EspecieNaoExiste{     //influencia = POS, NEG ou NEU;   POS=20, NEG=-20, NEU=0;
    Habitat habitatParaMudar = getHabitat(idHabitat);
    Especie especie = getEspecie(idEspecie);
    switch (influencia) {
      case "POS" -> {
        //o habitat agora tem influencia positiva na especie
        mudarInfluenciaHabitatEspeciePOS(especie,habitatParaMudar);
      }
      case "NEG" -> {
        //o habitat agora tem influencia negativa na especie
        mudarInfluenciaHabitatEspecieNEG(especie,habitatParaMudar);
      }
      default -> {
        //o habitat agora tem influencia neutra na especie
        mudarInfluenciaHabitatEspecieNEUTRA(especie,habitatParaMudar);
      }
    }
    for(Animal animal : _animais){                              //mudar a adequacao nos animais
      if(animal.getEspecie().equals(especie)){
        animal.changeAdequacaoHabitat();
      }
    }
  }

  private void mudarInfluenciaHabitatEspeciePOS(Especie especie, Habitat habitatParaMudar){
    especie.addHabitatAdequado(habitatParaMudar);
    for(Habitat habitat : especie.getHabitatsMaus()){
      if(habitat.equals(habitatParaMudar)){               //o habitat anteriormente tinha influencia negativa na especie
          especie.removeHabitatMau(habitat);
          if(!_alteracoes){
            changeAlteracoes();
          }
      }
    }
  }

  private void mudarInfluenciaHabitatEspecieNEG(Especie especie, Habitat habitatParaMudar){
    especie.addHabitatMau(habitatParaMudar);
    for(Habitat habitat : especie.getHabitatsAdequados()){
      if(habitat.equals(habitatParaMudar)){               //o habitat anteriormente tinha influencia positiva na especie
        especie.removeHabitatAdequado(habitat);
        if(!_alteracoes){
          changeAlteracoes();
        }
      }
    }
  }

  private void mudarInfluenciaHabitatEspecieNEUTRA(Especie especie, Habitat habitatParaMudar){
    for(Habitat habitat : especie.getHabitatsAdequados()){
      if(habitat.equals(habitatParaMudar)){               //o habitat anteriormente tinha influencia positiva na especie
        especie.removeHabitatAdequado(habitat);
        if(!_alteracoes){
          changeAlteracoes();
        }
      }
    }
    for(Habitat habitat : especie.getHabitatsMaus()){
      if(habitat.equals(habitatParaMudar)){               //o habitat anteriormente tinha influencia negativa na especie
        especie.removeHabitatMau(habitat);
        if(!_alteracoes){
          changeAlteracoes();
        }
      }
    }
  }

  public void novaArvore(String idArvore, String nome, int idade, int dificuldadeBase, String tipo)throws ArvoreJaExiste{ //tipo = PER ou CAD
      if(verificarIdArvore(idArvore)){
        Arvore newArvore;
        if(tipo.equals("PER")){                    //a arvore é perene
          newArvore = new Perene(idArvore,nome,dificuldadeBase,_estacao);
          _arvores.add(newArvore);
        }else{                                        //a arvore é caduca                                     
          newArvore = new Caduca(idArvore,nome,dificuldadeBase,_estacao);
          _arvores.add(newArvore);
        }
        //listarArvoreEspecifica(newArvore);
      }else{
        throw new ArvoreJaExiste(idArvore);
      }
   }
  
  public Arvore getArvore(String idArvore) throws ArvoreNaoExiste{
    for(Arvore arvore : _arvores){
      if(arvore.getId().equals(idArvore)){
        return arvore;
      }
    }
    throw new ArvoreNaoExiste(idArvore);
  }


  // Usada para o Parser, pois cria-se primeiro as arvores e só depois se cria o habitat que possui as arvores
  public void plantarArvoreExistente(String idHabitat, String idArvore) throws ArvoreNaoExiste, HabitatNaoExiste{ 
    if(!verificarIdArvore(idArvore)){
      Arvore arvore = getArvore(idArvore);
      Habitat habitat = getHabitat(idHabitat);
      habitat.addArvore(arvore);
      if(!_alteracoes){
        changeAlteracoes();
      }
    }else{
      throw new ArvoreNaoExiste(idArvore);
    }
 }

 //usada para o plantarArvores, pois cria-se primeiro o habitat e só depois se cria a arvore que vai ser colocada no habitat
 public void plantarArvoreNaoExistente(String idHabitat, String idArvore, String nome, int idade, int dificuldadeBase, String tipo) throws ArvoreNaoExiste, HabitatNaoExiste{ 
  if(verificarIdArvore(idArvore)){
    try {
      novaArvore(idArvore, nome, idade, dificuldadeBase, tipo);
    } catch (ArvoreJaExiste e) {
      // nunca ira acontecer
    }
    Arvore arvore = getArvore(idArvore);
    Habitat habitat = getHabitat(idHabitat);
    habitat.addArvore(arvore);
    if(!_alteracoes){
      changeAlteracoes();
    }
  }else{
    throw new ArvoreNaoExiste(idArvore);
  }
}

  public ArrayList <Arvore> getArvoresHabitat(Habitat habitat){
    return habitat.getArvores();
  }

  public SortedSet <Vacina> getAllVacinas(){
    return _vacinas;
  }

  public void novaVacina(String idVacina, String nome, String[] idEspecies) throws VacinaJaExiste, EspecieNaoExiste{
    if(verificarIdVacina(idVacina)){
      Vacina newVacina = new Vacina(idVacina,nome);
      for(String idEspecieSuposta : idEspecies){
        Especie especie = getEspecie(idEspecieSuposta);
        if(especie==null){
          throw new EspecieNaoExiste(idEspecieSuposta);
        }  
        newVacina.addEspecieSuposta(especie);
      }
      _vacinas.add(newVacina);
    }else{
      throw new VacinaJaExiste(idVacina);
    }
  }

  private Vacina getVacina(String idVacina) throws VacinaNaoExiste{
    for(Vacina vacina : _vacinas){
      if(vacina.getId().equals(idVacina)){
        return vacina;
      }
    }
    throw new VacinaNaoExiste(idVacina);
  }

  public void vacinarAnimal(String idVacina, String idVeterinario, String idAnimal) throws VeterinarioNaoAutorizado, FuncionarioNaoExiste, VacinaNaoExiste, AnimalNaoExiste, VeterinarioNaoExiste{
    Animal animalVacinado=getAnimal(idAnimal);
    Veterinario veterinarioDeuVacina=(Veterinario) getFuncionario(idVeterinario);
    Vacina vacinaDada=getVacina(idVacina);
    if(veterinarioDeuVacina==null){
      throw new VeterinarioNaoExiste(idVeterinario);
    }

    boolean naoPodeDarVacina = true;
    for(Especie especie : veterinarioDeuVacina.getEspeciesPode()){
      if(especie.equals(animalVacinado.getEspecie())){
        naoPodeDarVacina=false;
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
  }

  public ArrayList<Animal> getAnimaisHabitat(String idHabitat) throws HabitatNaoExiste{
    Habitat habitat = getHabitat(idHabitat);
    return habitat.getAnimais();
  }

  public ArrayList<RegistoVacina> getVacinasAnimal(String idAnimal) throws AnimalNaoExiste{
    Animal animal = getAnimal(idAnimal);
    return animal.getVacinacoes();
  } 
  

  public ArrayList<RegistoVacina> getVacinasVeterinario(String idVeterinario) throws VeterinarioNaoExiste, FuncionarioNaoExiste{
    if(!verificarIdFuncionario(idVeterinario)){
      Veterinario veterinario = (Veterinario) getFuncionario(idVeterinario);  //downcast de Funcionario para Veterinario
      return veterinario.getVacinacoes();
    }else{
      throw new VeterinarioNaoExiste(idVeterinario);
    }
  }

  public ArrayList<RegistoVacina> getVacinasMas(){
    return _registoVacinas;
  }
}
