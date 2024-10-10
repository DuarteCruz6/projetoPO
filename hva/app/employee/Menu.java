package hva.app.employee;

import pt.tecnico.uilib.menus.CommandException;

public class Menu extends pt.tecnico.uilib.menus.Menu {
  public Menu(hva.core.Hotel receiver) throws CommandException {
    super(Label.TITLE, //
          new DoShowAllEmployees(receiver),
          new DoRegisterEmployee(receiver),
          new DoAddResponsibility(receiver),
          new DoRemoveResponsibility(receiver),
          new DoShowSatisfactionOfEmployee(receiver)
          );
  }
}
