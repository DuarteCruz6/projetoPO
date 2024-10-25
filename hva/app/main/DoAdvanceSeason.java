package hva.app.main;

import hva.core.*;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  private final HotelManager _hotelManager;

  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
    _hotelManager = receiver;
  }

  @Override
  protected final void execute() {
    //avança a estacao do ano
    int estacaoAtual = _hotelManager.getHotel().avancarEstacao();
    _display.addLine(estacaoAtual);
    _display.display();
  }
}
