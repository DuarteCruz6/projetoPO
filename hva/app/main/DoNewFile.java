package hva.app.main;

import hva.core.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  private final HotelManager _hotelManager;

  DoNewFile(HotelManager receiver) throws CommandException {
    super(Label.NEW_FILE, receiver);
    _hotelManager = receiver;
  }

  @Override
  protected final void execute() throws CommandException {
      _hotelManager.novoHotel();
  }
}
