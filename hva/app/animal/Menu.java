package hva.app.animal;

import pt.tecnico.uilib.menus.CommandException;

public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(hva.core.Hotel receiver) throws CommandException{
    super(Label.TITLE, //
          new DoShowAllAnimals(receiver),
          new DoRegisterAnimal(receiver),
          new DoTransferToHabitat(receiver),
          new DoShowSatisfactionOfAnimal(receiver)
          );
  }
  
}
