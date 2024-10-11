package hva.app.habitat;

import hva.app.exception.*;
import hva.core.Hotel;
import hva.core.exception.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {
  private final Hotel _hotel;

  DoAddTreeToHabitat(Hotel receiver) throws CommandException {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    _hotel=receiver;
    //guarda o valor do input recebido no prompt com a chave idHabitat
    addStringField("idHabitat", Prompt.habitatKey());

    //guarda o valor do input recebido no prompt com a chave idArvore
    addStringField("idArvore", Prompt.treeKey());

    //guarda o valor do input recebido no prompt com a chave nomeArvore
    addStringField("nomeArvore", Prompt.treeName());

    //guarda o valor do input recebido no prompt com a chave idadeArvore
    addIntegerField("idadeArvore", Prompt.treeAge());

    //guarda o valor do input recebido no prompt com a chave dificuldadeArvore
    addIntegerField("dificuldadeArvore", Prompt.treeDifficulty());

    //guarda o valor do input recebido no prompt com a chave tipoArvore
    addStringField("tipoArvore", Prompt.treeType());
  }
  
  @Override
  protected void execute() throws CommandException {
    //carrega o valor do input recebido no prompt com a chave idHabitat para a variavel idHabitat
    String idHabitat = stringField("idHabitat");

    //carrega o valor do input recebido no prompt com a chave idArvore para a variavel idArvore
    String idArvore = stringField("idArvore");

    //carrega o valor do input recebido no prompt com a chave nomeArvore para a variavel nomeArvore
    String nomeArvore = stringField("nomeArvore");

    //carrega o valor do input recebido no prompt com a chave idadeArvore para a variavel idadeArvore
    int idadeArvore = integerField("idadeArvore");

    //carrega o valor do input recebido no prompt com a chave dificuldadeArvore para a variavel dificuldadeArvore
    int dificuldadeArvore = integerField("dificuldadeArvore");

    //carrega o valor do input recebido no prompt com a chave tipoArvore para a variavel tipoArvore
    String tipoArvore = stringField("tipoArvore");
    
    while(!tipoArvore.equals("PER") && !tipoArvore.equals("CAD")){
      //enquanto tipoArvore for diferente de "PER" (perene) ou "CAD" (caduca), pede 
      //repetidamente para o user corrigir, até ser um valor válido
      tipoArvore = Form.requestString(Prompt.treeType());
      }

    try {
      //cria uma arvore com id idArvore, nome nomeArvore, idade idadeArvore,
      //dificuldade base de limpeza dificuldadeArvore e tipo tipoArvore, 
      //e planta no habitat de id idHabitat
      _hotel.plantarArvoreNaoExistente(idHabitat, idArvore, nomeArvore,idadeArvore, dificuldadeArvore,tipoArvore);
      
    } catch (ArvoreNaoExiste e) {
      //ja ha arvore com este id
      throw new DuplicateTreeKeyException(idArvore);
      
    } catch (HabitatNaoExiste e) {
      //nao ha habitat com este id
      throw new UnknownHabitatKeyException(idHabitat);
    }
  }
}
