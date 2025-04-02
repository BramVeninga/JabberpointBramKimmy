package Command.Commands;

import Command.Command;
import Observer.ControlPresentation;

import javax.swing.*;

public class GoToPage implements Command
{
    protected static final String PAGE_NUMBER = "Page number?";

    @Override
    public String getLabel()
    {
        return "Go to slide";
    }

    @Override
    public char getShortcut()
    {
        return 'G';
    }

    @Override
    public void execute()
    {
        String pageNumberStr = JOptionPane.showInputDialog((Object) PAGE_NUMBER);
        int pageNumber = Integer.parseInt(pageNumberStr);
        ControlPresentation.getInstance().setSlideNumber(pageNumber - 1);
    }
}
