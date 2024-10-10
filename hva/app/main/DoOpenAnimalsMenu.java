package hva.app.main;

import hva.core.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for opening the menu for animal management.
 **/
class DoOpenAnimalsMenu extends Command<HotelManager> {
  DoOpenAnimalsMenu(HotelManager receiver) {
    super(hva.app.main.Label.MENU_ANIMALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    (new hva.app.animal.Menu(_receiver.getHotel())).open();
  }
}
