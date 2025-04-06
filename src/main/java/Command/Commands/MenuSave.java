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
            xmlAccessor.saveFile(ControlPresentation.getInstance().getPresentation(), MenuController.SAVE_FILE);
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(MenuController.getInstance().getFrame(), MenuController.IO_EXCEPTION + exception, MenuController.SAVE_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
}
