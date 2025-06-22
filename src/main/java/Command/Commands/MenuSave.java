package Command.Commands;

import Command.Command;
import Command.MenuController;
import Facade.Accessor;
import Facade.XMLAccessor;
import Observer.ControlPresentation;

import javax.swing.*;
import java.io.IOException;

public class MenuSave implements Command
{
    @Override
    public String getLabel()
    {
        return "Save";
    }

    @Override
    public char getShortcut()
    {
        return 'S';
    }

    @Override
    public void execute()
    {
        Accessor xmlAccessor = new XMLAccessor();
        try
        {
            xmlAccessor.saveFile(ControlPresentation.getInstance().getPresentation(), MenuController.getInstance().getSaveFile());
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(MenuController.getInstance().getFrame(), MenuController.getInstance().getIoException() + exception, MenuController.getInstance().getLoadError(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
