package Command.Commands;

import Command.Command;
import Command.MenuController;
import Facade.Accessor;
import Facade.XMLAccessor;
import Observer.ControlPresentation;
import Observer.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuOpen implements Command
{
    @Override
    public String getLabel()
    {
        return "Open";
    }

    @Override
    public char getShortcut()
    {
        return 'O';
    }

    @Override
    public void execute()
    {
        ControlPresentation.getInstance().clear();
        Frame frame = MenuController.getInstance().getFrame();
        Accessor xmlAccessor = new XMLAccessor();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(frame);
        try
        {
            Presentation presentation = ControlPresentation.getInstance().getPresentation();
            xmlAccessor.loadFile(presentation, fileChooser.getSelectedFile().getPath());
            presentation.setSlideNumber(0);
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(frame, MenuController.IO_EXCEPTION + exception, MenuController.LOAD_ERROR, JOptionPane.ERROR_MESSAGE);
        }
        frame.repaint();
    }
}
