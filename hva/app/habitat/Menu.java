package hva.app.habitat;

import pt.tecnico.uilib.menus.CommandException;

public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(hva.core.Hotel receiver) throws CommandException {
    super(Label.TITLE, //
          new DoShowAllHabitats(receiver),
          new DoRegisterHabitat(receiver),
          new DoChangeHabitatArea(receiver),
          new DoChangeHabitatInfluence(receiver),
          new DoAddTreeToHabitat(receiver),
          new DoShowAllTreesInHabitat(receiver)
          );
  }
}
