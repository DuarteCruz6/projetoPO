package hva.app.vaccine;

import pt.tecnico.uilib.menus.CommandException;

public class Menu extends pt.tecnico.uilib.menus.Menu {
  public Menu(hva.core.Hotel receiver) throws CommandException {
    super(Label.TITLE, //
          new DoShowAllVaccines(receiver),
          new DoRegisterVaccine(receiver),
          new DoVaccinateAnimal(receiver),
          new DoShowVaccinations(receiver)
          );
  }
}
