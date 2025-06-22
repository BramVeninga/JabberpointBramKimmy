package Command.Commands;

import Command.Command;
import Command.MenuController;
import Facade.Accessor;
import Facade.ImageDemo;
import Observer.ControlPresentation;
import Observer.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuDemoImage implements Command
{

    @Override
    public String getLabel()
    {
        return "Image Demo";
    }

    @Override
    public char getShortcut()
    {
        return '2';
    }

    @Override
    public void execute()
    {
        ControlPresentation.getInstance().clear();
        Frame frame = MenuController.getInstance().getFrame();
        Accessor demoPresentation = new ImageDemo();
        try
        {
            Presentation presentation = ControlPresentation.getInstance().getPresentation();
            demoPresentation.loadFile(presentation, null);
            presentation.setSlideNumber(0);
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(frame, MenuController.getInstance().getIoException() + exception, MenuController.getInstance().getLoadError(), JOptionPane.ERROR_MESSAGE);
        }
        frame.repaint();
    }
}
