package hva.app.main;

import hva.core.Hotel;
import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for show the global satisfation of the current zoo hotel.
 **/
class DoShowGlobalSatisfaction extends Command<HotelManager> {
  private final Hotel _hotel;

  DoShowGlobalSatisfaction(HotelManager receiver) {
    super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
    _hotel=receiver.getHotel();
  }
  
  @Override
  protected final void execute() {
    //mostra a satisfacao total, isto é, a dos animais e dos funcionarios
    _display.addLine(Math.round(_hotel.getSatisfacaoTotal()));
    _display.display();
  }
}
