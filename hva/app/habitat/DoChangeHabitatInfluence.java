package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.EspecieNaoExiste;
import hva.core.exception.HabitatNaoExiste;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {
  private final Hotel _hotel;
  
  DoChangeHabitatInfluence(Hotel receiver) throws CommandException {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    _hotel=receiver;
    addStringField("idEspecie", hva.app.animal.Prompt.speciesKey());
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("influenciaHabitat", Prompt.habitatInfluence());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idEspecie = stringField("idEspecie");
    String idHabitat = stringField("idHabitat");
    String influenciaHabitat = stringField("influenciaHabitat");

    while(!influenciaHabitat.equals("POS") && !influenciaHabitat.equals("NEG") && !influenciaHabitat.equals("NEU")){
      influenciaHabitat = Form.requestString(Prompt.habitatInfluence());
    }
      
    try {
      _hotel.mudarInfluenciaHabitatEspecie(idHabitat, idEspecie, influenciaHabitat);
    } catch (HabitatNaoExiste e) {
      throw new UnknownHabitatKeyException(idHabitat);
    } catch (EspecieNaoExiste e) {
      throw new UnknownSpeciesKeyException(idEspecie);
    }
  }
}
