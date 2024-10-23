package hva.app.main;

import hva.core.*;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;

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
    int estacaoAtual = _hotel.avancarEstacao();
    Display display = new Display();
    display.addLine(estacaoAtual);
    display.display();
  }
}
