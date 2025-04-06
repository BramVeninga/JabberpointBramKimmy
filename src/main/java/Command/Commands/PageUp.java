package Command.Commands;

import Command.Command;
import Observer.ControlPresentation;

public class PageUp implements Command
{
    @Override
    public String getLabel()
    {
        return "Next slide";
    }

    @Override
    public char getShortcut()
    {
        return '.';
    }

    @Override
    public void execute()
    {
        ControlPresentation.getInstance().nextSlide();
    }
}
