package hva.app.main;

import hva.core.*;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  private final Hotel _hotel;

  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
    _hotel= receiver.getHotel();
  }

  @Override
  protected final void execute() {
    //avança a estacao do ano
    _hotel.avancarEstacao();
  }
}
