package hva.app.main;

import pt.tecnico.uilib.menus.CommandException;

public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(hva.core.HotelManager receiver) throws CommandException {
    super(Label.TITLE, //
          new DoNewFile(receiver),
          new DoOpenFile(receiver),
          new DoSaveFile(receiver),
          new DoAdvanceSeason(receiver),
          new DoShowGlobalSatisfaction(receiver),
          new DoOpenAnimalsMenu(receiver),
          new DoOpenEmployeesMenu(receiver),
          new DoOpenHabitatsMenu(receiver),
          new DoOpenVaccinesMenu(receiver),
          new DoOpenLookupsMenu(receiver)
          );
  }
}
