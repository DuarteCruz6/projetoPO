package hva.core;

import hva.core.exception.*;
import java.io.*;

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager{
  /** The current zoo hotel */ // Should we initialize this field?
  private Hotel _hotel;
  private String _path;

  public HotelManager(){
    //initializes a new hotel
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
    //saves the current hotel in the current file

    //checks if there were any changes
    if(houveAlteracoes()){
      //there were changes, so we need to save them
      try {
        //saves to the current path
        saveAs(_path);
        //changes the _alteracoes boolean on hotel, because we saved the changes that were made
        //therefore there are no new changes
        _hotel.changeAlteracoes();

      } catch(UnavailableFileException| NullPointerException | MissingFileAssociationException | IOException e){
        //if there is an exception, we have to throw the MissingFileAssociationException 
        throw new MissingFileAssociationException();
      } 
    }
    //there were no changes, so we don't need to save
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
    //saves the current hotel on a newly created file whose name is the value of the variable filename

    //checks if there were any changes
    if(houveAlteracoes()){
      //there were changes, so we need to save them
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
        //tries to save the current hotel to a new file whose name is the value of the variable filename
        oos.writeObject(_hotel);
        //saves the new path on the variable _path
        _path = filename;
        //changes the _alteracoes boolean on hotel, because we saved the changes that were made
        //therefore there are no new changes
        _hotel.changeAlteracoes();
      } catch(IOException e){
        //if there is an exception, we have to throw the UnavailableFileException 
        throw new UnavailableFileException(filename);
      } 
    }
    //there were no changes, so we don't need to save
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    //load an hotel in a file whose name is the value of the variable filename

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      //tries to open the file and get its values
      //saves the file's Hotel in _hotel

      _hotel = (Hotel)ois.readObject();

      //checks if there were variable _alteracoes in this new Hotel, because we just saved this Hotel's changes
      if(houveAlteracoes()){
        //changes the _alteracoes boolean on hotel, because we saved the changes that were made
        _hotel.changeAlteracoes();
      }
      //saves the new path on the variable _path
      _path = filename;
    } catch (IOException | ClassNotFoundException e) {
      //if there is an exception, we have to throw the UnavailableFileException 
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
    //imports a file, whose name is the value of the variable filename, with values from an hotel
    try {
      //tries to import the file whose name is the value of the variable filename
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      //if there is an exception, we have to throw the ImportFileException 
      throw new ImportFileException(filename, e);
    }
  }
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    //returns the current hotel
    return _hotel;
  }

  public boolean houveAlteracoes(){
    //checks if there were any changes in the current hotel
    return _hotel.houveAlteracoes();
  }

  public void novoHotel(){
    //creates a new hotel
    _hotel = new Hotel();
  }
}
  