package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.EspecieNaoExiste;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {
  private final Hotel _hotel;
  
  DoChangeHabitatInfluence(Hotel receiver) throws CommandException {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    _hotel=receiver;
    //guarda o valor do input recebido no prompt com a chave idHabitat
    addStringField("idHabitat", Prompt.habitatKey());
    //guarda o valor do input recebido no prompt com a chave idEspecie
    addStringField("idEspecie", hva.app.animal.Prompt.speciesKey());
    //guarda o valor do input recebido no prompt com a chave influenciaHabitat
    addStringField("influenciaHabitat", Prompt.habitatInfluence());
  }
  
  @Override
  protected void execute() throws CommandException {
    //carrega o valor do input recebido no prompt com a chave idEspecie para a variavel idEspecie
    String idEspecie = stringField("idEspecie");
    //carrega o valor do input recebido no prompt com a chave idHabitat para a variavel idHabitat
    String idHabitat = stringField("idHabitat");
    //carrega o valor do input recebido no prompt com a chave influenciaHabitat para a variavel influenciaHabitat
    String influenciaHabitat = stringField("influenciaHabitat");


    while(!influenciaHabitat.equals("POS") && !influenciaHabitat.equals("NEG") 
    && !influenciaHabitat.equals("NEU")){
      //enquanto tipoFuncionario for diferente de "POS" (positivo) ou "NEG" (negativo) ou NEU (neutro),
      //pede repetidamente para o user corrigir, até ser um valor válido
      influenciaHabitat = Form.requestString(Prompt.habitatInfluence());
    }
      
    try {
      //muda a influencia do habitat de id idHabitat na especie de id idEspecie para o valor influenciaHabitat
      _hotel.mudarInfluenciaHabitatEspecie(idHabitat, idEspecie, influenciaHabitat);

    } catch (HabitatNaoExiste e) {
      //nao ha habitat com este id
      throw new UnknownHabitatKeyException(idHabitat);

    } catch (EspecieNaoExiste e) {
      //nao ha especie com este id
      throw new UnknownSpeciesKeyException(idEspecie);
    }
  }
}
