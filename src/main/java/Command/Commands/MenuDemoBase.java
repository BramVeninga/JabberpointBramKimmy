package Command.Commands;

import Command.Command;
import Command.MenuController;
import Facade.Accessor;
import Facade.DemoPresentation;
import Observer.ControlPresentation;
import Observer.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuDemoBase implements Command
{
    @Override
    public String getLabel()
    {
        return "Basic Demo";
    }

    @Override
    public char getShortcut()
    {
        return '1';
    }

    @Override
    public void execute()
    {
        ControlPresentation.getInstance().clear();
        Frame frame = MenuController.getInstance().getFrame();
        Accessor demoPresentation = new DemoPresentation();
        try
        {
            Presentation presentation = ControlPresentation.getInstance().getPresentation();
            demoPresentation.loadFile(presentation, null);
            presentation.setSlideNumber(0);
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(frame, MenuController.IO_EXCEPTION + exception, MenuController.LOAD_ERROR, JOptionPane.ERROR_MESSAGE);
        }
        frame.repaint();
    }
}
