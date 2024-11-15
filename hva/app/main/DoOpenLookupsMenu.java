package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for opening the menu for lookups.
 **/
class DoOpenLookupsMenu extends Command<HotelManager> {

  DoOpenLookupsMenu(HotelManager receiver) {
    super(hva.app.main.Label.MENU_LOOKUPS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    (new hva.app.search.Menu(_receiver.getHotel())).open();
  }
}
