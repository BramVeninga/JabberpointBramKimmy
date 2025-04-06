package Command.Commands;

import Command.Command;
import Command.MenuController;
import Observer.ControlPresentation;

import javax.naming.ldap.Control;

public class MenuNew implements Command
{
    @Override
    public String getLabel()
    {
        return "New";
    }

    @Override
    public char getShortcut()
    {
        return 'N';
    }

    @Override
    public void execute()
    {
        ControlPresentation.getInstance().clear();
        MenuController.getInstance().getFrame().repaint();
    }
}
