package hva.core;

import hva.core.exception.*;
import java.io.*;

// FIXME import classes

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager{
  /** The current zoo hotel */ // Should we initialize this field?
  private Hotel _hotel;
  private String _path;

  public HotelManager(){
    _hotel = new Hotel();
  }
  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    System.out.println("ESTA NO SAVE");
    if(_hotel.houveAlteracoes()){
      System.out.println("ESTA A IR BEM");
      try {
        saveAs(_path);
        _hotel.changeAlteracoes();
      } catch(UnavailableFileException| NullPointerException | MissingFileAssociationException | IOException e){
        throw new MissingFileAssociationException();
      } 
    }
    System.out.println("NÃO HOUVE ALTERACOES");
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   * @throws UnavailableFileException 
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException, UnavailableFileException {
    if(_hotel.houveAlteracoes()){
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
        oos.writeObject(_hotel);
        _path = filename;
        _hotel.changeAlteracoes();
      } catch(IOException e){
        throw new UnavailableFileException(filename);
      } 
    }
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      _hotel = (Hotel)ois.readObject();
      if(_hotel.houveAlteracoes()){
        _hotel.changeAlteracoes();
      }
      _path = filename;
    } catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException(filename);
    }
  }
  
  /**
   * Read text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entitiesi representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   * @throws FuncionarioJaExiste 
   * @throws UnknowSpeciesKeyException 
   * @throws VacinaJaExiste 
   * @throws AnimalJaExiste 
   * @throws HabitatJaExiste 
   * @throws NaoResponsabilidade 
   * @throws ArvoreJaExiste 
   * @throws FuncionarioNaoExiste 
   * @throws EspecieNaoExiste 
   * @throws HabitatNaoExiste 
   * @throws ArvoreNaoExiste
   **/
  public void importFile(String filename) throws ImportFileException, HabitatJaExiste, 
  AnimalJaExiste, VacinaJaExiste, 
   FuncionarioJaExiste, ArvoreJaExiste, NaoResponsabilidade, ArvoreNaoExiste, 
   HabitatNaoExiste, EspecieNaoExiste, FuncionarioNaoExiste {
    try {
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  }
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }

  public boolean houveAlteracoes(){
    return _hotel.houveAlteracoes();
  }

  public void novoHotel(){
    _hotel = new Hotel();
  }
}
  