package hva.app.search;

import pt.tecnico.uilib.menus.CommandException;

public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(hva.core.Hotel receiver) throws CommandException {
    super(Label.TITLE, //
          new DoShowAnimalsInHabitat(receiver),
          new DoShowMedicalActsOnAnimal(receiver),
          new DoShowMedicalActsByVeterinarian(receiver),
          new DoShowWrongVaccinations(receiver)
          );
  }
}
